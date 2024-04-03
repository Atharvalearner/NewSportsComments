// PostModel.java
package com.example.newsports.PostData;
import com.example.newsports.Models.PostDisplayModel;
public class PostModel {
    private String id;
    private String title;
    private String description;
    private String imageUrl;
    private String userId;

    public PostModel() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public PostModel(String id, String title, String description, String imageUrl, String userId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    // Method to convert PostModel to PostDisplayModel
    public PostDisplayModel toPostDisplayModel() {
        PostDisplayModel postDisplayModel = new PostDisplayModel();
        postDisplayModel.setName(this.title);
        postDisplayModel.setDescription(this.description);
        postDisplayModel.setImageUrl(this.imageUrl);
        postDisplayModel.setUid(this.userId);
        // You can set other properties as needed
        return postDisplayModel;
    }
}
