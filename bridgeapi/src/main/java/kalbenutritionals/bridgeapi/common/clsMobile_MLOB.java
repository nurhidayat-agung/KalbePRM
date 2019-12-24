package kalbenutritionals.bridgeapi.common;

import java.io.Serializable;

/**
 * Created by rhezaTesar on 2/10/2017.
 */

public class clsMobile_MLOB implements Serializable {
    private String intLOBID;
    private String txtLOBName;
    private String txtLOBDescription;

    public String getIntLOBID() {
        return intLOBID;
    }

    public void setIntLOBID(String intLOBID) {
        this.intLOBID = intLOBID;
    }

    public String getTxtLOBName() {
        return txtLOBName;
    }

    public void setTxtLOBName(String txtLOBName) {
        this.txtLOBName = txtLOBName;
    }

    public String getTxtLOBDescription() {
        return txtLOBDescription;
    }

    public void setTxtLOBDescription(String txtLOBDescription) {
        this.txtLOBDescription = txtLOBDescription;
    }
}
