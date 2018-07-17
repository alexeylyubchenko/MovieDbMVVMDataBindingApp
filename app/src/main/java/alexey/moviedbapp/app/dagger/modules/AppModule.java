package alexey.moviedbapp.app.dagger.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexey Lyubchenko.
 */

@Module
public class AppModule {

    private Context mContext;

    public AppModule(Context context) {
        this.mContext = context;
    }

    @Singleton
    @Provides
    public Context getContext() {
        return mContext;
    }

}
