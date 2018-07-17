package alexey.moviedbapp.utils;

import java.util.ArrayList;
import java.util.List;

import alexey.moviedbapp.data.models.Response;
import alexey.moviedbapp.data.models.TvShow;

/**
 * Created by Alexey Lyubchenko.
 */

public class TestHelperUtils {

    private static Response response;
    private static TvShow tvShow;

    public static Response getPreparedResponseWithTvShows() {
        if(response == null) {
            response = new Response();
            response.setCurrentPage(1);
            response.setTotalPages(3);

            List<TvShow> shows = new ArrayList<>();

            for(int i = 1; i < 5; i++) {
                TvShow show = new TvShow(i, "Show " + i, 7.6f,
                        "this is show " + i, "https://someurl.com/" + i);
                shows.add(show);
            }

            response.setTvShows(shows);
        }

        return response;
    }

    public static TvShow getPreparedTvShow() {
        if(tvShow == null) {
            tvShow = new TvShow(11, "Show 11", 7.7f,
                    "this is show 11", "https://someurl.com/11");
        }

        return tvShow;
    }


}
