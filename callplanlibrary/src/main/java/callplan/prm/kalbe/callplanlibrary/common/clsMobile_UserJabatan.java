package callplan.prm.kalbe.callplanlibrary.common;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by rhezaTesar on 9/19/2016.
 */
@Table(name = "Mobile_UserJabatan")
public class clsMobile_UserJabatan extends Model {
    @Column(name = "IntJabatanID")
    public long IntJabatanID;
    @Column(name = "TxtJabatanName")
    public String TxtJabatanName;
    @Column(name = "TxtJabatanDesc")
    public String TxtJabatanDesc;
    @Column(name = "BitActive")
    public long BitActive;
    @Column(name = "BitPrimary")
    public String BitPrimary;
    public final String txtConstBitPrimary = "BitPrimary";
    @Column(name = "txtLimit")
    public String txtLimit;
}
