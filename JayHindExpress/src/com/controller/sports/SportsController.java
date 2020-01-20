package com.controller.sports;

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

import com.dao.SportDAO;
import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.github.dandelion.datatables.core.ajax.DatatablesResponse;
import com.github.dandelion.datatables.extras.spring3.ajax.DatatablesParams;
import com.image.imagemagick.Imagemagick;
import com.models.Sport;
import com.models.User;
import com.session.SessionManagement;

@Controller
@RequestMapping(value={"/user/", "/admin/"})
public class SportsController {

	@Autowired
	private SportDAO SportDAO;
	
	@Autowired
	private Imagemagick imagemagick;
	
	@RequestMapping(value = "addSportForm", method = RequestMethod.GET)
	public ModelAndView adminAddSportForm(ModelMap model,HttpServletRequest request) {
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getSport()!=true){
			return new ModelAndView("403");	
		}
		model.put("program", new Sport());
		return new ModelAndView("addSportProgramAdminTile");
	}
	
	@RequestMapping(value = "addSport", method = RequestMethod.POST)
	public ModelAndView adminAddSport(ModelMap model,@Valid @Validated @ModelAttribute("Sport") Sport sports,BindingResult bindingResult,HttpServletRequest request) {
		if(SessionManagement.isSessionAlive()!=true){
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getSport()!=true){
			return new ModelAndView("403");	
		}
		if(bindingResult.hasErrors())
		{
			//System.out.println(bindingResult.toString());
			model.put("program", sports);
			return new ModelAndView("addSportProgramAdminTile");
		}
		if (sports.getFile()!=null && !sports.getFile().isEmpty()) {
			if(sports.getFile().getContentType().equalsIgnoreCase("image/jpg") || sports.getFile().getContentType().equalsIgnoreCase("image/jpeg")){
				double size = sports.getFile().getSize();
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
						bytes = sports.getFile().getBytes();
						String dirPath=request.getRealPath("/")+"themes\\sports\\"+new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss",Locale.ENGLISH).format(new Date());
						File dir = new File(dirPath);
						if(dir.exists()){
							model.put("error", "Please Enter another folder Name because this folder name already exists.");
						}else{
							dir.mkdir();
						 String filePath = request.getRealPath("/")+"themes\\sports\\"+new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss",Locale.ENGLISH).format(new Date())+"\\"+f.getName()+".jpeg";
			                
						 //System.out.println(filePath);
						 BufferedOutputStream stream =
			                        new BufferedOutputStream(new FileOutputStream(new File(filePath)));
			                stream.write(bytes);
			                stream.close();
			                //Sport.setPhotourl(filePath);
			                sports.setPhotourl("themes\\sports\\"+new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss",Locale.ENGLISH).format(new Date())+"\\"+f.getName()+".jpeg");
			                sports.setFolder_url(dirPath);
			                sports.setFolder_relative_url("themes\\sports\\"+new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss",Locale.ENGLISH).format(new Date()));
			                sports.setFrequency(0l);
			                SportDAO.save(sports);
			                model.put("msg", "File uploaded successfully");
			                model.put("filePath", "../themes/sports/"+new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss",Locale.ENGLISH).format(new Date())+"/"+f.getName()+".jpeg");

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
	               
	                
	                //console call
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
		model.put("program",sports);
		return new ModelAndView("addSportProgramAdminTile");
	}
	
	@RequestMapping(value="viewSports",method=RequestMethod.GET)
	public ModelAndView viewDepartment(ModelMap model,@RequestParam(value = "msg", required = false) String msg,@RequestParam(value = "error", required = false) String error,HttpServletRequest request)
	{
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getSport()!=true){
			return new ModelAndView("403");	
		}
		if (error != null) {
			model.put("error", error);
		}
		if (msg != null) {
			model.put("msg", msg);
		}
		return new ModelAndView("viewSportProgramsTile");
	}
	
	@RequestMapping(value = "getSportList")
	public @ResponseBody DatatablesResponse<Sport> findAll(@DatatablesParams DatatablesCriterias criterias) {
	   DataSet<Sport> dataSet = SportDAO.findSportWithDatatablesCriterias(criterias);
	   return DatatablesResponse.build(dataSet, criterias);
	}
	
	@RequestMapping(value = "editSportForm", method = RequestMethod.POST)
	public ModelAndView editSportForm(ModelMap model,@RequestParam(value ="programId", required = false) Long programId,HttpServletRequest request) {
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getSport()!=true){
			return new ModelAndView("403");	
		}
		model.put("program", SportDAO.findByDeptId(programId));
		return new ModelAndView("editSportProgramAdminTile");
	}
	
	@RequestMapping(value = "updateSport", method = RequestMethod.POST)
	public ModelAndView updateSport(ModelMap model,@Valid @Validated @ModelAttribute("Sport") Sport Sport,BindingResult bindingResult,HttpServletRequest request) {
		if(SessionManagement.isSessionAlive()!=true){
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getSport()!=true){
			return new ModelAndView("403");	
		}
		if(bindingResult.hasErrors())
		{
			model.put("program", Sport);
			return new ModelAndView("editSportProgramAdminTile");
		}
		Sport p = SportDAO.findByDeptId(Sport.getId());
		p.setProgramName(Sport.getProgramName());
		p.setDescription(Sport.getDescription());
		p.setLocation(Sport.getLocation());
		p.setPosted_by(Sport.getPosted_by());
		SportDAO.update(p);
		return new ModelAndView("redirect:viewSports");
	}
	
	@RequestMapping(value = "selectSportForm", method = RequestMethod.POST)
	public ModelAndView selectSportForm(ModelMap model,@RequestParam(value = "programIdsel", required = false) Long programIdsel,@RequestParam(value = "msg", required = false) String msg,@RequestParam(value = "error", required = false) String error,HttpServletRequest request) throws IOException {
		//System.out.println(programIdsel);
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getSport()!=true){
			return new ModelAndView("403");	
		}
		if (error != null) {
			model.put("error", error);
		}
		if (msg != null) {
			model.put("msg", msg);
		}
		if (error != null || programIdsel==null || programIdsel==0) {
			return new ModelAndView("redirect:viewSports?error=Argument Invalid");
		}
		Sport p = SportDAO.findByDeptId(programIdsel);
		ArrayList<String> fileList = new ArrayList<String>();
		
		String dirPath = p.getFolder_url();//request.getRealPath("/")+"themes\\"+p.getSportName()+"\\";
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
		return new ModelAndView("selectSportProgramsTile");
		
	}
	@RequestMapping(value="moreSportPhotos")
	public ModelAndView upload(ModelMap model,@RequestParam(value ="photoId", required = false) Long photoId,HttpServletRequest request){		 
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getSport()!=true){
			return new ModelAndView("403");	
		}
		
		model.put("photo", SportDAO.findByDeptId(photoId));
		model.put("extraParam", "&photoId="+photoId);
		
		return new ModelAndView("addMorePhotoSportTile");
	}
	
	@RequestMapping(value = "douploadSportPhoto", method = RequestMethod.POST)
    public ModelAndView uploadFileHandler(@Valid @Validated @ModelAttribute("uploadPhoto") Sport Sport,BindingResult bindingResult,@RequestParam("file") MultipartFile file,ModelMap model,HttpServletRequest request) {
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getSport()!=true){
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
	                Sport = SportDAO.findByDeptId(Sport.getId());
	                Sport.setPhotourl(fname);
	                SportDAO.update(Sport);
	                //Sport.setPhotourl(fname);
	                //Sport.setFile(file);
	               // //System.out.println("fileName     "   +uploadFile.getFileName());
	               
	                //SportDAO.save(Sport);
	               // //System.out.println("saveeeeeeeeeee            "   +  Sport.getId());
            	}          	
          
            } catch (Exception e) {
                model.put("error", e.getMessage());
	        	return new ModelAndView("addMorePhotoSportTile");
            }
           // showAllItemDAO.save(uploadFile);
        	} /*else {
            model.put("error", "Please select file because file was empty");
        	return new ModelAndView("addMorePhotoTile");
        }*/
		  model.put("addPhoto",  SportDAO.list());
//		  //System.out.println("     photo        "+SportDAO.list());
      	return new ModelAndView("addMorePhotoSportTile");
    }
	
	@RequestMapping(value = "deleteSport", method = RequestMethod.GET)
	public ModelAndView deleteSport(ModelMap model,@Valid @Validated @ModelAttribute("id") Long id,BindingResult bindingResult,HttpServletRequest request) {
		////System.out.println(id);
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getSport()!=true){
			return new ModelAndView("403");	
		}
		if (id==null || id==0 || bindingResult.hasErrors() ) {
			return new ModelAndView("redirect:viewSport?error=Argument Invalid");
		}
		//String dirPath = SportDAO.findByDeptId(id).getPhotourl();
		String dirPath = SportDAO.findByDeptId(id).getFolder_url();//getPhotourl();
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
        SportDAO.delete(SportDAO.findByDeptId(id));
	
        return new ModelAndView("redirect:viewSport?msg=Deleted Sucessfully");
	}
	
	@RequestMapping(value = "deleteSportFile", method = RequestMethod.GET)
	public ModelAndView deleteFile(@Valid @Validated @ModelAttribute("fileDir") String fileDir,HttpServletRequest request) {
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getSport()!=true){
			return new ModelAndView("403");	
		}
		//System.out.println(fileDir);
		String dirPath = request.getRealPath("/")+fileDir;
		
		//programDAO.findByDeptId(id).getFolder_url();//getPhotourl();
		////System.out.println(dirPath.substring(0, dirPath.lastIndexOf("/")));
		
		File index = new File(dirPath);
		index.delete();
		
		return new ModelAndView("redirect:viewSports?msg=Deleted Sucessfully");
	}
}