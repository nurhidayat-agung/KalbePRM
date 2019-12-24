package kalbenutritionals.bridgeapi.common;

/**
 * Created by arick.anjasmara on 12/30/2016.
 */

public class clsMobile_trDeviceInfoUserAPI {

    public String getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(String idDevice) {
        this.idDevice = idDevice;
    }

    public String getTxtGUI() {
        return txtGUI;
    }

    public void setTxtGUI(String txtGUI) {
        this.txtGUI = txtGUI;
    }

    public String getTxtVersion() {
        return txtVersion;
    }

    public void setTxtVersion(String txtVersion) {
        this.txtVersion = txtVersion;
    }

    public String getTxtDevice() {
        return txtDevice;
    }

    public void setTxtDevice(String txtDevice) {
        this.txtDevice = txtDevice;
    }

    public String getTxtModel() {
        return txtModel;
    }

    public void setTxtModel(String txtModel) {
        this.txtModel = txtModel;
    }

    public String getTxtUserId() {
        return txtUserId;
    }

    public void setTxtUserId(String txtUserId) {
        this.txtUserId = txtUserId;
    }

    private String idDevice;
    private String txtGUI;
    private String txtVersion;
    private String txtDevice;
    private String txtModel;
    private String txtUserId;

    public clsMobile_trDeviceInfoUserAPI(String idDevice, String txtGUI, String txtVersion, String txtDevice, String txtModel, String txtUserId) {

        this.idDevice = idDevice;
        this.txtGUI = txtGUI;
        this.txtVersion = txtVersion;
        this.txtVersion = txtVersion;
        this.txtDevice = txtDevice;
        this.txtModel = txtModel;
        this.txtUserId = txtUserId;
    }

    public clsMobile_trDeviceInfoUserAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

}
