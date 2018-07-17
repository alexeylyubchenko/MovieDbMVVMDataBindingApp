package alexey.moviedbapp.app.dagger.components;

import alexey.moviedbapp.app.dagger.modules.views.MainActivityModule;
import alexey.moviedbapp.app.dagger.scopes.ActivityScope;
import alexey.moviedbapp.views.activities.MainActivity;
import dagger.Component;

/**
 * Created by Alexey Lyubchenko.
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = MainActivityModule.class)
public interface MainActivityComponent {
    void injectActivity(MainActivity activity);
}
