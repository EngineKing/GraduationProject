package com.thesis.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author LeiPeng
 * @Date 2020/1/26 - 21:52
 */
@Getter
@Setter
public class Annex {
    private Integer id;
    private String title;
    private String url;
    private String remark;

    public Annex() {
    }

    public Annex(String title, String url, String remark) {
        this.title = title;
        this.url = url;
        this.remark = remark;
    }
}
