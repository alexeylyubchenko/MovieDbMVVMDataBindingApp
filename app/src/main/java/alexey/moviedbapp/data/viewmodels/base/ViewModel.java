package alexey.moviedbapp.data.viewmodels.base;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.Observable;

import alexey.moviedbapp.views.fragments.View;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Alexey Lyubchenko.
 */

public abstract class ViewModel<T extends View> extends Observable {

    protected CompositeDisposable mCompositeDisposable;
    protected Context mContext;
    protected T mView;

    protected abstract void loadData();

    public ViewModel(@NonNull Context context) {
        mContext = context;

        mCompositeDisposable = new CompositeDisposable();
    }

    public void detachView() {
        if(mCompositeDisposable != null && !mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
        }

        mCompositeDisposable = null;
        mContext = null;
        mView = null;
    }

    public void attachView(T view) {
        mView = view;
    }

    protected void disposeSubscription(Disposable disposable) {
        if(disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
