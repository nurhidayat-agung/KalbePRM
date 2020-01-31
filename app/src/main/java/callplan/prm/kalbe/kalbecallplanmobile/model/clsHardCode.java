package callplan.prm.kalbe.kalbecallplanmobile.model;

import android.os.Environment;

import java.io.File;

/**
 * Created by rhezaTesar on 9/28/2016.
 * migrated to Android room by ghqp 12/24/2019
 */

public class clsHardCode {
    public static String appName = "PRMCallPlan";
    public static String dateFormat = "yyyy-MM-dd";
    // public static String baseUrl = "http://prm.kalbenutritionals.web.id/VisitPlan/API/VisitPlanAPI/";
    // public static String baseUrl = "https://prm.kalbenutritionals.com/VisitPlan/API/VisitPlanAPI/";
    public static String baseUrl = "http://appgwdev.kalbenutritionals.com/api/knglobal/";
    // public static String baseUrl = "http://10.171.14.46/kn2015_prm_v2.web/VisitPlan/API/VisitPlanAPI/";
    // public static String baseUrl = "http://10.171.14.39//KN2019_Global.API/";
    public static long conTimeOut = 30;
    public static String tokenBearer = "D4r5M-fVcc9XE06-3oBaSJLl8LzU8NBZJu_ioqMAJWNVspwqP-tEqPT3j7XPNFniAxeeYZmwPDXQ29yCfvnrcwq1ILtp5ukCs9w5_9jg7kbs20U1bpZogE-tnqOvtP1_S1qY6xyGdxmO-7uaYXfAyHUsAKUNPgilE8SA8DdAXbJJ-nxyr3OEI-cePPjQj83yjoMAVQLPbw4YfJY4lTQWk-vHmQIYcc4vp-iuxs6TMmU";
    public String txtPathApp = Environment.getExternalStorageDirectory() + File.separator + "Android" + File.separator + "data" + File.separator + "KalbeCallPlan" + File.separator + "app_database" + File.separator;
    public String db = "callPlanMobile";
    public String txtPathUserData = Environment.getExternalStorageDirectory() + File.separator + "Android" + File.separator + "data" + File.separator + "KalbeCallPlan" + File.separator + "user_data" + File.separator;


    public static class ConstGetToken
    {
        public static String grant_type = "password";
        public static String username = "prm_mobile";
        public static String password = "password";
        public static String tagToken = "tagToken";
    }

}
