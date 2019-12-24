package kalbenutritionals.bridgeapi.common;

import java.io.Serializable;

/**
 * Created by arick.anjasmara on 12/29/2016.
 */

public class clsMobile_trUserLoginAPI implements Serializable {

    public String getDtLogOut() {
        return dtLogOut;
    }

    public void setDtLogOut(String dtLogOut) {
        this.dtLogOut = dtLogOut;
    }

    public String getIdUserLogin() {
        return idUserLogin;
    }

    public void setIdUserLogin(String idUserLogin) {
        this.idUserLogin = idUserLogin;
    }

    public String getIntCabangID() {
        return IntCabangID;
    }

    public void setIntCabangID(String intCabangID) {
        IntCabangID = intCabangID;
    }

    public String getTxtGUI() {
        return txtGUI;
    }

    public void setTxtGUI(String txtGUI) {
        this.txtGUI = txtGUI;
    }

    public String getTxtUserID() {
        return txtUserID;
    }

    public void setTxtUserID(String txtUserID) {
        this.txtUserID = txtUserID;
    }

    public String getTxtRoleID() {
        return txtRoleID;
    }

    public void setTxtRoleID(String txtRoleID) {
        this.txtRoleID = txtRoleID;
    }

    public String getTxtRoleName() {
        return txtRoleName;
    }

    public void setTxtRoleName(String txtRoleName) {
        this.txtRoleName = txtRoleName;
    }

    public String getTxtPathImage() {
        return txtPathImage;
    }

    public void setTxtPathImage(String txtPathImage) {
        this.txtPathImage = txtPathImage;
    }

    public String getTxtUserName() {
        return txtUserName;
    }

    public void setTxtUserName(String txtUserName) {
        this.txtUserName = txtUserName;
    }

    public String getTxtName() {
        return txtName;
    }

    public void setTxtName(String txtName) {
        this.txtName = txtName;
    }

    public String getTxtEmail() {
        return txtEmail;
    }

    public void setTxtEmail(String txtEmail) {
        this.txtEmail = txtEmail;
    }

    public String getTxtEmpID() {
        return txtEmpID;
    }

    public void setTxtEmpID(String txtEmpID) {
        this.txtEmpID = txtEmpID;
    }

    public String getTxtBranchCode() {
        return txtBranchCode;
    }

    public void setTxtBranchCode(String txtBranchCode) {
        this.txtBranchCode = txtBranchCode;
    }

    public String getTxtOutletCode() {
        return txtOutletCode;
    }

    public void setTxtOutletCode(String txtOutletCode) {
        this.txtOutletCode = txtOutletCode;
    }

    public String getTxtOutletName() {
        return txtOutletName;
    }

    public void setTxtOutletName(String txtOutletName) {
        this.txtOutletName = txtOutletName;
    }

    public String getDtLastLogin() {
        return dtLastLogin;
    }

    public void setDtLastLogin(String dtLastLogin) {
        this.dtLastLogin = dtLastLogin;
    }

    public String getTxtDeviceId() {
        return txtDeviceId;
    }

    public void setTxtDeviceId(String txtDeviceId) {
        this.txtDeviceId = txtDeviceId;
    }

    public String getDtCheckIn() {
        return dtCheckIn;
    }

    public void setDtCheckIn(String dtCheckIn) {
        this.dtCheckIn = dtCheckIn;
    }

    public String getDtCheckOut() {
        return dtCheckOut;
    }

    public void setDtCheckOut(String dtCheckOut) {
        this.dtCheckOut = dtCheckOut;
    }

    private String idUserLogin;
    private String IntCabangID;
    private String txtGUI;
    private String txtUserID;
    private String txtRoleID;
    private String txtRoleName;
    private String txtPathImage;
    private String txtUserName;
    private String txtName;
    private String txtEmail;
    private String txtEmpID;
    private String txtBranchCode;
    private String txtOutletCode;
    private String txtOutletName;
    private String dtLastLogin;
    private String txtDeviceId;
    private String dtCheckIn;
    private String dtCheckOut;
    private String dtLogOut;
    private String txtWarn;

    public clsMobile_trUserLoginAPI(String idUserLogin, String IntCabangID, String txtGUI,
            String txtUserID,
            String txtRoleID,
            String txtRoleName,
            String txtPathImage,
            String txtUserName,
            String txtName,
            String txtEmail,
            String txtEmpID,
            String txtBranchCode,
            String txtOutletCode,
            String txtOutletName,
            String dtLastLogin,
            String txtDeviceId,
            String dtCheckIn,
            String dtCheckOut,
            String dtLogOut,
            String txtWarn) {

        this.idUserLogin = idUserLogin;
        this.IntCabangID = IntCabangID;
        this.txtGUI = txtGUI;
        this.txtUserID = txtUserID;
        this.txtRoleID = txtRoleID;
        this.txtRoleName = txtRoleName;
        this.txtPathImage = txtPathImage;
        this.txtUserName = txtUserName;
        this.txtName = txtName;
        this.txtEmail = txtEmail;
        this.txtEmpID = txtEmpID;
        this.txtBranchCode = txtBranchCode;
        this.txtOutletCode = txtOutletCode;
        this.txtOutletName = txtOutletName;
        this.dtLastLogin = dtLastLogin;
        this.txtDeviceId = txtDeviceId;
        this.dtCheckIn = dtCheckIn;
        this.dtCheckOut = dtCheckOut;
        this.dtLogOut = dtLogOut;
        this.txtWarn = txtWarn;
    }

    public clsMobile_trUserLoginAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getTxtWarn() {
        return txtWarn;
    }

    public void setTxtWarn(String txtWarn) {
        this.txtWarn = txtWarn;
    }
}
