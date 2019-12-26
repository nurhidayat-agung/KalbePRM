package callplan.prm.kalbe.kalbecallplanmobile.roomDAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
import callplan.prm.kalbe.kalbecallplanmobile.model.clsMobile_mBinaryFile;

/**
 * Created by rhezaTesar on 9/28/2016.
 * migrated to Android room by ghqp 12/24/2019
 */
@Dao
public interface DAOMobile_mBinaryFile {

    @Query("SELECT * FROM Mobile_mBinaryFile")
    List<clsMobile_mBinaryFile> getAll();

    @Insert
    void insert(clsMobile_mBinaryFile file);

    @Delete
    void delete(clsMobile_mBinaryFile file);

    @Update
    void update(clsMobile_mBinaryFile file);

}
