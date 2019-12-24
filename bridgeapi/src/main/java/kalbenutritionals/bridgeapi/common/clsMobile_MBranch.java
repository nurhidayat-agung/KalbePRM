package kalbenutritionals.bridgeapi.common;

import java.io.Serializable;

/**
 * Created by rhezaTesar on 2/10/2017.
 */

public class clsMobile_MBranch implements Serializable {
    private String IntCabangID;
    private String TxtKodeCabang;
    private String TxtNamaCabang;

    public String getIntCabangID() {
        return IntCabangID;
    }

    public void setIntCabangID(String intCabangID) {
        IntCabangID = intCabangID;
    }

    public String getTxtKodeCabang() {
        return TxtKodeCabang;
    }

    public void setTxtKodeCabang(String txtKodeCabang) {
        TxtKodeCabang = txtKodeCabang;
    }

    public String getTxtNamaCabang() {
        return TxtNamaCabang;
    }

    public void setTxtNamaCabang(String txtNamaCabang) {
        TxtNamaCabang = txtNamaCabang;
    }
}
