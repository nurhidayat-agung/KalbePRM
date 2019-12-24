package kalbenutritionals.bridgeapi.common;

import java.io.Serializable;

/**
 * Created by arick.anjasmara on 12/30/2016.
 */

public class clsMobile_UserJabatanAPI implements Serializable {

    public String getIntJabatanID() {
        return IntJabatanID;
    }

    public void setIntJabatanID(String intJabatanID) {
        IntJabatanID = intJabatanID;
    }

    public String getTxtJabatanName() {
        return TxtJabatanName;
    }

    public void setTxtJabatanName(String txtJabatanName) {
        TxtJabatanName = txtJabatanName;
    }

    public String getTxtJabatanDesc() {
        return TxtJabatanDesc;
    }

    public void setTxtJabatanDesc(String txtJabatanDesc) {
        TxtJabatanDesc = txtJabatanDesc;
    }

    public String getBitActive() {
        return BitActive;
    }

    public void setBitActive(String bitActive) {
        BitActive = bitActive;
    }

    public String getBitPrimary() {
        return BitPrimary;
    }

    public void setBitPrimary(String bitPrimary) {
        BitPrimary = bitPrimary;
    }

    public String getTxtLimit() {
        return txtLimit;
    }

    public void setTxtLimit(String txtLimit) {
        this.txtLimit = txtLimit;
    }

    private String IntJabatanID;
    private String TxtJabatanName;
    private String TxtJabatanDesc;
    private String BitActive;
    private String BitPrimary;
    private String txtLimit;

    public clsMobile_UserJabatanAPI(String IntJabatanID, String TxtJabatanName, String TxtJabatanDesc, String BitActive, String BitPrimary, String txtLimit) {

        this.IntJabatanID = IntJabatanID;
        this.TxtJabatanName = TxtJabatanName;
        this.TxtJabatanDesc = TxtJabatanDesc;
        this.BitActive = BitActive;
        this.BitPrimary = BitPrimary;
        this.txtLimit = txtLimit;
    }

    public clsMobile_UserJabatanAPI() {
        super();
        // TODO Auto-generated constructor stub
    }
}
