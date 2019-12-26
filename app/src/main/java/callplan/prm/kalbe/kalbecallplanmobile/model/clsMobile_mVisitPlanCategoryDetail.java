package callplan.prm.kalbe.kalbecallplanmobile.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by rhezaTesar on 9/28/2016.
 * migrated to Android room by ghqp 12/24/2019
 */
@Entity(tableName = "mVisitPlanCategoryDetail")
public class clsMobile_mVisitPlanCategoryDetail {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "intCategoryIDDetail")
    public Long intCategoryIDDetail;
    public final String txtConstintCategoryIDDetail  = "intCategoryIDDetail";

    @ColumnInfo(name = "intCategoryID")
    public Long intCategoryID;
    public final String txtConstintCategoryID  = "intCategoryID";

    @ColumnInfo(name = "IntJabatanID")
    public Long IntJabatanID;
    public final String txtConstIntJabatanID  = "IntJabatanID";

    @ColumnInfo(name = "IntBobot")
    public Long IntBobot;
    public final String txtConstIntBobot  = "IntBobot";
}
