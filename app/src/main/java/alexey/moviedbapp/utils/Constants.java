package alexey.moviedbapp.utils;

import android.content.Context;

import alexey.moviedbapp.R;

/**
 * Created by Alexey Lyubchenko.
 */

public class Constants {

    //fragments
    public static final String FRAGMENT_MAINSCREEN__TAG = "FRAGMENT_MAINSCREEN";
    public static final String FRAGMENT_DETAILS_TAG = "FRAGMENT_DETAILS";
    public static final String FRAGMENT_HISTORY_TAG = "FRAGMENT_HISTORY";
    public static final String SHOWS_ARG_KEY = "SHOWS_ARG_";
    public static final int FRAGMENT_PLACE_HOLDER_ID = R.id.frame_holder;


    //on save instance state and restoring keys
    public static final String SAVED_STATE_CURRENT_PAGE_KEY = "saved_state_current_page";
    public static final String SAVED_RECYCLER_VIEW_STATE = "saved_recycler_view_state";
    public static final String SAVED_STATE_DATA_LIST = "saved_state_data_list";

    public static final int  PERMISSION_REQUEST_CODE = 1;
    public static final String[] permissions = {};


    //System
    public static final String SHARED_PREF_NAME = "tv_show_app_pref";
    public static final int SHARED_PREF_MODE = Context.MODE_PRIVATE;
    public static final String REQUEST_HISTORY_KEY = "REQUEST_HISTORY";
    public static final int NETWORK_TIMEOUT = 60;
    public static final int TYPE_TIMEOUT = 700;

    // API
    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String IMAGE_URL = "https://image.tmdb.org/t/p/w500";
    public static final String API_GET_SIMILAR_CMD = "tv/{tv_id}/similar";
    public static final String API_GET_SEARCH_SHOWS_CMD = "search/tv";

    public static final String API_KEY_PARAM = "api_key";
    public static final String API_KEY = "7b8de86628766506880a1228d148da3a";

    public static final String API_PARAM_LANG = "language";
    public static final String API_PARAM_LANG_VALUE = "en-US";

    public static final String API_PARAM_PAGE = "page";
    public static final String API_PARAM_TV_ID = "tv_id";

    public static final String API_PARAM_QUERY = "query";

    public static final String API_JSON_PARAM_PAGE = "page";
    public static final String API_JSON_PARAM_TOTAL_PAGES = "total_pages";
    public static final String API_JSON_PARAM_RESULTS = "results";
    public static final String API_JSON_PARAM_RESULTS_NAME = "original_name";
    public static final String API_JSON_PARAM_RESULTS_ID = "id";
    public static final String API_JSON_PARAM_RESULTS_VOTE_AVG = "vote_average";
    public static final String API_JSON_PARAM_RESULTS_OVERVIEW = "overview";
    public static final String API_JSON_PARAM_RESULTS_BACKDROP = "backdrop_path";
    public static final String API_JSON_PARAM_RESULTS_POSTER = "poster_path";
}
