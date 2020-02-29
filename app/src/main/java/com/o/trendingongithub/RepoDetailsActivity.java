package com.o.trendingongithub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.o.trendingongithub.model.RepoData;

public class RepoDetailsActivity extends AppCompatActivity {

    private RepoData repoData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_details);

        getRepoDetails();
        initViews();
    }

    private void initViews() {
        ImageView avatarImageView = findViewById(R.id.repo_details_avatar);
        TextView nameTextView = findViewById(R.id.repo_details_name);
        TextView authorTextView = findViewById(R.id.repo_detals_author);
        TextView languageTextView = findViewById(R.id.repo_details_lang);
        TextView starsTextView = findViewById(R.id.repo_details_stars);
        TextView forksTextView = findViewById(R.id.repo_details_forks);
        TextView descriptionTextView = findViewById(R.id.repo_details_description);
        LinearLayout openInGithubButton = findViewById(R.id.open_in_github);

        Glide.with(this).load(repoData.getAvatarUrl()).
                placeholder(R.drawable.avatar_placeholder).into(avatarImageView);
        nameTextView.setText(repoData.getName());
        authorTextView.setText(repoData.getAuthor());
        languageTextView.setText(repoData.getLanguage());
        if (repoData.getLanguage() == null)
            languageTextView.setVisibility(View.GONE);
        starsTextView.setText(String.valueOf(repoData.getStars()));
        forksTextView.setText(String.valueOf(repoData.getForks()));
        descriptionTextView.setText(repoData.getDescription());
        openInGithubButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(repoData.getUrl()));
                startActivity(intent);
            }
        });
    }

    private void getRepoDetails() {
        Gson gson = new Gson();
        repoData = gson.fromJson(getIntent().getStringExtra("objJson"), RepoData.class);
    }
}
