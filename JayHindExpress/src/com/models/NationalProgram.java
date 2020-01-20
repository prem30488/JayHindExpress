package com.models;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.sun.istack.internal.NotNull;

@Entity
@Table(name = "tbl_national_program_info", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class NationalProgram {
	
	private Long id;
	
	@NotNull
	//@Pattern(regexp="[a-zA-Z 0-9]*", message="{error.alphabetonly}")
	@NotBlank
	private String programName;
	
	@NotNull
	//@Pattern(regexp="[a-zA-Z0-9. _/*,-/&\n\r's]*", message="{error.invalid.chars}")
	@NotBlank
	private String description;
	
	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date createdDate=new Date();
	
	@NotNull
	private String photourl;
	
	@NotNull
	@NotBlank
	private String location;
	
	@NotNull
	@NotBlank
	private String posted_by;
	
	@NotNull
	private Long frequency;
	
	@NotNull
	private String folder_url;
	
	@NotNull
	private String folder_relative_url;
	
	@Transient
	private MultipartFile file;

	@Transient
	private String niceCreatedDate;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "program_name",  nullable = false)
	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "created_date")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "photo_url")
	public String getPhotourl() {
		return photourl;
	}

	public void setPhotourl(String photourl) {
		this.photourl = photourl;
	}
	@Transient
	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	@Column(name = "location_info")
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Column(name = "posted_by")
	public String getPosted_by() {
		return posted_by;
	}

	public void setPosted_by(String posted_by) {
		this.posted_by = posted_by;
	}
	
	@Column(name = "frequency", nullable = false)
	public Long getFrequency() {
		return frequency;
	}

	public void setFrequency(Long frequency) {
		this.frequency = frequency;
	}
	
	@Column(name = "folder_url")
	public String getFolder_url() {
		return folder_url;
	}

	public void setFolder_url(String folder_url) {
		this.folder_url = folder_url;
	}
	
	@Column(name = "folder_relative_url")
	public String getFolder_relative_url() {
		return folder_relative_url;
	}

	public void setFolder_relative_url(String folder_relative_url) {
		this.folder_relative_url = folder_relative_url;
	}

	@Transient
	public String getNiceCreatedDate(){
		Date d1 = getCreatedDate();
		Date d2 = new Date();
		try{
			
			long diff = d2.getTime() - d1.getTime();
			
			long diffSeconds = diff / 1000 % 60;
			long diffMinutes = diff / (60*1000) % 60;
			long diffHours = diff / (60 * 60 *1000) % 60;
			long diffDays = diff / (24* 60 * 60 *1000) % 60;
			
			if(diffDays>1){
				return diffDays + " days ago";
			}
			if(diffHours >1){
				return diffHours + " hours ago";
			}
			if(diffMinutes>1){
				return diffMinutes + " minutes ago";
			}
			return diffSeconds + " seconds ago";
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return "";
	}

	public void setNiceCreatedDate(String niceCreatedDate) {
		this.niceCreatedDate = niceCreatedDate;
	}
	
}
