package callplan.prm.kalbe.callplanlibrary.common;

import android.util.Base64;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by rhezaTesar on 9/30/2016.
 */
@Table(name = "Mobile_mBinaryFile")
public class clsMobile_mBinaryFile extends Model {
    @Column(name = "txtGUI_ID")
    public String txtGUI_ID;
    public final String txtConsttxtGUI_ID  = "txtGUI_ID";
    @Column(name = "txtGUI_IDTable")
    public String txtGUI_IDTable;
    public final String txtConsttxtGUI_IDTable  = "txtGUI_IDTable";
    @Column(name = "txtFileName")
    public String txtFileName;
    public final String txtConsttxtFileName  = "txtFileName";
    @Column(name = "txtBinary")
    public String txtBinary;
    public final String txtConsttxtBinary  = "txtBinary";

    public clsMobile_mBinaryFile(String txtGUI_ID, String txtGUI_IDTable, String txtFileName, byte[] _txtBinary) {
        this.txtGUI_ID = txtGUI_ID;
        this.txtGUI_IDTable = txtGUI_IDTable;
        this.txtFileName = txtFileName;
        this.txtBinary = Base64.encodeToString(_txtBinary, Base64.NO_WRAP);
    }
    public byte[] getBinary() {
        if(txtBinary==null)
            return null;
        else
            return Base64.decode(txtBinary, Base64.NO_WRAP);
    }

    public clsMobile_mBinaryFile() {
    }
}
