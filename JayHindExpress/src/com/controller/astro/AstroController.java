package com.controller.astro;

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

import com.dao.AstroDAO;
import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.github.dandelion.datatables.core.ajax.DatatablesResponse;
import com.github.dandelion.datatables.extras.spring3.ajax.DatatablesParams;
import com.image.imagemagick.Imagemagick;
import com.models.Astro;
import com.models.User;
import com.session.SessionManagement;

@Controller
@RequestMapping(value={"/user/", "/admin/"})
public class AstroController {

	@Autowired
	private AstroDAO AstroDAO;
	
	@Autowired
	private Imagemagick imagemagick;
	
	@RequestMapping(value = "addAstroForm", method = RequestMethod.GET)
	public ModelAndView adminAddAstroForm(ModelMap model,HttpServletRequest request) {
		if(SessionManagement.isSessionAlive()!=true){
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getAstro()!=true){
			return new ModelAndView("403");	
		}
		model.put("program", new Astro());
		return new ModelAndView("addAstroProgramAdminTile");
	}
	
	@RequestMapping(value = "addAstro", method = RequestMethod.POST)
	public ModelAndView adminAddAstro(ModelMap model,@Valid @Validated @ModelAttribute("Astro") Astro astro,BindingResult bindingResult,HttpServletRequest request) {
		if(SessionManagement.isSessionAlive()!=true){
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getAstro()!=true){
			return new ModelAndView("403");	
		}
		if(bindingResult.hasErrors())
		{
			//System.out.println(bindingResult.toString());
			model.put("program", astro);
			return new ModelAndView("addAstroProgramAdminTile");
		}
		if (astro.getFile()!=null && !astro.getFile().isEmpty()) {
			if(astro.getFile().getContentType().equalsIgnoreCase("image/jpg") || astro.getFile().getContentType().equalsIgnoreCase("image/jpeg")){
				double size = astro.getFile().getSize();
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
						bytes = astro.getFile().getBytes();
						String dirPath=request.getRealPath("/")+"themes\\astro\\"+new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss",Locale.ENGLISH).format(new Date());
						File dir = new File(dirPath);
						if(dir.exists()){
							model.put("error", "Please Enter another folder Name because this folder name already exists.");
						}else{
							dir.mkdir();
						 String filePath = request.getRealPath("/")+"themes\\astro\\"+new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss",Locale.ENGLISH).format(new Date())+"\\"+f.getName()+".jpeg";
			                
						 //System.out.println(filePath);
						 BufferedOutputStream stream =
			                        new BufferedOutputStream(new FileOutputStream(new File(filePath)));
			                stream.write(bytes);
			                stream.close();
			                //Astro.setPhotourl(filePath);
			                astro.setPhotourl("themes\\astro\\"+new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss",Locale.ENGLISH).format(new Date())+"\\"+f.getName()+".jpeg");
			                astro.setFolder_url(dirPath);
			                astro.setFolder_relative_url("themes\\astro\\"+new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss",Locale.ENGLISH).format(new Date()));
			                astro.setFrequency(0l);
			                AstroDAO.save(astro);
			                model.put("msg", "File uploaded successfully");
			                model.put("filePath", "../themes/astro/"+new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss",Locale.ENGLISH).format(new Date())+"/"+f.getName()+".jpeg");

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
		model.put("program",astro);
		return new ModelAndView("addAstroProgramAdminTile");
	}
	
	@RequestMapping(value="viewAstro",method=RequestMethod.GET)
	public ModelAndView viewDepartment(ModelMap model,@RequestParam(value = "msg", required = false) String msg,@RequestParam(value = "error", required = false) String error,HttpServletRequest request)
	{
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getAstro()!=true){
			return new ModelAndView("403");	
		}
		if (error != null) {
			model.put("error", error);
		}
		if (msg != null) {
			model.put("msg", msg);
		}
		return new ModelAndView("viewAstroProgramsTile");
	}
	
	@RequestMapping(value = "getAstroList")
	public @ResponseBody DatatablesResponse<Astro> findAll(@DatatablesParams DatatablesCriterias criterias) {
	   DataSet<Astro> dataSet = AstroDAO.findAstroWithDatatablesCriterias(criterias);
	   return DatatablesResponse.build(dataSet, criterias);
	}
	
	@RequestMapping(value = "editAstroForm", method = RequestMethod.POST)
	public ModelAndView editAstroForm(ModelMap model,@RequestParam(value ="programId", required = false) Long programId,HttpServletRequest request) {
		if(SessionManagement.isSessionAlive()!=true){
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getAstro()!=true){
			return new ModelAndView("403");	
		}
		model.put("program", AstroDAO.findByDeptId(programId));
		return new ModelAndView("editAstroAdminTile");
	}
	
	@RequestMapping(value = "updateAstro", method = RequestMethod.POST)
	public ModelAndView updateAstro(ModelMap model,@Valid @Validated @ModelAttribute("Astro") Astro Astro,BindingResult bindingResult,HttpServletRequest request) {
		if(SessionManagement.isSessionAlive()!=true){
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getAstro()!=true){
			return new ModelAndView("403");	
		}
		if(bindingResult.hasErrors())
		{
			model.put("program", Astro);
			return new ModelAndView("editAstroProgramAdminTile");
		}
		Astro p = AstroDAO.findByDeptId(Astro.getId());
		p.setProgramName(Astro.getProgramName());
		p.setDescription(Astro.getDescription());
		p.setLocation(Astro.getLocation());
		p.setPosted_by(Astro.getPosted_by());
		AstroDAO.update(p);
		return new ModelAndView("redirect:viewAstro");
	}
	
	@RequestMapping(value = "selectAstroForm", method = RequestMethod.POST)
	public ModelAndView selectAstroForm(ModelMap model,@RequestParam(value = "programIdsel", required = false) Long programIdsel,@RequestParam(value = "msg", required = false) String msg,@RequestParam(value = "error", required = false) String error,HttpServletRequest request) throws IOException {
		//System.out.println(programIdsel);
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getAstro()!=true){
			return new ModelAndView("403");	
		}
		if (error != null) {
			model.put("error", error);
		}
		if (msg != null) {
			model.put("msg", msg);
		}
		if (error != null || programIdsel==null || programIdsel==0) {
			return new ModelAndView("redirect:viewAstro?error=Argument Invalid");
		}
		Astro p = AstroDAO.findByDeptId(programIdsel);
		ArrayList<String> fileList = new ArrayList<String>();
		
		String dirPath = p.getFolder_url();//request.getRealPath("/")+"themes\\"+p.getAstroName()+"\\";
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
		return new ModelAndView("selectAstroProgramsTile");
		
	}
	@RequestMapping(value="moreAstroPhotos")
	public ModelAndView upload(ModelMap model,@RequestParam(value ="photoId", required = false) Long photoId,HttpServletRequest request){		 
		if(SessionManagement.isSessionAlive()!=true){
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getAstro()!=true){
			return new ModelAndView("403");	
		}
		
		model.put("photo", AstroDAO.findByDeptId(photoId));
		model.put("extraParam", "&photoId="+photoId);
		
		return new ModelAndView("addMorePhotoAstroTile");
	}
	
	@RequestMapping(value = "douploadAstroPhoto", method = RequestMethod.POST)
    public ModelAndView uploadFileHandler(@Valid @Validated @ModelAttribute("uploadPhoto") Astro Astro,BindingResult bindingResult,@RequestParam("file") MultipartFile file,ModelMap model,HttpServletRequest request) {
		if(SessionManagement.isSessionAlive()!=true){
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getAstro()!=true){
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
	                
	                Astro = AstroDAO.findByDeptId(Astro.getId());
	                Astro.setPhotourl(fname);
	                AstroDAO.update(Astro);
	                
	                //Astro.setPhotourl(fname);
	                //Astro.setFile(file);
	               // //System.out.println("fileName     "   +uploadFile.getFileName());
	               
	                //AstroDAO.save(Astro);
	               // //System.out.println("saveeeeeeeeeee            "   +  Astro.getId());
            	}          	
          
            } catch (Exception e) {
                model.put("error", e.getMessage());
	        	return new ModelAndView("addMorePhotoAstroTile");
            }
           // showAllItemDAO.save(uploadFile);
        	} /*else {
            model.put("error", "Please select file because file was empty");
        	return new ModelAndView("addMorePhotoTile");
        }*/
		  model.put("addPhoto",  AstroDAO.list());
//		  //System.out.println("     photo        "+AstroDAO.list());
      	return new ModelAndView("addMorePhotoAstroTile");
    }
	
	@RequestMapping(value = "deleteAstro", method = RequestMethod.GET)
	public ModelAndView deleteAstro(ModelMap model,@Valid @Validated @ModelAttribute("id") Long id,BindingResult bindingResult,HttpServletRequest request) {
		////System.out.println(id);
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getAstro()!=true){
			return new ModelAndView("403");	
		}
		if (id==null || id==0 || bindingResult.hasErrors() ) {
			return new ModelAndView("redirect:viewAstro?error=Argument Invalid");
		}
		//String dirPath = AstroDAO.findByDeptId(id).getPhotourl();
		String dirPath = AstroDAO.findByDeptId(id).getFolder_url();//getPhotourl();
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
        AstroDAO.delete(AstroDAO.findByDeptId(id));
	
		return new ModelAndView("redirect:viewAstro?msg=Deleted Sucessfully");
	}
	
	@RequestMapping(value = "deleteAstroFile", method = RequestMethod.GET)
	public ModelAndView deleteFile(@Valid @Validated @ModelAttribute("fileDir") String fileDir,HttpServletRequest request) {
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getAstro()!=true){
			return new ModelAndView("403");	
		}
		//System.out.println(fileDir);
		String dirPath = request.getRealPath("/")+fileDir;
		
		//programDAO.findByDeptId(id).getFolder_url();//getPhotourl();
		////System.out.println(dirPath.substring(0, dirPath.lastIndexOf("/")));
		
		File index = new File(dirPath);
		index.delete();
		
		return new ModelAndView("redirect:viewAstro?msg=Deleted Sucessfully");
	}
}