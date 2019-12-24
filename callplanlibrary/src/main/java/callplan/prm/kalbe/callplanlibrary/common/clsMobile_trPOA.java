package callplan.prm.kalbe.callplanlibrary.common;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by rhezaTesar on 10/14/2016.
 */
@Table(name = "Mobile_trPOA")
public class clsMobile_trPOA extends Model {
    @Column(name = "intProgramID")
    public Long intProgramID;
    public final String txtConstintProgramID  = "intProgramID";

    @Column(name = "txtNamaProgram")
    public String txtNamaProgram;
    public final String txtConsttxtNamaProgram  = "txtNamaProgram";

    @Column(name = "dtStartDate")
    public String dtStartDate;
    public final String txtConstdtStartDate  = "dtStartDate";

    @Column(name = "dtEndDate")
    public String dtEndDate;
    public final String txtConstdtEndDate  = "dtEndDate";

    @Column(name = "txtProgramDescription")
    public String txtProgramDescription;
    public final String txtConsttxtProgramDescription  = "txtProgramDescription";

    @Column(name = "txtServer")
    public String txtServer;
    public final String txtConsttxtServer  = "txtServer";
}
