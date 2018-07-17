package alexey.moviedbapp.app.dagger.components;

import alexey.moviedbapp.app.dagger.modules.views.FragmentScreenViewModule;
import alexey.moviedbapp.app.dagger.scopes.FragmentScope;
import alexey.moviedbapp.data.viewmodels.MainScreenTvShowsViewModel;
import alexey.moviedbapp.views.fragments.MainScreenFragment;
import alexey.rxsearchview.RxSearchView;
import dagger.Component;

/**
 * Created by Alexey Lyubchenko.
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentScreenViewModule.class)
public interface MainScreenViewComponent {
    void injectFragment(MainScreenFragment fragment);

    MainScreenTvShowsViewModel getMainScreenViewModel();
    RxSearchView getRxSearchView();
}
