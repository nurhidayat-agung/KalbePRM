package callplan.prm.kalbe.kalbecallplanmobile.app;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import callplan.prm.kalbe.kalbecallplanmobile.model.clsMobile_MBranch;
import callplan.prm.kalbe.kalbecallplanmobile.model.clsMobile_mAllbranch;
import callplan.prm.kalbe.kalbecallplanmobile.model.clsMobile_mBinaryFile;
import callplan.prm.kalbe.kalbecallplanmobile.model.clsMobile_mConfig;
import callplan.prm.kalbe.kalbecallplanmobile.model.*;
import callplan.prm.kalbe.kalbecallplanmobile.roomDAO.*;

@Database(entities = {clsMobile_mAllbranch.class, clsMobile_mBinaryFile.class,
        clsMobile_MBranch.class, clsMobile_mConfig.class, clsMobile_mLOB.class,
        clsMobile_MPartnerProfile.class, clsMobile_MPartnerProfileAlias.class,
        clsMobile_mVersionApp.class, clsMobile_mVisitPlanCategory.class,
        clsMobile_mVisitPlanCategoryDetail.class, clsMobile_TipeSumberData.class
        , clsMobile_trDeviceInfoUser.class, clsMobile_trPOA.class
        , clsMobile_trUserLogin.class, clsMobile_trVisitPlan_Detail.class
        , clsMobile_trVisitPlan_Detail_Item.class, clsMobile_trVisitPlan_Header.class
        , clsMobile_UserJabatan.class, clsMobile_ValidationNo.class},
        version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract DAOMobile_mAllbranch daoMobile_mAllbranch(); //

    public abstract DAOMobile_mBinaryFile daoMobile_mBinaryFile(); //

    public abstract DAOMobile_MBranch daoMobileMBranch(); //

    public abstract DAOMobile_mConfig daoMobileMConfig(); //

    public abstract DAOMobile_mLOB daoMobileMLOB(); //

    public abstract DAOMobile_MPartnerProfile daoMobileMPartnerProfile(); //

    public abstract DAOMobile_MPartnerProfileAlias daoMobileMPartnerProfileAlias(); //

    public abstract DAOMobile_mVersionApp daoMobileMVersionApp(); //

    public abstract DAOMobile_mVisitPlanCategory daoMobileMVisitPlanCategory(); //

    public abstract DAOMobile_mVisitPlanCategoryDetail daoMobileMVisitPlanCategoryDetail(); //

    public abstract DAOMobile_TipeSumberData daoMobileTipeSumberData(); //

    public abstract DAOMobile_trDeviceInfoUser daoMobileTrDeviceInfoUser(); //

    public abstract DAOMobile_trPOA daoMobileTrPOA(); //

    public abstract DAOMobile_trUserLogin daoMobileTrUserLogin(); //

    public abstract DAOMobile_trVisitPlan_Detail daoMobileTrVisitPlanDetail(); //

    public abstract DAOMobile_trVisitPlan_Detail_Item daoMobileTrVisitPlanDetailItem(); //

    public abstract DAOMobile_trVisitPlan_Header daoMobileTrVisitPlanHeader(); //

    public abstract DAOMobile_UserJabatan daoMobileUserJabatan(); //

    public abstract DAOMobile_ValidationNo daoMobileValidationNo();

    // marking the instance as volatile to ensure atomic access to the variable
    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "callPlanMobile")
                            .allowMainThreadQueries()
                            .addCallback(new RoomDatabase.Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    Log.d("MoviesDatabase", "populating with data...");
                                    new PopulateDbAsync(INSTANCE, context).execute();
                                }
                            })
                            .build();
                }
            }
        }
        return INSTANCE;
    }


    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final DAOMobile_mConfig configDAO;
        private final DAOMobile_mVersionApp daoMobileMVersionApp;
        private final Context context;

        public PopulateDbAsync(AppDatabase instance, Context context) {
            configDAO = instance.daoMobileMConfig();
            daoMobileMVersionApp = instance.daoMobileMVersionApp();
            this.context = context;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            configDAO.deleteAll();
            daoMobileMVersionApp.deleteAll();

            clsMobile_mConfig _clsMobile_mConfig = new clsMobile_mConfig();
            _clsMobile_mConfig.idConfig = Long.valueOf(1);
            _clsMobile_mConfig.txtName = "API";
            // _clsMobile_mConfig.txtValue="http://10.0.0.242/KN2015 _PRM_V2.WEB/VisitPlan/API/VisitPlanAPI/";
            // _clsMobile_mConfig.txtValue="http://10.171.11.100/KN2015_PRM_V2.WEB/VisitPlan/API/VisitPlanAPI/";
            // _clsMobile_mConfig.txtValue="http://10.171.8.44:8030/prm_v2/VisitPlan/API/VisitPlanAPI/";
            // _clsMobile_mConfig.txtValue="http://192.168.88.12/KN2015_PRM_V2.WEB//VisitPlan/API/VisitPlanAPI/";
            // _clsMobile_mConfig.txtValue="http://appgw.kalbenutritionals.com/api/prm/VisitPlan/API/VisitPlanAPI/";
            //  _clsMobile_mConfig.txtValue = "https://prm.kalbenutritionals.com/VisitPlan/API/VisitPlanAPI/";
            //  _clsMobile_mConfig.txtDefaultValue = "https://prm.kalbenutritionals.com/VisitPlan/API/VisitPlanAPI/";
            _clsMobile_mConfig.txtValue = clsHardCode.baseUrl;
            _clsMobile_mConfig.txtDefaultValue = clsHardCode.baseUrl;
            // _clsMobile_mConfig.txtValue="http://prm.kalbenutritionals.web.id/VisitPlan/API/VisitPlanAPI/";
            // _clsMobile_mConfig.txtDefaultValue="http://prm.kalbenutritionals.web.id/VisitPlan/API/VisitPlanAPI/";
            // _clsMobile_mConfig.txtDefaultValue="https://10.171.10.17/KN2015_PRM_V2.WEB/VisitPlan/API/VisitPlanAPI/";
            configDAO.insert(_clsMobile_mConfig);

            _clsMobile_mConfig = new clsMobile_mConfig();
            _clsMobile_mConfig.idConfig = Long.valueOf(2);
            _clsMobile_mConfig.txtName = "Type Mobile";
            _clsMobile_mConfig.txtValue = "Android - Call Plan BR Mobile";
            _clsMobile_mConfig.txtDefaultValue = "Android - Call Plan BR Mobile";
            //_clsMobile_mConfig.txtValue="Android - E-DETAILING";
            //_clsMobile_mConfig.txtDefaultValue="Android - E-DETAILING";
            configDAO.insert(_clsMobile_mConfig);

            _clsMobile_mConfig = new clsMobile_mConfig();
            _clsMobile_mConfig.idConfig = Long.valueOf(3);
            _clsMobile_mConfig.txtName = "Background Service Online";
            _clsMobile_mConfig.txtValue = "1000";
            _clsMobile_mConfig.txtDefaultValue = "60000";
            configDAO.insert(_clsMobile_mConfig);

            _clsMobile_mConfig = new clsMobile_mConfig();
            _clsMobile_mConfig.idConfig = Long.valueOf(4);
            _clsMobile_mConfig.txtName = "TIMEOUT";
            _clsMobile_mConfig.txtValue = "6000";
            _clsMobile_mConfig.txtDefaultValue = "1000";
            configDAO.insert(_clsMobile_mConfig);

            _clsMobile_mConfig = new clsMobile_mConfig();
            _clsMobile_mConfig.idConfig = Long.valueOf(5);
            _clsMobile_mConfig.txtName = "VISIT_PLAN_GEOTAGING_RADIUS";
            _clsMobile_mConfig.txtValue = "0";
            _clsMobile_mConfig.txtDefaultValue = "0";
            configDAO.insert(_clsMobile_mConfig);

            _clsMobile_mConfig = new clsMobile_mConfig();
            _clsMobile_mConfig.idConfig = Long.valueOf(6);
            _clsMobile_mConfig.txtName = "VISIT_PLAN_SIZE_SAVE_PHOTO";
            _clsMobile_mConfig.txtValue = "0";
            _clsMobile_mConfig.txtDefaultValue = "0";
            configDAO.insert(_clsMobile_mConfig);


            _clsMobile_mConfig = new clsMobile_mConfig();
            _clsMobile_mConfig.idConfig = Long.valueOf(7);
            _clsMobile_mConfig.txtName = "LAST_SYNC";
            _clsMobile_mConfig.txtValue = "0";
            _clsMobile_mConfig.txtDefaultValue = "0";
            configDAO.insert(_clsMobile_mConfig);

            _clsMobile_mConfig = new clsMobile_mConfig();
            _clsMobile_mConfig.idConfig = Long.valueOf(8);
            _clsMobile_mConfig.txtName = "LAST_RUN_SCHEDULE";
            _clsMobile_mConfig.txtValue = "0";
            _clsMobile_mConfig.txtDefaultValue = "0";
            configDAO.insert(_clsMobile_mConfig);

            _clsMobile_mConfig = new clsMobile_mConfig();
            _clsMobile_mConfig.idConfig = Long.valueOf(9);
            _clsMobile_mConfig.txtName = "APPNameEDetailing";
            _clsMobile_mConfig.txtValue = "";
            _clsMobile_mConfig.txtDefaultValue = "";
            configDAO.insert(_clsMobile_mConfig);

            clsMobile_mVersionApp versionApp = new clsMobile_mVersionApp();
            versionApp.idVersion = 1;
            versionApp.txtNameApp = "Android - Call Plan BR Mobile";
            versionApp.txtVersion = "AND 2017.002 ";
            versionApp.txtGUI = "20BF6A8E-A5AB-4E77-9A30-34AC906E6FD1";
            daoMobileMVersionApp.insert(versionApp);

            return null;
        }
    }


//    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
//        @Override
//        public void onOpen(@NonNull SupportSQLiteDatabase db) {
//            super.onOpen(db);
//
//            // If you want to keep data through app restarts,
//            // comment out the following block
//            databaseWriteExecutor.execute(() -> {
//                // Populate the database in the background.
//                // If you want to start with more words, just add them.
//                // WordDao dao = INSTANCE.wordDao();
//                // dao.deleteAll();
//
//                // Word word = new Word("Hello");
//                // dao.insert(word);
//                // word = new Word("World");
//                // dao.insert(word);
//                DAOMobile_mConfig dao = INSTANCE.daoMobileMConfig();
//                DAOMobile_mVersionApp daoAppName = INSTANCE.daoMobileMVersionApp();
//                dao.deleteAll();
//                daoAppName.deleteAll();
//
//                clsMobile_mConfig _clsMobile_mConfig = new clsMobile_mConfig();
//                _clsMobile_mConfig.idConfig = Long.valueOf(1);
//                _clsMobile_mConfig.txtName = "API";
//                // _clsMobile_mConfig.txtValue="http://10.0.0.242/KN2015 _PRM_V2.WEB/VisitPlan/API/VisitPlanAPI/";
//                // _clsMobile_mConfig.txtValue="http://10.171.11.100/KN2015_PRM_V2.WEB/VisitPlan/API/VisitPlanAPI/";
//                // _clsMobile_mConfig.txtValue="http://10.171.8.44:8030/prm_v2/VisitPlan/API/VisitPlanAPI/";
//                // _clsMobile_mConfig.txtValue="http://192.168.88.12/KN2015_PRM_V2.WEB//VisitPlan/API/VisitPlanAPI/";
//                // _clsMobile_mConfig.txtValue="http://appgw.kalbenutritionals.com/api/prm/VisitPlan/API/VisitPlanAPI/";
//                // _clsMobile_mConfig.txtValue="https://prm.kalbenutritionals.com/VisitPlan/API/VisitPlanAPI/";
//                // _clsMobile_mConfig.txtDefaultValue="https://prm.kalbenutritionals.com/VisitPlan/API/VisitPlanAPI/";
//                // _clsMobile_mConfig.txtValue = clsHardCode.baseUrl;
//                // _clsMobile_mConfig.txtDefaultValue = clsHardCode.baseUrl;
//                _clsMobile_mConfig.txtValue = "http://prm.kalbenutritionals.web.id/VisitPlan/API/VisitPlanAPI/";
//                _clsMobile_mConfig.txtDefaultValue = "http://prm.kalbenutritionals.web.id/VisitPlan/API/VisitPlanAPI/";
//                // _clsMobile_mConfig.txtDefaultValue="https://10.171.10.17/KN2015_PRM_V2.WEB/VisitPlan/API/VisitPlanAPI/";
//                dao.insert(_clsMobile_mConfig);
//
//                _clsMobile_mConfig = new clsMobile_mConfig();
//                _clsMobile_mConfig.idConfig = Long.valueOf(2);
//                _clsMobile_mConfig.txtName = "Type Mobile";
//                _clsMobile_mConfig.txtValue = "Android - Call Plan BR Mobile";
//                _clsMobile_mConfig.txtDefaultValue = "Android - Call Plan BR Mobile";
//                //_clsMobile_mConfig.txtValue="Android - E-DETAILING";
//                //_clsMobile_mConfig.txtDefaultValue="Android - E-DETAILING";
//                dao.insert(_clsMobile_mConfig);
//
//                _clsMobile_mConfig = new clsMobile_mConfig();
//                _clsMobile_mConfig.idConfig = Long.valueOf(3);
//                _clsMobile_mConfig.txtName = "Background Service Online";
//                _clsMobile_mConfig.txtValue = "1000";
//                _clsMobile_mConfig.txtDefaultValue = "60000";
//                dao.insert(_clsMobile_mConfig);
//
//                _clsMobile_mConfig = new clsMobile_mConfig();
//                _clsMobile_mConfig.idConfig = Long.valueOf(4);
//                _clsMobile_mConfig.txtName = "TIMEOUT";
//                _clsMobile_mConfig.txtValue = "6000";
//                _clsMobile_mConfig.txtDefaultValue = "1000";
//                dao.insert(_clsMobile_mConfig);
//
//                _clsMobile_mConfig = new clsMobile_mConfig();
//                _clsMobile_mConfig.idConfig = Long.valueOf(5);
//                _clsMobile_mConfig.txtName = "VISIT_PLAN_GEOTAGING_RADIUS";
//                _clsMobile_mConfig.txtValue = "0";
//                _clsMobile_mConfig.txtDefaultValue = "0";
//                dao.insert(_clsMobile_mConfig);
//
//                _clsMobile_mConfig = new clsMobile_mConfig();
//                _clsMobile_mConfig.idConfig = Long.valueOf(6);
//                _clsMobile_mConfig.txtName = "VISIT_PLAN_SIZE_SAVE_PHOTO";
//                _clsMobile_mConfig.txtValue = "0";
//                _clsMobile_mConfig.txtDefaultValue = "0";
//                dao.insert(_clsMobile_mConfig);
//
//
//                _clsMobile_mConfig = new clsMobile_mConfig();
//                _clsMobile_mConfig.idConfig = Long.valueOf(7);
//                _clsMobile_mConfig.txtName = "LAST_SYNC";
//                _clsMobile_mConfig.txtValue = "0";
//                _clsMobile_mConfig.txtDefaultValue = "0";
//                dao.insert(_clsMobile_mConfig);
//
//                _clsMobile_mConfig = new clsMobile_mConfig();
//                _clsMobile_mConfig.idConfig = Long.valueOf(8);
//                _clsMobile_mConfig.txtName = "LAST_RUN_SCHEDULE";
//                _clsMobile_mConfig.txtValue = "0";
//                _clsMobile_mConfig.txtDefaultValue = "0";
//                dao.insert(_clsMobile_mConfig);
//
//                _clsMobile_mConfig = new clsMobile_mConfig();
//                _clsMobile_mConfig.idConfig = Long.valueOf(9);
//                _clsMobile_mConfig.txtName = "APPNameEDetailing";
//                _clsMobile_mConfig.txtValue = "";
//                _clsMobile_mConfig.txtDefaultValue = "";
//                dao.insert(_clsMobile_mConfig);
//
//
//            });
//        }
//    };
}
