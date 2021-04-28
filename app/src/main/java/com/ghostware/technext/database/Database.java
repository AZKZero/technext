package com.ghostware.technext.database;

import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.ghostware.technext.database.dao.AuthorDao;
import com.ghostware.technext.database.dao.BlogAuthorDao;
import com.ghostware.technext.database.dao.BlogDao;
import com.ghostware.technext.models.Author;
import com.ghostware.technext.models.Blog;
import com.ghostware.technext.api.Converters;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@androidx.room.Database(entities = {Author.class, Blog.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class Database extends RoomDatabase {
    public abstract BlogDao blogDao();

    public abstract AuthorDao authorDao();

    public abstract BlogAuthorDao blogAuthorDao();

    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(1);
}
