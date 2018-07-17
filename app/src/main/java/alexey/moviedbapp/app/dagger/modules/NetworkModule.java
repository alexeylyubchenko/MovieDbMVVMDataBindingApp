package alexey.moviedbapp.app.dagger.modules;

import android.content.Context;
import android.net.ConnectivityManager;

import javax.inject.Singleton;

import alexey.rxnetwork.RxNetwork;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexey Lyubchenko.
 */

@Module
public class NetworkModule {

    @Singleton
    @Provides
    public ConnectivityManager getConnectivityManager(Context context) {
        return (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    @Singleton
    @Provides
    public RxNetwork getRxNetwork(Context context, ConnectivityManager cm) {
        return new RxNetwork(context, cm);
    }
}
