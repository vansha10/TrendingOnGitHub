package com.o.trendingongithub.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.o.trendingongithub.R;
import com.o.trendingongithub.model.RepoData;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private List<RepoData> mDataset;
    private final RequestManager glide;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView;
        public TextView authorTextView;
        public ImageView avatarImageView;
        public TextView starsTextView;
        public MyViewHolder(View v) {
            super(v);
            nameTextView = v.findViewById(R.id.repo_name);
            authorTextView = v.findViewById(R.id.repo_author);
            avatarImageView = v.findViewById(R.id.repo_avatar);
            starsTextView = v.findViewById(R.id.repo_stars);
        }
    }

    public RecyclerViewAdapter(RequestManager glide, List<RepoData> myDataset) {
        this.glide = glide;
        mDataset = myDataset;
    }

    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);


        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        RepoData repoData = mDataset.get(position);
        holder.nameTextView.setText(repoData.getName());
        holder.authorTextView.setText(repoData.getAuthor());
        holder.starsTextView.setText(String.valueOf(repoData.getStars()));
        glide.load(repoData.getAvatarUrl()).placeholder(R.drawable.avatar_placeholder).into(holder.avatarImageView);
    }

    @Override
    public int getItemCount() {
        if (mDataset == null)
            return 0;
        return mDataset.size();
    }
}