package callplan.prm.kalbe.callplanlibrary.common;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by rhezaTesar on 8/23/2016.
 */
@Table(name = "Mobile_mConfig")
public class clsMobile_mConfig extends Model {
    @Column(name = "idConfig")
    public Long idConfig;

    @Column(name = "txtName")
    public String txtName;

    @Column(name = "txtValue")
    public String txtValue;

    @Column(name = "txtDefaultValue")
    public String txtDefaultValue;



}
