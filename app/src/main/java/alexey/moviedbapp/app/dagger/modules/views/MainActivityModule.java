package alexey.moviedbapp.app.dagger.modules.views;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.inputmethod.InputMethodManager;

import alexey.moviedbapp.app.dagger.scopes.ActivityScope;
import alexey.moviedbapp.views.activities.MainActivity;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexey Lyubchenko.
 */

@Module
public class MainActivityModule {

    private MainActivity mMainActivity;

    public MainActivityModule(MainActivity activity) {
        this.mMainActivity = activity;
    }

    @ActivityScope
    @Provides
    public FragmentManager getFragmentManager() {
        return mMainActivity.getSupportFragmentManager();
    }

    @ActivityScope
    @Provides
    public InputMethodManager getInputMethodManager(Context context) {
        return (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
    }

}
