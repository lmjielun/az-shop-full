package com.yzit.framework.web.ui;

import java.io.Serializable;
import java.util.List;

/**
 *  该类作为vo类传递参数
 *  ["text":名称,"items":[{"text":名称,"value":值},....]]
 */
public class ValueNodes implements Serializable{
    // 节点的名称
    private String text;
    // 节点的集合
    private List<ValueNodes> items;
    // 节点的集合里的值
    private String value;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<ValueNodes> getItems() {
        return items;
    }

    public void setItems(List<ValueNodes> items) {
        this.items = items;
    }
}
