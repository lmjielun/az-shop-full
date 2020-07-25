package com.yzit.shop.entity;

import com.yzit.framework.web.ui.BaseEntity;

import java.util.List;

public class Category extends BaseEntity{
    private Long id;

    private String name;

    private Long parentId;

    private Integer isShow;

    private Integer isMenu;

    private Integer seq;

    // 扩展属性 子分类集合
    private List<Category> children;
    // 扩展属性  是否级联
    private Integer isCascade;

    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
    }

    public Integer getIsCascade() {
        return isCascade;
    }

    public void setIsCascade(Integer isCascade) {
        this.isCascade = isCascade;
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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public Integer getIsMenu() {
        return isMenu;
    }

    public void setIsMenu(Integer isMenu) {
        this.isMenu = isMenu;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }
}