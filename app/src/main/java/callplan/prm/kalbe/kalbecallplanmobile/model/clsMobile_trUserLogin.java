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
@Entity(tableName = "Mobile_trUserLogin")
public class clsMobile_trUserLogin {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "idUserLogin")
    public Integer idUserLogin;
    @Ignore
    public final String txtConstidUserLogin  = "idUserLogin";

    @ColumnInfo(name = "IntCabangID")
    public String IntCabangID;
    @Ignore
    public final String txtConstidIntCabangID  = "IntCabangID";

    @ColumnInfo(name = "txtGUI")
    public String txtGUI;
    @Ignore
    public final String txtConsttxtGUI  = "txtGUI";

    @ColumnInfo(name = "txtUserID")
    public String txtUserID;
    @Ignore
    public final String txtConsttxtUserID  = "txtUserID";

    @ColumnInfo(name = "txtRoleID")
    public String txtRoleID;
    @Ignore
    public final String txtConsttxtRoleID  = "txtRoleID";

    @ColumnInfo(name = "txtRoleName")
    public String txtRoleName;
    @Ignore
    public final String txtConsttxtRoleName = "txtRoleName";

    @ColumnInfo(name = "txtPathImage")
    public String txtPathImage;
    @Ignore
    public final String txtConsttxtPathImage = "txtPathImage";

    @ColumnInfo(name = "txtUserName")
    public String txtUserName;
    @Ignore
    public final String txtConsttxtUserName = "txtUserName";

    @ColumnInfo(name = "txtName")
    public String txtName;
    @Ignore
    public final String txtConsttxtName = "txtName";

    @ColumnInfo(name = "txtEmail")
    public String txtEmail;
    @Ignore
    public final String txtConsttxtEmail = "txtEmail";

    @ColumnInfo(name = "txtEmpID")
    public String txtEmpID;
    @Ignore
    public final String txtConsttxtEmpID = "txtEmpID";

    @ColumnInfo(name = "txtBranchCode")
    public String txtBranchCode;
    @Ignore
    public final String txtConsttxtBranchCode = "txtBranchCode";

    @ColumnInfo(name = "txtOutletCode")
    public String txtOutletCode;
    @Ignore
    public final String txtConsttxtOutletCode = "txtOutletCode";

    @ColumnInfo(name = "txtOutletName")
    public String txtOutletName;
    @Ignore
    public final String txtConsttxtOutletName = "txtOutletName";

    @ColumnInfo(name = "dtLastLogin")
    public String dtLastLogin;
    @Ignore
    public final String txtConstdtLastLogin = "dtLastLogin";

    @ColumnInfo(name = "txtDeviceId")
    public String txtDeviceId;
    @Ignore
    public final String txtConsttxtDeviceId = "txtDeviceId";

    @ColumnInfo(name = "dtCheckIn")
    public String dtCheckIn;
    @Ignore
    public final String txtConstdtCheckIn = "dtCheckIn";

    @ColumnInfo(name = "dtCheckOut")
    public String dtCheckOut;
    @Ignore
    public final String txtConstdtCheckOut = "dtCheckOut";

    @ColumnInfo(name = "dtLogOut")
    public String dtLogOut;
    @Ignore
    public final String txtConstdtLogOut = "dtLogOut";
}
