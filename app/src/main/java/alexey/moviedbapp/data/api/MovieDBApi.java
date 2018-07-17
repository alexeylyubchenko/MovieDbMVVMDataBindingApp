package alexey.moviedbapp.data.api;

import alexey.moviedbapp.data.models.Response;
import alexey.moviedbapp.utils.Constants;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
/**
 * Created by Alexey Lyubchenko.
 */

public interface MovieDBApi {

    @GET(Constants.API_GET_SEARCH_SHOWS_CMD)
    Observable<Response> searchTvShows (@Query(Constants.API_KEY_PARAM) String apiKey,
                                            @Query(Constants.API_PARAM_LANG) String language,
                                            @Query(Constants.API_PARAM_QUERY) String queryText,
                                            @Query(Constants.API_PARAM_PAGE) int page);

    @GET(Constants.API_GET_SIMILAR_CMD)
    Observable<Response> getSimilarTvShows (@Path(Constants.API_PARAM_TV_ID) long tvShowId,
                                            @Query(Constants.API_KEY_PARAM) String apiKey,
                                            @Query(Constants.API_PARAM_LANG) String language,
                                            @Query(Constants.API_PARAM_PAGE) int page);
}
