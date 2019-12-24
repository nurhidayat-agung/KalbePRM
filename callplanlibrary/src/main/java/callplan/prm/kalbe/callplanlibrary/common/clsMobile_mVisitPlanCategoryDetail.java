package callplan.prm.kalbe.callplanlibrary.common;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by rhezaTesar on 10/18/2016.
 */
@Table(name = "mVisitPlanCategoryDetail")
public class clsMobile_mVisitPlanCategoryDetail extends Model {
    @Column(name = "intCategoryIDDetail")
    public Long intCategoryIDDetail;
    public final String txtConstintCategoryIDDetail  = "intCategoryIDDetail";
    @Column(name = "intCategoryID")
    public Long intCategoryID;
    public final String txtConstintCategoryID  = "intCategoryID";
    @Column(name = "IntJabatanID")
    public Long IntJabatanID;
    public final String txtConstIntJabatanID  = "IntJabatanID";
    @Column(name = "IntBobot")
    public Long IntBobot;
    public final String txtConstIntBobot  = "IntBobot";
}
