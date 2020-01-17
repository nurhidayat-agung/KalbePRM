package callplan.prm.kalbe.kalbecallplanmobile.addons.util;

import android.content.Context;
import android.content.SharedPreferences;

import callplan.prm.kalbe.kalbecallplanmobile.model.clsHardCode;

public class SharedPref {

    private SharedPreferences sp;
    private SharedPreferences.Editor spe;

    public SharedPref(Context context) {
        sp = context.getSharedPreferences(clsHardCode.appName,Context.MODE_PRIVATE);
        spe = sp.edit();
    }

}
