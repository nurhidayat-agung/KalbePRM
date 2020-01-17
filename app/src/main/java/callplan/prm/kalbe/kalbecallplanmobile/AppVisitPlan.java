package callplan.prm.kalbe.kalbecallplanmobile;

import android.app.Application;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

/**
 * Created by rhezaTesar on 3/20/2017.
 */
public class AppVisitPlan extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // ActiveAndroid.initialize(this);
        Fabric fabric = new Fabric.Builder(this)
                .kits(new Crashlytics())
                .debuggable(true)
                .build();
        Fabric.with(fabric);
    }
}
