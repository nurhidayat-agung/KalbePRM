package callplan.prm.kalbe.callplanlibrary.common;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

/**
 * Created by rhezaTesar on 8/23/2016.
 */
@Table(name = "Mobile_trVisitPlan_Header")
public class clsMobile_trVisitPlan_Header extends Model {

    @Column(name = "IntVisitPlan_HeaderID")
    public Long IntVisitPlan_HeaderID;
    public final String txtConstIntVisitPlan_HeaderID = "IntVisitPlan_HeaderID";
    @Column(name = "txtGUI_Header")
    public String txtGUI_Header;
    public final String txtConsttxtGUI_Header = "txtGUI_Header";

    @Column(name = "dtDate")
    public String dtDate;
    public final String txtConstdtDate = "dtDate";

    @Column(name = "txtDeviceID")
    public String txtDeviceID;
    public final String txtConsttxtDeviceID = "txtDeviceID";

    @Column(name = "txtType")
    public String txtType;
    public final String txtConsttxtType = "txtType";

    @Column(name = "intPeriodID")
    public String intPeriodID;
    public final String txtConstintPeriodID = "intPeriodID";

    @Column(name = "txtPeriode")
    public String txtPeriode;
    public final String txtConsttxtPeriode = "txtPeriode";

    @Column(name = "intJabatan")
    public String intJabatan;
    public final String txtConstintJabatan = "intJabatan";

    @Column(name = "intBranchID")
    public String intBranchID;
    public final String txtConstintBranchID = "intBranchID";

    @Column(name = "intPegawaiID")
    public String intPegawaiID;
    public final String txtConstintPegawaiID = "intPegawaiID";

    @Column(name = "dtStartWeek")
    public String dtStartWeek;
    public final String txtConstdtStartWeek = "dtStartWeek";

    @Column(name = "dtEndWeek")
    public String dtEndWeek;
    public final String txtConstdtEndWeek = "dtEndWeek";

    @Column(name = "intStatus")
    public String intStatus;
    public final String txtConstintStatus = "intStatus";

    @Column(name = "intSubmit")
    public String intSubmit;
    public final String txtConstintSubmit = "intSubmit";

    @Column(name = "intActivePeriode")
    public String intActivePeriode;
    public final String txtConstintActivePeriode = "intActivePeriode";
}
