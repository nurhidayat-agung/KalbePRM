package callplan.prm.kalbe.kalbecallplanmobile.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by rhezaTesar on 9/28/2016.
 * migrated to Android room by ghqp 12/24/2019
 */
@Entity(tableName = "Mobile_mBranch")
public class clsMobile_MBranch {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "IntCabangID")
    public Long IntCabangID;
    public final String txtConstIntCabangID  = "IntCabangID";

    @ColumnInfo(name = "TxtKodeCabang")
    public String TxtKodeCabang;
    public final String txtConstTxtKodeCabang  = "TxtKodeCabang";

    @ColumnInfo(name = "TxtNamaCabang")
    public String TxtNamaCabang;
    public final String txtConstTxtNamaCabang  = "TxtNamaCabang";
}
