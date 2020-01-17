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
@Entity(tableName = "Mobile_trVisitPlan_Header")
public class clsMobile_trVisitPlan_Header {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "IntVisitPlan_HeaderID")
    public Long IntVisitPlan_HeaderID;
    @Ignore
    public final String txtConstIntVisitPlan_HeaderID = "IntVisitPlan_HeaderID";

    @ColumnInfo(name = "txtGUI_Header")
    public String txtGUI_Header;
    @Ignore
    public final String txtConsttxtGUI_Header = "txtGUI_Header";

    @ColumnInfo(name = "dtDate")
    public String dtDate;
    @Ignore
    public final String txtConstdtDate = "dtDate";

    @ColumnInfo(name = "txtDeviceID")
    public String txtDeviceID;
    @Ignore
    public final String txtConsttxtDeviceID = "txtDeviceID";

    @ColumnInfo(name = "txtType")
    public String txtType;
    @Ignore
    public final String txtConsttxtType = "txtType";

    @ColumnInfo(name = "intPeriodID")
    public String intPeriodID;
    @Ignore
    public final String txtConstintPeriodID = "intPeriodID";

    @ColumnInfo(name = "txtPeriode")
    public String txtPeriode;
    @Ignore
    public final String txtConsttxtPeriode = "txtPeriode";

    @ColumnInfo(name = "intJabatan")
    public String intJabatan;
    @Ignore
    public final String txtConstintJabatan = "intJabatan";

    @ColumnInfo(name = "intBranchID")
    public String intBranchID;
    @Ignore
    public final String txtConstintBranchID = "intBranchID";

    @ColumnInfo(name = "intPegawaiID")
    public String intPegawaiID;
    @Ignore
    public final String txtConstintPegawaiID = "intPegawaiID";

    @ColumnInfo(name = "dtStartWeek")
    public String dtStartWeek;
    @Ignore
    public final String txtConstdtStartWeek = "dtStartWeek";

    @ColumnInfo(name = "dtEndWeek")
    public String dtEndWeek;
    @Ignore
    public final String txtConstdtEndWeek = "dtEndWeek";

    @ColumnInfo(name = "intStatus")
    public String intStatus;
    @Ignore
    public final String txtConstintStatus = "intStatus";

    @ColumnInfo(name = "intSubmit")
    public String intSubmit;
    @Ignore
    public final String txtConstintSubmit = "intSubmit";

    @ColumnInfo(name = "intActivePeriode")
    public String intActivePeriode;
    @Ignore
    public final String txtConstintActivePeriode = "intActivePeriode";
}
