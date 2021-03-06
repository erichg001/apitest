package com.bitnei.apitest.pro.saas;

import java.util.HashMap;
import java.util.List;


public class UserPro {
	private String username;
	private String password;
	private String userRole;
	private String status;
	private String origin;
	private String nickName;
	private String mobile;
	private String email;
	private HashMap<String,String> createTimeRange;
	
	
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public HashMap<String, String> getCreateTimeRange() {
		return createTimeRange;
	}
	public void setCreateTimeRange(HashMap<String, String> createTimeRange) {
		this.createTimeRange = createTimeRange;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public static class CreateTimeRange {
		private String from;
		private String to;
		
		public String getFrom() {
			return from;
		}
		public void setFrom(String from) {
			this.from = from;
		}
		public String getTo() {
			return to;
		}
		public void setTo(String to) {
			this.to = to;
		}
		public CreateTimeRange() {
			super();
		}
		
	}
	

}
