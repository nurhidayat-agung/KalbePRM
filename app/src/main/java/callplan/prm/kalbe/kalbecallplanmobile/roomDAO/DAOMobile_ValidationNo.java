package callplan.prm.kalbe.kalbecallplanmobile.roomDAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import callplan.prm.kalbe.kalbecallplanmobile.model.clsMobile_ValidationNo;

/**
 * Created by rhezaTesar on 9/28/2016.
 * migrated to Android room by ghqp 12/24/2019
 */
@Dao
public interface DAOMobile_ValidationNo {

    @Query("SELECT * FROM Mobile_ValidationNo")
    List<clsMobile_ValidationNo> getAll();

    @Insert
    void insert(clsMobile_ValidationNo visitPlanHeader);

    @Delete
    void delete(clsMobile_ValidationNo visitPlanHeader);

    @Update
    void update(clsMobile_ValidationNo visitPlanHeader);

}

