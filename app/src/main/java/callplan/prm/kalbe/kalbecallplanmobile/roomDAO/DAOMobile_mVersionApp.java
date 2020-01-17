package callplan.prm.kalbe.kalbecallplanmobile.roomDAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import callplan.prm.kalbe.kalbecallplanmobile.model.clsMobile_mVersionApp;

/**
 * Created by rhezaTesar on 9/28/2016.
 * migrated to Android room by ghqp 12/24/2019
 */
@Dao
public interface DAOMobile_mVersionApp {

    @Query("SELECT * FROM Mobile_mVersionApp")
    List<clsMobile_mVersionApp> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(clsMobile_mVersionApp mVersionApp);

    @Delete
    void delete(clsMobile_mVersionApp mVersionApp);

    @Update
    void update(clsMobile_mVersionApp mVersionApp);

    @Query("select * from mobile_mversionapp where idVersion = :idVersion")
    List<clsMobile_mVersionApp> getByIDVersion(String idVersion);

    @Query("delete from mobile_mversionapp")
    void deleteAll();
}
