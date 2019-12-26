package callplan.prm.kalbe.kalbecallplanmobile.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by rhezaTesar on 9/28/2016.
 * migrated to Android room by ghqp 12/24/2019
 */
@Entity(tableName = "Mobile_mVersionApp")
public class clsMobile_mVersionApp {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "idVersion")
    public Integer idVersion;
    public final String txtConstidVersion  = "idVersion";

    @ColumnInfo(name = "txtGUI")
    public String txtGUI;
    public final String txtConsttxtGUI  = "TxtGUI";

    @ColumnInfo(name = "txtNameApp")
    public String txtNameApp;
    public final String txtConsttxtNameApp  = "TxtNameApp";

    @ColumnInfo(name = "txtVersion")
    public String txtVersion;
    public final String txtConsttxtVersion  = "TxtVersion";

    @ColumnInfo(name = "txtFile")
    public String txtFile;
    public final String txtConsttxtFile  = "TxtFile";
}
