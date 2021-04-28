package com.ghostware.technext.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.ghostware.technext.R;
import com.ghostware.technext.core.Core;
import com.ghostware.technext.databinding.ActivityBlogViewBinding;
import com.ghostware.technext.viewmodels.BlogModel;
import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

public class BlogView extends AppCompatActivity {

    ActivityBlogViewBinding binding;
    public static final String TAG = "BlogView";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_blog_view);

        int blogId = getIntent().getIntExtra("blogId", 0);

        BlogModel blogModel = new ViewModelProvider(this).get(BlogModel.class);
        blogModel.getBlog(blogId).observe(this, blog -> {
            Log.d(TAG, "onCreate: " + Core.getCore().gson.toJson(blog));
            binding.setBlog(blog);
            Picasso.get().load(blog.cover_photo).into(binding.cover);
            Picasso.get().load(blog.author.avatar).into(binding.authorImage);

            binding.flex.removeAllViews();

            for (String category : blog.categories) {
                View v = LayoutInflater.from(this).inflate(R.layout.cat_item, null);
                MaterialButton x = v.findViewById(R.id.cat);
                x.setText(category);
                binding.flex.addView(v);
            }
        });

        binding.edit.setOnClickListener(v -> startActivity(new Intent(this, EditInsert.class).putExtra("blogId", blogId)));
    }
}