package callplan.prm.kalbe.kalbecallplanmobile.roomDAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import callplan.prm.kalbe.kalbecallplanmobile.model.clsMobile_mConfig;


/**
 * Created by rhezaTesar on 9/28/2016.
 * migrated to Android room by ghqp 12/24/2019
 */
@Dao
public interface DAOMobile_mConfig {

    @Query("SELECT * FROM Mobile_mConfig")
    List<clsMobile_mConfig> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(clsMobile_mConfig mConfig);

    @Delete
    void delete(clsMobile_mConfig mConfig);

    @Update
    void update(clsMobile_mConfig mConfig);

    @Query("select * from mobile_mconfig where idConfig = :idConfig")
    List<clsMobile_mConfig> getbyIdConfig(int idConfig);

    @Query("DELETE FROM Mobile_mConfig")
    void deleteAll();

}
