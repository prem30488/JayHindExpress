package com.controller.front;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

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
import org.springframework.web.servlet.ModelAndView;

import com.controller.international.InternationalController;
import com.dao.AdvertisementDAO;
import com.dao.AstroDAO;
import com.dao.BusinessDAO;
import com.dao.ContactDAO;
import com.dao.EntertainmentDAO;
import com.dao.FashionDAO;
import com.dao.GameDAO;
import com.dao.GreetingDAO;
import com.dao.HeadingDAO;
import com.dao.HomePageDAO;
import com.dao.LinkDAO;
import com.dao.NationalProgramDAO;
import com.dao.OtherProgramDAO;
import com.dao.PhotographyDAO;
import com.dao.PollDAO;
import com.dao.ProgramDAO;
import com.dao.SponsorDAO;
import com.dao.SportDAO;
import com.dao.StateProgramDAO;
import com.dao.TechnologyDAO;
import com.dao.VideoDAO;
import java.util.List;

import com.models.Advertisement;
import com.models.Astro;
import com.models.Business;
import com.models.Contact;
import com.models.Entertainment;
import com.models.Fashion;
import com.models.Game;
import com.models.Greeting;
import com.models.HomePage;
import com.models.Link;
import com.models.NationalProgram;
import com.models.OtherProgram;
import com.models.Photography;
import com.models.Poll;
import com.models.Program;
import com.models.SampleContent;
import com.models.Sponsor;
import com.models.Sport;
import com.models.StateProgram;
import com.models.Technology;
import com.models.Video;

@Controller
public class FrontController {

	@Autowired
	private HomePageDAO homepageDAO;
	
	@Autowired
	private EntertainmentDAO EntertainmentDAO;
	
	@Autowired
	private FashionDAO fashionDAO;
	
	@Autowired
	private GameDAO gameDAO;
	
	@Autowired
	private ContactDAO contactDAO;
	
	@Autowired
	private ProgramDAO programDAO;

	@Autowired
	private VideoDAO videoDAO;
	
	@Autowired
	private NationalProgramDAO nationalProgramDAO;
	
	@Autowired
	private StateProgramDAO stateProgramDAO;
	
	@Autowired
	private BusinessDAO businessDAO;
	
	@Autowired
	private SportDAO sportDAO;
	
	@Autowired
	private TechnologyDAO technologyDAO;
	
	@Autowired
	private OtherProgramDAO otherProgramDAO;
	
	@Autowired
	private AstroDAO astroDAO;
	
	@Autowired
	private PollDAO pollDAO;
	
	@Autowired
	private HeadingDAO dao;
	
	@Autowired
	private LinkDAO linkdao;
	
	@Autowired
	private SponsorDAO sponsordao;
	
	@Autowired
	private AdvertisementDAO advertisementdao;
	
	@Autowired
	private PhotographyDAO photographyDAO;
	
	@Autowired
	private GreetingDAO greetingdao;
	
	private ModelMap defaulthomepage(ModelMap model){
		int  page = 1;
		int recordsPerPage = 10;
		HomePage configuration = homepageDAO.getDefaultconfig();
		if(dao.list().size()>0){
			model.put("heading", dao.list().get(0));
		}
		
		List<SampleContent> items = new ArrayList<SampleContent>();
		
		if(configuration.getInternational()==true){
		for(Program p : programDAO.getItemByModel((page-1)*recordsPerPage,recordsPerPage)){
			////System.out.println(p.getDescription());
			//p.setDescription("समारोह के");
			SampleContent content  = new SampleContent();
			content.setId(p.getId());
			content.setTitle(p.getProgramName());
			content.setUrl(url+"selectProgram?id="+p.getId()+"&albumName="+p.getProgramName());
			content.setPhotoURL(p.getPhotourl());
			content.setSummary(p.getDescription());
			content.setCreatedDate(p.getCreatedDate());
			content.setFrequency(p.getFrequency());
			content.setCategory("international");
			items.add(content);			
		}
		}
		if(configuration.getNational()==true){
		for(NationalProgram p : nationalProgramDAO.getItemByModel((page-1)*recordsPerPage,recordsPerPage)){
			////System.out.println(p.getDescription());
			SampleContent content  = new SampleContent();
			content.setId(p.getId());
			content.setTitle(p.getProgramName());
			content.setUrl(url+"selectNationalProgram?id="+p.getId()+"&albumName="+p.getProgramName());
			content.setPhotoURL(p.getPhotourl());
			content.setSummary(p.getDescription());
			content.setCreatedDate(p.getCreatedDate());
			content.setFrequency(p.getFrequency());
			content.setCategory("national");
			items.add(content);			
		}	
		}
		if(configuration.getState()==true){
		for(StateProgram p : stateProgramDAO.getItemByModel((page-1)*recordsPerPage,recordsPerPage)){

			SampleContent content  = new SampleContent();
			content.setId(p.getId());
			content.setTitle(p.getProgramName());
			content.setUrl(url+"selectStateProgram?id="+p.getId()+"&albumName="+p.getProgramName());
			content.setPhotoURL(p.getPhotourl());
			content.setSummary(p.getDescription());
			content.setCreatedDate(p.getCreatedDate());
			content.setFrequency(p.getFrequency());
			content.setCategory("state");
			items.add(content);			
		}
		}
		if(configuration.getEntertainment()==true){
		for(Entertainment p : EntertainmentDAO.getItemByModel((page-1)*recordsPerPage,recordsPerPage)){

			SampleContent content  = new SampleContent();
			content.setId(p.getId());
			content.setTitle(p.getProgramName());
			content.setUrl(url+"selectEntertainmentProgram?id="+p.getId()+"&albumName="+p.getProgramName());
			content.setPhotoURL(p.getPhotourl());
			content.setSummary(p.getDescription());
			content.setCreatedDate(p.getCreatedDate());
			content.setFrequency(p.getFrequency());
			content.setCategory("entertainment");
			items.add(content);			
		}
		}
		if(configuration.getFashion()==true){
		for(Fashion p : fashionDAO.getItemByModel((page-1)*recordsPerPage,recordsPerPage)){

			SampleContent content  = new SampleContent();
			content.setId(p.getId());
			content.setTitle(p.getProgramName());
			content.setUrl(url+"selectFashionProgram?id="+p.getId()+"&albumName="+p.getProgramName());
			content.setPhotoURL(p.getPhotourl());
			content.setSummary(p.getDescription());
			content.setCreatedDate(p.getCreatedDate());
			content.setFrequency(p.getFrequency());
			content.setCategory("fashion");
			items.add(content);			
		}
		}
		if(configuration.getGame()==true){
		for(Game p : gameDAO.getItemByModel((page-1)*recordsPerPage,recordsPerPage)){

			SampleContent content  = new SampleContent();
			content.setId(p.getId());
			content.setTitle(p.getProgramName());
			content.setUrl(url+"selectGameProgram?id="+p.getId()+"&albumName="+p.getProgramName());
			content.setPhotoURL(p.getPhotourl());
			content.setSummary(p.getDescription());
			content.setCreatedDate(p.getCreatedDate());
			content.setFrequency(p.getFrequency());
			content.setCategory("game");
			items.add(content);			
		}
		}
		if(configuration.getBusiness()==true){
		for(Business p : businessDAO.getItemByModel((page-1)*recordsPerPage,recordsPerPage)){

			SampleContent content  = new SampleContent();
			content.setId(p.getId());
			content.setTitle(p.getProgramName());
			content.setUrl(url+"selectBusinessProgram?id="+p.getId()+"&albumName="+p.getProgramName());
			content.setPhotoURL(p.getPhotourl());
			content.setSummary(p.getDescription());
			content.setCreatedDate(p.getCreatedDate());
			content.setFrequency(p.getFrequency());
			content.setCategory("business");
			items.add(content);			
		}
		}
		if(configuration.getSport()==true){
		for(Sport p : sportDAO.getItemByModel((page-1)*recordsPerPage,recordsPerPage)){

			SampleContent content  = new SampleContent();
			content.setId(p.getId());
			content.setTitle(p.getProgramName());
			content.setUrl(url+"selectSportProgram?id="+p.getId()+"&albumName="+p.getProgramName());
			content.setPhotoURL(p.getPhotourl());
			content.setSummary(p.getDescription());
			content.setCreatedDate(p.getCreatedDate());
			content.setFrequency(p.getFrequency());
			content.setCategory("sport");
			items.add(content);			
		}
		}
		if(configuration.getTechnology()==true){
		for(Technology p : technologyDAO.getItemByModel((page-1)*recordsPerPage,recordsPerPage)){

			SampleContent content  = new SampleContent();
			content.setId(p.getId());
			content.setTitle(p.getProgramName());
			content.setUrl(url+"selectTechnologyProgram?id="+p.getId()+"&albumName="+p.getProgramName());
			content.setPhotoURL(p.getPhotourl());
			content.setSummary(p.getDescription());
			content.setCreatedDate(p.getCreatedDate());
			content.setFrequency(p.getFrequency());
			content.setCategory("technology");
			items.add(content);			
		}
		}
		if(configuration.getAstro()==true){
		for(Astro p : astroDAO.getItemByModel((page-1)*recordsPerPage,recordsPerPage)){

			SampleContent content  = new SampleContent();
			content.setId(p.getId());
			content.setTitle(p.getProgramName());
			content.setUrl(url+"selectAstroProgram?id="+p.getId()+"&albumName="+p.getProgramName());
			content.setPhotoURL(p.getPhotourl());
			content.setSummary(p.getDescription());
			content.setCreatedDate(p.getCreatedDate());
			content.setFrequency(p.getFrequency());
			content.setCategory("astro");
			items.add(content);			
		}
		}
		if(configuration.getVideo()==true){
		for(Video p : videoDAO.getItemByModel((page-1)*recordsPerPage,recordsPerPage)){

			SampleContent content  = new SampleContent();
			content.setId(p.getId());
			content.setTitle(p.getVideoName());
			content.setUrl(url+"selectVideo?id="+p.getId()+"&albumName="+p.getVideoName());
			content.setPhotoURL(p.getPhotourl());
			content.setSummary(p.getDescription());
			content.setCreatedDate(p.getCreatedDate());
			content.setFrequency(p.getFrequency());
			content.setCategory("video");
			content.setVideo_id(p.getVideo_id());
			items.add(content);			
		}
		}
		if(configuration.getOther()==true){
		for(OtherProgram p : otherProgramDAO.getItemByModel((page-1)*recordsPerPage,recordsPerPage)){

			SampleContent content  = new SampleContent();
			content.setId(p.getId());
			content.setTitle(p.getProgramName());
			content.setUrl(url+"selectOtherProgram?id="+p.getId()+"&albumName="+p.getProgramName());
			content.setPhotoURL(p.getPhotourl());
			content.setSummary(p.getDescription());
			content.setCreatedDate(p.getCreatedDate());
			content.setFrequency(p.getFrequency());
			content.setCategory("other");
			items.add(content);			
		}		
		}
		//Collections.sort(items, SampleContent.SampleNameComparator);
		model.put("items",items);
		Collections.sort(items, SampleContent.SampleIdComparator);
		model.put("itemsById",items);
		Collections.sort(items, SampleContent.SampleCategoryComparator);
		model.put("itemsByCategory",items);
		Collections.sort(items);
		model.put("flist",items);
		
		Poll poll = pollDAO.getItemByModel((page-1)*1,1).get(0);
		if(poll!=null){
		model.put("poll",poll );
		}
		model.put("vlist", videoDAO.getItemByModel((page-1)*1,1));
		model.put("linklist", linkdao.getItemByModel((page-1)*1,30));
		model.put("sponsorlist", sponsordao.getItemByActiveModel((page-1)*1,10));
		model.put("advertisementlist", advertisementdao.getItemByActiveModel((page-1)*1,10));
		model.put("greetinglist", greetingdao.getItemByActiveModel((page-1)*1,10));
		model.put("configuration",homepageDAO.getDefaultconfig());
		
		return model;
	}
	
	@RequestMapping(value = "/Home", method = RequestMethod.GET)
	public ModelAndView homePage(ModelMap model) {
		model = defaulthomepage(model);
		model.put("heading", dao.list().get(0));
		
		int  page = 1;
		int recordsPerPage = 10;
		
		
		model.put("astrolist", astroDAO.getItemByModel((page-1)*recordsPerPage,recordsPerPage));
		
		model.put("businesslist", businessDAO.getItemByModel((page-1)*recordsPerPage,recordsPerPage));
		model.put("sportslist", sportDAO.getItemByModel((page-1)*recordsPerPage,recordsPerPage));
		model.put("technologylist", technologyDAO.getItemByModel((page-1)*recordsPerPage,recordsPerPage));
		
		
		//model.put("flist", programDAO.getItemByFrequency((page-1)*recordsPerPage,recordsPerPage));
		model.put("list", programDAO.getItemByModel((page-1)*recordsPerPage,recordsPerPage));
		model.put("nationallist", nationalProgramDAO.getItemByModel((page-1)*recordsPerPage,recordsPerPage));
		model.put("statelist", stateProgramDAO.getItemByModel((page-1)*recordsPerPage,recordsPerPage));
		model.put("otherlist", otherProgramDAO.getItemByModel((page-1)*recordsPerPage,recordsPerPage));
		model.put("entertainmentlist", EntertainmentDAO.getItemByModel((page-1)*recordsPerPage,recordsPerPage));
		model.put("fashionlist", fashionDAO.getItemByModel((page-1)*recordsPerPage,recordsPerPage));
		model.put("gamelist", gameDAO.getItemByModel((page-1)*recordsPerPage,recordsPerPage));
		model.put("photographylist", photographyDAO.getItemByModel((page-1)*recordsPerPage,recordsPerPage));
		return new ModelAndView("homePageTile");
	}

	@RequestMapping(value = "/viewVideoGallary", method = RequestMethod.GET)
	public ModelAndView viewVideoGallary(@RequestParam(value = "page", required = false) String strpage,ModelMap model) {
		model = defaulthomepage(model);
		if(homepageDAO.getDefaultconfig().getVideo()!=true){
			return new ModelAndView("404frontTile");
		}
		int  page = 1;
		int recordsPerPage = 5;		
		
		if(strpage==null){
			strpage="1";
		}
		if(!strpage.equals("0"))
		{
		 page = Integer.parseInt(strpage);	
		}

		model.put("list", videoDAO.getItemByModel((page-1)*recordsPerPage,recordsPerPage));
		//model.put("flist", programDAO.getItemByFrequency((page-1)*recordsPerPage,recordsPerPage));
		int noOfRecords = videoDAO.getNoOfRecords();
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		model.put("noOfRecords", noOfRecords);
		model.put("noOfPages", noOfPages);
		model.put("currentPage", page);
		return new ModelAndView("videoTile");
	}

	@RequestMapping(value = "/selectVideo", method = RequestMethod.GET)
	public ModelAndView selectVideo(@RequestParam(value = "id", required = true) Long id,@RequestParam(value = "albumName", required = false) String albumName,ModelMap model,HttpServletRequest request) {
		if(homepageDAO.getDefaultconfig().getVideo()!=true){
			return new ModelAndView("404frontTile");
		}
		model = defaulthomepage(model);
		if(videoDAO.updateFrequency(id)){
		Video p = videoDAO.findByDeptId(id);
		model.put("program", p);
		
		/*
 		ArrayList<String> fileList = new ArrayList<String>();
		
		String dirPath = p.getFolder_url();//request.getRealPath("/")+"themes\\"+albumName+"\\";
		File folder = new File(dirPath);
		if(folder.exists()){
		for (final File fileEntry : folder.listFiles()) {
			fileList.add(fileEntry.getName());
	    }
		}
		model.put("fileList", fileList);
		model.put("fileDir", p.getFolder_relative_url());
 		*/
		}
		return new ModelAndView("videoDetailTile");
	}
	
	@RequestMapping(value = "/viewPrograms", method = RequestMethod.GET)
	public ModelAndView viewPrograms(@RequestParam(value = "page", required = false) String strpage,ModelMap model) {
		model = defaulthomepage(model);
		if(homepageDAO.getDefaultconfig().getInternational()!=true){
			return new ModelAndView("404frontTile");
		}
		int  page = 1;
		int recordsPerPage = 5;		
		
		if(strpage==null){
			strpage="1";
		}
		if(!strpage.equals("0"))
		{
		 page = Integer.parseInt(strpage);	
		}
		
		model.put("list", programDAO.getItemByModel((page-1)*recordsPerPage,recordsPerPage));
		//model.put("flist", programDAO.getItemByFrequency((page-1)*recordsPerPage,recordsPerPage));
		int noOfRecords = programDAO.getNoOfRecords();
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		model.put("noOfRecords", noOfRecords);
		model.put("noOfPages", noOfPages);
		model.put("currentPage", page);
		return new ModelAndView("programsTile");
	}
	
	@RequestMapping(value = "/selectProgram", method = RequestMethod.GET)
	public ModelAndView selectPrograms(@RequestParam(value = "id", required = true) Long id,@RequestParam(value = "albumName", required = false) String albumName,ModelMap model,HttpServletRequest request) {
		model = defaulthomepage(model);
		if(homepageDAO.getDefaultconfig().getInternational()!=true){
			return new ModelAndView("404frontTile");
		}
		try{
		if(programDAO.updateFrequency(id)){
		Program p = programDAO.findByDeptId(id);
		model.put("program", p);
		model.put("albumName", albumName);
		
		ArrayList<String> fileList = new ArrayList<String>();
		
		String dirPath = p.getFolder_url();//request.getRealPath("/")+"themes\\"+albumName+"\\";
		File folder = new File(dirPath);
		if(folder.exists()){
		for (final File fileEntry : folder.listFiles()) {
			if(fileEntry.getName().equals("0_icon.jpeg") || fileEntry.getName().equals("0_wmark.jpeg")){
				
			}else{
				fileList.add(fileEntry.getName());
			}
	    }
		}
		model.put("fileList", fileList);
		model.put("fileDir", p.getFolder_relative_url());
		}
		}
		catch(Exception ex){
			ex.printStackTrace();
			return new ModelAndView("404frontTile");
		}
		return new ModelAndView("programsDetailTile");
	}
	
	@RequestMapping(value = "/viewNationalPrograms", method = RequestMethod.GET)
	public ModelAndView viewNationalPrograms(@RequestParam(value = "page", required = false) String strpage,ModelMap model) {
		model = defaulthomepage(model);
		if(homepageDAO.getDefaultconfig().getNational()!=true){
			return new ModelAndView("404frontTile");
		}
		int  page = 1;
		int recordsPerPage = 5;		
		
		if(strpage==null){
			strpage="1";
		}
		if(!strpage.equals("0"))
		{
		 page = Integer.parseInt(strpage);	
		}
		
		model.put("list", nationalProgramDAO.getItemByModel((page-1)*recordsPerPage,recordsPerPage));
		int noOfRecords = nationalProgramDAO.getNoOfRecords();
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		model.put("noOfRecords", noOfRecords);
		model.put("noOfPages", noOfPages);
		model.put("currentPage", page);
		return new ModelAndView("nationalprogramsTile");
	}
	
	@RequestMapping(value = "/selectNationalProgram", method = RequestMethod.GET)
	public ModelAndView selectNatinoalProgram(@RequestParam(value = "id", required = true) Long id,@RequestParam(value = "albumName", required = false) String albumName,ModelMap model,HttpServletRequest request) {
		model = defaulthomepage(model);
		if(homepageDAO.getDefaultconfig().getNational()!=true){
			return new ModelAndView("404frontTile");
		}
		try
		{
		if(nationalProgramDAO.updateFrequency(id)){
			NationalProgram p = nationalProgramDAO.findByDeptId(id);
			model.put("program", p);
			model.put("albumName", albumName);
		
			ArrayList<String> fileList = new ArrayList<String>();
		
			String dirPath = p.getFolder_url();//request.getRealPath("/")+"themes\\national\\"+albumName+"\\";
			//System.out.println(dirPath);
			File folder = new File(dirPath);
			if(folder.exists()){
				for (final File fileEntry : folder.listFiles()) {
					if(fileEntry.getName().equals("0_icon.jpeg") || fileEntry.getName().equals("0_wmark.jpeg")){
						
					}else{
						fileList.add(fileEntry.getName());
					}
				}
			}
			model.put("fileList", fileList);
			model.put("fileDir", p.getFolder_relative_url());
		}
		}
		catch(Exception ex){
			ex.printStackTrace();
			return new ModelAndView("404frontTile");
		}
		return new ModelAndView("nationalprogramsDetailTile");
	}
	
	@RequestMapping(value = "/viewStatePrograms", method = RequestMethod.GET)
	public ModelAndView viewStatePrograms(@RequestParam(value = "page", required = false) String strpage,ModelMap model) {
		model = defaulthomepage(model);
		if(homepageDAO.getDefaultconfig().getState()!=true){
			return new ModelAndView("404frontTile");
		}
		int  page = 1;
		int recordsPerPage = 5;		
		
		if(strpage==null){
			strpage="1";
		}
		if(!strpage.equals("0"))
		{
		 page = Integer.parseInt(strpage);	
		}
		
		model.put("list", stateProgramDAO.getItemByModel((page-1)*recordsPerPage,recordsPerPage));
		int noOfRecords = stateProgramDAO.getNoOfRecords();
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		model.put("noOfRecords", noOfRecords);
		model.put("noOfPages", noOfPages);
		model.put("currentPage", page);
		return new ModelAndView("stateprogramsTile");
	}
	
	@RequestMapping(value = "/selectStateProgram", method = RequestMethod.GET)
	public ModelAndView selectStateProgram(@RequestParam(value = "id", required = true) Long id,@RequestParam(value = "albumName", required = false) String albumName,ModelMap model,HttpServletRequest request) {
		if(homepageDAO.getDefaultconfig().getState()!=true){
			return new ModelAndView("404frontTile");
		}
		try{
		model = defaulthomepage(model);
		if(stateProgramDAO.updateFrequency(id)){
			StateProgram p = stateProgramDAO.findByDeptId(id);
			model.put("program", p);
			model.put("albumName", albumName);
		
			ArrayList<String> fileList = new ArrayList<String>();
		
			String dirPath = p.getFolder_url();//request.getRealPath("/")+"themes\\state\\"+albumName+"\\";
			File folder = new File(dirPath);
			if(folder.exists()){
				for (final File fileEntry : folder.listFiles()) {
					if(fileEntry.getName().equals("0_icon.jpeg") || fileEntry.getName().equals("0_wmark.jpeg")){
						
					}else{
						fileList.add(fileEntry.getName());
					}
				}
			}
			model.put("fileList", fileList);
			model.put("fileDir", p.getFolder_relative_url());
		}
		}
		catch(Exception ex){
			ex.printStackTrace();
			return new ModelAndView("404frontTile");
		}
		return new ModelAndView("stateprogramsDetailTile");
	}
	
	@RequestMapping(value = "/viewOtherPrograms", method = RequestMethod.GET)
	public ModelAndView viewOtherPrograms(@RequestParam(value = "page", required = false) String strpage,ModelMap model) {
		model = defaulthomepage(model);
		if(homepageDAO.getDefaultconfig().getOther()!=true){
			return new ModelAndView("404frontTile");
		}
		int  page = 1;
		int recordsPerPage = 5;		
		
		if(strpage==null){
			strpage="1";
		}
		if(!strpage.equals("0"))
		{
		 page = Integer.parseInt(strpage);	
		}
		
		model.put("list", otherProgramDAO.getItemByModel((page-1)*recordsPerPage,recordsPerPage));
		int noOfRecords = otherProgramDAO.getNoOfRecords();
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		model.put("noOfRecords", noOfRecords);
		model.put("noOfPages", noOfPages);
		model.put("currentPage", page);
		return new ModelAndView("otherprogramsTile");
	}
	
	@RequestMapping(value = "/selectOtherProgram", method = RequestMethod.GET)
	public ModelAndView selectOtherProgram(@RequestParam(value = "id", required = true) Long id,@RequestParam(value = "albumName", required = false) String albumName,ModelMap model,HttpServletRequest request) {
		model = defaulthomepage(model);
		if(homepageDAO.getDefaultconfig().getOther()!=true){
			return new ModelAndView("404frontTile");
		}
		try{
		if(otherProgramDAO.updateFrequency(id)){
			OtherProgram p = otherProgramDAO.findByDeptId(id);
			model.put("program", p);
			model.put("albumName", albumName);
			
			ArrayList<String> fileList = new ArrayList<String>();
			
			String dirPath = p.getFolder_url();//request.getRealPath("/")+"themes\\other\\"+albumName+"\\";
			File folder = new File(dirPath);
			if(folder.exists()){
			for (final File fileEntry : folder.listFiles()) {
				if(fileEntry.getName().equals("0_icon.jpeg") || fileEntry.getName().equals("0_wmark.jpeg")){
					
				}else{
					fileList.add(fileEntry.getName());
				}
		    }
			}
			model.put("fileList", fileList);
			model.put("fileDir", p.getFolder_relative_url());
		}
		}
		catch(Exception ex){
			ex.printStackTrace();
			return new ModelAndView("404frontTile");
		}
		return new ModelAndView("otherprogramsDetailTile");
	}
	
	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public ModelAndView about(ModelMap model) {
		model = defaulthomepage(model);
		
		return new ModelAndView("aboutTile");
	}
	
	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public ModelAndView contact(ModelMap model) {
		if(homepageDAO.getDefaultconfig().getContact()!=true){
			return new ModelAndView("404frontTile");
		}
		model = defaulthomepage(model);
		model.put("contact",new Contact());
		return new ModelAndView("contactTile");
	}
	
	@RequestMapping(value = "/addContactForm", method = RequestMethod.POST)
	public ModelAndView addContactForm(ModelMap model,@Valid @Validated @ModelAttribute("contact") Contact contact,BindingResult bindingResult ) {
		model = defaulthomepage(model);
		if(homepageDAO.getDefaultconfig().getContact()!=true){
			return new ModelAndView("404frontTile");
		}
		if(bindingResult.hasErrors())
		{
			model.put("contact", contact);
			return new ModelAndView("contactTile");
		}
		contactDAO.save(contact);
		model.put("msg", "Thank You for your information. Admin may contact you in 24 working hours.");
		return new ModelAndView("contactTile");
	}
	
	@RequestMapping(value = "/slideshow", method = RequestMethod.GET)
	public ModelAndView slideshow(@RequestParam(value = "albumName", required = false) String albumName,ModelMap model,HttpServletRequest request) {
		if(homepageDAO.getDefaultconfig().getPhotography()!=true){
			return new ModelAndView("404frontTile");
		}
		model = defaulthomepage(model);
		model.put("albumName", albumName);
		
		ArrayList<String> fileList = new ArrayList<String>();
		
		String dirPath = request.getRealPath("/")+"themes\\"+albumName+"\\";
		File folder = new File(dirPath);
		if(folder.exists()){
		for (final File fileEntry : folder.listFiles()) {
			fileList.add(fileEntry.getName());
	    }
		}
		model.put("fileList", fileList);
		return new ModelAndView("slideshowTile");
	}
	
	
	@RequestMapping(value = "/viewBusinessPrograms", method = RequestMethod.GET)
	public ModelAndView viewBusinessPrograms(@RequestParam(value = "page", required = false) String strpage,ModelMap model) {
		model = defaulthomepage(model);
		if(homepageDAO.getDefaultconfig().getBusiness()!=true){
			return new ModelAndView("404frontTile");
		}
		int  page = 1;
		int recordsPerPage = 5;		
		
		if(strpage==null){
			strpage="1";
		}
		if(!strpage.equals("0"))
		{
		 page = Integer.parseInt(strpage);	
		}
		
		model.put("list", businessDAO.getItemByModel((page-1)*recordsPerPage,recordsPerPage));
		int noOfRecords = businessDAO.getNoOfRecords();
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		model.put("noOfRecords", noOfRecords);
		model.put("noOfPages", noOfPages);
		model.put("currentPage", page);
		return new ModelAndView("businessprogramsTile");
	}
	
	@RequestMapping(value = "/selectBusinessProgram", method = RequestMethod.GET)
	public ModelAndView selectBusinessProgram(@RequestParam(value = "id", required = true) Long id,@RequestParam(value = "albumName", required = false) String albumName,ModelMap model,HttpServletRequest request) {
		model = defaulthomepage(model);
		if(homepageDAO.getDefaultconfig().getBusiness()!=true){
			return new ModelAndView("404frontTile");
		}
		try{
		if(businessDAO.updateFrequency(id)){
			Business p = businessDAO.findByDeptId(id);
			model.put("program", p);
			model.put("albumName", albumName);
		
			ArrayList<String> fileList = new ArrayList<String>();
		
			String dirPath = p.getFolder_url();//request.getRealPath("/")+"themes\\national\\"+albumName+"\\";
			//System.out.println(dirPath);
			File folder = new File(dirPath);
			if(folder.exists()){
				for (final File fileEntry : folder.listFiles()) {
					if(fileEntry.getName().equals("0_icon.jpeg") || fileEntry.getName().equals("0_wmark.jpeg")){
						
					}else{
						fileList.add(fileEntry.getName());
					}
				}
			}
			model.put("fileList", fileList);
			model.put("fileDir", p.getFolder_relative_url());
		}
		}
		catch(Exception ex){
			ex.printStackTrace();
			return new ModelAndView("404frontTile");
		}
		return new ModelAndView("businessprogramsDetailTile");
	}
	
	@RequestMapping(value = "/viewSportPrograms", method = RequestMethod.GET)
	public ModelAndView viewSportsPrograms(@RequestParam(value = "page", required = false) String strpage,ModelMap model) {
		model = defaulthomepage(model);
		if(homepageDAO.getDefaultconfig().getSport()!=true){
			return new ModelAndView("404frontTile");
		}
		int  page = 1;
		int recordsPerPage = 5;		
		
		if(strpage==null){
			strpage="1";
		}
		if(!strpage.equals("0"))
		{
		 page = Integer.parseInt(strpage);	
		}
		
		model.put("list", sportDAO.getItemByModel((page-1)*recordsPerPage,recordsPerPage));
		int noOfRecords = sportDAO.getNoOfRecords();
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		model.put("noOfRecords", noOfRecords);
		model.put("noOfPages", noOfPages);
		model.put("currentPage", page);
		return new ModelAndView("sportprogramsTile");
	}
	
	@RequestMapping(value = "/selectSportProgram", method = RequestMethod.GET)
	public ModelAndView selectSportProgram(@RequestParam(value = "id", required = true) Long id,@RequestParam(value = "albumName", required = false) String albumName,ModelMap model,HttpServletRequest request) {
		model = defaulthomepage(model);
		if(homepageDAO.getDefaultconfig().getSport()!=true){
			return new ModelAndView("404frontTile");
		}
		try{
		if(sportDAO.updateFrequency(id)){
			Sport p = sportDAO.findByDeptId(id);
			model.put("program", p);
			model.put("albumName", albumName);
		
			ArrayList<String> fileList = new ArrayList<String>();
		
			String dirPath = p.getFolder_url();//request.getRealPath("/")+"themes\\national\\"+albumName+"\\";
			//System.out.println(dirPath);
			File folder = new File(dirPath);
			if(folder.exists()){
				for (final File fileEntry : folder.listFiles()) {
					if(fileEntry.getName().equals("0_icon.jpeg") || fileEntry.getName().equals("0_wmark.jpeg")){
						
					}else{
						fileList.add(fileEntry.getName());
					}
				}
			}
			model.put("fileList", fileList);
			model.put("fileDir", p.getFolder_relative_url());
		}
		}
		catch(Exception ex){
			ex.printStackTrace();
			return new ModelAndView("404frontTile");
		}
		return new ModelAndView("sportsprogramsDetailTile");
	}

	@RequestMapping(value = "/viewTechnologyPrograms", method = RequestMethod.GET)
	public ModelAndView viewTechnologyPrograms(@RequestParam(value = "page", required = false) String strpage,ModelMap model) {
		model = defaulthomepage(model);
		if(homepageDAO.getDefaultconfig().getTechnology()!=true){
			return new ModelAndView("404frontTile");
		}
		int  page = 1;
		int recordsPerPage = 5;		
		
		if(strpage==null){
			strpage="1";
		}
		if(!strpage.equals("0"))
		{
		 page = Integer.parseInt(strpage);	
		}
		
		model.put("list", technologyDAO.getItemByModel((page-1)*recordsPerPage,recordsPerPage));
		int noOfRecords = technologyDAO.getNoOfRecords();
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		model.put("noOfRecords", noOfRecords);
		model.put("noOfPages", noOfPages);
		model.put("currentPage", page);
		return new ModelAndView("technologyprogramsTile");
	}
	
	@RequestMapping(value = "/selectTechnologyProgram", method = RequestMethod.GET)
	public ModelAndView selectTechnologyProgram(@RequestParam(value = "id", required = true) Long id,@RequestParam(value = "albumName", required = false) String albumName,ModelMap model,HttpServletRequest request) {
		model = defaulthomepage(model);
		if(homepageDAO.getDefaultconfig().getTechnology()!=true){
			return new ModelAndView("404frontTile");
		}
		try{
		if(technologyDAO.updateFrequency(id)){
			Technology p = technologyDAO.findByDeptId(id);
			model.put("program", p);
			model.put("albumName", albumName);
		
			ArrayList<String> fileList = new ArrayList<String>();
		
			String dirPath = p.getFolder_url();//request.getRealPath("/")+"themes\\national\\"+albumName+"\\";
			//System.out.println(dirPath);
			File folder = new File(dirPath);
			if(folder.exists()){
				for (final File fileEntry : folder.listFiles()) {
					if(fileEntry.getName().equals("0_icon.jpeg") || fileEntry.getName().equals("0_wmark.jpeg")){
						
					}else{
						fileList.add(fileEntry.getName());
					}
				}
			}
			model.put("fileList", fileList);
			model.put("fileDir", p.getFolder_relative_url());
		}
		}
		catch(Exception ex){
			ex.printStackTrace();
			return new ModelAndView("404frontTile");
		}
		return new ModelAndView("technologyprogramsDetailTile");
	}
	
	@RequestMapping(value = "/viewAstroPrograms", method = RequestMethod.GET)
	public ModelAndView viewAstroPrograms(@RequestParam(value = "page", required = false) String strpage,ModelMap model) {
		model = defaulthomepage(model);
		if(homepageDAO.getDefaultconfig().getAstro()!=true){
			return new ModelAndView("404frontTile");
		}
		int  page = 1;
		int recordsPerPage = 5;		
		
		if(strpage==null){
			strpage="1";
		}
		if(!strpage.equals("0"))
		{
		 page = Integer.parseInt(strpage);	
		}
		
		model.put("list", astroDAO.getItemByModel((page-1)*recordsPerPage,recordsPerPage));
		int noOfRecords = astroDAO.getNoOfRecords();
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		model.put("noOfRecords", noOfRecords);
		model.put("noOfPages", noOfPages);
		model.put("currentPage", page);
		return new ModelAndView("astroprogramsTile");
	}
	
	@RequestMapping(value = "/selectAstroProgram", method = RequestMethod.GET)
	public ModelAndView selectAstroProgram(@RequestParam(value = "id", required = true) Long id,@RequestParam(value = "albumName", required = false) String albumName,ModelMap model,HttpServletRequest request) {
		model = defaulthomepage(model);
		if(homepageDAO.getDefaultconfig().getAstro()!=true){
			return new ModelAndView("404frontTile");
		}
		try{
		if(astroDAO.updateFrequency(id)){
			Astro p = astroDAO.findByDeptId(id);
			model.put("program", p);
			model.put("albumName", albumName);
		
			ArrayList<String> fileList = new ArrayList<String>();
		
			String dirPath = p.getFolder_url();//request.getRealPath("/")+"themes\\national\\"+albumName+"\\";
			//System.out.println(dirPath);
			File folder = new File(dirPath);
			if(folder.exists()){
				for (final File fileEntry : folder.listFiles()) {
					if(fileEntry.getName().equals("0_icon.jpeg") || fileEntry.getName().equals("0_wmark.jpeg")){
						
					}else{
						fileList.add(fileEntry.getName());
					}
				}
			}
			model.put("fileList", fileList);
			model.put("fileDir", p.getFolder_relative_url());
		}
		}
		catch(Exception ex){
			ex.printStackTrace();
			return new ModelAndView("404frontTile");
		}
		return new ModelAndView("astroprogramsDetailTile");
	}
	
	@RequestMapping(value = "/viewEntertainmentPrograms", method = RequestMethod.GET)
	public ModelAndView viewEntertainmentPrograms(@RequestParam(value = "page", required = false) String strpage,ModelMap model) {
		model = defaulthomepage(model);
		if(homepageDAO.getDefaultconfig().getEntertainment()!=true){
			return new ModelAndView("404frontTile");
		}
		int  page = 1;
		int recordsPerPage = 5;		
		
		if(strpage==null){
			strpage="1";
		}
		if(!strpage.equals("0"))
		{
		 page = Integer.parseInt(strpage);	
		}
		
		model.put("list", EntertainmentDAO.getItemByModel((page-1)*recordsPerPage,recordsPerPage));
		int noOfRecords = EntertainmentDAO.getNoOfRecords();
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		model.put("noOfRecords", noOfRecords);
		model.put("noOfPages", noOfPages);
		model.put("currentPage", page);
		return new ModelAndView("entertainmentprogramsTile");
	}
	
	@RequestMapping(value = "/selectEntertainmentProgram", method = RequestMethod.GET)
	public ModelAndView selectEntertainmentProgram(@RequestParam(value = "id", required = true) Long id,@RequestParam(value = "albumName", required = false) String albumName,ModelMap model,HttpServletRequest request) {
		model = defaulthomepage(model);
		if(homepageDAO.getDefaultconfig().getEntertainment()!=true){
			return new ModelAndView("404frontTile");
		}
		try{
		if(EntertainmentDAO.updateFrequency(id)){
			Entertainment p = EntertainmentDAO.findByDeptId(id);
			model.put("program", p);
			model.put("albumName", albumName);
		
			ArrayList<String> fileList = new ArrayList<String>();
		
			String dirPath = p.getFolder_url();//request.getRealPath("/")+"themes\\national\\"+albumName+"\\";
			//System.out.println(dirPath);
			File folder = new File(dirPath);
			if(folder.exists()){
				for (final File fileEntry : folder.listFiles()) {
					if(fileEntry.getName().equals("0_icon.jpeg") || fileEntry.getName().equals("0_wmark.jpeg")){
						
					}else{
						fileList.add(fileEntry.getName());
					}
				}
			}
			model.put("fileList", fileList);
			model.put("fileDir", p.getFolder_relative_url());
		}
		}
		catch(Exception ex){
			ex.printStackTrace();
			return new ModelAndView("404frontTile");
		}
		return new ModelAndView("entertainmentprogramsDetailTile");
	}
	
	@RequestMapping(value = "/viewFashionPrograms", method = RequestMethod.GET)
	public ModelAndView viewFashionPrograms(@RequestParam(value = "page", required = false) String strpage,ModelMap model) {
		model = defaulthomepage(model);
		if(homepageDAO.getDefaultconfig().getFashion()!=true){
			return new ModelAndView("404frontTile");
		}
		int  page = 1;
		int recordsPerPage = 5;		
		
		if(strpage==null){
			strpage="1";
		}
		if(!strpage.equals("0"))
		{
		 page = Integer.parseInt(strpage);	
		}
		
		model.put("list", fashionDAO.getItemByModel((page-1)*recordsPerPage,recordsPerPage));
		int noOfRecords = fashionDAO.getNoOfRecords();
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		model.put("noOfRecords", noOfRecords);
		model.put("noOfPages", noOfPages);
		model.put("currentPage", page);
		return new ModelAndView("fashionprogramsTile");
	}
	@RequestMapping(value = "/selectFashionProgram", method = RequestMethod.GET)
	public ModelAndView selectFashionProgram(@RequestParam(value = "id", required = true) Long id,@RequestParam(value = "albumName", required = false) String albumName,ModelMap model,HttpServletRequest request) {
		model = defaulthomepage(model);
		try{
		if(homepageDAO.getDefaultconfig().getFashion()!=true){
			return new ModelAndView("404frontTile");
		}
		if(fashionDAO.updateFrequency(id)){
			Fashion p = fashionDAO.findByDeptId(id);
			model.put("program", p);
			model.put("albumName", albumName);
		
			ArrayList<String> fileList = new ArrayList<String>();
		
			String dirPath = p.getFolder_url();//request.getRealPath("/")+"themes\\national\\"+albumName+"\\";
			//System.out.println(dirPath);
			File folder = new File(dirPath);
			if(folder.exists()){
				for (final File fileEntry : folder.listFiles()) {
					if(fileEntry.getName().equals("0_icon.jpeg") || fileEntry.getName().equals("0_wmark.jpeg")){
						
					}else{
						fileList.add(fileEntry.getName());
					}
				}
			}
			model.put("fileList", fileList);
			model.put("fileDir", p.getFolder_relative_url());
		}
		}
		catch(Exception ex){
			ex.printStackTrace();
			return new ModelAndView("404frontTile");
		}
		return new ModelAndView("fashionprogramsDetailTile");
	}
	
	
	
	
	@RequestMapping(value = "/viewGamePrograms", method = RequestMethod.GET)
	public ModelAndView viewGamePrograms(@RequestParam(value = "page", required = false) String strpage,ModelMap model) {
		model = defaulthomepage(model);
		if(homepageDAO.getDefaultconfig().getGame()!=true){
			return new ModelAndView("404frontTile");
		}
		int  page = 1;
		int recordsPerPage = 5;		
		
		if(strpage==null){
			strpage="1";
		}
		if(!strpage.equals("0"))
		{
		 page = Integer.parseInt(strpage);	
		}
		
		model.put("list", gameDAO.getItemByModel((page-1)*recordsPerPage,recordsPerPage));
		int noOfRecords = gameDAO.getNoOfRecords();
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		model.put("noOfRecords", noOfRecords);
		model.put("noOfPages", noOfPages);
		model.put("currentPage", page);
		return new ModelAndView("gameprogramsTile");
	}
	@RequestMapping(value = "/selectGameProgram", method = RequestMethod.GET)
	public ModelAndView selectGameProgram(@RequestParam(value = "id", required = true) Long id,@RequestParam(value = "albumName", required = false) String albumName,ModelMap model,HttpServletRequest request) {
		model = defaulthomepage(model);
		if(homepageDAO.getDefaultconfig().getGame()!=true){
			return new ModelAndView("404frontTile");
		}
		try{
		if(gameDAO.updateFrequency(id)){
			Game p = gameDAO.findByDeptId(id);
			model.put("program", p);
			model.put("albumName", albumName);
		
			ArrayList<String> fileList = new ArrayList<String>();
		
			String dirPath = p.getFolder_url();//request.getRealPath("/")+"themes\\national\\"+albumName+"\\";
			//System.out.println(dirPath);
			File folder = new File(dirPath);
			if(folder.exists()){
				for (final File fileEntry : folder.listFiles()) {
					if(fileEntry.getName().equals("0_icon.jpeg") || fileEntry.getName().equals("0_wmark.jpeg")){
						
					}else{
						fileList.add(fileEntry.getName());
					}
				}
			}
			model.put("fileList", fileList);
			model.put("fileDir", p.getFolder_relative_url());
		}}
		catch(Exception ex){
			ex.printStackTrace();
			return new ModelAndView("404frontTile");
		}
		return new ModelAndView("gameprogramsDetailTile");
	}
	
	
	@RequestMapping(value = "/pollsubmit", method = RequestMethod.GET)
	public ModelAndView pollsubmit(@RequestParam(value = "id", required = false) Long id,@RequestParam(value = "vote", required = false) String vote ,ModelMap model) {
		model = defaulthomepage(model);
		if(homepageDAO.getDefaultconfig().getPoll()!=true){
			return new ModelAndView("404frontTile");
		}
		try{
		if(pollDAO.updateFrequency(id)){
			Poll p = pollDAO.findByDeptId(id);
			Boolean valid=true;
			if(vote.equalsIgnoreCase("yes")){
				p.setYes(p.getYes()+1);
			}else if (vote.equalsIgnoreCase("no")){
				p.setNo(p.getNo()+1);
			}
			else if (vote.equalsIgnoreCase("none")){
				p.setNone(p.getNone()+1);
			}else{
				valid=false;
			}
			if(valid){
				pollDAO.update(p);
				model.put("poll", p);
			}
		}
		}
		catch(Exception ex){
			ex.printStackTrace();
			return new ModelAndView("404frontTile");
		}
		return new ModelAndView("pollDetailTile");
	}
	
	@RequestMapping(value = "/viewProgramsByCity", method = RequestMethod.GET)
	public ModelAndView homePage(ModelMap model,@RequestParam(value = "city", required = false) String city) {
		model = defaulthomepage(model);
		//model.put("heading", dao.list().get(0));
		if(homepageDAO.getDefaultconfig().getCity()!=true){
			return new ModelAndView("404frontTile");
		}
		int  page = 1;
		int recordsPerPage = 10;
		
		/*Poll p = pollDAO.getItemByModel((page-1)*1,1).get(0);
		if(p!=null){
		model.put("poll",p );
		}*/
		model.put("astrolist", astroDAO.getItemByCity((page-1)*recordsPerPage,recordsPerPage,city));
		
		model.put("businesslist", businessDAO.getItemByCity((page-1)*recordsPerPage,recordsPerPage,city));
		model.put("sportslist", sportDAO.getItemByCity((page-1)*recordsPerPage,recordsPerPage,city));
		model.put("technologylist", technologyDAO.getItemByCity((page-1)*recordsPerPage,recordsPerPage,city));
		
		//model.put("vlist", videoDAO.getItemByCity((page-1)*1,1,city));
		//model.put("flist", programDAO.getItemByCityFrequency((page-1)*recordsPerPage,recordsPerPage,city));
		model.put("list", programDAO.getItemByCity((page-1)*recordsPerPage,recordsPerPage,city));
		model.put("nationallist", nationalProgramDAO.getItemByCity((page-1)*recordsPerPage,recordsPerPage,city));
		model.put("statelist", stateProgramDAO.getItemByCity((page-1)*recordsPerPage,recordsPerPage,city));
		model.put("otherlist", otherProgramDAO.getItemByCity((page-1)*recordsPerPage,recordsPerPage,city));
		model.put("entertainmentlist", EntertainmentDAO.getItemByCity((page-1)*recordsPerPage,recordsPerPage,city));
		model.put("fashionlist", fashionDAO.getItemByCity((page-1)*recordsPerPage,recordsPerPage,city));
		if(dao.list().size()>0){
			model.put("heading", dao.list().get(0));
		}
		return new ModelAndView("cityPageTile");
	}
	private String url = "http://localhost:8089/JayHindExpress/";
	
	@RequestMapping(value="/rssfeed", method = RequestMethod.GET)
	public ModelAndView getFeedInRss() {

		List<SampleContent> items = new ArrayList<SampleContent>();
		
		int  page = 1;
		int recordsPerPage = 10;
		
		for(Program p : programDAO.getItemByModel((page-1)*recordsPerPage,recordsPerPage)){
			////System.out.println(p.getDescription());
			//p.setDescription("समारोह के");
			SampleContent content  = new SampleContent();
			content.setTitle(p.getProgramName());
			content.setUrl(url+"selectProgram?id="+p.getId()+"&albumName="+p.getProgramName());
			content.setPhotoURL(p.getPhotourl());
			content.setSummary(p.getDescription());
			content.setCreatedDate(p.getCreatedDate());
			items.add(content);			
		}
		
		for(NationalProgram p : nationalProgramDAO.getItemByModel((page-1)*recordsPerPage,recordsPerPage)){
			////System.out.println(p.getDescription());
			SampleContent content  = new SampleContent();
			content.setTitle(p.getProgramName());
			content.setUrl(url+"selectNationalProgram?id="+p.getId()+"&albumName="+p.getProgramName());
			content.setPhotoURL(p.getPhotourl());
			content.setSummary(p.getDescription());
			content.setCreatedDate(p.getCreatedDate());
			items.add(content);			
		}	

		for(StateProgram p : stateProgramDAO.getItemByModel((page-1)*recordsPerPage,recordsPerPage)){

			SampleContent content  = new SampleContent();
			content.setTitle(p.getProgramName());
			content.setUrl(url+"selectStateProgram?id="+p.getId()+"&albumName="+p.getProgramName());
			content.setPhotoURL(p.getPhotourl());
			content.setSummary(p.getDescription());
			content.setCreatedDate(p.getCreatedDate());
			items.add(content);			
		}

		for(Entertainment p : EntertainmentDAO.getItemByModel((page-1)*recordsPerPage,recordsPerPage)){

			SampleContent content  = new SampleContent();
			content.setTitle(p.getProgramName());
			content.setUrl(url+"selectEntertainmentProgram?id="+p.getId()+"&albumName="+p.getProgramName());
			content.setPhotoURL(p.getPhotourl());
			content.setSummary(p.getDescription());
			content.setCreatedDate(p.getCreatedDate());
			items.add(content);			
		}

		for(Fashion p : fashionDAO.getItemByModel((page-1)*recordsPerPage,recordsPerPage)){

			SampleContent content  = new SampleContent();
			content.setTitle(p.getProgramName());
			content.setUrl(url+"selectFashionProgram?id="+p.getId()+"&albumName="+p.getProgramName());
			content.setPhotoURL(p.getPhotourl());
			content.setSummary(p.getDescription());
			content.setCreatedDate(p.getCreatedDate());
			items.add(content);			
		}

		
		for(Business p : businessDAO.getItemByModel((page-1)*recordsPerPage,recordsPerPage)){

			SampleContent content  = new SampleContent();
			content.setTitle(p.getProgramName());
			content.setUrl(url+"selectBusinessProgram?id="+p.getId()+"&albumName="+p.getProgramName());
			content.setPhotoURL(p.getPhotourl());
			content.setSummary(p.getDescription());
			content.setCreatedDate(p.getCreatedDate());
			items.add(content);			
		}

		for(Sport p : sportDAO.getItemByModel((page-1)*recordsPerPage,recordsPerPage)){

			SampleContent content  = new SampleContent();
			content.setTitle(p.getProgramName());
			content.setUrl(url+"selectSportProgram?id="+p.getId()+"&albumName="+p.getProgramName());
			content.setPhotoURL(p.getPhotourl());
			content.setSummary(p.getDescription());
			content.setCreatedDate(p.getCreatedDate());
			items.add(content);			
		}

		for(Technology p : technologyDAO.getItemByModel((page-1)*recordsPerPage,recordsPerPage)){

			SampleContent content  = new SampleContent();
			content.setTitle(p.getProgramName());
			content.setUrl(url+"selectTechnologyProgram?id="+p.getId()+"&albumName="+p.getProgramName());
			content.setPhotoURL(p.getPhotourl());
			content.setSummary(p.getDescription());
			content.setCreatedDate(p.getCreatedDate());
			items.add(content);			
		}
		
		for(Astro p : astroDAO.getItemByModel((page-1)*recordsPerPage,recordsPerPage)){

			SampleContent content  = new SampleContent();
			content.setTitle(p.getProgramName());
			content.setUrl(url+"selectAstroProgram?id="+p.getId()+"&albumName="+p.getProgramName());
			content.setPhotoURL(p.getPhotourl());
			content.setSummary(p.getDescription());
			content.setCreatedDate(p.getCreatedDate());
			items.add(content);			
		}
		
		for(Video p : videoDAO.getItemByModel((page-1)*recordsPerPage,recordsPerPage)){

			SampleContent content  = new SampleContent();
			content.setTitle(p.getVideoName());
			content.setUrl(url+"selectVideo?id="+p.getId()+"&albumName="+p.getVideoName());
			content.setPhotoURL(p.getPhotourl());
			content.setSummary(p.getDescription());
			content.setCreatedDate(p.getCreatedDate());
			items.add(content);			
		}
		
		for(OtherProgram p : otherProgramDAO.getItemByModel((page-1)*recordsPerPage,recordsPerPage)){

			SampleContent content  = new SampleContent();
			content.setTitle(p.getProgramName());
			content.setUrl(url+"selectOtherProgram?id="+p.getId()+"&albumName="+p.getProgramName());
			content.setPhotoURL(p.getPhotourl());
			content.setSummary(p.getDescription());
 			
			content.setCreatedDate(p.getCreatedDate());
			items.add(content);			
		}		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("rssViewer");
		mav.addObject("feedContent", items);

		return mav;

	}
	
	@RequestMapping(value = "/viewLinkPrograms", method = RequestMethod.GET)
	public ModelAndView viewLinkPrograms(@RequestParam(value = "page", required = false) String strpage,ModelMap model) {
		model = defaulthomepage(model);
		
		if(homepageDAO.getDefaultconfig().getLink()!=true){
			return new ModelAndView("404frontTile");
		}
		
		int  page = 1;
		int recordsPerPage = 5;		
		
		if(strpage==null){
			strpage="1";
		}
		if(!strpage.equals("0"))
		{
		 page = Integer.parseInt(strpage);	
		}
		
		model.put("list", linkdao.getItemByModel((page-1)*recordsPerPage,recordsPerPage));
		int noOfRecords = linkdao.getNoOfRecords();
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		model.put("noOfRecords", noOfRecords);
		model.put("noOfPages", noOfPages);
		model.put("currentPage", page);
		return new ModelAndView("linkprogramsTile");
	}
	
	@RequestMapping(value = "/selectLinkProgram", method = RequestMethod.GET)
	public ModelAndView selectLinkProgram(@RequestParam(value = "id", required = true) Long id,@RequestParam(value = "albumName", required = false) String albumName,ModelMap model,HttpServletRequest request) {
		model = defaulthomepage(model);
		if(homepageDAO.getDefaultconfig().getLink()!=true){
			return new ModelAndView("404frontTile");
		}
		try{
		if(linkdao.updateFrequency(id)){
			Link p = linkdao.findByDeptId(id);
			model.put("program", p);
			model.put("albumName", albumName);
		
			ArrayList<String> fileList = new ArrayList<String>();
		
			String dirPath = p.getFolder_url();//request.getRealPath("/")+"themes\\national\\"+albumName+"\\";
			//System.out.println(dirPath);
			File folder = new File(dirPath);
			if(folder.exists()){
				for (final File fileEntry : folder.listFiles()) {
					if(fileEntry.getName().equals("0_icon.jpeg") || fileEntry.getName().equals("0_wmark.jpeg")){
						
					}else{
						fileList.add(fileEntry.getName());
					}
				}
			}
			model.put("fileList", fileList);
			model.put("fileDir", p.getFolder_relative_url());
		}
		}
		catch(Exception ex){
			ex.printStackTrace();
			return new ModelAndView("404frontTile");
		}
		return new ModelAndView("linkprogramsDetailTile");
	}
	
	@RequestMapping(value = "/viewSponsorPrograms", method = RequestMethod.GET)
	public ModelAndView viewSponsorPrograms(@RequestParam(value = "page", required = false) String strpage,ModelMap model) {
		model = defaulthomepage(model);
		if(homepageDAO.getDefaultconfig().getSponser()!=true){
			return new ModelAndView("404frontTile");
		}
		int  page = 1;
		int recordsPerPage = 5;		
		
		if(strpage==null){
			strpage="1";
		}
		if(!strpage.equals("0"))
		{
		 page = Integer.parseInt(strpage);	
		}
		
		model.put("list", sponsordao.getItemByModel((page-1)*recordsPerPage,recordsPerPage));
		int noOfRecords = sponsordao.getNoOfRecords();
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		model.put("noOfRecords", noOfRecords);
		model.put("noOfPages", noOfPages);
		model.put("currentPage", page);
		return new ModelAndView("sponsorprogramsTile");
	}
	
	@RequestMapping(value = "/selectSponsorProgram", method = RequestMethod.GET)
	public ModelAndView selectSponsorProgram(@RequestParam(value = "id", required = true) Long id,@RequestParam(value = "albumName", required = false) String albumName,ModelMap model,HttpServletRequest request) {
		model = defaulthomepage(model);
		if(homepageDAO.getDefaultconfig().getSponser()!=true){
			return new ModelAndView("404frontTile");
		}
		try{
		if(sponsordao.updateFrequency(id)){
			Sponsor p = sponsordao.findByDeptId(id);
			model.put("program", p);
			model.put("albumName", albumName);
		
			ArrayList<String> fileList = new ArrayList<String>();
		
			String dirPath = p.getFolder_url();//request.getRealPath("/")+"themes\\national\\"+albumName+"\\";
			//System.out.println(dirPath);
			File folder = new File(dirPath);
			if(folder.exists()){
				for (final File fileEntry : folder.listFiles()) {
					if(fileEntry.getName().equals("0_icon.jpeg") || fileEntry.getName().equals("0_wmark.jpeg")){
						
					}else{
						fileList.add(fileEntry.getName());
					}
				}
			}
			model.put("fileList", fileList);
			model.put("fileDir", p.getFolder_relative_url());
		}
		}
		catch(Exception ex){
			ex.printStackTrace();
			return new ModelAndView("404frontTile");
		}
		return new ModelAndView("sponsorprogramsDetailTile");
	}
	
	@RequestMapping(value = "/viewAdvertisementPrograms", method = RequestMethod.GET)
	public ModelAndView viewAdvertisementPrograms(@RequestParam(value = "page", required = false) String strpage,ModelMap model) {
		model = defaulthomepage(model);
		if(homepageDAO.getDefaultconfig().getAdvertisement()!=true){
			return new ModelAndView("404frontTile");
		}
		int  page = 1;
		int recordsPerPage = 5;		
		
		if(strpage==null){
			strpage="1";
		}
		if(!strpage.equals("0"))
		{
		 page = Integer.parseInt(strpage);	
		}
		
		model.put("list", advertisementdao.getItemByModel((page-1)*recordsPerPage,recordsPerPage));
		int noOfRecords = advertisementdao.getNoOfRecords();
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		model.put("noOfRecords", noOfRecords);
		model.put("noOfPages", noOfPages);
		model.put("currentPage", page);
		return new ModelAndView("advertisementprogramsTile");
	}
	
	@RequestMapping(value = "/selectAdvertisementProgram", method = RequestMethod.GET)
	public ModelAndView selectAdvertisementProgram(@RequestParam(value = "id", required = true) Long id,@RequestParam(value = "albumName", required = false) String albumName,ModelMap model,HttpServletRequest request) {
		model = defaulthomepage(model);
		if(homepageDAO.getDefaultconfig().getAdvertisement()!=true){
			return new ModelAndView("404frontTile");
		}
		try{
		if(advertisementdao.updateFrequency(id)){
			Advertisement p = advertisementdao.findByDeptId(id);
			model.put("program", p);
			model.put("albumName", albumName);
		
			ArrayList<String> fileList = new ArrayList<String>();
		
			String dirPath = p.getFolder_url();//request.getRealPath("/")+"themes\\national\\"+albumName+"\\";
			//System.out.println(dirPath);
			File folder = new File(dirPath);
			if(folder.exists()){
				for (final File fileEntry : folder.listFiles()) {
					if(fileEntry.getName().equals("0_icon.jpeg") || fileEntry.getName().equals("0_wmark.jpeg")){
						
					}else{
						fileList.add(fileEntry.getName());
					}
				}
			}
			model.put("fileList", fileList);
			model.put("fileDir", p.getFolder_relative_url());
		}}
		catch(Exception ex){
			ex.printStackTrace();
			return new ModelAndView("404frontTile");
		}
		return new ModelAndView("advertisementprogramsDetailTile");
	}
	
	@RequestMapping(value = "/viewGreetingPrograms", method = RequestMethod.GET)
	public ModelAndView viewGreetingPrograms(@RequestParam(value = "page", required = false) String strpage,ModelMap model) {
		model = defaulthomepage(model);
		if(homepageDAO.getDefaultconfig().getGreeting()!=true){
			return new ModelAndView("404frontTile");
		}
		int  page = 1;
		int recordsPerPage = 5;		
		
		if(strpage==null){
			strpage="1";
		}
		if(!strpage.equals("0"))
		{
		 page = Integer.parseInt(strpage);	
		}
		
		model.put("list", greetingdao.getItemByModel((page-1)*recordsPerPage,recordsPerPage));
		int noOfRecords = greetingdao.getNoOfRecords();
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		model.put("noOfRecords", noOfRecords);
		model.put("noOfPages", noOfPages);
		model.put("currentPage", page);
		return new ModelAndView("greetingprogramsTile");
	}
	
	@RequestMapping(value = "/selectGreetingProgram", method = RequestMethod.GET)
	public ModelAndView selectGreetingProgram(@RequestParam(value = "id", required = true) Long id,@RequestParam(value = "albumName", required = false) String albumName,ModelMap model,HttpServletRequest request) {
		model = defaulthomepage(model);
		if(homepageDAO.getDefaultconfig().getGreeting()!=true){
			return new ModelAndView("404frontTile");
		}try{
		if(greetingdao.updateFrequency(id)){
			Greeting p = greetingdao.findByDeptId(id);
			model.put("program", p);
			model.put("albumName", albumName);
		
			ArrayList<String> fileList = new ArrayList<String>();
		
			String dirPath = p.getFolder_url();//request.getRealPath("/")+"themes\\national\\"+albumName+"\\";
			//System.out.println(dirPath);
			File folder = new File(dirPath);
			if(folder.exists()){
				for (final File fileEntry : folder.listFiles()) {
					if(fileEntry.getName().equals("0_icon.jpeg") || fileEntry.getName().equals("0_wmark.jpeg")){
						
					}else{
						fileList.add(fileEntry.getName());
					}
				}
			}
			model.put("fileList", fileList);
			model.put("fileDir", p.getFolder_relative_url());
		}}
		catch(Exception ex){
			ex.printStackTrace();
			return new ModelAndView("404frontTile");
		}
		return new ModelAndView("greetingprogramsDetailTile");
	}
	
	@RequestMapping(value = "/viewPhotographyPrograms", method = RequestMethod.GET)
	public ModelAndView viewPhotographyPrograms(@RequestParam(value = "page", required = false) String strpage,ModelMap model) {
		model = defaulthomepage(model);
		if(homepageDAO.getDefaultconfig().getPhotography()!=true){
			return new ModelAndView("404frontTile");
		}
		int  page = 1;
		int recordsPerPage = 5;		
		
		if(strpage==null){
			strpage="1";
		}
		if(!strpage.equals("0"))
		{
		 page = Integer.parseInt(strpage);	
		}
		model.put("photographylist", photographyDAO.getItemByModel((page-1)*recordsPerPage,recordsPerPage));
		model.put("list", photographyDAO.getItemByModel((page-1)*recordsPerPage,recordsPerPage));
		int noOfRecords = photographyDAO.getNoOfRecords();
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		model.put("noOfRecords", noOfRecords);
		model.put("noOfPages", noOfPages);
		model.put("currentPage", page);
		return new ModelAndView("photographyprogramsTile");
	}
	
	@RequestMapping(value = "/selectPhotographyProgram", method = RequestMethod.GET)
	public ModelAndView selectPhotographyProgram(@RequestParam(value = "id", required = true) Long id,@RequestParam(value = "albumName", required = false) String albumName,ModelMap model,HttpServletRequest request) {
		model = defaulthomepage(model);
		if(homepageDAO.getDefaultconfig().getPhotography()!=true){
			return new ModelAndView("404frontTile");
		}
		try{
		model.put("photographylist", photographyDAO.getItemByModel((1-1)*10,10));
		
		if(photographyDAO.updateFrequency(id)){
			Photography p = photographyDAO.findByDeptId(id);
			model.put("program", p);
			model.put("albumName", albumName);
		
			ArrayList<String> fileList = new ArrayList<String>();
		
			String dirPath = p.getFolder_url();//request.getRealPath("/")+"themes\\national\\"+albumName+"\\";
			//System.out.println(dirPath);
			File folder = new File(dirPath);
			if(folder.exists()){
				for (final File fileEntry : folder.listFiles()) {
					fileList.add(fileEntry.getName());
					//System.out.println(fileEntry.getName());
				}
			}
			model.put("fileList", fileList);
			model.put("fileDir", p.getFolder_relative_url());
		}}
		catch(Exception ex){
			ex.printStackTrace();
			return new ModelAndView("404frontTile");
		}
		return new ModelAndView("photographyprogramsDetailTile");
	}
}