package callplan.prm.kalbe.kalbecallplanmobile.roomDAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import callplan.prm.kalbe.kalbecallplanmobile.model.clsMobile_trPOA;

/**
 * Created by rhezaTesar on 9/28/2016.
 * migrated to Android room by ghqp 12/24/2019
 */
@Dao
public interface DAOMobile_trPOA {

    @Query("SELECT * FROM Mobile_trPOA")
    List<clsMobile_trPOA> getAll();

    @Insert
    void insert(clsMobile_trPOA trPOA);

    @Delete
    void delete(clsMobile_trPOA trPOA);

    @Update
    void update(clsMobile_trPOA trPOA);

}
