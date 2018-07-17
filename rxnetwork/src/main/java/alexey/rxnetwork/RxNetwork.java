package alexey.rxnetwork;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import io.reactivex.Observable;

/**
 * Created by Alexey Lyubchenko.
 */

public class RxNetwork {

    private ConnectivityManager mConnectivityManager;
    private Context mContext;

    public RxNetwork(Context context, ConnectivityManager cm) {
        this.mContext = context;
        this.mConnectivityManager = cm;
    }

    public boolean isConnectedToInternet() {
        if(mConnectivityManager != null) {
            NetworkInfo networkInfo = mConnectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnected();
        }

        return false;
    }

    public Observable<Boolean> getNetworkStateUpdates() {
        return Observable.create( emitter -> {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);

            final BroadcastReceiver networkStateBroadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    if(!emitter.isDisposed()) {
                        emitter.onNext(isConnectedToInternet());
                    }
                }
            };

            mContext.registerReceiver(networkStateBroadcastReceiver, intentFilter);

            emitter.setCancellable(() -> {
                try {
                    mContext.unregisterReceiver(networkStateBroadcastReceiver);
                } catch (Exception exp) {
                    exp.printStackTrace();
                }

            });
        });
    }

}
