package com.ghostware.technext.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ghostware.technext.api.BlogRequestCallback;
import com.ghostware.technext.core.Core;
import com.ghostware.technext.core.Repository;
import com.ghostware.technext.models.BlogAuthor;

import java.util.List;

import javax.inject.Inject;

public class BlogListModel extends ViewModel {
    private LiveData<List<BlogAuthor>> blogListLiveData;

    @Inject
    Repository repository;


    public BlogListModel() {
        Core.getCore().appComponent.inject(this);
    }

    public LiveData<List<BlogAuthor>> getBlogList() {
        if (blogListLiveData == null) {
            loadBlogFromDB();
        }
        return blogListLiveData;
    }

    void loadBlogFromDB() {
        blogListLiveData = repository.getBlogData();
    }

    public void loadBlogFromRemote(BlogRequestCallback requestCallback) {repository.requestBlogFromApi(requestCallback);}
}
