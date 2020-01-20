package com.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Length.List;
import org.hibernate.validator.constraints.NotEmpty;

import static javax.persistence.GenerationType.IDENTITY;

/**
* @author Parth Trivedi , Paresh Patel
* @version 1.0
* @Modify 22-09-2014
*/
@Entity
@Table(name = "logininformation", uniqueConstraints = {
		@UniqueConstraint(columnNames = "userid")})
public class ChangePassword {
	
	@Id
	private int userId;
	
	@Transient
	@NotEmpty(message = "{error.password.required}")
	@List({
	    @Length(min = 5, message = "{error.password.length.min}"),
	    @Length(max = 25, message = "{error.password.length.max}")
	})
	@Pattern(regexp="[a-zA-Z0-9_]*", message="{error.alphanumeric}")
	private String currentPassword;
	
	@NotEmpty(message = "{error.password.required}")
	@List({
	    @Length(min = 5, message = "{error.password.length.min}"),
	    @Length(max = 25, message = "{error.password.length.max}")
	})
	@Pattern(regexp="[a-zA-Z0-9_]*", message="{error.alphanumeric}")
	@Transient
	private String newPassword;
	
	@Transient
	@NotEmpty(message = "{error.password.required}")
	@List({
	    @Length(min = 5, message = "{error.password.length.min}"),
	    @Length(max = 25, message = "{error.password.length.max}")
	})
	@Pattern(regexp="[a-zA-Z0-9_]*", message="{error.alphanumeric}")
	private String confirmPassword;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "userid", unique = true, nullable = false)
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	@Transient
	public String getCurrentPassword() {
		return currentPassword;
	}
	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}
	
	@Transient
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	@Transient
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	
	
}
