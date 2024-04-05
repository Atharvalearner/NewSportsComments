package com.example.newsports.PostData;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newsports.Fragments.ReplacerFragment;
import com.example.newsports.Models.PostDisplayModel;
import com.example.newsports.R;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;
import com.example.newsports.Models.CommentModel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private Context context;
    private List<PostDisplayModel> postDisplayModelList;
    private Map<Integer, SimpleExoPlayer> playerMap;

    public PostAdapter(Context context, List<PostDisplayModel> postDisplayModelList) {
        this.context = context;
        this.postDisplayModelList = postDisplayModelList;
        this.playerMap = new HashMap<>();
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        PostDisplayModel postDisplayModel = postDisplayModelList.get(position);
        holder.titleTextView.setText(postDisplayModel.getName());
        holder.descriptionTextView.setText(postDisplayModel.getDescription());

        // Load profile image
        Glide.with(context)
                .load(postDisplayModel.getProfileImage())
                .placeholder(R.drawable.ic_person)
                .into(holder.profileImage);

        // Check if the post is an image or video
        if (postDisplayModel.getImageUrl() != null && postDisplayModel.getImageUrl().endsWith(".mp4")) {
            // Hide image view and show video view
            holder.imageView.setVisibility(View.GONE);
            holder.videoView.setVisibility(View.VISIBLE);
            // Load video from URL
            initializePlayer(holder.videoView, Uri.parse(postDisplayModel.getImageUrl()));
        } else {
            // Hide video view and show image view
            holder.imageView.setVisibility(View.VISIBLE);
            holder.videoView.setVisibility(View.GONE);
            // Load image from URL
            Glide.with(context)
                    .load(postDisplayModel.getImageUrl())
                    .into(holder.imageView);
        }

        // Check if the post is an image or video
//        if (PostDisplayModel.getImageUrl() != null && PostDisplayModel.getImageUrl().endsWith(".mp4")) {
//            holder.imageView.setVisibility(View.GONE);
//            holder.videoView.setVisibility(View.VISIBLE);
//            initializePlayer(holder.videoView, Uri.parse(PostDisplayModel.getImageUrl()));
//
//            // Set OnClickListener for the video view
//            holder.videoView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    // Open VideoActivity
//                    Intent intent = new Intent(context, VideoActivity.class);
//                    intent.putExtra("VIDEO_URL", PostDisplayModel.getImageUrl());
//                    context.startActivity(intent);
//                }
//            });
//        } else {
//            holder.imageView.setVisibility(View.VISIBLE);
//            holder.videoView.setVisibility(View.GONE);
//            Glide.with(context)
//                    .load(PostDisplayModel.getImageUrl())
//                    .into(holder.imageView);
//        }

        // Bind like count
        holder.likeCountTv.setText(context.getString(R.string.like_count, postDisplayModel.getLikeCount()));

        // Bind click listeners for like, comment, and share
        holder.likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle like button click
                // Toggle like status
                postDisplayModel.setLiked(!postDisplayModel.isLiked());
                // Update like count
                int likeCount = postDisplayModel.getLikeCount();
                if (postDisplayModel.isLiked()) {
                    likeCount++;
                } else {
                    likeCount--;
                }
                postDisplayModel.setLikeCount(likeCount);
                // Update UI
                holder.likeCountTv.setText(context.getString(R.string.like_count, likeCount));
                holder.likeBtn.setChecked(postDisplayModel.isLiked());
                // Update like status in database or server
            }
        });

        // Bind comments
        List<CommentModel> comments = postDisplayModel.getComments();
        if (comments != null && !comments.isEmpty()) {
            // Display comments
            // Example: holder.commentTextView.setText(comments.get(0).getText());
        }

        holder.commentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle comment button click
                Intent intent = new Intent(context, ReplacerFragment.class);
                intent.putExtra("postId", postDisplayModel.getId());
                intent.putExtra("uid", postDisplayModel.getUid());
                intent.putExtra("isComment", true);
                context.startActivity(intent);
            }
        });


        holder.shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle share button click
                // Share post URL or content
                int position = holder.getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    PostDisplayModel postDisplayModel = postDisplayModelList.get(position);
                    String imageUrl = postDisplayModel.getImageUrl();
                    if (imageUrl != null && !imageUrl.isEmpty()) {
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.putExtra(Intent.EXTRA_TEXT, imageUrl);
                        intent.setType("text/*");
                        context.startActivity(Intent.createChooser(intent, "Share link using..."));
                    }
                }
            }
        });

        holder.clickListener(postDisplayModel.getId(), postDisplayModel.getUid(), postDisplayModel.getLikes(), postDisplayModel.getImageUrl());
    }
    private void releasePlayer(PlayerView playerView) {
        int hashCode = playerView.hashCode();
        SimpleExoPlayer player = playerMap.get(hashCode);
        if (player != null) {
            player.release();
            playerMap.remove(hashCode);
        }
    }

    @Override
    public int getItemCount() {
        return postDisplayModelList.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleTextView;
        TextView descriptionTextView;
        VideoView videoView; // Change PlayerView to VideoView
        TextView likeCountTv;
        CheckBox likeBtn;
        ImageButton commentBtn;
        ImageButton shareBtn;
        CircleImageView profileImage;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.postImageView);
            titleTextView = itemView.findViewById(R.id.postTitleTextView);
            descriptionTextView = itemView.findViewById(R.id.postDescriptionTextView);
            videoView = itemView.findViewById(R.id.postVideoView); // Change playerView to videoView
//            likeCountTv = itemView.findViewById(R.id.likeCountTv);
            likeBtn = itemView.findViewById(R.id.likeBtn);
            commentBtn = itemView.findViewById(R.id.commentBtn);
            shareBtn = itemView.findViewById(R.id.shareBtn);
            profileImage = itemView.findViewById(R.id.profileImage);
        }

        public void clickListener(final String id, final String uid, final List<String> likes, final String imageUrl) {
            commentBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ReplacerFragment.class);
                    intent.putExtra("id", id);
                    intent.putExtra("uid", uid);
                    intent.putExtra("isComment", true);
                    context.startActivity(intent);
                }
            });

            shareBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.putExtra(Intent.EXTRA_TEXT, imageUrl);
                    intent.setType("text/*");
                    context.startActivity(Intent.createChooser(intent, "Share link using..."));
                }
            });
        }
    }
    private void initializePlayer(VideoView videoView, Uri videoUri) {
        videoView.setVideoURI(videoUri);
    }
}
