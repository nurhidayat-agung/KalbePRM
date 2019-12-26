package callplan.prm.kalbe.kalbecallplanmobile.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by rhezaTesar on 9/28/2016.
 * migrated to Android room by ghqp 12/24/2019
 */
@Entity(tableName = "mTipeSumberData")
public class clsMobile_TipeSumberData {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "txtSumberDataID")
    public String txtSumberDataID;

    @ColumnInfo(name = "txtCabangID")
    public String txtCabangID;

    @ColumnInfo(name = "TipeSumberDataID")
    public String TipeSumberDataID;

    @ColumnInfo(name = "txtNamaInstitusi")
    public String txtNamaInstitusi;

    @ColumnInfo(name = "txtAlamat")
    public String txtAlamat;

    @ColumnInfo(name = "txtNamaPropinsi")
    public String txtNamaPropinsi;

    @ColumnInfo(name = "txtNamaCabang")
    public String txtNamaCabang;

    @ColumnInfo(name = "txtLatitude")
    public String txtLatitude;

    @ColumnInfo(name = "txtLongitude")
    public String txtLongitude;

    @ColumnInfo(name = "txtAcc")
    public String txtAcc;
}
