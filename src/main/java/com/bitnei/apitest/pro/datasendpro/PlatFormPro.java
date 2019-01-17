package com.bitnei.apitest.pro.datasendpro;


import com.bitnei.apitest.annotation.pro.TableName;
import com.bitnei.apitest.pro.Po;

@TableName(name="forward_platform")
public class PlatFormPro extends Po {
	
	private String forwardMode;
	private String staticForwardPlatform;
	private String priority;
	private String nsPort;
	private String unitName;
	private String address;
	private String password;
	private String notes;
	private String cdKey;
	private String username;
	


	public PlatFormPro(String forwardMode, String staticForwardPlatform, String priority, String nsPort,
			String unitName, String address, String password, String notes, String cdKey, String username) {
		super();
		this.forwardMode = forwardMode;
		this.staticForwardPlatform = staticForwardPlatform;
		this.priority = priority;
		this.nsPort = nsPort;
		this.unitName = unitName;
		this.address = address;
		this.password = password;
		this.notes = notes;
		this.cdKey = cdKey;
		this.username = username;
	}
	public PlatFormPro() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getForwardMode() {
		return forwardMode;
	}
	public void setForwardMode(String forwardMode) {
		this.forwardMode = forwardMode;
	}
	public String getStaticForwardPlatform() {
		return staticForwardPlatform;
	}
	public void setStaticForwardPlatform(String staticForwardPlatform) {
		this.staticForwardPlatform = staticForwardPlatform;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getNsPort() {
		return nsPort;
	}
	public void setNsPort(String nsPort) {
		this.nsPort = nsPort;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getCdKey() {
		return cdKey;
	}
	public void setCdKey(String cdKey) {
		this.cdKey = cdKey;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
}
