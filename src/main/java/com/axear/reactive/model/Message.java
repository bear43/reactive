package com.axear.reactive.model;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

public class Message implements Serializable {
    @Id
    private Long id;

    private String string;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
