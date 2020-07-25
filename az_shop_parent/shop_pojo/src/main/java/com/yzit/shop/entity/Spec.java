package com.yzit.shop.entity;

import com.yzit.framework.web.ui.BaseEntity;
import com.yzit.framework.web.ui.Node;

import java.util.List;

public class Spec extends BaseEntity{
    private Long id;

    private Long categoryId;

    private String name;

    private String specItems;

    private Integer seq;

    private List<Node> specList;


    public List<Node> getSpecList() {
        return specList;
    }

    public void setSpecList(List<Node> specList) {
        this.specList = specList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSpecItems() {
        return specItems;
    }

    public void setSpecItems(String specItems) {
        this.specItems = specItems == null ? null : specItems.trim();
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }
}