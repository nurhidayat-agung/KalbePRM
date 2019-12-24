package callplan.prm.kalbe.callplanlibrary.common;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by rhezaTesar on 8/4/2017.
 */
@Table(name = "mTipeSumberData")
public class clsMobile_TipeSumberData extends Model {
    @Column(name = "txtSumberDataID")
    public String txtSumberDataID;
    @Column(name = "txtCabangID")
    public String txtCabangID;
    @Column(name = "TipeSumberDataID")
    public String TipeSumberDataID;
    @Column(name = "txtNamaInstitusi")
    public String txtNamaInstitusi;
    @Column(name = "txtAlamat")
    public String txtAlamat;
    @Column(name = "txtNamaPropinsi")
    public String txtNamaPropinsi;
    @Column(name = "txtNamaCabang")
    public String txtNamaCabang;
    @Column(name = "txtLatitude")
    public String txtLatitude;
    @Column(name = "txtLongitude")
    public String txtLongitude;
    @Column(name = "txtAcc")
    public String txtAcc;
}
