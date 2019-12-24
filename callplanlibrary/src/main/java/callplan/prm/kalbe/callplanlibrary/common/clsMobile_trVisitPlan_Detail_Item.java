package callplan.prm.kalbe.callplanlibrary.common;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by rhezaTesar on 8/23/2016.
 */
@Table(name = "Mobile_trVisitPlan_Detail_Item")
    public class clsMobile_trVisitPlan_Detail_Item extends Model {
    @Column(name = "txtGUI_Detail_Item")
    public String txtGUI_Detail_Item;
    public final String txtConsttxtGUI_Detail_Item = "txtGUI_Detail_Item";

    @Column(name = "intVisitPlan_DetailID")
    public Long intVisitPlan_DetailID;
    public final String txtConstintVisitPlan_DetailID = "intVisitPlan_DetailID";

    @Column(name = "txtGUI_Detail")
    public String txtGUI_Detail;
    public final String txtConsttxtGUI_Detail = "txtGUI_Detail";

    @Column(name = "txtFileName")
    public String txtFileName;
    public final String txtConsttxtFileName = "txtFileName";

    @Column(name = "dtCaptureGeotagging")
    public String dtCaptureGeotagging;
    public final String txtConstdtCaptureGeotagging = "dtCaptureGeotagging";

    @Column(name = "txtLongitude")
    public String txtLongitude;
    public final String txtConsttxtLongitude = "txtLongitude";

    @Column(name = "txtLatitude")
    public String txtLatitude;
    public final String txtConsttxtLatitude = "txtLatitude";

    @Column(name = "txtAccuracy")
    public String txtAccuracy;
    public final String txtConsttxtAccuracyD = "txtAccuracy";

    @Column(name = "intNo")
    public String intNo;
    public final String txtConsintNo = "intNo";

}
