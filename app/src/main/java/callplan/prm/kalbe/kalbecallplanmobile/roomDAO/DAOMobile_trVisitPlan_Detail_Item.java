package callplan.prm.kalbe.kalbecallplanmobile.roomDAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
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

    @Insert
    void insert(clsMobile_trVisitPlan_Detail_Item planDetailItem);

    @Delete
    void delete(clsMobile_trVisitPlan_Detail_Item planDetailItem);

    @Update
    void update(clsMobile_trVisitPlan_Detail_Item planDetailItem);

}