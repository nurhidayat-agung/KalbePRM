package callplan.prm.kalbe.kalbecallplanmobile;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Select;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import callplan.prm.kalbe.callplanlibrary.common.clsMobile_mVersionApp;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_mVisitPlanCategory;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_trUserLogin;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_trVisitPlan_Detail;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_trVisitPlan_Header;
import callplan.prm.kalbe.callplanlibrary.common.clsWarning;
import callplan.prm.kalbe.kalbecallplanmobile.bl.Mobile_mConfigBL;
import callplan.prm.kalbe.kalbecallplanmobile.bl.Mobile_trUserLoginBL;
import callplan.prm.kalbe.kalbecallplanmobile.bl.clsMainBL;


/**
 * A simple {@link Fragment} subclass.
 */
public class PushDataFragment extends Fragment {

    public PushDataFragment() {
        // Required empty public constructor
    }
    private Button buttonPushData;
    private TableLayout tablePushData;
    private  View v;
    int intProcesscancel;
    clsMainBL _clsMainBL;
    clsWarning _clsWarning;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_push_data, container, false);
        tablePushData=(TableLayout)v.findViewById(R.id.tablePushData);
        buttonPushData=(Button)v.findViewById(R.id.buttonPushData);
        _clsWarning=new clsWarning();
        _clsMainBL=new clsMainBL();
        ListData();
        buttonPushData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncCall _AsyncCall=new AsyncCall();
                _AsyncCall.execute();
            }
        });
        return v;
    }

    private void ListData(){
        tablePushData.removeAllViews();
        clsMobile_trVisitPlan_Detail _clsMobile_trVisitPlan_Detail=new clsMobile_trVisitPlan_Detail();
        List<clsMobile_trVisitPlan_Detail> ListOfclsMobile_trVisitPlan_Detail=new Select().from(clsMobile_trVisitPlan_Detail.class).where(_clsMobile_trVisitPlan_Detail.txtConstintSubmit+"=2").execute();
        final TableRow row = new TableRow(getContext());
        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1f);
        params.setMargins(1, 1, 1, 1);
        TableRow tr = new TableRow(getContext());
        String[] colTextHeader = {"No.", "Plan Date", "Category", "Desc"};
        for (String text : colTextHeader) {
            TextView tv = new TextView(getContext());

            tv.setTextSize(14);
            tv.setPadding(10, 10, 10, 10);
            tv.setText(text);
            tv.setGravity(Gravity.CENTER);
            tv.setBackgroundColor(Color.parseColor("#4CAF50"));
            tv.setTextColor(Color.WHITE);
            tv.setLayoutParams(params);

            tr.addView(tv);
        }
        tablePushData.addView(tr,0);

        if(ListOfclsMobile_trVisitPlan_Detail.size()>0){
            int index = 1;
            for (clsMobile_trVisitPlan_Detail _DtDetail:ListOfclsMobile_trVisitPlan_Detail) {
                tr = new TableRow(getContext());
                TextView tv_index = new TextView(getContext());
                tv_index.setTextSize(12);
                tv_index.setPadding(10, 10, 10, 10);
                tv_index.setBackgroundColor(Color.parseColor("#f0f0f0"));
                tv_index.setTextColor(Color.BLACK);
                tv_index.setGravity(Gravity.CENTER);
                tv_index.setText(String.valueOf(index + "."));
                tv_index.setLayoutParams(params);

                tr.addView(tv_index);

                TextView outlet_code = new TextView(getContext());
                outlet_code.setTextSize(12);
                outlet_code.setPadding(10, 10, 10, 10);
                outlet_code.setBackgroundColor(Color.parseColor("#f0f0f0"));
                outlet_code.setTextColor(Color.BLACK);
                outlet_code.setGravity(Gravity.CENTER);
                String txtPlanDate=_DtDetail.dtPlanDate;
                if(txtPlanDate==null){
                    txtPlanDate="Unplan";
                }
                outlet_code.setText(txtPlanDate);
                outlet_code.setLayoutParams(params);

                tr.addView(outlet_code);

                TextView outlet_name = new TextView(getContext());
                outlet_name.setTextSize(12);
                outlet_name.setPadding(10, 10, 10, 10);
                outlet_name.setBackgroundColor(Color.parseColor("#f0f0f0"));
                outlet_name.setTextColor(Color.BLACK);
                outlet_name.setGravity(Gravity.CENTER);
                clsMobile_mVisitPlanCategory _dtclsMobile_mVisitPlanCategory=new clsMobile_mVisitPlanCategory();
                List<clsMobile_mVisitPlanCategory> ListOfDataclsMobile_mVisitPlanCategory=new Select().from(clsMobile_mVisitPlanCategory.class).where(_dtclsMobile_mVisitPlanCategory.txtConstintCategoryID+"=?",_DtDetail.intCategoryID).execute();
                if(ListOfDataclsMobile_mVisitPlanCategory.size()>0){
                    outlet_name.setText(ListOfDataclsMobile_mVisitPlanCategory.get(0).txtCategoryName);
                }else {
                    outlet_name.setText("-");
                }
                outlet_name.setLayoutParams(params);

                tr.addView(outlet_name);

                TextView date = new TextView(getContext());
                date.setTextSize(12);
                date.setPadding(10, 10, 10, 10);
                date.setBackgroundColor(Color.parseColor("#f0f0f0"));
                date.setTextColor(Color.BLACK);
                date.setGravity(Gravity.CENTER);
                date.setText(_DtDetail.txtDescription);
                date.setLayoutParams(params);

                tr.addView(date);

                tablePushData.addView(tr,index++);
            }
        }
    }
    private class AsyncCall extends AsyncTask<JSONObject, Void, JSONObject> {
        @Override
        protected JSONObject doInBackground(JSONObject... params) {

            JSONObject JsonResult=null;
            try {
                clsMobile_trVisitPlan_Detail _clsMobile_trVisitPlan_Detail=new clsMobile_trVisitPlan_Detail();
                List<clsMobile_trVisitPlan_Detail> ListOfclsMobile_trVisitPlan_Detail=new Select().from(clsMobile_trVisitPlan_Detail.class).where(_clsMobile_trVisitPlan_Detail.txtConstintSubmit+"=2").execute();
                if(ListOfclsMobile_trVisitPlan_Detail.size()>0){
                    JsonResult=_clsMainBL.PushDataCallPlan();
                    do{
                        JsonResult=_clsMainBL.PushDataCallPlan();
                    }while (JsonResult!=null && ListOfclsMobile_trVisitPlan_Detail.size()>0);
                }
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
            Toast toast = Toast.makeText(getContext(), new clsWarning().txtConstCancelRequest, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP, 25, 400);
            toast.show();
        }

        @Override
        protected void onPostExecute(JSONObject JsonRes) {
            if(_clsMainBL.ValidJSON(JsonRes)){
                String TxtWarn="";
                String txtvalidJson= null;
                try {
                    txtvalidJson = (String.valueOf(JsonRes.get("validJson")));
                    JSONObject validJson=new JSONObject(txtvalidJson);
                    TxtWarn=(String.valueOf(validJson.get("TxtWarn")));
                    String intresult=(String.valueOf(validJson.get("TxtResult")));
                    if(intresult.equals("1")){
                        String txtGetDataJson = String.valueOf(validJson.get("TxtData"));
                        JSONObject JsonDataTxtData=new JSONObject(txtGetDataJson);
                        String txtMobile_trVisitPlan_Detail = String.valueOf(JsonDataTxtData.get("datMobile_trVisitPlan_Detail"));
                        JSONArray JSONArraytxtMobile_trVisitPlan_Detail =new JSONArray(txtMobile_trVisitPlan_Detail);
                        for (int i = 0; i < JSONArraytxtMobile_trVisitPlan_Detail.length(); i++) {
                            JSONObject JSONdatMobile_trVisitPlan_Detail = JSONArraytxtMobile_trVisitPlan_Detail.getJSONObject(i);
                            clsMobile_trVisitPlan_Detail _dt=new clsMobile_trVisitPlan_Detail();
                            String txtGuidDetail=String.valueOf(JSONdatMobile_trVisitPlan_Detail.get("TxtGUI_Detail"));
                            List<clsMobile_trVisitPlan_Detail> ListOfData=new Select().from(clsMobile_trVisitPlan_Detail.class).where(_dt.txtConsttxtGUI_Detail+"=?",txtGuidDetail).execute();
                            if(ListOfData.size()>0){
                                _dt=ListOfData.get(0);
                                _dt.intSubmit="3";
                                _dt.save();
                                clsMobile_trUserLogin dtUserLogin = new Mobile_trUserLoginBL().CheckUserActive();
                                if (dtUserLogin.txtUserID == null) {
                                    new clsMainBL().DeleteData();
                                    Intent myIntent = new Intent(getContext(), ActivityFlash.class);
                                    new Mobile_mConfigBL().InsertDefaultMconfig();
                                    myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(myIntent);
                                }
                            }
                            _clsMainBL.showToast(getContext(),new clsWarning().txtConstSuccessDownloadData);
                        }
                    }else {
                        _clsMainBL.showToastWarning(getContext(),TxtWarn);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                /*
                Toast toast = Toast.makeText(getContext(), new clsWarning().txtConstSuccessDownloadData,
                        Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP, 25, 400);
                toast.show();
                */
            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    clsMobile_trVisitPlan_Detail _clsMobile_trVisitPlan_Detail=new clsMobile_trVisitPlan_Detail();
                    List<clsMobile_trVisitPlan_Detail> ListOfclsMobile_trVisitPlan_Detail=new Select().from(clsMobile_trVisitPlan_Detail.class).where(_clsMobile_trVisitPlan_Detail.txtConstintSubmit+"=2").execute();
                    if(ListOfclsMobile_trVisitPlan_Detail.size()==0){
                        _clsMainBL.showToastWarning(getContext(),new clsWarning().txtConstMessNoData);
                    }else{
                        _clsMainBL.showToastWarning(getContext(),new clsWarning().txtConstErrorConnection);
                    }

                    /*
                    Toast toast = Toast.makeText(getContext(), new clsWarning().txtConstErrorConnection,
                            Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP, 25, 400);
                    toast.show();
                    */
                }

            }
            ListData();
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
