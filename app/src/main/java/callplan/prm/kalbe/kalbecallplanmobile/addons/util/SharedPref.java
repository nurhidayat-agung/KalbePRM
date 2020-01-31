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

    public boolean getBool(String key)
    {
        return sp.getBoolean(key,false);
    }

    public boolean setBool(String key, boolean value)
    {
        spe.putBoolean(key, value);
        return spe.commit();
    }

    public String getString(String key)
    {
        return sp.getString(key,"");
    }

    public boolean setString(String key, String value)
    {
        spe.putString(key,value);
        return spe.commit();
    }

    public Integer getInt(String key)
    {
        return sp.getInt(key,0);
    }

    public boolean setInt(String key, int value)
    {
        spe.putInt(key,value);
        return spe.commit();
    }

    public float getDouble(String key)
    {
        return sp.getFloat(key,0);
    }

    public boolean setFloat(String key, float value)
    {
        spe.putFloat(key,value);
        return spe.commit();
    }

}
