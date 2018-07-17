package alexey.moviedbapp.views.fragments;

import android.os.Parcelable;

/**
 * Created by Alexey Lyubchenko.
 */

public interface View {
    void showLoading();
    void hideLoading();
    void hideKeyBoard();
    void showMessage(String message);

    void onDataLoadSucceed();
    void setRecyclerViewState(Parcelable recyclerViewState);
    void clearDataAdapter();
}
