package callplan.prm.kalbe.callplanlibrary.common;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by rhezaTesar on 8/23/2016.
 */
@Table(name = "Mobile_mVersionApp")
public class clsMobile_mVersionApp extends Model {
    @Column(name = "idVersion")
    public Integer idVersion;
    public final String txtConstidVersion  = "idVersion";

    @Column(name = "txtGUI")
    public String txtGUI;
    public final String txtConsttxtGUI  = "TxtGUI";

    @Column(name = "txtNameApp")
    public String txtNameApp;
    public final String txtConsttxtNameApp  = "TxtNameApp";

    @Column(name = "txtVersion")
    public String txtVersion;
    public final String txtConsttxtVersion  = "TxtVersion";

    @Column(name = "txtFile")
    public String txtFile;
    public final String txtConsttxtFile  = "TxtFile";
}
