package alexey.moviedbapp.app;

import android.app.Application;

import alexey.moviedbapp.app.dagger.components.AppComponent;
import alexey.moviedbapp.app.dagger.components.DaggerAppComponent;
import alexey.moviedbapp.app.dagger.modules.AppModule;

/**
 * Created by Alexey Lyubchenko.
 */

public class AppEngine extends Application {

    private static AppComponent appComponent;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        injectDagger();
    }

    private void injectDagger() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this.getApplicationContext()))
                .build();
        appComponent.injectApp(this);
    }
}
