package alexey.moviedbapp.app.dagger.modules;

import javax.inject.Singleton;

import alexey.moviedbapp.data.api.ApiFactory;
import alexey.moviedbapp.data.api.MovieDBApi;
import alexey.moviedbapp.data.api.MovieDBOkHttpClientProvider;
import alexey.moviedbapp.utils.Constants;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Alexey Lyubchenko.
 */

@Module
public class ApiModule {

    @Singleton
    @Provides
    public GsonConverterFactory getGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Singleton
    @Provides
    public RxJava2CallAdapterFactory getRxJavaCallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Singleton
    @Provides
    public OkHttpClient getMovieDBOkHttpClient() {
        return MovieDBOkHttpClientProvider.createOkHttpClient();
    }

    @Singleton
    @Provides
    public MovieDBApi getMovieDBApi(OkHttpClient httpClient,
                                    RxJava2CallAdapterFactory adapterFactory,
                                    GsonConverterFactory converterFactory) {
        return ApiFactory.createApiService(Constants.BASE_URL, MovieDBApi.class, httpClient, adapterFactory, converterFactory);
    }

}
