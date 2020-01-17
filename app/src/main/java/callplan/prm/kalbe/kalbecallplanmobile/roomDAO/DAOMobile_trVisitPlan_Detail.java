package callplan.prm.kalbe.kalbecallplanmobile.roomDAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

import callplan.prm.kalbe.kalbecallplanmobile.model.clsMobile_trVisitPlan_Detail;

/**
 * Created by rhezaTesar on 9/28/2016.
 * migrated to Android room by ghqp 12/24/2019
 */
@Dao
public interface DAOMobile_trVisitPlan_Detail {

    @Query("SELECT * FROM Mobile_trVisitPlan_Detail")
    List<clsMobile_trVisitPlan_Detail> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(clsMobile_trVisitPlan_Detail visitPlanDetail);

    @Delete
    void delete(clsMobile_trVisitPlan_Detail visitPlanDetail);

    @Update
    void update(clsMobile_trVisitPlan_Detail visitPlanDetail);

    @Query("select * from mobile_trvisitplan_detail where intSubmit = :intSubmit limit :intLimit")
    List<clsMobile_trVisitPlan_Detail> getByIntSubmitLimit(int intSubmit, int intLimit);

    @Query("select * from mobile_trvisitplan_detail where intSubmit = :intSubmit")
    List<clsMobile_trVisitPlan_Detail> getByIntSubmit(int intSubmit);

    @Query("select * from mobile_trvisitplan_detail where intSubmit = :intSubmit group by intVisitPlan_HeaderID")
    List<clsMobile_trVisitPlan_Detail> getByIntSubmitGroupByintVisitPlan_HeaderID(int intSubmit);

    @Query("delete from mobile_trvisitplan_detail")
    void delete();

    @Query("select * from mobile_trvisitplan_detail where dtPlanDate not null order by dtPlanDate desc")
    List<clsMobile_trVisitPlan_Detail> gettxtConstdtPlanDateNotNullOrdertxtConstdtPlanDate();

    @Query("select * from mobile_trvisitplan_detail where dtPlanDate is null")
    List<clsMobile_trVisitPlan_Detail> gettxtConstdtPlanDateNull();

    @Query("select * from mobile_trvisitplan_detail where dtPlanDate is not null and intSubmit in (:listSubmit)")
    List<clsMobile_trVisitPlan_Detail> getBytxtConstdtPlanDateNotNullAndtxtConstintSubmit(List<String> listSubmit);

    @Query("select * from mobile_trvisitplan_detail where intVisitPlan_DetailID = :intVisitPlan")
    List<clsMobile_trVisitPlan_Detail> gettxtConstintVisitPlan_DetailID(String intVisitPlan);

    @Query("select * from mobile_trvisitplan_detail where txtGUI_Detail like :txtGuidDetail")
    List<clsMobile_trVisitPlan_Detail> getBytxtConsttxtGUI_Detail(String txtGuidDetail);

    @Query("select * from mobile_trvisitplan_detail where intVisitPlan_DetailID = :intVisitPlan_detailID")
    List<clsMobile_trVisitPlan_Detail> gettxtConstintVisitPlan_DetailID(Long intVisitPlan_detailID);

    @Query("select * from mobile_trvisitplan_detail where intSubmit in (:strings)")
    List<clsMobile_trVisitPlan_Detail> getByIntSubmitLimit(ArrayList<String> strings);

    @Query("delete from mobile_trvisitplan_detail")
    void deleteAll();
}
