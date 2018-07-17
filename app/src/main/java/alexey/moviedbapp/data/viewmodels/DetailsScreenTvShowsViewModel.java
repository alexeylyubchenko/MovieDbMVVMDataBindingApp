package alexey.moviedbapp.data.viewmodels;

import android.content.Context;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import alexey.moviedbapp.app.dagger.scopes.FragmentScope;
import alexey.rxnetwork.RxNetwork;
import alexey.moviedbapp.data.api.MovieDBApi;
import alexey.moviedbapp.data.models.TvShow;
import alexey.moviedbapp.data.viewmodels.base.TvShowsViewModel;
import alexey.moviedbapp.utils.Constants;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Alexey Lyubchenko.
 */

@FragmentScope
public class DetailsScreenTvShowsViewModel extends TvShowsViewModel {

    private TvShow mTvShow;
    public ObservableField<Integer> similarLltVisibility = new ObservableField<>();

    @Inject
    public DetailsScreenTvShowsViewModel(@NonNull Context context, @NonNull RxNetwork rxNetwork, @NonNull MovieDBApi api) {
        super(context, rxNetwork, api);
        similarLltVisibility.set(android.view.View.GONE);
    }

    public void setTvShow(@NonNull TvShow tvShow) {
        this.mTvShow = tvShow;
    }

    public void fetchSimilarTvShows() {
        if(mTvShow == null) {
            return;
        }

        Disposable disposable = mMovieDBApi.getSimilarTvShows(mTvShow.getId(),
                                                                Constants.API_KEY,
                                                                Constants.API_PARAM_LANG_VALUE,
                                                                currentPage)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doFinally(() -> {
                    loadingNow = false;
                })
                .subscribe(
                        response -> {
                            if(response != null && response.getTvShows() != null) {

                                if(currentPage == 1 && response.getTvShows().size() > 0) {
                                    similarLltVisibility.set(android.view.View.VISIBLE);
                                }

                                totalPages = response.getTotalPages();
                                updateTvShowsList(response.getTvShows());
                            }
                        }, throwable -> {
                            mView.showMessage(throwable.getMessage());
                        }
                );

        mCompositeDisposable.add(disposable);
    }

    @Override
    protected void loadData() {
        fetchSimilarTvShows();
    }

}
