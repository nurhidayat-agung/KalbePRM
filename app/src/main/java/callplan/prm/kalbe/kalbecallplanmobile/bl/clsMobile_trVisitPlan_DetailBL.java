package callplan.prm.kalbe.kalbecallplanmobile.bl;

import android.database.sqlite.SQLiteDatabase;

import com.activeandroid.ActiveAndroid;

/**
 * Created by rhezaTesar on 4/7/2017.
 */

public class clsMobile_trVisitPlan_DetailBL {
    public void clsMobile_trVisitPlan_DetailTable(){
        SQLiteDatabase db = ActiveAndroid.getDatabase();
        db.execSQL("CREATE TABLE if not exists  Mobile_trVisitPlan_Detail (Id INT PRIMARY KEY NOT NULL,txtGUI_Detail   TEXT,intVisitPlan_DetailID TEXT,intVisitPlan_HeaderID  TEXT, intLOBID TEXT, txtLOBName TEXT, intCategoryID TEXT, txtCategoryName TEXT, txtNoKode TEXT, txtDescription TEXT, txtLongitude_Knis TEXT, txtLatitude_Knis TEXT, txtAccuracy_Knis TEXT, intStatus TEXT, dtPlanDate TEXT,  intBobot TEXT, dtActualDate TEXT, intJumlahPreNC TEXT, intSubmit TEXT, bitStatus TEXT,  txtValidationNo TEXT, dtValidated TEXT, bitUnplan TEXT, intPartnerID TEXT, intPartnerIDCheckIn TEXT) ;");
        db.close();
    }
}
