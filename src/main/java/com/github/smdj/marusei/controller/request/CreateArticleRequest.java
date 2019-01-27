package com.github.smdj.marusei.controller.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.StringJoiner;

public class CreateArticleRequest {
    @NotEmpty
    @Size(max = 225)
    private String title;

    @NotEmpty
    private String body;


    public CreateArticleRequest() {
    }

    public CreateArticleRequest(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CreateArticleRequest.class.getSimpleName() + "[", "]")
                .add("title='" + title + "'")
                .add("body='" + body + "'")
                .toString();
    }
}
