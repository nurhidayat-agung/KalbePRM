package callplan.prm.kalbe.kalbecallplanmobile;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

import callplan.prm.kalbe.callplanlibrary.ENUM.enum_mconfig;
import callplan.prm.kalbe.kalbecallplanmobile.bl.Mobile_mConfigBL;

/**
 * Created by rhezaTesar on 9/8/2016.
 */

public class clsMainAppCompatActivity extends AppCompatActivity {
    public String txtVersionApp(){
        PackageInfo pInfo = null;
        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            return pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
            return "No Version";
        }
    }
    public String txtInfoVersionAndAPIApp(){
        PackageInfo pInfo = null;
        try {
            Mobile_mConfigBL _Mobile_mConfigBL=new Mobile_mConfigBL();
            String txtLink=_Mobile_mConfigBL.getValue(enum_mconfig.API.getValue());
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            return pInfo.versionName+System.getProperty ("line.separator")+txtLink.replace("/VisitPlan/API/VisitPlanAPI/","");
        } catch (PackageManager.NameNotFoundException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
            return "No Version";
        }
    }
}
