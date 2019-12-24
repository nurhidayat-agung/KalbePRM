package callplan.prm.kalbe.kalbecallplanmobile;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ParseException;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v4.content.FileProvider;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.activeandroid.query.From;
import com.activeandroid.query.Select;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import callplan.prm.kalbe.callplanlibrary.ENUM.enum_mconfig;
import callplan.prm.kalbe.callplanlibrary.common.clsHardCode;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_UserJabatan;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_mConfig;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_mVersionApp;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_trDeviceInfoUser;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_trUserLogin;
import callplan.prm.kalbe.callplanlibrary.common.clsWarning;
import callplan.prm.kalbe.kalbecallplanmobile.bl.Mobile_mConfigBL;
import callplan.prm.kalbe.kalbecallplanmobile.bl.Mobile_tDeviceInfoUserBL;
import callplan.prm.kalbe.kalbecallplanmobile.bl.clsMainBL;
import callplan.prm.kalbe.kalbecallplanmobile.service.MyRebootReceiver;
import callplan.prm.kalbe.kalbecallplanmobile.service.MyServiceNative;

public class LoginActivity extends clsMainAppCompatActivity {
    public EditText etLogin_userName=null;
    public EditText etLogin_pass=null;
    public Button btnLogin_login=null;
    public ImageView imgBanner=null;
    private Button buttonExit;
    private Button btncheckVersion;
    public TextView tvLogin_Version=null;
    public clsWarning _clsWarning=new clsWarning();
    LinearLayout llOldVersion;
    LinearLayout llContent;
    clsMainBL _clsMainBL;
    private int intSet = 1;
    byte[] logoImage;
    ProgressDialog mProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        llOldVersion=(LinearLayout) findViewById(R.id.llOldVersion) ;
        llContent=(LinearLayout) findViewById(R.id.llContent) ;
        imgBanner = (ImageView) findViewById(R.id.ivBannerLogin) ;
        etLogin_userName=(EditText) findViewById(R.id.etLogin_userName);
        etLogin_pass=(EditText) findViewById(R.id.etLogin_pass);
        btnLogin_login=(Button) findViewById(R.id.btnLogin_login);
        tvLogin_Version=(TextView) findViewById(R.id.tvLogin_Version);
        btncheckVersion=(Button) findViewById(R.id.btncheckVersion);
        _clsMainBL=new clsMainBL();
        llOldVersion.setVisibility(View.VISIBLE);
        llContent.setVisibility(View.GONE);
        buttonExit=(Button) findViewById(R.id.buttonExit);

        tvLogin_Version.setText(txtInfoVersionAndAPIApp());
        tvLogin_Version.setGravity(Gravity.CENTER | Gravity.BOTTOM);
        new Mobile_tDeviceInfoUserBL().SaveInfoDevice("","");
        imgBanner.setAdjustViewBounds(true);
        imgBanner.setScaleType(ImageView.ScaleType.CENTER_CROP);
        AsyncCallVersion _callVersion=new AsyncCallVersion();
        _callVersion.execute();
        buttonExit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnLogin_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(etLogin_userName.getText().toString().equals("")||etLogin_pass.getText().toString().equals("")){
                    _clsMainBL.showToastWarning(getApplicationContext() ,"Username atau Password tidak boleh kosong!!");
                }else{
                    AsyncCallLogin _AsyncCallLogin=new AsyncCallLogin();
                    _AsyncCallLogin.execute();
                }

            }
        });
        btncheckVersion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AsyncCallVersion _callVersion=new AsyncCallVersion();
                _callVersion.execute();
            }
        });
        etLogin_pass.setOnTouchListener(new DrawableClickListener.RightDrawableClickListener(etLogin_pass) {
            public boolean onDrawableClick() {
                if (intSet == 1) {
                    etLogin_pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    intSet = 0;
                } else {
                    etLogin_pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    intSet = 1;
                }

                return true;
            }
        });
    }

    private class AsyncCallLogin extends AsyncTask<JSONObject, Void, JSONObject> {
        @SuppressWarnings("WrongThread")
        @Override
        protected JSONObject doInBackground(JSONObject... params) {
            JSONObject LoginData = null;
            try {
                LoginData = new JSONObject();
                clsMobile_mVersionApp _clsMobile_mVersionApp=new clsMobile_mVersionApp();
                clsMobile_trDeviceInfoUser _clsMobile_trDeviceInfoUser=new clsMobile_trDeviceInfoUser();
                JSONObject JsonParam=new JSONObject();
                JSONArray _JSONArray=new JSONArray();
                String txtNameApp= new Mobile_mConfigBL().getValue(enum_mconfig.TypeMobile.getValue());
                String txtVersion= txtVersionApp();
                List<clsMobile_mVersionApp> listOfclsMobile_mVersionApp= new Select().from(clsMobile_mVersionApp.class).where(_clsMobile_mVersionApp.txtConstidVersion+"=?",1).execute();
                List<clsMobile_trDeviceInfoUser> listOfclsMobile_trDeviceInfoUser= new Select().from(clsMobile_trDeviceInfoUser.class).execute();
                JsonParam.put("TxtPegawaiID",etLogin_userName.getText().toString());
                JsonParam.put("TxtPassword",etLogin_pass.getText().toString());
                JsonParam.put("TxtGUI_mVersionApp",listOfclsMobile_mVersionApp.get(0).txtGUI);
                JsonParam.put("TxtVersion",listOfclsMobile_mVersionApp.get(0).txtVersion);
                JsonParam.put("TxtDevice",listOfclsMobile_trDeviceInfoUser.get(0).txtDevice);
                JsonParam.put("TxtModel",listOfclsMobile_trDeviceInfoUser.get(0).txtModel);
                _JSONArray.put(JsonParam);
                LoginData=_clsMainBL.PushData("LogIn_J",_JSONArray.toString());

                //EditText txt = (EditText) findViewById(R.id.txtLoginEmail);
                //roledata = new mUserRoleBL().getRoleAndOutlet(txtEmail1, pInfo.versionName);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                _clsMainBL.showToastWarning(getApplicationContext() ,e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                _clsMainBL.showToastWarning(getApplicationContext()
                        ,e.getMessage());
            }

            return LoginData;
        }

        private ProgressDialog Dialog = new ProgressDialog(LoginActivity.this);

        @Override
        protected void onCancelled() {
            Dialog.dismiss();
            _clsMainBL.showToast(getApplicationContext(),_clsWarning.txtConstCancelRequest);
        }
        @Override
        protected void onPostExecute(JSONObject JsonRes) {
            if(_clsMainBL.ValidJSON(JsonRes)){
                try {
                    String txtvalidJson=(String.valueOf(JsonRes.get("validJson")));
                    JSONObject validJson=new JSONObject(txtvalidJson);
                    String intresult=(String.valueOf(validJson.get("TxtResult")));
                    if(intresult.equals("1")){
                        String txtGetDataJson = String.valueOf(validJson.get("TxtData"));
                        JSONObject JsonData=new JSONObject(txtGetDataJson);
                        String txtJSONUserPegawai = String.valueOf(JsonData.get("UserPegawai"));
                        JSONObject JSONUserPegawai=new JSONObject(txtJSONUserPegawai);
                        String txtJSONUserJabatan = String.valueOf(JsonData.get("UserJabatan"));
                        JSONArray JSONUserJabatanArray=new JSONArray(txtJSONUserJabatan);
                        for (int i = 0; i < JSONUserJabatanArray.length(); i++) {
                            JSONObject JSONUserJabatan = JSONUserJabatanArray.getJSONObject(i);
                            clsMobile_UserJabatan _clsMobile_UserJabatan=new clsMobile_UserJabatan();
                            _clsMobile_UserJabatan.IntJabatanID=Long.valueOf(String.valueOf(JSONUserJabatan.get("IntJabatanID")));
                            _clsMobile_UserJabatan.TxtJabatanName =String.valueOf(JSONUserJabatan.get("TxtJabatanName"));
                            _clsMobile_UserJabatan.TxtJabatanDesc =String.valueOf(JSONUserJabatan.get("TxtJabatanDesc"));
                            _clsMobile_UserJabatan.txtLimit =String.valueOf(JSONUserJabatan.get("Limit"));
                            _clsMobile_UserJabatan.BitPrimary =String.valueOf(JSONUserJabatan.get("BitPrimary"));
                            _clsMobile_UserJabatan.save();
                        }
                        String txtJSONUserLogin = String.valueOf(JsonData.get("UserLogin"));
                        JSONObject JSONUserLogin=new JSONObject(txtJSONUserLogin);
                        clsMobile_trUserLogin _clsMobile_trUserLogin=new clsMobile_trUserLogin();
                        _clsMobile_trUserLogin.idUserLogin=1;
                        _clsMobile_trUserLogin.IntCabangID=String.valueOf(JSONUserLogin.get("IntCabangID"));
                        _clsMobile_trUserLogin.txtBranchCode=String.valueOf(JSONUserLogin.get("TxtKodeCabang"));
                        _clsMobile_trUserLogin.txtDeviceId=String.valueOf(JSONUserLogin.get("TxtDeviceId"));
                        _clsMobile_trUserLogin.txtEmail=String.valueOf(JSONUserLogin.get("TxtEmail"));
                        _clsMobile_trUserLogin.txtEmpID=String.valueOf(JSONUserLogin.get("TxtEmpID"));
                        _clsMobile_trUserLogin.txtGUI=String.valueOf(JSONUserLogin.get("TxtGUI"));
                        _clsMobile_trUserLogin.txtName=String.valueOf(JSONUserLogin.get("TxtName"));
                        _clsMobile_trUserLogin.txtUserName=String.valueOf(JSONUserLogin.get("TxtUserName"));
                        _clsMobile_trUserLogin.txtUserID=String.valueOf(JSONUserLogin.get("TxtUserID"));
                        _clsMobile_trUserLogin.dtLastLogin=String.valueOf(JSONUserLogin.get("DtLastLogin"));
                        _clsMobile_trUserLogin.dtCheckIn=String.valueOf(JSONUserLogin.get("DtCheckIn"));
                        _clsMobile_trUserLogin.dtCheckOut=String.valueOf(JSONUserLogin.get("DtCheckOut"));
                        _clsMobile_trUserLogin.dtLogOut=String.valueOf(JSONUserLogin.get("DtLogOut"));
                        _clsMobile_trUserLogin.txtRoleID=String.valueOf(JSONUserLogin.get("TxtJabatanID"));
                        _clsMobile_trUserLogin.txtRoleName=String.valueOf(JSONUserLogin.get("TxtJabatanName"));
                        _clsMobile_trUserLogin.save();

                        List<clsMobile_mConfig> ListOfclsMobile_mConfig=new ArrayList<clsMobile_mConfig>();
                        clsMobile_mConfig _clsMobile_mConfig=new clsMobile_mConfig();
                        From from = new Select().from(clsMobile_mConfig.class);
                        ListOfclsMobile_mConfig = new Select().from(clsMobile_mConfig.class).where("idConfig=?",5).execute();
                        if(ListOfclsMobile_mConfig.size()>0){
                            _clsMobile_mConfig=ListOfclsMobile_mConfig.get(0);
                            _clsMobile_mConfig.txtValue=String.valueOf(JsonData.get("IntRadius"));
                            _clsMobile_mConfig.txtDefaultValue=String.valueOf(JsonData.get("IntRadius"));
                            _clsMobile_mConfig.save();
                        }

                        ListOfclsMobile_mConfig = new Select().from(clsMobile_mConfig.class).where("idConfig=?",6).execute();
                        if(ListOfclsMobile_mConfig.size()>0){
                            _clsMobile_mConfig=ListOfclsMobile_mConfig.get(0);
                            _clsMobile_mConfig.txtValue=String.valueOf(JsonData.get("SizePhoto"));
                            _clsMobile_mConfig.txtDefaultValue=String.valueOf(JsonData.get("SizePhoto"));
                            _clsMobile_mConfig.save();
                        }

                        ListOfclsMobile_mConfig = new Select().from(clsMobile_mConfig.class).where("idConfig=?",9).execute();
                        if(ListOfclsMobile_mConfig.size()>0){
                            _clsMobile_mConfig=ListOfclsMobile_mConfig.get(0);
                            _clsMobile_mConfig.txtValue=String.valueOf(JsonData.get("APPNameEDetailing"));
                            _clsMobile_mConfig.txtDefaultValue=String.valueOf(JsonData.get("APPNameEDetailing"));
                            _clsMobile_mConfig.save();
                        }
                        startService(new Intent(getApplicationContext(), MyServiceNative.class));
                        Intent myIntent = new Intent(getApplicationContext(), MainMenu.class);
                        finish();
                        startActivity(myIntent);
                    }else{
                        String txtvalidJsonError=(String.valueOf(JsonRes.get("validJson")));
                        JSONObject validJsonError=new JSONObject(txtvalidJsonError);
                        String txtWarn=(String) validJsonError.get("TxtWarn");
                        _clsMainBL.showToastWarning(LoginActivity.this,txtWarn);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    _clsMainBL.showToastWarning(LoginActivity.this,e.getMessage());
                }


            }
            Dialog.dismiss();
        }

        int intProcesscancel = 0;

        @Override
        protected void onPreExecute() {
            //Make ProgressBar invisible
            //pg.setVisibility(View.VISIBLE);
            Dialog.setMessage(_clsWarning.txtConstGettingAsyncData);
            Dialog.setCancelable(false);
            Dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    intProcesscancel = 1;
                    etLogin_pass.requestFocus();
                }
            });
            Dialog.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            Dialog.dismiss();
        }

    }
    private class DownloadTask extends AsyncTask<String, Integer, String> {
        private Context context;
        private PowerManager.WakeLock mWakeLock;

        public DownloadTask(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(String... sUrl) {
            InputStream input = null;
            OutputStream output = null;
            HttpURLConnection connection = null;
            try {
                URL url = new URL(sUrl[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                // expect HTTP 200 OK, so we don't mistakenly save error report
                // instead of the file
                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    return "Server returned HTTP " + connection.getResponseCode()
                            + " " + connection.getResponseMessage();
                }

                // this will be useful to display download percentage
                // might be -1: server did not report the length
                int fileLength = connection.getContentLength();

                // download the file
                input = connection.getInputStream();
                String txtPath = new clsHardCode().txtPathUserData;
                File mediaStorageDir = new File(txtPath);
                // Create the storage directory if it does not exist
                if (!mediaStorageDir.exists()) {
                    if (!mediaStorageDir.mkdirs()) {
                        return null;
                    }
                }
                output = new FileOutputStream(txtPath + "callplan.apk");

                byte data[] = new byte[4096];
                long total = 0;
                int count;
                while ((count = input.read(data)) != -1) {
                    // allow canceling with back button
                    if (isCancelled()) {
                        input.close();
                        return null;
                    }
                    total += count;
                    // publishing the progress....
                    if (fileLength > 0) // only if total length is known
                        publishProgress((int) (total * 100 / fileLength));
                    output.write(data, 0, count);
                }
            } catch (Exception e) {
                return e.toString();
            }
            finally {
                try {
                    if (output != null)
                        output.close();
                    if (input != null)
                        input.close();
                } catch (IOException ignored) {
                }

                if (connection != null)
                    connection.disconnect();
            }
            return null;
        }

        int intProcesscancel = 0;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // take CPU lock to prevent CPU from going off if the user
            // presses the power button during download
            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
                    getClass().getName());
            mWakeLock.acquire();
            mProgressDialog.show();
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress);
            // if we get here, length is known, now set indeterminate to false
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setMax(100);
            mProgressDialog.setProgress(progress[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            mWakeLock.release();
            mProgressDialog.dismiss();
            if (result != null)
                _clsMainBL.showToastWarning(context, "Download error: " + result);
            else {
                _clsMainBL.showToast(context, "File downloaded");

                Intent intent = new Intent(Intent.ACTION_VIEW);
                String txtPath = new clsHardCode().txtPathUserData + "callplan.apk";
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    intent.setDataAndType(FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", new File(txtPath)), "application/vnd.android.package-archive");
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                }else{
                    intent.setDataAndType(Uri.fromFile(new File(txtPath)), "application/vnd.android.package-archive");
                }
                //intent.setDataAndType(Uri.fromFile(new File(txtPath)), "application/vnd.android.package-archive");
                startActivity(intent);
            }
        }
    }
    private class AsyncCallVersion extends AsyncTask<JSONObject, Void, JSONObject> {
        @SuppressWarnings("WrongThread")
        @Override
        protected JSONObject doInBackground(JSONObject... params) {
            JSONObject roledata = null;
            try {
                roledata = new JSONObject();
                clsMobile_mVersionApp _clsMobile_mVersionApp=new clsMobile_mVersionApp();
                JSONObject JsonParam=new JSONObject();
                JSONArray _JSONArray=new JSONArray();
                String txtNameApp= new Mobile_mConfigBL().getValue(enum_mconfig.TypeMobile.getValue());
                String txtVersion= txtVersionApp();
                JsonParam.put(_clsMobile_mVersionApp.txtConsttxtNameApp,txtNameApp);
                JsonParam.put(_clsMobile_mVersionApp.txtConsttxtVersion,txtVersion);
                _JSONArray.put(JsonParam);
                roledata=_clsMainBL.PushData("CheckVersionApp_J",_JSONArray.toString());
                /*
                Bitmap dtBitmap= _clsMainBL.downloadImage("http://vignette2.wikia.nocookie.net/logopedia/images/6/6c/Kalbe_Farma.gif");
                byte[] getByte=_clsMainBL.getBytesFromBitmap(dtBitmap);
                //String str = new String(getByte);
                String str = Base64.encodeToString(getByte, Base64.NO_WRAP);
                //byte[] getByte2=_clsMainBL.ConvertStringtoByte(str);
                byte[] getByte2=Base64.decode(str, Base64.NO_WRAP);
                Bitmap bitmap23 = BitmapFactory.decodeByteArray(getByte2, 0, getByte2.length);
                imgBanner.setImageBitmap(bitmap23);
                */
                //EditText txt = (EditText) findViewById(R.id.txtLoginEmail);
                //roledata = new mUserRoleBL().getRoleAndOutlet(txtEmail1, pInfo.versionName);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return roledata;
        }

        private ProgressDialog Dialog = new ProgressDialog(LoginActivity.this);

        @Override
        protected void onCancelled() {
            Dialog.dismiss();
            _clsMainBL.showToast(LoginActivity.this,_clsWarning.txtConstCancelRequest);
        }
        @Override
        protected void onPostExecute(JSONObject JsonRes) {
            if(_clsMainBL.ValidJSON(JsonRes)){
                llContent.setVisibility(View.VISIBLE);
                llOldVersion.setVisibility(View.GONE);
                try {
                    Boolean resUpdate=false;
                    String txtvalidJson=(String.valueOf(JsonRes.get("validJson")));
                    JSONObject validJson=new JSONObject(txtvalidJson);
                    String intresult=(String.valueOf(validJson.get("TxtResult")));
                    String txtLink="";
                    if(intresult.equals("1")){
                        String txtGetDataJson = String.valueOf(validJson.get("TxtData"));
                        JSONObject JsonData=new JSONObject(txtGetDataJson);
                        clsMobile_mVersionApp _clsMobile_mVersionApp=new clsMobile_mVersionApp();
                        _clsMobile_mVersionApp.idVersion=1;
                        _clsMobile_mVersionApp.txtGUI=String.valueOf(JsonData.get(_clsMobile_mVersionApp.txtConsttxtGUI));
                        _clsMobile_mVersionApp.txtFile=String.valueOf(JsonData.get(_clsMobile_mVersionApp.txtConsttxtFile));
                        _clsMobile_mVersionApp.txtNameApp=String.valueOf(JsonData.get(_clsMobile_mVersionApp.txtConsttxtNameApp));
                        _clsMobile_mVersionApp.txtVersion=String.valueOf(JsonData.get(_clsMobile_mVersionApp.txtConsttxtVersion));
                        txtLink=_clsMobile_mVersionApp.txtFile;
                        if(txtVersionApp().equals(_clsMobile_mVersionApp.txtVersion)){
                            _clsMobile_mVersionApp.save();
                        }
                        //tvLogin_Version.setText(_clsMobile_mVersionApp.txtVersion);
                        if(_clsMobile_mVersionApp.txtVersion.equals(txtVersionApp())==false){
                            resUpdate=true;
                        }
                    }else{
                        String txtWarn=(String) JsonRes.get("TxtWarn");
                        _clsMainBL.showToast(LoginActivity.this,txtWarn);
                    }
                    if (resUpdate) {
                        llContent.setVisibility(View.GONE);
                        llOldVersion.setVisibility(View.VISIBLE);
                        _clsMainBL.showToastWarning(LoginActivity.this,new clsWarning().txtConstWarnVersion);
                        // instantiate it within the onCreate method
                        mProgressDialog = new ProgressDialog(LoginActivity.this);
                        mProgressDialog.setMessage("Please Wait For Downloading File....");
                        mProgressDialog.setIndeterminate(true);
                        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                        mProgressDialog.setCancelable(false);

                        // execute this when the downloader must be fired
                        final DownloadTask downloadTask = new DownloadTask(LoginActivity.this);
                        downloadTask.execute(txtLink);

                        mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                            @Override
                            public void onCancel(DialogInterface dialog) {
                                downloadTask.cancel(true);
                            }
                        });
                    }else{
                        llOldVersion.setVisibility(View.GONE);
                        llContent.setVisibility(View.VISIBLE);
                    }
                } catch (JSONException e) {
                    _clsMainBL.showToastWarning(LoginActivity.this,e.getMessage());
                }
            }else{
                llContent.setVisibility(View.GONE);
                llOldVersion.setVisibility(View.VISIBLE);
                _clsMainBL.showToastWarning(LoginActivity.this,new clsWarning().txtConstErrorConnection);
            }
            Dialog.dismiss();
        }

        int intProcesscancel = 0;

        @Override
        protected void onPreExecute() {
            //Make ProgressBar invisible
            //pg.setVisibility(View.VISIBLE);
            Dialog.setMessage(_clsWarning.txtConstGettingAsyncData);
            Dialog.setCancelable(false);
            Dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    intProcesscancel = 1;
                    etLogin_pass.requestFocus();
                }
            });
            Dialog.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            Dialog.dismiss();
        }

    }
}
