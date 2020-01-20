package com.controller.technology;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.dao.TechnologyDAO;
import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.github.dandelion.datatables.core.ajax.DatatablesResponse;
import com.github.dandelion.datatables.extras.spring3.ajax.DatatablesParams;
import com.image.imagemagick.Imagemagick;
import com.models.Technology;
import com.models.User;
import com.session.SessionManagement;

@Controller
@RequestMapping(value={"/user/", "/admin/"})
public class TechnologyController {

	@Autowired
	private TechnologyDAO TechnologyDAO;
	
	@Autowired
	private Imagemagick imagemagick;
	
	@RequestMapping(value = "addTechnologyForm", method = RequestMethod.GET)
	public ModelAndView adminAddTechnologyForm(ModelMap model,HttpServletRequest request) {
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getTechnology()!=true){
			return new ModelAndView("403");	
		}
		model.put("program", new Technology());
		return new ModelAndView("addTechnologyProgramAdminTile");
	}
	
	@RequestMapping(value = "addTechnology", method = RequestMethod.POST)
	public ModelAndView adminAddTechnology(ModelMap model,@Valid @Validated @ModelAttribute("Technology") Technology technology,BindingResult bindingResult,HttpServletRequest request) {
		if(SessionManagement.isSessionAlive()!=true){
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getTechnology()!=true){
			return new ModelAndView("403");	
		}
		if(bindingResult.hasErrors())
		{
			//System.out.println(bindingResult.toString());
			model.put("program", technology);
			return new ModelAndView("addTechnologyProgramAdminTile");
		}
		if (technology.getFile()!=null && !technology.getFile().isEmpty()) {
			if(technology.getFile().getContentType().equalsIgnoreCase("image/jpg") || technology.getFile().getContentType().equalsIgnoreCase("image/jpeg")){
				double size = technology.getFile().getSize();
				double kilobytes = (size / 1024);
				double megabytes = (kilobytes / 1024);
				int i = 0;
			    String filename = Integer.toString(i);
			    File f = new File(filename);
			    while (f.exists()) {
			        i++;
			        filename = Integer.toString(i);
			        f = new File(filename);
			    }
				if(megabytes<2){
					byte[] bytes;
					try {
						bytes = technology.getFile().getBytes();
						String dirPath=request.getRealPath("/")+"themes\\technology\\"+new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss",Locale.ENGLISH).format(new Date());
						File dir = new File(dirPath);
						if(dir.exists()){
							model.put("error", "Please Enter another folder Name because this folder name already exists.");
						}else{
							dir.mkdir();
						 String filePath = request.getRealPath("/")+"themes\\technology\\"+new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss",Locale.ENGLISH).format(new Date())+"\\"+f.getName()+".jpeg";
			                
						 //System.out.println(filePath);
						 BufferedOutputStream stream =
			                        new BufferedOutputStream(new FileOutputStream(new File(filePath)));
			                stream.write(bytes);
			                stream.close();
			                //Technology.setPhotourl(filePath);
			                technology.setPhotourl("themes\\technology\\"+new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss",Locale.ENGLISH).format(new Date())+"\\"+f.getName()+".jpeg");
			                technology.setFolder_url(dirPath);
			                technology.setFolder_relative_url("themes\\technology\\"+new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss",Locale.ENGLISH).format(new Date()));
			                technology.setFrequency(0l);
			                TechnologyDAO.save(technology);
			                model.put("msg", "File uploaded successfully");
			                model.put("filePath", "../themes/technology/"+new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss",Locale.ENGLISH).format(new Date())+"/"+f.getName()+".jpeg");

			                try{
				            	   imagemagick.convert(filePath, "jpeg",request);
				               }catch(Exception ex){
				            	   model.put("error", "File creation error for resized");
				               }
			                try{
				            	   imagemagick.watermark(filePath, "jpeg",request);
				               }catch(Exception ex){
				            	   model.put("error", "File creation error for watermark");
				               }
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	               
	                	
				}
				else{
		        	model.put("error", "Please select File less than 2 MB");
		        }
			}
			else{
	        	model.put("error", "Please select JPEG File");
	        }
		}else{
			model.put("error", "Please select Main Album Photo");
		}
		model.put("program",technology);
		////System.out.println("here");
		return new ModelAndView("addTechnologyProgramAdminTile");
	}
	
	@RequestMapping(value="viewTechnologies",method=RequestMethod.GET)
	public ModelAndView viewDepartment(ModelMap model,@RequestParam(value = "msg", required = false) String msg,@RequestParam(value = "error", required = false) String error,HttpServletRequest request)
	{
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getTechnology()!=true){
			return new ModelAndView("403");	
		}
		if (error != null) {
			model.put("error", error);
		}
		if (msg != null) {
			model.put("msg", msg);
		}
		return new ModelAndView("viewTechnologyProgramsTile");
	}
	
	@RequestMapping(value = "getTechnologyList")
	public @ResponseBody DatatablesResponse<Technology> findAll(@DatatablesParams DatatablesCriterias criterias) {
	   DataSet<Technology> dataSet = TechnologyDAO.findTechnologyWithDatatablesCriterias(criterias);
	   return DatatablesResponse.build(dataSet, criterias);
	}
	
	@RequestMapping(value = "editTechnologyForm", method = RequestMethod.POST)
	public ModelAndView editTechnologyForm(ModelMap model,@RequestParam(value ="programId", required = false) Long programId,HttpServletRequest request) {
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getTechnology()!=true){
			return new ModelAndView("403");	
		}
		model.put("program", TechnologyDAO.findByDeptId(programId));
		return new ModelAndView("editTechnologyAdminTile");
	}
	
	@RequestMapping(value = "updateTechnology", method = RequestMethod.POST)
	public ModelAndView updateTechnology(ModelMap model,@Valid @Validated @ModelAttribute("Technology") Technology Technology,BindingResult bindingResult,HttpServletRequest request) {
		if(SessionManagement.isSessionAlive()!=true){
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getTechnology()!=true){
			return new ModelAndView("403");	
		}
		if(bindingResult.hasErrors())
		{
			model.put("program", Technology);
			return new ModelAndView("editTechnologyProgramAdminTile");
		}
		Technology p = TechnologyDAO.findByDeptId(Technology.getId());
		p.setProgramName(Technology.getProgramName());
		p.setDescription(Technology.getDescription());
		p.setLocation(Technology.getLocation());
		p.setPosted_by(Technology.getPosted_by());
		TechnologyDAO.update(p);
		return new ModelAndView("redirect:viewTechnologies");
	}
	
	@RequestMapping(value = "selectTechnologyForm", method = RequestMethod.POST)
	public ModelAndView selectTechnologyForm(ModelMap model,@RequestParam(value = "programIdsel", required = false) Long programIdsel,@RequestParam(value = "msg", required = false) String msg,@RequestParam(value = "error", required = false) String error,HttpServletRequest request) throws IOException {
		//System.out.println(programIdsel);
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getTechnology()!=true){
			return new ModelAndView("403");	
		}
		if (error != null) {
			model.put("error", error);
		}
		if (msg != null) {
			model.put("msg", msg);
		}
		if (error != null || programIdsel==null || programIdsel==0) {
			return new ModelAndView("redirect:viewTechnologies?error=Argument Invalid");
		}
		Technology p = TechnologyDAO.findByDeptId(programIdsel);
		ArrayList<String> fileList = new ArrayList<String>();
		
		String dirPath = p.getFolder_url();//request.getRealPath("/")+"themes\\"+p.getTechnologyName()+"\\";
		File folder = new File(dirPath);
		if(folder.exists()){
		for (final File fileEntry : folder.listFiles()) {
			fileList.add(fileEntry.getName());
			//System.out.println(fileEntry.getName());
	    }
		}
		model.put("fileList", fileList);
		model.put("fileDir", p.getFolder_relative_url());
		model.put("selectProgram", p);
		return new ModelAndView("selectTechnologyProgramsTile");
		
	}
	@RequestMapping(value="moreTechnologyPhotos")
	public ModelAndView upload(ModelMap model,@RequestParam(value ="photoId", required = false) Long photoId,HttpServletRequest request){		 
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getTechnology()!=true){
			return new ModelAndView("403");	
		}
		model.put("photo", TechnologyDAO.findByDeptId(photoId));
		model.put("extraParam", "&photoId="+photoId);
		
		return new ModelAndView("addMorePhotoTechnologyTile");
	}
	
	@RequestMapping(value = "douploadTechnologyPhoto", method = RequestMethod.POST)
    public ModelAndView uploadFileHandler(@Valid @Validated @ModelAttribute("uploadPhoto") Technology Technology,BindingResult bindingResult,@RequestParam("file") MultipartFile file,ModelMap model,HttpServletRequest request) {
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getTechnology()!=true){
			return new ModelAndView("403");	
		}
		if (!file.isEmpty()) {
            try {
            	if(file.getContentType().equalsIgnoreCase("image/jpg") || file.getContentType().equalsIgnoreCase("image/jpeg")){
	                byte[] bytes = file.getBytes(); 
	                int i = 0;
	                String filename = request.getParameter("addPhoto")+"\\"+Integer.toString(i)+".jpeg";
				    File f = new File(filename);
				    while(f.exists()) {
				        i++;
				        filename = request.getParameter("addPhoto")+"\\"+Integer.toString(i)+".jpeg";
				        f = new File(filename);
				    }
	              //  String rootPath = System.getProperty("catalina.home");
	               // File dir = new File(rootPath + File.separator + "tmpFiles");
				    ////System.out.println(request.getRealPath("/")+"themes\\"+request.getParameter("addPhoto")+"\\"+f.getName()+);
	                File dir = new File(request.getParameter("addPhoto"));
	                if (!dir.exists())
	                    dir.mkdirs();
	                ////System.out.println("file Name : " + dir);
	                // Create the file on server
	                //java.util.Date date= new java.util.Date();
	                file.toString();
	                String fname = dir+"\\"+f.getName().replace("jpg","jpeg");
	              // String fname = dir.getAbsolutePath()+ File.separator + (new Timestamp(date.getTime())).toString().replace(":", "").toString().replace(".", ".").toString().replace(" ","").toString().replace("-","").toString()+".txt";
	                
	               // String filename = file.getOriginalFilename();
	                File serverFile = new File(fname);
	                //System.out.println("f Name : " + fname);
	                
	                BufferedOutputStream stream = new BufferedOutputStream(
	                        new FileOutputStream(serverFile));
	                stream.write(bytes);
	                stream.close();

	                if(fname.endsWith("0.jpeg"))
	                {
		                try{
			            	   imagemagick.convert(fname, "jpeg",request);
			               }catch(Exception ex){
			            	   model.put("error", "File creation error for resized");
			               }
		                try{
			            	   imagemagick.watermark(fname, "jpeg",request);
			               }catch(Exception ex){
			            	   model.put("error", "File creation error for watermark");
			               }
	                }
	                

	                Technology = TechnologyDAO.findByDeptId(Technology.getId());
	                Technology.setPhotourl(fname);
	                TechnologyDAO.update(Technology);
	                
	                //Technology.setPhotourl(fname);
	                //Technology.setFile(file);
	               // //System.out.println("fileName     "   +uploadFile.getFileName());
	               
	                //TechnologyDAO.save(Technology);
	               // //System.out.println("saveeeeeeeeeee            "   +  Technology.getId());
            	}          	
          
            } catch (Exception e) {
                model.put("error", e.getMessage());
	        	return new ModelAndView("addMorePhotoTechnologyTile");
            }
           // showAllItemDAO.save(uploadFile);
        	} /*else {
            model.put("error", "Please select file because file was empty");
        	return new ModelAndView("addMorePhotoTile");
        }*/
		  model.put("addPhoto",  TechnologyDAO.list());
//		  //System.out.println("     photo        "+TechnologyDAO.list());
      	return new ModelAndView("addMorePhotoTechnologyTile");
    }
	
	@RequestMapping(value = "deleteTechnology", method = RequestMethod.GET)
	public ModelAndView deleteTechnology(ModelMap model,@Valid @Validated @ModelAttribute("id") Long id,BindingResult bindingResult,HttpServletRequest request) {
		////System.out.println(id);
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getTechnology()!=true){
			return new ModelAndView("403");	
		}
		if (id==null || id==0 || bindingResult.hasErrors() ) {
			return new ModelAndView("redirect:viewTechnologies?error=Argument Invalid");
		}
		//String dirPath = TechnologyDAO.findByDeptId(id).getPhotourl();
		String dirPath = TechnologyDAO.findByDeptId(id).getFolder_url();//getPhotourl();
		////System.out.println(dirPath.substring(0, dirPath.lastIndexOf("/")));
		
		//System.out.println(dirPath);
	
		File index = new File(dirPath);
		if (index.exists()){
			String[]entries = index.list();
			for(String s: entries){
			    File currentFile = new File(index.getPath(),s);
			    currentFile.delete();
			}
			index.delete();
        }
        TechnologyDAO.delete(TechnologyDAO.findByDeptId(id));
	
        return new ModelAndView("redirect:viewTechnologies?msg=Deleted Sucessfully");
	}
	
	@RequestMapping(value = "deleteTechnologyFile", method = RequestMethod.GET)
	public ModelAndView deleteFile(@Valid @Validated @ModelAttribute("fileDir") String fileDir,HttpServletRequest request) {
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getTechnology()!=true){
			return new ModelAndView("403");	
		}
		//System.out.println(fileDir);
		String dirPath = request.getRealPath("/")+fileDir;
		
		//programDAO.findByDeptId(id).getFolder_url();//getPhotourl();
		////System.out.println(dirPath.substring(0, dirPath.lastIndexOf("/")));
		
		File index = new File(dirPath);
		index.delete();
		
		return new ModelAndView("redirect:viewTechnologies?msg=Deleted Sucessfully");
	}
}