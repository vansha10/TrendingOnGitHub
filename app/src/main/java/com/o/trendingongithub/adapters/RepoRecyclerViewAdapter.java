package com.o.trendingongithub.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.o.trendingongithub.R;
import com.o.trendingongithub.model.RepoData;

import java.util.List;

public class RepoRecyclerViewAdapter extends RecyclerView.Adapter<RepoRecyclerViewAdapter.MyViewHolder> {
    private final RequestManager glide;
    private List<RepoData> mDataset;

    public RepoRecyclerViewAdapter(RequestManager glide, List<RepoData> myDataset) {
        this.glide = glide;
        mDataset = myDataset;
    }

    @NonNull
    @Override
    public RepoRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.repo_list_item, parent, false);


        return new MyViewHolder(v);
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

    public void updateList(List<RepoData> newList) {
        mDataset.clear();
        mDataset.addAll(newList);
        notifyDataSetChanged();
    }

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
}