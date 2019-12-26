package callplan.prm.kalbe.kalbecallplanmobile.roomDAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import callplan.prm.kalbe.kalbecallplanmobile.model.clsMobile_trVisitPlan_Header;

/**
 * Created by rhezaTesar on 9/28/2016.
 * migrated to Android room by ghqp 12/24/2019
 */
@Dao
public interface DAOMobile_trVisitPlan_Header {

    @Query("SELECT * FROM Mobile_trVisitPlan_Header")
    List<clsMobile_trVisitPlan_Header> getAll();

    @Insert
    void insert(clsMobile_trVisitPlan_Header visitPlanHeader);

    @Delete
    void delete(clsMobile_trVisitPlan_Header visitPlanHeader);

    @Update
    void update(clsMobile_trVisitPlan_Header visitPlanHeader);

}
