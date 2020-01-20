package com.models;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "roleinformation",  uniqueConstraints = {
		@UniqueConstraint(columnNames = "Role_Id"),
		@UniqueConstraint(columnNames = "ROLE") })
public class Role implements java.io.Serializable{
	private Long roleId;
	
	@NotEmpty/*(message = "{error.role.required}")*/
	@NotBlank/*(message = "{error.role.required}")*/
	@Column(unique = true)
	private String role;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Role_Id", unique = true, nullable = false)
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
	@Column(name = "ROLE", unique = true, nullable = false, length=45)
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
		
	private Set<User> users = new HashSet<User>();

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	
}
