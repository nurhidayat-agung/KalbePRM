package callplan.prm.kalbe.kalbecallplanmobile.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
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
    @Ignore
    public final String txtConstIntPartnerID  = "IntPartnerID";

    @ColumnInfo(name = "txtTipePartner")
    public String txtTipePartner;
    @Ignore
    public final String txtConsttxtTipePartner  = "txtTipePartner";

    @ColumnInfo(name = "txtPartnerCRM")
    public String txtPartnerCRM;
    @Ignore
    public final String txtConsttxtPartnerCRM  = "txtPartnerCRM";

    @ColumnInfo(name = "txtNamaPartner")
    public String txtNamaPartner;
    @Ignore
    public final String txtConstidVersion  = "idVersion";

    @ColumnInfo(name = "txtChannelName")
    public String txtChannelName;
    @Ignore
    public final String txtConsttxtChannelName  = "txtChannelName";

    @ColumnInfo(name = "txtAlamat")
    public String txtAlamat;
    @Ignore
    public final String txtConsttxtAlamat  = "txtAlamat";

    @ColumnInfo(name = "intBranchID")
    public Long intBranchID;
    @Ignore
    public final String txtConstintBranchID  = "intBranchID";

    @ColumnInfo(name = "txtBranchName")
    public String txtBranchName;
    @Ignore
    public final String txtConsttxtBranchName  = "txtBranchName";

    @ColumnInfo(name = "txtLongitude")
    public String txtLongitude;
    @Ignore
    public final String txtConsttxtLongitude  = "txtLongitude";

    @ColumnInfo(name = "txtLatitude")
    public String txtLatitude;
    @Ignore
    public final String txtConsttxtLatitude  = "txtLatitude";

    @ColumnInfo(name = "txtAcc")
    public String txtAcc;
    @Ignore
    public final String txtConsttxtAcc  = "txtAcc";

    @ColumnInfo(name = "intPartnerIDSub")
    public String intPartnerIDSub;
    @Ignore
    public final String txtConstintPartnerIDSub  = "intPartnerIDSub";
}
