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
@Entity(tableName = "Mobile_trVisitPlan_Detail_Item")
public class clsMobile_trVisitPlan_Detail_Item {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "txtGUI_Detail_Item")
    public String txtGUI_Detail_Item;
    @Ignore
    public final String txtConsttxtGUI_Detail_Item = "txtGUI_Detail_Item";

    @ColumnInfo(name = "intVisitPlan_DetailID")
    public Long intVisitPlan_DetailID;
    @Ignore
    public final String txtConstintVisitPlan_DetailID = "intVisitPlan_DetailID";

    @ColumnInfo(name = "txtGUI_Detail")
    public String txtGUI_Detail;
    @Ignore
    public final String txtConsttxtGUI_Detail = "txtGUI_Detail";

    @ColumnInfo(name = "txtFileName")
    public String txtFileName;
    @Ignore
    public final String txtConsttxtFileName = "txtFileName";

    @ColumnInfo(name = "dtCaptureGeotagging")
    public String dtCaptureGeotagging;
    @Ignore
    public final String txtConstdtCaptureGeotagging = "dtCaptureGeotagging";

    @ColumnInfo(name = "txtLongitude")
    public String txtLongitude;
    @Ignore
    public final String txtConsttxtLongitude = "txtLongitude";

    @ColumnInfo(name = "txtLatitude")
    public String txtLatitude;
    @Ignore
    public final String txtConsttxtLatitude = "txtLatitude";

    @ColumnInfo(name = "txtAccuracy")
    public String txtAccuracy;
    @Ignore
    public final String txtConsttxtAccuracyD = "txtAccuracy";

    @ColumnInfo(name = "intNo")
    public String intNo;
    @Ignore
    public final String txtConsintNo = "intNo";

}
