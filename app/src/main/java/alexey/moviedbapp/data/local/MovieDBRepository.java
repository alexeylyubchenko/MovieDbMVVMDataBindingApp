package alexey.moviedbapp.data.local;

import android.support.annotation.NonNull;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

import alexey.moviedbapp.utils.Constants;
import alexey.storage.PreferencesRepository;


/**
 * Created by Alexey Lyubchenko.
 */

@Singleton
public class MovieDBRepository {

    private PreferencesRepository mPreferencesRepository;
    private Set<String> requestsHistory;

    @Inject
    public MovieDBRepository(PreferencesRepository preferencesRepository) {
        this.mPreferencesRepository = preferencesRepository;
    }

    public void addRequestsStringToHistory(@NonNull String query) {
        if(requestsHistory == null) {
            loadRequestsHistoryFromPref();
        }

        requestsHistory.add(query);
        saveRequestsHistoryToPref();
    }

    public Set<String> getRequestsHistory() {
        if(requestsHistory == null) {
            loadRequestsHistoryFromPref();
        }

        return requestsHistory;
    }

    private void loadRequestsHistoryFromPref() {
        requestsHistory = mPreferencesRepository.getSavedStringSetFromPref(Constants.REQUEST_HISTORY_KEY);

        if(requestsHistory == null) {
            requestsHistory = new HashSet<>();
        }
    }

    private void saveRequestsHistoryToPref() {
        mPreferencesRepository.saveStringSetToPref(Constants.REQUEST_HISTORY_KEY, requestsHistory);
    }
}
