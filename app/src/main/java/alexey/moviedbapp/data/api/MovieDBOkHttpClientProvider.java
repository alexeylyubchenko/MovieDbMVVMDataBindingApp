package alexey.moviedbapp.data.api;

import java.util.concurrent.TimeUnit;

import alexey.moviedbapp.utils.Constants;
import okhttp3.OkHttpClient;

/**
 * Created by Alexey Lyubchenko.
 */

public class MovieDBOkHttpClientProvider extends OkHttpClient {

    /***
     * Provide OkHttpClient with custom settings
     * @return OkHttpClient
     */
    public static OkHttpClient createOkHttpClient() {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder()
                .writeTimeout(Constants.NETWORK_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Constants.NETWORK_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(Constants.NETWORK_TIMEOUT, TimeUnit.SECONDS);

        return okHttpClientBuilder.build();
    }

}
