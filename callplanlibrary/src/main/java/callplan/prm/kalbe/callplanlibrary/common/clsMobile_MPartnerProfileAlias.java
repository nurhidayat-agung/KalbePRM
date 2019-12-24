package callplan.prm.kalbe.callplanlibrary.common;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by rhezaTesar on 12/14/2016.
 */
@Table(name = "Mobile_MPartnerProfileAlias")
public class clsMobile_MPartnerProfileAlias extends Model {
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

    @Column(name = "intPartnerIDSub")
    public String intPartnerIDSub;
    public final String txtConstintPartnerIDSub  = "intPartnerIDSub";
}
