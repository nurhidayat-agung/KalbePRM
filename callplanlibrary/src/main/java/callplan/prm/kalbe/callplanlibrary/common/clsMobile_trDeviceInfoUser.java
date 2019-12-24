package callplan.prm.kalbe.callplanlibrary.common;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by rhezaTesar on 8/23/2016.
 */
@Table(name = "Mobile_trDeviceInfoUser")
public class clsMobile_trDeviceInfoUser extends Model {
    // This is the unique id given by the server
    @Column(name = "idDevice")
    public Integer idDevice;
    public final String txtConstidDevice  = "idDevice";

    @Column(name = "txtGUI")
    public String txtGUI;
    public final String txtConsttxtGUI  = "txtGUI";

    @Column(name = "txtVersion")
    public String txtVersion;
    public final String txtConsttxtVersion  = "txtVersion";

    @Column(name = "txtDevice")
    public String txtDevice;
    public final String txtConsttxtDevice = "txtDevice";

    @Column(name = "txtModel")
    public String txtModel;
    public final String txtConsttxtModel = "txtModel";

    @Column(name = "txtUserId")
    public String txtUserId;
    public final String txtConsttxtUserId = "txtUserId";
}
