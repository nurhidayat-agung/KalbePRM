package callplan.prm.kalbe.kalbecallplanmobile.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by rhezaTesar on 9/28/2016.
 * migrated to Android room by ghqp 12/24/2019
 */
@Entity(tableName = "Mobile_trPOA")
public class clsMobile_trPOA {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "intProgramID")
    public Long intProgramID;
    public final String txtConstintProgramID  = "intProgramID";

    @ColumnInfo(name = "txtNamaProgram")
    public String txtNamaProgram;
    public final String txtConsttxtNamaProgram  = "txtNamaProgram";

    @ColumnInfo(name = "dtStartDate")
    public String dtStartDate;
    public final String txtConstdtStartDate  = "dtStartDate";

    @ColumnInfo(name = "dtEndDate")
    public String dtEndDate;
    public final String txtConstdtEndDate  = "dtEndDate";

    @ColumnInfo(name = "txtProgramDescription")
    public String txtProgramDescription;
    public final String txtConsttxtProgramDescription  = "txtProgramDescription";

    @ColumnInfo(name = "txtServer")
    public String txtServer;
    public final String txtConsttxtServer  = "txtServer";
}
