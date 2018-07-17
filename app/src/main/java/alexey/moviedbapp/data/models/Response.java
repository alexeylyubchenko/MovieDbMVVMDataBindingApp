package alexey.moviedbapp.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import alexey.moviedbapp.utils.Constants;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Alexey Lyubchenko.
 */

public class Response {

    @Getter @Setter
    @SerializedName(Constants.API_JSON_PARAM_TOTAL_PAGES)
    int totalPages;

    @Getter @Setter
    @SerializedName(Constants.API_JSON_PARAM_PAGE)
    int currentPage;

    @Getter @Setter
    @SerializedName(Constants.API_JSON_PARAM_RESULTS)
    List<TvShow> tvShows;

}
