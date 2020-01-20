package com.controller.advertisement;

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

import com.dao.AdvertisementDAO;
import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.github.dandelion.datatables.core.ajax.DatatablesResponse;
import com.github.dandelion.datatables.extras.spring3.ajax.DatatablesParams;
import com.models.Advertisement;
import com.models.User;
import com.session.SessionManagement;

@Controller
@RequestMapping(value={"/user/", "/admin/"})
public class AdvertisementController {

	@Autowired
	private AdvertisementDAO AdvertisementDAO;
	
	@RequestMapping(value = "addAdvertisementForm", method = RequestMethod.GET)
	public ModelAndView adminAddAdvertisementForm(ModelMap model,HttpServletRequest request) {
		if(SessionManagement.isSessionAlive()!=true){
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getAdvertisement()!=true){
			return new ModelAndView("403");	
		}
		model.put("program", new Advertisement());
		return new ModelAndView("addAdvertisementProgramAdminTile");
	}
	
	@RequestMapping(value = "addAdvertisement", method = RequestMethod.POST)
	public ModelAndView adminAddAdvertisement(ModelMap model,@Valid @Validated @ModelAttribute("Advertisement") Advertisement advertisement,BindingResult bindingResult,HttpServletRequest request) {
		if(SessionManagement.isSessionAlive()!=true){
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getAdvertisement()!=true){
			return new ModelAndView("403");	
		}
		if(bindingResult.hasErrors())
		{
			//System.out.println(bindingResult.toString());
			model.put("program", advertisement);
			return new ModelAndView("addAdvertisementProgramAdminTile");
		}
		if (advertisement.getFile()!=null && !advertisement.getFile().isEmpty()) {
			if(advertisement.getFile().getContentType().equalsIgnoreCase("image/jpg") || advertisement.getFile().getContentType().equalsIgnoreCase("image/jpeg") || advertisement.getFile().getContentType().equalsIgnoreCase("image/gif")){
				double size = advertisement.getFile().getSize();
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
						bytes = advertisement.getFile().getBytes();
						String dirPath=request.getRealPath("/")+"themes\\advertisement\\"+new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss",Locale.ENGLISH).format(new Date());
						File dir = new File(dirPath);
						if(dir.exists()){
							model.put("error", "Please Enter another folder Name because this folder name already exists.");
						}else{
							dir.mkdir();
						 String filePath = request.getRealPath("/")+"themes\\advertisement\\"+new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss",Locale.ENGLISH).format(new Date())+"\\"+f.getName()+".jpeg";
			                
						 //System.out.println(filePath);
						 BufferedOutputStream stream =
			                        new BufferedOutputStream(new FileOutputStream(new File(filePath)));
			                stream.write(bytes);
			                stream.close();
			                if(advertisement.getFile().getContentType().equalsIgnoreCase("image/gif")){
			                	//Advertisement.setPhotourl(filePath);
				                advertisement.setPhotourl("themes\\advertisement\\"+new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss",Locale.ENGLISH).format(new Date())+"\\"+f.getName()+".gif");
				                advertisement.setFolder_url(dirPath);
				                advertisement.setFolder_relative_url("themes\\advertisement\\"+new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss",Locale.ENGLISH).format(new Date()));
				                advertisement.setFrequency(0l);
				                AdvertisementDAO.save(advertisement);
				                model.put("msg", "File uploaded successfully");
				                model.put("filePath", "../themes/advertisement/"+new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss",Locale.ENGLISH).format(new Date())+"/"+f.getName()+".gif");	
			                }else{
			                //Advertisement.setPhotourl(filePath);
			                advertisement.setPhotourl("themes\\advertisement\\"+new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss",Locale.ENGLISH).format(new Date())+"\\"+f.getName()+".jpeg");
			                advertisement.setFolder_url(dirPath);
			                advertisement.setFolder_relative_url("themes\\advertisement\\"+new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss",Locale.ENGLISH).format(new Date()));
			                advertisement.setFrequency(0l);
			                AdvertisementDAO.save(advertisement);
			                model.put("msg", "File uploaded successfully");
			                model.put("filePath", "../themes/advertisement/"+new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss",Locale.ENGLISH).format(new Date())+"/"+f.getName()+".jpeg");
			                }
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
		model.put("program",advertisement);
		////System.out.println("here");
		return new ModelAndView("addAdvertisementProgramAdminTile");
	}
	
	@RequestMapping(value="viewAdvertisements",method=RequestMethod.GET)
	public ModelAndView viewDepartment(ModelMap model,@RequestParam(value = "msg", required = false) String msg,@RequestParam(value = "error", required = false) String error,HttpServletRequest request)
	{
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getAdvertisement()!=true){
			return new ModelAndView("403");	
		}
		if (error != null) {
			model.put("error", error);
		}
		if (msg != null) {
			model.put("msg", msg);
		}
		return new ModelAndView("viewAdvertisementProgramsTile");
	}
	
	@RequestMapping(value = "getAdvertisementList")
	public @ResponseBody DatatablesResponse<Advertisement> findAll(@DatatablesParams DatatablesCriterias criterias) {
	   DataSet<Advertisement> dataSet = AdvertisementDAO.findAdvertisementWithDatatablesCriterias(criterias);
	   return DatatablesResponse.build(dataSet, criterias);
	}
	
	@RequestMapping(value = "editAdvertisementForm", method = RequestMethod.POST)
	public ModelAndView editAdvertisementForm(ModelMap model,@RequestParam(value ="programId", required = false) Long programId,HttpServletRequest request) {
		if(SessionManagement.isSessionAlive()!=true){
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getAdvertisement()!=true){
			return new ModelAndView("403");	
		}
		model.put("program", AdvertisementDAO.findByDeptId(programId));
		return new ModelAndView("editAdvertisementAdminTile");
	}
	
	@RequestMapping(value = "updateAdvertisement", method = RequestMethod.POST)
	public ModelAndView updateAdvertisement(ModelMap model,@Valid @Validated @ModelAttribute("Advertisement") Advertisement Advertisement,BindingResult bindingResult,HttpServletRequest request) {
		if(SessionManagement.isSessionAlive()!=true){
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getAdvertisement()!=true){
			return new ModelAndView("403");	
		}
		if(bindingResult.hasErrors())
		{
			model.put("program", Advertisement);
			return new ModelAndView("editAdvertisementProgramAdminTile");
		}
		Advertisement p = AdvertisementDAO.findByDeptId(Advertisement.getId());
		p.setProgramName(Advertisement.getProgramName());
		p.setDescription(Advertisement.getDescription());
		p.setLocation(Advertisement.getLocation());
		p.setPosted_by(Advertisement.getPosted_by());
		p.setLink(Advertisement.getLink());
		p.setLink_target(Advertisement.getLink_target());
		p.setActive(Advertisement.getActive());
		AdvertisementDAO.update(p);
		return new ModelAndView("redirect:viewAdvertisements");
	}
	
	@RequestMapping(value = "selectAdvertisementForm", method = RequestMethod.POST)
	public ModelAndView selectAdvertisementForm(ModelMap model,@RequestParam(value = "programIdsel", required = false) Long programIdsel,@RequestParam(value = "msg", required = false) String msg,@RequestParam(value = "error", required = false) String error,HttpServletRequest request) throws IOException {
		//System.out.println(programIdsel);
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getAdvertisement()!=true){
			return new ModelAndView("403");	
		}
		if (error != null) {
			model.put("error", error);
		}
		if (msg != null) {
			model.put("msg", msg);
		}
		if (error != null || programIdsel==null || programIdsel==0) {
			return new ModelAndView("redirect:viewAdvertisements?error=Argument Invalid");
		}
		Advertisement p = AdvertisementDAO.findByDeptId(programIdsel);
		ArrayList<String> fileList = new ArrayList<String>();
		
		String dirPath = p.getFolder_url();//request.getRealPath("/")+"themes\\"+p.getAdvertisementName()+"\\";
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
		return new ModelAndView("selectAdvertisementProgramsTile");
		
	}
	@RequestMapping(value="moreAdvertisementPhotos")
	public ModelAndView upload(ModelMap model,@RequestParam(value ="photoId", required = false) Long photoId,HttpServletRequest request){		 
		if(SessionManagement.isSessionAlive()!=true){
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getAdvertisement()!=true){
			return new ModelAndView("403");	
		}
		model.put("photo", AdvertisementDAO.findByDeptId(photoId));
		model.put("extraParam", "&photoId="+photoId);
		
		return new ModelAndView("addMorePhotoAdvertisementTile");
	}
	
	@RequestMapping(value = "douploadAdvertisementPhoto", method = RequestMethod.POST)
    public ModelAndView uploadFileHandler(@Valid @Validated @ModelAttribute("uploadPhoto") Advertisement Advertisement,BindingResult bindingResult,@RequestParam("file") MultipartFile file,ModelMap model,HttpServletRequest request) {
		if(SessionManagement.isSessionAlive()!=true){
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getAdvertisement()!=true){
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
	                //Advertisement.setPhotourl(fname);
	                //Advertisement.setFile(file);
	               // //System.out.println("fileName     "   +uploadFile.getFileName());
	               
	                //AdvertisementDAO.save(Advertisement);
	               // //System.out.println("saveeeeeeeeeee            "   +  Advertisement.getId());
            	}          	
            	if(file.getContentType().equalsIgnoreCase("image/gif")){
            		byte[] bytes = file.getBytes(); 
	                int i = 0;
	                String filename = request.getParameter("addPhoto")+"\\"+Integer.toString(i)+".gif";
				    File f = new File(filename);
				    while(f.exists()) {
				        i++;
				        filename = request.getParameter("addPhoto")+"\\"+Integer.toString(i)+".gif";
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
	                //Advertisement.setPhotourl(fname);
	                //Advertisement.setFile(file);
	               // //System.out.println("fileName     "   +uploadFile.getFileName());
	               
	                //AdvertisementDAO.save(Advertisement);
	               // //System.out.println("saveeeeeeeeeee            "   +  Advertisement.getId());
            	}
            } catch (Exception e) {
                model.put("error", e.getMessage());
	        	return new ModelAndView("addMorePhotoAdvertisementTile");
            }
           // showAllItemDAO.save(uploadFile);
        	} /*else {
            model.put("error", "Please select file because file was empty");
        	return new ModelAndView("addMorePhotoTile");
        }*/
		  model.put("addPhoto",  AdvertisementDAO.list());
//		  //System.out.println("     photo        "+AdvertisementDAO.list());
      	return new ModelAndView("addMorePhotoAdvertisementTile");
    }
	
	@RequestMapping(value = "deleteAdvertisement", method = RequestMethod.GET)
	public ModelAndView deleteAdvertisement(ModelMap model,@Valid @Validated @ModelAttribute("id") Long id,BindingResult bindingResult,HttpServletRequest request) {
		////System.out.println(id);
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getAdvertisement()!=true){
			return new ModelAndView("403");	
		}
		if (id==null || id==0 || bindingResult.hasErrors() ) {
			return new ModelAndView("redirect:viewAdvertisements?error=Argument Invalid");
		}
		//String dirPath = AdvertisementDAO.findByDeptId(id).getPhotourl();
		String dirPath = AdvertisementDAO.findByDeptId(id).getFolder_url();//getPhotourl();
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
        AdvertisementDAO.delete(AdvertisementDAO.findByDeptId(id));
	
		return new ModelAndView("redirect:viewAdvertisements?msg=Deleted Sucessfully");
	}
	
	@RequestMapping(value = "deleteAdvertisementFile", method = RequestMethod.GET)
	public ModelAndView deleteFile(@Valid @Validated @ModelAttribute("fileDir") String fileDir,HttpServletRequest request) {
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getAdvertisement()!=true){
			return new ModelAndView("403");	
		}
		//System.out.println(fileDir);
		String dirPath = request.getRealPath("/")+fileDir;
		
		//programDAO.findByDeptId(id).getFolder_url();//getPhotourl();
		////System.out.println(dirPath.substring(0, dirPath.lastIndexOf("/")));
		
		File index = new File(dirPath);
		index.delete();
		
		return new ModelAndView("redirect:viewAdvertisements?msg=Deleted Sucessfully");
	}
}