package alexey.moviedbapp.adapters.tvshows;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import alexey.moviedbapp.data.models.TvShow;
import alexey.moviedbapp.data.viewmodels.items.ItemTvShowViewModel;
import alexey.moviedbapp.databinding.TvshowItemBinding;
import alexey.moviedbapp.views.fragments.OnRecyclerItemClickListener;

/**
 * Created by Alexey Lyubchenko.
 */

public class SearchingTvShowsAdapter extends TvShowAdapter<SearchingTvShowsAdapter.SearchingTvShowViewHolder> {

    public SearchingTvShowsAdapter(OnRecyclerItemClickListener listener) {
        super(listener);
    }

    @Override
    public SearchingTvShowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TvshowItemBinding tvshowItemBinding
                = TvshowItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new SearchingTvShowViewHolder(tvshowItemBinding);
    }

    @Override
    public void onBindViewHolder(SearchingTvShowViewHolder holder, int position) {
        if(mTvShows != null && mTvShows.size() > position) {
            holder.bindItem(mTvShows.get(position));
        }
    }

    class SearchingTvShowViewHolder extends RecyclerView.ViewHolder {

        TvshowItemBinding mTvshowItemBinding;

        public SearchingTvShowViewHolder(TvshowItemBinding itemBinding) {
            super(itemBinding.getRoot());
            mTvshowItemBinding = itemBinding;
        }

        public void bindItem(TvShow item) {
            if(mTvshowItemBinding.getItemTvShow() == null) {
                mTvshowItemBinding.setItemTvShow(new ItemTvShowViewModel(item, mListener));
            } else {
                mTvshowItemBinding.getItemTvShow().setTvShow(item);
            }
        }
    }
}
