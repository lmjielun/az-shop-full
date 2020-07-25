package com.yzit.shop.entity;
import com.yzit.framework.web.ui.BaseEntity;

/**
 * 
 * <br>
 * <b>功能：</b>优惠表实体类<br>
 * <b>作者：</b>Administrator<br>
 * <b>日期：</b> 2020-05-29 <br>
 * <b>版权所有： 2020，云优众<br>
 */ 
public class Preferential extends BaseEntity {
   private static final long serialVersionUID = 1L;
    private Integer  id;//
    private Integer  buyMoney;//消费金额
    private Integer  preMoney;//优惠金额
    private Integer  categoryId;//商品分类
    private java.util.Date  startTime;//活动开始时间
    private java.util.Date  endTime;//活动结束时间
    private Integer  state;//状态：1 有效 0 无效
    private Integer  type;//类型: 1 不翻倍 2 翻倍
    
   	public Integer getId() {
		return id;
	}
	public void setId(Integer  id) {
		this.id = id;
	}
	
   	public Integer getBuyMoney() {
		return buyMoney;
	}
	public void setBuyMoney(Integer  buyMoney) {
		this.buyMoney = buyMoney;
	}
	
   	public Integer getPreMoney() {
		return preMoney;
	}
	public void setPreMoney(Integer  preMoney) {
		this.preMoney = preMoney;
	}
	
   	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer  categoryId) {
		this.categoryId = categoryId;
	}
	
   	public java.util.Date getStartTime() {
		return startTime;
	}
	public void setStartTime(java.util.Date  startTime) {
		this.startTime = startTime;
	}
	
   	public java.util.Date getEndTime() {
		return endTime;
	}
	public void setEndTime(java.util.Date  endTime) {
		this.endTime = endTime;
	}
	
   	public Integer getState() {
		return state;
	}
	public void setState(Integer  state) {
		this.state = state;
	}
	
   	public Integer getType() {
		return type;
	}
	public void setType(Integer  type) {
		this.type = type;
	}
	
}