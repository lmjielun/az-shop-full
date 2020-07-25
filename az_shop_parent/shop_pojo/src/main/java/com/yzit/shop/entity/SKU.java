package com.yzit.shop.entity;

import com.yzit.framework.web.ui.BaseEntity;
import com.yzit.framework.web.ui.ValueNodes;

import java.util.List;

/**
 * 
 * <br>
 * <b>功能：</b>SKU实体类<br>
 * <b>作者：</b>Administrator<br>
 * <b>日期：</b> 2020-05-08 <br>
 * <b>版权所有： 2020，云优众<br>
 */ 
public class SKU extends BaseEntity {
   private static final long serialVersionUID = 1L;
    private Integer  id;//商品id，同时也是商品编号
    private Integer  goodsId;//商品编号
    private String  name;//商品标题
    private String  sellPoint;//商品卖点
    private Integer  price;//商品价格，单位为：元
    private Integer  num;//库存数量
    private Integer  alertNum;//预警数量
    private String  barcode;//商品条形码
    private String  specs;//sku规格
    private String  image;//商品图片

	// 扩展属性
	private List<ValueNodes> specList;

	public List<ValueNodes> getSpecList() {
		return specList;
	}

	public void setSpecList(List<ValueNodes> specList) {
		this.specList = specList;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer  id) {
		this.id = id;
	}
	
   	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer  goodsId) {
		this.goodsId = goodsId;
	}
	
   	public String getName() {
		return name;
	}
	public void setName(String  name) {
		this.name = name;
	}
	
   	public String getSellPoint() {
		return sellPoint;
	}
	public void setSellPoint(String  sellPoint) {
		this.sellPoint = sellPoint;
	}
	
   	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer  price) {
		this.price = price;
	}
	
   	public Integer getNum() {
		return num;
	}
	public void setNum(Integer  num) {
		this.num = num;
	}
	
   	public Integer getAlertNum() {
		return alertNum;
	}
	public void setAlertNum(Integer  alertNum) {
		this.alertNum = alertNum;
	}
	
   	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String  barcode) {
		this.barcode = barcode;
	}
	
   	public String getSpecs() {
		return specs;
	}
	public void setSpecs(String  specs) {
		this.specs = specs;
	}
	
   	public String getImage() {
		return image;
	}
	public void setImage(String  image) {
		this.image = image;
	}
	
}