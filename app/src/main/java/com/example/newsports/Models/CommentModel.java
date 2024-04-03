package com.example.newsports.Models;

public class CommentModel {
    private String id;
    private String userId;
    private String postId;
    private String text;
    private long timestamp;

    public CommentModel() {
        // Default constructor required for Firebase
    }

    public CommentModel(String id, String userId, String postId, String text, long timestamp) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
        this.text = text;
        this.timestamp = timestamp;
    }

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
