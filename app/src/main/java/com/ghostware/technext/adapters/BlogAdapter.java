package com.ghostware.technext.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ghostware.technext.R;
import com.ghostware.technext.databinding.BlogRvItemBinding;
import com.ghostware.technext.models.BlogAuthor;
import com.ghostware.technext.ui.BlogView;
import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Random;

public class BlogAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final ArrayList<BlogAuthor> blogList;
    private final Context context;
    private Random random;


    public BlogAdapter(
            ArrayList<BlogAuthor> blogList, Context context
    ) //adapters gets the json array and turns it into a list of products
    {
        this.blogList = blogList;
        this.context = context;
        random = new Random();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        BlogRvItemBinding blogRvItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.blog_rv_item, parent, false);
        return new ViewHolder(blogRvItemBinding);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        BlogRvItemBinding blogRvItemBinding;

        public ViewHolder(@NonNull BlogRvItemBinding blogRvItemBinding) {
            super(blogRvItemBinding.getRoot());
            this.blogRvItemBinding = blogRvItemBinding;
        }
    }

    @Override
    public int getItemCount() {
        return blogList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        BlogAuthor blog = blogList.get(position);
        if (holder instanceof ViewHolder) {
            BlogRvItemBinding binding = ((ViewHolder) holder).blogRvItemBinding;
            binding.setBlog(blog);
            Picasso.get().load(blog.cover_photo).into(binding.cover);
            Picasso.get().load(blog.author.avatar).into(binding.authorImage);


            binding.flex.removeAllViews();
            for (String category : blog.categories) {
                View v = LayoutInflater.from(context).inflate(R.layout.cat_item, null);
                MaterialButton x = v.findViewById(R.id.cat);
                x.setText(category);
                binding.flex.addView(v);
            }

            binding.getRoot().setOnClickListener(v -> context.startActivity(new Intent(context, BlogView.class).putExtra("blogId", blog.id)));
        }
    }

}
