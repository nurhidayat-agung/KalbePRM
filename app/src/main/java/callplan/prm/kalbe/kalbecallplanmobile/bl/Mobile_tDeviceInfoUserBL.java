package callplan.prm.kalbe.kalbecallplanmobile.bl;


import android.content.Context;

import java.util.List;

import callplan.prm.kalbe.kalbecallplanmobile.model.clsMobile_trDeviceInfoUser;
import callplan.prm.kalbe.kalbecallplanmobile.app.AppDatabase;


/**prm
 * Created by rhezaTesar on 8/23/2016.
 * edited for migration to android room db by ghqp 27-12-2019
 */

public class Mobile_tDeviceInfoUserBL extends clsMainBL {

    public void SaveInfoDevice(String _txtUserId, String _txtDeviceId, Context context){
        AppDatabase appDatabase = AppDatabase.getDatabase(context);
        clsMobile_trDeviceInfoUser _clsMobile_trDeviceInfoUser = new clsMobile_trDeviceInfoUser();
        List<clsMobile_trDeviceInfoUser> ListOfData = appDatabase.daoMobileTrDeviceInfoUser().getbyId(1);
        if(ListOfData.size()==0){
            _clsMobile_trDeviceInfoUser.txtDevice=android.os.Build.DEVICE;
            _clsMobile_trDeviceInfoUser.idDevice=1;
            _clsMobile_trDeviceInfoUser.txtGUI=_txtDeviceId;
            _clsMobile_trDeviceInfoUser.txtModel=android.os.Build.MANUFACTURER+" "+android.os.Build.MODEL;
            _clsMobile_trDeviceInfoUser.txtVersion=System.getProperty("os.version");
            _clsMobile_trDeviceInfoUser.txtUserId=_txtUserId;
            appDatabase.daoMobileTrDeviceInfoUser().insert(_clsMobile_trDeviceInfoUser);
        }else{
            _clsMobile_trDeviceInfoUser=ListOfData.get(0);
            _clsMobile_trDeviceInfoUser.txtDevice=android.os.Build.DEVICE;
            _clsMobile_trDeviceInfoUser.idDevice=1;
            _clsMobile_trDeviceInfoUser.txtGUI=_txtDeviceId;
            _clsMobile_trDeviceInfoUser.txtModel=android.os.Build.MANUFACTURER+" "+android.os.Build.MODEL;
            _clsMobile_trDeviceInfoUser.txtVersion=System.getProperty("os.version");
            _clsMobile_trDeviceInfoUser.txtUserId=_txtUserId;
            appDatabase.daoMobileTrDeviceInfoUser().insert(_clsMobile_trDeviceInfoUser);
        }

    }
    public clsMobile_trDeviceInfoUser GetDeviceActive(Context context){
        AppDatabase appDatabase = AppDatabase.getDatabase(context);
        clsMobile_trDeviceInfoUser _clsMobile_trDeviceInfoUser = new clsMobile_trDeviceInfoUser();
        List<clsMobile_trDeviceInfoUser> ListOfclsMobile_trDeviceInfoUser = appDatabase.daoMobileTrDeviceInfoUser().getAll();
        if(ListOfclsMobile_trDeviceInfoUser != null){
            if(ListOfclsMobile_trDeviceInfoUser.size()>0){
                _clsMobile_trDeviceInfoUser=ListOfclsMobile_trDeviceInfoUser.get(0);
            }else{
                _clsMobile_trDeviceInfoUser = null;
            }
        }else{
            _clsMobile_trDeviceInfoUser=null;
        }
        return  _clsMobile_trDeviceInfoUser;
    }
}
