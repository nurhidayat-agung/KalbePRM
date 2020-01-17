package callplan.prm.kalbe.kalbecallplanmobile.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

//import callplan.prm.kalbe.callplanlibrary.common.clsMobile_mConfig;
//import callplan.prm.kalbe.callplanlibrary.common.clsMobile_trVisitPlan_Detail;
//import callplan.prm.kalbe.callplanlibrary.common.clsWarning;
import callplan.prm.kalbe.kalbecallplanmobile.bl.clsMainBL;
import callplan.prm.kalbe.kalbecallplanmobile.model.clsMobile_mConfig;
import callplan.prm.kalbe.kalbecallplanmobile.model.clsMobile_trVisitPlan_Detail;
import callplan.prm.kalbe.kalbecallplanmobile.app.AppDatabase;


public class MyServiceNative extends Service {
    private AppDatabase appDatabase;

    public MyServiceNative() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public void onCreate() {
        //Toast.makeText(this, "Welcome Kalbe SPG Mobile", Toast.LENGTH_LONG).show();
        appDatabase = AppDatabase.getDatabase(this);
    }

    //private static long UPDATE_INTERVAL = 1*36*1000;  //default
    private static long UPDATE_INTERVAL = 1 * 360 * 1000;
    ;  //default
    //private static long UPDATE_INTERVAL_DELAY = 180000;  //default
    private static long UPDATE_INTERVAL_TESTING = 6000;  //default
    private static Timer timer = new Timer();

    private void _startService() {
        long intInverval = 0;
		/*
		if(new clsMainBL().getLIVE().equals("1")){
			intInverval=UPDATE_INTERVAL;
		}else{
			intInverval=UPDATE_INTERVAL_TESTING;
		}
		*/
        intInverval = UPDATE_INTERVAL_TESTING;
        if (timer != null) {
            timer.cancel();
        }
        timer = new Timer();
        timer.scheduleAtFixedRate(
                new TimerTask() {
                    public void run() {
                        try {
                            doServiceWork();
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }, 30000, intInverval);
        //Log.i(getClass().getSimpleName(), "FileScannerService Timer started....");
    }

    clsMainBL _clsMainBL = new clsMainBL();

    private void doServiceWork() throws JSONException {
        clsMobile_mConfig _clsMobile_mConfig = new clsMobile_mConfig();
        _clsMobile_mConfig = new clsMobile_mConfig();
        // List<clsMobile_mConfig> ListOfclsMobile_mConfig = new Select()
        // .from(clsMobile_mConfig.class).where("idConfig=?", 8).execute();
        List<clsMobile_mConfig> ListOfclsMobile_mConfig = appDatabase.daoMobileMConfig().getbyIdConfig(8);
        if (ListOfclsMobile_mConfig.size() > 0) {
            _clsMobile_mConfig = ListOfclsMobile_mConfig.get(0);
            _clsMobile_mConfig.idConfig = Long.valueOf(8);
            _clsMobile_mConfig.txtName = "LAST_RUN_SCHEDULE";
            _clsMobile_mConfig.txtValue = _clsMainBL.GetDateNow();
            _clsMobile_mConfig.txtDefaultValue = _clsMainBL.GetDateNow();
            // _clsMobile_mConfig.save();
//            if (_clsMobile_mConfig.idConfig > 0) {
//                appDatabase.daoMobileMConfig().update(_clsMobile_mConfig);
//            } else {
//                appDatabase.daoMobileMConfig().insert(_clsMobile_mConfig);
//            }
            appDatabase.daoMobileMConfig().insert(_clsMobile_mConfig);

        }
        JSONObject JsonRes = _clsMainBL.PushDataCallPlan(MyServiceNative.this);
        if (_clsMainBL.ValidJSON(JsonRes)) {
            String TxtWarn = "";
            String txtvalidJson = null;
            try {
                txtvalidJson = (String.valueOf(JsonRes.get("validJson")));
                JSONObject validJson = new JSONObject(txtvalidJson);
                TxtWarn = (String.valueOf(validJson.get("TxtWarn")));
                String intresult = (String.valueOf(validJson.get("TxtResult")));
                if (intresult.equals("1")) {
                    String txtGetDataJson = String.valueOf(validJson.get("TxtData"));
                    JSONObject JsonDataTxtData = new JSONObject(txtGetDataJson);
                    String txtMobile_trVisitPlan_Detail = String.valueOf(JsonDataTxtData.get("datMobile_trVisitPlan_Detail"));
                    JSONArray JSONArraytxtMobile_trVisitPlan_Detail = new JSONArray(txtMobile_trVisitPlan_Detail);
                    for (int i = 0; i < JSONArraytxtMobile_trVisitPlan_Detail.length(); i++) {
                        JSONObject JSONdatMobile_trVisitPlan_Detail = JSONArraytxtMobile_trVisitPlan_Detail.getJSONObject(i);
                        clsMobile_trVisitPlan_Detail _dt = new clsMobile_trVisitPlan_Detail();
                        String txtGuidDetail = String.valueOf(JSONdatMobile_trVisitPlan_Detail.get("TxtGUI_Detail"));
                        // List<clsMobile_trVisitPlan_Detail> ListOfData = new Select()
                        // .from(clsMobile_trVisitPlan_Detail.class)
                        // .where(_dt.txtConsttxtGUI_Detail + "=?", txtGuidDetail).execute();
                        List<clsMobile_trVisitPlan_Detail> ListOfData = appDatabase.daoMobileTrVisitPlanDetail()
                                .getBytxtConsttxtGUI_Detail(txtGuidDetail);

                        if (ListOfData.size() > 0) {
                            _dt = ListOfData.get(0);
                            _dt.intSubmit = "3";
                            // _dt.save();

//                            if (UtilFunc.isStringNotNull(_dt.txtGUI_Detail)) {
//                                appDatabase.daoMobileTrVisitPlanDetail().update(_dt);
//                            } else {
//                                appDatabase.daoMobileTrVisitPlanDetail().insert(_dt);
//                            }
                            appDatabase.daoMobileTrVisitPlanDetail().insert(_dt);
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void _shutdownService() {
        if (timer != null) timer.cancel();
        Log.i(getClass().getSimpleName(), "Timer stopped...");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        // For time consuming an long tasks you can launch a new thread here...
        //Toast.makeText(this, "Welcome Kalbe SPG Mobile", Toast.LENGTH_LONG).show();
        _startService();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub
        //Toast.makeText(this, " onStartCommand", Toast.LENGTH_LONG).show();
        _startService();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        //Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
        _shutdownService();
    }
}
