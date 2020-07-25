package com.yzit.shop.entity;
import com.yzit.framework.web.ui.BaseEntity;

/**
 * 
 * <br>
 * <b>功能：</b>地址表实体类<br>
 * <b>作者：</b>Administrator<br>
 * <b>日期：</b> 2020-06-01 <br>
 * <b>版权所有： 2020，云优众<br>
 */ 
public class Address extends BaseEntity {
   private static final long serialVersionUID = 1L;
    private Integer  id;//
    private String  userId;//用户ID
    private String  provinceId;//省
    private String  cityId;//市
    private String  townId;//县/区
    private String  mobile;//手机
    private String  address;//详细地址
    private String  contact;//联系人
    private String  isDefault;//是否是默认 1默认 0否
    private String  notes;//备注
    private java.util.Date  createDate;//创建日期
    private String  alias;//别名
    
   	public Integer getId() {
		return id;
	}
	public void setId(Integer  id) {
		this.id = id;
	}
	
   	public String getUserId() {
		return userId;
	}
	public void setUserId(String  userId) {
		this.userId = userId;
	}
	
   	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String  provinceId) {
		this.provinceId = provinceId;
	}
	
   	public String getCityId() {
		return cityId;
	}
	public void setCityId(String  cityId) {
		this.cityId = cityId;
	}
	
   	public String getTownId() {
		return townId;
	}
	public void setTownId(String  townId) {
		this.townId = townId;
	}
	
   	public String getMobile() {
		return mobile;
	}
	public void setMobile(String  mobile) {
		this.mobile = mobile;
	}
	
   	public String getAddress() {
		return address;
	}
	public void setAddress(String  address) {
		this.address = address;
	}
	
   	public String getContact() {
		return contact;
	}
	public void setContact(String  contact) {
		this.contact = contact;
	}
	
   	public String getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(String  isDefault) {
		this.isDefault = isDefault;
	}
	
   	public String getNotes() {
		return notes;
	}
	public void setNotes(String  notes) {
		this.notes = notes;
	}
	
   	public java.util.Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(java.util.Date  createDate) {
		this.createDate = createDate;
	}
	
   	public String getAlias() {
		return alias;
	}
	public void setAlias(String  alias) {
		this.alias = alias;
	}
	
}