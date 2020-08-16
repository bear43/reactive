package com.axear.reactive.form;

import javax.validation.constraints.NotBlank;

public class MessageForm {
    @NotBlank(message = "Message string cannot be blank")
    private String string;

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
