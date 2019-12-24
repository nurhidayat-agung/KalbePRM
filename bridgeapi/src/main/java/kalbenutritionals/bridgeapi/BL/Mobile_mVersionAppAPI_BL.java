package kalbenutritionals.bridgeapi.BL;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

import kalbenutritionals.bridgeapi.common.clsMobile_mVersionAppAPI;

/**
 * Created by arick.anjasmara on 12/30/2016.
 */

public class Mobile_mVersionAppAPI_BL {
    
    public List<clsMobile_mVersionAppAPI> getAllData(Activity activity, Uri CONTENT_URI){
        List<clsMobile_mVersionAppAPI> listData = new ArrayList<>();

        Cursor c = activity.getContentResolver().query(CONTENT_URI, null, null, null, "Mobile_mVersionApp");

        if (!c.moveToFirst()) {
            listData = new ArrayList<>();
        } else {
            do {
                clsMobile_mVersionAppAPI data = new clsMobile_mVersionAppAPI();
                
                data.setIdVersion(c.getString(c.getColumnIndex("idVersion")));
                data.setTxtGUI(c.getString(c.getColumnIndex("txtGUI")));
                data.setTxtNameApp(c.getString(c.getColumnIndex("txtNameApp")));
                data.setTxtVersion(c.getString(c.getColumnIndex("txtVersion")));
                data.setTxtFile(c.getString(c.getColumnIndex("txtFile")));
                listData.add(data);
            } while (c.moveToNext());
        }
        
        return listData;
    }
}
