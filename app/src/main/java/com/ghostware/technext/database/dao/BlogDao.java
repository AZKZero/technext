package com.ghostware.technext.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.ghostware.technext.models.Blog;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

import io.reactivex.Completable;

@Dao
public interface BlogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(Blog blog);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertBlocking(Blog blog);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Blog> blogs);

    @Update
    ListenableFuture<Integer> update(Blog blog);

    @Query("SELECT * from blogs")
    LiveData<List<Blog>> getBlogs();

    @Query("select * from blogs inner join authors on blogs.author_id=authors.id")
    LiveData<List<Blog>> compositeQuery();
}
