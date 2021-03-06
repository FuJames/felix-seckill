package com.fqz.sku.dto;

import java.io.Serializable;

/**
 * @author fuqianzhong
 * @date 2019/2/25
 */
public class SkuDto implements Serializable {
    private Integer id;

    private String title;

    private Integer stock;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
