package alexey.moviedbapp.app.dagger.modules.views;

import alexey.moviedbapp.app.dagger.scopes.FragmentScope;
import alexey.moviedbapp.views.fragments.View;
import alexey.rxsearchview.RxSearchView;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexey Lyubchenko.
 */

@Module
public class FragmentScreenViewModule {

    View view;

    public FragmentScreenViewModule(View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    public View getView() {
        return view;
    }

    @FragmentScope
    @Provides
    public RxSearchView provideRxSearchView() {
        return new RxSearchView();
    }

}

