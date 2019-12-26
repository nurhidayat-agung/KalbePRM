package callplan.prm.kalbe.kalbecallplanmobile.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by rhezaTesar on 9/28/2016.
 * migrated to Android room by ghqp 12/24/2019
 */
@Entity(tableName = "Mobile_trUserLogin")
public class clsMobile_trUserLogin {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "idUserLogin")
    public Integer idUserLogin;
    public final String txtConstidUserLogin  = "idUserLogin";

    @ColumnInfo(name = "IntCabangID")
    public String IntCabangID;
    public final String txtConstidIntCabangID  = "IntCabangID";

    @ColumnInfo(name = "txtGUI")
    public String txtGUI;
    public final String txtConsttxtGUI  = "txtGUI";

    @ColumnInfo(name = "txtUserID")
    public String txtUserID;
    public final String txtConsttxtUserID  = "txtUserID";

    @ColumnInfo(name = "txtRoleID")
    public String txtRoleID;
    public final String txtConsttxtRoleID  = "txtRoleID";

    @ColumnInfo(name = "txtRoleName")
    public String txtRoleName;
    public final String txtConsttxtRoleName = "txtRoleName";

    @ColumnInfo(name = "txtPathImage")
    public String txtPathImage;
    public final String txtConsttxtPathImage = "txtPathImage";

    @ColumnInfo(name = "txtUserName")
    public String txtUserName;
    public final String txtConsttxtUserName = "txtUserName";

    @ColumnInfo(name = "txtName")
    public String txtName;
    public final String txtConsttxtName = "txtName";

    @ColumnInfo(name = "txtEmail")
    public String txtEmail;
    public final String txtConsttxtEmail = "txtEmail";

    @ColumnInfo(name = "txtEmpID")
    public String txtEmpID;
    public final String txtConsttxtEmpID = "txtEmpID";

    @ColumnInfo(name = "txtBranchCode")
    public String txtBranchCode;
    public final String txtConsttxtBranchCode = "txtBranchCode";

    @ColumnInfo(name = "txtOutletCode")
    public String txtOutletCode;
    public final String txtConsttxtOutletCode = "txtOutletCode";

    @ColumnInfo(name = "txtOutletName")
    public String txtOutletName;
    public final String txtConsttxtOutletName = "txtOutletName";

    @ColumnInfo(name = "dtLastLogin")
    public String dtLastLogin;
    public final String txtConstdtLastLogin = "dtLastLogin";

    @ColumnInfo(name = "txtDeviceId")
    public String txtDeviceId;
    public final String txtConsttxtDeviceId = "txtDeviceId";

    @ColumnInfo(name = "dtCheckIn")
    public String dtCheckIn;
    public final String txtConstdtCheckIn = "dtCheckIn";

    @ColumnInfo(name = "dtCheckOut")
    public String dtCheckOut;
    public final String txtConstdtCheckOut = "dtCheckOut";

    @ColumnInfo(name = "dtLogOut")
    public String dtLogOut;
    public final String txtConstdtLogOut = "dtLogOut";
}
