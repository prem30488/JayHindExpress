package com.controller.fashion;

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

import com.dao.FashionDAO;
import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.github.dandelion.datatables.core.ajax.DatatablesResponse;
import com.github.dandelion.datatables.extras.spring3.ajax.DatatablesParams;
import com.image.imagemagick.Imagemagick;
import com.models.Fashion;
import com.models.User;
import com.session.SessionManagement;

@Controller
@RequestMapping(value={"/user/", "/admin/"})
public class FashionController {

	@Autowired
	private FashionDAO FashionDAO;
	
	@Autowired
	private Imagemagick imagemagick;
	
	@RequestMapping(value = "addFashionForm", method = RequestMethod.GET)
	public ModelAndView adminAddFashionForm(ModelMap model,HttpServletRequest request) {
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getFashion()!=true){
			return new ModelAndView("403");	
		}
		model.put("program", new Fashion());
		return new ModelAndView("addFashionProgramAdminTile");
	}
	
	@RequestMapping(value = "addFashion", method = RequestMethod.POST)
	public ModelAndView adminAddFashion(ModelMap model,@Valid @Validated @ModelAttribute("Fashion") Fashion fashion,BindingResult bindingResult,HttpServletRequest request) {
		if(SessionManagement.isSessionAlive()!=true){
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getFashion()!=true){
			return new ModelAndView("403");	
		}
		if(bindingResult.hasErrors())
		{
			//System.out.println(bindingResult.toString());
			model.put("program", fashion);
			return new ModelAndView("addFashionProgramAdminTile");
		}
		if (fashion.getFile()!=null && !fashion.getFile().isEmpty()) {
			if(fashion.getFile().getContentType().equalsIgnoreCase("image/jpg") || fashion.getFile().getContentType().equalsIgnoreCase("image/jpeg")){
				double size = fashion.getFile().getSize();
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
						bytes = fashion.getFile().getBytes();
						String dirPath=request.getRealPath("/")+"themes\\fashion\\"+new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss",Locale.ENGLISH).format(new Date());
						File dir = new File(dirPath);
						if(dir.exists()){
							model.put("error", "Please Enter another folder Name because this folder name already exists.");
						}else{
							dir.mkdir();
						 String filePath = request.getRealPath("/")+"themes\\fashion\\"+new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss",Locale.ENGLISH).format(new Date())+"\\"+f.getName()+".jpeg";
			                
						 //System.out.println(filePath);
						 BufferedOutputStream stream =
			                        new BufferedOutputStream(new FileOutputStream(new File(filePath)));
			                stream.write(bytes);
			                stream.close();
			                //Fashion.setPhotourl(filePath);
			                fashion.setPhotourl("themes\\fashion\\"+new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss",Locale.ENGLISH).format(new Date())+"\\"+f.getName()+".jpeg");
			                fashion.setFolder_url(dirPath);
			                fashion.setFolder_relative_url("themes\\fashion\\"+new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss",Locale.ENGLISH).format(new Date()));
			                fashion.setFrequency(0l);
			                FashionDAO.save(fashion);
			                model.put("msg", "File uploaded successfully");
			                model.put("filePath", "../themes/fashion/"+new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss",Locale.ENGLISH).format(new Date())+"/"+f.getName()+".jpeg");

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
		model.put("program",fashion);
		return new ModelAndView("addFashionProgramAdminTile");
	}
	
	@RequestMapping(value="viewFashions",method=RequestMethod.GET)
	public ModelAndView viewDepartment(ModelMap model,@RequestParam(value = "msg", required = false) String msg,@RequestParam(value = "error", required = false) String error,HttpServletRequest request)
	{
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getFashion()!=true){
			return new ModelAndView("403");	
		}
		if (error != null) {
			model.put("error", error);
		}
		if (msg != null) {
			model.put("msg", msg);
		}
		return new ModelAndView("viewFashionProgramsTile");
	}
	
	@RequestMapping(value = "getFashionList")
	public @ResponseBody DatatablesResponse<Fashion> findAll(@DatatablesParams DatatablesCriterias criterias) {
	   DataSet<Fashion> dataSet = FashionDAO.findFashionWithDatatablesCriterias(criterias);
	   return DatatablesResponse.build(dataSet, criterias);
	}
	
	@RequestMapping(value = "editFashionForm", method = RequestMethod.POST)
	public ModelAndView editFashionForm(ModelMap model,@RequestParam(value ="programId", required = false) Long programId,HttpServletRequest request) {
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getFashion()!=true){
			return new ModelAndView("403");	
		}
		model.put("program", FashionDAO.findByDeptId(programId));
		return new ModelAndView("editFashionAdminTile");
	}
	
	@RequestMapping(value = "updateFashion", method = RequestMethod.POST)
	public ModelAndView updateFashion(ModelMap model,@Valid @Validated @ModelAttribute("Fashion") Fashion Fashion,BindingResult bindingResult,HttpServletRequest request) {
		if(SessionManagement.isSessionAlive()!=true){
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getFashion()!=true){
			return new ModelAndView("403");	
		}
		if(bindingResult.hasErrors())
		{
			model.put("program", Fashion);
			return new ModelAndView("editFashionProgramAdminTile");
		}
		Fashion p = FashionDAO.findByDeptId(Fashion.getId());
		p.setProgramName(Fashion.getProgramName());
		p.setDescription(Fashion.getDescription());
		p.setLocation(Fashion.getLocation());
		p.setPosted_by(Fashion.getPosted_by());
		FashionDAO.update(p);
		return new ModelAndView("redirect:viewFashions");
	}
	
	@RequestMapping(value = "selectFashionForm", method = RequestMethod.POST)
	public ModelAndView selectFashionForm(ModelMap model,@RequestParam(value = "programIdsel", required = false) Long programIdsel,@RequestParam(value = "msg", required = false) String msg,@RequestParam(value = "error", required = false) String error,HttpServletRequest request) throws IOException {
		//System.out.println(programIdsel);
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getFashion()!=true){
			return new ModelAndView("403");	
		}
		if (error != null) {
			model.put("error", error);
		}
		if (msg != null) {
			model.put("msg", msg);
		}
		if (error != null || programIdsel==null || programIdsel==0) {
			return new ModelAndView("redirect:viewFashions?error=Argument Invalid");
		}
		Fashion p = FashionDAO.findByDeptId(programIdsel);
		ArrayList<String> fileList = new ArrayList<String>();
		
		String dirPath = p.getFolder_url();//request.getRealPath("/")+"themes\\"+p.getFashionName()+"\\";
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
		return new ModelAndView("selectFashionProgramsTile");
		
	}
	@RequestMapping(value="moreFashionPhotos")
	public ModelAndView upload(ModelMap model,@RequestParam(value ="photoId", required = false) Long photoId,HttpServletRequest request){		 
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getFashion()!=true){
			return new ModelAndView("403");	
		}
		model.put("photo", FashionDAO.findByDeptId(photoId));
		model.put("extraParam", "&photoId="+photoId);
		
		return new ModelAndView("addMorePhotoFashionTile");
	}
	
	@RequestMapping(value = "douploadFashionPhoto", method = RequestMethod.POST)
    public ModelAndView uploadFileHandler(@Valid @Validated @ModelAttribute("uploadPhoto") Fashion Fashion,BindingResult bindingResult,@RequestParam("file") MultipartFile file,ModelMap model,HttpServletRequest request) {
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getFashion()!=true){
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
	                Fashion = FashionDAO.findByDeptId(Fashion.getId());
	                Fashion.setPhotourl(fname);
	                FashionDAO.update(Fashion);
	                //Fashion.setPhotourl(fname);
	                //Fashion.setFile(file);
	               // //System.out.println("fileName     "   +uploadFile.getFileName());
	               
	                //FashionDAO.save(Fashion);
	               // //System.out.println("saveeeeeeeeeee            "   +  Fashion.getId());
            	}          	
          
            } catch (Exception e) {
                model.put("error", e.getMessage());
	        	return new ModelAndView("addMorePhotoFashionTile");
            }
           // showAllItemDAO.save(uploadFile);
        	} /*else {
            model.put("error", "Please select file because file was empty");
        	return new ModelAndView("addMorePhotoTile");
        }*/
		  model.put("addPhoto",  FashionDAO.list());
//		  //System.out.println("     photo        "+FashionDAO.list());
      	return new ModelAndView("addMorePhotoFashionTile");
    }
	
	@RequestMapping(value = "deleteFashion", method = RequestMethod.GET)
	public ModelAndView deleteFashion(ModelMap model,@Valid @Validated @ModelAttribute("id") Long id,BindingResult bindingResult,HttpServletRequest request) {
		////System.out.println(id);
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getFashion()!=true){
			return new ModelAndView("403");	
		}
		if (id==null || id==0 || bindingResult.hasErrors() ) {
			return new ModelAndView("redirect:viewFashions?error=Argument Invalid");
		}
		//String dirPath = FashionDAO.findByDeptId(id).getPhotourl();
		String dirPath = FashionDAO.findByDeptId(id).getFolder_url();//getPhotourl();
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
        FashionDAO.delete(FashionDAO.findByDeptId(id));
	
		return new ModelAndView("redirect:viewFashions?msg=Deleted Sucessfully");
	}
	
	@RequestMapping(value = "deleteFashionFile", method = RequestMethod.GET)
	public ModelAndView deleteFile(@Valid @Validated @ModelAttribute("fileDir") String fileDir,HttpServletRequest request) {
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getFashion()!=true){
			return new ModelAndView("403");	
		}
		//System.out.println(fileDir);
		String dirPath = request.getRealPath("/")+fileDir;
		
		//programDAO.findByDeptId(id).getFolder_url();//getPhotourl();
		////System.out.println(dirPath.substring(0, dirPath.lastIndexOf("/")));
		
		File index = new File(dirPath);
		index.delete();
		
		return new ModelAndView("redirect:viewFashions?msg=Deleted Sucessfully");
	}
}