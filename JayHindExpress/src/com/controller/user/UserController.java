package com.controller.user;

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

import com.dao.RoleDAO;
import com.dao.UserDAO;
import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.github.dandelion.datatables.core.ajax.DatatablesResponse;
import com.github.dandelion.datatables.extras.spring3.ajax.DatatablesParams;
import com.models.Role;
import com.models.User;
import com.models.UserRole;
import com.session.SessionManagement;

@Controller
public class UserController {

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private RoleDAO roleDAO;
	
	
	@RequestMapping(value = "/admin/addUserForm", method = RequestMethod.GET)
	public ModelAndView adminAddUserForm(ModelMap model,HttpServletRequest request) {
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(!((User)request.getSession().getAttribute("user")).getUserName().equalsIgnoreCase("admin")){
			return new ModelAndView("403");	
		}
		model.put("program", new User());
		return new ModelAndView("addUserAdminTile");
	}
	
	@RequestMapping(value = "/admin/addUser", method = RequestMethod.POST)
	public ModelAndView adminAddUser(ModelMap model,@Valid @Validated @ModelAttribute("program") User program,BindingResult bindingResult,HttpServletRequest request) {
		if(SessionManagement.isSessionAlive()!=true){
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(!((User)request.getSession().getAttribute("user")).getUserName().equalsIgnoreCase("admin")){
			return new ModelAndView("403");	
		}
		if (program.getFile()!=null && !program.getFile().isEmpty()) {
			if(program.getFile().getContentType().equalsIgnoreCase("image/jpg") || program.getFile().getContentType().equalsIgnoreCase("image/jpeg")){
				double size = program.getFile().getSize();
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
						bytes = program.getFile().getBytes();
						String dirPath=request.getRealPath("/")+"themes\\user\\"+new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss",Locale.ENGLISH).format(new Date());
						File dir = new File(dirPath);
						if(dir.exists()){
							model.put("error", "Please Enter another folder Name because this folder name already exists.");
						}else{
							dir.mkdir();
						 String filePath = request.getRealPath("/")+"themes\\user\\"+new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss",Locale.ENGLISH).format(new Date())+"\\"+f.getName()+".jpeg";
			                
						 //System.out.println(filePath);
						 BufferedOutputStream stream =
			                        new BufferedOutputStream(new FileOutputStream(new File(filePath)));
			                stream.write(bytes);
			                stream.close();
			                //user.setPhotourl(filePath);
			                program.setPhotourl("themes\\user\\"+new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss",Locale.ENGLISH).format(new Date())+"\\"+f.getName()+".jpeg");
			                program.setFolder_url(dirPath);
			                program.setFolder_relative_url("themes\\user\\"+new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss",Locale.ENGLISH).format(new Date()));
			                program.setFrequency(0l);
			                
			                program.getRoles().add(roleDAO.findByRoleId(2L));
			                
			                userDAO.save(program);
			                
			                model.put("msg", "File uploaded successfully");
			                model.put("filePath", "../themes/user/"+new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss",Locale.ENGLISH).format(new Date())+"/"+f.getName()+".jpeg");
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
		if(bindingResult.hasErrors())
		{
			model.put("program", program);
			return new ModelAndView("addUserAdminTile");
		}
		return new ModelAndView("addUserAdminTile");
	}
	
	@RequestMapping(value="/admin/viewUsers",method=RequestMethod.GET)
	public ModelAndView viewDepartment(ModelMap model,@RequestParam(value = "msg", required = false) String msg,@RequestParam(value = "error", required = false) String error,HttpServletRequest request)
	{
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(!((User)request.getSession().getAttribute("user")).getUserName().equalsIgnoreCase("admin")){
			return new ModelAndView("403");	
		}
		if (error != null) {
			model.put("error", error);
		}
		if (msg != null) {
			model.put("msg", msg);
		}
		return new ModelAndView("viewUsersTile");
	}
	
	@RequestMapping(value = "/admin/getUserList")
	public @ResponseBody DatatablesResponse<User> findAll(@DatatablesParams DatatablesCriterias criterias) {
	   DataSet<User> dataSet = userDAO.findUserWithDatatablesCriterias(criterias);
	   return DatatablesResponse.build(dataSet, criterias);
	}
	
	@RequestMapping(value = "/admin/editUserForm", method = RequestMethod.POST)
	public ModelAndView editUserForm(ModelMap model,@RequestParam(value ="programId", required = true) Long userId,HttpServletRequest request) {
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(!((User)request.getSession().getAttribute("user")).getUserName().equalsIgnoreCase("admin")){
			return new ModelAndView("403");	
		}
		model.put("program", userDAO.findByDeptId(userId));
		return new ModelAndView("editUserAdminTile");
	}
	
	@RequestMapping(value = "/admin/updateUser", method = RequestMethod.POST)
	public ModelAndView updateUser(ModelMap model,@Valid @Validated @ModelAttribute("program") User user,BindingResult bindingResult,HttpServletRequest request) {
		if(SessionManagement.isSessionAlive()!=true){
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(!((User)request.getSession().getAttribute("user")).getUserName().equalsIgnoreCase("admin")){
			return new ModelAndView("403");	
		}
		if(bindingResult.hasErrors())
		{
			model.put("program", user);
			return new ModelAndView("editUserAdminTile");
		}
		
		User p = userDAO.findByDeptId(user.getUserId());
		p.setUserName(user.getUserName());
		p.setDescription(user.getDescription());
		p.setLocation(user.getLocation());
		p.setPosted_by(user.getPosted_by());
		p.setEnabled(user.getEnabled());
		p.setAccountNonExpired(user.getAccountNonExpired());
		p.setAccountNonLocked(user.getAccountNonLocked());
		p.setCredentialsNonExpired(user.getCredentialsNonExpired());
		p.setPassword(user.getPassword());
		
		p.setInternational(user.getInternational());
		p.setNational(user.getInternational());
		p.setState(user.getState());
		p.setBusiness(user.getBusiness());
		p.setSport(user.getSport());
		p.setTechnology(user.getTechnology());
		p.setAstro(user.getAstro());
		p.setOther(user.getOther());
		p.setVideo(user.getVideo());
		p.setPoll(user.getPoll());
		p.setEntertainment(user.getEntertainment());
		p.setFashion(user.getFashion());
		p.setGame(user.getGame());
		p.setLink(user.getLink());
		p.setSponser(user.getSponser());
		p.setAdvertisement(user.getAdvertisement());
		p.setGreeting(user.getGreeting());
		p.setPhotography(user.getPhotography());
		p.setContact(user.getContact());
		
		
		userDAO.update(p);
		return new ModelAndView("redirect:viewUsers");
	}
	
	@RequestMapping(value = "/admin/selectUserForm", method = RequestMethod.POST)
	public ModelAndView selectUserForm(ModelMap model,@RequestParam(value = "programIdsel", required = true) Long userId,@RequestParam(value = "msg", required = false) String msg,@RequestParam(value = "error", required = false) String error,HttpServletRequest request) throws IOException {
		//System.out.println(userId);
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(!((User)request.getSession().getAttribute("user")).getUserName().equalsIgnoreCase("admin")){
			return new ModelAndView("403");	
		}
		//System.out.println(userId);
		if (error != null) {
			model.put("error", error);
		}
		if (msg != null) {
			model.put("msg", msg);
		}
		if (error != null || userId==null || userId==0) {
			return new ModelAndView("redirect:viewUsers?error=Argument Invalid");
		}
		User p = userDAO.findByDeptId(userId);
		ArrayList<String> fileList = new ArrayList<String>();
		
		String dirPath = p.getFolder_url();//request.getRealPath("/")+"themes\\"+p.getUserName()+"\\";
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
		return new ModelAndView("selectUsersTile");
		
	}
	@RequestMapping(value="/admin/moreUserPhotos")
	public ModelAndView upload(ModelMap model,@RequestParam(value ="photoId", required = false) Long photoId,HttpServletRequest request){		 
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(!((User)request.getSession().getAttribute("user")).getUserName().equalsIgnoreCase("admin")){
			return new ModelAndView("403");	
		}
		model.put("photo", userDAO.findByDeptId(photoId));
		model.put("extraParam", "&photoId="+photoId);
		
		return new ModelAndView("addMorePhotoUserTile");
	}
	
	@RequestMapping(value = "/admin/douploadUserPhoto", method = RequestMethod.POST)
    public ModelAndView uploadFileHandler(@Valid @Validated @ModelAttribute("uploadPhoto") User user,BindingResult bindingResult,@RequestParam("file") MultipartFile file,ModelMap model,HttpServletRequest request) {
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(!((User)request.getSession().getAttribute("user")).getUserName().equalsIgnoreCase("admin")){
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
	                //user.setPhotourl(fname);
	                //user.setFile(file);
	               // //System.out.println("fileName     "   +uploadFile.getFileName());
	               
	                //userDAO.save(user);
	               // //System.out.println("saveeeeeeeeeee            "   +  user.getId());
            	}          	
          
            } catch (Exception e) {
                model.put("error", e.getMessage());
	        	return new ModelAndView("addMorePhotoUserTile");
            }
           // showAllItemDAO.save(uploadFile);
        	} /*else {
            model.put("error", "Please select file because file was empty");
        	return new ModelAndView("addMorePhotoTile");
        }*/
		  model.put("addPhoto",  userDAO.list());
//		  //System.out.println("     photo        "+userDAO.list());
      	return new ModelAndView("addMorePhotoTile");
    }
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/admin/deleteUser", method = RequestMethod.GET)
	public ModelAndView deleteUser(ModelMap model,@Valid @Validated @ModelAttribute("id") Long id,BindingResult bindingResult,HttpServletRequest request) {
		////System.out.println(id);
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(!((User)request.getSession().getAttribute("user")).getUserName().equalsIgnoreCase("admin")){
			return new ModelAndView("403");	
		}
		if (id==null || id==0 || bindingResult.hasErrors() ) {
			return new ModelAndView("redirect:viewUsers?error=Argument Invalid");
		}
		String dirPath = userDAO.findByDeptId(id).getFolder_url();//getPhotourl();
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
        userDAO.delete(userDAO.findByDeptId(id));
		return new ModelAndView("redirect:viewUsers?msg=Deleted Sucessfully");
	}
	
	@RequestMapping(value = "/admin/deleteUserFile", method = RequestMethod.GET)
	public ModelAndView deleteFile(@Valid @Validated @ModelAttribute("fileDir") String fileDir,HttpServletRequest request) {
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(!((User)request.getSession().getAttribute("user")).getUserName().equalsIgnoreCase("admin")){
			return new ModelAndView("403");	
		}
		//System.out.println(fileDir);
		String dirPath = request.getRealPath("/")+fileDir;
		
		//userDAO.findByDeptId(id).getFolder_url();//getPhotourl();
		////System.out.println(dirPath.substring(0, dirPath.lastIndexOf("/")));
		
		File index = new File(dirPath);
		index.delete();
		
		return new ModelAndView("redirect:viewUsers?msg=Deleted Sucessfully");
	}
}