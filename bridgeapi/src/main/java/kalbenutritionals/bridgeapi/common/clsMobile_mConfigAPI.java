package kalbenutritionals.bridgeapi.common;

/**
 * Created by arick.anjasmara on 12/30/2016.
 */

public class clsMobile_mConfigAPI {

    public String getIdConfig() {
        return idConfig;
    }

    public void setIdConfig(String idConfig) {
        this.idConfig = idConfig;
    }

    public String getTxtName() {
        return txtName;
    }

    public void setTxtName(String txtName) {
        this.txtName = txtName;
    }

    public String getTxtValue() {
        return txtValue;
    }

    public void setTxtValue(String txtValue) {
        this.txtValue = txtValue;
    }

    public String getTxtDefaultValue() {
        return txtDefaultValue;
    }

    public void setTxtDefaultValue(String txtDefaultValue) {
        this.txtDefaultValue = txtDefaultValue;
    }

    private String idConfig;
    private String txtName;
    private String txtValue;
    private String txtDefaultValue;

    public clsMobile_mConfigAPI(String idConfig, String txtName, String txtValue, String txtDefaultValue) {

        this.idConfig = idConfig;
        this.txtName = txtName;
        this.txtValue = txtValue;
        this.txtDefaultValue = txtDefaultValue;
    }

    public clsMobile_mConfigAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

}
