package callplan.prm.kalbe.kalbecallplanmobile;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.theartofdev.edmodo.cropper.CropImage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import callplan.prm.kalbe.callplanlibrary.common.clsMobile_UserJabatan;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_mBinaryFile;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_mConfig;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_mVersionApp;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_trUserLogin;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_trVisitPlan_Detail;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_trVisitPlan_Header;
import callplan.prm.kalbe.callplanlibrary.common.clsWarning;
import callplan.prm.kalbe.kalbecallplanmobile.bl.Mobile_trUserLoginBL;
import callplan.prm.kalbe.kalbecallplanmobile.bl.clsMainBL;
import callplan.prm.kalbe.kalbecallplanmobile.service.MyServiceNative;
import de.hdodenhof.circleimageview.CircleImageView;
import kalbenutritionals.bridgeapi.BridgeAPI;
import kalbenutritionals.bridgeapi.common.clsDataLogin;

public class MainMenu extends clsMainAppCompatActivity implements View.OnClickListener {
    int intProcesscancel = 0;
    public clsWarning _clsWarning=new clsWarning();

    private TextView tvUsername, tvEmail;
    private CircleImageView ivProfile;

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private clsMobile_trUserLogin _clsMobile_trUserLogin;
    clsMainBL _clsMainBL;
    Toolbar toolbar;
    @Override
    public void onBackPressed() {
        List<Fragment> dtFragment1= new ArrayList<>();
        if(getSupportFragmentManager().getFragments().size()<1){
            Fragment dtFragment=getSupportFragmentManager().getFragments().get(0);
            dtFragment1.add(dtFragment);
        }else{
            dtFragment1=getSupportFragmentManager().getFragments();
        }

        for (Fragment fragment : dtFragment1) {
            if(fragment!=null){
                if(fragment.toString().contains("HomeFragment")){
                    finish();
                }else{
                    toolbar.setTitle("Home");
                    HomeFragment homeFragment = new HomeFragment();
                    FragmentTransaction fragmentTransactionHome = getSupportFragmentManager().beginTransaction();
                    fragmentTransactionHome.replace(R.id.frame, homeFragment);
                    fragmentTransactionHome.commit();
                }
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Home");
        setSupportActionBar(toolbar);
        _clsMainBL=new clsMainBL();

        //new clsMainBL().CopyDatabase(getApplication());
        List<clsMobile_trUserLogin> _ListOfUserLogin = new Select().from(clsMobile_trUserLogin.class).execute();
        clsMobile_trUserLogin dt =_ListOfUserLogin.get(0);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        View vwHeader = navigationView.getHeaderView(0);
        ivProfile = (CircleImageView) vwHeader.findViewById(R.id.profile_image);
        clsMobile_mBinaryFile dtclsMobile_mBinaryFile = new clsMobile_mBinaryFile();
        List<clsMobile_mBinaryFile> ListOfclsMobile_mBinaryFile = new Select().from(clsMobile_mBinaryFile.class).where(dtclsMobile_mBinaryFile.txtConsttxtGUI_IDTable + "=?", "1").execute();
        if(_clsMainBL.isMyServiceRunning(MyServiceNative.class,getApplicationContext())==false){
            startService(new Intent(getApplicationContext(), MyServiceNative.class));
        }

        if(ListOfclsMobile_mBinaryFile.size() != 0){
            Bitmap dtBitmap = new clsMainBL().getImageFromclsMobile_mBinaryFile(ListOfclsMobile_mBinaryFile.get(0));
            Bitmap photo_view = Bitmap.createScaledBitmap(dtBitmap, 600, 600, true);
            ivProfile.setImageBitmap(photo_view);
        }

        ivProfile.setOnClickListener(this);
        tvUsername = (TextView) vwHeader.findViewById(R.id.username);
        tvEmail = (TextView) vwHeader.findViewById(R.id.email);
        tvUsername.setText("Welcome, " + dt.txtName);
        tvEmail.setText(dt.txtEmail);
        Menu header = navigationView.getMenu();
        //header.removeItem(R.id.mnEdetailing);
        Intent intent = getIntent();
        String txtData = intent.getStringExtra("Data");
        String intVisitPlan = intent.getStringExtra("intVisitPlan");
        if(txtData!=null){
            if(txtData.equals("0")){
                toolbar.setTitle("Home");
                HomeFragment homeFragment = new HomeFragment();
                FragmentTransaction fragmentTransactionHome = getSupportFragmentManager().beginTransaction();
                fragmentTransactionHome.replace(R.id.frame, homeFragment);
                fragmentTransactionHome.commit();
            }else if(txtData.equals("1")){
                toolbar.setTitle("Push Data");
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
                PushDataFragment pushDataFragment = new PushDataFragment();
                FragmentTransaction fragmentTransactionPushData = getSupportFragmentManager().beginTransaction();
                fragmentTransactionPushData.replace(R.id.frame, pushDataFragment);
                fragmentTransactionPushData.commit();
                header.removeItem(R.id.mncallPlan);
                header.removeItem(R.id.mnDownloadData);
                header.removeItem(R.id.mnEdetailing);
            }else if(txtData.equals("PUSHDATA")){
                toolbar.setTitle("Push Data");
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
                PushDataFragment pushDataFragment = new PushDataFragment();
                FragmentTransaction fragmentTransactionPushData = getSupportFragmentManager().beginTransaction();
                fragmentTransactionPushData.replace(R.id.frame, pushDataFragment);
                fragmentTransactionPushData.commit();
            }else if(txtData.equals("3")){
                clsMobile_trVisitPlan_Detail _clsMobile_trVisitPlan_Detail=new clsMobile_trVisitPlan_Detail();
                List<clsMobile_trVisitPlan_Detail> listDataDetaila= new Select().from(clsMobile_trVisitPlan_Detail.class).where(_clsMobile_trVisitPlan_Detail.txtConstintVisitPlan_DetailID+"=?",intVisitPlan).execute();
                if(listDataDetaila.size()>0){
                    toolbar.setTitle("Call Plan Data");
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
                    CallPlanFragment CallPlanFragment = new CallPlanFragment(listDataDetaila.get(0).txtGUI_Detail);
                    FragmentTransaction fragmentTransactionCallPlanFragment = getSupportFragmentManager().beginTransaction();
                    fragmentTransactionCallPlanFragment.replace(R.id.frame, CallPlanFragment);
                    fragmentTransactionCallPlanFragment.commit();
                }else{
                    toolbar.setTitle("Home");
                    HomeFragment homeFragment = new HomeFragment();
                    FragmentTransaction fragmentTransactionHome = getSupportFragmentManager().beginTransaction();
                    fragmentTransactionHome.replace(R.id.frame, homeFragment);
                    fragmentTransactionHome.commit();
                }
            }
        }else{
            toolbar.setTitle("Home");
            HomeFragment homeFragment = new HomeFragment();
            FragmentTransaction fragmentTransactionHome = getSupportFragmentManager().beginTransaction();
            fragmentTransactionHome.replace(R.id.frame, homeFragment);
            fragmentTransactionHome.commit();
        }

        SubMenu subMenuVersion = header.addSubMenu(R.id.groupVersion, 0, 3, "Version");
        subMenuVersion.add(txtVersionApp()).setIcon(R.drawable.ic_android).setEnabled(true).setCheckable(true).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                new clsMainBL().CopyDatabase(getApplicationContext());
                return false;
            }
        });
        /*
        Mobile_mConfigBL _Mobile_mConfigBL=new Mobile_mConfigBL();
        String txtLink=_Mobile_mConfigBL.getValue(enum_mconfig.API.getValue());
        subMenuVersion.add(txtLink.replace("/VisitPlan/API/VisitPlanAPI/","")).setEnabled(false).setCheckable(false);
        */
        List<clsMobile_trVisitPlan_Header> ListOfclsMobile_MBranch=new Select().from(clsMobile_trVisitPlan_Header.class).execute();

        if(ListOfclsMobile_MBranch.size() == 0){
            header.removeItem(R.id.mncallPlan);
            header.removeItem(R.id.mnReporting);
            header.removeItem(R.id.mnEdetailing);
            header.removeItem(R.id.mnGeotaging);
        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                menuItem.setChecked(true);

                drawerLayout.closeDrawers();

                Fragment fragment = null;
                switch (menuItem.getItemId()) {
                    case R.id.mnhome:
                        toolbar.setTitle("Home");
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
                        HomeFragment homeFragment = new HomeFragment();
                        FragmentTransaction fragmentTransactionHome = getSupportFragmentManager().beginTransaction();
                        fragmentTransactionHome.replace(R.id.frame, homeFragment);
                        fragmentTransactionHome.commit();
                        //_clsMainBL.showToast(getApplication(),"1");
                        return true;
                    case R.id.mnpushData:
                        toolbar.setTitle("Push Data");
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
                        PushDataFragment pushDataFragment = new PushDataFragment();
                        FragmentTransaction fragmentTransactionPushData = getSupportFragmentManager().beginTransaction();
                        fragmentTransactionPushData.replace(R.id.frame, pushDataFragment);
                        fragmentTransactionPushData.commit();
                        //_clsMainBL.showToast(getApplication(),"2");
                        return true;
                    case R.id.mnDownloadData:
                        toolbar.setTitle("Download Data");
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
                        DownloadFragment DownloadFragment = new DownloadFragment();
                        FragmentTransaction fragmentTransactionPushDownloadData = getSupportFragmentManager().beginTransaction();
                        fragmentTransactionPushDownloadData.replace(R.id.frame, DownloadFragment);
                        fragmentTransactionPushDownloadData.commit();
                        //_clsMainBL.showToast(getApplication(),"3");
                        return true;
                    case R.id.mnGeotaging:
                        toolbar.setTitle("Download Data");
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
                        fragment_geotagging _fragment_geotagging = new fragment_geotagging();
                        FragmentTransaction _fragmentTransactionGeotagging = getSupportFragmentManager().beginTransaction();
                        _fragmentTransactionGeotagging.replace(R.id.frame, _fragment_geotagging);
                        _fragmentTransactionGeotagging.commit();
                        //_clsMainBL.showToast(getApplication(),"3");
                        return true;
                    case R.id.mnLogout:
                        new AlertDialog.Builder(MainMenu.this)
                                .setMessage("Do you really want to Logout?")
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        clsMobile_trVisitPlan_Detail _clsMobile_trVisitPlan_Detail=new clsMobile_trVisitPlan_Detail();
                                        List<clsMobile_trVisitPlan_Detail> ListOfclsMobile_trVisitPlan_Detail=new Select().from(clsMobile_trVisitPlan_Detail.class).where(_clsMobile_trVisitPlan_Detail.txtConstintSubmit+"=2").execute();
                                        if(ListOfclsMobile_trVisitPlan_Detail.size()>0){
                                            AsyncCall _AsyncCall=new AsyncCall();
                                            _AsyncCall.execute();
                                        }else{
                                            AsyncCallLogOut _AsyncCallLogOut=new AsyncCallLogOut();
                                            _AsyncCallLogOut.execute();
                                        }
                                    }})
                                .setNegativeButton(android.R.string.no, null).show();
                        //_clsMainBL.showToast(getApplication(),"5");
                        return true;
                    case R.id.mncallPlan:
                        toolbar.setTitle("Call Plan Data");
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
                        ListCallPlanFragment _CallPlanFragment = new ListCallPlanFragment();
                        FragmentTransaction _FragmentTransactionCallPlan = getSupportFragmentManager().beginTransaction();
                        _FragmentTransactionCallPlan.replace(R.id.frame, _CallPlanFragment);
                        _FragmentTransactionCallPlan.commit();
                        //_clsMainBL.showToast(getApplication(),"4");
                        return true;
                    case R.id.mnReporting:
                        toolbar.setTitle("Realisasi");
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
                        ReportingFragment _ReportingFragment = new ReportingFragment();
                        FragmentTransaction _FragmentTransactionReport = getSupportFragmentManager().beginTransaction();
                        _FragmentTransactionReport.replace(R.id.frame, _ReportingFragment);
                        _FragmentTransactionReport.commit();
                        //_clsMainBL.showToast(getApplication(),"4");
                        return true;
                    case R.id.mnEdetailing:
                        BridgeAPI bridgeAPI = new BridgeAPI();
                        clsMobile_mConfig _clsMobile_mConfig=new clsMobile_mConfig();
                        List<clsMobile_mConfig> ListOfclsMobile_mConfig = new Select().from(clsMobile_mConfig.class).where("idConfig=?",9).execute();
                        if(ListOfclsMobile_mConfig.size()>0){
                            //Boolean status = bridgeAPI.checkInstalledApplication(MainMenu.this, "kalbenutritionals.testjar");
                            Boolean status = bridgeAPI.checkInstalledApplication(MainMenu.this, "com.edetailing");
                            if(status){
                                List<clsMobile_trUserLogin> dataLogin = new Select().from(clsMobile_trUserLogin.class).execute();
                                if(dataLogin.size()>0){
                                    clsDataLogin dtUser = new clsMainBL().getDataLogin();
                                    bridgeAPI.intentWithPackageName(MainMenu.this, "com.edetailing","com.edetailing.MainActivity", dtUser, "0");
                                }else{
                                    new clsMainBL().showToastWarning(MainMenu.this,"user login null, please contact admin PRM!!");
                                }
                            }else{
                                new clsMainBL().showToastWarning(MainMenu.this,"please installed Application E Detailing!!");
                            }
                        }else{
                            new clsMainBL().showToastWarning(MainMenu.this,"Name App E Detailing is null. please contact Admin PRM!!");
                        }
                        //_clsMainBL.showToast(getApplication(),"4");
                        return true;
                    default:
                        return true;

                }
            }
        });

        drawerLayout = (DrawerLayout) findViewById(R.id.activity_main_menu);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }
    private GoogleApiClient client;

    @Override
    public void onClick(View v) {

    }
    private class AsyncCall extends AsyncTask<JSONObject, Void, JSONObject> {
        @Override
        protected JSONObject doInBackground(JSONObject... params) {

            JSONObject JsonResult=null;
            try {
                JsonResult=_clsMainBL.PushDataCallPlan();

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return JsonResult;
        }
        private ProgressDialog Dialog = new ProgressDialog(MainMenu.this);
        @Override
        protected void onCancelled() {
            Dialog.dismiss();
            Toast toast = Toast.makeText(MainMenu.this, new clsWarning().txtConstCancelRequest, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP, 25, 400);
            toast.show();
        }

        @Override
        protected void onPostExecute(JSONObject JsonRes) {
            if(_clsMainBL.ValidJSON(JsonRes)){
                String TxtWarn="";
                String txtvalidJson= null;
                try {
                    txtvalidJson = (String.valueOf(JsonRes.get("validJson")));
                    JSONObject validJson=new JSONObject(txtvalidJson);
                    TxtWarn=(String.valueOf(validJson.get("TxtWarn")));
                    String intresult=(String.valueOf(validJson.get("TxtResult")));
                    if(intresult.equals("1")){
                        String txtGetDataJson = String.valueOf(validJson.get("TxtData"));
                        JSONObject JsonDataTxtData=new JSONObject(txtGetDataJson);
                        String txtMobile_trVisitPlan_Detail = String.valueOf(JsonDataTxtData.get("datMobile_trVisitPlan_Detail"));
                        JSONArray JSONArraytxtMobile_trVisitPlan_Detail =new JSONArray(txtMobile_trVisitPlan_Detail);
                        for (int i = 0; i < JSONArraytxtMobile_trVisitPlan_Detail.length(); i++) {
                            JSONObject JSONdatMobile_trVisitPlan_Detail = JSONArraytxtMobile_trVisitPlan_Detail.getJSONObject(i);
                            clsMobile_trVisitPlan_Detail _dt=new clsMobile_trVisitPlan_Detail();
                            String txtGuidDetail=String.valueOf(JSONdatMobile_trVisitPlan_Detail.get("TxtGUI_Detail"));
                            List<clsMobile_trVisitPlan_Detail> ListOfData=new Select().from(clsMobile_trVisitPlan_Detail.class).where(_dt.txtConsttxtGUI_Detail+"=?",txtGuidDetail).execute();
                            if(ListOfData.size()>0){
                                _dt=ListOfData.get(0);
                                _dt.intSubmit="3";
                                _dt.save();
                            }
                            AsyncCallLogOut _AsyncCallLogOut=new AsyncCallLogOut();
                            _AsyncCallLogOut.execute();

                        }
                    }else {
                        _clsMainBL.showToastWarning(MainMenu.this,TxtWarn);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    _clsMainBL.showToastWarning(MainMenu.this,new clsWarning().txtConstErrorConnection);
                    /*
                    Toast toast = Toast.makeText(getContext(), new clsWarning().txtConstErrorConnection,
                            Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP, 25, 400);
                    toast.show();
                    */
                }

            }
            Dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            // Make ProgressBar invisible
            // pg.setVisibility(View.VISIBLE);
            Dialog.setMessage(new clsWarning().txtConstGettingAsyncData);
            Dialog.setCancelable(false);
            Dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    intProcesscancel = 1;
                }
            });
            Dialog.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            Dialog.dismiss();
        }
    }
    private class AsyncCallLogOut extends AsyncTask<JSONObject, Void, JSONObject> {
        @Override
        protected JSONObject doInBackground(JSONObject... params) {
            JSONObject JsonResult=null;
            JSONArray _JSONArray = null;
            JSONObject JsonParam=new JSONObject();
            _JSONArray=new JSONArray();
            clsMobile_mVersionApp _clsMobile_mVersionApp=new clsMobile_mVersionApp();
            List<clsMobile_mVersionApp> listOfclsMobile_mVersionApp= new Select().from(clsMobile_mVersionApp.class).where(_clsMobile_mVersionApp.txtConstidVersion+"=?",1).execute();
            clsMobile_trUserLogin dtclsMobile_trUserLogin= new Mobile_trUserLoginBL().CheckUserLogin();
            if(dtclsMobile_trUserLogin.txtUserID == null){
                List<clsMobile_trUserLogin> ListOfUserLogin=new Select().from(clsMobile_trUserLogin.class).execute();
                if(ListOfUserLogin.size()>0){
                    dtclsMobile_trUserLogin=ListOfUserLogin.get(0);
                }
            }
            clsMobile_UserJabatan _clsMobile_UserJabatan=new clsMobile_UserJabatan();
            List<clsMobile_UserJabatan> LisOfjabatan=new Select().from(clsMobile_UserJabatan.class).where(_clsMobile_UserJabatan.txtConstBitPrimary+"=1").execute();
            try {
                JsonParam.put("TxtGUI_trUserLogin",dtclsMobile_trUserLogin.txtGUI.toString());
                JsonParam.put("TxtUserID",dtclsMobile_trUserLogin.txtUserID.toString());
                JsonParam.put("TxtGUI_mVersionApp",listOfclsMobile_mVersionApp.get(0).txtGUI);
                JsonParam.put("IntCabangID",dtclsMobile_trUserLogin.IntCabangID);
                _JSONArray.put(JsonParam);
                JsonResult=_clsMainBL.PushData("LogOut_J",_JSONArray.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return JsonResult;
        }

        private ProgressDialog Dialog = new ProgressDialog(MainMenu.this);

        @Override
        protected void onCancelled() {
            Dialog.dismiss();
            Toast toast = Toast.makeText(MainMenu.this, "cancel", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP, 25, 400);
            toast.show();
        }

        @Override
        protected void onPostExecute(JSONObject JsonRes) {
            if(_clsMainBL.ValidJSON(JsonRes)){
                try {
                    String txtvalidJson=(String.valueOf(JsonRes.get("validJson")));
                    JSONObject validJson=new JSONObject(txtvalidJson);
                    String intresult=(String.valueOf(validJson.get("TxtResult")));
                    if(intresult.equals("1")){
                        stopService(new Intent(getApplicationContext(), MyServiceNative.class));
                        Intent myIntent = new Intent(MainMenu.this, ActivityFlash.class);
                        new clsMainBL().DeleteData();
                        finish();
                        startActivity(myIntent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            else{
                _clsMainBL.showToast(MainMenu.this,new clsWarning().txtConstErrorConnection);
            }
            Dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            Dialog.setMessage(_clsWarning.txtConstMessLogout);
            Dialog.setCancelable(false);
            Dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    intProcesscancel = 1;
                }
            });
            Dialog.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            Dialog.dismiss();
        }

    }
    @Override
    public void onStart() {
        super.onStart();
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "MainMenu Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://callplan.prm.kalbe.kalbecallplanmobile/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }
    @Override
    @SuppressLint("NewApi")
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == AppCompatActivity.RESULT_OK) {
            Uri imageUri = CropImage.getPickImageResultUri(this, data);
            CallPlanFragment dtCallPlanFragment= ((CallPlanFragment) getSupportFragmentManager().getFragments().get(0));
            if (CropImage.isReadExternalStoragePermissionsRequired(this, imageUri)) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, CropImage.PICK_IMAGE_PERMISSIONS_REQUEST_CODE);
            } else {
                Intent intent = new Intent(this, CropDisplayPicture.class);
                String strName = imageUri.toString();
                intent.putExtra("uriPicture", strName);
                startActivity(intent);
                finish();
            }
        } else if (requestCode==100 || requestCode == 130){
            for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                if(fragment.toString().contains("CallPlanFragment")){
                    CallPlanFragment dtCallPlanFragment= ((CallPlanFragment) fragment);
                    if(dtCallPlanFragment!=null){
                        fragment.onActivityResult(requestCode, resultCode, data);
                        break;
                    }
                }
            }
        }

    }
    /*
    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "MainMenu Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://callplan.prm.kalbe.kalbecallplanmobile/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
    */
}
