package alexey.moviedbapp.views.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.Observable;
import java.util.Observer;

import javax.inject.Inject;

import alexey.moviedbapp.data.viewmodels.base.TvShowsViewModel;
import alexey.moviedbapp.data.viewmodels.base.ViewModel;
import alexey.moviedbapp.views.activities.MainActivityInteractor;
import alexey.moviedbapp.views.fragments.View;

/**
 * Created by Alexey Lyubchenko.
 */

public abstract class BaseFragment<T extends ViewModel> extends Fragment implements Observer, View {

    @Inject
    protected T mViewModel;
    protected MainActivityInteractor mMainActivityInteractor;


    protected abstract void injectDagger();
    protected abstract void setupView();
    protected abstract void updateObservableInfo(Observable o, Object arg);
    protected abstract void setupRecyclerViewList(RecyclerView recyclerView);

    public void setUpObserver(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        updateObservableInfo(o, arg);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public android.view.View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        injectDagger();
        setupView();
        setUpObserver(mViewModel);
        mViewModel.attachView(this);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivityInteractor) {
            mMainActivityInteractor = (MainActivityInteractor) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement MainActivityInteractor");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mMainActivityInteractor = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mViewModel.detachView();
    }


    @Override
    public void showLoading() {
        if(mMainActivityInteractor != null) {
            mMainActivityInteractor.showLoadingProgress();
        }
    }

    @Override
    public void hideLoading() {
        if(mMainActivityInteractor != null) {
            mMainActivityInteractor.hideLoadingProgress();
        }
    }

    @Override
    public void showMessage(String message) {
        if(mMainActivityInteractor != null && message != null) {
            mMainActivityInteractor.showMessage(message);
        }
    }

    @Override
    public void onDataLoadSucceed() {
    }

    @Override
    public void hideKeyBoard() {
        if(mMainActivityInteractor != null) {
            mMainActivityInteractor.hideKeyBoard();
        }
    }

}
