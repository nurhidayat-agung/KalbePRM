package callplan.prm.kalbe.kalbecallplanmobile.roomDAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import callplan.prm.kalbe.kalbecallplanmobile.model.clsMobile_mLOB;


/**
 * Created by rhezaTesar on 9/28/2016.
 * migrated to Android room by ghqp 12/24/2019
 */
@Dao
public interface DAOMobile_mLOB {

    @Query("SELECT * FROM mLOB")
    List<clsMobile_mLOB> getAll();

    @Insert
    void insert(clsMobile_mLOB mLOB);

    @Delete
    void delete(clsMobile_mLOB mLOB);

    @Update
    void update(clsMobile_mLOB mLOB);
}
