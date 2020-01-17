package callplan.prm.kalbe.kalbecallplanmobile.roomDAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
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

    @Query("select * from mobile_mbinaryfile where txtGUI_IDTable = 1")
    List<clsMobile_mBinaryFile> getbytxtGUI_IDTable();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(clsMobile_mBinaryFile file);

    @Delete
    void delete(clsMobile_mBinaryFile file);

    @Update
    void update(clsMobile_mBinaryFile file);

    @Query("select * from mobile_mbinaryfile where txtGUI_IDTable like :GUI_IDTable and txtFileName like :FileName")
    List<clsMobile_mBinaryFile> getByGUI_IDTablenFileName(String GUI_IDTable, String FileName);

    @Query("select * from mobile_mbinaryfile where txtGUI_IDTable like :s and txtFileName like :s1")
    List<clsMobile_mBinaryFile> getbyGUI_IDTableFileName(String s, String s1);

    @Query("select * from mobile_mbinaryfile where txtGUI_IDTable = :txtGUI_detail_item")
    List<clsMobile_mBinaryFile> getbytxtGUI_IDTable(String txtGUI_detail_item);

    @Query("delete from mobile_mbinaryfile where txtGUI_IDTable != :i")
    void deleteEx(String i);
}
