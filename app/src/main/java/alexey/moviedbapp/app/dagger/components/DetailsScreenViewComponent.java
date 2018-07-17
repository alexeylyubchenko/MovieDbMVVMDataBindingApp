package alexey.moviedbapp.app.dagger.components;

import alexey.moviedbapp.app.dagger.modules.views.FragmentScreenViewModule;
import alexey.moviedbapp.app.dagger.scopes.FragmentScope;
import alexey.moviedbapp.data.viewmodels.DetailsScreenTvShowsViewModel;
import alexey.moviedbapp.views.fragments.DetailsScreenFragment;
import dagger.Component;

/**
 * Created by Alexey Lyubchenko.
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentScreenViewModule.class)
public interface DetailsScreenViewComponent {
    void injectFragment(DetailsScreenFragment fragment);
    DetailsScreenTvShowsViewModel getDetailsScreenViewModel();
}
