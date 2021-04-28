package com.ghostware.technext.core;

import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.ghostware.technext.api.APICall;
import com.ghostware.technext.api.BlogRequestCallback;
import com.ghostware.technext.database.Database;
import com.ghostware.technext.interfaces.EntityInsertedCallback;
import com.ghostware.technext.models.Author;
import com.ghostware.technext.models.Blog;
import com.ghostware.technext.models.BlogAuthor;
import com.ghostware.technext.models.BlogSuper;
import com.google.gson.Gson;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {
    public static final String TAG = "Repository";

    public static final String[] categories = {
            "Productivity",
            "Entertainment",
            "Business",
            "Lifestyle"
    };

    public Database db;

    APICall apiCall;

    @Inject
    public Repository(APICall apiCall, Database db) {
        this.apiCall = apiCall;
        this.db = db;
    }

    String BASE_URL = "https://my-json-server.typicode.com/";

    /*public static Repository getRepository() {
        if (repository == null) {
            synchronized (Core.class) {
                if (repository == null) repository = new Repository();
            }
        }
        return repository;
    }*/

    public LiveData<List<BlogAuthor>> getBlogData() {
        return db.blogAuthorDao().getBlogs();
    }

    public LiveData<BlogAuthor> getBlogData(int id) {
        return db.blogAuthorDao().getBlog(id);
    }

    public void requestBlogFromApi(BlogRequestCallback blogRequestCallback) {
        apiCall.getBlogList().enqueue(new Callback<BlogSuper>() {
            @Override
            public void onResponse(@NonNull Call<BlogSuper> call, @NonNull Response<BlogSuper> response) {
                BlogSuper blogSuper = response.body();
                Log.d(TAG, "onResponse: " + new Gson().toJson(blogSuper));
                List<BlogAuthor> blogAuthors = blogSuper.blogs;
                for (BlogAuthor blogAuthor :
                        blogAuthors) {
                    Blog blog = blogAuthor.getBlog();
                    blog.authorId = blogAuthor.author.id;
                    db.blogDao().insertBlocking(blog);
                    db.authorDao().insertBlocking(blogAuthor.author);
                }
                blogRequestCallback.onSuccess();
            }

            @Override
            public void onFailure(Call<BlogSuper> call, Throwable t) {
                blogRequestCallback.onFailure();
            }
        });
    }

    public Completable insertBlog(Blog blog) {
        return db.blogDao().insert(blog);
    }

    public void insertAuthor(Author author, Handler handler, EntityInsertedCallback entityInsertedCallback) {
        Database.databaseWriteExecutor.submit(() -> {
            long authorId = db.authorDao().insertBlocking(author);
            handler.post(() -> entityInsertedCallback.entityInserted(authorId));
        });
    }

    public void insertBlog(Blog blog, Handler handler, EntityInsertedCallback entityInsertedCallback) {
        Database.databaseWriteExecutor.submit(() -> {
            long blogId = db.blogDao().insertBlocking(blog);
            handler.post(() -> entityInsertedCallback.entityInserted(blogId));
        });
    }

    public LiveData<Author> getAuthor(Blog blog) {
        return db.authorDao().getAuthor(blog.authorId);
    }
}
