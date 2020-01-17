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
@Entity(tableName = "Mobile_UserJabatan")
public class clsMobile_UserJabatan {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "IntJabatanID")
    public long IntJabatanID;

    @ColumnInfo(name = "TxtJabatanName")
    public String TxtJabatanName;

    @ColumnInfo(name = "TxtJabatanDesc")
    public String TxtJabatanDesc;

    @ColumnInfo(name = "BitActive")
    public long BitActive;

    @ColumnInfo(name = "BitPrimary")
    public String BitPrimary;
    @Ignore
    public final String txtConstBitPrimary = "BitPrimary";

    @ColumnInfo(name = "txtLimit")
    public String txtLimit;
}
