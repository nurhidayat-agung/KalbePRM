package callplan.prm.kalbe.kalbecallplanmobile.roomDAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
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

    @Insert
    void insert(clsMobile_UserJabatan visitPlanHeader);

    @Delete
    void delete(clsMobile_UserJabatan visitPlanHeader);

    @Update
    void update(clsMobile_UserJabatan visitPlanHeader);

}
