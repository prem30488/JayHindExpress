package com.models;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.JoinColumn;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.sun.istack.internal.NotNull;

@Entity
@Table(name = "logininformation", uniqueConstraints = {
		@UniqueConstraint(columnNames = "UserId"),
		@UniqueConstraint(columnNames = "User_Name") })
public class User implements java.io.Serializable{
	private Long userId;
	private String userName;
	private String password;
	private int enabled;
	private int accountNonExpired;
	private int accountNonLocked;
	private int credentialsNonExpired;
	
	@NotNull
//	@Pattern(regexp="[a-zA-Z0-9. _/*,-/&\n\r's]*", message="{error.invalid.chars}")
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
	@Column(name = "UserId", unique = true, nullable = false)
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@Column(name = "User_Name", unique = true, nullable = false, length = 45)
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name = "password", nullable = false, length = 45)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name = "enabled", nullable = false, length = 4)
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	
	@Column(name = "accountNonExpired", nullable = false, length = 4)
	public int getAccountNonExpired() {
		return accountNonExpired;
	}
	public void setAccountNonExpired(int accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}
	
	@Column(name = "accountNonLocked", nullable = false, length = 4)
	public int getAccountNonLocked() {
		return accountNonLocked;
	}
	public void setAccountNonLocked(int accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}
	
	@Column(name = "credentialsNonExpired", nullable = false, length = 4)
	public int getCredentialsNonExpired() {
		return credentialsNonExpired;
	}
	public void setCredentialsNonExpired(int credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}
	
	@Column(name = "description", nullable = true)
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
	
	@Column(name = "location_info", nullable = false, length=100000)
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	@Column(name = "posted_by", nullable = false, length=100000)
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
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	@Transient
	public String getNiceCreatedDate() {
		return niceCreatedDate;
	}
	public void setNiceCreatedDate(String niceCreatedDate) {
		this.niceCreatedDate = niceCreatedDate;
	}
	
	
	private Set<Role> roles = new HashSet<Role>(0);;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "userroleinformation", catalog = "public", joinColumns = {
			@JoinColumn(name = "user_id", nullable = false, updatable = false) },
			inverseJoinColumns = { @JoinColumn(name = "role_Id",
					nullable = false, updatable = false) })
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	private Boolean international;
	private Boolean national;
	private Boolean state;
	private Boolean business;
	private Boolean sport;
	private Boolean technology;
	private Boolean astro;
	private Boolean other;
	private Boolean video;
	private Boolean poll;
	private Boolean entertainment;
	private Boolean fashion;
	private Boolean game;
	private Boolean link;
	private Boolean sponser;
	private Boolean advertisement;
	private Boolean greeting;
	private Boolean photography;
	private Boolean contact;

	@Column(name = "international")
	public Boolean getInternational() {
		return international;
	}
	public void setInternational(Boolean international) {
		this.international = international;
	}
	
	@Column(name = "national")
	public Boolean getNational() {
		return national;
	}
	public void setNational(Boolean national) {
		this.national = national;
	}
	
	@Column(name = "state")
	public Boolean getState() {
		return state;
	}
	public void setState(Boolean state) {
		this.state = state;
	}
	
	@Column(name = "business")
	public Boolean getBusiness() {
		return business;
	}
	public void setBusiness(Boolean business) {
		this.business = business;
	}
	
	@Column(name = "sport")
	public Boolean getSport() {
		return sport;
	}
	public void setSport(Boolean sport) {
		this.sport = sport;
	}
	
	@Column(name = "technology")
	public Boolean getTechnology() {
		return technology;
	}
	public void setTechnology(Boolean technology) {
		this.technology = technology;
	}
	
	@Column(name = "astro")
	public Boolean getAstro() {
		return astro;
	}
	public void setAstro(Boolean astro) {
		this.astro = astro;
	}
	
	@Column(name = "others")
	public Boolean getOther() {
		return other;
	}
	public void setOther(Boolean other) {
		this.other = other;
	}
	
	@Column(name = "video")
	public Boolean getVideo() {
		return video;
	}
	public void setVideo(Boolean video) {
		this.video = video;
	}
	
	@Column(name = "poll")
	public Boolean getPoll() {
		return poll;
	}
	public void setPoll(Boolean poll) {
		this.poll = poll;
	}
	
	@Column(name = "entertainment")
	public Boolean getEntertainment() {
		return entertainment;
	}
	public void setEntertainment(Boolean entertainment) {
		this.entertainment = entertainment;
	}
	
	@Column(name = "fashion")
	public Boolean getFashion() {
		return fashion;
	}
	public void setFashion(Boolean fashion) {
		this.fashion = fashion;
	}
	
	@Column(name = "game")
	public Boolean getGame() {
		return game;
	}
	public void setGame(Boolean game) {
		this.game = game;
	}
	
	@Column(name = "link")
	public Boolean getLink() {
		return link;
	}
	public void setLink(Boolean link) {
		this.link = link;
	}
	
	@Column(name = "sponser")
	public Boolean getSponser() {
		return sponser;
	}
	public void setSponser(Boolean sponser) {
		this.sponser = sponser;
	}
	
	@Column(name = "advertisement")
	public Boolean getAdvertisement() {
		return advertisement;
	}
	public void setAdvertisement(Boolean advertisement) {
		this.advertisement = advertisement;
	}
	
	@Column(name = "greeting")
	public Boolean getGreeting() {
		return greeting;
	}
	public void setGreeting(Boolean greeting) {
		this.greeting = greeting;
	}
	
	@Column(name = "photography")
	public Boolean getPhotography() {
		return photography;
	}
	public void setPhotography(Boolean photography) {
		this.photography = photography;
	}
	
	@Column(name = "contact")
	public Boolean getContact() {
		return contact;
	}
	public void setContact(Boolean contact) {
		this.contact = contact;
	}
	
}