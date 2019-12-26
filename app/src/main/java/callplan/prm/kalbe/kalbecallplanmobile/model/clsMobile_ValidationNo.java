package callplan.prm.kalbe.kalbecallplanmobile.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by rhezaTesar on 9/28/2016.
 * migrated to Android room by ghqp 12/24/2019
 */
@Entity(tableName = "Mobile_ValidationNo")
public class clsMobile_ValidationNo {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "txtValidationNo")
    public String txtValidationNo;
    public final String txtConsttxtValidationNo = "txtValidationNo";

    @ColumnInfo(name = "BitUse")
    public String bitUse;
    public final String txtConstBitUse = "BitUse";
}

