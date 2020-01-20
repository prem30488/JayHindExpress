package com.models;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.internal.NotNull;

@Entity
@Table(name = "tbl_heading_info", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class Heading {
private int id;
	
	@NotNull
	@NotBlank
	private String message;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "message", nullable = false)
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
		/*byte ptext[] = null;
		try {
			ptext = message.getBytes("ISO_8859_1");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			String value = new String(ptext, "UTF-8");
			this.message=value;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */
		
	}
	
}
