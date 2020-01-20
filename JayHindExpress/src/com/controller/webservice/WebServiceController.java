package com.controller.webservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.models.Program;
import com.dao.ProgramDAO;

@RestController
public class WebServiceController {
	
	@Autowired
	private ProgramDAO programDAO;
	
	@RequestMapping(value = "/getInternational", method = RequestMethod.GET,headers="Accept=application/json")  
	 public List<Program> getDatabaseValues(@RequestParam("start") Long start, @RequestParam("length") Long length)  
	 {  
	  List<Program> list = new ArrayList<Program>();  
	  list =  programDAO.list(start,length);
	  return list;  
	 }  
	

	@RequestMapping(value = "/international/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Program> getPlan(@PathVariable("id") Long id) {
        System.out.println("Fetching plan with workid " + id);
        Program p = programDAO.findByDeptId(id);
        if (p == null) {
            System.out.println("Plan with workid " + id + " not found");
            return new ResponseEntity<Program>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Program>(p, HttpStatus.OK);
    	} 
	    
	    
	    /*@RequestMapping(value = "/createplan/", method = RequestMethod.POST)
	    public ResponseEntity<Void> createPlan(@RequestBody Program p,    UriComponentsBuilder ucBuilder) {
	        System.out.println("Creating Plan " + p.getWorkCode());
	 
	        if (programDAO.isUserExist(p)) {
	            System.out.println("A plan with id " + p.getWorkCode() + " already exist");
	            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
	        }
	 
	        programDAO.save(p);
	 
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/plan/{id}").buildAndExpand(p.getWorkCode()).toUri());
	        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	    }*/
	    
	    /*@RequestMapping(value = "/updateplan/{id}", method = RequestMethod.PUT)
	    public ResponseEntity<Program> updatePlan(@PathVariable("id") long id, @RequestBody Program p) {
	        System.out.println("Updating Plan " + p.getWorkCode());
	         
	        Program Program = programDAO.findByworkid(id);
	         
	        if (p==null) {
	            System.out.println("Plan with workid " + id + " not found");
	            return new ResponseEntity<Program>(HttpStatus.NOT_FOUND);
	        }
	 
	        Program.setZpName(p.getZpName());
	        Program.setZpCode(p.getZpCode());
	        Program.setBpName(p.getBpName());
	        Program.setBpCode(p.getBpCode());
	        Program.setGpName(p.getGpName());
	        Program.setGpCode(p.getGpCode());
	        Program.setLbType(p.getLbType());
	        Program.setPlanYear(p.getPlanYear());
	        Program.setWorkCode(p.getWorkCode());
	        Program.setWorkName(p.getWorkName());
	        Program.setWorkDescroption(p.getWorkDescroption());
	        Program.setSectorCode(p.getSectorCode());
	        Program.setSectorName(p.getSectorName());
	        Program.setAssetCoverage(p.getAssetCoverage());
	        Program.setAssetLocation(p.getAssetLocation());
	        Program.setAssetLocationCode(p.getAssetLocationCode());
	        Program.setAssetName(p.getAssetName());
	        Program.setAssetTypeCode(p.getAssetTypeCode());
	        Program.setAssetType(p.getAssetType());
	        Program.setAsset_category_code(p.getAsset_category_code());
	        Program.setAsset_category(p.getAsset_category());
	        Program.setAsset_subcategory_code(p.getAsset_subcategory_code());
	        Program.setAsset_subcategory(p.getAsset_subcategory());
	        Program.setAsset_unit(p.getAsset_unit());
	        Program.setTotal_cost_of_work(p.getTotal_cost_of_work());
	        Program.setScheme(p.getScheme());
	        Program.setSchemecomponent(p.getSchemecomponent());
	        Program.setAmount_gen(p.getAmount_gen());
	        Program.setAmount_sc(p.getAmount_sc());
	        Program.setAmount_st(p.getAmount_st());
	        
	        programDAO.updateUser(Program);
	        
	        return new ResponseEntity<Program>(Program, HttpStatus.OK);
	    }
	    
	    @RequestMapping(value = "/deleteplan/{id}", method = RequestMethod.DELETE)
	    public ResponseEntity<Program> deleteUser(@PathVariable("id") long id) {
	        System.out.println("Fetching & Deleting plan with id " + id);
	 
	        Program Program = programDAO.findByDeptId(id);
	        if (Program == null) {
	            System.out.println("Unable to delete. plan with id " + id + " not found");
	            return new ResponseEntity<Program>(HttpStatus.NOT_FOUND);
	        }
	 
	        programDAO.delete(Program);
	        return new ResponseEntity<Program>(HttpStatus.NO_CONTENT);
	    }
	    
	    @RequestMapping(value = "/deleteall/", method = RequestMethod.DELETE)
	    public ResponseEntity<Program> deleteAllUsers() {
	        System.out.println("Deleting All plans");
	 
	        //programDAO.deleteAllPlans();
	        return new ResponseEntity<Program>(HttpStatus.NO_CONTENT);
	    }
*/}
