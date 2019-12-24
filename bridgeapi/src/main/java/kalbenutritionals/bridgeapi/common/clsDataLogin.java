package kalbenutritionals.bridgeapi.common;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by rhezaTesar on 2/10/2017.
 */

public class clsDataLogin implements Serializable {
    private List<clsMobile_MBranch> clsMobile_MBranch;
    private List<clsMobile_MLOB> clsMobile_MLOB;
    private List<clsMobile_UserJabatanAPI> clsMobile_UserJabatanAPI;
    private clsMobile_trUserLoginAPI clsMobile_trUserLoginAPI;
    public JSONObject txtJSON() throws JSONException {
        JSONObject resJson = new JSONObject();
        Collection<JSONObject> itemsListJquey = new ArrayList<JSONObject>();
        if (this.getClsMobile_UserJabatanAPI() != null) {
            itemsListJquey = new ArrayList<JSONObject>();
            for (clsMobile_UserJabatanAPI data : this.getClsMobile_UserJabatanAPI()) {
                JSONObject item1 = new JSONObject();
                item1.put("IntJabatanID", String.valueOf(data.getIntJabatanID()));
                item1.put("TxtJabatanName", String.valueOf(data.getTxtJabatanName()));
                itemsListJquey.add(item1);
            }
            resJson.put("ListOfUserJabatan", new JSONArray(itemsListJquey));
        }
        if (this.getClsMobile_MLOB() != null) {
            itemsListJquey = new ArrayList<JSONObject>();
            for (clsMobile_MLOB data : this.getClsMobile_MLOB()) {
                JSONObject item1 = new JSONObject();
                item1.put("IntLOBID", String.valueOf(data.getIntLOBID()));
                item1.put("TxtLOBName", String.valueOf(data.getTxtLOBName()));
                itemsListJquey.add(item1);
            }
            resJson.put("ListOfMLOB", new JSONArray(itemsListJquey));
        }
        if (this.getClsMobile_MBranch() != null) {
            itemsListJquey = new ArrayList<JSONObject>();
            for (clsMobile_MBranch data : this.getClsMobile_MBranch()) {
                JSONObject item1 = new JSONObject();
                item1.put("IntCabangID", String.valueOf(data.getIntCabangID()));
                item1.put("TxtKodeCabang", String.valueOf(data.getTxtKodeCabang()));
                item1.put("TxtNamaCabang", String.valueOf(data.getTxtNamaCabang()));
                itemsListJquey.add(item1);
            }
            resJson.put("ListOfMBranch", new JSONArray(itemsListJquey));
        }
        if (this.getClsMobile_trUserLoginAPI() != null) {
            itemsListJquey = new ArrayList<JSONObject>();
            clsMobile_trUserLoginAPI data= this.getClsMobile_trUserLoginAPI();
            JSONObject item1 = new JSONObject();
            item1.put("DtLogOut", String.valueOf(data.getDtLogOut()));
            item1.put("IdUserLogin", String.valueOf(data.getIdUserLogin()));
            item1.put("IntCabangID", String.valueOf(data.getIntCabangID()));
            item1.put("TxtGUI", String.valueOf(data.getTxtGUI()));
            item1.put("TxtUserID", String.valueOf(data.getTxtUserID()));
            item1.put("TxtRoleID", String.valueOf(data.getTxtRoleID()));
            item1.put("TxtRoleName", String.valueOf(data.getTxtRoleName()));
            item1.put("TxtUserName", String.valueOf(data.getTxtUserName()));
            item1.put("TxtName", String.valueOf(data.getTxtName()));
            item1.put("TxtEmpID", String.valueOf(data.getTxtEmpID()));
            item1.put("TxtBranchCode", String.valueOf(data.getTxtBranchCode()));
            item1.put("TxtOutletCode", String.valueOf(data.getTxtOutletCode()));
            item1.put("TxtOutletName", String.valueOf(data.getTxtOutletName()));
            item1.put("DtLastLogin", String.valueOf(data.getDtLastLogin()));
            item1.put("TxtDeviceId", String.valueOf(data.getTxtDeviceId()));
            itemsListJquey.add(item1);
            resJson.put("ListOfTrUserLoginMobile", new JSONArray(itemsListJquey));
        }
        return  resJson;
    }
    public List<kalbenutritionals.bridgeapi.common.clsMobile_UserJabatanAPI> getClsMobile_UserJabatanAPI() {
        return clsMobile_UserJabatanAPI;
    }

    public void setClsMobile_UserJabatanAPI(List<kalbenutritionals.bridgeapi.common.clsMobile_UserJabatanAPI> clsMobile_UserJabatanAPI) {
        this.clsMobile_UserJabatanAPI = clsMobile_UserJabatanAPI;
    }

    public List<kalbenutritionals.bridgeapi.common.clsMobile_MBranch> getClsMobile_MBranch() {
        return clsMobile_MBranch;
    }

    public void setClsMobile_MBranch(List<kalbenutritionals.bridgeapi.common.clsMobile_MBranch> clsMobile_MBranch) {
        this.clsMobile_MBranch = clsMobile_MBranch;
    }

    public List<kalbenutritionals.bridgeapi.common.clsMobile_MLOB> getClsMobile_MLOB() {
        return clsMobile_MLOB;
    }

    public void setClsMobile_MLOB(List<kalbenutritionals.bridgeapi.common.clsMobile_MLOB> clsMobile_MLOB) {
        this.clsMobile_MLOB = clsMobile_MLOB;
    }

    public kalbenutritionals.bridgeapi.common.clsMobile_trUserLoginAPI getClsMobile_trUserLoginAPI() {
        return clsMobile_trUserLoginAPI;
    }

    public void setClsMobile_trUserLoginAPI(kalbenutritionals.bridgeapi.common.clsMobile_trUserLoginAPI clsMobile_trUserLoginAPI) {
        this.clsMobile_trUserLoginAPI = clsMobile_trUserLoginAPI;
    }
}
