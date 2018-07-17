package alexey.moviedbapp.adapters.tvshows;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import alexey.moviedbapp.data.models.TvShow;
import alexey.moviedbapp.views.fragments.OnRecyclerItemClickListener;

/**
 * Created by Alexey Lyubchenko.
 */

public abstract class TvShowAdapter<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T> {

    protected List<TvShow> mTvShows;
    protected OnRecyclerItemClickListener mListener;

    public TvShowAdapter(OnRecyclerItemClickListener listener)  {
        mTvShows = new ArrayList<>();
        this.mListener = listener;
    }

    public void addTvShows(List list) {
        mTvShows.addAll(list);
        notifyDataSetChanged();
    }

    public void clear() {
        mTvShows.clear();
        notifyDataSetChanged();
    }

    public TvShow[]  getTvShowsArray() {
        if(mTvShows == null) {
            return null;
        }

        return mTvShows.toArray(new TvShow[mTvShows.size()]);
    }

    @Override
    public T onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(T holder, int position) {
    }


    @Override
    public int getItemCount() {
        return mTvShows.size();
    }
}
