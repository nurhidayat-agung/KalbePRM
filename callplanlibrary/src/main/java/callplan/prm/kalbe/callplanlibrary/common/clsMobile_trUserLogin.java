package callplan.prm.kalbe.callplanlibrary.common;

import android.database.sqlite.SQLiteDatabase;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by rhezaTesar on 8/23/2016.
 */
@Table(name = "Mobile_trUserLogin")
public class clsMobile_trUserLogin extends Model {

    @Column(name = "idUserLogin", unique = true)
    public Integer idUserLogin;
    public final String txtConstidUserLogin  = "idUserLogin";

    @Column(name = "IntCabangID")
    public String IntCabangID;
    public final String txtConstidIntCabangID  = "IntCabangID";

    @Column(name = "txtGUI")
    public String txtGUI;
    public final String txtConsttxtGUI  = "txtGUI";

    @Column(name = "txtUserID")
    public String txtUserID;
    public final String txtConsttxtUserID  = "txtUserID";

    @Column(name = "txtRoleID")
    public String txtRoleID;
    public final String txtConsttxtRoleID  = "txtRoleID";

    @Column(name = "txtRoleName")
    public String txtRoleName;
    public final String txtConsttxtRoleName = "txtRoleName";

    @Column(name = "txtPathImage")
    public String txtPathImage;
    public final String txtConsttxtPathImage = "txtPathImage";

    @Column(name = "txtUserName")
    public String txtUserName;
    public final String txtConsttxtUserName = "txtUserName";

    @Column(name = "txtName")
    public String txtName;
    public final String txtConsttxtName = "txtName";

    @Column(name = "txtEmail")
    public String txtEmail;
    public final String txtConsttxtEmail = "txtEmail";

    @Column(name = "txtEmpID")
    public String txtEmpID;
    public final String txtConsttxtEmpID = "txtEmpID";

    @Column(name = "txtBranchCode")
    public String txtBranchCode;
    public final String txtConsttxtBranchCode = "txtBranchCode";

    @Column(name = "txtOutletCode")
    public String txtOutletCode;
    public final String txtConsttxtOutletCode = "txtOutletCode";

    @Column(name = "txtOutletName")
    public String txtOutletName;
    public final String txtConsttxtOutletName = "txtOutletName";

    @Column(name = "dtLastLogin")
    public String dtLastLogin;
    public final String txtConstdtLastLogin = "dtLastLogin";

    @Column(name = "txtDeviceId")
    public String txtDeviceId;
    public final String txtConsttxtDeviceId = "txtDeviceId";

    @Column(name = "dtCheckIn")
    public String dtCheckIn;
    public final String txtConstdtCheckIn = "dtCheckIn";

    @Column(name = "dtCheckOut")
    public String dtCheckOut;
    public final String txtConstdtCheckOut = "dtCheckOut";

    @Column(name = "dtLogOut")
    public String dtLogOut;
    public final String txtConstdtLogOut = "dtLogOut";
}
