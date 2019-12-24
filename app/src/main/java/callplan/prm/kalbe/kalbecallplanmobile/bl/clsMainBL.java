package callplan.prm.kalbe.kalbecallplanmobile.bl;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TimeZone;
import java.util.UUID;

import callplan.prm.kalbe.callplanlibrary.ENUM.enum_VisitPlanCategory;
import callplan.prm.kalbe.callplanlibrary.ENUM.enum_mconfig;
import callplan.prm.kalbe.callplanlibrary.common.MultipartUtility;
import callplan.prm.kalbe.callplanlibrary.common.clsHardCode;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_MBranch;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_MPartnerProfile;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_UserJabatan;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_mBinaryFile;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_mLOB;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_mVersionApp;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_mVisitPlanCategory;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_mVisitPlanCategoryDetail;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_trDeviceInfoUser;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_trPOA;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_trUserLogin;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_trVisitPlan_Detail;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_trVisitPlan_Detail_Item;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_trVisitPlan_Header;
import callplan.prm.kalbe.callplanlibrary.common.clsSpinner;
import callplan.prm.kalbe.callplanlibrary.common.clsSwipeList;
import callplan.prm.kalbe.kalbecallplanmobile.AppAdapter;
import callplan.prm.kalbe.kalbecallplanmobile.R;
import callplan.prm.kalbe.kalbecallplanmobile.addons.zoomview.CustomZoomView;
import edu.swu.pulltorefreshswipemenulistview.library.PullToRefreshSwipeMenuListView;
import edu.swu.pulltorefreshswipemenulistview.library.swipemenu.bean.SwipeMenu;
import edu.swu.pulltorefreshswipemenulistview.library.swipemenu.bean.SwipeMenuItem;
import edu.swu.pulltorefreshswipemenulistview.library.swipemenu.interfaces.SwipeMenuCreator;
import kalbenutritionals.bridgeapi.common.clsDataLogin;
import kalbenutritionals.bridgeapi.common.clsMobile_MLOB;
import kalbenutritionals.bridgeapi.common.clsMobile_UserJabatanAPI;
import kalbenutritionals.bridgeapi.common.clsMobile_trUserLoginAPI;

/**
 * Created by rhezaTesar on 8/23/2016.
 */

public class clsMainBL {
    public void zoomImage (Bitmap bitmap, Context context){
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        final View promptView = layoutInflater.inflate(R.layout.custom_zoom_image, null);
        final TextView tv_desc = (TextView) promptView.findViewById(R.id.desc_act);

        CustomZoomView customZoomView ;
        customZoomView = (CustomZoomView)promptView.findViewById(R.id.customImageVIew1);
        customZoomView.setBitmap(bitmap);

//        tv_desc.setText(description);

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(promptView);
        alertDialogBuilder
                .setCancelable(false)
                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        final AlertDialog alertD = alertDialogBuilder.create();
        alertD.show();
    }
    public int getIndexListOfclsSpinnerByname(String pId,String pName,List<clsSpinner> ListSpinnerData)
    {
        for(clsSpinner _item : ListSpinnerData)
        {
            if(pId.equals("")==false){
                if(_item.getID().equals(pId)) {
                    return ListSpinnerData.indexOf(_item);
                }
            }
            if(pName.equals("")==false){
                if(_item.getNama().equals(pName)) {
                    return ListSpinnerData.indexOf(_item);
                }
            }
        }
        return -1;
    }
    public static int CheckValidApps(Context _context){
        int valid =0;
        if(areThereMockPermissionApps(_context)){
            if(isMockSettingsON(_context)){
                valid=1;
            }
        }
        if(isMockSettingsON(_context)){
            valid=1;
        }
        //if(RootUtil.isDeviceRooted() && valid==0){
        //valid=2;
        //}
        if(CheckAutoDateTime(_context)==false && valid==0){
            valid=3;
        }
        return  valid;
    }
    public static boolean areThereMockPermissionApps(Context context) {

        int count = 0;

        PackageManager pm = context.getPackageManager();
        List<ApplicationInfo> packages =
                pm.getInstalledApplications(PackageManager.GET_META_DATA);

        for (ApplicationInfo applicationInfo : packages) {
            try {
                PackageInfo packageInfo = pm.getPackageInfo(applicationInfo.packageName,
                        PackageManager.GET_PERMISSIONS);

                // Get Permissions
                String[] requestedPermissions = packageInfo.requestedPermissions;

                if (requestedPermissions != null) {
                    for (int i = 0; i < requestedPermissions.length; i++) {
                        if (requestedPermissions[i]
                                .equals("android.permission.ACCESS_MOCK_LOCATION")
                                && !applicationInfo.packageName.equals(context.getPackageName())) {
                            count++;
                        }
                    }
                }
            } catch (PackageManager.NameNotFoundException e) {
                Log.e("Error","Got exception " + e.getMessage().toString());
            }
        }

        if (count > 0)
            return true;
        return false;
    }
    public static boolean CheckAutoDateTime(Context context){
        int sdkVersion = Build.VERSION.SDK_INT;
        boolean valid= false;
        if (sdkVersion >=17 ) {
            try {
                if(Settings.Global.getInt(context.getContentResolver(), Settings.Global.AUTO_TIME) == 1)
                {
                    valid=true;
                }
            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
            }
        }else{
            try {
                if(Settings.Global.getInt(context.getContentResolver(), Settings.System.AUTO_TIME) == 1){
                    valid=true;
                }
            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
            }
        }
        return  valid;
    }
    public static boolean isMockSettingsON(Context context) {
        // returns true if mock location enabled, false if not enabled.
        if (Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ALLOW_MOCK_LOCATION).equals("0"))
            return false;
        else

            return true;
    }
    public clsDataLogin getDataLogin(){
        clsMobile_trUserLoginAPI dataLogin = new clsMobile_trUserLoginAPI();

        clsDataLogin _clsDataLogin=new clsDataLogin();

        List<clsMobile_MLOB> dataLoginLOB = null;

        List<kalbenutritionals.bridgeapi.common.clsMobile_MBranch> dataLoginBranch = null;

        List<clsMobile_UserJabatanAPI> dataclsMobile_UserJabatanAPI = null;

        List<clsMobile_trUserLogin> ListDataclsMobile_trUserLogin=new Select().from(clsMobile_trUserLogin.class).execute();

        if(ListDataclsMobile_trUserLogin.size()>0){
            dataLogin=new clsMobile_trUserLoginAPI();
            for (clsMobile_trUserLogin dt:ListDataclsMobile_trUserLogin) {
                dataLogin.setIdUserLogin(dt.txtUserID);
                dataLogin.setDtLogOut(dt.dtLogOut);
                dataLogin.setDtLastLogin(dt.dtLastLogin);
                dataLogin.setDtCheckOut(dt.dtCheckOut);
                dataLogin.setDtCheckIn(dt.dtCheckIn);
                dataLogin.setIntCabangID(dt.IntCabangID);
                dataLogin.setTxtBranchCode(dt.txtBranchCode);
                dataLogin.setTxtDeviceId(dt.txtDeviceId);
                dataLogin.setTxtEmail(dt.txtEmail);
                dataLogin.setTxtEmpID(dt.txtEmpID);
                dataLogin.setTxtGUI(dt.txtGUI);
                dataLogin.setTxtName(dt.txtName);
                dataLogin.setTxtOutletCode(dt.txtOutletCode);
                dataLogin.setTxtOutletName(dt.txtOutletName);
                dataLogin.setTxtRoleID(dt.txtRoleID);
                dataLogin.setTxtRoleName(dt.txtRoleName);
                dataLogin.setTxtUserID(dt.txtUserID);
                dataLogin.setTxtUserName(dt.txtUserName);
                _clsDataLogin.setClsMobile_trUserLoginAPI(dataLogin);
            }
        }
        List<clsMobile_mLOB> ListDataclsMobile_mLOB=new Select().from(clsMobile_mLOB.class).execute();
        if(ListDataclsMobile_mLOB.size()>0){
            dataLoginLOB=new ArrayList<clsMobile_MLOB>();
            for (clsMobile_mLOB dt:ListDataclsMobile_mLOB) {
                clsMobile_MLOB dt1 =new clsMobile_MLOB();
                dt1.setTxtLOBDescription(dt.txtLOBDescription);
                dt1.setTxtLOBName(dt.txtLOBName);
                dt1.setIntLOBID(dt.intLOBID+"");
                dataLoginLOB.add(dt1);
            }
            _clsDataLogin.setClsMobile_MLOB(dataLoginLOB);
        }

        List<clsMobile_MBranch> ListDataclsMobile_MBranch=new Select().from(clsMobile_MBranch.class).execute();
        if(ListDataclsMobile_MBranch.size()>0){
            dataLoginBranch=new ArrayList<kalbenutritionals.bridgeapi.common.clsMobile_MBranch>();
            for (clsMobile_MBranch dt1:ListDataclsMobile_MBranch) {
                kalbenutritionals.bridgeapi.common.clsMobile_MBranch dt=new kalbenutritionals.bridgeapi.common.clsMobile_MBranch();
                dt.setTxtNamaCabang(dt1.TxtNamaCabang);
                dt.setTxtKodeCabang(dt1.TxtKodeCabang);
                dt.setIntCabangID(dt1.IntCabangID+"");
                dataLoginBranch.add(dt);
            }
            _clsDataLogin.setClsMobile_MBranch(dataLoginBranch);
        }
        List<clsMobile_UserJabatan> ListDataclsMobile_UserJabatan=new Select().from(clsMobile_UserJabatan.class).execute();
        if(ListDataclsMobile_MBranch.size()>0){
             dataclsMobile_UserJabatanAPI = new ArrayList<clsMobile_UserJabatanAPI>();
            for (clsMobile_UserJabatan dt1:ListDataclsMobile_UserJabatan) {
                clsMobile_UserJabatanAPI dt=new clsMobile_UserJabatanAPI();
                dt.setTxtLimit(dt1.txtLimit);
                dt.setTxtJabatanName(dt1.TxtJabatanName);
                dt.setTxtJabatanDesc(dt1.TxtJabatanDesc);
                dt.setIntJabatanID(dt1.IntJabatanID+"");
                dataclsMobile_UserJabatanAPI.add(dt);
            }
            _clsDataLogin.setClsMobile_UserJabatanAPI(dataclsMobile_UserJabatanAPI);
        }
        return _clsDataLogin;
    }
    public JSONObject  PushDataCallPlan(){
        JSONArray _JSONArray=new JSONArray();
        JSONObject JsonParam=new JSONObject();
        JSONObject JsonResult=null;
        JSONObject JsonParamData=new JSONObject();
        JSONObject JsonParamdatMobile_trVisitPlan_Header=new JSONObject();
        JSONObject JsonParamdatMobile_trVisitPlan_Detail=new JSONObject();
        JSONObject JsonParamdatMobile_trVisitPlan_Item=new JSONObject();
        JSONArray JsonArraydatMobile_trVisitPlan_Header=null;
        JSONArray JsonArraydatMobile_trVisitPlan_Detail=null;
        JSONArray JsonArraydatMobile_trVisitPlan_Detail_Item=null;
        HashMap<String,String> ListOfFile=new HashMap<>();
        clsMobile_mVersionApp _clsMobile_mVersionApp=new clsMobile_mVersionApp();
        List<clsMobile_mVersionApp> listOfclsMobile_mVersionApp= new Select().from(clsMobile_mVersionApp.class).where(_clsMobile_mVersionApp.txtConstidVersion+"=?",1).execute();
        clsMobile_trUserLogin dtclsMobile_trUserLogin= new Mobile_trUserLoginBL().CheckUserLogin();
        if(dtclsMobile_trUserLogin.txtUserID == null){
            List<clsMobile_trUserLogin> ListOfUserLogin=new Select().from(clsMobile_trUserLogin.class).execute();
            if(ListOfUserLogin.size()>0){
                dtclsMobile_trUserLogin=ListOfUserLogin.get(0);
            }
        }
        clsMobile_trVisitPlan_Detail _clsMobile_trVisitPlan_Detail=new clsMobile_trVisitPlan_Detail();
        List<clsMobile_trVisitPlan_Detail> ListOfclsMobile_trVisitPlan_Detail=new Select().from(clsMobile_trVisitPlan_Detail.class).where(_clsMobile_trVisitPlan_Detail.txtConstintSubmit+"=2").limit(3).execute();
        if(ListOfclsMobile_trVisitPlan_Detail.size()>0){
            try {
                JsonParam.put("TxtGUI_trUserLogin",dtclsMobile_trUserLogin.txtGUI.toString());
                JsonParam.put("TxtUserID",dtclsMobile_trUserLogin.txtUserID.toString());
                JsonParam.put("TxtGUI_mVersionApp",listOfclsMobile_mVersionApp.get(0).txtGUI);
                JsonParam.put("IntCabangID",dtclsMobile_trUserLogin.IntCabangID);

                clsMobile_trVisitPlan_Header _clsMobile_trVisitPlan_Header=new clsMobile_trVisitPlan_Header();
                clsMobile_trVisitPlan_Detail_Item _clsMobile_trVisitPlan_Detail_item=new clsMobile_trVisitPlan_Detail_Item();
                List<clsMobile_trVisitPlan_Detail> ListOfclsMobile_trVisitPlan_DetailGetHeaderId=new Select().distinct().from(clsMobile_trVisitPlan_Detail.class).where(_clsMobile_trVisitPlan_Detail.txtConstintSubmit+"=2").groupBy(_clsMobile_trVisitPlan_Detail.txtConstintVisitPlan_HeaderID).execute();
                String txtHeaderId="";
                int indexHeader=0;
                for (clsMobile_trVisitPlan_Detail dtclsMobile_trVisitPlan_Detail:ListOfclsMobile_trVisitPlan_DetailGetHeaderId) {
                    if(indexHeader>0){
                        txtHeaderId+=",";
                    }
                    txtHeaderId+=dtclsMobile_trVisitPlan_Detail.intVisitPlan_HeaderID;
                    indexHeader+=1;
                }
                List<clsMobile_trVisitPlan_Header> ListOfclsMobile_trVisitPlan_Header2=new Select().from(clsMobile_trVisitPlan_Header.class).where(_clsMobile_trVisitPlan_Header.txtConstIntVisitPlan_HeaderID+" in ("+txtHeaderId+")").execute();

                if(ListOfclsMobile_trVisitPlan_Header2.size()>0){
                    JsonArraydatMobile_trVisitPlan_Header=new JSONArray();
                    for (clsMobile_trVisitPlan_Header _dt:ListOfclsMobile_trVisitPlan_Header2) {
                        JsonParamdatMobile_trVisitPlan_Header=new JSONObject();
                        JsonParamdatMobile_trVisitPlan_Header.put("TxtGUI_Header",_dt.txtGUI_Header);
                        JsonParamdatMobile_trVisitPlan_Header.put("DtDate",_dt.dtDate);
                        JsonParamdatMobile_trVisitPlan_Header.put("TxtDeviceID",_dt.txtDeviceID);
                        JsonParamdatMobile_trVisitPlan_Header.put("TxtType",_dt.txtType);
                        JsonParamdatMobile_trVisitPlan_Header.put("IntVisitPlan_HeaderID",_dt.IntVisitPlan_HeaderID);
                        JsonParamdatMobile_trVisitPlan_Header.put("IntPeriodID",_dt.intPeriodID);
                        JsonParamdatMobile_trVisitPlan_Header.put("TxtPeriode",_dt.txtPeriode);
                        JsonParamdatMobile_trVisitPlan_Header.put("IntBranchID",_dt.intBranchID);
                        JsonParamdatMobile_trVisitPlan_Header.put("IntPegawaiID",_dt.intPegawaiID);
                        JsonParamdatMobile_trVisitPlan_Header.put("IntJabatanID",_dt.intJabatan);
                        JsonParamdatMobile_trVisitPlan_Header.put("DtStartWeek",_dt.dtStartWeek);
                        JsonParamdatMobile_trVisitPlan_Header.put("DtEndWeek",_dt.dtEndWeek);
                        JsonArraydatMobile_trVisitPlan_Header.put(JsonParamdatMobile_trVisitPlan_Header);
                    }
                }
                JsonParamData.put("datMobile_trVisitPlan_Header",JsonArraydatMobile_trVisitPlan_Header);

                if(ListOfclsMobile_trVisitPlan_Detail.size()>0){
                    JsonArraydatMobile_trVisitPlan_Detail=new JSONArray();
                    for (clsMobile_trVisitPlan_Detail _dt:ListOfclsMobile_trVisitPlan_Detail) {
                        JsonParamdatMobile_trVisitPlan_Detail=new JSONObject();
                        JsonParamdatMobile_trVisitPlan_Detail.put("TxtGUI_Detail",_dt.txtGUI_Detail);
                        JsonParamdatMobile_trVisitPlan_Detail.put("IntVisitPlan_DetailID",_dt.intVisitPlan_DetailID);
                        JsonParamdatMobile_trVisitPlan_Detail.put("IntVisitPlan_HeaderID",_dt.intVisitPlan_HeaderID);
                        JsonParamdatMobile_trVisitPlan_Detail.put("IntLOBID",_dt.intLOBID);
                        JsonParamdatMobile_trVisitPlan_Detail.put("IntCategoryID",_dt.intCategoryID);
                        JsonParamdatMobile_trVisitPlan_Detail.put("TxtNoKode",_dt.txtNoKode);
                        JsonParamdatMobile_trVisitPlan_Detail.put("TxtDescription",_dt.txtDescription);
                        JsonParamdatMobile_trVisitPlan_Detail.put("TxtLongitude_Knis",_dt.txtLongitude_Knis);
                        JsonParamdatMobile_trVisitPlan_Detail.put("TxtLatitude_Knis",_dt.txtLatitude_Knis);
                        JsonParamdatMobile_trVisitPlan_Detail.put("TxtAccuracy_Knis",_dt.txtAccuracy_Knis);
                        JsonParamdatMobile_trVisitPlan_Detail.put("DtActual",_dt.dtActualDate);
                        JsonParamdatMobile_trVisitPlan_Detail.put("DtPlan",_dt.dtPlanDate);
                        //JsonParamdatMobile_trVisitPlan_Detail.put("IntJumlahPreNC",_dt.intJumlahPreNC);
                        JsonParamdatMobile_trVisitPlan_Detail.put("TxtValidationNo",_dt.txtValidationNo);
                        JsonParamdatMobile_trVisitPlan_Detail.put("DtValidated",_dt.dtValidated);
                        JsonParamdatMobile_trVisitPlan_Detail.put("IntBobot",_dt.intBobot);
                        JsonParamdatMobile_trVisitPlan_Detail.put("IntJumlahPreNC",_dt.intJumlahPreNC);
                        JsonParamdatMobile_trVisitPlan_Detail.put("IntPartnerID",_dt.intPartnerID);
                        JsonParamdatMobile_trVisitPlan_Detail.put("IntPartnerIDCheckIn",_dt.intPartnerIDCheckIn);
                        _clsMobile_trVisitPlan_Detail_item=new clsMobile_trVisitPlan_Detail_Item();
                        List<clsMobile_trVisitPlan_Detail_Item> ListOfclsMobile_trVisitPlan_Detail_item=new Select().from(clsMobile_trVisitPlan_Detail_Item.class).where(_clsMobile_trVisitPlan_Detail_item.txtConsttxtGUI_Detail+"=?",_dt.txtGUI_Detail).execute();
                        if(ListOfclsMobile_trVisitPlan_Detail_item.size()>0){
                            JsonArraydatMobile_trVisitPlan_Detail_Item=new JSONArray();
                            for (clsMobile_trVisitPlan_Detail_Item _dtItem:ListOfclsMobile_trVisitPlan_Detail_item) {
                                JsonParamdatMobile_trVisitPlan_Item=new JSONObject();
                                JsonParamdatMobile_trVisitPlan_Item.put("TxtGUI_Detail_Item",_dtItem.txtGUI_Detail_Item);
                                JsonParamdatMobile_trVisitPlan_Item.put("IntVisitPlan_DetailID",_dtItem.intVisitPlan_DetailID);
                                JsonParamdatMobile_trVisitPlan_Item.put("DtCaptureGeotagging",_dtItem.dtCaptureGeotagging);
                                JsonParamdatMobile_trVisitPlan_Item.put("TxtLongitude",_dtItem.txtLongitude);
                                JsonParamdatMobile_trVisitPlan_Item.put("TxtLatitude",_dtItem.txtLatitude);
                                JsonParamdatMobile_trVisitPlan_Item.put("TxtAccuracy",_dtItem.txtAccuracy);
                                JsonParamdatMobile_trVisitPlan_Item.put("TxtGUI_Detail",_dtItem.txtGUI_Detail);
                                if((_dt.intCategoryID.equals(enum_VisitPlanCategory.CUTI.getValue()+"") || _dt.intCategoryID.equals(enum_VisitPlanCategory.PERJALANAN_DINAS.getValue()+"") )) {
                                    clsMobile_mBinaryFile _clsMobile_mBinaryFile=new clsMobile_mBinaryFile();
                                }else{
                                    clsMobile_mBinaryFile _clsMobile_mBinaryFile=new clsMobile_mBinaryFile();
                                    List<clsMobile_mBinaryFile> ListOfclsMobile_mBinaryFile =new Select().from(clsMobile_mBinaryFile.class).where(_clsMobile_mBinaryFile.txtConsttxtGUI_IDTable+"=?",
                                            _dtItem.txtGUI_Detail_Item).where(_clsMobile_mBinaryFile.txtConsttxtFileName+"=?",_dtItem.txtFileName).execute();
                                    if(ListOfclsMobile_mBinaryFile.size()>0){
                                        _clsMobile_mBinaryFile=ListOfclsMobile_mBinaryFile.get(0);
                                        Bitmap img= getImageFromclsMobile_mBinaryFile(_clsMobile_mBinaryFile);
                                        if(img!=null){
                                            int index = _dtItem.txtFileName.lastIndexOf("\\");
                                            String fileName = _dtItem.txtFileName.substring(index + 1);
                                            JsonParamdatMobile_trVisitPlan_Item.put("TxtFileName",fileName);
                                            ListOfFile.put(fileName,_clsMobile_mBinaryFile.txtFileName);
                                        }
                                    }
                                }
                                JsonArraydatMobile_trVisitPlan_Detail_Item.put(JsonParamdatMobile_trVisitPlan_Item);
                            }
                            JsonParamdatMobile_trVisitPlan_Detail.put("datMobile_trVisitPlan_Detail_Item",JsonArraydatMobile_trVisitPlan_Detail_Item);
                        }
                        JsonArraydatMobile_trVisitPlan_Detail.put(JsonParamdatMobile_trVisitPlan_Detail);
                    }
                }
                JsonParamData.put("datMobile_trVisitPlan_Detail",JsonArraydatMobile_trVisitPlan_Detail);
                JsonParam.put("data",JsonParamData);
                try {
                    JsonResult= PushData("PushDataVisitPlan_J",JsonParam.toString(),ListOfFile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return JsonResult;
    }
    public String GetTimeNow(){
        clsMobile_trUserLogin dtclsMobile_trUserLogin= new Mobile_trUserLoginBL().CheckUserLogin();
        return  dtclsMobile_trUserLogin.dtLastLogin;
    }
    public Bitmap getImageFromclsMobile_mBinaryFile(clsMobile_mBinaryFile dt){
        File fileCheck = new File(dt.txtFileName);
        Bitmap bitmap=null;
        if(fileCheck.exists()){
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inSampleSize=8;
            bitmap= BitmapFactory.decodeFile(fileCheck.getAbsolutePath(),bmOptions);
        }else{
            if(dt.getBinary()!=null){
                bitmap = BitmapFactory.decodeByteArray(dt.getBinary(), 0, dt.getBinary().length);
                FileOutputStream out = null;
                try {
                    out = new FileOutputStream(dt.txtFileName);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 50, out); // bmp is your Bitmap instance
                    // PNG is a lossless format, the compression factor (100) is ignored
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (out != null) {
                            out.flush();
                            out.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return bitmap;
    }

    public Bitmap downloadImage(String _URL){
        Bitmap bmpimg=null;
        InputStream in = null;
        try
        {
            URL url = new URL(_URL);
            URLConnection urlConn = url.openConnection();
            HttpURLConnection httpConn = (HttpURLConnection) urlConn;
            httpConn.connect();
            in = httpConn.getInputStream();
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        if(in != null){
            bmpimg = BitmapFactory.decodeStream(in);
        }
        return bmpimg;
    }
    public byte[] getBytesFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        return stream.toByteArray();
    }
    public byte[] ConvertStringtoByte(String givenString) {
        byte[] byteValue = new byte[0];
        try {
            byteValue = givenString.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return byteValue;
            //byte[] byteValueAscii= letters.getBytes("US-ASCII");
    }
    public JSONObject PushData(String txtMethod, String txtJson) throws Exception {
        Mobile_mConfigBL _Mobile_mConfigBL=new Mobile_mConfigBL();
        String txtLink=_Mobile_mConfigBL.getValue(enum_mconfig.API.getValue());
        JSONObject _JSONArray=_Mobile_mConfigBL.callPushDataReturnJson(txtLink,txtMethod,txtJson);
        return _JSONArray;
    }
    public JSONObject PushData(String txtMethod, String txtJson, HashMap<String, String> ListOfDataFile) throws Exception {
        Mobile_mConfigBL _Mobile_mConfigBL=new Mobile_mConfigBL();
        String txtLink=_Mobile_mConfigBL.getValue(enum_mconfig.API.getValue());
        JSONObject _JSONArray=_Mobile_mConfigBL.callPushDataReturnJson(txtLink,txtMethod,txtJson,ListOfDataFile);
        return _JSONArray;
    }
    public Boolean ValidJSON(JSONObject JsonRes){
        if(JsonRes == null){
            return false;
        }else{
            if(JsonRes.length()==0){
                return false;
            }else{
                if(JsonRes.toString().equals("{}")==false){
                    return true;
                }else{
                    return false;
                }
            }
        }

    }
    public Bitmap resizeImageForBlob(Bitmap photo){
        int width = photo.getWidth();
        int height = photo.getHeight();
        Mobile_mConfigBL _Mobile_mConfigBL=new Mobile_mConfigBL();
        String txtValue=_Mobile_mConfigBL.getValue(enum_mconfig.VISIT_PLAN_SIZE_SAVE_PHOTO.getValue());
        int maxHeight = 800;
        int maxWidth = 800;
        if(txtValue.contains("x")){
            String[] output = txtValue.split("x");
            maxWidth=Integer.valueOf(output[0]);
            maxHeight=Integer.valueOf(output[1]);
        }


        Bitmap bitmap;

        if(height > width){
            float ratio = (float) height / maxHeight;
            height = maxHeight;
            width = (int)(width / ratio);
        }
        else if(height < width){
            float ratio = (float) width / maxWidth;
            width = maxWidth;
            height = (int)(height / ratio);
        }
        else{
            width = maxWidth;
            height = maxHeight;
        }

        bitmap = Bitmap.createScaledBitmap(photo, width, height, true);

        return bitmap;
    }
    public void showToastWarning(Context ctx, String str){
        LayoutInflater mInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View promptView = mInflater.inflate(R.layout.custom_toast, null);

        TextView tvTextToast = (TextView) promptView.findViewById(R.id.custom_toast_message);
        ImageView icon = (ImageView) promptView.findViewById(R.id.custom_toast_image);
        tvTextToast.setText(str);

        GradientDrawable bgShape = (GradientDrawable)promptView.getBackground();

        bgShape.setColor(Color.parseColor("#e74c3c"));
        icon.setImageResource(R.drawable.ic_error);
        /*
        if (status) {
            bgShape.setColor(Color.parseColor("#6dc066"));
            icon.setImageResource(R.drawable.ic_checklist);

        } else {
            bgShape.setColor(Color.parseColor("#e74c3c"));
            icon.setImageResource(R.drawable.ic_error);
        }
        */
        Toast toast = new Toast(ctx);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(promptView);
        toast.setGravity(Gravity.TOP, 25, 400);
        //toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        /*
        Toast toast = Toast.makeText(ctx,
                str, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP, 25, 400);
        toast.show();
        */
    }
    public boolean isMyServiceRunning(Class<?> serviceClass,Context context) {
        ActivityManager manager = (ActivityManager)context. getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                Log.i("Service already","running");
                return true;
            }
        }
        Log.i("Service not","running");
        return false;
    }
    public void showToast(Context ctx, String str){
        LayoutInflater mInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View promptView = mInflater.inflate(R.layout.custom_toast, null);

        TextView tvTextToast = (TextView) promptView.findViewById(R.id.custom_toast_message);
        ImageView icon = (ImageView) promptView.findViewById(R.id.custom_toast_image);
        tvTextToast.setText(str);

        GradientDrawable bgShape = (GradientDrawable)promptView.getBackground();

        bgShape.setColor(Color.parseColor("#6dc066"));
        icon.setImageResource(R.drawable.ic_checklist);
        /*
        if (status) {
            bgShape.setColor(Color.parseColor("#6dc066"));
            icon.setImageResource(R.drawable.ic_checklist);

        } else {
            bgShape.setColor(Color.parseColor("#e74c3c"));
            icon.setImageResource(R.drawable.ic_error);
        }
        */
        Toast toast = new Toast(ctx);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(promptView);
        toast.setGravity(Gravity.TOP, 25, 400);
        //toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        /*
        Toast toast = Toast.makeText(ctx,
                str, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP, 25, 400);
        toast.show();
        */
    }
    public String getDataDir(final Context context) throws Exception {
        return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.dataDir;
    }
    private static int getDbVersionFromFile() throws Exception
    {
        String dbDest=new clsHardCode().txtPathApp+ new clsHardCode().db;
        File file = new File(dbDest);
        RandomAccessFile fp = new RandomAccessFile(file,"r");
        fp.seek(60);
        byte[] buff = new byte[4];
        fp.read(buff, 0, 4);
        return ByteBuffer.wrap(buff).getInt();
    }
    public void CopyDatabase(Context ctx){
        File dbFile = ctx.getDatabasePath("callPlanMobile.db");
        if(dbFile.exists()){
            String dbDest=new clsHardCode().txtPathApp+ new clsHardCode().db;
            try {
                FileChannel src = new FileInputStream(dbFile).getChannel();
                File directory = new File(dbDest);
                File backupDB = new File(dbDest, new clsMainBL().GetDateNow()+".db");
                try {
                    directory.mkdirs();
                    backupDB.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                FileChannel dst = new FileOutputStream(backupDB).getChannel();
                try {
                    dst.transferFrom(src, 0, src.size());
                    src.close();
                    dst.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public String GenerateGuid(){
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        return randomUUIDString;
    }

    public JSONObject CreateJSONObject(HashMap<String, String> ListData) throws JSONException {
        JSONObject _JSONObject=new JSONObject();;
        for(Map.Entry<String, String> entry : ListData.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            _JSONObject.put(key, value);
        }
        clsMobile_mVersionApp _clsMobile_mVersionApp=new clsMobile_mVersionApp();
        return _JSONObject;
    }
    public JSONObject callPushDataReturnJson(String link,String txtMethod, String txtJson) {
        JSONObject _JSONObject = null;
        //notify("asa","asda","asdas");
        URL url;
        HttpURLConnection conn;
        BufferedReader rd;
        String line;
        String result = "";
        String txtTimeOut= new Mobile_mConfigBL().getValue(enum_mconfig.TimeOut.getValue());
        String urlToRead=link+txtMethod;
        try {
            url = new URL(urlToRead);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(Integer.valueOf(txtTimeOut));
            conn.setRequestProperty("Accept","*/*");
            String param="txtParam="+txtJson;
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            conn.setFixedLengthStreamingMode(param.getBytes().length);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("charset", "utf-8");
            //conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            out.print(param);
            out.close();
            String response= "";
            Scanner inStream = new Scanner(conn.getInputStream());
            while(inStream.hasNextLine())
            {
                response+=(inStream.nextLine());
            }
            conn.disconnect();
            result=response;
            _JSONObject=new JSONObject(result);
        } catch (IOException e) {
        } catch (Exception e) {
        }
        return _JSONObject;
    }
    public static AppAdapter setList(Context _ctx, final List<clsSwipeList> swipeList) {
        final AppAdapter mAdapter;
        PullToRefreshSwipeMenuListView mListView;
        Handler mHandler;

        List<String> mAppList = new ArrayList<String>();

        for (int i = 0; i < swipeList.size(); i++) {
            clsSwipeList getswipeList = swipeList.get(i);
            mAppList.add(getswipeList.get_txtTitle() + "\n" + getswipeList.get_txtDescription());
        }

        mAdapter = new AppAdapter(_ctx, mAppList);

        return mAdapter;

    }
    private static int dp2px(Context _ctx, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, _ctx.getResources().getDisplayMetrics());
    }
    public static SwipeMenuCreator setCreator(final Context _ctx, final Map<String, HashMap> map) {
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {

                HashMap<String, String> map2 = new HashMap<String, String>();

                for (int i = 0; i < map.size(); i++) {
                    map2 = map.get(String.valueOf(i));

                    // create "open" item
                    SwipeMenuItem menuItem = new SwipeMenuItem(_ctx);
                    // set item background
                    menuItem.setBackground(new ColorDrawable(Color.parseColor(map2.get("bgColor"))));
                    // set item width
                    menuItem.setWidth(dp2px(_ctx, 90));
                    // set item title

                    if (map2.get("name") == "View") {
                        int icon = R.drawable.ic_view;
                        menuItem.setIcon(icon);
                    } else if (map2.get("name") == "Edit") {
                        int icon = R.drawable.ic_edit;
                        menuItem.setIcon(icon);
                    } else if (map2.get("name") == "Delete") {
                        int icon = R.drawable.ic_delete;
                        menuItem.setIcon(icon);
                    }
                    // add to menu
                    menu.addMenuItem(menuItem);
                }
                // create "delete" item
                // SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
                // set item background
                // deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9, 0x3F, 0x25)));
                // set item width
                // deleteItem.setWidth(dp2px(90));
                // set a icon
                // deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                // menu.addMenuItem(deleteItem);
            }
        };

        return creator;

    }
    public String GetDateNowFromLogin(){
        List<clsMobile_trUserLogin> ListOfUserLogin=new Select().from(clsMobile_trUserLogin.class).execute();
        if(ListOfUserLogin.size() == 0){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            String formattedDate = dateFormat.format(c.getTime());
            return (formattedDate);
        }else{
            clsMobile_trUserLogin dtUserActive= new Mobile_trUserLoginBL().CheckUserActive();
            return dtUserActive.dtLastLogin;
        }
    }
    public String GetDateNow(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        String formattedDate = dateFormat.format(c.getTime());
        return (formattedDate);

    }
    public Date ConvertFromJson(String txtDate){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date convertedDate = new Date();
        try {
            convertedDate = dateFormat.parse(txtDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return (convertedDate);
    }
    public Date ConvertFromTimeStamp(String txtDate){
        txtDate=txtDate.substring(7,20).replace(")","");
        Long datetimestamp = Long.parseLong(txtDate);
        Timestamp stamp = new Timestamp(datetimestamp);
        Date date = new Date(stamp.getTime());
        return (date);
    }
    public String getStringFrombitmap(Bitmap bitmap){
        byte[] getByte=getBytesFromBitmap(bitmap);
        String str = Base64.encodeToString(getByte, Base64.NO_WRAP);
        return  str;
    }
    public void DeleteData(){
        /*
        new Delete().from(clsMobile_mVersionApp.class).execute();
        new Delete().from(clsMobile_trDeviceInfoUser.class).execute();
        new Delete().from(clsMobile_trUserLogin.class).execute();
        new Delete().from(clsMobile_trVisitPlan_Detail.class).execute();
        new Delete().from(clsMobile_trVisitPlan_Detail_Item.class).execute();
        new Delete().from(clsMobile_trVisitPlan_Header.class).execute();
        new Delete().from(clsMobile_UserJabatan.class).execute();
        new Delete().from(clsMobile_MBranch.class).execute();
        new Delete().from(clsMobile_trPOA.class).execute();
        new Delete().from(clsMobile_MPartnerProfile.class).execute();
        new Delete().from(clsMobile_mLOB.class).execute();
        new Delete().from(clsMobile_mVisitPlanCategory.class).execute();
        new Delete().from(clsMobile_mVisitPlanCategoryDetail.class).execute();
        */
        SQLiteDatabase db = ActiveAndroid.getDatabase();
        List<String> tables = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM sqlite_master WHERE type='table';", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String tableName = cursor.getString(1);
            if (!tableName.equals("android_metadata") &&
                    !tableName.equals("sqlite_sequence")) {
                tables.add(tableName);
            }
            cursor.moveToNext();
        }
        cursor.close();
        if(tables.size()>0){
            for (String tableName : tables) {
                if(tableName.equals("Mobile_mBinaryFile")){
                    db.execSQL("DELETE FROM  " + tableName+" WHERE txtGUI_IDTable != 1;");
                }else{
                    db.execSQL("DELETE FROM  " + tableName+" ;");
                }
            }
        }
    }
    public JSONObject callPushDataReturnJson(String link,String Method, String strJson, HashMap<String, String> ListOfDataFile) throws IOException {
        JSONObject _JSONObject = null;
        String charset = "UTF-8";
        String UrlApi= "";
        String txtTimeOut= new Mobile_mConfigBL().getValue(enum_mconfig.TimeOut.getValue());
        UrlApi=link+Method;
        MultipartUtility multipart = new MultipartUtility(UrlApi, charset,Integer.valueOf(txtTimeOut));
        if(ListOfDataFile!= null){
            for(Map.Entry<String, String> entry : ListOfDataFile.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                multipart.addFilePart(key, new File(value));
            }
        }
        multipart.addFormField("txtParam",strJson);
        //multipart.addHeaderField("txtParam",strJson);
        String Result="";
        List<String> response = multipart.finish();
        for (String line : response) {
            Result+=line;
            System.out.println(line);
        }
        try {
            _JSONObject=new JSONObject(Result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return _JSONObject;
    }
}
