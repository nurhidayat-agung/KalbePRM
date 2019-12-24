package kalbenutritionals.bridgeapi.common;

import android.os.Environment;

import java.io.File;

/**
 * Created by arick.anjasmara on 1/2/2017.
 */

public class clsHardcode {
    public final String txtConstGettingAsyncData  = "Please Wait";
    public String txtPathApp= Environment.getExternalStorageDirectory()+ File.separator+"Android"+File.separator+"data"+File.separator+"KalbeCallPlan"+File.separator+"app_database"+File.separator;
    public String db="callPlanMobile";
    public String txtPathUserData= Environment.getExternalStorageDirectory()+File.separator+"Android"+File.separator+"data"+File.separator+"KalbeCallPlan"+File.separator+"user_data"+File.separator;
}
