package com.bitnei.apitest.pro.datasendpro;

import com.bitnei.apitest.annotation.pro.TableName;
import com.bitnei.apitest.pro.Po;

@TableName(name="forward_task")
public class TaskPro extends Po{
	private int id;
	private String name;
	private String platformId;
	private String protocolId;
	private int isEncrypt;
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPlatformId() {
		return platformId;
	}
	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}
	public String getProtocolId() {
		return protocolId;
	}
	public void setProtocolId(String protocolId) {
		this.protocolId = protocolId;
	}
	public int getIsEncrypt() {
		return isEncrypt;
	}
	public void setIsEncrypt(int isEncrypt) {
		this.isEncrypt = isEncrypt;
	}

}
