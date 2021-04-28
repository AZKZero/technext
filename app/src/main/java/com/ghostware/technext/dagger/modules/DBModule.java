package com.ghostware.technext.dagger.modules;

import android.content.Context;

import androidx.room.Room;

import com.ghostware.technext.database.Database;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DBModule {
    private final Context context;

    public DBModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public Database db() {
        return Room.databaseBuilder(context, Database.class, "technext").build();
    }
}
