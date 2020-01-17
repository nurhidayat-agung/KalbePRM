package callplan.prm.kalbe.kalbecallplanmobile.roomDAO;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import callplan.prm.kalbe.kalbecallplanmobile.model.clsMobile_mAllbranch;

/**
 * Created by rhezaTesar on 9/28/2016.
 * migrated to Android room by ghqp 12/24/2019
 */
@Dao
public interface DAOMobile_mAllbranch {
    @Query("SELECT * FROM Mobile_mAllBranch")
    List<clsMobile_mAllbranch> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(clsMobile_mAllbranch mAllbranch);

    @Delete
    void delete(clsMobile_mAllbranch mAllbranch);

    @Query("delete from mobile_mallbranch")
    void delete();

    @Update
    void update(clsMobile_mAllbranch mAllbranch);

}
