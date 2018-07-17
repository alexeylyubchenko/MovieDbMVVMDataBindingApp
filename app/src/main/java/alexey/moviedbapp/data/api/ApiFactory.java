package alexey.moviedbapp.data.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Alexey Lyubchenko.
 */

public class ApiFactory {

    /***
     * Generating API service from Retrofit builder
     * @param baseUrl - main API url
     * @param classType - API class type "NameOfAPI.class"
     * @param httpClient - http client for adding interceptors and request settings
     * @param rxJavaCallAdapterFactory - call adapter factory to use RxJava as request calls
     * @param gsonConverterFactory - parsing response mechanism
     * @param <T> - API class
     * @return - API service to make Network request via RxJava funcs
     */
    public static <T> T createApiService(String baseUrl, Class<T> classType,
                                         OkHttpClient httpClient,
                                         RxJava2CallAdapterFactory rxJavaCallAdapterFactory,
                                         GsonConverterFactory gsonConverterFactory) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(httpClient)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .addConverterFactory(gsonConverterFactory)
                .build();


        return retrofit.create(classType);
    }

}
