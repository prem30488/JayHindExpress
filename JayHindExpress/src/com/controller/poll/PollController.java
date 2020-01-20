package com.controller.poll;

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

import com.dao.PollDAO;
import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.github.dandelion.datatables.core.ajax.DatatablesResponse;
import com.github.dandelion.datatables.extras.spring3.ajax.DatatablesParams;
import com.models.Poll;
import com.models.User;
import com.session.SessionManagement;

@Controller
@RequestMapping(value={"/user/", "/admin/"})
public class PollController {

	@Autowired
	private PollDAO PollDAO;
	
	@RequestMapping(value = "addPollForm", method = RequestMethod.GET)
	public ModelAndView adminAddPollForm(ModelMap model,HttpServletRequest request) {
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getPoll()!=true){
			return new ModelAndView("403");	
		}
		model.put("program", new Poll());
		return new ModelAndView("addPollProgramAdminTile");
	}
	
	@RequestMapping(value = "addPoll", method = RequestMethod.POST)
	public ModelAndView adminAddPoll(ModelMap model,@Valid @Validated @ModelAttribute("Poll") Poll poll,BindingResult bindingResult,HttpServletRequest request) {
		if(SessionManagement.isSessionAlive()!=true){
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getPoll()!=true){
			return new ModelAndView("403");	
		}
		if(bindingResult.hasErrors())
		{
			//System.out.println(bindingResult.toString());
			model.put("program", poll);
			return new ModelAndView("addPollProgramAdminTile");
		}
		/*if (poll.getFile()!=null && !poll.getFile().isEmpty()) {
			if(poll.getFile().getContentType().equalsIgnoreCase("image/jpg") || poll.getFile().getContentType().equalsIgnoreCase("image/jpeg")){
				double size = poll.getFile().getSize();
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
						bytes = poll.getFile().getBytes();
						String dirPath=request.getRealPath("/")+"themes\\poll\\"+new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss",Locale.ENGLISH).format(new Date());
						File dir = new File(dirPath);
						if(dir.exists()){
							model.put("error", "Please Enter another folder Name because this folder name already exists.");
						}else{
							dir.mkdir();
						 String filePath = request.getRealPath("/")+"themes\\poll\\"+new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss",Locale.ENGLISH).format(new Date())+"\\"+f.getName()+".jpeg";
			                
						 //System.out.println(filePath);
						 BufferedOutputStream stream =
			                        new BufferedOutputStream(new FileOutputStream(new File(filePath)));
			                stream.write(bytes);
			                stream.close();
			                //Poll.setPhotourl(filePath);
			                poll.setPhotourl("themes\\poll\\"+new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss",Locale.ENGLISH).format(new Date())+"\\"+f.getName()+".jpeg");
			                poll.setFolder_url(dirPath);
			                poll.setFolder_relative_url("themes\\poll\\"+new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss",Locale.ENGLISH).format(new Date()));
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	               
	                
	                //console call
	                String command="c:\\ImageMagick\\convert.exe "+request.getRealPath("/")+"themes\\ProfileImages\\"+SessionManagement.getUserName()+".jpeg" + " -resize 10% "+ request.getRealPath("/")+"themes\\ProfileImages\\"+SessionManagement.getUserName()+"_icon.jpeg";
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
		}*/
		poll.setFrequency(0l);
		poll.setYes(0l);
		poll.setNo(0l);
		poll.setNone(0l);
        PollDAO.save(poll);
        model.put("msg", "File uploaded successfully");
		model.put("program",poll);
		////System.out.println("here");
		return new ModelAndView("addPollProgramAdminTile");
	}
	
	@RequestMapping(value="viewPolls",method=RequestMethod.GET)
	public ModelAndView viewDepartment(ModelMap model,@RequestParam(value = "msg", required = false) String msg,@RequestParam(value = "error", required = false) String error,HttpServletRequest request)
	{
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getPoll()!=true){
			return new ModelAndView("403");	
		}
		if (error != null) {
			model.put("error", error);
		}
		if (msg != null) {
			model.put("msg", msg);
		}
		return new ModelAndView("viewPollProgramsTile");
	}
	
	@RequestMapping(value = "getPollList")
	public @ResponseBody DatatablesResponse<Poll> findAll(@DatatablesParams DatatablesCriterias criterias) {
	   DataSet<Poll> dataSet = PollDAO.findPollWithDatatablesCriterias(criterias);
	   return DatatablesResponse.build(dataSet, criterias);
	}
	
	@RequestMapping(value = "editPollForm", method = RequestMethod.POST)
	public ModelAndView editPollForm(ModelMap model,@RequestParam(value ="programId", required = false) Long programId,HttpServletRequest request) {
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getPoll()!=true){
			return new ModelAndView("403");	
		}
		model.put("program", PollDAO.findByDeptId(programId));
		return new ModelAndView("editPollAdminTile");
	}
	
	@RequestMapping(value = "updatePoll", method = RequestMethod.POST)
	public ModelAndView updatePoll(ModelMap model,@Valid @Validated @ModelAttribute("Poll") Poll Poll,BindingResult bindingResult,HttpServletRequest request) {
		if(SessionManagement.isSessionAlive()!=true){
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getPoll()!=true){
			return new ModelAndView("403");	
		}
		if(bindingResult.hasErrors())
		{
			model.put("program", Poll);
			return new ModelAndView("editPollProgramAdminTile");
		}
		Poll p = PollDAO.findByDeptId(Poll.getId());
		p.setProgramName(Poll.getProgramName());
		//p.setDescription(Poll.getDescription());
		p.setLocation(Poll.getLocation());
		p.setPosted_by(Poll.getPosted_by());
		PollDAO.update(p);
		return new ModelAndView("redirect:viewPolls");
	}
	
	@RequestMapping(value = "selectPollForm", method = RequestMethod.POST)
	public ModelAndView selectPollForm(ModelMap model,@RequestParam(value = "programIdsel", required = false) Long programIdsel,@RequestParam(value = "msg", required = false) String msg,@RequestParam(value = "error", required = false) String error,HttpServletRequest request) throws IOException {
		//System.out.println(programIdsel);
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getPoll()!=true){
			return new ModelAndView("403");	
		}
		if (error != null) {
			model.put("error", error);
		}
		if (msg != null) {
			model.put("msg", msg);
		}
		if (error != null || programIdsel==null || programIdsel==0) {
			return new ModelAndView("redirect:viewPolls?error=Argument Invalid");
		}
		Poll p = PollDAO.findByDeptId(programIdsel);
		//ArrayList<String> fileList = new ArrayList<String>();
		
		//String dirPath = p.getFolder_url();//request.getRealPath("/")+"themes\\"+p.getPollName()+"\\";
		//File folder = new File(dirPath);
		//if(folder.exists()){
		//for (final File fileEntry : folder.listFiles()) {
		//	fileList.add(fileEntry.getName());
		//	//System.out.println(fileEntry.getName());
	    //}
		//}
		//model.put("fileList", fileList);
		//model.put("fileDir", p.getFolder_relative_url());
		model.put("selectProgram", p);
		return new ModelAndView("selectPollProgramsTile");
		
	}
	/*@RequestMapping(value="/admin/morePollPhotos")
	public ModelAndView upload(ModelMap model,@RequestParam(value ="photoId", required = false) Long photoId){		 
		model.put("photo", PollDAO.findByDeptId(photoId));
		model.put("extraParam", "&photoId="+photoId);
		
		return new ModelAndView("addMorePhotoPollTile");
	}
	
*/	/*@RequestMapping(value = "/admin/douploadPollPhoto", method = RequestMethod.POST)
    public ModelAndView uploadFileHandler(@Valid @Validated @ModelAttribute("uploadPhoto") Poll Poll,BindingResult bindingResult,@RequestParam("file") MultipartFile file,ModelMap model,HttpServletRequest request) {

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
	                //Poll.setPhotourl(fname);
	                //Poll.setFile(file);
	               // //System.out.println("fileName     "   +uploadFile.getFileName());
	               
	                //PollDAO.save(Poll);
	               // //System.out.println("saveeeeeeeeeee            "   +  Poll.getId());
            	}          	
          
            } catch (Exception e) {
                model.put("error", e.getMessage());
	        	return new ModelAndView("addMorePhotoPollTile");
            }
           // showAllItemDAO.save(uploadFile);
        	} else {
            model.put("error", "Please select file because file was empty");
        	return new ModelAndView("addMorePhotoTile");
        }
		  model.put("addPhoto",  PollDAO.list());
//		  //System.out.println("     photo        "+PollDAO.list());
      	return new ModelAndView("addMorePhotoPollTile");
    }
*/	
	@RequestMapping(value = "deletePoll", method = RequestMethod.GET)
	public ModelAndView deletePoll(ModelMap model,@Valid @Validated @ModelAttribute("id") Long id,BindingResult bindingResult,HttpServletRequest request) {
		////System.out.println(id);
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getPoll()!=true){
			return new ModelAndView("403");	
		}
		
		if (id==null || id==0 || bindingResult.hasErrors() ) {
			return new ModelAndView("redirect:viewPolls?error=Argument Invalid");
		}
		/*String dirPath = PollDAO.findByDeptId(id).getPhotourl();
		////System.out.println(dirPath.substring(0, dirPath.lastIndexOf("/")));
		File dir = new File(request.getRealPath("/")+dirPath);
        if (dir.exists()){
            dir.delete();
            
        }*/
        PollDAO.delete(PollDAO.findByDeptId(id));
		return new ModelAndView("redirect:viewPolls?msg=Deleted Sucessfully");
	}
}