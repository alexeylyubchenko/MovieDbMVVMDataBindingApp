package alexey.moviedbapp.data.viewmodels.items;


import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import alexey.moviedbapp.data.models.TvShow;
import alexey.moviedbapp.utils.Constants;
import alexey.moviedbapp.views.fragments.OnRecyclerItemClickListener;

/**
 * Created by Alexey Lyubchenko.
 */

public class ItemTvShowViewModel extends BaseObservable {

    private TvShow tvShow;
    private OnRecyclerItemClickListener mListener;

    public ItemTvShowViewModel(@NonNull TvShow tvShow, OnRecyclerItemClickListener listener) {
        this.tvShow = tvShow;
        this.mListener = listener;
        notifyChange();
    }

    public void setTvShow(TvShow tvShow) {
        this.tvShow = tvShow;
        notifyChange();
    }

    @BindingAdapter("imageUrl") public static void setImageUrl(ImageView imageView, String url) {
        Picasso.with(imageView.getContext()).load(url).into(imageView);
    }

    public void onItemClicked(View view) {
        if(mListener != null) {
            mListener.onItemClicked(tvShow);
        }
    }

    public String getName() {
        return tvShow.getName();
    }

    public String getPosterUrl() {
        return Constants.IMAGE_URL + tvShow.getPosterImgUrl();
    }

    public String getOverview() {
        return tvShow.getOverview();
    }

    public String getVote() {
        return String.valueOf(tvShow.getVoteAvg());
    }


}
