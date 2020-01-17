package callplan.prm.kalbe.kalbecallplanmobile.app;

import android.app.Application;
import android.content.Context;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class AppPRM extends Application {
    private Scheduler scheduler;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric fabric = new Fabric.Builder(this)
                .kits(new Crashlytics())
                .debuggable(true)
                .build();
        Fabric.with(fabric);
    }

    private static AppPRM get(Context context) {
        return (AppPRM) context.getApplicationContext();
    }


    public static AppPRM create(Context context) {
        return AppPRM.get(context);
    }

    public Scheduler subscribeScheduler() {
        if (scheduler == null) {
            scheduler = Schedulers.io();
        }

        return scheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }
}
