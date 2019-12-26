package callplan.prm.kalbe.kalbecallplanmobile.roomDAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import callplan.prm.kalbe.kalbecallplanmobile.model.clsMobile_MPartnerProfileAlias;

/**
 * Created by rhezaTesar on 9/28/2016.
 * migrated to Android room by ghqp 12/24/2019
 */
@Dao
public interface DAOMobile_MPartnerProfileAlias {

    @Query("SELECT * FROM Mobile_MPartnerProfileAlias")
    List<clsMobile_MPartnerProfileAlias> getAll();

    @Insert
    void insert(clsMobile_MPartnerProfileAlias profileAlias);

    @Delete
    void delete(clsMobile_MPartnerProfileAlias profileAlias);

    @Update
    void update(clsMobile_MPartnerProfileAlias profileAlias);

}
