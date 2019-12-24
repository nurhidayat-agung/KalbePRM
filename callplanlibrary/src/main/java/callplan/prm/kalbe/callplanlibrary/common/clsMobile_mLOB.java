package callplan.prm.kalbe.callplanlibrary.common;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by rhezaTesar on 9/28/2016.
 */
@Table(name = "mLOB")
public class clsMobile_mLOB extends Model {
    @Column(name = "intLOBID")
    public Long intLOBID;
    public final String txtConstintLOBID  = "intLOBID";

    @Column(name = "txtLOBName")
    public String txtLOBName;
    public final String txtConsttxtLOBName  = "txtLOBName";

    @Column(name = "txtLOBDescription")
    public String txtLOBDescription;
    public final String txtConsttxtLOBDescription  = "txtLOBDescription";
}
