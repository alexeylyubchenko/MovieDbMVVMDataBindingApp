package alexey.moviedbapp.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

import alexey.rxnetwork.RxNetwork;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by Alexey Lyubchenko.
 */

@RunWith(RobolectricTestRunner.class)
public class NetworkStateTests {

    @Mock
    private NetworkInfo mockNetworkInfo;

    private RxNetwork mRxNetwork;

    @Mock
    private Context mContext;

    @Mock
    private ConnectivityManager mConnectivityManager;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mRxNetwork = new RxNetwork(mContext, mConnectivityManager);
        when(mConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
    }

    @Test
    public void testNetworkConnection_isConnected() {
        when(mockNetworkInfo.isConnected()).thenReturn(true);

        boolean isConnected = mRxNetwork.isConnectedToInternet();

        assertEquals(isConnected, true);
        Mockito.verify(mConnectivityManager).getActiveNetworkInfo();
        Mockito.verify(mockNetworkInfo).isConnected();
    }

    @Test
    public void testNetworkConnection_isDisconnected() {
        when(mockNetworkInfo.isConnected()).thenReturn(false);

        boolean isConnected = mRxNetwork.isConnectedToInternet();

        assertEquals(isConnected, false);
        Mockito.verify(mConnectivityManager).getActiveNetworkInfo();
        Mockito.verify(mockNetworkInfo).isConnected();
    }

}
