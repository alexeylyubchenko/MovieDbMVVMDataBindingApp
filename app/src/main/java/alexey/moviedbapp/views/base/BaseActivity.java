package alexey.moviedbapp.views.base;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;

import alexey.moviedbapp.R;
import alexey.moviedbapp.utils.Constants;

/**
 * Created by Alexey Lyubchenko.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected abstract void injectActivity();
    protected abstract void setUpView();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestAllPermissions();

        injectActivity();
        setUpView();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    protected void hideKeyBoard(InputMethodManager imm) {
        if(imm != null) {
            View view = this.getCurrentFocus();

            if(view != null) {
                IBinder iBinder = view.getWindowToken();
                if (iBinder!= null) {
                    imm.hideSoftInputFromWindow(iBinder, 0);
                }
            }
        }
    }

    protected void switchFragment(FragmentManager fm, BaseFragment fragment, String tag) {
        fm.beginTransaction()
                .replace(Constants.FRAGMENT_PLACE_HOLDER_ID, fragment, tag)
                .addToBackStack(tag)
                .commit();
    }

    private void showLoadingProcess(ProgressBar progressBar, boolean show) {
        if(progressBar != null) {
            progressBar.bringToFront();
            int animationTime = getResources().getInteger(android.R.integer.config_shortAnimTime);
            progressBar.setVisibility(show ? View.VISIBLE : View.GONE);

            progressBar.animate().setDuration(animationTime).alpha(show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        }
    }

    protected void showLoadingAnimation(ProgressBar progressBar, boolean show) {
        if(Looper.getMainLooper() == Looper.myLooper()) {
            showLoadingProcess(progressBar, show);
        } else {
            runOnUiThread(() -> {
                showLoadingProcess(progressBar, show);
            });
        }
    }

    protected void showSnackBarMessage(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);
        snackbar.getView().setBackgroundColor(getResources().getColor(R.color.snack_bar_bg_color));
        snackbar.show();
    }

    protected void requestAllPermissions() {
        for(String permission : Constants.permissions) {
            requestPermission(permission, String.format(getString(R.string.permission_rational_text), permission));
        }
    }

    private void requestPermission(String permission, String rationalText) {
        int checkPermission = ContextCompat.checkSelfPermission(this, permission);

        if(checkPermission != PackageManager.PERMISSION_GRANTED) {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {

                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this)
                        .setCancelable(false)
                        .setTitle(rationalText)
                        .setPositiveButton(R.string.button_alert_dialog_positive_btn, (dialog, id) -> {
                            ActivityCompat
                                    .requestPermissions(this, new String[] {permission}, Constants.PERMISSION_REQUEST_CODE);
                        });

                dialogBuilder.create().show();
            } else {
                ActivityCompat
                        .requestPermissions(this, new String[] {permission}, Constants.PERMISSION_REQUEST_CODE);
            }
        }
    }

}
