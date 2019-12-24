package kalbenutritionals.bridgeapi.BL;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

import kalbenutritionals.bridgeapi.common.clsMobile_trDeviceInfoUserAPI;

/**
 * Created by arick.anjasmara on 12/30/2016.
 */

public class Mobile_trDeviceInfoUserAPI_BL {
    
    public List<clsMobile_trDeviceInfoUserAPI> getAllData(Activity activity, Uri CONTENT_URI){
        List<clsMobile_trDeviceInfoUserAPI> listData = new ArrayList<>();

        Cursor c = activity.getContentResolver().query(CONTENT_URI, null, null, null, "Mobile_trDeviceInfoUser");

        if (!c.moveToFirst()) {
            listData = new ArrayList<>();
        } else {
            do {
                clsMobile_trDeviceInfoUserAPI data = new clsMobile_trDeviceInfoUserAPI();
                
                data.setIdDevice(c.getString(c.getColumnIndex("idDevice")));
                data.setTxtGUI(c.getString(c.getColumnIndex("txtGUI")));
                data.setTxtVersion(c.getString(c.getColumnIndex("txtVersion")));
                data.setTxtDevice(c.getString(c.getColumnIndex("txtDevice")));
                data.setTxtModel(c.getString(c.getColumnIndex("txtModel")));
                data.setTxtUserId(c.getString(c.getColumnIndex("txtUserId")));
                listData.add(data);
            } while (c.moveToNext());
        }
        
        return listData;
    }
}
