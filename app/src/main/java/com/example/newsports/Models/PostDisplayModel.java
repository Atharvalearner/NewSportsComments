package com.example.newsports.Models;

import com.google.firebase.firestore.ServerTimestamp;



import java.util.Date;
import java.util.List;

public class PostDisplayModel {
    private String name, profileImage, imageUrl, uid, description, id;
    @ServerTimestamp
    private Date timestamp;
    private List<String> likes;
    private int likeCount;
    private boolean liked; // variable to represent whether the post is liked or not
    private List<CommentModel> comments;

    public PostDisplayModel() {
    }

    public PostDisplayModel(String name, String profileImage, String imageUrl, String uid, String description, String id, Date timestamp, List<String> likes, int likeCount, boolean liked, List<CommentModel> comments) {
        this.name = name;
        this.profileImage = profileImage;
        this.imageUrl = imageUrl;
        this.uid = uid;
        this.description = description;
        this.id = id;
        this.timestamp = timestamp;
        this.likes = likes;
        this.likeCount = likeCount;
        this.liked = liked;
        this.comments = comments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public List<String> getLikes() {
        return likes;
    }

    public void setLikes(List<String> likes) {
        this.likes = likes;
    }
    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    // Add getter and setter methods for liked
    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public List<CommentModel> getComments() {
        return comments;
    }

    public void setComments(List<CommentModel> comments) {
        this.comments = comments;
    }
}
