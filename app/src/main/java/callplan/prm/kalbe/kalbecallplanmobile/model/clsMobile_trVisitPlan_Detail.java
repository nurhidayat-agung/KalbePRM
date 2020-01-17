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
@Entity(tableName = "Mobile_trVisitPlan_Detail")
public class clsMobile_trVisitPlan_Detail {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "txtGUI_Detail")
    public String txtGUI_Detail;
    @Ignore
    public final String txtConsttxtGUI_Detail = "txtGUI_Detail";

    @ColumnInfo(name = "intVisitPlan_DetailID")
    public Long intVisitPlan_DetailID;
    @Ignore
    public final String txtConstintVisitPlan_DetailID = "intVisitPlan_DetailID";

    @ColumnInfo(name = "intVisitPlan_HeaderID")
    public String intVisitPlan_HeaderID;
    @Ignore
    public final String txtConstintVisitPlan_HeaderID = "intVisitPlan_HeaderID";

    @ColumnInfo(name = "intLOBID")
    public String intLOBID;
    @Ignore
    public final String txtConstintLOBID = "intLOBID";

    @ColumnInfo(name = "txtLOBName")
    public String txtLOBName;
    @Ignore
    public final String txtConsttxtLOBName = "txtLOBName";

    @ColumnInfo(name = "intCategoryID")
    public String intCategoryID;
    @Ignore
    public final String txtConstintCategoryID = "intCategoryID";

    @ColumnInfo(name = "txtCategoryName")
    public String txtCategoryName;
    @Ignore
    public final String txtConsttxtCategoryName = "txtCategoryName";

    @ColumnInfo(name = "txtNoKode")
    public String txtNoKode;
    @Ignore
    public final String txtConsttxtNoKode = "txtNoKode";

    @ColumnInfo(name = "txtDescription")
    public String txtDescription;
    @Ignore
    public final String txtConsttxtDescription = "txtDescription";

    @ColumnInfo(name = "txtLongitude_Knis")
    public String txtLongitude_Knis;
    @Ignore
    public final String txtConsttxtLongitude_Knis = "txtLongitude_Knis";

    @ColumnInfo(name = "txtLatitude_Knis")
    public String txtLatitude_Knis;
    @Ignore
    public final String txtConsttxtLatitude_Knis = "txtLatitude_Knis";

    @ColumnInfo(name = "txtAccuracy_Knis")
    public String txtAccuracy_Knis;
    @Ignore
    public final String txtConsttxtAccuracy_Knis = "txtAccuracy_Knis";

    @ColumnInfo(name = "intStatus")
    public String intStatus;
    @Ignore
    public final String txtConstintStatus = "intStatus";

    @ColumnInfo(name = "dtPlanDate")
    public String dtPlanDate;
    @Ignore
    public final String txtConstdtPlanDate = "dtPlanDate";

    @ColumnInfo(name = "intBobot")
    public String intBobot;
    @Ignore
    public final String txtConstintBobot = "intBobot";

    @ColumnInfo(name = "dtActualDate")
    public String dtActualDate;
    @Ignore
    public final String txtConstdtActualDate = "dtActualDate";

    @ColumnInfo(name = "intJumlahPreNC")
    public String intJumlahPreNC;
    @Ignore
    public final String txtConstintJumlahPreNC = "intJumlahPreNC";

    @ColumnInfo(name = "intSubmit")
    public String intSubmit;
    @Ignore
    public final String txtConstintSubmit = "intSubmit";

    @ColumnInfo(name = "bitStatus")
    public String bitStatus;
    @Ignore
    public final String txtConstbitStatus = "bitStatus";

    @ColumnInfo(name = "txtValidationNo")
    public String txtValidationNo;
    @Ignore
    public final String txtConsttxtValidationNo = "txtValidationNo";

    @ColumnInfo(name = "dtValidated")
    public String dtValidated;
    @Ignore
    public final String txtConstdtValidated = "dtValidated";

    @ColumnInfo(name = "bitUnplan")
    public String bitUnplan;
    @Ignore
    public final String txtConstbitUnplan = "bitUnplan";

    @ColumnInfo(name = "intPartnerID")
    public String intPartnerID;
    @Ignore
    public final String txtConstintPartnerID = "intPartnerID";

    @ColumnInfo(name = "intPartnerIDCheckIn")
    public String intPartnerIDCheckIn;
    @Ignore
    public final String txtConstintPartnerIDCheckIn = "intPartnerIDCheckIn";
}
