package callplan.prm.kalbe.kalbecallplanmobile.roomDAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import callplan.prm.kalbe.kalbecallplanmobile.model.clsMobile_trDeviceInfoUser;

/**
 * Created by rhezaTesar on 9/28/2016.
 * migrated to Android room by ghqp 12/24/2019
 */
@Dao
public interface DAOMobile_trDeviceInfoUser {

    @Query("SELECT * FROM Mobile_trDeviceInfoUser")
    List<clsMobile_trDeviceInfoUser> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(clsMobile_trDeviceInfoUser deviceInfoUser);

    @Delete
    void delete(clsMobile_trDeviceInfoUser deviceInfoUser);

    @Update
    void update(clsMobile_trDeviceInfoUser deviceInfoUser);

    @Query("select * from mobile_trdeviceinfouser where idDevice = :idDevice")
    List<clsMobile_trDeviceInfoUser> getbyId(int idDevice);

    @Query("delete from Mobile_trDeviceInfoUser")
    void deleteAll();
}
