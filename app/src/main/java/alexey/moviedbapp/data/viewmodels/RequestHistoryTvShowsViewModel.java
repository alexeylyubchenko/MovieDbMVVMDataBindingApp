package alexey.moviedbapp.data.viewmodels;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import alexey.moviedbapp.app.dagger.scopes.FragmentScope;
import alexey.moviedbapp.data.local.MovieDBRepository;
import alexey.moviedbapp.data.viewmodels.base.ViewModel;

/**
 * Created by Alexey Lyubchenko.
 */

@FragmentScope
public class RequestHistoryTvShowsViewModel extends ViewModel {

    private MovieDBRepository mMovieDBRepository;

    List<String> historyOfQueries;

    @Inject
    public RequestHistoryTvShowsViewModel(@NonNull Context context, @NonNull MovieDBRepository repository) {
        super(context);
        this.mMovieDBRepository = repository;
        loadData();
    }

    public void fetchRequestsHistory() {
        loadData();
    }

    @Override
    protected void loadData() {
        historyOfQueries = new ArrayList<>(mMovieDBRepository.getRequestsHistory());
        notifyObservers();
    }

    public List<String> getQueriesList() {
        return historyOfQueries;
    }
}
