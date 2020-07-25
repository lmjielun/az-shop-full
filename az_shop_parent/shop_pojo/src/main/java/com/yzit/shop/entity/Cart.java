package com.yzit.shop.entity;

import com.yzit.framework.web.ui.ValueNodes;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 *  购物车
 */
public class Cart implements Serializable{
    private static final long serialVersionUID = -6028248408428272735L;
    /**
     *  分析：
     *      1、购物车需要有商品的id
     *      2、购物车需要有sku的id
     *      3、购物车需要有规格以及规格选项skuSpecList
     *      4、购物车需要有价格price
     *      5、购物车需要有购买数量num
     *      6、购物车需要有商品图片 image
     *      7、购物车需要有商品名称（标题）title
     *      8、购物车需要有用户的Id userId
     */
    //商品的id
    private Integer goodsId;
    //sku的id
    private Integer skuId;
    //用户的Id userId
    private Integer userId;
    //规格以及规格选项skuSpecList
    private List<ValueNodes> skuSpecList;
    //价格price
    private BigDecimal price;
    //购买数量num
    private Integer num;
    //商品图片 image
    private String image;
    //商品名称（标题）title
    private String title;

    // 扩展属性 商品分类编号
    private Integer categoryId;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }


    @Override
    public String toString() {
        return "Cart{" +
                "goodsId=" + goodsId +
                ", skuId=" + skuId +
                ", userId=" + userId +
                ", skuSpecList=" + skuSpecList +
                ", price=" + price +
                ", num=" + num +
                ", image='" + image + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<ValueNodes> getSkuSpecList() {
        return skuSpecList;
    }

    public void setSkuSpecList(List<ValueNodes> skuSpecList) {
        this.skuSpecList = skuSpecList;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
