package com.yzit.shop.entity;

import com.yzit.framework.web.ui.BaseEntity;
import com.yzit.framework.web.ui.FileVo;
import com.yzit.framework.web.ui.ValueNodes;

import java.util.List;

/**
 * 
 * <br>
 * <b>功能：</b>Goods实体类<br>
 * <b>作者：</b>Administrator<br>
 * <b>日期：</b> 2020-05-08 <br>
 * <b>版权所有： 2020，云优众<br>
 */ 
public class Goods extends BaseEntity {
   private static final long serialVersionUID = 1L;
    private Integer  id;//主键
    private String  goodsName;//SPU名称
    private String  sn;//货号
    private Integer  brandId;//品牌
    private Integer  category1Id;//一级类目
    private Integer  category2Id;//二级类目
    private Integer  category3Id;//三级类目
    private String  image;//商品图片
    private Object  itemImages;//商品图片列表
    private Object  packageList;//包装列表
    private Object  saleService;//售后服务
    private Object  introduction;//描述
    private Integer  defaultItemId;//默认SKU
    private String  caption;//副标题
    private String  smallPic;//小图
    private Integer  price;//商城价
    private Object  specItems;//规格结果集
    private Object  paraItems;//商品参数结果集
    private Object  attrItems;//商品属性结果集
    private String  isMarketable;//是否上架
    private String  isEnableSpec;//是否启用规格
    private String  isDelete;//是否删除
    private String  status;//状态

	// 扩展属性： 参数List集合
	private List<ValueNodes> paraItemsList;

	// 扩展属性： 属性List集合
	private List<ValueNodes> attrItemsList;

	// 扩展属性： 规格List集合
	private List<ValueNodes> specItemsList;

	// 扩展属性：图片集合
	private List<FileVo> imageList;

	// 扩展属性： sku list集合
	private List<SKU> skuList;

	// 扩展属性  默认的sku对象  就是当你选中一个商品的时候，默认显示哪个sku
	private SKU defaultSKU;

	public SKU getDefaultSKU() {
		return defaultSKU;
	}

	public void setDefaultSKU(SKU defaultSKU) {
		this.defaultSKU = defaultSKU;
	}

	public List<FileVo> getImageList() {
		return imageList;
	}

	public void setImageList(List<FileVo> imageList) {
		this.imageList = imageList;
	}

	public List<ValueNodes> getSpecItemsList() {
		return specItemsList;
	}

	public void setSpecItemsList(List<ValueNodes> specItemsList) {
		this.specItemsList = specItemsList;
	}

	public List<SKU> getSkuList() {
		return skuList;
	}

	public void setSkuList(List<SKU> skuList) {
		this.skuList = skuList;
	}

	public List<ValueNodes> getAttrItemsList() {
		return attrItemsList;
	}

	public void setAttrItemsList(List<ValueNodes> attrItemsList) {
		this.attrItemsList = attrItemsList;
	}

	public List<ValueNodes> getParaItemsList() {
		return paraItemsList;
	}

	public void setParaItemsList(List<ValueNodes> paraItemsList) {
		this.paraItemsList = paraItemsList;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer  id) {
		this.id = id;
	}
	
   	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String  goodsName) {
		this.goodsName = goodsName;
	}
	
   	public String getSn() {
		return sn;
	}
	public void setSn(String  sn) {
		this.sn = sn;
	}
	
   	public Integer getBrandId() {
		return brandId;
	}
	public void setBrandId(Integer  brandId) {
		this.brandId = brandId;
	}
	
   	public Integer getCategory1Id() {
		return category1Id;
	}
	public void setCategory1Id(Integer  category1Id) {
		this.category1Id = category1Id;
	}
	
   	public Integer getCategory2Id() {
		return category2Id;
	}
	public void setCategory2Id(Integer  category2Id) {
		this.category2Id = category2Id;
	}
	
   	public Integer getCategory3Id() {
		return category3Id;
	}
	public void setCategory3Id(Integer  category3Id) {
		this.category3Id = category3Id;
	}
	
   	public String getImage() {
		return image;
	}
	public void setImage(String  image) {
		this.image = image;
	}
	
   	public Object getItemImages() {
		return itemImages;
	}
	public void setItemImages(Object  itemImages) {
		this.itemImages = itemImages;
	}
	
   	public Object getPackageList() {
		return packageList;
	}
	public void setPackageList(Object  packageList) {
		this.packageList = packageList;
	}
	
   	public Object getSaleService() {
		return saleService;
	}
	public void setSaleService(Object  saleService) {
		this.saleService = saleService;
	}
	
   	public Object getIntroduction() {
		return introduction;
	}
	public void setIntroduction(Object  introduction) {
		this.introduction = introduction;
	}
	
   	public Integer getDefaultItemId() {
		return defaultItemId;
	}
	public void setDefaultItemId(Integer  defaultItemId) {
		this.defaultItemId = defaultItemId;
	}
	
   	public String getCaption() {
		return caption;
	}
	public void setCaption(String  caption) {
		this.caption = caption;
	}
	
   	public String getSmallPic() {
		return smallPic;
	}
	public void setSmallPic(String  smallPic) {
		this.smallPic = smallPic;
	}
	
   	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer  price) {
		this.price = price;
	}
	
   	public Object getSpecItems() {
		return specItems;
	}
	public void setSpecItems(Object  specItems) {
		this.specItems = specItems;
	}
	
   	public Object getParaItems() {
		return paraItems;
	}
	public void setParaItems(Object  paraItems) {
		this.paraItems = paraItems;
	}
	
   	public Object getAttrItems() {
		return attrItems;
	}
	public void setAttrItems(Object  attrItems) {
		this.attrItems = attrItems;
	}
	
   	public String getIsMarketable() {
		return isMarketable;
	}
	public void setIsMarketable(String  isMarketable) {
		this.isMarketable = isMarketable;
	}
	
   	public String getIsEnableSpec() {
		return isEnableSpec;
	}
	public void setIsEnableSpec(String  isEnableSpec) {
		this.isEnableSpec = isEnableSpec;
	}
	
   	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String  isDelete) {
		this.isDelete = isDelete;
	}
	
   	public String getStatus() {
		return status;
	}
	public void setStatus(String  status) {
		this.status = status;
	}
	
}