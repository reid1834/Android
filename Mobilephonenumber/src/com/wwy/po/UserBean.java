package com.wwy.po;

public class UserBean {
   private String userid;
   private String username;
   private String userpwd;
public UserBean() {
	super();
	// TODO Auto-generated constructor stub
}
public UserBean(String userid, String username, String userpwd) {
	super();
	this.userid = userid;
	this.username = username;
	this.userpwd = userpwd;
}
public String getUserid() {
	return userid;
}
public void setUserid(String userid) {
	this.userid = userid;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getUserpwd() {
	return userpwd;
}
public void setUserpwd(String userpwd) {
	this.userpwd = userpwd;
}
   
}
