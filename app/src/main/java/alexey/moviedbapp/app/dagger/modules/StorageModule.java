package alexey.moviedbapp.app.dagger.modules;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import alexey.storage.PreferencesRepository;
import alexey.moviedbapp.utils.Constants;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexey Lyubchenko.
 */

@Module
public class StorageModule {

    @Singleton
    @Provides
    public SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(Constants.SHARED_PREF_NAME, Constants.SHARED_PREF_MODE);
    }

    @Singleton
    @Provides
    public PreferencesRepository getPreferencesRepository(SharedPreferences sharedPreferences) {
        return new PreferencesRepository(sharedPreferences);
    }
}
