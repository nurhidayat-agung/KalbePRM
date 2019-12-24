package callplan.prm.kalbe.callplanlibrary.common;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by rhezaTesar on 9/21/2016.
 */
@Table(name = "Mobile_MPartnerProfile")
public class clsMobile_MPartnerProfile extends Model {
    @Column(name = "IntPartnerID")
    public Long IntPartnerID;
    public final String txtConstIntPartnerID  = "IntPartnerID";

    @Column(name = "txtTipePartner")
    public String txtTipePartner;
    public final String txtConsttxtTipePartner  = "txtTipePartner";

    @Column(name = "txtPartnerCRM")
    public String txtPartnerCRM;
    public final String txtConsttxtPartnerCRM  = "txtPartnerCRM";

    @Column(name = "txtNamaPartner")
    public String txtNamaPartner;
    public final String txtConstidVersion  = "idVersion";

    @Column(name = "txtChannelName")
    public String txtChannelName;
    public final String txtConsttxtChannelName  = "txtChannelName";

    @Column(name = "txtAlamat")
    public String txtAlamat;
    public final String txtConsttxtAlamat  = "txtAlamat";

    @Column(name = "intBranchID")
    public Long intBranchID;
    public final String txtConstintBranchID  = "intBranchID";

    @Column(name = "txtBranchName")
    public String txtBranchName;
    public final String txtConsttxtBranchName  = "txtBranchName";

    @Column(name = "txtLongitude")
    public String txtLongitude;
    public final String txtConsttxtLongitude  = "txtLongitude";

    @Column(name = "txtLatitude")
    public String txtLatitude;
    public final String txtConsttxtLatitude  = "txtLatitude";

    @Column(name = "txtAcc")
    public String txtAcc;
    public final String txtConsttxtAcc  = "txtAcc";
}
