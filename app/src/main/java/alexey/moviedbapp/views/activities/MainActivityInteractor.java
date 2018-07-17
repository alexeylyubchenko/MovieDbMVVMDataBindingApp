package alexey.moviedbapp.views.activities;

import alexey.moviedbapp.views.base.BaseFragment;

/**
 * Created by Alexey Lyubchenko.
 */

public interface MainActivityInteractor {
    void showLoadingProgress();
    void hideLoadingProgress();
    void showMessage(String message);
    void hideKeyBoard();

    void switchFragment(BaseFragment fragment, String tag);
}
