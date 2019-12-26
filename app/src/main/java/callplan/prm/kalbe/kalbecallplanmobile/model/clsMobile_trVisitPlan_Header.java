package callplan.prm.kalbe.kalbecallplanmobile.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
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
    public final String txtConstIntVisitPlan_HeaderID = "IntVisitPlan_HeaderID";

    @ColumnInfo(name = "txtGUI_Header")
    public String txtGUI_Header;
    public final String txtConsttxtGUI_Header = "txtGUI_Header";

    @ColumnInfo(name = "dtDate")
    public String dtDate;
    public final String txtConstdtDate = "dtDate";

    @ColumnInfo(name = "txtDeviceID")
    public String txtDeviceID;
    public final String txtConsttxtDeviceID = "txtDeviceID";

    @ColumnInfo(name = "txtType")
    public String txtType;
    public final String txtConsttxtType = "txtType";

    @ColumnInfo(name = "intPeriodID")
    public String intPeriodID;
    public final String txtConstintPeriodID = "intPeriodID";

    @ColumnInfo(name = "txtPeriode")
    public String txtPeriode;
    public final String txtConsttxtPeriode = "txtPeriode";

    @ColumnInfo(name = "intJabatan")
    public String intJabatan;
    public final String txtConstintJabatan = "intJabatan";

    @ColumnInfo(name = "intBranchID")
    public String intBranchID;
    public final String txtConstintBranchID = "intBranchID";

    @ColumnInfo(name = "intPegawaiID")
    public String intPegawaiID;
    public final String txtConstintPegawaiID = "intPegawaiID";

    @ColumnInfo(name = "dtStartWeek")
    public String dtStartWeek;
    public final String txtConstdtStartWeek = "dtStartWeek";

    @ColumnInfo(name = "dtEndWeek")
    public String dtEndWeek;
    public final String txtConstdtEndWeek = "dtEndWeek";

    @ColumnInfo(name = "intStatus")
    public String intStatus;
    public final String txtConstintStatus = "intStatus";

    @ColumnInfo(name = "intSubmit")
    public String intSubmit;
    public final String txtConstintSubmit = "intSubmit";

    @ColumnInfo(name = "intActivePeriode")
    public String intActivePeriode;
    public final String txtConstintActivePeriode = "intActivePeriode";
}
