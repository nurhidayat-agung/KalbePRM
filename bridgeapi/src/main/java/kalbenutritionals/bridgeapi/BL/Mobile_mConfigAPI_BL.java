package kalbenutritionals.bridgeapi.BL;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

import kalbenutritionals.bridgeapi.common.clsMobile_mConfigAPI;

/**
 * Created by arick.anjasmara on 12/30/2016.
 */

public class Mobile_mConfigAPI_BL {
    public String getValue(int id, Activity activity, Uri CONTENT_URI) {
        String txtValue = "";
        List<clsMobile_mConfigAPI> listData = new ArrayList<>();

        Cursor c = activity.getContentResolver().query(CONTENT_URI, null, "idConfig=?", new String[] {String.valueOf(id)}, "Mobile_mConfig");

        if (!c.moveToFirst()) {
            listData = new ArrayList<>();
        } else {
            do {
                clsMobile_mConfigAPI data = new clsMobile_mConfigAPI();

                data.setIdConfig(c.getString(c.getColumnIndex("idConfig")));
                data.setTxtName(c.getString(c.getColumnIndex("txtName")));
                data.setTxtValue(c.getString(c.getColumnIndex("txtValue")));
                data.setTxtDefaultValue(c.getString(c.getColumnIndex("txtDefaultValue")));
                listData.add(data);
            } while (c.moveToNext());
        }

        if (listData != null) {
            if (listData.get(0).getTxtValue().equals("")) {
                txtValue = listData.get(0).getTxtDefaultValue();
            } else {
                txtValue = listData.get(0).getTxtValue();
            }
        }
        return txtValue;
    }
}
