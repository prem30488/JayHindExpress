package com.models;

import java.util.Comparator;
import java.util.Date;

public class SampleContent implements Comparable<SampleContent> {
	String title;
	String url;
	String summary;
	Date createdDate;
	String author;
	String location;
	String photoURL;
	Long frequency;
	String category;
	Long id;
	String video_id;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getFrequency() {
		return frequency;
	}
	public void setFrequency(Long frequency) {
		this.frequency = frequency;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getPhotoURL() {
		return photoURL;
	}
	public void setPhotoURL(String photoURL) {
		this.photoURL = photoURL;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getVideo_id() {
		return video_id;
	}
	public void setVideo_id(String video_id) {
		this.video_id = video_id;
	}
	@Override
	public int compareTo(SampleContent o) {
		Long compareQuantity = ((SampleContent) o).getFrequency();

		//ascending order
		//return (int) (this.frequency - compareQuantity);
		
		//descending order
		return (int) (compareQuantity-this.frequency);
		
	}
	
	public static Comparator<SampleContent> SampleNameComparator
		    = new Comparator<SampleContent>() {
		
		public int compare(SampleContent fruit1, SampleContent fruit2) {
		
		String fruitName1 = fruit1.getTitle().toUpperCase();
		String fruitName2 = fruit2.getTitle().toUpperCase();
		
		//ascending order
		return fruitName1.compareTo(fruitName2);
		
		//descending order
		//return fruitName2.compareTo(fruitName1);
		}
		
		};
		public static Comparator<SampleContent> SampleCategoryComparator
	    = new Comparator<SampleContent>() {
			
			public int compare(SampleContent fruit1, SampleContent fruit2) {
			
			String fruitName1 = fruit1.getCategory().toUpperCase();
			String fruitName2 = fruit2.getCategory().toUpperCase();
			
			//ascending order
			return fruitName1.compareTo(fruitName2);
			
			//descending order
			//return fruitName2.compareTo(fruitName1);
			}
			
			};
	
		public static Comparator<SampleContent> SampleIdComparator
	    = new Comparator<SampleContent>() {
	
			public int compare(SampleContent fruit1, SampleContent fruit2) {
	
				Long fruitName1 = fruit1.getId();
				Long fruitName2 = fruit2.getId();
		
				//ascending order
				return fruitName1.compareTo(fruitName2);
				
				//descending order
				//return fruitName2.compareTo(fruitName1);
		}
	
	};

}
