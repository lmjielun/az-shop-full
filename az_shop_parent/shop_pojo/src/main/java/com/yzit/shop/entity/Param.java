package com.yzit.shop.entity;

import com.yzit.framework.web.ui.BaseEntity;
import com.yzit.framework.web.ui.Node;

import java.util.List;

public class Param extends BaseEntity{
    private Long id;

    private Long categoryId;

    private String name;

    private String paramItems;

    private List<Node> paramList;

    public List<Node> getParamList() {
        return paramList;
    }

    public void setParamList(List<Node> paramList) {
        this.paramList = paramList;
    }

    private Integer seq;

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

    public String getParamItems() {
        return paramItems;
    }

    public void setParamItems(String paramItems) {
        this.paramItems = paramItems == null ? null : paramItems.trim();
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }
}