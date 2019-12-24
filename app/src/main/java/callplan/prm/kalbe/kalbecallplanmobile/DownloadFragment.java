package callplan.prm.kalbe.kalbecallplanmobile;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import callplan.prm.kalbe.callplanlibrary.common.clsMobile_MBranch;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_MPartnerProfile;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_MPartnerProfileAlias;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_UserJabatan;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_ValidationNo;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_mAllbranch;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_mLOB;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_mVersionApp;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_mVisitPlanCategory;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_mVisitPlanCategoryDetail;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_trDeviceInfoUser;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_trPOA;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_trUserLogin;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_trVisitPlan_Detail;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_trVisitPlan_Header;
import callplan.prm.kalbe.callplanlibrary.common.clsWarning;
import callplan.prm.kalbe.kalbecallplanmobile.bl.Mobile_tDeviceInfoUserBL;
import callplan.prm.kalbe.kalbecallplanmobile.bl.Mobile_trUserLoginBL;
import callplan.prm.kalbe.kalbecallplanmobile.bl.clsMainBL;


/**
 * A simple {@link Fragment} subclass
 */
public class DownloadFragment extends Fragment {
    Spinner spnNoRealisasi;
    Button btnDownloadNoRealisasi;
    Spinner spnPOA;
    Button btnDownloadPOA;
    Spinner spnLOB;
    Button btnDownloadLOB;
    Spinner spnCategory;
    Button btnDownloadCategory;
    Spinner spnBranchKNDASHBOARD;
    Button btnDownloadBranchKNDASHBOARD;
    Spinner spnBranch;
    Button btnDownloadBranch;
    Spinner spnOutlet;
    Spinner spnOutletAlias;
    Button btnDownloadOutlet;
    Spinner spnOutStandingCall;
    Button btnOutStandingCall;
    Button btnDownnloadAll;
    int intProcesscancel;
    View v;
    String txtProcess;
    clsMainBL _clsMainBL;
    List<String> ListSpinTitle;
    List<String> ListSpinDesc;
    public DownloadFragment() {
        // Required empty public constructor
    }
    clsWarning _clsWarning;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_download, container, false);
        Intent i = getActivity().getIntent();
        spnBranch=(Spinner)v.findViewById(R.id.spnBranch);
        spnBranchKNDASHBOARD=(Spinner)v.findViewById(R.id.spnBranchKNDASHBOARD);
        spnOutletAlias=(Spinner)v.findViewById(R.id.spnOutletAlias);
        spnOutlet=(Spinner)v.findViewById(R.id.spnOutlet);
        spnCategory=(Spinner)v.findViewById(R.id.spnCategory);
        spnLOB=(Spinner)v.findViewById(R.id.spnLOB);
        spnOutStandingCall=(Spinner)v.findViewById(R.id.spnOutStandingCall);
        spnCategory=(Spinner)v.findViewById(R.id.spnCategory);
        spnLOB=(Spinner)v.findViewById(R.id.spnLOB);
        spnPOA=(Spinner)v.findViewById(R.id.spnPOA);
        spnNoRealisasi=(Spinner)v.findViewById(R.id.spnNoRealisasi);
        btnDownloadNoRealisasi=(Button) v.findViewById(R.id.btnDownloadNoRealisasi);
        btnDownloadPOA=(Button) v.findViewById(R.id.btnDownloadPOA);
        btnDownloadBranch=(Button) v.findViewById(R.id.btnDownloadBranch);
        btnDownloadBranchKNDASHBOARD=(Button) v.findViewById(R.id.btnDownloadBranchKNDASHBOARD);
        btnDownloadLOB=(Button) v.findViewById(R.id.btnDownloadLOB);
        btnDownloadCategory=(Button) v.findViewById(R.id.btnDownloadCategory);
        btnDownloadOutlet=(Button) v.findViewById(R.id.btnDownloadOutlet);
        btnOutStandingCall=(Button) v.findViewById(R.id.btnOutStandingCall);
        btnDownnloadAll=(Button) v.findViewById(R.id.btnDownnloadAll);

        _clsWarning=new clsWarning();
        _clsMainBL=new clsMainBL();
        txtProcess="";
        clsMobile_trVisitPlan_Detail _clsMobile_trVisitPlan_Detail=new clsMobile_trVisitPlan_Detail();
        List<clsMobile_trVisitPlan_Detail> ListOfclsMobile_trVisitPlan_Detail=new Select().from(clsMobile_trVisitPlan_Detail.class).where(_clsMobile_trVisitPlan_Detail.txtConstintSubmit+"=2").execute();
        if(ListOfclsMobile_trVisitPlan_Detail.size()>0){
            Intent myIntent = new Intent(getContext(), MainMenu.class);
            myIntent.putExtra("Data","PUSHDATA");
            getActivity().finish();
            startActivity(myIntent);
        }
        btnDownloadNoRealisasi.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                clsMobile_MBranch _clsMobile_MBranch=new clsMobile_MBranch();
                List<clsMobile_MBranch> ListOfMbranch=new Select().from(clsMobile_MBranch.class).execute();
                if(ListOfMbranch.size()>0){
                    txtProcess=_clsWarning.txtConstProcessDownloadNoRealisasi;
                    AsyncCall _AsyncCall=new AsyncCall();
                    _AsyncCall.execute();
                }else{
                    _clsMainBL.showToastWarning(getContext(),"Download data Branch Terlebih Dahulu");
                }
            }
        });
        btnDownloadPOA.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                clsMobile_MBranch _clsMobile_MBranch=new clsMobile_MBranch();
                List<clsMobile_MBranch> ListOfMbranch=new Select().from(clsMobile_MBranch.class).execute();
                if(ListOfMbranch.size()>0){
                    txtProcess=_clsWarning.txtConstProcessDownloadPOA;
                    AsyncCall _AsyncCall=new AsyncCall();
                    _AsyncCall.execute();
                }else{
                    _clsMainBL.showToastWarning(getContext(),"Download data Branch Terlebih Dahulu");
                }
            }
        });
        btnDownloadLOB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                clsMobile_MBranch _clsMobile_MBranch=new clsMobile_MBranch();
                List<clsMobile_MBranch> ListOfMbranch=new Select().from(clsMobile_MBranch.class).execute();
                if(ListOfMbranch.size()>0){
                    txtProcess=_clsWarning.txtConstProcessDownloadLOB;
                    AsyncCall _AsyncCall=new AsyncCall();
                    _AsyncCall.execute();
                }else{
                    _clsMainBL.showToastWarning(getContext(),"Download data Branch Terlebih Dahulu");
                }

            }
        });
        btnDownloadCategory.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                txtProcess=_clsWarning.txtConstProcessDownloadCategory;
                AsyncCall _AsyncCall=new AsyncCall();
                _AsyncCall.execute();
            }
        });
        btnDownloadBranch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                txtProcess=_clsWarning.txtConstProcessDownloadBranch;
                AsyncCall _AsyncCall=new AsyncCall();
                _AsyncCall.execute();
            }
        });
        btnDownloadBranchKNDASHBOARD.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                txtProcess=_clsWarning.txtConstProcessDownloadBranchKNDASHBOARD;
                AsyncCall _AsyncCall=new AsyncCall();
                _AsyncCall.execute();
            }
        });
        btnOutStandingCall.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                clsMobile_MBranch _clsMobile_MBranch=new clsMobile_MBranch();
                List<clsMobile_MBranch> ListOfMbranch=new Select().from(clsMobile_MBranch.class).execute();
                if(ListOfMbranch.size()>0){
                    txtProcess=_clsWarning.txtConstProcessDownloadOutstanding;
                    AsyncCall _AsyncCall=new AsyncCall();
                    _AsyncCall.execute();
                }else{
                    _clsMainBL.showToastWarning(getContext(),"Download data Branch Terlebih Dahulu");
                }

            }
        });
        btnDownloadOutlet.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                clsMobile_MBranch _clsMobile_MBranch=new clsMobile_MBranch();
                List<clsMobile_MBranch> ListOfMbranch=new Select().from(clsMobile_MBranch.class).execute();
                if(ListOfMbranch.size()>0){
                    txtProcess=_clsWarning.txtConstProcessDownloadOutlet;
                    AsyncCall _AsyncCall=new AsyncCall();
                    _AsyncCall.execute();
                }else{
                    _clsMainBL.showToastWarning(getContext(),"Download data Branch Terlebih Dahulu");
                }

            }
        });
        btnDownnloadAll.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                txtProcess=_clsWarning.txtConstProcessDownloadALL;
                AsyncCall _AsyncCall=new AsyncCall();
                _AsyncCall.execute();
            }
        });
        LoadDataOutstanding();
        LoadDataBranch();
        LoadDataOutletV2();
        LoadDataLOB();
        LoadDataCategory();
        LoadDataPOA();
        LoadDataNoRealisasi();
        LoadDataBranchKNDASHBoard();
        return v;
    }
    private void LoadDataNoRealisasi(){
        spnNoRealisasi.setAdapter(null);
        List<clsMobile_ValidationNo> ListOfclsMobile_ValidationNoe=new Select().from(clsMobile_ValidationNo.class).execute();
        ListSpinTitle=new ArrayList<String>();
        ListSpinDesc=new ArrayList<String>();
        if(ListOfclsMobile_ValidationNoe!=null){
            if(ListOfclsMobile_ValidationNoe.size()>0){
                for (clsMobile_ValidationNo _clsMobile_ValidationNo :ListOfclsMobile_ValidationNoe) {
                    ListSpinTitle.add(_clsMobile_ValidationNo.txtValidationNo);
                    ListSpinDesc.add("");
                }
            }
        }
        spnNoRealisasi.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, ListSpinTitle, ListSpinDesc));
        spnNoRealisasi.setEnabled(true);
    }
    private void SaveDataNoRealisasi(JSONObject JsonRes){
        if(_clsMainBL.ValidJSON(JsonRes)){
            try {
                String txtvalidJson=(String.valueOf(JsonRes.get("validJson")));
                JSONObject validJson=new JSONObject(txtvalidJson);
                String intresult=(String.valueOf(validJson.get("TxtResult")));
                if(intresult.equals("1")){
                    String txtGetDataJson = String.valueOf(validJson.get("TxtData"));
                    JSONObject JsonDataTxtData=new JSONObject(txtGetDataJson);
                    String txtGetDataJsonNoAssign = String.valueOf(JsonDataTxtData.get("NoAssign"));
                    JSONArray JSONArrayNoAssign =new JSONArray(txtGetDataJsonNoAssign);
                    for (int i = 0; i < JSONArrayNoAssign.length(); i++) {
                        JSONObject JSONObjectNoRealisasi = JSONArrayNoAssign.getJSONObject(i);
                        clsMobile_ValidationNo _clsMobile_ValidationNo=new clsMobile_ValidationNo();
                        String txtValidationNo = String.valueOf(JSONObjectNoRealisasi.get("TxtValidationNo"));
                        List<clsMobile_ValidationNo> ListOfCheckclsMobile_ValidationNo=new Select().from(clsMobile_ValidationNo.class).where(_clsMobile_ValidationNo.txtConstBitUse+"=?",txtValidationNo).execute();
                        if(ListOfCheckclsMobile_ValidationNo != null){
                            if(ListOfCheckclsMobile_ValidationNo.size()==0){
                                _clsMobile_ValidationNo=new clsMobile_ValidationNo();
                                _clsMobile_ValidationNo.txtValidationNo=String.valueOf(JSONObjectNoRealisasi.get("TxtValidationNo"));
                                String txtBool=String.valueOf(JSONObjectNoRealisasi.get("BitUse"));
                                if(txtBool.toUpperCase().equals("TRUE")){
                                    _clsMobile_ValidationNo.bitUse=String.valueOf("1");
                                }else {
                                    _clsMobile_ValidationNo.bitUse=String.valueOf("0");
                                }
                                _clsMobile_ValidationNo.save();
                            }
                        }
                    }
                }else{
                    String txtvalidJsonError=(String.valueOf(JsonRes.get("validJson")));
                    JSONObject validJsonError=new JSONObject(txtvalidJsonError);
                    String txtWarn=(String) validJsonError.get("TxtWarn");
                    _clsMainBL.showToast(getContext(),txtWarn);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
    private void LoadDataOutstanding(){
        List<clsMobile_trVisitPlan_Detail> ListOfclsMobile_trVisitPlan_Details=new Select().from(clsMobile_trVisitPlan_Detail.class).execute();
        ListSpinTitle=new ArrayList<String>();
        ListSpinDesc=new ArrayList<String>();
        if(ListOfclsMobile_trVisitPlan_Details!=null){
            if(ListOfclsMobile_trVisitPlan_Details.size()>0){
                for (clsMobile_trVisitPlan_Detail _clsMobile_trVisitPlan_Detail :ListOfclsMobile_trVisitPlan_Details) {
                    ListSpinTitle.add( _clsMobile_trVisitPlan_Detail.txtDescription);
                    ListSpinDesc.add(_clsMobile_trVisitPlan_Detail.txtLOBName +"-"+ _clsMobile_trVisitPlan_Detail.txtCategoryName);
                }
            }
        }
        spnOutStandingCall.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, ListSpinTitle, ListSpinDesc));
        spnOutStandingCall.setEnabled(true);
    }
    private void SaveDataDataOutstanding(JSONObject JsonRes){
        if(_clsMainBL.ValidJSON(JsonRes)){
            String TxtWarn="";
            try {
                String txtvalidJson=(String.valueOf(JsonRes.get("validJson")));
                JSONObject validJson=new JSONObject(txtvalidJson);
                TxtWarn=(String.valueOf(validJson.get("TxtWarn")));
                String intresult=(String.valueOf(validJson.get("TxtResult")));
                if(intresult.equals("1")){
                    String txtGetDataJson = String.valueOf(validJson.get("TxtData"));
                    JSONObject JsonDataTxtData=new JSONObject(txtGetDataJson);
                    String txtGetDataJsonVisitPlan_Dummy = String.valueOf(JsonDataTxtData.get("VisitPlan_Dummy"));
                    JSONObject JsonDataTxtVisitPlan_Dummy=new JSONObject(txtGetDataJsonVisitPlan_Dummy);
                    String txtGetDataJsonVisitPlan_Header_Dummy = String.valueOf(JsonDataTxtVisitPlan_Dummy.get("VisitPlan_Header_Dummy"));
                    String txtGetDataJsonVisitPlan_Detail_Dummy = String.valueOf(JsonDataTxtVisitPlan_Dummy.get("VisitPlan_Detail_Dummy"));
                    JSONArray JSONArrayVisitPlan_Header_Dummy =new JSONArray(txtGetDataJsonVisitPlan_Header_Dummy);
                    JSONArray JSONArrayVisitPlan_Detail_Dummy =new JSONArray(txtGetDataJsonVisitPlan_Detail_Dummy);
                    for (int i = 0; i < JSONArrayVisitPlan_Header_Dummy.length(); i++) {
                        JSONObject JSONObjectVisitPlan_Header_Dummy = JSONArrayVisitPlan_Header_Dummy.getJSONObject(i);
                        clsMobile_trVisitPlan_Header _clsMobile_trVisitPlan_Header=new clsMobile_trVisitPlan_Header();
                        Long IntVisitPlan_HeaderID = Long.valueOf(String.valueOf(JSONObjectVisitPlan_Header_Dummy.get("IntVisitPlan_HeaderID")));
                        List<clsMobile_trVisitPlan_Header> ListOfCheckclsMobile_trVisitPlan_Header=new Select().from(clsMobile_trVisitPlan_Header.class).where(_clsMobile_trVisitPlan_Header.txtConstIntVisitPlan_HeaderID+"=?",IntVisitPlan_HeaderID).execute();
                        clsMobile_trDeviceInfoUser _clsMobile_trDeviceInfoUser=new Mobile_tDeviceInfoUserBL().GetDeviceActive();
                        if(ListOfCheckclsMobile_trVisitPlan_Header != null){
                            if(ListOfCheckclsMobile_trVisitPlan_Header.size()==0){
                                _clsMobile_trVisitPlan_Header=new clsMobile_trVisitPlan_Header();
                                _clsMobile_trVisitPlan_Header.IntVisitPlan_HeaderID=IntVisitPlan_HeaderID;
                                _clsMobile_trVisitPlan_Header.txtGUI_Header=_clsMainBL.GenerateGuid();
                                _clsMobile_trVisitPlan_Header.dtDate=_clsMainBL.GetDateNow();
                                _clsMobile_trVisitPlan_Header.txtDeviceID=_clsMobile_trDeviceInfoUser.txtGUI;
                                _clsMobile_trVisitPlan_Header.txtType=_clsWarning.txtConstTypeWeb;
                                _clsMobile_trVisitPlan_Header.intPeriodID=String.valueOf(JSONObjectVisitPlan_Header_Dummy.get("IntPeriodID"));
                                _clsMobile_trVisitPlan_Header.txtPeriode=String.valueOf(JSONObjectVisitPlan_Header_Dummy.get("TxtPeriode"));
                                _clsMobile_trVisitPlan_Header.intBranchID=String.valueOf(JSONObjectVisitPlan_Header_Dummy.get("IntBranchID"));
                                _clsMobile_trVisitPlan_Header.intPegawaiID=String.valueOf(JSONObjectVisitPlan_Header_Dummy.get("IntPegawaiID"));
                                _clsMobile_trVisitPlan_Header.dtStartWeek=String.valueOf(JSONObjectVisitPlan_Header_Dummy.get("DtStartWeek"));
                                _clsMobile_trVisitPlan_Header.dtEndWeek=String.valueOf(JSONObjectVisitPlan_Header_Dummy.get("DtEndWeek"));
                                _clsMobile_trVisitPlan_Header.intStatus=String.valueOf(JSONObjectVisitPlan_Header_Dummy.get("IntStatus"));
                                _clsMobile_trVisitPlan_Header.intJabatan=String.valueOf(JSONObjectVisitPlan_Header_Dummy.get("IntJabatanID"));
                                _clsMobile_trVisitPlan_Header.intSubmit="1";
                                if(TxtWarn.equals(IntVisitPlan_HeaderID.toString())){
                                    _clsMobile_trVisitPlan_Header.intActivePeriode="1";
                                }else{
                                    _clsMobile_trVisitPlan_Header.intActivePeriode="0";
                                }
                                _clsMobile_trVisitPlan_Header.save();
                            }else{
                                _clsMobile_trVisitPlan_Header=ListOfCheckclsMobile_trVisitPlan_Header.get(0);
                                _clsMobile_trVisitPlan_Header.IntVisitPlan_HeaderID=IntVisitPlan_HeaderID;
                                _clsMobile_trVisitPlan_Header.dtDate=_clsMainBL.GetDateNow();
                                _clsMobile_trVisitPlan_Header.txtDeviceID=_clsMainBL.GetDateNow();
                                _clsMobile_trVisitPlan_Header.txtDeviceID=_clsMobile_trDeviceInfoUser.txtGUI;
                                _clsMobile_trVisitPlan_Header.txtType=_clsWarning.txtConstTypeWeb;
                                _clsMobile_trVisitPlan_Header.intPeriodID=String.valueOf(JSONObjectVisitPlan_Header_Dummy.get("IntPeriodID"));
                                _clsMobile_trVisitPlan_Header.txtPeriode=String.valueOf(JSONObjectVisitPlan_Header_Dummy.get("TxtPeriode"));
                                _clsMobile_trVisitPlan_Header.intBranchID=String.valueOf(JSONObjectVisitPlan_Header_Dummy.get("IntBranchID"));
                                _clsMobile_trVisitPlan_Header.intPegawaiID=String.valueOf(JSONObjectVisitPlan_Header_Dummy.get("IntPegawaiID"));
                                _clsMobile_trVisitPlan_Header.dtStartWeek=String.valueOf(JSONObjectVisitPlan_Header_Dummy.get("DtStartWeek"));
                                _clsMobile_trVisitPlan_Header.dtEndWeek=String.valueOf(JSONObjectVisitPlan_Header_Dummy.get("DtEndWeek"));
                                _clsMobile_trVisitPlan_Header.intStatus=String.valueOf(JSONObjectVisitPlan_Header_Dummy.get("IntStatus"));
                                if(TxtWarn.equals(IntVisitPlan_HeaderID.toString())){
                                    _clsMobile_trVisitPlan_Header.intActivePeriode="1";
                                }else{
                                    _clsMobile_trVisitPlan_Header.intActivePeriode="0";
                                }
                                _clsMobile_trVisitPlan_Header.save();
                            }
                        }
                    }

                    for (int i = 0; i < txtGetDataJsonVisitPlan_Detail_Dummy.length(); i++) {
                        JSONObject JSONObjectVisitPlan_Detail_Dummy = JSONArrayVisitPlan_Detail_Dummy.getJSONObject(i);
                        clsMobile_trVisitPlan_Detail _clsMobile_trVisitPlan_Detail=new clsMobile_trVisitPlan_Detail();
                        Long IntVisitPlan_DetailID = Long.valueOf(String.valueOf(JSONObjectVisitPlan_Detail_Dummy.get("IntVisitPlan_DetailID")));
                        List<clsMobile_trVisitPlan_Detail> ListOfCheckclsMobile_trVisitPlan_Detail=new Select().from(clsMobile_trVisitPlan_Detail.class).where(_clsMobile_trVisitPlan_Detail.txtConstintVisitPlan_DetailID+"=?",IntVisitPlan_DetailID).execute();
                        clsMobile_trDeviceInfoUser _clsMobile_trDeviceInfoUser=new Mobile_tDeviceInfoUserBL().GetDeviceActive();
                        if(ListOfCheckclsMobile_trVisitPlan_Detail != null){
                            if(ListOfCheckclsMobile_trVisitPlan_Detail.size()==0){
                                _clsMobile_trVisitPlan_Detail=new clsMobile_trVisitPlan_Detail();
                                _clsMobile_trVisitPlan_Detail.intVisitPlan_DetailID=IntVisitPlan_DetailID;
                                _clsMobile_trVisitPlan_Detail.txtGUI_Detail=_clsMainBL.GenerateGuid();
                                _clsMobile_trVisitPlan_Detail.intVisitPlan_HeaderID=String.valueOf(JSONObjectVisitPlan_Detail_Dummy.get("IntVisitPlan_HeaderID"));
                                _clsMobile_trVisitPlan_Detail.intLOBID=String.valueOf(JSONObjectVisitPlan_Detail_Dummy.get("IntLOBID"));
                                _clsMobile_trVisitPlan_Detail.txtLOBName=String.valueOf(JSONObjectVisitPlan_Detail_Dummy.get("TxtLOBName"));
                                _clsMobile_trVisitPlan_Detail.intCategoryID=String.valueOf(JSONObjectVisitPlan_Detail_Dummy.get("IntCategoryID"));
                                _clsMobile_trVisitPlan_Detail.txtCategoryName=String.valueOf(JSONObjectVisitPlan_Detail_Dummy.get("TxtCategoryName"));
                                _clsMobile_trVisitPlan_Detail.txtNoKode=String.valueOf(JSONObjectVisitPlan_Detail_Dummy.get("TxtNoKode"));
                                _clsMobile_trVisitPlan_Detail.txtDescription=String.valueOf(JSONObjectVisitPlan_Detail_Dummy.get("TxtDescription"));
                                _clsMobile_trVisitPlan_Detail.dtPlanDate=String.valueOf(JSONObjectVisitPlan_Detail_Dummy.get("DtPlanDate"));
                                _clsMobile_trVisitPlan_Detail.intBobot=String.valueOf(JSONObjectVisitPlan_Detail_Dummy.get("IntBobot"));
                                _clsMobile_trVisitPlan_Detail.dtActualDate=String.valueOf(JSONObjectVisitPlan_Detail_Dummy.get("DtActualDate"));
                                _clsMobile_trVisitPlan_Detail.intJumlahPreNC=String.valueOf(JSONObjectVisitPlan_Detail_Dummy.get("IntJumlahPreNC"));
                                _clsMobile_trVisitPlan_Detail.intSubmit=String.valueOf(JSONObjectVisitPlan_Detail_Dummy.get("IntSubmit"));
                                _clsMobile_trVisitPlan_Detail.bitStatus=String.valueOf(JSONObjectVisitPlan_Detail_Dummy.get("BitStatus"));
                                _clsMobile_trVisitPlan_Detail.txtValidationNo=String.valueOf(JSONObjectVisitPlan_Detail_Dummy.get("TxtValidationNo"));
                                _clsMobile_trVisitPlan_Detail.dtValidated=String.valueOf(JSONObjectVisitPlan_Detail_Dummy.get("DtValidated"));
                                _clsMobile_trVisitPlan_Detail.save();
                            }else{
                                _clsMobile_trVisitPlan_Detail= ListOfCheckclsMobile_trVisitPlan_Detail.get(0);
                                _clsMobile_trVisitPlan_Detail.intVisitPlan_DetailID=IntVisitPlan_DetailID;
                                _clsMobile_trVisitPlan_Detail.intVisitPlan_HeaderID=String.valueOf(JSONObjectVisitPlan_Detail_Dummy.get("IntVisitPlan_HeaderID"));
                                _clsMobile_trVisitPlan_Detail.intLOBID=String.valueOf(JSONObjectVisitPlan_Detail_Dummy.get("IntLOBID"));
                                _clsMobile_trVisitPlan_Detail.txtLOBName=String.valueOf(JSONObjectVisitPlan_Detail_Dummy.get("TxtLOBName"));
                                _clsMobile_trVisitPlan_Detail.intCategoryID=String.valueOf(JSONObjectVisitPlan_Detail_Dummy.get("IntCategoryID"));
                                _clsMobile_trVisitPlan_Detail.txtCategoryName=String.valueOf(JSONObjectVisitPlan_Detail_Dummy.get("TxtCategoryName"));
                                _clsMobile_trVisitPlan_Detail.txtNoKode=String.valueOf(JSONObjectVisitPlan_Detail_Dummy.get("TxtNoKode"));
                                _clsMobile_trVisitPlan_Detail.txtDescription=String.valueOf(JSONObjectVisitPlan_Detail_Dummy.get("TxtDescription"));
                                _clsMobile_trVisitPlan_Detail.dtPlanDate=String.valueOf(JSONObjectVisitPlan_Detail_Dummy.get("DtPlanDate"));
                                _clsMobile_trVisitPlan_Detail.intBobot=String.valueOf(JSONObjectVisitPlan_Detail_Dummy.get("IntBobot"));
                                _clsMobile_trVisitPlan_Detail.dtActualDate=String.valueOf(JSONObjectVisitPlan_Detail_Dummy.get("DtActualDate"));
                                _clsMobile_trVisitPlan_Detail.intJumlahPreNC=String.valueOf(JSONObjectVisitPlan_Detail_Dummy.get("IntJumlahPreNC"));
                                _clsMobile_trVisitPlan_Detail.bitStatus=String.valueOf(JSONObjectVisitPlan_Detail_Dummy.get("BitStatus"));
                                _clsMobile_trVisitPlan_Detail.txtValidationNo=String.valueOf(JSONObjectVisitPlan_Detail_Dummy.get("TxtValidationNo"));
                                _clsMobile_trVisitPlan_Detail.dtValidated=String.valueOf(JSONObjectVisitPlan_Detail_Dummy.get("DtValidated"));
                                _clsMobile_trVisitPlan_Detail.save();
                            }
                        }
                    }
                }else{
                    String txtvalidJsonError=(String.valueOf(JsonRes.get("validJson")));
                    JSONObject validJsonError=new JSONObject(txtvalidJsonError);
                    _clsMainBL.showToast(getContext(),TxtWarn);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
    private void SaveDataDataBranchKNDASHBOARD(JSONObject JsonRes){
        if(_clsMainBL.ValidJSON(JsonRes)){
            String TxtWarn="";
            try {
                String txtvalidJson=(String.valueOf(JsonRes.get("validJson")));
                JSONObject validJson=new JSONObject(txtvalidJson);
                TxtWarn=(String.valueOf(validJson.get("TxtWarn")));
                String intresult=(String.valueOf(validJson.get("TxtResult")));
                if(intresult.equals("1")){
                    clsMobile_mAllbranch _clsMobile_trVisitPlan_Header=new clsMobile_mAllbranch();
                    new Delete().from(clsMobile_mAllbranch.class).execute();
                    String txtGetDataJson = String.valueOf(validJson.get("TxtData"));
                    JSONObject JsonDataTxtData=new JSONObject(txtGetDataJson);
                    String txtGetDataJsonCabangKNDashboard = String.valueOf(JsonDataTxtData.get("CabangKNDashboard"));
                    JSONArray JSONArrayCabangKNDashboard =new JSONArray(txtGetDataJsonCabangKNDashboard);
                    for (int i = 0; i < JSONArrayCabangKNDashboard.length(); i++) {
                        JSONObject JSONObjectCabangKNDashboard = JSONArrayCabangKNDashboard.getJSONObject(i);
                        _clsMobile_trVisitPlan_Header=new clsMobile_mAllbranch();
                        String TxtCabangID = String.valueOf(JSONObjectCabangKNDashboard.get("TxtCabangID"));
                        String TxtKodeCabang = String.valueOf(JSONObjectCabangKNDashboard.get("TxtKodeCabang"));
                        String TxtNamaCabang = String.valueOf(JSONObjectCabangKNDashboard.get("TxtNamaCabang"));
                        _clsMobile_trVisitPlan_Header.IntCabangID=TxtCabangID;
                        _clsMobile_trVisitPlan_Header.TxtKodeCabang=TxtKodeCabang;
                        _clsMobile_trVisitPlan_Header.TxtNamaCabang=TxtNamaCabang;
                        _clsMobile_trVisitPlan_Header.save();
                    }
                }else{
                    String txtvalidJsonError=(String.valueOf(JsonRes.get("validJson")));
                    JSONObject validJsonError=new JSONObject(txtvalidJsonError);
                    _clsMainBL.showToast(getContext(),TxtWarn);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
    private void LoadDataBranchKNDASHBoard(){
        List<clsMobile_mAllbranch> ListOfclsMobile_mAllbranch=new Select().from(clsMobile_mAllbranch.class).execute();
        ListSpinTitle=new ArrayList<String>();
        ListSpinDesc=new ArrayList<String>();
        if(ListOfclsMobile_mAllbranch!=null){
            if(ListOfclsMobile_mAllbranch.size()>0){
                for (clsMobile_mAllbranch _clsMobile_MBranch :ListOfclsMobile_mAllbranch) {
                    ListSpinTitle.add(_clsMobile_MBranch.TxtNamaCabang);
                    ListSpinDesc.add(_clsMobile_MBranch.TxtKodeCabang);
                }
            }
        }
        spnBranchKNDASHBOARD.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, ListSpinTitle, ListSpinDesc));
        spnBranchKNDASHBOARD.setEnabled(true);
    }
    private void LoadDataBranch(){
        List<clsMobile_MBranch> ListOfclsMobile_MBranch=new Select().from(clsMobile_MBranch.class).execute();
        ListSpinTitle=new ArrayList<String>();
        ListSpinDesc=new ArrayList<String>();
        if(ListOfclsMobile_MBranch!=null){
            if(ListOfclsMobile_MBranch.size()>0){
                for (clsMobile_MBranch _clsMobile_MBranch :ListOfclsMobile_MBranch) {
                    ListSpinTitle.add(_clsMobile_MBranch.TxtNamaCabang);
                    ListSpinDesc.add(_clsMobile_MBranch.TxtKodeCabang);
                }
            }
        }
        spnBranch.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, ListSpinTitle, ListSpinDesc));
        spnBranch.setEnabled(true);
    }
    private void SaveDataBranch(JSONObject JsonRes){
        if(_clsMainBL.ValidJSON(JsonRes)){
            try {
                String txtvalidJson=(String.valueOf(JsonRes.get("validJson")));
                JSONObject validJson=new JSONObject(txtvalidJson);
                String intresult=(String.valueOf(validJson.get("TxtResult")));
                if(intresult.equals("1")){
                    String txtGetDataJson = String.valueOf(validJson.get("TxtData"));
                    JSONObject JsonDataTxtData=new JSONObject(txtGetDataJson);
                    String txtGetDataJsonCabang = String.valueOf(JsonDataTxtData.get("Cabang"));
                    JSONArray JSONArrayCabang =new JSONArray(txtGetDataJsonCabang);
                    for (int i = 0; i < JSONArrayCabang.length(); i++) {
                        JSONObject JSONObjectCabang = JSONArrayCabang.getJSONObject(i);
                        clsMobile_MBranch _clsMobile_MBranch=new clsMobile_MBranch();
                        Long TxtCabangID = Long.valueOf(String.valueOf(JSONObjectCabang.get("TxtCabangID")));
                        List<clsMobile_MBranch> ListOfCheckclsMobile_MBranch=new Select().from(clsMobile_MBranch.class).where(_clsMobile_MBranch.txtConstIntCabangID+"=?",TxtCabangID+"").execute();
                        if(ListOfCheckclsMobile_MBranch != null){
                            if(ListOfCheckclsMobile_MBranch.size()==0){
                                _clsMobile_MBranch=new clsMobile_MBranch();
                                _clsMobile_MBranch.IntCabangID=TxtCabangID;
                                _clsMobile_MBranch.TxtKodeCabang=String.valueOf(JSONObjectCabang.get("TxtKodeCabang"));
                                _clsMobile_MBranch.TxtNamaCabang=String.valueOf(JSONObjectCabang.get("TxtNamaCabang"));
                                _clsMobile_MBranch.save();
                            }else{
                                _clsMobile_MBranch=ListOfCheckclsMobile_MBranch.get(0);
                                _clsMobile_MBranch.IntCabangID=TxtCabangID;
                                _clsMobile_MBranch.TxtKodeCabang=String.valueOf(JSONObjectCabang.get("TxtKodeCabang"));;
                                _clsMobile_MBranch.TxtNamaCabang=String.valueOf(JSONObjectCabang.get("TxtNamaCabang"));;
                                _clsMobile_MBranch.save();
                            }
                        }
                    }
                }else{
                    String txtvalidJsonError=(String.valueOf(JsonRes.get("validJson")));
                    JSONObject validJsonError=new JSONObject(txtvalidJsonError);
                    String txtWarn=(String) validJsonError.get("TxtWarn");
                    _clsMainBL.showToast(getContext(),txtWarn);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
    private void LoadDataLOB(){
        List<clsMobile_mLOB> ListOfclsMobile_mLOB=new Select().from(clsMobile_mLOB.class).execute();
        ListSpinTitle=new ArrayList<String>();
        ListSpinDesc=new ArrayList<String>();
        if(ListOfclsMobile_mLOB!=null){
            if(ListOfclsMobile_mLOB.size()>0){
                for (clsMobile_mLOB _clsMobile_mLOB :ListOfclsMobile_mLOB) {
                    ListSpinTitle.add(_clsMobile_mLOB.txtLOBName);
                    ListSpinDesc.add("");
                }
            }
        }
        spnLOB.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, ListSpinTitle, ListSpinDesc));
        spnLOB.setEnabled(true);
    }
    private void SaveDataLOB(JSONObject JsonRes){
        if(_clsMainBL.ValidJSON(JsonRes)){
            try {
                String txtvalidJson=(String.valueOf(JsonRes.get("validJson")));
                JSONObject validJson=new JSONObject(txtvalidJson);
                String intresult=(String.valueOf(validJson.get("TxtResult")));
                if(intresult.equals("1")){
                    String txtGetDataJson = String.valueOf(validJson.get("TxtData"));
                    JSONObject JsonDataTxtData=new JSONObject(txtGetDataJson);
                    String txtGetDataJsonOutlet = String.valueOf(JsonDataTxtData.get("LOB"));
                    JSONArray JSONArrayOutlet =new JSONArray(txtGetDataJsonOutlet);
                    for (int i = 0; i < JSONArrayOutlet.length(); i++) {
                        JSONObject JSONObjectOutlet = JSONArrayOutlet.getJSONObject(i);
                        clsMobile_mLOB _clsMobile_mLOB=new clsMobile_mLOB();
                        Long IntLOBID = Long.valueOf(String.valueOf(JSONObjectOutlet.get("IntLOBID")));
                        List<clsMobile_mLOB> ListOfCheckclsMobile_mLOB=new Select().from(clsMobile_mLOB.class).where(_clsMobile_mLOB.txtConstintLOBID+"=?",IntLOBID).execute();
                        if(ListOfCheckclsMobile_mLOB != null){
                            if(ListOfCheckclsMobile_mLOB.size()==0){
                                _clsMobile_mLOB=new clsMobile_mLOB();
                                _clsMobile_mLOB.intLOBID=IntLOBID;
                                _clsMobile_mLOB.txtLOBDescription=String.valueOf(JSONObjectOutlet.get("TxtLOBDescription"));
                                _clsMobile_mLOB.txtLOBName=String.valueOf(JSONObjectOutlet.get("TxtLOBName"));
                                _clsMobile_mLOB.save();
                            }else{
                                _clsMobile_mLOB=ListOfCheckclsMobile_mLOB.get(0);
                                _clsMobile_mLOB.intLOBID=IntLOBID;
                                _clsMobile_mLOB.txtLOBDescription=String.valueOf(JSONObjectOutlet.get("TxtLOBDescription"));
                                _clsMobile_mLOB.txtLOBName=String.valueOf(JSONObjectOutlet.get("TxtLOBName"));
                                _clsMobile_mLOB.save();
                            }
                        }
                    }
                }else{
                    String txtvalidJsonError=(String.valueOf(JsonRes.get("validJson")));
                    JSONObject validJsonError=new JSONObject(txtvalidJsonError);
                    String txtWarn=(String) validJsonError.get("TxtWarn");
                    _clsMainBL.showToast(getContext(),txtWarn);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
    private void LoadDataCategory(){
        List<clsMobile_mVisitPlanCategory> ListOfclsMobile_mVisitPlanCategory=new Select().from(clsMobile_mVisitPlanCategory.class).execute();
        ListSpinTitle=new ArrayList<String>();
        ListSpinDesc=new ArrayList<String>();
        if(ListOfclsMobile_mVisitPlanCategory!=null){
            if(ListOfclsMobile_mVisitPlanCategory.size()>0){
                for (clsMobile_mVisitPlanCategory _clsMobile_mVisitPlanCategory :ListOfclsMobile_mVisitPlanCategory) {
                    ListSpinTitle.add(_clsMobile_mVisitPlanCategory.txtCategoryName);
                    ListSpinDesc.add("Bobot : "+ _clsMobile_mVisitPlanCategory.intNilaiBobot+"");
                }
            }
        }
        spnCategory.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, ListSpinTitle, ListSpinDesc));
        spnCategory.setEnabled(true);
    }
    private void SaveDataCategory(JSONObject JsonRes){
        if(_clsMainBL.ValidJSON(JsonRes)){
            try {
                String txtvalidJson=(String.valueOf(JsonRes.get("validJson")));
                JSONObject validJson=new JSONObject(txtvalidJson);
                String intresult=(String.valueOf(validJson.get("TxtResult")));
                if(intresult.equals("1")){
                    String txtGetDataJson = String.valueOf(validJson.get("TxtData"));
                    JSONObject JsonDataTxtData=new JSONObject(txtGetDataJson);
                    String txtGetDataJsonCategory = String.valueOf(JsonDataTxtData.get("VisitPlanCategory"));
                    String txtGetDataJsonCategoryDetail = String.valueOf(JsonDataTxtData.get("VisitPlanCategoryDetail"));
                    JSONArray JSONArrayCategoryDetail =new JSONArray(txtGetDataJsonCategoryDetail);
                    JSONArray JSONArrayOutlet =new JSONArray(txtGetDataJsonCategory);
                    for (int i = 0; i < JSONArrayCategoryDetail.length(); i++) {
                        JSONObject JSONObjectOutlet = JSONArrayCategoryDetail.getJSONObject(i);
                        clsMobile_mVisitPlanCategoryDetail _clsMobile_mVisitPlanCategoryDetail=new clsMobile_mVisitPlanCategoryDetail();
                        Long intCategory_DetailID = Long.valueOf(String.valueOf(JSONObjectOutlet.get("IntCategory_DetailID")));
                        List<clsMobile_mVisitPlanCategoryDetail> ListOfCheckclsMobile_mVisitPlanCategory=new Select().from(clsMobile_mVisitPlanCategoryDetail.class).where(_clsMobile_mVisitPlanCategoryDetail.txtConstintCategoryIDDetail+"=?",intCategory_DetailID).execute();
                        if(ListOfCheckclsMobile_mVisitPlanCategory != null){
                            if(ListOfCheckclsMobile_mVisitPlanCategory.size()==0){
                                _clsMobile_mVisitPlanCategoryDetail=new clsMobile_mVisitPlanCategoryDetail();
                                _clsMobile_mVisitPlanCategoryDetail.intCategoryID=Long.valueOf(JSONObjectOutlet.get("IntCategoryID")+"");
                                _clsMobile_mVisitPlanCategoryDetail.intCategoryIDDetail=Long.valueOf(JSONObjectOutlet.get("IntCategory_DetailID")+"");
                                _clsMobile_mVisitPlanCategoryDetail.IntJabatanID=Long.valueOf(JSONObjectOutlet.get("IntJabatanID")+"");
                                _clsMobile_mVisitPlanCategoryDetail.IntBobot=Long.valueOf(String.valueOf(JSONObjectOutlet.get("IntNilaiBobot"))+"");
                                _clsMobile_mVisitPlanCategoryDetail.save();
                            }else{
                                _clsMobile_mVisitPlanCategoryDetail=ListOfCheckclsMobile_mVisitPlanCategory.get(0);
                                _clsMobile_mVisitPlanCategoryDetail.intCategoryID=Long.valueOf(JSONObjectOutlet.get("IntCategoryID")+"");
                                _clsMobile_mVisitPlanCategoryDetail.IntJabatanID=Long.valueOf(JSONObjectOutlet.get("IntJabatanID")+"");
                                _clsMobile_mVisitPlanCategoryDetail.IntBobot=Long.valueOf(String.valueOf(JSONObjectOutlet.get("IntNilaiBobot"))+"");
                                _clsMobile_mVisitPlanCategoryDetail.save();
                            }
                        }
                    }
                    for (int i = 0; i < JSONArrayOutlet.length(); i++) {
                        JSONObject JSONObjectOutlet = JSONArrayOutlet.getJSONObject(i);
                        clsMobile_mVisitPlanCategory _clsMobile_mVisitPlanCategory=new clsMobile_mVisitPlanCategory();
                        Long IntCategoryID = Long.valueOf(String.valueOf(JSONObjectOutlet.get("IntCategoryID")));
                        List<clsMobile_mVisitPlanCategory> ListOfCheckclsMobile_mVisitPlanCategory=new Select().from(clsMobile_mVisitPlanCategory.class).where(_clsMobile_mVisitPlanCategory.txtConstintCategoryID+"=?",IntCategoryID).execute();
                        if(ListOfCheckclsMobile_mVisitPlanCategory != null){
                            if(ListOfCheckclsMobile_mVisitPlanCategory.size()==0){
                                _clsMobile_mVisitPlanCategory=new clsMobile_mVisitPlanCategory();
                                _clsMobile_mVisitPlanCategory.intCategoryID=IntCategoryID;
                                _clsMobile_mVisitPlanCategory.txtCategoryName=String.valueOf(JSONObjectOutlet.get("TxtCategoryName"));
                                _clsMobile_mVisitPlanCategory.intNilaiBobot=Long.valueOf(String.valueOf(JSONObjectOutlet.get("IntNilaiBobot")));
                                if(String.valueOf(JSONObjectOutlet.get("BitFullDay")).equals("true")){
                                    _clsMobile_mVisitPlanCategory.bitFullDay=1;
                                }else{
                                    _clsMobile_mVisitPlanCategory.bitFullDay=0;
                                }
                                if(String.valueOf(JSONObjectOutlet.get("BitActive")).equals("true")){
                                    _clsMobile_mVisitPlanCategory.bitActive=1;
                                }else{
                                    _clsMobile_mVisitPlanCategory.bitActive=0;
                                }
                                _clsMobile_mVisitPlanCategory.save();
                            }else{
                                _clsMobile_mVisitPlanCategory=ListOfCheckclsMobile_mVisitPlanCategory.get(0);
                                _clsMobile_mVisitPlanCategory.intCategoryID=IntCategoryID;
                                _clsMobile_mVisitPlanCategory.txtCategoryName=String.valueOf(JSONObjectOutlet.get("TxtCategoryName"));
                                _clsMobile_mVisitPlanCategory.intNilaiBobot=Long.valueOf(String.valueOf(JSONObjectOutlet.get("IntNilaiBobot")));
                                if(String.valueOf(JSONObjectOutlet.get("BitFullDay")).equals("true")){
                                    _clsMobile_mVisitPlanCategory.bitFullDay=1;
                                }else{
                                    _clsMobile_mVisitPlanCategory.bitFullDay=0;
                                }
                                if(String.valueOf(JSONObjectOutlet.get("BitActive")).equals("true")){
                                    _clsMobile_mVisitPlanCategory.bitActive=1;
                                }else{
                                    _clsMobile_mVisitPlanCategory.bitActive=0;
                                }
                                _clsMobile_mVisitPlanCategory.save();
                            }
                        }
                    }
                }else{
                    String txtvalidJsonError=(String.valueOf(JsonRes.get("validJson")));
                    JSONObject validJsonError=new JSONObject(txtvalidJsonError);
                    String txtWarn=(String) validJsonError.get("TxtWarn");
                    _clsMainBL.showToast(getContext(),txtWarn);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
    private void LoadDataPOA(){
        List<clsMobile_trPOA> ListOfclsMobile_trPOA=new Select().from(clsMobile_trPOA.class).execute();
        ListSpinTitle=new ArrayList<String>();
        ListSpinDesc=new ArrayList<String>();
        if(ListOfclsMobile_trPOA!=null){
            if(ListOfclsMobile_trPOA.size()>0){
                for (clsMobile_trPOA _clsMobile_trPOA :ListOfclsMobile_trPOA) {
                    ListSpinTitle.add(_clsMobile_trPOA.txtNamaProgram);
                    ListSpinDesc.add(_clsMobile_trPOA.txtProgramDescription);
                }
            }
        }
        spnPOA.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, ListSpinTitle, ListSpinDesc));
        spnPOA.setEnabled(true);
    }
    private void SaveDataPOA(JSONObject JsonRes){
        if(_clsMainBL.ValidJSON(JsonRes)){
            try {
                String txtvalidJson=(String.valueOf(JsonRes.get("validJson")));
                JSONObject validJson=new JSONObject(txtvalidJson);
                String intresult=(String.valueOf(validJson.get("TxtResult")));
                clsMobile_trPOA _clsMobile_trPOA=new clsMobile_trPOA();
                if(intresult.equals("1")){
                    String txtGetDataJson = String.valueOf(validJson.get("TxtData"));
                    JSONObject JsonDataTxtData=new JSONObject(txtGetDataJson);
                    String txtGetDataJsonOutlet = String.valueOf(JsonDataTxtData.get("VisitPlanPOA"));
                    JSONArray JSONArrayOutlet =new JSONArray(txtGetDataJsonOutlet);
                    for (int i = 0; i < JSONArrayOutlet.length(); i++) {
                        JSONObject JSONObjectOutlet = JSONArrayOutlet.getJSONObject(i);
                        Long IntProgramID = Long.valueOf(String.valueOf(JSONObjectOutlet.get("IntProgramID")));
                        List<clsMobile_trPOA> ListOfCheckPOA=new Select().from(clsMobile_trPOA.class).where(_clsMobile_trPOA.txtConstintProgramID+"=?",IntProgramID).execute();
                        if(ListOfCheckPOA != null){
                            if(ListOfCheckPOA.size()==0){
                                _clsMobile_trPOA=new clsMobile_trPOA();
                                _clsMobile_trPOA.intProgramID=IntProgramID;
                                _clsMobile_trPOA.txtNamaProgram=String.valueOf(JSONObjectOutlet.get("TxtNamaProgram"));
                                _clsMobile_trPOA.dtStartDate=String.valueOf(JSONObjectOutlet.get("DtStartDate"));
                                _clsMobile_trPOA.dtEndDate=String.valueOf(JSONObjectOutlet.get("DtEndDate"));
                                _clsMobile_trPOA.txtServer=String.valueOf(JSONObjectOutlet.get("TxtServer"));
                                _clsMobile_trPOA.txtProgramDescription=String.valueOf(JSONObjectOutlet.get("TxtProgramDescription"));
                                _clsMobile_trPOA.save();
                            }else{
                                _clsMobile_trPOA=ListOfCheckPOA.get(0);
                                _clsMobile_trPOA.intProgramID=IntProgramID;
                                _clsMobile_trPOA.txtNamaProgram=String.valueOf(JSONObjectOutlet.get("TxtNamaProgram"));
                                _clsMobile_trPOA.dtStartDate=String.valueOf(JSONObjectOutlet.get("DtStartDate"));
                                _clsMobile_trPOA.dtEndDate=String.valueOf(JSONObjectOutlet.get("DtEndDate"));
                                _clsMobile_trPOA.txtServer=String.valueOf(JSONObjectOutlet.get("TxtServer"));
                                _clsMobile_trPOA.txtProgramDescription=String.valueOf(JSONObjectOutlet.get("TxtProgramDescription"));
                                _clsMobile_trPOA.save();
                            }
                        }
                    }
                }else{
                    String txtvalidJsonError=(String.valueOf(JsonRes.get("validJson")));
                    JSONObject validJsonError=new JSONObject(txtvalidJsonError);
                    String txtWarn=(String) validJsonError.get("TxtWarn");
                    _clsMainBL.showToast(getContext(),txtWarn);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
    private void LoadDataOutletV2(){
        List<clsMobile_MPartnerProfile> ListOfclsMobile_MPartnerProfile=new Select().from(clsMobile_MPartnerProfile.class).execute();
        List<clsMobile_MPartnerProfileAlias> ListOfclsMobile_MPartnerProfileALias=new Select().from(clsMobile_MPartnerProfileAlias.class).execute();
        ListSpinTitle=new ArrayList<String>();
        ListSpinDesc=new ArrayList<String>();
        if(ListOfclsMobile_MPartnerProfile!=null){
            if(ListOfclsMobile_MPartnerProfile.size()>0){
                for (clsMobile_MPartnerProfile _clsMobile_MPartnerProfile :ListOfclsMobile_MPartnerProfile) {
                    ListSpinTitle.add(_clsMobile_MPartnerProfile.txtNamaPartner);
                    ListSpinDesc.add(_clsMobile_MPartnerProfile.txtAlamat+System.getProperty("line.separator")+
                            _clsMobile_MPartnerProfile.txtTipePartner+System.getProperty("line.separator")+
                            _clsMobile_MPartnerProfile.txtLatitude+" "+_clsMobile_MPartnerProfile.txtLongitude);
                }
            }
        }
        spnOutlet.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, ListSpinTitle, ListSpinDesc));
        spnOutlet.setEnabled(true);
        ListSpinTitle=new ArrayList<String>();
        ListSpinDesc=new ArrayList<String>();
        if(ListOfclsMobile_MPartnerProfileALias!=null){
            if(ListOfclsMobile_MPartnerProfileALias.size()>0){
                for (clsMobile_MPartnerProfileAlias _clsMobile_MPartnerProfile :ListOfclsMobile_MPartnerProfileALias) {
                    ListSpinTitle.add(_clsMobile_MPartnerProfile.txtNamaPartner);
                    ListSpinDesc.add(_clsMobile_MPartnerProfile.txtAlamat+System.getProperty("line.separator")+
                            _clsMobile_MPartnerProfile.txtTipePartner+System.getProperty("line.separator")+
                            _clsMobile_MPartnerProfile.txtLatitude+" "+_clsMobile_MPartnerProfile.txtLongitude);
                }
            }
        }
        spnOutletAlias.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, ListSpinTitle, ListSpinDesc));
        spnOutletAlias.setEnabled(true);
    }
    private void LoadDataOutlet(){
        List<clsMobile_MPartnerProfile> ListOfclsMobile_MPartnerProfile=new Select().from(clsMobile_MPartnerProfile.class).execute();
        ListSpinTitle=new ArrayList<String>();
        ListSpinDesc=new ArrayList<String>();
        if(ListOfclsMobile_MPartnerProfile!=null){
            if(ListOfclsMobile_MPartnerProfile.size()>0){
                for (clsMobile_MPartnerProfile _clsMobile_MPartnerProfile :ListOfclsMobile_MPartnerProfile) {
                    ListSpinTitle.add(_clsMobile_MPartnerProfile.txtNamaPartner);
                    if(_clsMobile_MPartnerProfile.txtChannelName.equals("null")==false){
                        ListSpinDesc.add(_clsMobile_MPartnerProfile.txtChannelName);
                    }else{
                        ListSpinDesc.add("-");
                    }

                }
            }
        }
        spnOutlet.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, ListSpinTitle, ListSpinDesc));
        spnOutlet.setEnabled(true);
    }
    private void SaveDataOutletV2(JSONObject JsonRes){
        if(_clsMainBL.ValidJSON(JsonRes)){
            try {
                String txtvalidJson=(String.valueOf(JsonRes.get("validJson")));
                JSONObject validJson=new JSONObject(txtvalidJson);
                String intresult=(String.valueOf(validJson.get("TxtResult")));
                if(intresult.equals("1")){
                    String txtGetDataJson = String.valueOf(validJson.get("TxtData"));
                    JSONObject JsonDataTxtData=new JSONObject(txtGetDataJson);
                    String txtGetDataJsonOutlet = String.valueOf(JsonDataTxtData.get("MPartnerProfile"));
                    if(txtGetDataJsonOutlet != null){
                        JSONArray JSONArrayOutlet =new JSONArray(txtGetDataJsonOutlet);
                        for (int i = 0; i < JSONArrayOutlet.length(); i++) {
                            JSONObject JSONObjectOutlet = JSONArrayOutlet.getJSONObject(i);
                            clsMobile_MPartnerProfile _clsMobile_MPartnerProfile=new clsMobile_MPartnerProfile();
                            Long IntPartnerID = Long.valueOf(String.valueOf(JSONObjectOutlet.get("IntPartnerID")));
                            List<clsMobile_MPartnerProfile> ListOfCheckclsMobile_MPartnerProfile=new Select().from(clsMobile_MPartnerProfile.class).where(_clsMobile_MPartnerProfile.txtConstIntPartnerID+"=?",IntPartnerID).execute();
                            if(ListOfCheckclsMobile_MPartnerProfile != null){
                                if(ListOfCheckclsMobile_MPartnerProfile.size()==0){
                                    _clsMobile_MPartnerProfile=new clsMobile_MPartnerProfile();
                                    _clsMobile_MPartnerProfile.IntPartnerID=IntPartnerID;
                                    _clsMobile_MPartnerProfile.txtPartnerCRM=String.valueOf(JSONObjectOutlet.get("TxtPartnerCRM"));
                                    _clsMobile_MPartnerProfile.txtNamaPartner=String.valueOf(JSONObjectOutlet.get("TxtNamaPartner"));
                                    _clsMobile_MPartnerProfile.txtChannelName=String.valueOf(JSONObjectOutlet.get("TxtChannelName"));
                                    _clsMobile_MPartnerProfile.txtAlamat=String.valueOf(JSONObjectOutlet.get("TxtAlamat"));
                                    _clsMobile_MPartnerProfile.intBranchID=Long.valueOf(String.valueOf(JSONObjectOutlet.get("IntBranchID")));
                                    _clsMobile_MPartnerProfile.txtBranchName=String.valueOf(JSONObjectOutlet.get("TxtBranchName"));
                                    _clsMobile_MPartnerProfile.txtLongitude=String.valueOf(JSONObjectOutlet.get("TxtLongitude"));
                                    _clsMobile_MPartnerProfile.txtLatitude=String.valueOf(JSONObjectOutlet.get("TxtLatitude"));
                                    _clsMobile_MPartnerProfile.txtAcc=String.valueOf(JSONObjectOutlet.get("TxtAcc"));
                                    _clsMobile_MPartnerProfile.txtTipePartner=String.valueOf(JSONObjectOutlet.get("TxtTipePartner"));
                                    _clsMobile_MPartnerProfile.save();
                                }else{
                                    _clsMobile_MPartnerProfile=ListOfCheckclsMobile_MPartnerProfile.get(0);
                                    _clsMobile_MPartnerProfile.IntPartnerID=IntPartnerID;
                                    _clsMobile_MPartnerProfile.txtPartnerCRM=String.valueOf(JSONObjectOutlet.get("TxtPartnerCRM"));
                                    _clsMobile_MPartnerProfile.txtNamaPartner=String.valueOf(JSONObjectOutlet.get("TxtNamaPartner"));
                                    _clsMobile_MPartnerProfile.txtChannelName=String.valueOf(JSONObjectOutlet.get("TxtChannelName"));
                                    _clsMobile_MPartnerProfile.txtAlamat=String.valueOf(JSONObjectOutlet.get("TxtAlamat"));
                                    _clsMobile_MPartnerProfile.intBranchID=Long.valueOf(String.valueOf(JSONObjectOutlet.get("IntBranchID")));
                                    _clsMobile_MPartnerProfile.txtBranchName=String.valueOf(JSONObjectOutlet.get("TxtBranchName"));
                                    _clsMobile_MPartnerProfile.txtLongitude=String.valueOf(JSONObjectOutlet.get("TxtLongitude"));
                                    _clsMobile_MPartnerProfile.txtLatitude=String.valueOf(JSONObjectOutlet.get("TxtLatitude"));
                                    _clsMobile_MPartnerProfile.txtAcc=String.valueOf(JSONObjectOutlet.get("TxtAcc"));
                                    _clsMobile_MPartnerProfile.txtTipePartner=String.valueOf(JSONObjectOutlet.get("TxtTipePartner"));
                                    _clsMobile_MPartnerProfile.save();
                                }
                            }
                        }
                    }
                    String txtGetDataJsonOutletAlias = String.valueOf(JsonDataTxtData.get("MPartnerProfileAlias"));
                    if(txtGetDataJsonOutletAlias != null){
                        JSONArray JSONArrayOutlet =new JSONArray(txtGetDataJsonOutletAlias);
                        for (int i = 0; i < JSONArrayOutlet.length(); i++) {
                            JSONObject JSONObjectOutlet = JSONArrayOutlet.getJSONObject(i);
                            clsMobile_MPartnerProfileAlias _clsMobile_MPartnerProfile=new clsMobile_MPartnerProfileAlias();
                            Long IntPartnerID = Long.valueOf(String.valueOf(JSONObjectOutlet.get("IntPartnerID")));
                            List<clsMobile_MPartnerProfileAlias> ListOfCheckclsMobile_MPartnerProfile=new Select().from(clsMobile_MPartnerProfileAlias.class).where(_clsMobile_MPartnerProfile.txtConstIntPartnerID+"=?",IntPartnerID).execute();
                            if(ListOfCheckclsMobile_MPartnerProfile != null){
                                if(ListOfCheckclsMobile_MPartnerProfile.size()==0){
                                    _clsMobile_MPartnerProfile=new clsMobile_MPartnerProfileAlias();
                                    _clsMobile_MPartnerProfile.IntPartnerID=IntPartnerID;
                                    _clsMobile_MPartnerProfile.txtPartnerCRM=String.valueOf(JSONObjectOutlet.get("TxtPartnerCRM"));
                                    _clsMobile_MPartnerProfile.txtNamaPartner=String.valueOf(JSONObjectOutlet.get("TxtNamaPartner"));
                                    _clsMobile_MPartnerProfile.txtChannelName=String.valueOf(JSONObjectOutlet.get("TxtChannelName"));
                                    _clsMobile_MPartnerProfile.txtAlamat=String.valueOf(JSONObjectOutlet.get("TxtAlamat"));
                                    _clsMobile_MPartnerProfile.intBranchID=Long.valueOf(String.valueOf(JSONObjectOutlet.get("IntBranchID")));
                                    _clsMobile_MPartnerProfile.txtBranchName=String.valueOf(JSONObjectOutlet.get("TxtBranchName"));
                                    _clsMobile_MPartnerProfile.txtLongitude=String.valueOf(JSONObjectOutlet.get("TxtLongitude"));
                                    _clsMobile_MPartnerProfile.txtLatitude=String.valueOf(JSONObjectOutlet.get("TxtLatitude"));
                                    _clsMobile_MPartnerProfile.txtAcc=String.valueOf(JSONObjectOutlet.get("TxtAcc"));
                                    _clsMobile_MPartnerProfile.txtTipePartner=String.valueOf(JSONObjectOutlet.get("TxtTipePartner"));
                                    _clsMobile_MPartnerProfile.intPartnerIDSub=String.valueOf(JSONObjectOutlet.get("IntPartnerIDSub"));
                                    _clsMobile_MPartnerProfile.save();
                                }else{
                                    _clsMobile_MPartnerProfile=ListOfCheckclsMobile_MPartnerProfile.get(0);
                                    _clsMobile_MPartnerProfile.IntPartnerID=IntPartnerID;
                                    _clsMobile_MPartnerProfile.txtPartnerCRM=String.valueOf(JSONObjectOutlet.get("TxtPartnerCRM"));
                                    _clsMobile_MPartnerProfile.txtNamaPartner=String.valueOf(JSONObjectOutlet.get("TxtNamaPartner"));
                                    _clsMobile_MPartnerProfile.txtChannelName=String.valueOf(JSONObjectOutlet.get("TxtChannelName"));
                                    _clsMobile_MPartnerProfile.txtAlamat=String.valueOf(JSONObjectOutlet.get("TxtAlamat"));
                                    _clsMobile_MPartnerProfile.intBranchID=Long.valueOf(String.valueOf(JSONObjectOutlet.get("IntBranchID")));
                                    _clsMobile_MPartnerProfile.txtBranchName=String.valueOf(JSONObjectOutlet.get("TxtBranchName"));
                                    _clsMobile_MPartnerProfile.txtLongitude=String.valueOf(JSONObjectOutlet.get("TxtLongitude"));
                                    _clsMobile_MPartnerProfile.txtLatitude=String.valueOf(JSONObjectOutlet.get("TxtLatitude"));
                                    _clsMobile_MPartnerProfile.txtAcc=String.valueOf(JSONObjectOutlet.get("TxtAcc"));
                                    _clsMobile_MPartnerProfile.txtTipePartner=String.valueOf(JSONObjectOutlet.get("TxtTipePartner"));
                                    _clsMobile_MPartnerProfile.intPartnerIDSub=String.valueOf(JSONObjectOutlet.get("IntPartnerIDSub"));
                                    _clsMobile_MPartnerProfile.save();
                                }
                            }
                        }
                    }
                }else{
                    String txtvalidJsonError=(String.valueOf(JsonRes.get("validJson")));
                    JSONObject validJsonError=new JSONObject(txtvalidJsonError);
                    String txtWarn=(String) validJsonError.get("TxtWarn");
                    _clsMainBL.showToast(getContext(),txtWarn);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
    private void SaveDataOutlet(JSONObject JsonRes){
        if(_clsMainBL.ValidJSON(JsonRes)){
            try {
                String txtvalidJson=(String.valueOf(JsonRes.get("validJson")));
                JSONObject validJson=new JSONObject(txtvalidJson);
                String intresult=(String.valueOf(validJson.get("TxtResult")));
                if(intresult.equals("1")){
                    String txtGetDataJson = String.valueOf(validJson.get("TxtData"));
                    JSONObject JsonDataTxtData=new JSONObject(txtGetDataJson);
                    String txtGetDataJsonOutlet = String.valueOf(JsonDataTxtData.get("Outlet"));
                    JSONArray JSONArrayOutlet =new JSONArray(txtGetDataJsonOutlet);
                    for (int i = 0; i < JSONArrayOutlet.length(); i++) {
                        JSONObject JSONObjectOutlet = JSONArrayOutlet.getJSONObject(i);
                        clsMobile_MPartnerProfile _clsMobile_MPartnerProfile=new clsMobile_MPartnerProfile();
                        Long IntPartnerID = Long.valueOf(String.valueOf(JSONObjectOutlet.get("IntPartnerID")));
                        List<clsMobile_MPartnerProfile> ListOfCheckclsMobile_MPartnerProfile=new Select().from(clsMobile_MPartnerProfile.class).where(_clsMobile_MPartnerProfile.txtConstIntPartnerID+"=?",IntPartnerID).execute();
                        if(ListOfCheckclsMobile_MPartnerProfile != null){
                            if(ListOfCheckclsMobile_MPartnerProfile.size()==0){
                                _clsMobile_MPartnerProfile=new clsMobile_MPartnerProfile();
                                _clsMobile_MPartnerProfile.IntPartnerID=IntPartnerID;
                                _clsMobile_MPartnerProfile.txtPartnerCRM=String.valueOf(JSONObjectOutlet.get("TxtPartnerCRM"));
                                _clsMobile_MPartnerProfile.txtNamaPartner=String.valueOf(JSONObjectOutlet.get("TxtNamaPartner"));
                                _clsMobile_MPartnerProfile.txtChannelName=String.valueOf(JSONObjectOutlet.get("TxtChannelName"));
                                _clsMobile_MPartnerProfile.txtAlamat=String.valueOf(JSONObjectOutlet.get("TxtAlamat"));
                                _clsMobile_MPartnerProfile.intBranchID=Long.valueOf(String.valueOf(JSONObjectOutlet.get("IntBranchID")));
                                _clsMobile_MPartnerProfile.txtBranchName=String.valueOf(JSONObjectOutlet.get("TxtBranchName"));
                                _clsMobile_MPartnerProfile.txtLongitude=String.valueOf(JSONObjectOutlet.get("TxtLongitude"));
                                _clsMobile_MPartnerProfile.txtLatitude=String.valueOf(JSONObjectOutlet.get("TxtLatitude"));
                                _clsMobile_MPartnerProfile.save();
                            }else{
                                _clsMobile_MPartnerProfile=ListOfCheckclsMobile_MPartnerProfile.get(0);
                                _clsMobile_MPartnerProfile.IntPartnerID=IntPartnerID;
                                _clsMobile_MPartnerProfile.txtPartnerCRM=String.valueOf(JSONObjectOutlet.get("TxtPartnerCRM"));
                                _clsMobile_MPartnerProfile.txtNamaPartner=String.valueOf(JSONObjectOutlet.get("TxtNamaPartner"));
                                _clsMobile_MPartnerProfile.txtChannelName=String.valueOf(JSONObjectOutlet.get("TxtChannelName"));
                                _clsMobile_MPartnerProfile.txtAlamat=String.valueOf(JSONObjectOutlet.get("TxtAlamat"));
                                _clsMobile_MPartnerProfile.intBranchID=Long.valueOf(String.valueOf(JSONObjectOutlet.get("IntBranchID")));
                                _clsMobile_MPartnerProfile.txtBranchName=String.valueOf(JSONObjectOutlet.get("TxtBranchName"));
                                _clsMobile_MPartnerProfile.txtLongitude=String.valueOf(JSONObjectOutlet.get("TxtLongitude"));
                                _clsMobile_MPartnerProfile.txtLatitude=String.valueOf(JSONObjectOutlet.get("TxtLatitude"));
                                _clsMobile_MPartnerProfile.save();
                            }
                        }
                    }
                }else{
                    String txtvalidJsonError=(String.valueOf(JsonRes.get("validJson")));
                    JSONObject validJsonError=new JSONObject(txtvalidJsonError);
                    String txtWarn=(String) validJsonError.get("TxtWarn");
                    _clsMainBL.showToast(getContext(),txtWarn);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
    public class MyAdapter extends ArrayAdapter<String>
    {
        private List<String> arrayDataAdapyter;
        private List<String> arrayDataAdapyterDesc;
        private Context Ctx;

        public List<String> getArrayDataAdapyterDesc() {
            return arrayDataAdapyterDesc;
        }
        public void setArrayDataAdapyterDesc(List<String> arrayDataAdapyterDesc) {
            this.arrayDataAdapyterDesc = arrayDataAdapyterDesc;
        }

        public List<String> getArrayDataAdapyter() {
            return arrayDataAdapyter;
        }
        public void setArrayDataAdapyter(List<String> arrayDataAdapyter) {
            this.arrayDataAdapyter = arrayDataAdapyter;
        }
        public Context getCtx() {
            return Ctx;
        }
        public void setCtx(Context ctx) {
            Ctx = ctx;
        }
        public MyAdapter(Context context, int textViewResourceId, List<String> objects,List<String> objectsDesc)
        {
            super(context, textViewResourceId, objects);
            setCtx(context);
            setArrayDataAdapyter(objects);
            setArrayDataAdapyterDesc(objectsDesc);
            // TODO Auto-generated constructor stub
        }
        @Override
        public View getDropDownView(int position, View convertView,ViewGroup parent)
        {
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            return getCustomView(position, convertView, parent);
        }

        public View getCustomView(int position, View convertView, ViewGroup parent)
        {
            LayoutInflater inflater=getActivity().getLayoutInflater();
            View row=inflater.inflate(R.layout.custom_spinner, parent, false);
            if(getArrayDataAdapyter().size()>0){
                TextView label=(TextView)row.findViewById(R.id.tvTitle);
                label.setText(getArrayDataAdapyter().get(position));
                label.setTextColor(new Color().parseColor("#000000"));
                TextView sub=(TextView)row.findViewById(R.id.tvDesc);
                if(getArrayDataAdapyterDesc().get(position).equals("")){
                    sub.setVisibility(View.INVISIBLE);
                    sub.setVisibility(View.GONE);
                }else{
                    sub.setVisibility(View.VISIBLE);
                }
                sub.setText(getArrayDataAdapyterDesc().get(position));
                sub.setTextColor(new Color().parseColor("#000000"));
                row.setBackgroundColor(new Color().parseColor("#FFFFFF"));
            }
            //sub.setText(mydata2[position]);
            return row;
        }

    }
    private class AsyncCall extends AsyncTask<JSONObject, Void, JSONObject> {
        @Override
        protected JSONObject doInBackground(JSONObject... params) {
            JSONArray _JSONArray = null;
            JSONObject JsonParam=new JSONObject();
            JSONObject JsonResult=null;
            clsMobile_mVersionApp _clsMobile_mVersionApp=new clsMobile_mVersionApp();
            List<clsMobile_mVersionApp> listOfclsMobile_mVersionApp= new Select().from(clsMobile_mVersionApp.class).where(_clsMobile_mVersionApp.txtConstidVersion+"=?",1).execute();
            clsMobile_trUserLogin dtclsMobile_trUserLogin= new Mobile_trUserLoginBL().CheckUserLogin();
            clsMobile_UserJabatan _clsMobile_UserJabatan=new clsMobile_UserJabatan();
            List<clsMobile_UserJabatan> LisOfjabatan=new Select().from(clsMobile_UserJabatan.class).where(_clsMobile_UserJabatan.txtConstBitPrimary+"=1").execute();
            List<clsMobile_MBranch> ListOfCabang=new Select().from(clsMobile_MBranch.class).execute();
            try {
                _JSONArray=new JSONArray();
                JsonParam.put("TxtGUI_trUserLogin",dtclsMobile_trUserLogin.txtGUI.toString());
                JsonParam.put("TxtUserID",dtclsMobile_trUserLogin.txtUserID.toString());
                JsonParam.put("TxtGUI_mVersionApp",listOfclsMobile_mVersionApp.get(0).txtGUI);
                String txtCabang ="";
                int IndexCabang=0;
                if(ListOfCabang.size()>0){
                    for (clsMobile_MBranch dtclsMobile_MBranch :ListOfCabang) {
                        if(IndexCabang>0){
                            txtCabang+=",";
                        }
                        txtCabang+=dtclsMobile_MBranch.IntCabangID;
                        IndexCabang+=1;
                    }
                }else{
                    txtCabang=dtclsMobile_trUserLogin.IntCabangID;
                }
                JsonParam.put("IntCabangID",txtCabang);
                JsonParam.put("IntCabangPrimaryID",dtclsMobile_trUserLogin.IntCabangID);
                _JSONArray.put(JsonParam);

                if(txtProcess.equals(_clsWarning.txtConstProcessDownloadBranch)){
                    JsonResult=_clsMainBL.PushData("DownloadDataCabang_J",_JSONArray.toString());
                }else if(txtProcess.equals(_clsWarning.txtConstProcessDownloadOutlet)){
                    JsonResult=_clsMainBL.PushData("DownloadDataOutlet_J",_JSONArray.toString());
                }else if(txtProcess.equals(_clsWarning.txtConstProcessDownloadBranchKNDASHBOARD)){
                    JsonResult=_clsMainBL.PushData("DownloadDataBranchKNDashboard_J",_JSONArray.toString());
                }else if(txtProcess.equals(_clsWarning.txtConstProcessDownloadPOA)){
                    JsonResult=_clsMainBL.PushData("DownloadDataPOA_J",_JSONArray.toString());
                }else if(txtProcess.equals(_clsWarning.txtConstProcessDownloadOutstanding)){
                    JsonResult=_clsMainBL.PushData("DownloadDataVisitPlanOutstanding_J",_JSONArray.toString());
                }else if(txtProcess.equals(_clsWarning.txtConstProcessDownloadALL)){
                    JsonResult=_clsMainBL.PushData("DownloadDataAll_J",_JSONArray.toString());
                }else if(txtProcess.equals(_clsWarning.txtConstProcessDownloadLOB)){
                    JsonResult=_clsMainBL.PushData("DownloadDataLOB_J",_JSONArray.toString());
                }else if(txtProcess.equals(_clsWarning.txtConstProcessDownloadCategory)){
                    JsonResult=_clsMainBL.PushData("DownloadDataVisitPlanCategory_J",_JSONArray.toString());
                }else if(txtProcess.equals(_clsWarning.txtConstProcessDownloadNoRealisasi)){
                    JsonResult=_clsMainBL.PushData("DownloadDataNORealisai_J",_JSONArray.toString());
                }

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return JsonResult;
        }
        private ProgressDialog Dialog = new ProgressDialog(getContext());
        @Override
        protected void onCancelled() {
            Dialog.dismiss();
            _clsMainBL.showToast(getContext(), new clsWarning().txtConstCancelRequest);
        }

        @Override
        protected void onPostExecute(JSONObject JsonRes) {
            if(_clsMainBL.ValidJSON(JsonRes)){

                if(txtProcess.equals(_clsWarning.txtConstProcessDownloadBranch)){
                    new Delete().from(clsMobile_MBranch.class).execute();
                    SaveDataBranch(JsonRes);
                    LoadDataBranch();
                }else if(txtProcess.equals(_clsWarning.txtConstProcessDownloadOutlet)){
                    new Delete().from(clsMobile_MPartnerProfile.class).execute();
                    SaveDataOutletV2(JsonRes);
                    LoadDataOutletV2();
                }else if(txtProcess.equals(_clsWarning.txtConstProcessDownloadOutstanding)){
                    new Delete().from(clsMobile_trVisitPlan_Detail.class).execute();
                    new Delete().from(clsMobile_trVisitPlan_Header.class).execute();
                    SaveDataDataOutstanding(JsonRes);
                    LoadDataOutstanding();
                }else if(txtProcess.equals(_clsWarning.txtConstProcessDownloadLOB)){
                    new Delete().from(clsMobile_mLOB.class).execute();
                    SaveDataLOB(JsonRes);
                    LoadDataLOB();
                }else if(txtProcess.equals(_clsWarning.txtConstProcessDownloadCategory)){
                    new Delete().from(clsMobile_mVisitPlanCategory.class).execute();
                    SaveDataCategory(JsonRes);
                    LoadDataCategory();
                }else if(txtProcess.equals(_clsWarning.txtConstProcessDownloadPOA)){
                    SaveDataPOA(JsonRes);
                    LoadDataPOA();
                }else if(txtProcess.equals(_clsWarning.txtConstProcessDownloadNoRealisasi)){
                    SaveDataNoRealisasi(JsonRes);
                    LoadDataNoRealisasi();
                }else if(txtProcess.equals(_clsWarning.txtConstProcessDownloadBranchKNDASHBOARD)){
                    SaveDataDataBranchKNDASHBOARD(JsonRes);
                    LoadDataBranchKNDASHBoard();
                }else if(txtProcess.equals(_clsWarning.txtConstProcessDownloadALL)){
                    new Delete().from(clsMobile_MBranch.class).execute();
                    new Delete().from(clsMobile_MPartnerProfile.class).execute();
                    new Delete().from(clsMobile_mLOB.class).execute();
                    new Delete().from(clsMobile_mVisitPlanCategory.class).execute();
                    SaveDataDataBranchKNDASHBOARD(JsonRes);
                    LoadDataBranchKNDASHBoard();
                    SaveDataPOA(JsonRes);
                    LoadDataPOA();
                    SaveDataDataOutstanding(JsonRes);
                    SaveDataOutletV2(JsonRes);
                    SaveDataBranch(JsonRes);
                    SaveDataCategory(JsonRes);
                    SaveDataNoRealisasi(JsonRes);
                    SaveDataLOB(JsonRes);
                    LoadDataBranch();
                    LoadDataLOB();
                    LoadDataCategory();
                    LoadDataOutstanding();
                    LoadDataOutletV2();
                    LoadDataNoRealisasi();
                }

                Intent myIntent = new Intent(getContext(), MainMenu.class);
                getActivity().finish();
                startActivity(myIntent);
                _clsMainBL.showToast(getContext(),new clsWarning().txtConstSuccessDownloadData);
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    _clsMainBL.showToast(getContext(),new clsWarning().txtConstErrorConnection);
                }

            }
            Dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            // Make ProgressBar invisible
            // pg.setVisibility(View.VISIBLE);
            Dialog.setMessage(new clsWarning().txtConstGettingAsyncData);
            Dialog.setCancelable(false);
            Dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    intProcesscancel = 1;
                }
            });
            Dialog.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            Dialog.dismiss();
        }
    }


}
