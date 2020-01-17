package callplan.prm.kalbe.kalbecallplanmobile.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;



/**
 * Created by rhezaTesar on 9/28/2016.
 * migrated to Android room by ghqp 12/24/2019
 */
@Entity(tableName = "Mobile_mConfig")
public class clsMobile_mConfig {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "idConfig")
    public Long idConfig;

    @ColumnInfo(name = "txtName")
    public String txtName;

    @ColumnInfo(name = "txtValue")
    public String txtValue;

    @ColumnInfo(name = "txtDefaultValue")
    public String txtDefaultValue;



}
