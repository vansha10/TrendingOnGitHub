package com.o.trendingongithub.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.o.trendingongithub.R;
import com.o.trendingongithub.model.DeveloperData;

import java.util.List;

public class DeveloperRecyclerViewAdapter extends RecyclerView.Adapter<DeveloperRecyclerViewAdapter.MyViewHolder> {
    private List<DeveloperData> mDataset;
    private final RequestManager glide;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView usernameTextView;
        public TextView nameTextView;
        public ImageView avatarImageView;
        public MyViewHolder(View v) {
            super(v);
            usernameTextView = v.findViewById(R.id.developer_username);
            nameTextView = v.findViewById(R.id.developer_name);
            avatarImageView = v.findViewById(R.id.developer_avatar);
        }
    }

    public DeveloperRecyclerViewAdapter(RequestManager glide, List<DeveloperData> myDataset) {
        this.glide = glide;
        mDataset = myDataset;
    }

    @Override
    public DeveloperRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                        int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.developer_list_item, parent, false);


        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        DeveloperData developerData = mDataset.get(position);
        holder.usernameTextView.setText(String.format("@%s", developerData.getUsername()));
        holder.nameTextView.setText(developerData.getName());
        glide.load(developerData.getAvatar()).placeholder(R.drawable.avatar_placeholder).into(holder.avatarImageView);
    }

    @Override
    public int getItemCount() {
        if (mDataset == null)
            return 0;
        return mDataset.size();
    }
}