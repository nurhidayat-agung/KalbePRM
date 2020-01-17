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
@Entity(tableName = "mVisitPlanCategory")
public class clsMobile_mVisitPlanCategory {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "intCategoryID")
    public Long intCategoryID;
    @Ignore
    public final String txtConstintCategoryID  = "intCategoryID";

    @ColumnInfo(name = "txtCategoryName")
    public String txtCategoryName;
    @Ignore
    public final String txtConsttxtCategoryName  = "txtCategoryName";

    @ColumnInfo(name = "intNilaiBobot")
    public Long intNilaiBobot;
    @Ignore
    public final String txtConstintNilaiBobot  = "intNilaiBobot";

    @ColumnInfo(name = "bitFullDay")
    public Integer bitFullDay;
    @Ignore
    public final String txtConstbitFullDay  = "bitFullDay";

    @ColumnInfo(name = "bitActive")
    public Integer bitActive;
    @Ignore
    public final String txtConstidbitActive  = "bitActive";

    @ColumnInfo(name = "bitCapture")
    public Integer bitCapture;
    @Ignore
    public final String txtConstidbitCapture  = "bitCapture";
}
