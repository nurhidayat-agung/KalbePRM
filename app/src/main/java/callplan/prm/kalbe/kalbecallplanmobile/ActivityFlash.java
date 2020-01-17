package callplan.prm.kalbe.kalbecallplanmobile;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import callplan.prm.kalbe.kalbecallplanmobile.bl.Mobile_mConfigBL;
import callplan.prm.kalbe.kalbecallplanmobile.bl.Mobile_trUserLoginBL;
import callplan.prm.kalbe.kalbecallplanmobile.bl.clsMainBL;
import callplan.prm.kalbe.kalbecallplanmobile.model.clsMobile_trUserLogin;
import callplan.prm.kalbe.kalbecallplanmobile.model.clsMobile_trVisitPlan_Detail;
import callplan.prm.kalbe.kalbecallplanmobile.app.AppDatabase;
import io.fabric.sdk.android.Fabric;

public class ActivityFlash extends clsMainAppCompatActivity {
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    long delay = 5000;
    private TextView version;
    private String intVisitPlan;
    private String txtfromWhere;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;
    private GoogleApiClient client;
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        appDatabase = AppDatabase.getDatabase(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.anim_layout);
        version = (TextView) findViewById(R.id.tv_version);
        version.setText(txtVersionApp());
        version.setGravity(Gravity.CENTER | Gravity.BOTTOM);

//        this.getSupportLoaderManager().initLoader(1, null, this);
//        this.getSupportLoaderManager().initLoader(2, null, this);
//        this.getSupportLoaderManager().initLoader(3, null, this);


        Intent intent = getIntent();
        intVisitPlan = intent.getStringExtra("intVisitPlan");
        txtfromWhere = intent.getStringExtra("fromWhere");


//        ImageView imgBanner = (ImageView) findViewById(R.id.ivBannerLogin);
//        imgBanner.setAdjustViewBounds(true);
//        imgBanner.setScaleType(ImageView.ScaleType.CENTER_CROP);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

        // throw new RuntimeException("This is a crash from ghqp");


    }



    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        int hasWriteExternalStoragePermission = ContextCompat.checkSelfPermission(ActivityFlash.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int hasReadExternalStoragePermission = ContextCompat.checkSelfPermission(ActivityFlash.this,
                Manifest.permission.READ_EXTERNAL_STORAGE);
        int hasAccessFineLocationPermission = ContextCompat.checkSelfPermission(ActivityFlash.this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        int hasCameraPermission = ContextCompat.checkSelfPermission(ActivityFlash.this,
                Manifest.permission.CAMERA);

        if (Build.VERSION.SDK_INT >= 23
                && hasWriteExternalStoragePermission != PackageManager.PERMISSION_GRANTED
                && hasReadExternalStoragePermission != PackageManager.PERMISSION_GRANTED
                && hasAccessFineLocationPermission != PackageManager.PERMISSION_GRANTED
                && hasCameraPermission != PackageManager.PERMISSION_GRANTED
        ) {
            boolean checkPermission = checkPermission();

        } else if (Build.VERSION.SDK_INT >= 23
                && hasWriteExternalStoragePermission == PackageManager.PERMISSION_GRANTED
                && hasReadExternalStoragePermission == PackageManager.PERMISSION_GRANTED
                && hasAccessFineLocationPermission == PackageManager.PERMISSION_GRANTED
                && hasCameraPermission == PackageManager.PERMISSION_GRANTED
        ) {
            StartAnimations();
            checkStatusMenu();

        } else if (Build.VERSION.SDK_INT >= 23
                && hasCameraPermission != PackageManager.PERMISSION_GRANTED) {
            boolean checkPermission = checkPermission();
        } else {
            StartAnimations();
            checkStatusMenu();
        }
        //StartAnimations();
        //checkStatusMenu();
    }

    private boolean checkPermission() {

        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityFlash.this);
        builder.setMessage("You need to allow access. . .");
        builder.setCancelable(false);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                if (!ActivityCompat.shouldShowRequestPermissionRationale(ActivityFlash.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        && !ActivityCompat.shouldShowRequestPermissionRationale(ActivityFlash.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                        && !ActivityCompat.shouldShowRequestPermissionRationale(ActivityFlash.this,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                        && !ActivityCompat.shouldShowRequestPermissionRationale(ActivityFlash.this,
                        Manifest.permission.CAMERA)) {
                    ActivityCompat.requestPermissions(ActivityFlash.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA},
                            REQUEST_CODE_ASK_PERMISSIONS);
                    dialog.dismiss();

                }
                ActivityCompat.requestPermissions(ActivityFlash.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA},
                        REQUEST_CODE_ASK_PERMISSIONS);
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();

        return true;
    }

    private void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        TextView tv = (TextView) findViewById(R.id.iv_anim);
        tv.clearAnimation();
        tv.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        anim.reset();
        ImageView iv = (ImageView) findViewById(R.id.imageView2);
        iv.clearAnimation();
        iv.startAnimation(anim);
    }

    private void checkStatusMenu() {

        Timer runProgress = new Timer();
        TimerTask viewTask = new TimerTask() {

            public void run() {
                new clsMainBL().DeleteData(ActivityFlash.this);
                Intent myIntent = null;
                clsMobile_trUserLogin dtUserLogin = new Mobile_trUserLoginBL().CheckUserActive(getApplicationContext());
                if (txtfromWhere != null) {
                    if (txtfromWhere.equals("EDetailing")) {
                        if (intVisitPlan == "0") {
                            if (dtUserLogin.txtUserID == null) {
                                new clsMainBL().DeleteData(ActivityFlash.this);
                                new Mobile_mConfigBL().InsertDefaultMconfig();
                                myIntent = new Intent(ActivityFlash.this, LoginActivity.class);
                            } else {
                                myIntent = new Intent(ActivityFlash.this, MainMenu.class);
                                myIntent.putExtra("Data", "3");
                                myIntent.putExtra("intVisitPlan", intVisitPlan);
                            }
                        } else {
                            if (dtUserLogin.txtUserID == null) {
                                new clsMainBL().DeleteData(ActivityFlash.this);
                                new Mobile_mConfigBL().InsertDefaultMconfig();
                                myIntent = new Intent(ActivityFlash.this, LoginActivity.class);
                            } else {
                                myIntent = new Intent(ActivityFlash.this, MainMenu.class);
                                myIntent.putExtra("Data", "3");
                                myIntent.putExtra("intVisitPlan", intVisitPlan);
                            }
                        }
                    }
                } else {
                    //new clsMobile_trVisitPlan_DetailBL().clsMobile_trVisitPlan_DetailTable();
                    clsMobile_trVisitPlan_Detail _clsMobile_trVisitPlan_Detail = new clsMobile_trVisitPlan_Detail();
                    // List<clsMobile_trVisitPlan_Detail> ListOfclsMobile_trVisitPlan_Detail = new Select().from(clsMobile_trVisitPlan_Detail.class).where(_clsMobile_trVisitPlan_Detail.txtConstintSubmit+"=2").execute();
                    List<clsMobile_trVisitPlan_Detail> ListOfclsMobile_trVisitPlan_Detail = appDatabase.daoMobileTrVisitPlanDetail().getByIntSubmit(2);

//                client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
                    if (dtUserLogin.txtUserID == null) {
                        if (ListOfclsMobile_trVisitPlan_Detail.size() > 0) {
                            myIntent = new Intent(ActivityFlash.this, MainMenu.class);
                            myIntent.putExtra("Data", "1");
                        } else {
                            // todo ghqp changes
                            new clsMainBL().DeleteData(ActivityFlash.this);
                            myIntent = new Intent(ActivityFlash.this, LoginActivity.class);
                        }
                    } else {
                        myIntent = new Intent(ActivityFlash.this, MainMenu.class);
                        myIntent.putExtra("Data", "0");
                    }
                    //new Mobile_mConfigBL().Mobile_mConfigTable();
                    new Mobile_mConfigBL().InsertDefaultMconfig();
                    myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                }

                finish();
                startActivity(myIntent);
            }
        };
        runProgress.schedule(viewTask, delay);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("ActivityFlash Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        client.connect();
//        AppIndex.AppIndexApi.start(client, getIndexApiAction());


    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        AppIndex.AppIndexApi.end(client, getIndexApiAction());
//        client.disconnect();
    }

//    @Override
//    public android.support.v4.content.Loader<Cursor> onCreateLoader(int id, Bundle args) {
//        //return null;
//        if (id == 1) {
//            // new CursorLoader(ActivityFlash.this,ContentProvider)
//            return new CursorLoader(ActivityFlash.this,
//                    ContentProvider.createUri(clsMobile_trUserLogin.class, null),
//                    null, null, null, null
//            );
//        } else if (id == 2) {
//            return new CursorLoader(ActivityFlash.this,
//                    ContentProvider.createUri(clsMobile_trVisitPlan_Detail.class, null),
//                    null, null, null, null
//            );
//        } else {
//            return new CursorLoader(ActivityFlash.this,
//                    ContentProvider.createUri(clsMobile_mConfig.class, null),
//                    null, null, null, null
//            );
//        }
//
//
//        //return null;
//    }
//
//    @Override
//    public void onLoadFinished(android.support.v4.content.Loader<Cursor> loader, Cursor data) {
//
//    }
//
//    @Override
//    public void onLoaderReset(android.support.v4.content.Loader<Cursor> loader) {
//
//    }
}