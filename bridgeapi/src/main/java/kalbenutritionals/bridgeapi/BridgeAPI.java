package kalbenutritionals.bridgeapi;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Parcelable;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import kalbenutritionals.bridgeapi.BL.Mobile_mConfigAPI_BL;
import kalbenutritionals.bridgeapi.BL.Mobile_trUserLoginAPI_BL;
import kalbenutritionals.bridgeapi.BL.clsMainAPI_BL;
import kalbenutritionals.bridgeapi.ENUM.enum_mconfig;
import kalbenutritionals.bridgeapi.common.clsDataLogin;
import kalbenutritionals.bridgeapi.common.clsMobile_mVersionAppAPI;
import kalbenutritionals.bridgeapi.common.clsMobile_trUserLoginAPI;

/**
 * Created by arick.anjasmara on 12/29/2016.
 */

public class BridgeAPI {

    private static final String PROVIDER_NAME = "callplan.prm.kalbe.kalbecallplanmobile.CallPlanProvider";
    private static final String URL = "content://" + PROVIDER_NAME + "/connector";
    private static final Uri CONTENT_URI = Uri.parse(URL);
    private final String  txtTimeOutAPI="6000";
    private clsMobile_mVersionAppAPI _clsMobile_mVersionAppAPI;
    private clsMobile_trUserLoginAPI _clsMobile_trUserLoginAPI;

    ProgressDialog pd;

    public void helloWorld(Context context) {
        Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show();
        SQLiteDatabase db = ActiveAndroid.getDatabase();

        String asjdkh = "aklsd";
    }

    public void intentWithPackageName(Activity activity, String targetPackage,String targetPackageLaunch, clsDataLogin dataLogin, String intVisitPlan) {
        Intent launchIntent = activity.getPackageManager().getLaunchIntentForPackage(targetPackage);
        launchIntent.putExtra("dataLogin", dataLogin);
        launchIntent.putExtra("intVisitPlan", intVisitPlan);
        String txtJson="";
        try {
            txtJson=String.valueOf(dataLogin.txtJSON());
        } catch (JSONException e) {
            txtJson="Error Generate JSON";
        }
        launchIntent.putExtra("dataLoginJson", String.valueOf(txtJson));
        launchIntent.setComponent(new ComponentName(targetPackage, targetPackageLaunch));
        if (launchIntent != null) {
            activity.startActivity(launchIntent);
        } else {
            Toast.makeText(activity.getApplicationContext(), "Application not installed", Toast.LENGTH_LONG).show();
        }

    }

    public Boolean checkInstalledApplication(Activity activity, String targetPackage) {
        try {
            activity.getPackageManager().getPackageInfo(targetPackage, activity.getPackageManager().GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
    /*
    public clsDataLogin getDataLogin(Activity activity) {
        clsDataLogin dataLogin = new Mobile_trUserLoginAPI_BL().getDataLogin(activity, CONTENT_URI);
        return dataLogin;
    }
    */
    public clsMobile_mVersionAppAPI checkVersion(Activity activity, String TxtLinkAPI, String txtNameAppAPI) {
        clsMobile_mVersionAppAPI dataVersion = new clsMobile_mVersionAppAPI();
        PackageInfo packageInfo;
        JSONObject roledata = new JSONObject();
        JSONObject _JSONObject = null;

        try {
            packageInfo = activity.getPackageManager().getPackageInfo("callplan.prm.kalbe.kalbecallplanmobile", 0);
            clsMobile_mVersionAppAPI _clsMobile_mVersionApp = new clsMobile_mVersionAppAPI();
            JSONObject JsonParam = new JSONObject();
            JSONArray _JSONArray = new JSONArray();
            String txtNameApp = txtNameAppAPI;

            JsonParam.put(_clsMobile_mVersionApp.txtConsttxtNameApp, txtNameApp);
            JsonParam.put(_clsMobile_mVersionApp.txtConsttxtVersion, packageInfo.versionName);
            _JSONArray.put(JsonParam);

            String txtLink = TxtLinkAPI;
            String txtTimeOut = txtTimeOutAPI;

            _JSONObject = new clsMainAPI_BL().callPushDataReturnJson(txtLink, "CheckVersionApp_J", txtTimeOut, _JSONArray.toString());

            if (new clsMainAPI_BL().ValidJSON(_JSONObject)) {

                try {
                    Boolean resUpdate = false;
                    String txtvalidJson = (String.valueOf(_JSONObject.get("validJson")));
                    JSONObject validJson = new JSONObject(txtvalidJson);
                    String intresult = (String.valueOf(validJson.get("TxtResult")));
                    if (intresult.equals("1")) {
                        String txtGetDataJson = String.valueOf(validJson.get("TxtData"));
                        JSONObject JsonData = new JSONObject(txtGetDataJson);
                        _clsMobile_mVersionApp = new clsMobile_mVersionAppAPI();
                        _clsMobile_mVersionApp.setIdVersion("1");
                        _clsMobile_mVersionApp.setTxtGUI(String.valueOf(JsonData.get(_clsMobile_mVersionApp.txtConsttxtGUI)));
                        _clsMobile_mVersionApp.setTxtFile(String.valueOf(JsonData.get(_clsMobile_mVersionApp.txtConsttxtFile)));
                        _clsMobile_mVersionApp.setTxtNameApp(String.valueOf(JsonData.get(_clsMobile_mVersionApp.txtConsttxtNameApp)));
                        _clsMobile_mVersionApp.setTxtVersion(String.valueOf(JsonData.get(_clsMobile_mVersionApp.txtConsttxtVersion)));
                        txtLink = _clsMobile_mVersionApp.getTxtFile();
                        if (packageInfo.versionName.equals(_clsMobile_mVersionApp.getTxtVersion())) {
                            dataVersion = _clsMobile_mVersionApp;
                        }

                        if (_clsMobile_mVersionApp.getTxtVersion().equals(packageInfo.versionName) == false) {
                            resUpdate = true;
                        }
                    } else {
                        String txtWarn = (String) _JSONObject.get("TxtWarn");
                    }
//                    if (resUpdate) {
//                        mProgressDialog = new ProgressDialog(activity.getApplicationContext());
//                        mProgressDialog.setMessage("Please Wait For Downloading File....");
//                        mProgressDialog.setIndeterminate(true);
//                        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//                        mProgressDialog.setCancelable(false);
//
//                        // execute this when the downloader must be fired
//                        final DownloadTask downloadTask = new DownloadTask(activity.getApplicationContext());
//                        downloadTask.execute(txtLink);
//
//                        mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
//                            @Override
//                            public void onCancel(DialogInterface dialog) {
//                                downloadTask.cancel(true);
//                            }
//                        });
//                    } else {
//
//                    }
                } catch (JSONException e) {

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataVersion;
    }

    public clsMobile_trUserLoginAPI pushLogin(Activity activity, String TxtLinkAPI, String txtUsername, String txtPassword, String txtDevice, String txtModel, String txtGUI, String txtNameApp, String txtVersion) {
        JSONObject LoginData = new JSONObject();
        clsMobile_trUserLoginAPI dataLogin = new clsMobile_trUserLoginAPI();
        JSONObject _JSONObject = null;

        try {
            JSONObject JsonParam = new JSONObject();
            JSONArray _JSONArray = new JSONArray();

            JsonParam.put("TxtPegawaiID", txtUsername);
            JsonParam.put("TxtPassword", txtPassword);
            JsonParam.put("TxtGUI_mVersionApp", txtGUI);
            JsonParam.put("TxtVersion", txtVersion);

            JsonParam.put("TxtDevice", txtDevice);
            JsonParam.put("TxtModel", txtModel);
            _JSONArray.put(JsonParam);

            String txtLink = TxtLinkAPI;
            String txtTimeOut = txtTimeOutAPI;

            _JSONObject = new clsMainAPI_BL().callPushDataReturnJson(txtLink, "LogIn_J", txtTimeOut, _JSONArray.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (new clsMainAPI_BL().ValidJSON(_JSONObject)) {
            try {
                String txtvalidJson = (String.valueOf(_JSONObject.get("validJson")));
                JSONObject validJson = new JSONObject(txtvalidJson);
                String intresult = (String.valueOf(validJson.get("TxtResult")));
                if (intresult.equals("1")) {
                    String txtGetDataJson = String.valueOf(validJson.get("TxtData"));
                    JSONObject JsonData = new JSONObject(txtGetDataJson);

                    String txtJSONUserLogin = String.valueOf(JsonData.get("UserLogin"));
                    JSONObject JSONUserLogin = new JSONObject(txtJSONUserLogin);
                    clsMobile_trUserLoginAPI _clsMobile_trUserLogin = new clsMobile_trUserLoginAPI();
                    _clsMobile_trUserLogin.setIdUserLogin("1");
                    _clsMobile_trUserLogin.setIntCabangID(String.valueOf(JSONUserLogin.get("IntCabangID")));
                    _clsMobile_trUserLogin.setTxtBranchCode(String.valueOf(JSONUserLogin.get("TxtKodeCabang")));
                    _clsMobile_trUserLogin.setTxtDeviceId(String.valueOf(JSONUserLogin.get("TxtDeviceId")));
                    _clsMobile_trUserLogin.setTxtEmail(String.valueOf(JSONUserLogin.get("TxtEmail")));
                    _clsMobile_trUserLogin.setTxtEmpID(String.valueOf(JSONUserLogin.get("TxtEmpID")));
                    _clsMobile_trUserLogin.setTxtGUI(String.valueOf(JSONUserLogin.get("TxtGUI")));
                    _clsMobile_trUserLogin.setTxtName(String.valueOf(JSONUserLogin.get("TxtName")));
                    _clsMobile_trUserLogin.setTxtUserName(String.valueOf(JSONUserLogin.get("TxtUserName")));
                    _clsMobile_trUserLogin.setTxtUserID(String.valueOf(JSONUserLogin.get("TxtUserID")));
                    _clsMobile_trUserLogin.setDtLastLogin(String.valueOf(JSONUserLogin.get("DtLastLogin")));
                    _clsMobile_trUserLogin.setDtCheckIn(String.valueOf(JSONUserLogin.get("DtCheckIn")));
                    _clsMobile_trUserLogin.setDtCheckOut(String.valueOf(JSONUserLogin.get("DtCheckOut")));
                    _clsMobile_trUserLogin.setDtLogOut(String.valueOf(JSONUserLogin.get("DtLogOut")));
                    _clsMobile_trUserLogin.setTxtRoleID(String.valueOf(JSONUserLogin.get("TxtJabatanID")));
                    _clsMobile_trUserLogin.setTxtRoleName(String.valueOf(JSONUserLogin.get("TxtJabatanName")));

                    dataLogin = _clsMobile_trUserLogin;

                } else {
                    String txtWarn = (String.valueOf(validJson.get("TxtWarn")));
                    dataLogin.setTxtWarn(txtWarn);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return dataLogin;
    }

    public Boolean pushLogout(Activity activity,String TxtLinkAPI, String txtLoginGUI, String txtUserId, String txtVersionGUI, String intCabangId) {
        JSONArray _JSONArray = null;
        JSONObject JsonParam = new JSONObject();
        _JSONArray = new JSONArray();
        JSONObject _JSONObject = null;

        try {
            JsonParam.put("TxtGUI_trUserLogin", txtLoginGUI);
            JsonParam.put("TxtUserID", txtUserId);
            JsonParam.put("TxtGUI_mVersionApp", txtVersionGUI);
            JsonParam.put("IntCabangID", intCabangId);
            _JSONArray.put(JsonParam);
            String txtLink = TxtLinkAPI;
            String txtTimeOut = txtTimeOutAPI;

            _JSONObject = new clsMainAPI_BL().callPushDataReturnJson(txtLink, "LogOut_J", txtTimeOut, _JSONArray.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (new clsMainAPI_BL().ValidJSON(_JSONObject)) {
            try {
                String txtvalidJson = (String.valueOf(_JSONObject.get("validJson")));
                JSONObject validJson = new JSONObject(txtvalidJson);
                String intresult = (String.valueOf(validJson.get("TxtResult")));
                if (intresult.equals("1")) {
                    return true;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else {
            return false;
        }
        return false;
    }

}
