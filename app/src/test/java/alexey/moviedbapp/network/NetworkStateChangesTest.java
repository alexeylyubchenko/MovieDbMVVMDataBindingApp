package alexey.moviedbapp.network;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowNetworkInfo;

import java.util.List;

import alexey.rxnetwork.RxNetwork;
import alexey.moviedbapp.BuildConfig;
import io.reactivex.observers.TestObserver;


/**
 * Created by Alexey Lyubchenko.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 23)
public class NetworkStateChangesTest {

    private RxNetwork mRxNetwork;

    @Mock
    private ConnectivityManager mConnectivityManager;
    private Context mContext;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mContext = RuntimeEnvironment.application.getApplicationContext();
        mRxNetwork = new RxNetwork(mContext, mConnectivityManager);
    }

    @Test
    public void testNetworkSateChangedToConnected() {
        setNetworkState(true);
        TestObserver<Boolean> testObserver = prepareTestObserver();
        triggerBroadcastAndCheckEvents(testObserver, true);
    }

    @Test
    public void testNetworkSateChangedToDisconnected() {
        setNetworkState(false);
        TestObserver<Boolean> testObserver = prepareTestObserver();
        triggerBroadcastAndCheckEvents(testObserver, false);
    }

    private void triggerBroadcastAndCheckEvents(TestObserver<Boolean> testObserver, boolean connected) {
        //Trigger BroadcastReceiver
        RuntimeEnvironment.application.sendBroadcast(new Intent(ConnectivityManager.CONNECTIVITY_ACTION));

        testObserver.assertNoErrors();

        List eventsList = testObserver.getEvents().get(0);
        Assert.assertTrue(eventsList.size() > 0);

        Assert.assertEquals((boolean) eventsList.get(0), connected);
    }

    private TestObserver<Boolean> prepareTestObserver() {
        TestObserver<Boolean> testObserver = new TestObserver<>();
        mRxNetwork.getNetworkStateUpdates().subscribe(testObserver);

        return testObserver;
    }

    private void setNetworkState(boolean connected) {
        NetworkInfo netInfoShadow = ShadowNetworkInfo.newInstance(connected ?
                        NetworkInfo.DetailedState.CONNECTED : NetworkInfo.DetailedState.DISCONNECTED,
                        ConnectivityManager.TYPE_WIFI, 0, true, connected);

        Mockito.when(mConnectivityManager.getActiveNetworkInfo()).thenReturn(netInfoShadow);
    }

}
