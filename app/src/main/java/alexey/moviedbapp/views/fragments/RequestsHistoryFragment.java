package alexey.moviedbapp.views.fragments;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Observable;

import alexey.moviedbapp.adapters.RequestHistoryAdapter;
import alexey.moviedbapp.app.AppEngine;
import alexey.moviedbapp.app.dagger.components.DaggerRequestsHistoryScreenViewComponent;
import alexey.moviedbapp.app.dagger.components.RequestsHistoryScreenViewComponent;
import alexey.moviedbapp.app.dagger.modules.views.FragmentScreenViewModule;
import alexey.moviedbapp.data.viewmodels.RequestHistoryTvShowsViewModel;
import alexey.moviedbapp.databinding.FragmentRequestsHistoryBinding;
import alexey.moviedbapp.views.base.BaseFragment;


public class RequestsHistoryFragment extends BaseFragment<RequestHistoryTvShowsViewModel> {

    RequestsHistoryScreenViewComponent mRequestsHistoryScreenViewComponent;
    FragmentRequestsHistoryBinding mFragmentRequestsHistoryBinding;
    RequestHistoryAdapter mRequestHistoryAdapter;

    public static RequestsHistoryFragment newInstance() {
        RequestsHistoryFragment fragment = new RequestsHistoryFragment();
        return fragment;
    }

    @Override
    protected void injectDagger() {
        mRequestsHistoryScreenViewComponent = DaggerRequestsHistoryScreenViewComponent.builder()
                .appComponent(AppEngine.getAppComponent())
                .fragmentScreenViewModule(new FragmentScreenViewModule(this))
                .build();
        mRequestsHistoryScreenViewComponent.injectFragment(this);
    }

    @Override
    protected void setupView() {
        mRequestHistoryAdapter = new RequestHistoryAdapter();
        mRequestHistoryAdapter.setQueries(mViewModel.getQueriesList());
        mFragmentRequestsHistoryBinding.setViewModel(mViewModel);
        setupRecyclerViewList(mFragmentRequestsHistoryBinding.requestHistoryList);
    }

    @Override
    protected void updateObservableInfo(Observable o, Object arg) {
        if(o instanceof RequestHistoryTvShowsViewModel) {
            mViewModel = (RequestHistoryTvShowsViewModel) o;
            mRequestHistoryAdapter.setQueries(mViewModel.getQueriesList());
        }
    }

    @Override
    protected void setupRecyclerViewList(RecyclerView recyclerView) {
        recyclerView.setAdapter(mRequestHistoryAdapter);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(llm);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(mFragmentRequestsHistoryBinding == null) {
            mFragmentRequestsHistoryBinding = FragmentRequestsHistoryBinding.inflate(inflater, container, false);
            super.onCreateView(inflater, container, savedInstanceState);

            mViewModel.fetchRequestsHistory();
        }

        return mFragmentRequestsHistoryBinding.getRoot();
    }


    @Override
    public void setRecyclerViewState(Parcelable recyclerViewState) {

    }

    @Override
    public void clearDataAdapter() {
        mRequestHistoryAdapter.clear();
    }
}
