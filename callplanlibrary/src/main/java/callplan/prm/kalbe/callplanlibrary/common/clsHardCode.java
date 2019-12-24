package callplan.prm.kalbe.callplanlibrary.common;

import android.os.Environment;

import java.io.File;

/**
 * Created by rhezaTesar on 9/21/2016.
 */

public class clsHardCode {
    public String txtPathApp= Environment.getExternalStorageDirectory()+ File.separator+"Android"+File.separator+"data"+File.separator+"KalbeCallPlan"+File.separator+"app_database"+File.separator;
    public String db="callPlanMobile";
    public String txtPathUserData= Environment.getExternalStorageDirectory()+File.separator+"Android"+File.separator+"data"+File.separator+"KalbeCallPlan"+File.separator+"user_data"+File.separator;
}
