package alexey.moviedbapp.data.viewmodels.items;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;

/**
 * Created by Alexey Lyubchenko.
 */

public class ItemRequestHistoryViewModel extends BaseObservable {

    public ObservableField<String> historyQ = new ObservableField<>();

    public ItemRequestHistoryViewModel(String queryFromHistory) {
        this.historyQ.set(queryFromHistory);
    }

}
