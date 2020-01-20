package com.controller.sponsor;

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

import com.dao.SponsorDAO;
import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.github.dandelion.datatables.core.ajax.DatatablesResponse;
import com.github.dandelion.datatables.extras.spring3.ajax.DatatablesParams;
import com.models.Sponsor;
import com.models.User;
import com.session.SessionManagement;

@Controller
@RequestMapping(value={"/user/", "/admin/"})
public class SponsorController {

	@Autowired
	private SponsorDAO SponsorDAO;
	
	@RequestMapping(value = "addSponsorForm", method = RequestMethod.GET)
	public ModelAndView adminAddSponsorForm(ModelMap model,HttpServletRequest request) {
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getSponser()!=true){
			return new ModelAndView("403");	
		}
		model.put("program", new Sponsor());
		return new ModelAndView("addSponsorProgramAdminTile");
	}
	
	@RequestMapping(value = "addSponsor", method = RequestMethod.POST)
	public ModelAndView adminAddSponsor(ModelMap model,@Valid @Validated @ModelAttribute("Sponsor") Sponsor sponsor,BindingResult bindingResult,HttpServletRequest request) {
		if(SessionManagement.isSessionAlive()!=true){
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getSponser()!=true){
			return new ModelAndView("403");	
		}
		if(bindingResult.hasErrors())
		{
			//System.out.println(bindingResult.toString());
			model.put("program", sponsor);
			return new ModelAndView("addSponsorProgramAdminTile");
		}
		if (sponsor.getFile()!=null && !sponsor.getFile().isEmpty()) {
			if(sponsor.getFile().getContentType().equalsIgnoreCase("image/jpg") || sponsor.getFile().getContentType().equalsIgnoreCase("image/jpeg")){
				double size = sponsor.getFile().getSize();
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
						bytes = sponsor.getFile().getBytes();
						String dirPath=request.getRealPath("/")+"themes\\sponsor\\"+new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss",Locale.ENGLISH).format(new Date());
						File dir = new File(dirPath);
						if(dir.exists()){
							model.put("error", "Please Enter another folder Name because this folder name already exists.");
						}else{
							dir.mkdir();
						 String filePath = request.getRealPath("/")+"themes\\sponsor\\"+new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss",Locale.ENGLISH).format(new Date())+"\\"+f.getName()+".jpeg";
			                
						 //System.out.println(filePath);
						 BufferedOutputStream stream =
			                        new BufferedOutputStream(new FileOutputStream(new File(filePath)));
			                stream.write(bytes);
			                stream.close();
			                //Sponsor.setPhotourl(filePath);
			                sponsor.setPhotourl("themes\\sponsor\\"+new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss",Locale.ENGLISH).format(new Date())+"\\"+f.getName()+".jpeg");
			                sponsor.setFolder_url(dirPath);
			                sponsor.setFolder_relative_url("themes\\sponsor\\"+new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss",Locale.ENGLISH).format(new Date()));
			                sponsor.setFrequency(0l);
			                SponsorDAO.save(sponsor);
			                model.put("msg", "File uploaded successfully");
			                model.put("filePath", "../themes/sponsor/"+new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss",Locale.ENGLISH).format(new Date())+"/"+f.getName()+".jpeg");
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	               
	                
	                //console call
	               /* String command="c:\\ImageMagick\\convert.exe "+request.getRealPath("/")+"themes\\ProfileImages\\"+SessionManagement.getUserName()+".jpeg" + " -resize 10% "+ request.getRealPath("/")+"themes\\ProfileImages\\"+SessionManagement.getUserName()+"_icon.jpeg";
	                ////System.out.println(command);
	                Process proc;
	                try {
						Runtime runtime = Runtime.getRuntime();
						proc = runtime.exec(command);
						proc.getOutputStream().close();
						InputStream inputstream = proc.getInputStream();
						InputStreamReader inputstreamreader = new InputStreamReader(
								inputstream);
						BufferedReader bufferedreader = new BufferedReader(
								inputstreamreader);
						String line;
						int j = 0;
						while ((line = bufferedreader.readLine()) != null) {
							if (line.contains("Invalid")) {
								//System.out.println(line);
								break;
							}
							//System.out.println(line);
						}
						request.getSession().setAttribute("icon", "../themes/ProfileImages/"+SessionManagement.getUserName()+"_wmark.jpeg");
						model.put("msg", "File uploaded successfully");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						model.put("error", "File creation error for resized");
					}
	                
	                command="c:\\ImageMagick\\convert.exe "+request.getRealPath("/")+"themes\\ProfileImages\\"+SessionManagement.getUserName()+".jpeg" + " -font Arial -pointsize 10 -draw \"gravity south fill green text 0,12 'bisag' fill white text 1,12 'bisag' \" "+ request.getRealPath("/")+"themes\\ProfileImages\\"+SessionManagement.getUserName()+"_wmark.jpeg";
	                ////System.out.println(command);
	                try {
						Runtime runtime = Runtime.getRuntime();
						proc = runtime.exec(command);
						proc.getOutputStream().close();
						InputStream inputstream = proc.getInputStream();
						InputStreamReader inputstreamreader = new InputStreamReader(
								inputstream);
						BufferedReader bufferedreader = new BufferedReader(
								inputstreamreader);
						String line;
						int j = 0;
						while ((line = bufferedreader.readLine()) != null) {
							if (line.contains("Invalid")) {
								////System.out.println(line);
								break;
							}
							////System.out.println(line);
						}
						
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						model.put("error", "File creation error for watermark");
					}*/
					
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
		model.put("program",sponsor);
		////System.out.println("here");
		return new ModelAndView("addSponsorProgramAdminTile");
	}
	
	@RequestMapping(value="viewSponsors",method=RequestMethod.GET)
	public ModelAndView viewDepartment(ModelMap model,@RequestParam(value = "msg", required = false) String msg,@RequestParam(value = "error", required = false) String error,HttpServletRequest request)
	{
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getSponser()!=true){
			return new ModelAndView("403");	
		}
		if (error != null) {
			model.put("error", error);
		}
		if (msg != null) {
			model.put("msg", msg);
		}
		return new ModelAndView("viewSponsorProgramsTile");
	}
	
	@RequestMapping(value = "getSponsorList")
	public @ResponseBody DatatablesResponse<Sponsor> findAll(@DatatablesParams DatatablesCriterias criterias) {
	   DataSet<Sponsor> dataSet = SponsorDAO.findSponsorWithDatatablesCriterias(criterias);
	   return DatatablesResponse.build(dataSet, criterias);
	}
	
	@RequestMapping(value = "editSponsorForm", method = RequestMethod.POST)
	public ModelAndView editSponsorForm(ModelMap model,@RequestParam(value ="programId", required = false) Long programId,HttpServletRequest request) {
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getSponser()!=true){
			return new ModelAndView("403");	
		}
		model.put("program", SponsorDAO.findByDeptId(programId));
		return new ModelAndView("editSponsorAdminTile");
	}
	
	@RequestMapping(value = "updateSponsor", method = RequestMethod.POST)
	public ModelAndView updateSponsor(ModelMap model,@Valid @Validated @ModelAttribute("Sponsor") Sponsor Sponsor,BindingResult bindingResult,HttpServletRequest request) {
		if(SessionManagement.isSessionAlive()!=true){
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getSponser()!=true){
			return new ModelAndView("403");	
		}
		if(bindingResult.hasErrors())
		{
			model.put("program", Sponsor);
			return new ModelAndView("editSponsorProgramAdminTile");
		}
		Sponsor p = SponsorDAO.findByDeptId(Sponsor.getId());
		p.setProgramName(Sponsor.getProgramName());
		p.setDescription(Sponsor.getDescription());
		p.setLocation(Sponsor.getLocation());
		p.setPosted_by(Sponsor.getPosted_by());
		p.setLink(Sponsor.getLink());
		p.setLink_target(Sponsor.getLink_target());
		p.setActive(Sponsor.getActive());
		SponsorDAO.update(p);
		return new ModelAndView("redirect:viewSponsors");
	}
	
	@RequestMapping(value = "selectSponsorForm", method = RequestMethod.POST)
	public ModelAndView selectSponsorForm(ModelMap model,@RequestParam(value = "programIdsel", required = false) Long programIdsel,@RequestParam(value = "msg", required = false) String msg,@RequestParam(value = "error", required = false) String error,HttpServletRequest request) throws IOException {
		//System.out.println(programIdsel);
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getSponser()!=true){
			return new ModelAndView("403");	
		}
		if (error != null) {
			model.put("error", error);
		}
		if (msg != null) {
			model.put("msg", msg);
		}
		if (error != null || programIdsel==null || programIdsel==0) {
			return new ModelAndView("redirect:viewSponsors?error=Argument Invalid");
		}
		Sponsor p = SponsorDAO.findByDeptId(programIdsel);
		ArrayList<String> fileList = new ArrayList<String>();
		
		String dirPath = p.getFolder_url();//request.getRealPath("/")+"themes\\"+p.getSponsorName()+"\\";
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
		return new ModelAndView("selectSponsorProgramsTile");
		
	}
	@RequestMapping(value="moreSponsorPhotos")
	public ModelAndView upload(ModelMap model,@RequestParam(value ="photoId", required = false) Long photoId,HttpServletRequest request){		 
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getSponser()!=true){
			return new ModelAndView("403");	
		}
		model.put("photo", SponsorDAO.findByDeptId(photoId));
		model.put("extraParam", "&photoId="+photoId);
		
		return new ModelAndView("addMorePhotoSponsorTile");
	}
	
	@RequestMapping(value = "douploadSponsorPhoto", method = RequestMethod.POST)
    public ModelAndView uploadFileHandler(@Valid @Validated @ModelAttribute("uploadPhoto") Sponsor Sponsor,BindingResult bindingResult,@RequestParam("file") MultipartFile file,ModelMap model,HttpServletRequest request) {
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getSponser()!=true){
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
	                //Sponsor.setPhotourl(fname);
	                //Sponsor.setFile(file);
	               // //System.out.println("fileName     "   +uploadFile.getFileName());
	               
	                //SponsorDAO.save(Sponsor);
	               // //System.out.println("saveeeeeeeeeee            "   +  Sponsor.getId());
            	}          	
          
            } catch (Exception e) {
                model.put("error", e.getMessage());
	        	return new ModelAndView("addMorePhotoSponsorTile");
            }
           // showAllItemDAO.save(uploadFile);
        	} /*else {
            model.put("error", "Please select file because file was empty");
        	return new ModelAndView("addMorePhotoTile");
        }*/
		  model.put("addPhoto",  SponsorDAO.list());
//		  //System.out.println("     photo        "+SponsorDAO.list());
      	return new ModelAndView("addMorePhotoSponsorTile");
    }
	
	@RequestMapping(value = "deleteSponsor", method = RequestMethod.GET)
	public ModelAndView deleteSponsor(ModelMap model,@Valid @Validated @ModelAttribute("id") Long id,BindingResult bindingResult,HttpServletRequest request) {
		////System.out.println(id);
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getSponser()!=true){
			return new ModelAndView("403");	
		}
		if (id==null || id==0 || bindingResult.hasErrors() ) {
			return new ModelAndView("redirect:viewSponsor?error=Argument Invalid");
		}
		//String dirPath = SponsorDAO.findByDeptId(id).getPhotourl();
		String dirPath = SponsorDAO.findByDeptId(id).getFolder_url();//getPhotourl();
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
        SponsorDAO.delete(SponsorDAO.findByDeptId(id));
	
		return new ModelAndView("redirect:viewSponsors?msg=Deleted Sucessfully");
	}
	
	@RequestMapping(value = "deleteSponsorFile", method = RequestMethod.GET)
	public ModelAndView deleteFile(@Valid @Validated @ModelAttribute("fileDir") String fileDir,HttpServletRequest request) {
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getSponser()!=true){
			return new ModelAndView("403");	
		}
		//System.out.println(fileDir);
		String dirPath = request.getRealPath("/")+fileDir;
		
		//programDAO.findByDeptId(id).getFolder_url();//getPhotourl();
		////System.out.println(dirPath.substring(0, dirPath.lastIndexOf("/")));
		
		File index = new File(dirPath);
		index.delete();
		
		return new ModelAndView("redirect:viewSponsor?msg=Deleted Sucessfully");
	}
}