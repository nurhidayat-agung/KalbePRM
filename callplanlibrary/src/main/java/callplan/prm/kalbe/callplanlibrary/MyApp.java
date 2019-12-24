package callplan.prm.kalbe.callplanlibrary;

import android.app.Application;
import android.content.ContentProvider;
import android.database.sqlite.SQLiteDatabase;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;

import callplan.prm.kalbe.callplanlibrary.common.clsMobile_MBranch;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_MPartnerProfile;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_MPartnerProfileAlias;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_TipeSumberData;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_UserJabatan;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_ValidationNo;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_mAllbranch;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_mBinaryFile;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_mConfig;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_mLOB;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_mVersionApp;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_mVisitPlanCategory;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_mVisitPlanCategoryDetail;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_trDeviceInfoUser;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_trPOA;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_trUserLogin;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_trVisitPlan_Detail;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_trVisitPlan_Detail_Item;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_trVisitPlan_Header;

/**
 * Created by rhezaTesar on 9/8/2016.
 */
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //ActiveAndroid.initialize(this);
        Configuration.Builder config = new Configuration.Builder(this);
        config.setDatabaseName("callPlanMobile.db");
        config.addModelClasses(clsMobile_mBinaryFile.class, clsMobile_MBranch.class, clsMobile_mConfig.class,
                clsMobile_mLOB.class,clsMobile_MPartnerProfile.class,clsMobile_MPartnerProfileAlias.class,clsMobile_mVersionApp.class,
                clsMobile_mVisitPlanCategory.class,clsMobile_mVisitPlanCategoryDetail.class,clsMobile_trDeviceInfoUser.class,
                clsMobile_trPOA.class,clsMobile_trUserLogin.class,clsMobile_trVisitPlan_Detail.class,clsMobile_trVisitPlan_Detail_Item.class,
                clsMobile_trVisitPlan_Header.class,clsMobile_UserJabatan.class,clsMobile_ValidationNo.class,clsMobile_mAllbranch.class,clsMobile_TipeSumberData.class);

        ActiveAndroid.initialize(config.create());

    }

}
