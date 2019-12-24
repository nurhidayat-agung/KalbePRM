package callplan.prm.kalbe.kalbecallplanmobile.bl;

import android.database.sqlite.SQLiteDatabase;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.From;
import com.activeandroid.query.Select;

import java.util.List;

import callplan.prm.kalbe.callplanlibrary.common.clsMobile_mConfig;

/**
 * Created by rhezaTesar on 8/24/2016.
 */

public class Mobile_mConfigBL extends clsMainBL {

    public List<clsMobile_mConfig> getById(int id){
        return new Select().from(clsMobile_mConfig.class).where("idConfig=?",new String[]{String.valueOf(id)}).execute();
    }

    public String getValue(int id){
        String txtValue="";
        List<clsMobile_mConfig> ListData=new Select().from(clsMobile_mConfig.class).where("idConfig=?",new String[]{String.valueOf(id)}).execute();
        if(ListData.size()>0){
            if(ListData.get(0).txtValue.equals("")){
                txtValue=ListData.get(0).txtDefaultValue;
            }else{
                txtValue=ListData.get(0).txtValue;
            }
        }
        return txtValue;
    }

    public List<clsMobile_mConfig> getAll(){
        return new Select().from(clsMobile_mConfig.class).execute();
    }
    public void Mobile_mConfigTable(){
        SQLiteDatabase db = ActiveAndroid.getDatabase();
        db.execSQL("CREATE TABLE if not exists  Mobile_mConfig(Id INT PRIMARY KEY NOT NULL,idConfig   INT,txtName TEXT,txtValue  TEXT, txtDefaultValue TEXT) ;");
        db.close();
    }
        public void InsertDefaultMconfig(){
        int _count=1;
        clsMobile_mConfig _clsMobile_mConfig=new clsMobile_mConfig();
        From from = new Select().from(clsMobile_mConfig.class);
        List<clsMobile_mConfig> ListOfclsMobile_mConfig = new Select().from(clsMobile_mConfig.class).where("idConfig=?",1).execute();
        if(ListOfclsMobile_mConfig.size()==0){
            _clsMobile_mConfig=new clsMobile_mConfig();
            _count=from.count()+1;
        }else{
            _clsMobile_mConfig=ListOfclsMobile_mConfig.get(0);
        }
        _clsMobile_mConfig.idConfig= Long.valueOf(1);
        _clsMobile_mConfig.txtName="API";
        // _clsMobile_mConfig.txtValue="http://10.0.0.242/KN2015 _PRM_V2.WEB/VisitPlan/API/VisitPlanAPI/";
        // _clsMobile_mConfig.txtValue="http://10.171.11.100/KN2015_PRM_V2.WEB/VisitPlan/API/VisitPlanAPI/";
        // _clsMobile_mConfig.txtValue="http://10.171.8.44:8030/prm_v2/VisitPlan/API/VisitPlanAPI/";
        // _clsMobile_mConfig.txtValue="http://192.168.88.12/KN2015_PRM_V2.WEB//VisitPlan/API/VisitPlanAPI/";
        // _clsMobile_mConfig.txtValue="http://appgw.kalbenutritionals.com/api/prm/VisitPlan/API/VisitPlanAPI/";
        _clsMobile_mConfig.txtValue="https://prm.kalbenutritionals.com/VisitPlan/API/VisitPlanAPI/";
        _clsMobile_mConfig.txtDefaultValue="https://prm.kalbenutritionals.com/VisitPlan/API/VisitPlanAPI/";
        // _clsMobile_mConfig.txtValue="http://prm.kalbenutritionals.web.id/VisitPlan/API/VisitPlanAPI/";
        // _clsMobile_mConfig.txtDefaultValue="http://prm.kalbenutritionals.web.id/VisitPlan/API/VisitPlanAPI/";
        // _clsMobile_mConfig.txtDefaultValue="https://10.171.10.17/KN2015_PRM_V2.WEB/VisitPlan/API/VisitPlanAPI/";
        _clsMobile_mConfig.save();
        ListOfclsMobile_mConfig = new Select().from(clsMobile_mConfig.class).where("idConfig=?",2).execute();
        if(ListOfclsMobile_mConfig.size()==0){
            _clsMobile_mConfig=new clsMobile_mConfig();
            from = new Select().from(clsMobile_mConfig.class);
            _count=from.count()+1;
        }else{
            _clsMobile_mConfig=ListOfclsMobile_mConfig.get(0);
        }
        _clsMobile_mConfig.idConfig= Long.valueOf(2);
        _clsMobile_mConfig.txtName="Type Mobile";
            _clsMobile_mConfig.txtValue="Android - Call Plan BR Mobile";
            _clsMobile_mConfig.txtDefaultValue="Android - Call Plan BR Mobile";
        //_clsMobile_mConfig.txtValue="Android - E-DETAILING";
        //_clsMobile_mConfig.txtDefaultValue="Android - E-DETAILING";
        _clsMobile_mConfig.save();

        ListOfclsMobile_mConfig = new Select().from(clsMobile_mConfig.class).where("idConfig=?",3).execute();
        if(ListOfclsMobile_mConfig.size()==0){
            _clsMobile_mConfig=new clsMobile_mConfig();
            from = new Select().from(clsMobile_mConfig.class);
            _count=from.count()+1;
        }else{
            _clsMobile_mConfig=ListOfclsMobile_mConfig.get(0);
        }
        _clsMobile_mConfig.idConfig= Long.valueOf(3);
        _clsMobile_mConfig.txtName="Background Service Online";
        _clsMobile_mConfig.txtValue="1000";
        _clsMobile_mConfig.txtDefaultValue="60000";
        _clsMobile_mConfig.save();

        _clsMobile_mConfig=new clsMobile_mConfig();
        ListOfclsMobile_mConfig = new Select().from(clsMobile_mConfig.class).where("idConfig=?",4).execute();
        if(ListOfclsMobile_mConfig.size()==0){
            _clsMobile_mConfig=new clsMobile_mConfig();
            from = new Select().from(clsMobile_mConfig.class);
            _count=from.count()+1;

        }else{
            _clsMobile_mConfig=ListOfclsMobile_mConfig.get(0);
        }
        _clsMobile_mConfig.idConfig= Long.valueOf(4);
        _clsMobile_mConfig.txtName="TIMEOUT";
        _clsMobile_mConfig.txtValue="6000";
        _clsMobile_mConfig.txtDefaultValue="1000";
        _clsMobile_mConfig.save();

        _clsMobile_mConfig=new clsMobile_mConfig();
        ListOfclsMobile_mConfig = new Select().from(clsMobile_mConfig.class).where("idConfig=?",5).execute();
        if(ListOfclsMobile_mConfig.size()==0){
            _clsMobile_mConfig=new clsMobile_mConfig();
            from = new Select().from(clsMobile_mConfig.class);
            _count=from.count()+1;

        }else{
            _clsMobile_mConfig=ListOfclsMobile_mConfig.get(0);
        }
        _clsMobile_mConfig.idConfig= Long.valueOf(5);
        _clsMobile_mConfig.txtName="VISIT_PLAN_GEOTAGING_RADIUS";
        _clsMobile_mConfig.txtValue="0";
        _clsMobile_mConfig.txtDefaultValue="0";
        _clsMobile_mConfig.save();

        _clsMobile_mConfig=new clsMobile_mConfig();
        ListOfclsMobile_mConfig = new Select().from(clsMobile_mConfig.class).where("idConfig=?",6).execute();
        if(ListOfclsMobile_mConfig.size()==0){
            _clsMobile_mConfig=new clsMobile_mConfig();
            from = new Select().from(clsMobile_mConfig.class);
            _count=from.count()+1;

        }else{
            _clsMobile_mConfig=ListOfclsMobile_mConfig.get(0);
        }
        _clsMobile_mConfig.idConfig= Long.valueOf(6);
        _clsMobile_mConfig.txtName="VISIT_PLAN_SIZE_SAVE_PHOTO";
        _clsMobile_mConfig.txtValue="0";
        _clsMobile_mConfig.txtDefaultValue="0";
        _clsMobile_mConfig.save();


        _clsMobile_mConfig=new clsMobile_mConfig();
        ListOfclsMobile_mConfig = new Select().from(clsMobile_mConfig.class).where("idConfig=?",7).execute();
        if(ListOfclsMobile_mConfig.size()==0){
            _clsMobile_mConfig=new clsMobile_mConfig();
            from = new Select().from(clsMobile_mConfig.class);
            _count=from.count()+1;

        }else{
            _clsMobile_mConfig=ListOfclsMobile_mConfig.get(0);
        }
        _clsMobile_mConfig.idConfig= Long.valueOf(7);
        _clsMobile_mConfig.txtName="LAST_SYNC";
        _clsMobile_mConfig.txtValue="0";
        _clsMobile_mConfig.txtDefaultValue="0";
        _clsMobile_mConfig.save();

        _clsMobile_mConfig=new clsMobile_mConfig();
        ListOfclsMobile_mConfig = new Select().from(clsMobile_mConfig.class).where("idConfig=?",8).execute();
        if(ListOfclsMobile_mConfig.size()==0){
            _clsMobile_mConfig=new clsMobile_mConfig();
            from = new Select().from(clsMobile_mConfig.class);
            _count=from.count()+1;

        }else{
            _clsMobile_mConfig=ListOfclsMobile_mConfig.get(0);
        }
        _clsMobile_mConfig.idConfig= Long.valueOf(8);
        _clsMobile_mConfig.txtName="LAST_RUN_SCHEDULE";
        _clsMobile_mConfig.txtValue="0";
        _clsMobile_mConfig.txtDefaultValue="0";
        _clsMobile_mConfig.save();

        _clsMobile_mConfig=new clsMobile_mConfig();
        ListOfclsMobile_mConfig = new Select().from(clsMobile_mConfig.class).where("idConfig=?",9).execute();
        if(ListOfclsMobile_mConfig.size()==0){
            _clsMobile_mConfig=new clsMobile_mConfig();
            from = new Select().from(clsMobile_mConfig.class);
            _count=from.count()+1;
        }else{
            _clsMobile_mConfig=ListOfclsMobile_mConfig.get(0);
        }
        _clsMobile_mConfig.idConfig= Long.valueOf(9);
        _clsMobile_mConfig.txtName="APPNameEDetailing";
        _clsMobile_mConfig.txtValue="";
        _clsMobile_mConfig.txtDefaultValue="";
        _clsMobile_mConfig.save();
    }
}
