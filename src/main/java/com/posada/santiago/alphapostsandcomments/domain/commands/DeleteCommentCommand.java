package com.posada.santiago.alphapostsandcomments.domain.commands;

import co.com.sofka.domain.generic.Command;

public class DeleteCommentCommand extends Command {
    private String postId;
    private String commentId;
    private String author;
    private String content;

    public DeleteCommentCommand() {
    }

    public String getPostId() {
        return postId;
    }

    public String getCommentId() {
        return commentId;
    }


    public String getAuthor() {
        return author;
    }


    public String getContent() {
        return content;
    }
}
