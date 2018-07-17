package alexey.moviedbapp.views.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;

import javax.inject.Inject;

import alexey.moviedbapp.R;
import alexey.moviedbapp.app.AppEngine;
import alexey.moviedbapp.app.dagger.components.DaggerMainActivityComponent;
import alexey.moviedbapp.app.dagger.components.MainActivityComponent;
import alexey.moviedbapp.app.dagger.modules.views.MainActivityModule;
import alexey.moviedbapp.utils.Constants;
import alexey.moviedbapp.views.base.BaseActivity;
import alexey.moviedbapp.views.base.BaseFragment;
import alexey.moviedbapp.views.fragments.MainScreenFragment;
import alexey.moviedbapp.views.fragments.RequestsHistoryFragment;

public class MainActivity extends BaseActivity implements MainActivityInteractor {

    ProgressBar processLoadingBar;

    ActionBar mActionBar;

    @Inject
    InputMethodManager mInputMethodManager;

    @Inject
    FragmentManager mFragmentManager;

    MainActivityComponent mMainActivityComponent;

    @Override
    protected void injectActivity() {
        mMainActivityComponent = DaggerMainActivityComponent.builder()
                .appComponent(AppEngine.getAppComponent())
                .mainActivityModule(new MainActivityModule(this))
                .build();

        mMainActivityComponent.injectActivity(this);
    }

    @Override
    protected void setUpView() {
        mActionBar = getSupportActionBar();
        mActionBar.setHomeButtonEnabled(true);

        Fragment fragment = mFragmentManager.findFragmentByTag(Constants.FRAGMENT_MAINSCREEN__TAG);
        if(fragment == null || fragment.isDetached() ) {
            switchFragment(mFragmentManager, MainScreenFragment.newInstance(), Constants.FRAGMENT_MAINSCREEN__TAG);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if(mFragmentManager.getBackStackEntryCount() == 0) {
            finish();
        }

        Fragment mainFirstFragment = mFragmentManager.findFragmentByTag(Constants.FRAGMENT_MAINSCREEN__TAG);
        if(mainFirstFragment != null && mainFirstFragment.isVisible()) {
            mActionBar.setDisplayHomeAsUpEnabled(false);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                onBackPressed();
            } break;

            case R.id.history: {
                switchFragment(mFragmentManager, RequestsHistoryFragment.newInstance(), Constants.FRAGMENT_HISTORY_TAG);
            } break;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public void hideKeyBoard() {
        hideKeyBoard(mInputMethodManager);
    }

    @Override
    public void showLoadingProgress() {
        showLoadingAnimation(processLoadingBar, true);
    }

    @Override
    public void hideLoadingProgress() {
        showLoadingAnimation(processLoadingBar, false);
    }

    @Override
    public void showMessage(String message) {
        showSnackBarMessage(message);
        hideKeyBoard(mInputMethodManager);
    }

    @Override
    public void switchFragment(BaseFragment fragment, String tag) {
        mActionBar.setDisplayHomeAsUpEnabled(true);
        switchFragment(mFragmentManager, fragment, tag);
    }
}
