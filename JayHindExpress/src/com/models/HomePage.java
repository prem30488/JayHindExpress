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

import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.internal.NotNull;

@Entity
@Table(name = "homepageinformation", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class HomePage {
	private int id;
	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date createdDate=new Date();

	@Transient
	private String niceCreatedDate;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "created_date")
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	@Transient
	public String getNiceCreatedDate() {
		return niceCreatedDate;
	}
	public void setNiceCreatedDate(String niceCreatedDate) {
		this.niceCreatedDate = niceCreatedDate;
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
	
	private Boolean topmenu;
	private Boolean logo;
	private Boolean banner;
	private Boolean advertisement_dropdown;
	private Boolean menu;
	private Boolean breaking_news;
	private Boolean social_links;
	private Boolean slider;
	private Boolean latest_post;
	private Boolean advertisement_sidebar;	
	private Boolean popular_post;
	private Boolean category_tags_tab;
	private Boolean video_tab;
	private Boolean poll_tab;
	private Boolean twitter;
	private Boolean tag_footer;
	private Boolean contact_footer;
	private Boolean copyright_footer;
	private Boolean city;

	@Column(name = "topmenu")
	public Boolean getTopmenu() {
		return topmenu;
	}

	public void setTopmenu(Boolean topmenu) {
		this.topmenu = topmenu;
	}

	@Column(name = "logo")
	public Boolean getLogo() {
		return logo;
	}

	public void setLogo(Boolean logo) {
		this.logo = logo;
	}

	@Column(name = "banner")
	public Boolean getBanner() {
		return banner;
	}

	public void setBanner(Boolean banner) {
		this.banner = banner;
	}

	@Column(name = "advertisement_dropdown")
	public Boolean getAdvertisement_dropdown() {
		return advertisement_dropdown;
	}

	public void setAdvertisement_dropdown(Boolean advertisement_dropdown) {
		this.advertisement_dropdown = advertisement_dropdown;
	}

	@Column(name = "menu")
	public Boolean getMenu() {
		return menu;
	}

	public void setMenu(Boolean menu) {
		this.menu = menu;
	}

	@Column(name = "breaking_news")
	public Boolean getBreaking_news() {
		return breaking_news;
	}

	public void setBreaking_news(Boolean breaking_news) {
		this.breaking_news = breaking_news;
	}

	@Column(name = "social_links")
	public Boolean getSocial_links() {
		return social_links;
	}

	public void setSocial_links(Boolean social_links) {
		this.social_links = social_links;
	}

	@Column(name = "slider")
	public Boolean getSlider() {
		return slider;
	}

	public void setSlider(Boolean slider) {
		this.slider = slider;
	}

	@Column(name = "latest_post")
	public Boolean getLatest_post() {
		return latest_post;
	}

	public void setLatest_post(Boolean latest_post) {
		this.latest_post = latest_post;
	}

	@Column(name = "advertisement_sidebar")
	public Boolean getAdvertisement_sidebar() {
		return advertisement_sidebar;
	}

	public void setAdvertisement_sidebar(Boolean advertisement_sidebar) {
		this.advertisement_sidebar = advertisement_sidebar;
	}

	@Column(name = "popular_post")
	public Boolean getPopular_post() {
		return popular_post;
	}

	public void setPopular_post(Boolean popular_post) {
		this.popular_post = popular_post;
	}

	@Column(name = "category_tags_tab")
	public Boolean getCategory_tags_tab() {
		return category_tags_tab;
	}

	public void setCategory_tags_tab(Boolean category_tags_tab) {
		this.category_tags_tab = category_tags_tab;
	}

	@Column(name = "video_tab")
	public Boolean getVideo_tab() {
		return video_tab;
	}

	public void setVideo_tab(Boolean video_tab) {
		this.video_tab = video_tab;
	}

	@Column(name = "poll_tab")
	public Boolean getPoll_tab() {
		return poll_tab;
	}

	public void setPoll_tab(Boolean poll_tab) {
		this.poll_tab = poll_tab;
	}

	@Column(name = "twitter")
	public Boolean getTwitter() {
		return twitter;
	}

	public void setTwitter(Boolean twitter) {
		this.twitter = twitter;
	}

	@Column(name = "tag_footer")
	public Boolean getTag_footer() {
		return tag_footer;
	}

	public void setTag_footer(Boolean tag_footer) {
		this.tag_footer = tag_footer;
	}

	@Column(name = "contact_footer")
	public Boolean getContact_footer() {
		return contact_footer;
	}

	public void setContact_footer(Boolean contact_footer) {
		this.contact_footer = contact_footer;
	}

	@Column(name = "copyright_footer")
	public Boolean getCopyright_footer() {
		return copyright_footer;
	}

	public void setCopyright_footer(Boolean copyright_footer) {
		this.copyright_footer = copyright_footer;
	}

	@Column(name = "city")
	public Boolean getCity() {
		return city;
	}

	public void setCity(Boolean city) {
		this.city = city;
	}
	
	
}
