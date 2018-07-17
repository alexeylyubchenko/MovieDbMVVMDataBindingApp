package alexey.moviedbapp.app.dagger.components;

import alexey.moviedbapp.app.dagger.modules.views.FragmentScreenViewModule;
import alexey.moviedbapp.app.dagger.scopes.FragmentScope;
import alexey.moviedbapp.data.viewmodels.RequestHistoryTvShowsViewModel;
import alexey.moviedbapp.views.fragments.RequestsHistoryFragment;
import dagger.Component;

/**
 * Created by Alexey Lyubchenko.
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentScreenViewModule.class)
public interface RequestsHistoryScreenViewComponent {
    void injectFragment(RequestsHistoryFragment fragment);

    RequestHistoryTvShowsViewModel getRequestHistoryViewModel();
}
