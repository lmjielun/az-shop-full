package com.yzit.shop.entity;

import com.yzit.framework.web.ui.BaseEntity;
import com.yzit.framework.web.ui.Node;

import java.util.List;

public class Attr extends BaseEntity{
    private Long id;

    private String name;

    private Long categoryId;

    private String attrItems;

    private Integer seq;

    // 扩展属性
    private List<Node> itemList;

    public List<Node> getItemList() {
        return itemList;
    }

    public void setItemList(List<Node> itemList) {
        this.itemList = itemList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getAttrItems() {
        return attrItems;
    }

    public void setAttrItems(String attrItems) {
        this.attrItems = attrItems == null ? null : attrItems.trim();
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }
}