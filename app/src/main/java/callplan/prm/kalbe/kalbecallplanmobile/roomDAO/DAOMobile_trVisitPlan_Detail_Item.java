package callplan.prm.kalbe.kalbecallplanmobile.roomDAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import callplan.prm.kalbe.kalbecallplanmobile.model.clsMobile_trVisitPlan_Detail_Item;

/**
 * Created by rhezaTesar on 9/28/2016.
 * migrated to Android room by ghqp 12/24/2019
 */
@Dao
public interface DAOMobile_trVisitPlan_Detail_Item {

    @Query("SELECT * FROM Mobile_trVisitPlan_Detail_Item")
    List<clsMobile_trVisitPlan_Detail_Item> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(clsMobile_trVisitPlan_Detail_Item planDetailItem);

    @Delete
    void delete(clsMobile_trVisitPlan_Detail_Item planDetailItem);

    @Update
    void update(clsMobile_trVisitPlan_Detail_Item planDetailItem);

    @Query("select * from mobile_trvisitplan_detail_item where txtGUI_Detail = :idGui")
    List<clsMobile_trVisitPlan_Detail_Item> getByGuiDetail(String idGui);

    @Query("select * from mobile_trvisitplan_detail_item where txtGUI_Detail like :s and intNo like :s1")
    List<clsMobile_trVisitPlan_Detail_Item> getbyGUI_DetailtxtConsintNo(String s, String s1);

    @Query("select * from mobile_trvisitplan_detail_item where txtGUI_Detail like :txtConsttxtGUI_detail order by intNo asc")
    List<clsMobile_trVisitPlan_Detail_Item> getbytxtConsttxtGUI_DetailOrdertxtConsintNo(String txtConsttxtGUI_detail);

    @Query("select * from mobile_trvisitplan_detail_item where intVisitPlan_DetailID = :intVisitPlan_detailID order by intNo asc")
    List<clsMobile_trVisitPlan_Detail_Item> getBytxtConstintVisitPlan_DetailIDorderBytxtConsintNo(Long intVisitPlan_detailID);

    @Query("delete from mobile_trvisitplan_detail_item")
    void deleteAll();
}
