package callplan.prm.kalbe.kalbecallplanmobile.model;

import android.util.Base64;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by rhezaTesar on 9/28/2016.
 * migrated to Android room by ghqp 12/24/2019
 */
@Entity(tableName = "Mobile_mBinaryFile")
public class clsMobile_mBinaryFile {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "txtGUI_ID")
    public String txtGUI_ID;
    public final String txtConsttxtGUI_ID  = "txtGUI_ID";

    @ColumnInfo(name = "txtGUI_IDTable")
    public String txtGUI_IDTable;
    public final String txtConsttxtGUI_IDTable  = "txtGUI_IDTable";

    @ColumnInfo(name = "txtFileName")
    public String txtFileName;
    public final String txtConsttxtFileName  = "txtFileName";

    @ColumnInfo(name = "txtBinary")
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
