package alexey.moviedbapp.data.viewmodels.base;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import alexey.rxnetwork.RxNetwork;
import alexey.moviedbapp.R;
import alexey.moviedbapp.data.api.MovieDBApi;
import alexey.moviedbapp.data.models.TvShow;
import alexey.moviedbapp.utils.Constants;
import alexey.moviedbapp.views.fragments.View;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Alexey Lyubchenko.
 */

public abstract class TvShowsViewModel<T extends View> extends ViewModel<T> {

    protected MovieDBApi mMovieDBApi;
    private RxNetwork mRxNetwork;

    private List<TvShow> mTvShows;
    protected int currentPage = 1;
    protected boolean loadingNow = false;
    protected int totalPages = 1;


    public TvShowsViewModel(@NonNull Context context, @NonNull RxNetwork rxNetwork, @NonNull MovieDBApi api) {
        super(context);
        mRxNetwork = rxNetwork;
        mMovieDBApi = api;

        mTvShows = new ArrayList<>();
    }

    @Override
    public void attachView(T view) {
        super.attachView(view);
        subscribeForNetworkStateUpdates();
    }

    private void subscribeForNetworkStateUpdates() {
        Disposable disposable = mRxNetwork
                .getNetworkStateUpdates()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe((isConnected) -> {
                            if(!isConnected) {
                                onNoInternetConnection();
                            }
                        },
                        throwable -> {
                            if(mView != null) {
                                mView.showMessage(throwable.getMessage());
                            }
                        }
                );

        mCompositeDisposable.add(disposable);
    }

    private void onNoInternetConnection() {
        mView.showMessage(mContext.getString(R.string.no_internet));
    }

    protected void updateTvShowsList(List<TvShow> list) {
        if(list != null && list.size() == 0 && currentPage == 1) {
            mView.showMessage(mContext.getString(R.string.no_results));
        }

        mTvShows.clear();
        mTvShows.addAll(list);
        setChanged();
        notifyObservers();
        mView.onDataLoadSucceed();
    }

    public List<TvShow> getTvShows() {
        return mTvShows;
    }

    public void loadNextPageIfNeeded(LinearLayoutManager llm, int delta) {
        int pastVisiblesItems, visibleItemCount, totalItemCount;

        if (!loadingNow) {
            if (delta > 0) {
                visibleItemCount = llm.getChildCount();
                totalItemCount = llm.getItemCount();
                pastVisiblesItems = llm.findFirstVisibleItemPosition();

                if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                    loadingNow = true;
                    currentPage++;
                    if(currentPage < totalPages) {
                        loadData();
                    }
                }
            }
        }
    }

    public void onSaveInstanceState(Bundle bundle, Parcelable recyclerViewState, TvShow[] dataList) {
        if(recyclerViewState != null && dataList != null && dataList.length > 0) {
            bundle.putParcelableArray(Constants.SAVED_STATE_DATA_LIST, dataList);
            bundle.putParcelable(Constants.SAVED_RECYCLER_VIEW_STATE, recyclerViewState);
            bundle.putInt(Constants.SAVED_STATE_CURRENT_PAGE_KEY, currentPage);
        }
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        if(savedInstanceState != null) {
            if(savedInstanceState.containsKey(Constants.SAVED_RECYCLER_VIEW_STATE)) {
                mView.setRecyclerViewState(savedInstanceState.getParcelable(Constants.SAVED_RECYCLER_VIEW_STATE));
            }

            if(savedInstanceState.containsKey(Constants.SAVED_STATE_CURRENT_PAGE_KEY)) {
                currentPage = savedInstanceState.getInt(Constants.SAVED_STATE_CURRENT_PAGE_KEY);
            }

            if(savedInstanceState.containsKey(Constants.SAVED_STATE_DATA_LIST)) {
                TvShow[] array = (TvShow[]) savedInstanceState.getParcelableArray(Constants.SAVED_STATE_DATA_LIST);
                if(array != null) {
                    List list = Arrays.asList(array);
                    updateTvShowsList(list);
                }
            }
        }
    }

}
