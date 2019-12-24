package kalbenutritionals.bridgeapi.common;

/**
 * Created by arick.anjasmara on 12/30/2016.
 */

public class clsMobile_mVersionAppAPI {

    private String idVersion;
    private String txtGUI;
    private String txtNameApp;
    private String txtVersion;
    private String txtFile;

    public clsMobile_mVersionAppAPI(String idVersion, String txtGUI, String txtNameApp, String txtVersion, String txtFile) {

        this.idVersion = idVersion;
        this.txtGUI = txtGUI;
        this.txtNameApp = txtNameApp;
        this.txtVersion = txtVersion;
        this.txtFile = txtFile;
    }

    public clsMobile_mVersionAppAPI() {
        super();
        // TODO Auto-generated constructor stub
    }
    public String getTxtFile() {
        return txtFile;
    }

    public void setTxtFile(String txtFile) {
        this.txtFile = txtFile;
    }

    public String getIdVersion() {
        return idVersion;
    }

    public void setIdVersion(String idVersion) {
        this.idVersion = idVersion;
    }

    public String getTxtGUI() {
        return txtGUI;
    }

    public void setTxtGUI(String txtGUI) {
        this.txtGUI = txtGUI;
    }

    public String getTxtNameApp() {
        return txtNameApp;
    }

    public void setTxtNameApp(String txtNameApp) {
        this.txtNameApp = txtNameApp;
    }

    public String getTxtVersion() {
        return txtVersion;
    }

    public void setTxtVersion(String txtVersion) {
        this.txtVersion = txtVersion;
    }

    public String txtConsttxtNameApp="TxtNameApp";
    public String txtConsttxtVersion="TxtVersion";
    public String txtConsttxtGUI="TxtGUI";
    public String txtConsttxtFile="TxtFile";
}
