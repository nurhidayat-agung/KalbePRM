package callplan.prm.kalbe.callplanlibrary.common;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by rhezaTesar on 9/28/2016.
 */
@Table(name = "mVisitPlanCategory")
public class clsMobile_mVisitPlanCategory extends Model {
    @Column(name = "intCategoryID")
    public Long intCategoryID;
    public final String txtConstintCategoryID  = "intCategoryID";

    @Column(name = "txtCategoryName")
    public String txtCategoryName;
    public final String txtConsttxtCategoryName  = "txtCategoryName";

    @Column(name = "intNilaiBobot")
    public Long intNilaiBobot;
    public final String txtConstintNilaiBobot  = "intNilaiBobot";

    @Column(name = "bitFullDay")
    public Integer bitFullDay;
    public final String txtConstbitFullDay  = "bitFullDay";

    @Column(name = "bitActive")
    public Integer bitActive;
    public final String txtConstidbitActive  = "bitActive";

    @Column(name = "bitCapture")
    public Integer bitCapture;
    public final String txtConstidbitCapture  = "bitCapture";
}
