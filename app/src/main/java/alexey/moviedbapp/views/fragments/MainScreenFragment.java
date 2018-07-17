package alexey.moviedbapp.views.fragments;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Observable;

import alexey.moviedbapp.adapters.tvshows.SearchingTvShowsAdapter;
import alexey.moviedbapp.app.AppEngine;
import alexey.moviedbapp.app.dagger.components.DaggerMainScreenViewComponent;
import alexey.moviedbapp.app.dagger.components.MainScreenViewComponent;
import alexey.moviedbapp.app.dagger.modules.views.FragmentScreenViewModule;
import alexey.moviedbapp.data.models.TvShow;
import alexey.moviedbapp.data.viewmodels.MainScreenTvShowsViewModel;
import alexey.moviedbapp.databinding.FragmentMainScreenBinding;
import alexey.moviedbapp.utils.Constants;
import alexey.moviedbapp.views.base.BaseFragment;


public class MainScreenFragment extends BaseFragment<MainScreenTvShowsViewModel> implements OnRecyclerItemClickListener {

    MainScreenViewComponent mMainScreenViewComponent;
    FragmentMainScreenBinding mFragmentMainScreenBinding;
    SearchingTvShowsAdapter mSearchingTvShowsAdapter;

    public MainScreenFragment() {
        // Required empty public constructor
    }

    public static MainScreenFragment newInstance() {
        MainScreenFragment fragment = new MainScreenFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(mFragmentMainScreenBinding == null || mViewModel == null) {
            mFragmentMainScreenBinding = FragmentMainScreenBinding.inflate(inflater, container, false);
            super.onCreateView(inflater, container, savedInstanceState);
        }

        mViewModel.startListeningSearchViewChanges(mFragmentMainScreenBinding.searchView);

        return mFragmentMainScreenBinding.getRoot();
    }


    @Override
    protected void injectDagger() {
        mMainScreenViewComponent = DaggerMainScreenViewComponent.builder()
                .appComponent(AppEngine.getAppComponent())
                .fragmentScreenViewModule(new FragmentScreenViewModule(this))
                .build();

        mMainScreenViewComponent.injectFragment(this);
    }

    @Override
    protected void setupView() {
        mSearchingTvShowsAdapter = new SearchingTvShowsAdapter(this);
        mFragmentMainScreenBinding.setViewModel(mViewModel);
        setupRecyclerViewList(mFragmentMainScreenBinding.populatTvShowList);
    }

    @Override
    protected void setupRecyclerViewList(RecyclerView recyclerView) {
        recyclerView.setAdapter(mSearchingTvShowsAdapter);

        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(llm);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                mViewModel.loadNextPageIfNeeded(llm, dy);
            }
        });
    }

    @Override
    protected void updateObservableInfo(Observable o, Object arg) {
        if(o instanceof MainScreenTvShowsViewModel) {
            mViewModel = (MainScreenTvShowsViewModel) o;
            mSearchingTvShowsAdapter.addTvShows(mViewModel.getTvShows());
        }
    }

    @Override
    public void onItemClicked(TvShow tvShow) {
        mMainActivityInteractor.switchFragment(DetailsScreenFragment.newInstance(tvShow), Constants.FRAGMENT_DETAILS_TAG);
    }

    @Override
    public void onSaveInstanceState(Bundle outSate) {
        if(mViewModel != null && mFragmentMainScreenBinding != null && mSearchingTvShowsAdapter != null) {
            try {
                Parcelable recyclerViewState = mFragmentMainScreenBinding.populatTvShowList.getLayoutManager().onSaveInstanceState();
                mViewModel.onSaveInstanceState(outSate, recyclerViewState, mSearchingTvShowsAdapter.getTvShowsArray());
            } catch (Exception exp) {
                exp.printStackTrace();
            }
        }

        super.onSaveInstanceState(outSate);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void setRecyclerViewState(Parcelable recyclerViewState) {
        if(mFragmentMainScreenBinding != null && mSearchingTvShowsAdapter != null) {
            try {
                mFragmentMainScreenBinding.populatTvShowList.getLayoutManager().onRestoreInstanceState(recyclerViewState);
            } catch (Exception exp) {
                exp.printStackTrace();
            }
        }
    }

    @Override
    public void clearDataAdapter() {
        mSearchingTvShowsAdapter.clear();
    }

}
