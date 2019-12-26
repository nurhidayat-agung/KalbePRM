package callplan.prm.kalbe.kalbecallplanmobile.model;

import android.os.Environment;

import java.io.File;

/**
 * Created by rhezaTesar on 9/28/2016.
 * migrated to Android room by ghqp 12/24/2019
 */

public class clsHardCode {
    public String txtPathApp= Environment.getExternalStorageDirectory()+ File.separator+"Android"+File.separator+"data"+File.separator+"KalbeCallPlan"+File.separator+"app_database"+File.separator;
    public String db="callPlanMobile";
    public String txtPathUserData= Environment.getExternalStorageDirectory()+File.separator+"Android"+File.separator+"data"+File.separator+"KalbeCallPlan"+File.separator+"user_data"+File.separator;
}
