package com.ghostware.technext.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.ghostware.technext.models.BlogAuthor;

import java.util.List;

@Dao
public interface BlogAuthorDao {
    @Transaction
    @Query("SELECT * FROM blogs")
    LiveData<List<BlogAuthor>> getBlogs();

    @Transaction
    @Query("select * from blogs where blogs.rowId=:blogId")
    LiveData<BlogAuthor> getBlog(int blogId);
}
