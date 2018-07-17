package alexey.rxsearchview;


import android.support.v7.widget.SearchView;

import io.reactivex.Observable;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Alexey Lyubchenko.
 */

public class RxSearchView {

    @Getter @Setter
    private static String currentQueryText = "";

    /***
     * Create observable for SearchView and sending OnNext then text has been changed
     * and onComplete then you hit Submit button on SearchView
     * @param view
     * @return Observable
     */
    public Observable<String> observeSearchViewTextChanges(SearchView view) {
        return Observable.create( emmiter -> {
            view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    currentQueryText = query;
                    emmiter.onComplete();
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    currentQueryText = newText;
                    emmiter.onNext(currentQueryText);
                    return true;
                }
            });


        });
    }

}
