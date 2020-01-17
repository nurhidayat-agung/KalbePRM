package callplan.prm.kalbe.kalbecallplanmobile.roomDAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import callplan.prm.kalbe.kalbecallplanmobile.model.clsMobile_UserJabatan;

/**
 * Created by rhezaTesar on 9/28/2016.
 * migrated to Android room by ghqp 12/24/2019
 */
@Dao
public interface DAOMobile_UserJabatan {

    @Query("SELECT * FROM Mobile_UserJabatan")
    List<clsMobile_UserJabatan> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(clsMobile_UserJabatan visitPlanHeader);

    @Delete
    void delete(clsMobile_UserJabatan visitPlanHeader);

    @Update
    void update(clsMobile_UserJabatan visitPlanHeader);

    @Query("select * from mobile_userjabatan where BitPrimary like :bitPrimary")
    List<clsMobile_UserJabatan> getBytxtConstBitPrimary(String bitPrimary);

    @Query("delete from mobile_userjabatan")
    void deleteAll();
}
