package callplan.prm.kalbe.kalbecallplanmobile;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


import callplan.prm.kalbe.kalbecallplanmobile.bl.Mobile_trUserLoginBL;
import callplan.prm.kalbe.kalbecallplanmobile.bl.clsMainBL;
import callplan.prm.kalbe.kalbecallplanmobile.model.clsMobile_MBranch;
import callplan.prm.kalbe.kalbecallplanmobile.model.clsMobile_mLOB;
import callplan.prm.kalbe.kalbecallplanmobile.model.clsMobile_mVersionApp;
import callplan.prm.kalbe.kalbecallplanmobile.model.clsMobile_mVisitPlanCategory;
import callplan.prm.kalbe.kalbecallplanmobile.model.clsMobile_trUserLogin;
import callplan.prm.kalbe.kalbecallplanmobile.model.clsMobile_trVisitPlan_Detail;
import callplan.prm.kalbe.kalbecallplanmobile.model.clsMobile_trVisitPlan_Header;
import callplan.prm.kalbe.kalbecallplanmobile.model.clsSpinner;
import callplan.prm.kalbe.kalbecallplanmobile.model.clsWarning;
import callplan.prm.kalbe.kalbecallplanmobile.app.AppDatabase;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * create an instance of this fragment.
 */
public class AddVisitPlanFragment extends Fragment {
    View v;
    TextView tvDate;
    TextView tvNoKode;
    Spinner spnBranch;
    Spinner spnCategory;
    Spinner spnLOB;
    EditText etBobot;
    EditText etPreNC;
    EditText etOutlet;
    TextView tvLong;
    TextView tvLatitude;
    EditText etDesc;
    TextView tvStatus;
    Button btnSubmit;
    Button btnBack;
    String txtIdBranch;
    String txtIdBranchSearch;
    String txtIdCategory;
    String txtIdLOB;
    TableRow trOutlet;
    TableRow trOutletCoordinate;
    clsMainBL _clsMainBL;
    clsWarning _clsWarning;
    List<clsMobile_MBranch> ListOfclsMobile_MBranch = null;
    AppDatabase appDatabase;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_add_visit_plan, container, false);
        tvDate = (TextView) v.findViewById(R.id.tvDate);
        tvNoKode = (TextView) v.findViewById(R.id.tvNoKode);
        spnCategory = (Spinner) v.findViewById(R.id.spnCategory);
        spnBranch = (Spinner) v.findViewById(R.id.spnBranch);
        spnLOB = (Spinner) v.findViewById(R.id.spnLOB);
        etBobot = (EditText) v.findViewById(R.id.etBobot);
        etPreNC = (EditText) v.findViewById(R.id.etPreNC);
        etOutlet = (EditText) v.findViewById(R.id.etOutlet);
        tvLong = (TextView) v.findViewById(R.id.tvLong);
        tvLatitude = (TextView) v.findViewById(R.id.tvLatitude);
        etDesc = (EditText) v.findViewById(R.id.etDesc);
        tvStatus = (TextView) v.findViewById(R.id.tvStatus);
        trOutlet = (TableRow) v.findViewById(R.id.trOutlet);
        trOutletCoordinate = (TableRow) v.findViewById(R.id.trOutletCoordinate);
        btnBack = (Button) v.findViewById(R.id.btnBack);
        btnSubmit = (Button) v.findViewById(R.id.btnSubmit);
        appDatabase = AppDatabase.getDatabase(getActivity());

        // List<clsMobile_mLOB> ListOfclsMobile_mLOB = new Select().from(clsMobile_mLOB.class).execute();
        List<clsMobile_mLOB> ListOfclsMobile_mLOB = appDatabase.daoMobileMLOB().getAll();
        // ListOfclsMobile_MBranch = new Select().from(clsMobile_MBranch.class).execute();
        ListOfclsMobile_MBranch = appDatabase.daoMobileMBranch().getAll();
        List<clsMobile_mVisitPlanCategory> ListOfclsMobile_mVisitPlanCategory
                = appDatabase.daoMobileMVisitPlanCategory().getAll();
        if (ListOfclsMobile_mLOB.size() > 0) {
            ArrayList<clsSpinner> listSpinner = new ArrayList<>();
            for (clsMobile_mLOB dtclsMobile_mLOB : ListOfclsMobile_mLOB) {
                clsSpinner _clsSpinner = new clsSpinner(dtclsMobile_mLOB.intLOBID.toString(), dtclsMobile_mLOB.txtLOBName);
                listSpinner.add(_clsSpinner);
            }
            ArrayAdapter<clsSpinner> adapter = new ArrayAdapter<clsSpinner>(getContext(), android.R.layout.simple_spinner_item, listSpinner);
            spnLOB.setAdapter(adapter);
        }
        if (ListOfclsMobile_MBranch.size() > 0) {
            List<clsSpinner> listSpinner = new ArrayList<clsSpinner>();
            for (clsMobile_MBranch dtclsMobile_MBranch : ListOfclsMobile_MBranch) {
                clsSpinner _clsSpinner = new clsSpinner(dtclsMobile_MBranch.IntCabangID.toString(), dtclsMobile_MBranch.TxtNamaCabang);
                listSpinner.add(_clsSpinner);
            }
            ArrayAdapter<clsSpinner> adapter = new ArrayAdapter<clsSpinner>(getContext(), android.R.layout.simple_spinner_item, listSpinner);
            spnBranch.setAdapter(adapter);
        }
        if (ListOfclsMobile_mVisitPlanCategory.size() > 0) {
            List<clsSpinner> listSpinner = new ArrayList<clsSpinner>();
            for (clsMobile_mVisitPlanCategory dtclsMobile_mVisitPlanCategory : ListOfclsMobile_mVisitPlanCategory) {
                clsSpinner _clsSpinner = new clsSpinner(dtclsMobile_mVisitPlanCategory.intCategoryID.toString(), dtclsMobile_mVisitPlanCategory.txtCategoryName);
                listSpinner.add(_clsSpinner);
            }
            ArrayAdapter<clsSpinner> adapter = new ArrayAdapter<clsSpinner>(getContext(), android.R.layout.simple_spinner_item, listSpinner);
            spnCategory.setAdapter(adapter);
        }
        etOutlet.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER || keyCode == KeyEvent.KEYCODE_DPAD_CENTER)) {
                    LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                    final View promptView = layoutInflater.inflate(R.layout.layout_popup_outlet, null);
                    final Spinner spnBranch = (Spinner) promptView.findViewById(R.id.spnBranch);
                    final Spinner spnSearchBy = (Spinner) promptView.findViewById(R.id.spnSearchBy);
                    final EditText etSearch = (EditText) promptView.findViewById(R.id.etSearch);
                    final Button btnSearch = (Button) promptView.findViewById(R.id.btnSearch);
                    ArrayList<String> arrySpinner = new ArrayList<>();
                    arrySpinner.add("Name");
                    arrySpinner.add("ID");
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, arrySpinner);
                    spnSearchBy.setAdapter(adapter);
                    if (ListOfclsMobile_MBranch.size() > 0) {
                        List<clsSpinner> listSpinner = new ArrayList<clsSpinner>();
                        for (clsMobile_MBranch dtclsMobile_MBranch : ListOfclsMobile_MBranch) {
                            clsSpinner _clsSpinner = new clsSpinner(dtclsMobile_MBranch.IntCabangID.toString(), dtclsMobile_MBranch.TxtNamaCabang);
                            listSpinner.add(_clsSpinner);
                        }
                        ArrayAdapter<clsSpinner> adapterBranch = new ArrayAdapter<clsSpinner>(getContext(), android.R.layout.simple_spinner_item, listSpinner);
                        spnBranch.setAdapter(adapterBranch);
                    }
                    spnBranch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            clsSpinner _clsSpinner = (clsSpinner) parent.getSelectedItem();
                            txtIdBranchSearch = _clsSpinner.getID();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    btnSearch.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                    alertDialogBuilder.setView(promptView);
                    alertDialogBuilder.setCancelable(false)
                            .setPositiveButton("Cancel",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });
                    final AlertDialog alertD = alertDialogBuilder.create();
                    alertD.show();
                    return true;
                }
                return false;
            }
        });

        spnBranch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                clsSpinner _clsSpinner = (clsSpinner) parent.getSelectedItem();
                txtIdBranch = _clsSpinner.getID();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spnLOB.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                clsSpinner _clsSpinner = (clsSpinner) parent.getSelectedItem();
                txtIdLOB = _clsSpinner.getID();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spnCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                clsSpinner _clsSpinner = (clsSpinner) parent.getSelectedItem();
                txtIdCategory = _clsSpinner.getID();
                if (_clsSpinner.getNama().toUpperCase().equals("OUTLET")) {
                    trOutlet.setVisibility(View.VISIBLE);
                    trOutletCoordinate.setVisibility(View.VISIBLE);
                } else {
                    trOutlet.setVisibility(View.INVISIBLE);
                    trOutletCoordinate.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListCallPlanFragment frag = new ListCallPlanFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.frame, frag).commit();
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clsMobile_trVisitPlan_Header _clsMobile_trVisitPlan_Header = new clsMobile_trVisitPlan_Header();
                clsMobile_trVisitPlan_Detail _clsMobile_trVisitPlan_Detail = new clsMobile_trVisitPlan_Detail();

                // List<clsMobile_trVisitPlan_Header> ListOfclsMobile_trVisitPlan_Header = new Select().from(clsMobile_trVisitPlan_Detail.class).where(_clsMobile_trVisitPlan_Header.txtConstIntVisitPlan_HeaderID + "=1").execute();
                List<clsMobile_trVisitPlan_Header> ListOfclsMobile_trVisitPlan_Header = appDatabase.daoMobileTrVisitPlanHeader().getbyVisitPlan_HeaderID(1);

                if (ListOfclsMobile_trVisitPlan_Header.size() > 0) {
                    _clsMobile_trVisitPlan_Header = ListOfclsMobile_trVisitPlan_Header.get(0);
                }
                _clsMobile_trVisitPlan_Detail.intVisitPlan_DetailID = Long.valueOf("0");
                _clsMobile_trVisitPlan_Detail.intVisitPlan_HeaderID = String.valueOf(_clsMobile_trVisitPlan_Header.IntVisitPlan_HeaderID);
                _clsMobile_trVisitPlan_Detail.dtActualDate = _clsMainBL.GetTimeNow(getActivity());
                _clsMobile_trVisitPlan_Detail.bitStatus = "1";
                _clsMobile_trVisitPlan_Detail.intStatus = "2";
                _clsMobile_trVisitPlan_Detail.intJumlahPreNC = etPreNC.getText().toString();
                _clsMobile_trVisitPlan_Detail.intBobot = etBobot.getText().toString();
                _clsMobile_trVisitPlan_Detail.dtPlanDate = null;
                _clsMobile_trVisitPlan_Detail.intCategoryID = txtIdCategory;
                _clsMobile_trVisitPlan_Detail.intLOBID = txtIdLOB;
                _clsMobile_trVisitPlan_Detail.txtLongitude_Knis = tvLong.getText().toString();
                _clsMobile_trVisitPlan_Detail.txtLatitude_Knis = tvLatitude.getText().toString();
                ListCallPlanFragment frag = new ListCallPlanFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.frame, frag).commit();

                _clsMainBL.showToast(getContext(), "Success Save Data");
            }
        });
        return v;
    }

    private class AsyncCallLogin extends AsyncTask<JSONObject, Void, JSONObject> {
        @SuppressWarnings("WrongThread")
        @Override
        protected JSONObject doInBackground(JSONObject... params) {
            JSONArray _JSONArray = null;
            JSONObject JsonOutlet = null;
            JSONObject JsonResult = null;
            clsMobile_mVersionApp _clsMobile_mVersionApp = new clsMobile_mVersionApp();

            // List<clsMobile_mVersionApp> listOfclsMobile_mVersionApp = new Select().from(clsMobile_mVersionApp.class).where(_clsMobile_mVersionApp.txtConstidVersion + "=?", 1).execute();
            List<clsMobile_mVersionApp> listOfclsMobile_mVersionApp
                    = appDatabase.daoMobileMVersionApp().getByIDVersion("1");

            clsMobile_trUserLogin dtclsMobile_trUserLogin = new Mobile_trUserLoginBL().CheckUserLogin(getActivity());
            try {
                JsonOutlet = new JSONObject();
                _JSONArray = new JSONArray();
                JsonOutlet.put("TxtGUI_trUserLogin", dtclsMobile_trUserLogin.txtGUI.toString());
                JsonOutlet.put("TxtUserID", dtclsMobile_trUserLogin.txtUserID.toString());
                JsonOutlet.put("TxtGUI_mVersionApp", listOfclsMobile_mVersionApp.get(0).txtGUI);
                JsonOutlet.put("IntCabangID", dtclsMobile_trUserLogin.IntCabangID);
                _JSONArray.put(JsonOutlet);

                // JsonResult = _clsMainBL.PushData("JsonResult=_clsMainBL.PushData(\"DownloadDataCabang_J\",_JSONArray.toString());", _JSONArray.toString());
                JsonResult = _clsMainBL.PushData("JsonResult=_clsMainBL.PushData(\"DownloadDataCabang_J\",_JSONArray.toString());", _JSONArray.toString(),getActivity());

                //EditText txt = (EditText) findViewById(R.id.txtLoginEmail);
                //roledata = new mUserRoleBL().getRoleAndOutlet(txtEmail1, pInfo.versionName);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                _clsMainBL.showToast(getContext(), e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                _clsMainBL.showToast(getContext(), e.getMessage());
            }

            return JsonResult;
        }

        private ProgressDialog Dialog = new ProgressDialog(getContext());

        @Override
        protected void onCancelled() {
            Dialog.dismiss();
            _clsMainBL.showToast(getContext(), _clsWarning.txtConstCancelRequest);
        }

        @Override
        protected void onPostExecute(JSONObject JsonRes) {
            if (_clsMainBL.ValidJSON(JsonRes)) {
                try {
                    String txtvalidJson = (String.valueOf(JsonRes.get("validJson")));
                    JSONObject validJson = new JSONObject(txtvalidJson);
                    String intresult = (String.valueOf(validJson.get("TxtResult")));
                    if (intresult.equals("1")) {
                        String txtGetDataJson = String.valueOf(validJson.get("TxtData"));
                        JSONObject JsonData = new JSONObject(txtGetDataJson);

                    } else {
                        String txtvalidJsonError = (String.valueOf(JsonRes.get("validJson")));
                        JSONObject validJsonError = new JSONObject(txtvalidJsonError);
                        String txtWarn = (String) validJsonError.get("TxtWarn");
                        _clsMainBL.showToast(getContext(), txtWarn);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    _clsMainBL.showToast(getContext(), e.getMessage());
                }


            }
            Dialog.dismiss();
        }

        int intProcesscancel = 0;

        @Override
        protected void onPreExecute() {
            //Make ProgressBar invisible
            //pg.setVisibility(View.VISIBLE);
            Dialog.setMessage(_clsWarning.txtConstGettingUserRole);
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
