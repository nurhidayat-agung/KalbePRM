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
@Entity(tableName = "mVisitPlanCategoryDetail")
public class clsMobile_mVisitPlanCategoryDetail {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "intCategoryIDDetail")
    public Long intCategoryIDDetail;
    @Ignore
    public final String txtConstintCategoryIDDetail  = "intCategoryIDDetail";

    @ColumnInfo(name = "intCategoryID")
    public Long intCategoryID;
    @Ignore
    public final String txtConstintCategoryID  = "intCategoryID";

    @ColumnInfo(name = "IntJabatanID")
    public Long IntJabatanID;
    @Ignore
    public final String txtConstIntJabatanID  = "IntJabatanID";

    @ColumnInfo(name = "IntBobot")
    public Long IntBobot;
    @Ignore
    public final String txtConstIntBobot  = "IntBobot";
}
