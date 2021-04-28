package com.ghostware.technext.dagger.modules;

import com.ghostware.technext.core.Core;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    Core core;

    public ApplicationModule(Core core) {
        this.core = core;
    }

    @Provides
    @Singleton
    public Core core() {
        return core;
    }

}
