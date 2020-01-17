package callplan.prm.kalbe.kalbecallplanmobile.roomDAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import callplan.prm.kalbe.kalbecallplanmobile.model.clsMobile_MPartnerProfile;

/**
 * Created by rhezaTesar on 9/28/2016.
 * migrated to Android room by ghqp 12/24/2019
 */
@Dao
public interface DAOMobile_MPartnerProfile {

    @Query("SELECT * FROM Mobile_MPartnerProfile")
    List<clsMobile_MPartnerProfile> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(clsMobile_MPartnerProfile profile);

    @Delete
    void delete(clsMobile_MPartnerProfile profile);

    @Update
    void update(clsMobile_MPartnerProfile profile);

    @Query("select * from mobile_mpartnerprofilealias where txtPartnerCRM like :txtConsttxtPartnerCRM")
    List<clsMobile_MPartnerProfile> getBytxtConsttxtPartnerCRM(String txtConsttxtPartnerCRM);

    @Query("select * from mobile_mpartnerprofile where IntPartnerID = :intPartnerID")
    List<clsMobile_MPartnerProfile> gettxtConstIntPartnerID(Long intPartnerID);

    @Query("delete from mobile_mpartnerprofile")
    void delete();
}
