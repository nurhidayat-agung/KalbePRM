package callplan.prm.kalbe.kalbecallplanmobile.bl;

import android.database.sqlite.SQLiteDatabase;
import android.icu.util.Calendar;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Select;

import java.util.Date;
import java.util.List;

import callplan.prm.kalbe.callplanlibrary.common.clsMobile_mConfig;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_trUserLogin;

/**
 * Created by rhezaTesar on 9/21/2016.
 */

public class Mobile_trUserLoginBL {
    public clsMobile_trUserLogin CheckUserActive(){
        List<clsMobile_trUserLogin> ListOfUserLogin=null;
        clsMobile_trUserLogin dt=new clsMobile_trUserLogin();
        clsMobile_trUserLogin _clsMobile_trUserLogin=new clsMobile_trUserLogin();
        ListOfUserLogin=new Select().from(clsMobile_trUserLogin.class).where(_clsMobile_trUserLogin.txtConstdtLastLogin +"=?",new clsMainBL().GetDateNow()).execute();
        if(ListOfUserLogin.size()!=0){
            dt=ListOfUserLogin.get(0);
            /*
            String txtDate=ListOfUserLogin.get(0).dtLastLogin;
            if(txtDate.equals(new clsMainBL().GetDateNow())){
                dt=ListOfUserLogin.get(0);
            }
            */
        }
        return dt;
    }
    public clsMobile_trUserLogin CheckUserLogin(){
        List<clsMobile_trUserLogin> ListOfUserLogin=null;
        clsMobile_trUserLogin dt=new clsMobile_trUserLogin();
        clsMobile_trUserLogin _clsMobile_trUserLogin=new clsMobile_trUserLogin();
        ListOfUserLogin=new Select().from(clsMobile_trUserLogin.class).execute();
        if(ListOfUserLogin.size()!=0){
            dt=ListOfUserLogin.get(0);
            /*
            String txtDate=ListOfUserLogin.get(0).dtLastLogin;
            if(txtDate.equals(new clsMainBL().GetDateNow())){
                dt=ListOfUserLogin.get(0);
            }
            */
        }
        return dt;
    }
    public List<clsMobile_trUserLogin> getAll(){
        List<clsMobile_trUserLogin> ListOfUserLogin=null;
        clsMobile_trUserLogin _clsMobile_trUserLogin=new clsMobile_trUserLogin();
        ListOfUserLogin=new Select().from(clsMobile_trUserLogin.class).execute();
        return ListOfUserLogin;
    }
    public void Mobile_trUserLoginTable(){
        SQLiteDatabase db = ActiveAndroid.getDatabase();
        db.execSQL("CREATE TABLE if not exists  Mobile_trUserLogin(Id INT PRIMARY KEY NOT NULL,idUserLogin   INT,IntCabangID TEXT,txtGUI  TEXT, txtUserID TEXT, txtRoleID TEXT, txtRoleName TEXT, txtPathImage TEXT, txtUserName TEXT, txtName TEXT, txtEmail TEXT, txtEmpID TEXT, txtBranchCode TEXT, txtOutletCode TEXT, txtOutletName TEXT, dtLastLogin TEXT, txtDeviceId TEXT, dtCheckIn TEXT, dtCheckOut TEXT, dtLogOut TEXT) ;");
        db.close();
    }
}
