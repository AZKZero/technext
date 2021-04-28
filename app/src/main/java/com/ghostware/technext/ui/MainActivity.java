package com.ghostware.technext.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ghostware.technext.R;
import com.ghostware.technext.adapters.BlogAdapter;
import com.ghostware.technext.api.BlogRequestCallback;
import com.ghostware.technext.core.Core;
import com.ghostware.technext.databinding.ActivityMainBinding;
import com.ghostware.technext.models.BlogAuthor;
import com.ghostware.technext.viewmodels.BlogListModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BlogRequestCallback {

    ActivityMainBinding activityMainBinding;
    public static final String TAG = "Main";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        BlogListModel blogListModel = new ViewModelProvider(this).get(BlogListModel.class);
        blogListModel.getBlogList().observe(this, blogs -> {
            Log.d(TAG, "onCreate: " + Core.getCore().gson.toJson(blogs));
            if (blogs.isEmpty()) {
                blogListModel.loadBlogFromRemote(this);
            } else {
                for (BlogAuthor blog :
                        blogs) {
                    Log.d(TAG, "onCreate: " + Core.getCore().gson.toJson(blog));
                }
                activityMainBinding.list.setLayoutManager(new LinearLayoutManager(this));
                activityMainBinding.list.setAdapter(new BlogAdapter(new ArrayList<>(blogs), this));
            }
        });

        activityMainBinding.insert.setOnClickListener(v -> startActivity(new Intent(this, EditInsert.class)));
    }

    @Override
    public void onSuccess() {
        Log.d(TAG, "onSuccess: ");
    }

    @Override
    public void onFailure() {
        Log.e(TAG, "onFailure: ");
    }
}