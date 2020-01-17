package callplan.prm.kalbe.kalbecallplanmobile.roomDAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(clsMobile_trPOA trPOA);

    @Delete
    void delete(clsMobile_trPOA trPOA);

    @Update
    void update(clsMobile_trPOA trPOA);

    @Query("select * from mobile_trpoa where txtNamaProgram like '%' || :toString || '%'")
    List<clsMobile_trPOA> getLiketxtConsttxtNamaProgram(String toString);

    @Query("select * from mobile_trpoa where intProgramID = :txtNoKode")
    List<clsMobile_trPOA> getBytxtConstintProgramID(String txtNoKode);

    @Query("select * from mobile_trpoa where intProgramID = :intProgramID")
    List<clsMobile_trPOA> getBytxtConstintProgramID(Long intProgramID);

    @Query("delete from mobile_trpoa")
    void deleteAll();
}
