package callplan.prm.kalbe.kalbecallplanmobile.bl;


import com.activeandroid.query.Select;

import java.util.List;

import callplan.prm.kalbe.callplanlibrary.common.clsMobile_trDeviceInfoUser;

/**prm
 * Created by rhezaTesar on 8/23/2016.
 */

public class Mobile_tDeviceInfoUserBL extends  clsMainBL {
    public void SaveInfoDevice(String _txtUserId,String _txtDeviceId){
        clsMobile_trDeviceInfoUser _clsMobile_trDeviceInfoUser=new clsMobile_trDeviceInfoUser();
        List<clsMobile_trDeviceInfoUser> ListOfData=new Select().from(clsMobile_trDeviceInfoUser.class).where("id=?",1).execute();
        if(ListOfData.size()==0){
            _clsMobile_trDeviceInfoUser.txtDevice=android.os.Build.DEVICE;
            _clsMobile_trDeviceInfoUser.idDevice=1;
            _clsMobile_trDeviceInfoUser.txtGUI=_txtDeviceId;
            _clsMobile_trDeviceInfoUser.txtModel=android.os.Build.MANUFACTURER+" "+android.os.Build.MODEL;
            _clsMobile_trDeviceInfoUser.txtVersion=System.getProperty("os.version");
            _clsMobile_trDeviceInfoUser.txtUserId=_txtUserId;
            _clsMobile_trDeviceInfoUser.save();
        }else{
            _clsMobile_trDeviceInfoUser=ListOfData.get(0);
            _clsMobile_trDeviceInfoUser.txtDevice=android.os.Build.DEVICE;
            _clsMobile_trDeviceInfoUser.idDevice=1;
            _clsMobile_trDeviceInfoUser.txtGUI=_txtDeviceId;
            _clsMobile_trDeviceInfoUser.txtModel=android.os.Build.MANUFACTURER+" "+android.os.Build.MODEL;
            _clsMobile_trDeviceInfoUser.txtVersion=System.getProperty("os.version");
            _clsMobile_trDeviceInfoUser.txtUserId=_txtUserId;
            _clsMobile_trDeviceInfoUser.save();
        }

    }
    public clsMobile_trDeviceInfoUser GetDeviceActive(){
        clsMobile_trDeviceInfoUser _clsMobile_trDeviceInfoUser=new clsMobile_trDeviceInfoUser();

        List<clsMobile_trDeviceInfoUser> ListOfclsMobile_trDeviceInfoUser =new Select().from(clsMobile_trDeviceInfoUser.class).execute();
        if(ListOfclsMobile_trDeviceInfoUser != null){
            if(ListOfclsMobile_trDeviceInfoUser.size()>0){
                _clsMobile_trDeviceInfoUser=ListOfclsMobile_trDeviceInfoUser.get(0);
            }else{
                _clsMobile_trDeviceInfoUser=null;
            }
        }else{
            _clsMobile_trDeviceInfoUser=null;
        }
        return  _clsMobile_trDeviceInfoUser;
    }
}
