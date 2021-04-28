package com.ghostware.technext.dagger;

import com.ghostware.technext.dagger.modules.ApplicationModule;
import com.ghostware.technext.dagger.modules.DBModule;
import com.ghostware.technext.dagger.modules.RetrofitModule;
import com.ghostware.technext.ui.EditInsert;
import com.ghostware.technext.ui.MainActivity;
import com.ghostware.technext.viewmodels.BlogListModel;
import com.ghostware.technext.viewmodels.BlogModel;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {RetrofitModule.class, ApplicationModule.class, DBModule.class})
@Singleton
public interface AppComponent {
    void inject(MainActivity mainActivity);

    void inject(EditInsert editInsert);

    void inject(BlogListModel blogListModel);

    void inject(BlogModel blogModel);
}
