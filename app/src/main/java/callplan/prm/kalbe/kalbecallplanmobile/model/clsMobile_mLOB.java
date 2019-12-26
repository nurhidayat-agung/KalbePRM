package callplan.prm.kalbe.kalbecallplanmobile.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


/**
 * Created by rhezaTesar on 9/28/2016.
 * migrated to Android room by ghqp 12/24/2019
 */
@Entity(tableName = "mLOB")
public class clsMobile_mLOB {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "intLOBID")
    public Long intLOBID;
    public final String txtConstintLOBID  = "intLOBID";

    @ColumnInfo(name = "txtLOBName")
    public String txtLOBName;
    public final String txtConsttxtLOBName  = "txtLOBName";

    @ColumnInfo(name = "txtLOBDescription")
    public String txtLOBDescription;
    public final String txtConsttxtLOBDescription  = "txtLOBDescription";
}