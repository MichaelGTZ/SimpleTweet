package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import java.util.List;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder> {

    Context context;
    List<Tweet> tweets;

    // Pass in the context and list of tweets
    public TweetsAdapter(Context context, List<Tweet> tweets) {
        this.context = context;
        this.tweets = tweets;
    }

    // For each row, inflate the layout for a Tweet
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tweet, parent, false);
        return new ViewHolder(view);
    }

    // Bind values based on position of the Tweet element
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the data at position
        Tweet tweet = tweets.get(position);
        // Bind the tweet to the view holder
        holder.bind(tweet);
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    // Clean all elements of the recycler
    public void clear() {
        tweets.clear();
        notifyDataSetChanged();
    }

    // Add a list of items to data set
    public void addAll(List<Tweet> list) {
        tweets.addAll(list);
        notifyDataSetChanged();
    }

    // Define a viewholder
    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivProfileImage;
        TextView tvName;
        TextView tvScreenName;
        TextView tvBody;
        TextView tvTimestamp;
        ImageView ivMedia;

        public ViewHolder(@NonNull View itemView) { // itemView is a representation of one Tweet in the RV
            super(itemView);
            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
            tvName = itemView.findViewById(R.id.tvName);
            tvScreenName = itemView.findViewById(R.id.tvScreenName);
            tvBody = itemView.findViewById(R.id.tvBody);
            tvTimestamp = itemView.findViewById(R.id.tvTimestamp);
            ivMedia = itemView.findViewById(R.id.ivMedia);
        }

        public void bind(Tweet tweet) {
            tvName.setText(tweet.user.name);
            tvScreenName.setText(tweet.user.screenName);
            tvBody.setText(tweet.body);
            tvTimestamp.setText(tweet.getRelativeTimeAgo(tweet.createdAt));
            Glide.with(context).load(tweet.user.profileImageURL).into(ivProfileImage);
            if (tweet.mediaURL == null) {
                ivMedia.setVisibility(View.GONE);
            } else {
                Glide.with(context).load(tweet.mediaURL).into(ivMedia);
                ivMedia.setVisibility(View.VISIBLE);
            }

        }
    }
}
