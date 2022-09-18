package com.code.solvers.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class User implements Serializable {
	
	private static final long serialVersionUID = -1736459380005621699L;
	private String _id;
	private ArrayList<Email> emails;
	private String type;
	private String status;
	private boolean active;
	private ArrayList<String> roles;
	private String name;
	private Date lastLogin;
	private String username;
	private String nameInsensitive;
	private Object avatarETag;
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public ArrayList<Email> getEmails() {
		return emails;
	}
	public void setEmails(ArrayList<Email> emails) {
		this.emails = emails;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public ArrayList<String> getRoles() {
		return roles;
	}
	public void setRoles(ArrayList<String> roles) {
		this.roles = roles;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNameInsensitive() {
		return nameInsensitive;
	}
	public void setNameInsensitive(String nameInsensitive) {
		this.nameInsensitive = nameInsensitive;
	}
	public Object getAvatarETag() {
		return avatarETag;
	}
	public void setAvatarETag(Object avatarETag) {
		this.avatarETag = avatarETag;
	}
	
}
