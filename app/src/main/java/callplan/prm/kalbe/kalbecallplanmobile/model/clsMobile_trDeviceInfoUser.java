package callplan.prm.kalbe.kalbecallplanmobile.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * Created by rhezaTesar on 9/28/2016.
 * migrated to Android room by ghqp 12/24/2019
 */
@Entity(tableName = "Mobile_trDeviceInfoUser")
public class clsMobile_trDeviceInfoUser {
    // This is the unique id given by the server
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "idDevice")
    public Integer idDevice;
    @Ignore
    public final String txtConstidDevice  = "idDevice";

    @ColumnInfo(name = "txtGUI")
    public String txtGUI;
    @Ignore
    public final String txtConsttxtGUI  = "txtGUI";

    @ColumnInfo(name = "txtVersion")
    public String txtVersion;
    @Ignore
    public final String txtConsttxtVersion  = "txtVersion";

    @ColumnInfo(name = "txtDevice")
    public String txtDevice;
    @Ignore
    public final String txtConsttxtDevice = "txtDevice";

    @ColumnInfo(name = "txtModel")
    public String txtModel;
    @Ignore
    public final String txtConsttxtModel = "txtModel";

    @ColumnInfo(name = "txtUserId")
    public String txtUserId;
    @Ignore
    public final String txtConsttxtUserId = "txtUserId";
}
