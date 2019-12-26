package callplan.prm.kalbe.kalbecallplanmobile.roomDAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import callplan.prm.kalbe.kalbecallplanmobile.model.clsMobile_mVisitPlanCategoryDetail;

/**
 * Created by rhezaTesar on 9/28/2016.
 * migrated to Android room by ghqp 12/24/2019
 */
@Dao
public interface DAOMobile_mVisitPlanCategoryDetail {

    @Query("SELECT * FROM mVisitPlanCategoryDetail")
    List<clsMobile_mVisitPlanCategoryDetail> getAll();

    @Insert
    void insert(clsMobile_mVisitPlanCategoryDetail planCategoryDetail);

    @Delete
    void delete(clsMobile_mVisitPlanCategoryDetail planCategoryDetail);

    @Update
    void update(clsMobile_mVisitPlanCategoryDetail planCategoryDetail);

}
