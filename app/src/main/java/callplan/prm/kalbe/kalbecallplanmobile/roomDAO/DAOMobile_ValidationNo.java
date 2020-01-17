package callplan.prm.kalbe.kalbecallplanmobile.roomDAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import callplan.prm.kalbe.kalbecallplanmobile.model.clsMobile_ValidationNo;

/**
 * Created by rhezaTesar on 9/28/2016.
 * migrated to Android room by ghqp 12/24/2019
 */
@Dao
public interface DAOMobile_ValidationNo {

    @Query("SELECT * FROM Mobile_ValidationNo")
    List<clsMobile_ValidationNo> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(clsMobile_ValidationNo visitPlanHeader);

    @Delete
    void delete(clsMobile_ValidationNo visitPlanHeader);

    @Update
    void update(clsMobile_ValidationNo visitPlanHeader);

    @Query("select * from mobile_validationno where BitUse like :txtConstBitUse")
    List<clsMobile_ValidationNo> getBytxtConstBitUse(String txtConstBitUse);

    @Query("select * from mobile_validationno where BitUse like :txtConstBitUse limit :limit")
    List<clsMobile_ValidationNo> getBytxtConstBitUseLimit(String txtConstBitUse, Integer limit);

    @Query("select * from mobile_validationno where txtValidationNo = :toString")
    List<clsMobile_ValidationNo> getbyConsttxtValidationNo(String toString);

    @Query("select * from mobile_validationno where txtValidationNo = :toString and BitUse = :i")
    List<clsMobile_ValidationNo> getbyValidationNoBitUse(String toString, String i);

    @Query("delete from mobile_validationno")
    void deleteAll();
}

