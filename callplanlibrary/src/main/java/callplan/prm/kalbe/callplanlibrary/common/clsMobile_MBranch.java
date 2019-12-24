package callplan.prm.kalbe.callplanlibrary.common;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by rhezaTesar on 9/21/2016.
 */
@Table(name = "Mobile_mBranch")
public class clsMobile_MBranch extends Model {
    @Column(name = "IntCabangID")
    public Long IntCabangID;
    public final String txtConstIntCabangID  = "IntCabangID";

    @Column(name = "TxtKodeCabang")
    public String TxtKodeCabang;
    public final String txtConstTxtKodeCabang  = "TxtKodeCabang";

    @Column(name = "TxtNamaCabang")
    public String TxtNamaCabang;
    public final String txtConstTxtNamaCabang  = "TxtNamaCabang";
}
