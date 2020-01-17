package callplan.prm.kalbe.kalbecallplanmobile.roomDAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(clsMobile_MPartnerProfileAlias profileAlias);

    @Delete
    void delete(clsMobile_MPartnerProfileAlias profileAlias);

    @Update
    void update(clsMobile_MPartnerProfileAlias profileAlias);

    @Query("select * from mobile_mpartnerprofilealias where intPartnerIDSub = :toString")
    List<clsMobile_MPartnerProfileAlias> getBytxtConstintPartnerIDSub(String toString);

    @Query("select * from mobile_mpartnerprofilealias where IntPartnerID = :intPartnerID")
    List<clsMobile_MPartnerProfileAlias> gettxtConstIntPartnerID(Long intPartnerID);

    @Query("select * from mobile_mpartnerprofilealias where txtPartnerCRM like :txtOutletKodeSumberDataID")
    List<clsMobile_MPartnerProfileAlias> getBytxtConsttxtPartnerCRM(String txtOutletKodeSumberDataID);

    @Query("delete from mobile_mpartnerprofilealias")
    void deleteAll();
}
