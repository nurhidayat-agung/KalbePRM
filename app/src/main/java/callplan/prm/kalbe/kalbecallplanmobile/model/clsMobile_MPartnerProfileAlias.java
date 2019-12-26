package callplan.prm.kalbe.kalbecallplanmobile.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by rhezaTesar on 9/28/2016.
 * migrated to Android room by ghqp 12/24/2019
 */
@Entity(tableName = "Mobile_MPartnerProfileAlias")
public class clsMobile_MPartnerProfileAlias {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "IntPartnerID")
    public Long IntPartnerID;
    public final String txtConstIntPartnerID  = "IntPartnerID";

    @ColumnInfo(name = "txtTipePartner")
    public String txtTipePartner;
    public final String txtConsttxtTipePartner  = "txtTipePartner";

    @ColumnInfo(name = "txtPartnerCRM")
    public String txtPartnerCRM;
    public final String txtConsttxtPartnerCRM  = "txtPartnerCRM";

    @ColumnInfo(name = "txtNamaPartner")
    public String txtNamaPartner;
    public final String txtConstidVersion  = "idVersion";

    @ColumnInfo(name = "txtChannelName")
    public String txtChannelName;
    public final String txtConsttxtChannelName  = "txtChannelName";

    @ColumnInfo(name = "txtAlamat")
    public String txtAlamat;
    public final String txtConsttxtAlamat  = "txtAlamat";

    @ColumnInfo(name = "intBranchID")
    public Long intBranchID;
    public final String txtConstintBranchID  = "intBranchID";

    @ColumnInfo(name = "txtBranchName")
    public String txtBranchName;
    public final String txtConsttxtBranchName  = "txtBranchName";

    @ColumnInfo(name = "txtLongitude")
    public String txtLongitude;
    public final String txtConsttxtLongitude  = "txtLongitude";

    @ColumnInfo(name = "txtLatitude")
    public String txtLatitude;
    public final String txtConsttxtLatitude  = "txtLatitude";

    @ColumnInfo(name = "txtAcc")
    public String txtAcc;
    public final String txtConsttxtAcc  = "txtAcc";

    @ColumnInfo(name = "intPartnerIDSub")
    public String intPartnerIDSub;
    public final String txtConstintPartnerIDSub  = "intPartnerIDSub";
}
