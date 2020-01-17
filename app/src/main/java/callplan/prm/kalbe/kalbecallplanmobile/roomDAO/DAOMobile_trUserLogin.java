package callplan.prm.kalbe.kalbecallplanmobile.roomDAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import callplan.prm.kalbe.kalbecallplanmobile.model.clsMobile_trUserLogin;

/**
 * Created by rhezaTesar on 9/28/2016.
 * migrated to Android room by ghqp 12/24/2019
 */
@Dao
public interface DAOMobile_trUserLogin {

    @Query("SELECT * FROM Mobile_trUserLogin")
    List<clsMobile_trUserLogin> getAll();

    @Query("select * from mobile_truserlogin where dtLastLogin like :lastLogin")
    List<clsMobile_trUserLogin> getBydtLastLogin(String lastLogin);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(clsMobile_trUserLogin trUserLogin);

    @Delete
    void delete(clsMobile_trUserLogin trUserLogin);

    @Update
    void update(clsMobile_trUserLogin trUserLogin);

    @Query("delete from mobile_truserlogin")
    void deleteAll();
}
