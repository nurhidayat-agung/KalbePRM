package kalbenutritionals.bridgeapi.BL;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

import kalbenutritionals.bridgeapi.common.clsDataLogin;
import kalbenutritionals.bridgeapi.common.clsMobile_MBranch;
import kalbenutritionals.bridgeapi.common.clsMobile_MLOB;
import kalbenutritionals.bridgeapi.common.clsMobile_UserJabatanAPI;
import kalbenutritionals.bridgeapi.common.clsMobile_trUserLoginAPI;

/**
 * Created by arick.anjasmara on 1/2/2017.
 */

public class Mobile_trUserLoginAPI_BL {

    public clsDataLogin getDataLogin(Activity activity, Uri CONTENT_URI){

        clsMobile_trUserLoginAPI dataLogin = new clsMobile_trUserLoginAPI();

        clsDataLogin _clsDataLogin=new clsDataLogin();

        List<clsMobile_MLOB> dataLoginLOB = null;

        List<clsMobile_MBranch> dataLoginBranch = null;

        List<clsMobile_UserJabatanAPI> dataclsMobile_UserJabatanAPI = null;

        Cursor userLogin = activity.getContentResolver().query(CONTENT_URI, null, null, null, "Mobile_trUserLogin");

        Cursor curlob = activity.getContentResolver().query(CONTENT_URI, null, null, null, "mLOB");

        Cursor curBranch = activity.getContentResolver().query(CONTENT_URI, null, null, null, "Mobile_mBranch");

        Cursor curUserJabatan = activity.getContentResolver().query(CONTENT_URI, null, null, null, "Mobile_UserJabatan");

        if (!userLogin.moveToFirst()) {
            dataLogin = new clsMobile_trUserLoginAPI();
        } else {
            do {
                dataLogin.setIdUserLogin(userLogin.getString(userLogin.getColumnIndex("idUserLogin")));
                dataLogin.setIntCabangID(userLogin.getString(userLogin.getColumnIndex("IntCabangID")));
                dataLogin.setTxtGUI(userLogin.getString(userLogin.getColumnIndex("txtGUI")));
                dataLogin.setTxtUserID(userLogin.getString(userLogin.getColumnIndex("txtUserID")));
                dataLogin.setTxtRoleID(userLogin.getString(userLogin.getColumnIndex("txtRoleID")));
                dataLogin.setTxtRoleName(userLogin.getString(userLogin.getColumnIndex("txtRoleName")));
                dataLogin.setTxtPathImage(userLogin.getString(userLogin.getColumnIndex("txtPathImage")));
                dataLogin.setTxtUserName(userLogin.getString(userLogin.getColumnIndex("txtUserName")));
                dataLogin.setTxtName(userLogin.getString(userLogin.getColumnIndex("txtName")));
                dataLogin.setTxtEmail(userLogin.getString(userLogin.getColumnIndex("txtEmail")));
                dataLogin.setTxtEmpID(userLogin.getString(userLogin.getColumnIndex("txtEmpID")));
                dataLogin.setTxtBranchCode(userLogin.getString(userLogin.getColumnIndex("txtBranchCode")));
                dataLogin.setTxtOutletCode(userLogin.getString(userLogin.getColumnIndex("txtOutletCode")));
                dataLogin.setTxtOutletName(userLogin.getString(userLogin.getColumnIndex("txtOutletName")));
                dataLogin.setDtLastLogin(userLogin.getString(userLogin.getColumnIndex("dtLastLogin")));
                dataLogin.setTxtDeviceId(userLogin.getString(userLogin.getColumnIndex("txtDeviceId")));
                dataLogin.setDtCheckIn(userLogin.getString(userLogin.getColumnIndex("dtCheckIn")));
                dataLogin.setDtCheckOut(userLogin.getString(userLogin.getColumnIndex("dtCheckOut")));
                dataLogin.setDtLogOut(userLogin.getString(userLogin.getColumnIndex("dtLogOut")));
            } while (userLogin.moveToNext());
        }
        if(!curUserJabatan.moveToFirst()){
            dataclsMobile_UserJabatanAPI=null;
        }else{
            dataclsMobile_UserJabatanAPI=new ArrayList<clsMobile_UserJabatanAPI>();
            do {
                clsMobile_UserJabatanAPI dt=new clsMobile_UserJabatanAPI();
                dt.setBitActive(curUserJabatan.getString(curUserJabatan.getColumnIndex("BitActive")));
                dt.setBitPrimary(curUserJabatan.getString(curUserJabatan.getColumnIndex("BitPrimary")));
                dt.setIntJabatanID(curUserJabatan.getString(curUserJabatan.getColumnIndex("IntJabatanID")));
                dt.setTxtJabatanDesc(curUserJabatan.getString(curUserJabatan.getColumnIndex("TxtJabatanDesc")));
                dt.setTxtJabatanName(curUserJabatan.getString(curUserJabatan.getColumnIndex("TxtJabatanName")));
                dt.setTxtLimit(curUserJabatan.getString(curUserJabatan.getColumnIndex("txtLimit")));
                dataclsMobile_UserJabatanAPI.add(dt);
            } while (curUserJabatan.moveToNext());
        }
        if (!curlob.moveToFirst()) {
            dataLoginLOB = null;
        } else {
            dataLoginLOB=new ArrayList<clsMobile_MLOB>();
            do {
                clsMobile_MLOB dt=new clsMobile_MLOB();
                dt.setIntLOBID(curlob.getString(curlob.getColumnIndex("intLOBID")));
                dt.setTxtLOBName(curlob.getString(curlob.getColumnIndex("txtLOBName")));
                dt.setTxtLOBDescription(curlob.getString(curlob.getColumnIndex("txtLOBDescription")));
                dataLoginLOB.add(dt);
            } while (curlob.moveToNext());
        }

        if (!curBranch.moveToFirst()) {
            dataLoginBranch = null;
        } else {
            dataLoginBranch=new ArrayList<clsMobile_MBranch>();
            do {
                clsMobile_MBranch dt=new clsMobile_MBranch();
                dt.setIntCabangID(curBranch.getString(curBranch.getColumnIndex("IntCabangID")));
                dt.setTxtKodeCabang(curBranch.getString(curBranch.getColumnIndex("TxtKodeCabang")));
                dt.setTxtNamaCabang(curBranch.getString(curBranch.getColumnIndex("TxtNamaCabang")));
                dataLoginBranch.add(dt);
            } while (curlob.moveToNext());
        }
        _clsDataLogin.setClsMobile_trUserLoginAPI(dataLogin);
        _clsDataLogin.setClsMobile_MLOB(dataLoginLOB);
        _clsDataLogin.setClsMobile_MBranch(dataLoginBranch);
        _clsDataLogin.setClsMobile_UserJabatanAPI(dataclsMobile_UserJabatanAPI);
        return _clsDataLogin;
    }
}

