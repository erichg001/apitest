package com.bitnei.apitest.pro.openapi;

import java.sql.Date;
import java.sql.Timestamp;

import com.bitnei.apitest.annotation.pro.TableName;
import com.bitnei.apitest.pro.Po;

/** 
* @author 作者 hangang
* @version 创建时间：2020年3月5日 上午10:36:23 
* 类说明 
*/
@TableName(name="vehicle_charge_meta")
public class VehicleChargeMetaPro extends Po{
	
	private Integer id;
	private String user_id;
	private String car_no;
	private String station;
	private Double lat;
	private Double lng;
	private Double start_soc;
	private Double end_soc;
	private Date start_time;
	private Date end_time;
	private Timestamp create_time;
	private String customer;
	private Double electric_amount;
	private Double voltage;
	private Double current;
	private String type;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getCar_no() {
		return car_no;
	}
	public void setCar_no(String car_no) {
		this.car_no = car_no;
	}
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}
	public Double getStart_soc() {
		return start_soc;
	}
	public void setStart_soc(Double start_soc) {
		this.start_soc = start_soc;
	}
	public Double getEnd_soc() {
		return end_soc;
	}
	public void setEnd_soc(Double end_soc) {
		this.end_soc = end_soc;
	}
	public Date getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public Double getElectric_amount() {
		return electric_amount;
	}
	public void setElectric_amount(Double electric_amount) {
		this.electric_amount = electric_amount;
	}
	public Double getVoltage() {
		return voltage;
	}
	public void setVoltage(Double voltage) {
		this.voltage = voltage;
	}
	public Double getCurrent() {
		return current;
	}
	public void setCurrent(Double current) {
		this.current = current;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
