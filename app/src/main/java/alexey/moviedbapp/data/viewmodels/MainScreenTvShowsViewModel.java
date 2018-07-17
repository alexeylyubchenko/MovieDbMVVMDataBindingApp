package alexey.moviedbapp.data.viewmodels;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.SearchView;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import alexey.moviedbapp.app.dagger.scopes.FragmentScope;
import alexey.moviedbapp.data.local.MovieDBRepository;
import alexey.moviedbapp.data.models.Response;
import alexey.rxnetwork.RxNetwork;
import alexey.moviedbapp.data.api.MovieDBApi;
import alexey.moviedbapp.data.viewmodels.base.TvShowsViewModel;
import alexey.moviedbapp.utils.Constants;
import alexey.rxsearchview.RxSearchView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Alexey Lyubchenko.
 */

@FragmentScope
public class MainScreenTvShowsViewModel extends TvShowsViewModel {

    private RxSearchView mRxSearchView;
    private Disposable searchViewSubscription, newPagesSubscription;
    private MovieDBRepository mMovieDBRepository;

    @Inject
    public MainScreenTvShowsViewModel(@NonNull Context context, @NonNull RxNetwork rxNetwork,
                                      @NonNull MovieDBApi api, @NonNull RxSearchView rxSearchView, @NonNull MovieDBRepository repository) {
        super(context, rxNetwork, api);

        mRxSearchView = rxSearchView;
        mMovieDBRepository = repository;
    }

    @Override
    protected void loadData() {
        disposeSubscription(newPagesSubscription);

        if(RxSearchView.getCurrentQueryText() != null && !RxSearchView.getCurrentQueryText().isEmpty()) {
            newPagesSubscription = subscribeForSearchTvShow(searchForTvShowsWithAPI(RxSearchView.getCurrentQueryText()), true);

            mCompositeDisposable.add(newPagesSubscription);
        }
    }

    /***
     * Start listening and get observable for query text listener changes
     * debounce - using for setting expected "no typing" silence for explicit time to preserve from quick typing
     * filter - do not react on empty query text in SearchView
     * distinctUntilChanged - do not do the same request again till query text not changed
     * We subscribe on SearchView query text changes in Main UI thread and observe on changes in IO - network request thread
     * Then we observe result we do switchMap and return Observable on request to server side to get repos by text query
     * that we got as Subscriber
     * We use switchMap to prevent from queue of request - we just observe last sent request
     * then we subscribe onNext sending us for SearchView query text and with switchMap onNext with Repos result.
     * onComplete we get then we hit submit on SearchView and at that point we resubscribe for getting info from SearchView
     * @param searchView - SearchView to observe
     */
    public void startListeningSearchViewChanges(android.support.v7.widget.SearchView searchView) {
        disposeSubscription(searchViewSubscription);

        searchViewSubscription = subscribeForSearchTvShow(observableForSearchView(searchView), false);
        mCompositeDisposable.add(searchViewSubscription);
    }

    private Observable<Response> observableForSearchView(SearchView searchView) {
        return mRxSearchView.observeSearchViewTextChanges(searchView)
                .debounce(Constants.TYPE_TIMEOUT, TimeUnit.MILLISECONDS)
                .filter((query) ->  !query.isEmpty())
                .distinctUntilChanged()
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .switchMap( (query) ->
                        searchForTvShowsWithAPI(query)
                                .doOnSubscribe(disposable -> mView.showLoading())
                );
    }

    private Observable<Response> searchForTvShowsWithAPI(String query) {
        return mMovieDBApi.searchTvShows(Constants.API_KEY, Constants.API_PARAM_LANG_VALUE, query, currentPage)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    /***
     *
     * @param observable - final observable for getting data from server side
     * @param loadNewPages - if its SearchView subscription we do not do request for paging but we do it async in separate subscription.
     *                     If its TRUE - then its subscription for getting new page of repos for the same query text
     *                     If its False - then its subscription for get new repos for new first query text request
     * @return Disposable for SearchView changes ot for paging changes
     */
    public Disposable subscribeForSearchTvShow(Observable<Response> observable, boolean loadNewPages) {
        return observable
                .doFinally(() -> {
                    loadingNow = false;
                    mView.hideLoading();
                })
                .doAfterNext(response -> {
                    if(RxSearchView.getCurrentQueryText() != null && !RxSearchView.getCurrentQueryText().isEmpty()) {
                        mMovieDBRepository.addRequestsStringToHistory(RxSearchView.getCurrentQueryText());
                    }
                })
                .subscribe(
                response -> {
                    if(response != null && response.getTvShows() != null) {
                        totalPages = response.getTotalPages();

                        if(!loadNewPages) {
                            currentPage = 1;
                            mView.clearDataAdapter();
                        }

                        updateTvShowsList(response.getTvShows());
                    }
                },
                throwable -> {
                    mView.showMessage(throwable.getMessage());
                },
                () -> {
                    mView.hideKeyBoard();
                }
        );
    }
}
