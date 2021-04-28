package com.ghostware.technext.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ghostware.technext.api.BlogRequestCallback;
import com.ghostware.technext.core.Core;
import com.ghostware.technext.core.Repository;
import com.ghostware.technext.models.BlogAuthor;

import javax.inject.Inject;

public class BlogModel extends ViewModel {
    private LiveData<BlogAuthor> blogLiveData;

    @Inject
    Repository repository;


    public BlogModel() {
        Core.getCore().appComponent.inject(this);
    }

    public LiveData<BlogAuthor> getBlog(int blogId) {
        if (blogLiveData == null) {
            loadBlogFromDB(blogId);
        }
        return blogLiveData;
    }

    void loadBlogFromDB(int blogId) {
        blogLiveData = repository.getBlogData(blogId);
    }
}
