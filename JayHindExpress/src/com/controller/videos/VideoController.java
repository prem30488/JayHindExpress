package com.controller.videos;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
import org.springframework.web.servlet.ModelAndView;

import com.dao.VideoDAO;
import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.github.dandelion.datatables.core.ajax.DatatablesResponse;
import com.github.dandelion.datatables.extras.spring3.ajax.DatatablesParams;
import com.models.User;
import com.models.Video;
import com.session.SessionManagement;

@Controller
@RequestMapping(value={"/user/", "/admin/"})
public class VideoController {

	@Autowired
	private VideoDAO videoDAO;
	
	@RequestMapping(value = "addVideoForm", method = RequestMethod.GET)
	public ModelAndView addVideoForm(ModelMap model,HttpServletRequest request) {
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getVideo()!=true){
			return new ModelAndView("403");	
		}
		model.put("video", new Video());
		return new ModelAndView("addVideoAdminTile");
	}
	
	@RequestMapping(value = "addVideo", method = RequestMethod.POST)
	public ModelAndView adminAddProgram(ModelMap model,@Valid @Validated @ModelAttribute("video") Video video,BindingResult bindingResult,HttpServletRequest request) {
		if(SessionManagement.isSessionAlive()!=true){
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getVideo()!=true){
			return new ModelAndView("403");	
		}
		try{
			video.setFrequency(0L);
		videoDAO.save(video);
        model.put("msg", "Video Saved successfully");
		}catch(Exception ex){
			ex.printStackTrace();
			model.put("error", "Some error in saving video");	
			return new ModelAndView("addVideoAdminTile");
		}
		
		if(bindingResult.hasErrors())
		{
			model.put("video", video);
			return new ModelAndView("addVideoAdminTile");
		}
		return new ModelAndView("redirect:viewVideos");
	}
	
	@RequestMapping(value="viewVideos",method=RequestMethod.GET)
	public ModelAndView viewDepartment(ModelMap model,@RequestParam(value = "msg", required = false) String msg,@RequestParam(value = "error", required = false) String error,HttpServletRequest request)
	{
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getVideo()!=true){
			return new ModelAndView("403");	
		}
		if (error != null) {
			model.put("error", error);
		}
		if (msg != null) {
			model.put("msg", msg);
		}
		return new ModelAndView("viewVideosTile");
	}
	
	@RequestMapping(value = "getVideoList")
	public @ResponseBody DatatablesResponse<Video> findAll(@DatatablesParams DatatablesCriterias criterias) {
	   DataSet<Video> dataSet = videoDAO.findVideoWithDatatablesCriterias(criterias);
	   return DatatablesResponse.build(dataSet, criterias);
	}
	
	@RequestMapping(value = "editVideoForm", method = RequestMethod.POST)
	public ModelAndView editProgramForm(ModelMap model,@RequestParam(value ="videoId", required = false) Long videoId,HttpServletRequest request) {
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getVideo()!=true){
			return new ModelAndView("403");	
		}
		model.put("video", videoDAO.findByDeptId(videoId));
		return new ModelAndView("editVideoAdminTile");
	}
	
	@RequestMapping(value = "updateVideo", method = RequestMethod.POST)
	public ModelAndView updateProgram(ModelMap model,@Valid @Validated @ModelAttribute("video") Video video,BindingResult bindingResult,HttpServletRequest request) {
		if(SessionManagement.isSessionAlive()!=true){
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getVideo()!=true){
			return new ModelAndView("403");	
		}
		if(bindingResult.hasErrors())
		{
			model.put("video", video);
			return new ModelAndView("editVideoAdminTile");
		}
		Video v = videoDAO.findByDeptId(video.getId());
		v.setVideoName(video.getVideoName());
		v.setDescription(video.getDescription());
		v.setPhotourl(video.getPhotourl());
		v.setVideo_id(video.getVideo_id());
		v.setPosted_by(video.getPosted_by());
		videoDAO.update(v);
		return new ModelAndView("redirect:viewVideos");
	}
	
	@RequestMapping(value = "selectVideoForm", method = RequestMethod.POST)
	public ModelAndView selectProgramForm(ModelMap model,@RequestParam(value = "videoIdsel", required = false) Long videoId,@RequestParam(value = "msg", required = false) String msg,@RequestParam(value = "error", required = false) String error,HttpServletRequest request) throws IOException {
		//System.out.println(videoId);
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getVideo()!=true){
			return new ModelAndView("403");	
		}
		if (error != null) {
			model.put("error", error);
		}
		if (msg != null) {
			model.put("msg", msg);
		}
		if (error != null || videoId==null || videoId==0) {
			return new ModelAndView("redirect:viewVideos?error=Argument Invalid");
		}
		
		model.put("video", videoDAO.findByDeptId(videoId));
		return new ModelAndView("selectVideosTile");
		
	}
	
	@RequestMapping(value = "getVideoNames", method = RequestMethod.GET)
	public @ResponseBody List<Video> getVideoNames() throws IOException {
		return videoDAO.list();
	}
	
	@RequestMapping(value = "deleteVideo", method = RequestMethod.GET)
	public ModelAndView deleteProgram(ModelMap model,@Valid @Validated @ModelAttribute("id") Long id,BindingResult bindingResult,HttpServletRequest request) {
		////System.out.println(id);
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(((User)request.getSession().getAttribute("user")).getVideo()!=true){
			return new ModelAndView("403");	
		}		
		if (id==null || id==0 || bindingResult.hasErrors() ) {
			return new ModelAndView("redirect:viewPrograms?error=Argument Invalid");
		}
		//String dirPath = videoDAO.findByDeptId(id).getPhotourl();
		////System.out.println(dirPath.substring(0, dirPath.lastIndexOf("/")));
		//File dir = new File(request.getRealPath("/")+dirPath);
        //if (dir.exists()){
          //  dir.delete();
            
        //}
        videoDAO.delete(videoDAO.findByDeptId(id));
		return new ModelAndView("redirect:viewVideos?msg=Deleted Sucessfully");
	}
	
}