package callplan.prm.kalbe.callplanlibrary.common;

import android.database.sqlite.SQLiteDatabase;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

/**
 * Created by rhezaTesar on 8/23/2016.
 */
@Table(name = "Mobile_trVisitPlan_Detail")
public class clsMobile_trVisitPlan_Detail extends Model {

    @Column(name = "txtGUI_Detail")
    public String txtGUI_Detail;
    public final String txtConsttxtGUI_Detail = "txtGUI_Detail";

    @Column(name = "intVisitPlan_DetailID")
    public Long intVisitPlan_DetailID;
    public final String txtConstintVisitPlan_DetailID = "intVisitPlan_DetailID";

    @Column(name = "intVisitPlan_HeaderID")
    public String intVisitPlan_HeaderID;
    public final String txtConstintVisitPlan_HeaderID = "intVisitPlan_HeaderID";

    @Column(name = "intLOBID")
    public String intLOBID;
    public final String txtConstintLOBID = "intLOBID";

    @Column(name = "txtLOBName")
    public String txtLOBName;
    public final String txtConsttxtLOBName = "txtLOBName";

    @Column(name = "intCategoryID")
    public String intCategoryID;
    public final String txtConstintCategoryID = "intCategoryID";

    @Column(name = "txtCategoryName")
    public String txtCategoryName;
    public final String txtConsttxtCategoryName = "txtCategoryName";

    @Column(name = "txtNoKode")
    public String txtNoKode;
    public final String txtConsttxtNoKode = "txtNoKode";

    @Column(name = "txtDescription")
    public String txtDescription;
    public final String txtConsttxtDescription = "txtDescription";

    @Column(name = "txtLongitude_Knis")
    public String txtLongitude_Knis;
    public final String txtConsttxtLongitude_Knis = "txtLongitude_Knis";

    @Column(name = "txtLatitude_Knis")
    public String txtLatitude_Knis;
    public final String txtConsttxtLatitude_Knis = "txtLatitude_Knis";

    @Column(name = "txtAccuracy_Knis")
    public String txtAccuracy_Knis;
    public final String txtConsttxtAccuracy_Knis = "txtAccuracy_Knis";

    @Column(name = "intStatus")
    public String intStatus;
    public final String txtConstintStatus = "intStatus";

    @Column(name = "dtPlanDate")
    public String dtPlanDate;
    public final String txtConstdtPlanDate = "dtPlanDate";

    @Column(name = "intBobot")
    public String intBobot;
    public final String txtConstintBobot = "intBobot";

    @Column(name = "dtActualDate")
    public String dtActualDate;
    public final String txtConstdtActualDate = "dtActualDate";

    @Column(name = "intJumlahPreNC")
    public String intJumlahPreNC;
    public final String txtConstintJumlahPreNC = "intJumlahPreNC";

    @Column(name = "intSubmit")
    public String intSubmit;
    public final String txtConstintSubmit = "intSubmit";

    @Column(name = "bitStatus")
    public String bitStatus;
    public final String txtConstbitStatus = "bitStatus";

    @Column(name = "txtValidationNo")
    public String txtValidationNo;
    public final String txtConsttxtValidationNo = "txtValidationNo";

    @Column(name = "dtValidated")
    public String dtValidated;
    public final String txtConstdtValidated = "dtValidated";

    @Column(name = "bitUnplan")
    public String bitUnplan;
    public final String txtConstbitUnplan = "bitUnplan";

    @Column(name = "intPartnerID")
    public String intPartnerID;
    public final String txtConstintPartnerID = "intPartnerID";

    @Column(name = "intPartnerIDCheckIn")
    public String intPartnerIDCheckIn;
    public final String txtConstintPartnerIDCheckIn = "intPartnerIDCheckIn";
}
