package callplan.prm.kalbe.kalbecallplanmobile.roomDAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import callplan.prm.kalbe.kalbecallplanmobile.model.clsMobile_MBranch;

/**
 * Created by rhezaTesar on 9/28/2016.
 * migrated to Android room by ghqp 12/24/2019
 */
@Dao
public interface DAOMobile_MBranch {

    @Query("SELECT * FROM Mobile_mBranch")
    List<clsMobile_MBranch> getAll();

    @Insert
    void insert(clsMobile_MBranch mBranch);

    @Delete
    void delete(clsMobile_MBranch mBranch);

    @Update
    void update(clsMobile_MBranch mBranch);
}
