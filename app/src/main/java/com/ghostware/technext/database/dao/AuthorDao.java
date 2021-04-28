package com.ghostware.technext.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.ghostware.technext.models.Author;
import com.google.common.util.concurrent.ListenableFuture;

import io.reactivex.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface AuthorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    ListenableFuture<Long> insert(Author author);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertBlocking(Author author);

    @Update
    Single<Integer> update(Author author);

    @Query("SELECT * from authors where id=:authorId")
    LiveData<Author> getAuthor(long authorId);
}
