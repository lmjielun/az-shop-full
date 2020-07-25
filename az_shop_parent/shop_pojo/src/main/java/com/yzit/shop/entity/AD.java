package com.yzit.shop.entity;
import com.yzit.framework.web.ui.BaseEntity;

/**
 * 
 * <br>
 * <b>功能：</b>AD实体类<br>
 * <b>作者：</b>Administrator<br>
 * <b>日期：</b> 2020-05-16 <br>
 * <b>版权所有： 2020，云优众<br>
 */ 
public class AD extends BaseEntity {
   private static final long serialVersionUID = 1L;
    private Integer  id;//广告id主键
    private Integer  categoryId;//广告分类Id
    private String  title;//广告标题
    private String  url;//广告连接
    private String  pic;//图片地址
    private String  status;//状态
    private Integer  seq;//排序

	// 扩展属性
	private String code;
	// 扩展属性
	private Integer count;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer  id) {
		this.id = id;
	}
	
   	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer  categoryId) {
		this.categoryId = categoryId;
	}
	
   	public String getTitle() {
		return title;
	}
	public void setTitle(String  title) {
		this.title = title;
	}
	
   	public String getUrl() {
		return url;
	}
	public void setUrl(String  url) {
		this.url = url;
	}
	
   	public String getPic() {
		return pic;
	}
	public void setPic(String  pic) {
		this.pic = pic;
	}
	
   	public String getStatus() {
		return status;
	}
	public void setStatus(String  status) {
		this.status = status;
	}
	
   	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer  seq) {
		this.seq = seq;
	}
	
}