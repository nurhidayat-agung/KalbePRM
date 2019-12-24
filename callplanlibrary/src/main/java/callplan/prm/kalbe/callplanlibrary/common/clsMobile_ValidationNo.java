package callplan.prm.kalbe.callplanlibrary.common;

import android.graphics.PorterDuff;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by rhezaTesar on 10/25/2016.
 */
@Table(name = "Mobile_ValidationNo")
public class clsMobile_ValidationNo extends Model {
    @Column(name = "txtValidationNo")
    public String txtValidationNo;
    public final String txtConsttxtValidationNo = "txtValidationNo";
    @Column(name = "BitUse")
    public String bitUse;
    public final String txtConstBitUse = "BitUse";
}

