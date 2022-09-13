package com.posada.santiago.alphapostsandcomments.domain.events;

import co.com.sofka.domain.generic.DomainEvent;

public class CommentAdded extends DomainEvent {

    private String postID;
    private String id;
    private String author;
    private String content;


    public CommentAdded( String id, String author, String content) {
        super("posada.santiago.commentcreated");
        //this.postID = postID;
        this.id = id;
        this.author = author;
        this.content = content;
    }

    public String getPostID() {
        return postID;
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
