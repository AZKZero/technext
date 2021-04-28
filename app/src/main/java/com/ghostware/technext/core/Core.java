package com.ghostware.technext.core;

import android.app.Application;

import androidx.room.Room;

import com.ghostware.technext.dagger.DaggerAppComponent;
import com.ghostware.technext.dagger.AppComponent;
import com.ghostware.technext.database.Database;
import com.ghostware.technext.dagger.modules.ApplicationModule;
import com.ghostware.technext.dagger.modules.DBModule;
import com.ghostware.technext.dagger.modules.RetrofitModule;
import com.google.gson.Gson;

public class Core extends Application {
    public static volatile Core core;
    public Database db;
    public Gson gson;
    public AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        core = this;
        db = Room.databaseBuilder(getApplicationContext(), Database.class, "technext").build();
        gson = new Gson();
        appComponent = DaggerAppComponent.builder()
                                         .applicationModule(new ApplicationModule(this))
                                         .retrofitModule(new RetrofitModule())
                                         .dBModule(new DBModule(this))
                                         .build();
    }

    public static Core getCore() {
        if (core == null) {
            synchronized (Core.class) {
                if (core == null) core = new Core();
            }
        }
        return core;
    }
}
