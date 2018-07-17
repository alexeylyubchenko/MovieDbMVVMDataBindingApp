package alexey.moviedbapp.viewmodels;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import alexey.rxnetwork.RxNetwork;
import alexey.moviedbapp.utils.TestHelperUtils;
import alexey.moviedbapp.data.api.MovieDBApi;
import alexey.moviedbapp.data.viewmodels.DetailsScreenTvShowsViewModel;
import alexey.moviedbapp.testrules.SchedulersTestRule;
import alexey.moviedbapp.views.fragments.View;
import io.reactivex.Observable;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Alexey Lyubchenko.
 */

public class DetailsTvShowsViewModelTests {

    @ClassRule
    public static final SchedulersTestRule scheduleTestRule = new SchedulersTestRule();

    @Mock
    private MovieDBApi movieDBApiMock;

    @Mock
    private View viewMock;

    @Mock
    private Context contextMock;

    @Mock
    private NetworkInfo mockNetworkInfo;

    @Mock
    private ConnectivityManager connectivityManagerMock;

    private RxNetwork rxNetwork;
    private DetailsScreenTvShowsViewModel mDetailsScreenViewModel;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        rxNetwork = new RxNetwork(contextMock, connectivityManagerMock);
        mDetailsScreenViewModel = new DetailsScreenTvShowsViewModel(contextMock, rxNetwork, movieDBApiMock);

        when(connectivityManagerMock.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isConnected()).thenReturn(true);

        mDetailsScreenViewModel.attachView(viewMock);
        mDetailsScreenViewModel.setTvShow(TestHelperUtils.getPreparedTvShow());
    }

    private void mockGetSimilarTvShowsResponse() {
        Mockito.when(movieDBApiMock
                .getSimilarTvShows(Mockito.anyLong(), Mockito.anyString(), Mockito.anyString(), Mockito.anyInt()))
                .thenReturn(Observable.just(TestHelperUtils.getPreparedResponseWithTvShows()));
    }


    @Test
    public void test_GetSimilarTvShows_Succeed() {
        mockGetSimilarTvShowsResponse();

        mDetailsScreenViewModel.fetchSimilarTvShows();

        Assert.assertTrue(mDetailsScreenViewModel.getTvShows().size() > 0);
        Assert.assertEquals(mDetailsScreenViewModel.getTvShows(), TestHelperUtils.getPreparedResponseWithTvShows().getTvShows());

        verify(viewMock).onDataLoadSucceed();
    }

    @Test
    public void test_GetSimilarTvShows_ExpectedError() {
        String error = "Expected error";

        Mockito.when(movieDBApiMock.getSimilarTvShows(Mockito.anyLong(), Mockito.anyString(), Mockito.anyString(), Mockito.anyInt()))
                .thenReturn(Observable.error(new Exception(error)));

        mDetailsScreenViewModel.fetchSimilarTvShows();

        Assert.assertTrue(mDetailsScreenViewModel.getTvShows().size() == 0);

        verify(viewMock).showMessage(error);
    }




}
