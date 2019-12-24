package callplan.prm.kalbe.callplanlibrary.common;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by rhezaTesar on 8/3/2017.
 */
@Table(name = "Mobile_mAllBranch")
public class clsMobile_mAllbranch extends Model {
    @Column(name = "IntCabangID")
    public String IntCabangID;
    public final String txtConstIntCabangID  = "IntCabangID";

    @Column(name = "TxtKodeCabang")
    public String TxtKodeCabang;
    public final String txtConstTxtKodeCabang  = "TxtKodeCabang";

    @Column(name = "TxtNamaCabang")
    public String TxtNamaCabang;
    public final String txtConstTxtNamaCabang  = "TxtNamaCabang";
}
