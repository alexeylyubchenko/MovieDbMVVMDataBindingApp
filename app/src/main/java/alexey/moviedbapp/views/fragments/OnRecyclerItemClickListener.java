package alexey.moviedbapp.views.fragments;

import alexey.moviedbapp.data.models.TvShow;

/**
 * Created by Alexey Lyubchenko.
 */

public interface OnRecyclerItemClickListener {
    void onItemClicked(TvShow tvShow);
}
