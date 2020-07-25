package com.yzit.shop.entity;
import com.yzit.framework.web.ui.BaseEntity;

/**
 * 
 * <br>
 * <b>功能：</b>AdCategory实体类<br>
 * <b>作者：</b>Administrator<br>
 * <b>日期：</b> 2020-05-16 <br>
 * <b>版权所有： 2020，云优众<br>
 */ 
public class AdCategory extends BaseEntity {
   private static final long serialVersionUID = 1L;
    private Integer  id;//广告分类主键
    private String  name;//广告分类名称
    private String  code;//key值
    private String  status;//状态
    
   	public Integer getId() {
		return id;
	}
	public void setId(Integer  id) {
		this.id = id;
	}
	
   	public String getName() {
		return name;
	}
	public void setName(String  name) {
		this.name = name;
	}
	
   	public String getCode() {
		return code;
	}
	public void setCode(String  code) {
		this.code = code;
	}
	
   	public String getStatus() {
		return status;
	}
	public void setStatus(String  status) {
		this.status = status;
	}
	
}