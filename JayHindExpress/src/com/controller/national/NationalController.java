package com.controller.national;

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

import com.dao.NationalProgramDAO;
import com.dao.ProgramDAO;
import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.github.dandelion.datatables.core.ajax.DatatablesResponse;
import com.github.dandelion.datatables.extras.spring3.ajax.DatatablesParams;
import com.image.imagemagick.Imagemagick;
import com.models.NationalProgram;
import com.models.Program;
import com.models.User;
import com.session.SessionManagement;

@Controller
@RequestMapping(value={"/user/", "/admin/"})
public class NationalController {

	@Autowired
	private NationalProgramDAO nationalProgramDAO;
	
	@Autowired
    private Imagemagick imagemagick;
	
	@RequestMapping(value = "addNationalProgramForm", method = RequestMethod.GET)
	public ModelAndView adminAddProgramForm(ModelMap model,HttpServletRequest request) {
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getNational()!=true){
			return new ModelAndView("403");	
		}
		model.put("program", new Program());
		return new ModelAndView("addNationalProgramAdminTile");
	}
	
	@RequestMapping(value = "addNationalProgram", method = RequestMethod.POST)
	public ModelAndView adminAddProgram(ModelMap model,@Valid @Validated @ModelAttribute("program") NationalProgram nationalProgram,BindingResult bindingResult,HttpServletRequest request) {
		if(SessionManagement.isSessionAlive()!=true){
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getNational()!=true){
			return new ModelAndView("403");	
		}
		if (nationalProgram.getFile()!=null && !nationalProgram.getFile().isEmpty()) {
			if(nationalProgram.getFile().getContentType().equalsIgnoreCase("image/jpg") || nationalProgram.getFile().getContentType().equalsIgnoreCase("image/jpeg")){
				double size = nationalProgram.getFile().getSize();
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
						bytes = nationalProgram.getFile().getBytes();
						
						String dirPath=request.getRealPath("/")+"themes\\national\\"+new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss",Locale.ENGLISH).format(new Date());
						File dir = new File(dirPath);
						if(dir.exists()){
							model.put("error", "Please Enter another folder Name because this folder name already exists.");
						}else{
							dir.mkdir();
						 String filePath = request.getRealPath("/")+"themes\\national\\"+new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss",Locale.ENGLISH).format(new Date())+"\\"+f.getName()+".jpeg";
			                
						 //System.out.println(filePath);
						 BufferedOutputStream stream =
			                        new BufferedOutputStream(new FileOutputStream(new File(filePath)));
			                stream.write(bytes);
			                stream.close();
			                //program.setPhotourl(filePath);
			                nationalProgram.setPhotourl("themes\\national\\"+new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss",Locale.ENGLISH).format(new Date())+"\\"+f.getName()+".jpeg");
			                nationalProgram.setFolder_url(dirPath);
			                nationalProgram.setFolder_relative_url("themes\\national\\"+new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss",Locale.ENGLISH).format(new Date()));
			                nationalProgram.setFrequency(0l);
			                nationalProgramDAO.save(nationalProgram);
			                model.put("msg", "File uploaded successfully");
			                model.put("filePath", "../themes/national/"+new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss",Locale.ENGLISH).format(new Date())+"/"+f.getName()+".jpeg");
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
		if(bindingResult.hasErrors())
		{
			model.put("program", nationalProgram);
			return new ModelAndView("addNationalProgramAdminTile");
		}
		return new ModelAndView("addNationalProgramAdminTile");
	}
	
	@RequestMapping(value="viewNationalPrograms",method=RequestMethod.GET)
	public ModelAndView viewDepartment(ModelMap model,@RequestParam(value = "msg", required = false) String msg,@RequestParam(value = "error", required = false) String error,HttpServletRequest request)
	{
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getNational()!=true){
			return new ModelAndView("403");	
		}
		if (error != null) {
			model.put("error", error);
		}
		if (msg != null) {
			model.put("msg", msg);
		}
		return new ModelAndView("viewNationalProgramsTile");
	}
	
	@RequestMapping(value = "getNationalProgramList")
	public @ResponseBody DatatablesResponse<NationalProgram> findAll(@DatatablesParams DatatablesCriterias criterias) {
	   DataSet<NationalProgram> dataSet = nationalProgramDAO.findNationalProgramWithDatatablesCriterias(criterias);
	   return DatatablesResponse.build(dataSet, criterias);
	}
	
	@RequestMapping(value = "editNationalProgramForm", method = RequestMethod.POST)
	public ModelAndView editProgramForm(ModelMap model,@RequestParam(value ="programId", required = false) Long programId,HttpServletRequest request) {
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getNational()!=true){
			return new ModelAndView("403");	
		}
		model.put("program", nationalProgramDAO.findByDeptId(programId));
		return new ModelAndView("editNationalProgramAdminTile");
	}
	
	@RequestMapping(value = "updateNationalProgram", method = RequestMethod.POST)
	public ModelAndView updateProgram(ModelMap model,@Valid @Validated @ModelAttribute("program") NationalProgram nationalProgram,BindingResult bindingResult,HttpServletRequest request) {
		if(SessionManagement.isSessionAlive()!=true){
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getNational()!=true){
			return new ModelAndView("403");	
		}
		if(bindingResult.hasErrors())
		{
			model.put("program", nationalProgram);
			return new ModelAndView("editProgramAdminTile");
		}
		NationalProgram p = nationalProgramDAO.findByDeptId(nationalProgram.getId());
		p.setProgramName(nationalProgram.getProgramName());
		p.setDescription(nationalProgram.getDescription());
		p.setLocation(nationalProgram.getLocation());
		p.setPosted_by(nationalProgram.getPosted_by());
		nationalProgramDAO.update(p);
		return new ModelAndView("redirect:viewNationalPrograms");
	}
	
	@RequestMapping(value = "selectNationalProgramForm", method = RequestMethod.POST)
	public ModelAndView selectProgramForm(ModelMap model,@RequestParam(value = "programIdsel", required = false) Long programId,@RequestParam(value = "msg", required = false) String msg,@RequestParam(value = "error", required = false) String error,HttpServletRequest request) throws IOException {
		//System.out.println(programId);
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getNational()!=true){
			return new ModelAndView("403");	
		}
		if (error != null) {
			model.put("error", error);
		}
		if (msg != null) {
			model.put("msg", msg);
		}
		if (error != null || programId==null || programId==0) {
			return new ModelAndView("redirect:viewNationalPrograms?error=Argument Invalid");
		}
		NationalProgram p = nationalProgramDAO.findByDeptId(programId);
		ArrayList<String> fileList = new ArrayList<String>();
		
		String dirPath = p.getFolder_url();//request.getRealPath("/")+"themes\\national\\"+p.getProgramName()+"\\";
		File folder = new File(dirPath);
		if(folder.exists()){
		for (final File fileEntry : folder.listFiles()) {
			fileList.add(fileEntry.getName());
	    }
		}
		model.put("fileList", fileList);
		model.put("fileDir", p.getFolder_relative_url());
		model.put("selectProgram", p);
		return new ModelAndView("selectNationalProgramsTile");
		
	}
	@RequestMapping(value="moreNationalPhotos")
	public ModelAndView upload(ModelMap model,@RequestParam(value ="photoId", required = false) Long photoId,HttpServletRequest request){		 
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getNational()!=true){
			return new ModelAndView("403");	
		}
		model.put("photo", nationalProgramDAO.findByDeptId(photoId));
		model.put("extraParam", "&photoId="+photoId);
		
		return new ModelAndView("addMoreNationalPhotoTile");
	}
	
	@RequestMapping(value = "douploadNationalPhoto", method = RequestMethod.POST)
    public ModelAndView uploadFileHandler(@Valid @Validated @ModelAttribute("uploadPhoto") NationalProgram program,BindingResult bindingResult,@RequestParam("file") MultipartFile file,ModelMap model,HttpServletRequest request) {
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getNational()!=true){
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
	                java.util.Date date= new java.util.Date();
	                file.toString();
	                String fname = dir+"\\"+f.getName().replace("jpg","jpeg");
	              // String fname = dir.getAbsolutePath()+ File.separator + (new Timestamp(date.getTime())).toString().replace(":", "").toString().replace(".", ".").toString().replace(" ","").toString().replace("-","").toString()+".txt";
	                
	               // String filename = file.getOriginalFilename();
	                File serverFile = new File(fname);
	                ////System.out.println("f Name : " + fname);
	                
	                BufferedOutputStream stream = new BufferedOutputStream(
	                        new FileOutputStream(serverFile));
	                stream.write(bytes);
	                stream.close();
	                if(fname.endsWith("0.jpeg"))
	                {
		                Imagemagick imagemagick = new Imagemagick();
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
	                program = nationalProgramDAO.findByDeptId(program.getId());
	                program.setPhotourl(fname);
	                nationalProgramDAO.update(program);
	                //program.setFile(file);
	               // //System.out.println("fileName     "   +uploadFile.getFileName());
	               
	                //nationalProgramDAO.save(program);
	               // //System.out.println("saveeeeeeeeeee            "   +  program.getId());
            	}          	
          
            } catch (Exception e) {
                model.put("error", e.getMessage());
	        	return new ModelAndView("addMorePhotoTile");
            }
           // showAllItemDAO.save(uploadFile);
        	} /*else {
            model.put("error", "Please select file because file was empty");
        	return new ModelAndView("addMorePhotoTile");
        }*/
		  model.put("addPhoto",  nationalProgramDAO.list());
		  ////System.out.println("     photo        "+nationalProgramDAO.list());
      	return new ModelAndView("addMoreNationalPhotoTile");
    }
	
	@RequestMapping(value = "deleteNationalProgram", method = RequestMethod.GET)
	public ModelAndView deleteProgram(ModelMap model,@Valid @Validated @ModelAttribute("id") Long id,BindingResult bindingResult,HttpServletRequest request) {
		////System.out.println(id);
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getNational()!=true){
			return new ModelAndView("403");	
		}
		if (id==null || id==0 || bindingResult.hasErrors() ) {
			return new ModelAndView("redirect:viewNationalPrograms?error=Argument Invalid");
		}
		//String dirPath = nationalProgramDAO.findByDeptId(id).getPhotourl();
		String dirPath = nationalProgramDAO.findByDeptId(id).getFolder_url();//getPhotourl();
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
        nationalProgramDAO.delete(nationalProgramDAO.findByDeptId(id));
		return new ModelAndView("redirect:viewNationalPrograms?msg=Deleted Sucessfully");
	}
	
	@RequestMapping(value = "deleteNationalFile", method = RequestMethod.GET)
	public ModelAndView deleteFile(@Valid @Validated @ModelAttribute("fileDir") String fileDir,HttpServletRequest request) {
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getNational()!=true){
			return new ModelAndView("403");	
		}
		//System.out.println(fileDir);
		String dirPath = request.getRealPath("/")+fileDir;
		
		//programDAO.findByDeptId(id).getFolder_url();//getPhotourl();
		////System.out.println(dirPath.substring(0, dirPath.lastIndexOf("/")));
		
		File index = new File(dirPath);
		index.delete();
		
		return new ModelAndView("redirect:viewNationalPrograms?msg=Deleted Sucessfully");
	}
	
}