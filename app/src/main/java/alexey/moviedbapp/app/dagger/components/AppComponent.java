package alexey.moviedbapp.app.dagger.components;

import android.content.Context;

import javax.inject.Singleton;

import alexey.moviedbapp.data.local.MovieDBRepository;
import alexey.rxnetwork.RxNetwork;
import alexey.moviedbapp.app.AppEngine;
import alexey.moviedbapp.app.dagger.modules.ApiModule;
import alexey.moviedbapp.app.dagger.modules.AppModule;
import alexey.moviedbapp.app.dagger.modules.NetworkModule;
import alexey.moviedbapp.app.dagger.modules.StorageModule;
import alexey.moviedbapp.data.api.MovieDBApi;
import alexey.storage.PreferencesRepository;
import dagger.Component;

/**
 * Created by Alexey Lyubchenko.
 */

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class, StorageModule.class, ApiModule.class})
public interface AppComponent {
    void injectApp(AppEngine appEngine);

    Context getContext();
    RxNetwork getRxNetwork();
    MovieDBApi getMovieDBApi();
    MovieDBRepository getMovieDBRepository();
}
