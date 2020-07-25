package com.yzit.shop.entity;

/**
 * 
 * <br>
 * <b>功能：</b>日志类实体类<br>
 * <b>作者：</b>Administrator<br>
 * <b>日期：</b> 2020-06-08 <br>
 * <b>版权所有： 2020，云优众<br>
 */ 
public class SysLog {
   private static final long serialVersionUID = 1L;
    private Integer  id;//编号
    private String  types;//日志类型
    private String  title;//日志标题
    private Integer  createBy;//创建者
    private java.util.Date  startDate;//创建时间
    private java.util.Date  endDate;//结束时间
    private String  remoteAddr;//操作IP地址
    private String  requestUri;//请求URI
    private String  method;//操作方式
    private String  params;//操作提交的数据
    private String  exceptions;//异常信息
    
   	public Integer getId() {
		return id;
	}
	public void setId(Integer  id) {
		this.id = id;
	}
	
   	public String getTypes() {
		return types;
	}
	public void setTypes(String  types) {
		this.types = types;
	}
	
   	public String getTitle() {
		return title;
	}
	public void setTitle(String  title) {
		this.title = title;
	}
	
   	public Integer getCreateBy() {
		return createBy;
	}
	public void setCreateBy(Integer  createBy) {
		this.createBy = createBy;
	}
	
   	public java.util.Date getStartDate() {
		return startDate;
	}
	public void setStartDate(java.util.Date  startDate) {
		this.startDate = startDate;
	}
	
   	public java.util.Date getEndDate() {
		return endDate;
	}
	public void setEndDate(java.util.Date  endDate) {
		this.endDate = endDate;
	}
	
   	public String getRemoteAddr() {
		return remoteAddr;
	}
	public void setRemoteAddr(String  remoteAddr) {
		this.remoteAddr = remoteAddr;
	}
	
   	public String getRequestUri() {
		return requestUri;
	}
	public void setRequestUri(String  requestUri) {
		this.requestUri = requestUri;
	}
	
   	public String getMethod() {
		return method;
	}
	public void setMethod(String  method) {
		this.method = method;
	}
	
   	public Object getParams() {
		return params;
	}
	public void setParams(String  params) {
		this.params = params;
	}
	
   	public Object getExceptions() {
		return exceptions;
	}
	public void setExceptions(String  exceptions) {
		this.exceptions = exceptions;
	}
	
}