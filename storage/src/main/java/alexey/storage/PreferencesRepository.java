package alexey.storage;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import java.util.Set;

/**
 * Created by Alexey Lyubchenko.
 */

public class PreferencesRepository {
    private SharedPreferences mLocalSharedPreferences;
    private SharedPreferences.Editor mLocalSharedPreferencesEditor;

    public PreferencesRepository(@NonNull SharedPreferences sharedPreferences) {
        mLocalSharedPreferences = sharedPreferences;
    }

    public void saveStringToPref(String key, String value) {
        mLocalSharedPreferencesEditor = mLocalSharedPreferences.edit();
        mLocalSharedPreferencesEditor.putString(key, value);
        mLocalSharedPreferencesEditor.apply();
    }

    public String getSavedStringFromPref(String key, String defaultValue) {
        return mLocalSharedPreferences.getString(key, defaultValue);
    }

    public void saveIntegerToPref(String key, int value) {
        mLocalSharedPreferencesEditor = mLocalSharedPreferences.edit();
        mLocalSharedPreferencesEditor.putInt(key, value);
        mLocalSharedPreferencesEditor.apply();
    }

    public int getSavedIntegerFromPref(String key, int defaultValue) {
        return mLocalSharedPreferences.getInt(key, defaultValue);
    }

    public void saveBooleanToPref(String key, boolean value) {
        mLocalSharedPreferencesEditor = mLocalSharedPreferences.edit();
        mLocalSharedPreferencesEditor.putBoolean(key, value);
        mLocalSharedPreferencesEditor.apply();
    }

    public boolean getSavedIntegerFromPref(String key, boolean defaultValue) {
        return mLocalSharedPreferences.getBoolean(key, defaultValue);
    }

    public void saveLongToPref(String key, long value) {
        mLocalSharedPreferencesEditor = mLocalSharedPreferences.edit();
        mLocalSharedPreferencesEditor.putLong(key, value);
        mLocalSharedPreferencesEditor.apply();
    }

    public long getSavedLongFromPref(String key, long defaultValue) {
        return mLocalSharedPreferences.getLong(key, defaultValue);
    }

    public void saveFloatToPref(String key, float value) {
        mLocalSharedPreferencesEditor = mLocalSharedPreferences.edit();
        mLocalSharedPreferencesEditor.putFloat(key, value);
        mLocalSharedPreferencesEditor.apply();
    }

    public float getSavedFloatFromPref(String key, float defaultValue) {
        return mLocalSharedPreferences.getFloat(key, defaultValue);
    }

    public void saveStringSetToPref(String key, Set<String> set) {
        mLocalSharedPreferencesEditor = mLocalSharedPreferences.edit();
        mLocalSharedPreferencesEditor.putStringSet(key, set);
        mLocalSharedPreferencesEditor.apply();
    }

    public Set<String> getSavedStringSetFromPref(String key) {
        return mLocalSharedPreferences.getStringSet(key, null);
    }
}
