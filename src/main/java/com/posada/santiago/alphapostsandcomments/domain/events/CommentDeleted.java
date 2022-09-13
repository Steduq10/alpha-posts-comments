package com.posada.santiago.alphapostsandcomments.domain.events;

import co.com.sofka.domain.generic.DomainEvent;

public class CommentDeleted extends DomainEvent {
    private String id;
    private String author;
    private String content;


    public CommentDeleted(String id, String author, String content) {
        super("posada.santiago.commentdeleted");
        this.id = id;
        this.author = author;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }
}
