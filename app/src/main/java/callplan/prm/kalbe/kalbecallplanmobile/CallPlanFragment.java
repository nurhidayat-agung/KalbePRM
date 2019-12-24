package callplan.prm.kalbe.kalbecallplanmobile;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.net.ParseException;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.FileProvider;
import android.text.InputFilter;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import callplan.prm.kalbe.callplanlibrary.ENUM.enum_VisitPlanCategory;
import callplan.prm.kalbe.callplanlibrary.ENUM.enum_mconfig;
import callplan.prm.kalbe.callplanlibrary.common.clsHardCode;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_MBranch;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_MPartnerProfile;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_MPartnerProfileAlias;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_UserJabatan;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_ValidationNo;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_mBinaryFile;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_mConfig;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_mLOB;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_mVersionApp;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_mVisitPlanCategory;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_mVisitPlanCategoryDetail;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_trPOA;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_trUserLogin;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_trVisitPlan_Detail;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_trVisitPlan_Detail_Item;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_trVisitPlan_Header;
import callplan.prm.kalbe.callplanlibrary.common.clsSpinner;
import callplan.prm.kalbe.callplanlibrary.common.clsWarning;
import callplan.prm.kalbe.kalbecallplanmobile.bl.Mobile_mConfigBL;
import callplan.prm.kalbe.kalbecallplanmobile.bl.Mobile_trUserLoginBL;
import callplan.prm.kalbe.kalbecallplanmobile.bl.clsMainBL;
import kalbenutritionals.bridgeapi.BridgeAPI;
import kalbenutritionals.bridgeapi.common.clsDataLogin;

import static android.content.Context.LOCATION_SERVICE;


/**
 * A simple {@link Fragment} subclass.
 */
public class CallPlanFragment extends Fragment implements ConnectionCallbacks, OnConnectionFailedListener, android.location.LocationListener {
    public CallPlanFragment() {
        // Required empty public constructor
    }

    View v;
    clsMainBL _clsMainBL = null;
    clsWarning _clsWarning = null;
    private String Id;
    private String txtPathImage1 = null;
    private String txtPathImage2 = null;
    private String txtBinaryImage1 = null;
    private String txtBinaryImage2 = null;

    @SuppressLint("ValidFragment")
    public CallPlanFragment(String txtGUI_detail) {
        Id = txtGUI_detail;
    }
    private String mCurrentPhotoPath;
    private TextInputLayout input_layout_poa_search, input_layout_outlet_search, textDescription, textPreNC, textBobot;
    private LinearLayout lnOutletLocation;
    private RelativeLayout rlDatePlan;
    //private Spinner spnNoRealisasi;
    private EditText etRealisasi;
    private CheckBox cbFullDay;
    private Spinner spnBranch;
    private Spinner spnCategory;
    private EditText etPOA;
    private Spinner spnLOB;
    private EditText etOutletName;
    private TextView tvLongKNIS;
    private TextView tvLatKNIS;
    private EditText etDesc;
    private EditText etDatePlan;
    private EditText etBobot;
    private EditText etPreNC;
    private Integer intdistance;
    private RelativeLayout trLocation;
    //private TableRow trLong;
    //private TableRow trLati;
    //private TableRow trAcc;
    //private TableRow trDistance;
    private TextView tvTypeOutlet;
    private Spinner spnOutletAlias;
    private List<String> ListTitle;
    private List<String> ListDesc;
    private TextView lblLong;
    private TextView lblLang;
    private TextView lblAcc;
    private TextView txtHDLati;
    private TextView txtHDLong;
    private TextView txtHDActualDate;
    private TextView txtHDId;
    private TextView tvViewDate;
    private TextView tvDistance;
    private EditText tvAddInformation;
    private Button btnRefreshMaps, btnPopupMap;
    private Button btnCheckIn;
    private Button btnEDetailing;
    private View viewEmpty;
    private LinearLayout llimgview;
    private static final int CAMERA_CAPTURE_IMAGE1_REQUEST_CODE = 100;
    private static final int CAMERA_CAPTURE_IMAGE2_REQUEST_CODE = 130;
    private GoogleMap mMap;
    String txtIdNoRealisasi;
    private Location mLastLocation;
    private RelativeLayout RLPreNC;
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 1000;
    private static final String TAG = CallPlanFragment.class.getSimpleName();
    private GoogleApiClient mGoogleApiClient;
    private boolean mRequestingLocationUpdates = false;
    private LocationRequest mLocationRequest;
    private EditText etSearch;
    private ListView lvSearch;
    private static int UPDATE_INTERVAL = 3000; // 10 sec
    private static int FATEST_INTERVAL = 5000; // 5 sec
    private static int DISPLACEMENT = 10; // 10 meters
    private String Long;
    private String Lat;
    private String Acc;
    private ImageView imgPrevNoImg1;
    private TableLayout tblData;
    private ImageView imgPrevNoImg2;
    private String IMAGE_DIRECTORY_NAME = "Image Sales";
    final String finalFile = null;
    private GoogleApiClient client;
    BitmapFactory.Options options;
    private Uri uriImage;
    clsMobile_trVisitPlan_Detail dtclsMobile_trVisitPlan_Detail = null;
    List<clsMobile_trVisitPlan_Detail_Item> ListOfclsMobile_trVisitPlan_Detail_Item = null;
    private static clsMobile_MPartnerProfile dtclsMobile_MPartnerProfile = null;
    List<clsMobile_MPartnerProfile> ListOfclsMobile_MPartnerProfileSearch = null;
    List<clsMobile_MPartnerProfileAlias> ListOfclsMobile_MPartnerProfileAliasSearch = null;
    clsMobile_trPOA dtclsMobile_trPOA = null;
    String txtIdBranch;
    String txtIdBranchSearch;
    String txtSearchBy;
    String txtIdCategory;
    String txtIdLOB;
    String txtIdOutletAlias;
    List<clsMobile_MBranch> ListOfclsMobile_MBranch = null;
    List<clsMobile_mLOB> ListOfclsMobile_mLOB = null;
    List<clsMobile_mVisitPlanCategory> ListOfclsMobile_mVisitPlanCategory = null;
    List<clsMobile_trPOA> ListOfclsMobile_trPOA = null;
    List<clsSpinner> listSpinnerBranch = null;
    List<clsSpinner> listSpinnermVisitPlanCategory = null;
    List<clsSpinner> listSpinnerLOB = null;
    List<clsSpinner> listSpinnerOutletAlias = null;
    List<clsSpinner> listSpinnerNoRealiasasi=null;
    clsMobile_MPartnerProfile _clsMobile_MPartnerProfile;
    android.support.v7.app.AlertDialog alertD;
    List<clsMobile_ValidationNo> ListOfclsMobile_ValidationNoe=null;
    ListView listData;
    View viewListEmpty;

    public Location getLocation() {
        try {
            LocationManager locationManager = (LocationManager) getActivity()
                    .getSystemService(LOCATION_SERVICE);

            // getting GPS status
            boolean isGPSEnabled = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);

            // getting network status
            boolean isNetworkEnabled = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            boolean canGetLocation=false;
            Location location = null;

            if (!isGPSEnabled && !isNetworkEnabled) {
                // no network provider is enabled
                new clsMainBL().showToastWarning(getContext(), "Aktifkan Mode GPS!!" );
            } else {
                canGetLocation = true;
                if (isNetworkEnabled) {
                    if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    }
                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            1000,
                            0, this);
                    Log.d("Network", "Network Enabled");
                    if (locationManager != null) {
                        mLastLocation = locationManager
                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (location != null) {
                            double latitude = location.getLatitude();
                            double longitude = location.getLongitude();
                        }
                    }
                }
                // if GPS Enabled get lat/long using GPS Services
                if (isGPSEnabled) {
                    if (mLastLocation == null) {

                        locationManager.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER,
                                1000,
                                0,  this);
                        Log.d("GPS", "GPS Enabled");
                        if (locationManager != null) {
                            location = locationManager
                                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if (location != null) {
                                double latitude = location.getLatitude();
                                double longitude = location.getLongitude();
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return mLastLocation;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_call_plan_new, container, false);
        _clsWarning = new clsWarning();
        _clsMainBL = new clsMainBL();
        txtHDLati=(TextView)  v.findViewById(R.id.txtHDLati);
        txtHDLong=(TextView)  v.findViewById(R.id.txtHDLong);
        txtHDActualDate=(TextView)  v.findViewById(R.id.txtHDActualDate);
        llimgview=(LinearLayout)  v.findViewById(R.id.llimgview);
        btnRefreshMaps = (Button) v.findViewById(R.id.btnRefreshMaps);
        btnEDetailing= (Button) v.findViewById(R.id.btnEDetailing);
        cbFullDay = (CheckBox) v.findViewById(R.id.cbFullDay);
        btnPopupMap = (Button) v.findViewById(R.id.viewMap);
        btnCheckIn = (Button) v.findViewById(R.id.buttonCheckIn);
        imgPrevNoImg1 = (ImageView) v.findViewById(R.id.imageViewCamera1);
        imgPrevNoImg2 = (ImageView) v.findViewById(R.id.imageViewCamera2);
        tvTypeOutlet = (TextView) v.findViewById(R.id.tvTypeOutlet);;
        spnOutletAlias= (Spinner) v.findViewById(R.id.spnOutletAlias);;
        //spnNoRealisasi = (Spinner) v.findViewById(R.id.spnNoRealisasi);
        etRealisasi= (EditText) v.findViewById(R.id.etRealisasi);
        lblLong = (TextView) v.findViewById(R.id.tvLong);
        lblLang = (TextView) v.findViewById(R.id.tvLat);
        tvViewDate = (TextView) v.findViewById(R.id.tvViewDate);
        lblAcc = (TextView) v.findViewById(R.id.tvAcc);
        spnBranch = (Spinner) v.findViewById(R.id.spnBranch);
        spnCategory = (Spinner) v.findViewById(R.id.spnCategory);
        spnLOB = (Spinner) v.findViewById(R.id.spnLOB);
        etOutletName = (EditText) v.findViewById(R.id.etOutletName);
        etDesc = (EditText) v.findViewById(R.id.etDesc);
        etDatePlan = (EditText) v.findViewById(R.id.etDatePlan);
        etBobot = (EditText) v.findViewById(R.id.etBobot);
        etPreNC = (EditText) v.findViewById(R.id.etPreNC);
        etPOA = (EditText) v.findViewById(R.id.etPOA);
        trLocation= (RelativeLayout) v.findViewById(R.id.trLocation);
        RLPreNC= (RelativeLayout) v.findViewById(R.id.RLPreNC);
        //trLong = (TableRow) v.findViewById(R.id.trLong);
        //trLati = (TableRow) v.findViewById(R.id.trLati);
        //trAcc = (TableRow) v.findViewById(R.id.trAcc);
        //trDistance = (TableRow) v.findViewById(R.id.trDistance);
        tvDistance = (TextView) v.findViewById(R.id.tvDistance);
        tvLongKNIS = (TextView) v.findViewById(R.id.tvLongKNIS);
        tvLatKNIS = (TextView) v.findViewById(R.id.tvLatKNIS);
        txtHDId = (TextView) v.findViewById(R.id.txtHDId);
        lblLong.setText("");
        lblLang.setText("");
        lblAcc.setText("");
        input_layout_poa_search = (TextInputLayout) v.findViewById(R.id.input_layout_poa_search);
        input_layout_outlet_search = (TextInputLayout) v.findViewById(R.id.input_layout_outlet_search);
        textDescription = (TextInputLayout) v.findViewById(R.id.input_layout_desc);
        textPreNC = (TextInputLayout) v.findViewById(R.id.input_layout_prenc);
        textBobot = (TextInputLayout) v.findViewById(R.id.input_layout_bobot);
        lnOutletLocation = (LinearLayout) v.findViewById(R.id.outletLocation);
        rlDatePlan = (RelativeLayout) v.findViewById(R.id.rlDatePlan);

        txtHDId.setText(Id);
        tvAddInformation = (EditText) v.findViewById(R.id.tvAddInformation);
        ListOfclsMobile_mLOB = new Select().from(clsMobile_mLOB.class).execute();
        ListOfclsMobile_MBranch = new Select().from(clsMobile_MBranch.class).execute();
        ListOfclsMobile_mVisitPlanCategory = new Select().from(clsMobile_mVisitPlanCategory.class).execute();
        ListOfclsMobile_trPOA = new Select().from(clsMobile_trPOA.class).execute();
        etDatePlan.setText(_clsMainBL.GetDateNowFromLogin());
        tvViewDate.setText(_clsMainBL.GetDateNowFromLogin());
        etDatePlan.setEnabled(false);
        etBobot.setEnabled(false);
        //spnNoRealisasi.setAdapter(null);
        spnLOB.setAdapter(null);
        spnBranch.setAdapter(null);
        spnCategory.setAdapter(null);
        etDesc.setFilters(new InputFilter[] {new InputFilter.AllCaps()});
        clsMobile_ValidationNo _clsMobile_ValidationNo=new clsMobile_ValidationNo();
        ListOfclsMobile_ValidationNoe=new Select().from(clsMobile_ValidationNo.class).where(_clsMobile_ValidationNo.txtConstBitUse+"=?",0).execute();
        /*
        if(ListOfclsMobile_ValidationNoe.size()>0){
            listSpinnerNoRealiasasi=new ArrayList<>();
            for (clsMobile_ValidationNo dtclsMobile_ValidationNo:ListOfclsMobile_ValidationNoe) {
                clsSpinner _clsSpinner=new clsSpinner(dtclsMobile_ValidationNo.txtValidationNo.toString(),dtclsMobile_ValidationNo.txtValidationNo);
                listSpinnerNoRealiasasi.add(_clsSpinner);
            }
            ArrayAdapter<clsSpinner> adapter = new ArrayAdapter<clsSpinner> (getContext(),android.R.layout.simple_spinner_item,listSpinnerNoRealiasasi);
            //spnNoRealisasi.setAdapter(adapter);
        }
        */
        if (ListOfclsMobile_mLOB.size() > 0) {
            listSpinnerLOB = new ArrayList<>();
            for (clsMobile_mLOB dtclsMobile_mLOB : ListOfclsMobile_mLOB) {
                clsSpinner _clsSpinner = new clsSpinner(dtclsMobile_mLOB.intLOBID.toString(), dtclsMobile_mLOB.txtLOBName);
                listSpinnerLOB.add(_clsSpinner);
            }
            ArrayAdapter<clsSpinner> adapter = new ArrayAdapter<clsSpinner>(getContext(), android.R.layout.simple_spinner_item, listSpinnerLOB);
            spnLOB.setAdapter(adapter);
        }
        //getActivity().grantUriPermission(getCallingPackage(), fileUri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
        if (ListOfclsMobile_MBranch.size() > 0) {
            listSpinnerBranch = new ArrayList<clsSpinner>();
            for (clsMobile_MBranch dtclsMobile_MBranch : ListOfclsMobile_MBranch) {
                clsSpinner _clsSpinner = new clsSpinner(dtclsMobile_MBranch.IntCabangID.toString(), dtclsMobile_MBranch.TxtNamaCabang);
                listSpinnerBranch.add(_clsSpinner);
            }
            ArrayAdapter<clsSpinner> adapter = new ArrayAdapter<clsSpinner>(getContext(), android.R.layout.simple_spinner_item, listSpinnerBranch);
            spnBranch.setAdapter(adapter);
        }
        if (ListOfclsMobile_mVisitPlanCategory.size() > 0) {
            listSpinnermVisitPlanCategory = new ArrayList<clsSpinner>();
            for (clsMobile_mVisitPlanCategory dtclsMobile_mVisitPlanCategory : ListOfclsMobile_mVisitPlanCategory) {
                clsSpinner _clsSpinner = new clsSpinner(dtclsMobile_mVisitPlanCategory.intCategoryID.toString(), dtclsMobile_mVisitPlanCategory.txtCategoryName);
                listSpinnermVisitPlanCategory.add(_clsSpinner);
            }
            ArrayAdapter<clsSpinner> adapter = new ArrayAdapter<clsSpinner>(getContext(), android.R.layout.simple_spinner_item, listSpinnermVisitPlanCategory);
            spnCategory.setAdapter(adapter);

        }
        cbFullDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etBobot.setText(GetBobot());
            }
        });

//        trdtPlan.setVisibility(View.GONE);
        rlDatePlan.setVisibility(View.GONE);
        spnOutletAlias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //_clsMainBL.showToast(getContext(),spnOutletAlias.getSelectedItem().toString()) ;
                if(ListOfclsMobile_MPartnerProfileAliasSearch!=null){
                    for (clsMobile_MPartnerProfileAlias dtclsMobile_MPartnerProfileAlias:ListOfclsMobile_MPartnerProfileAliasSearch) {
                        if(spnOutletAlias.getSelectedItem().toString().equals(dtclsMobile_MPartnerProfileAlias.txtPartnerCRM+" - "+dtclsMobile_MPartnerProfileAlias.txtNamaPartner)){
                            String txtLatitudeKNIS=null;
                            String txtLongitudeKNIS=null;
                            txtIdOutletAlias=dtclsMobile_MPartnerProfileAlias.IntPartnerID.toString();
                            txtLatitudeKNIS=dtclsMobile_MPartnerProfileAlias.txtLatitude;
                            txtLongitudeKNIS=dtclsMobile_MPartnerProfileAlias.txtLongitude;
                            if(txtLatitudeKNIS==null||txtLatitudeKNIS.equals("null")){
                                txtLatitudeKNIS="No Location";
                                txtHDLati.setText("");
                            }else{
                                txtHDLati.setText(txtLatitudeKNIS);
                            }
                            if(txtLongitudeKNIS==null||txtLongitudeKNIS.equals("null")){
                                txtLongitudeKNIS="No Location";
                                txtHDLong.setText("");
                            }else{
                                txtHDLong.setText(txtLongitudeKNIS);
                            }
                            tvLatKNIS.setText("Lati : " + txtLatitudeKNIS);
                            tvLongKNIS.setText("Long : " + txtLongitudeKNIS);
                            /*
                            if (checkPlayServices()) {
                                buildGoogleApiClient();
                                displayLocation();
                            }
                            */
                            displayLocation();
                            break;
                        }
                    }
                }
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
        spnCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(Id.equals("")){
                    clsSpinner _clsSpinner = (clsSpinner) parent.getSelectedItem();
                    txtIdCategory = _clsSpinner.getID();
                    clsMobile_mVisitPlanCategory _clsMobile_mVisitPlanCategory = new clsMobile_mVisitPlanCategory();
                    List<clsMobile_mVisitPlanCategory> ListCategoryPlan = new Select().from(clsMobile_mVisitPlanCategory.class).where(_clsMobile_mVisitPlanCategory.txtConstintCategoryID + "=?", txtIdCategory).execute();
                    int Bobot = Integer.valueOf(ListCategoryPlan.get(0).intNilaiBobot + "");
                    dtclsMobile_MPartnerProfile = null;
                    etBobot.setText(Bobot + "");

                    dtclsMobile_MPartnerProfile=null;
                    dtclsMobile_trPOA=null;
                    if (ListCategoryPlan.size() > 0) {
                        if (ListCategoryPlan.get(0).bitFullDay == 1) {
                            if(Id.equals("")){
                                cbFullDay.setEnabled(true);
                                cbFullDay.setClickable(true);
                            }else{
                                cbFullDay.setEnabled(false);
                                cbFullDay.setClickable(false);
                            }
                        } else {
                            cbFullDay.setEnabled(false);
                            cbFullDay.setClickable(false);
                        }
                    }
                    input_layout_outlet_search.setVisibility(View.VISIBLE);
                    input_layout_poa_search.setVisibility(View.VISIBLE);
                    lnOutletLocation.setVisibility(View.VISIBLE);
                    RLPreNC.setVisibility(View.GONE);
                    ShowForLocationAndPhoto(true);

                    etBobot.setText(GetBobot());

                    if (txtIdCategory.equals(enum_VisitPlanCategory.OUTLET.getValue()+"")) {
                        tvDistance.setText("0");
                        input_layout_outlet_search.setVisibility(View.VISIBLE);
                        input_layout_poa_search.setVisibility(View.GONE);
                        lnOutletLocation.setVisibility(View.VISIBLE);

                    } else if (txtIdCategory.equals(enum_VisitPlanCategory.ACTIVITY.getValue()+"")) {
                        tvDistance.setText("0");
                        RLPreNC.setVisibility(View.VISIBLE);
                        input_layout_poa_search.setVisibility(View.VISIBLE);
                        input_layout_outlet_search.setVisibility(View.GONE);
                        lnOutletLocation.setVisibility(View.GONE);
                    }else if(txtIdCategory.equals(enum_VisitPlanCategory.CUTI.getValue()+"")||txtIdCategory.equals(enum_VisitPlanCategory.PERJALANAN_DINAS.getValue()+"")){
                        ShowForLocationAndPhoto(false);
                        input_layout_outlet_search.setVisibility(View.GONE);
                        input_layout_poa_search.setVisibility(View.GONE);
                        lnOutletLocation.setVisibility(View.GONE);
                        RLPreNC.setVisibility(View.GONE);
                    } else {
                        tvDistance.setText("0");
                        input_layout_poa_search.setVisibility(View.GONE);
                        input_layout_outlet_search.setVisibility(View.GONE);
                        lnOutletLocation.setVisibility(View.GONE);

                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        etPOA.setOnTouchListener(new DrawableClickListener.RightDrawableClickListener(etPOA) {
            public boolean onDrawableClick() {
                searchPOA();
                return true;
            }
        });
        etRealisasi.setOnTouchListener(new DrawableClickListener.RightDrawableClickListener(etRealisasi) {
            public boolean onDrawableClick() {
                searchNoRealisai();
                return true;
            }
        });
        etPOA.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER || keyCode == KeyEvent.KEYCODE_DPAD_CENTER)) {
                    searchPOA();
                    return true;
                }
                return false;
            }
        });

        etOutletName.setOnTouchListener(new DrawableClickListener.RightDrawableClickListener(etPOA) {
            public boolean onDrawableClick() {
                if(etOutletName.getText().length()>=3){
                    searchOutlet();
                }else{
                    _clsMainBL.showToastWarning(getContext(), "Isi Minimal 3 karakter");
                    etOutletName.requestFocus();
                }
                return true;
            }
        });
        etOutletName.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER || keyCode == KeyEvent.KEYCODE_DPAD_CENTER)) {
                    if(etOutletName.getText().length()>=3){
                        searchOutlet();
                    }else{
                        _clsMainBL.showToastWarning(getContext(), "Isi Minimal 3 karakter");
                        etOutletName.requestFocus();
                    }
                    return true;
                }
                return false;
            }
        });



        if (!isDeviceSupportCamera()) {
            _clsMainBL.showToastWarning(getContext(), "HP Tidak Support Camera!!");
        }
        /*
        if (checkPlayServices()) {
            buildGoogleApiClient();
            displayLocation();
        }
        */
        //btnEDetailing.setVisibility(View.GONE);
        displayLocation();
        btnRefreshMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayLocation();
                _clsMainBL.showToast(getContext(), "Location Updated");
            }
        });
        btnEDetailing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BridgeAPI bridgeAPI = new BridgeAPI();
                clsMobile_mConfig _clsMobile_mConfig=new clsMobile_mConfig();
                List<clsMobile_mConfig> ListOfclsMobile_mConfig = new Select().from(clsMobile_mConfig.class).where("idConfig=?",9).execute();
                if(ListOfclsMobile_mConfig.size()>0){
                    //Boolean status = bridgeAPI.checkInstalledApplication(MainMenu.this, "kalbenutritionals.testjar");
                    Boolean status = bridgeAPI.checkInstalledApplication(getActivity(), "com.edetailing");
                    if(status){
                        List<clsMobile_trUserLogin> dataLogin = new Select().from(clsMobile_trUserLogin.class).execute();
                        if(dataLogin.size()>0){
                            clsDataLogin dtUser = new clsMainBL().getDataLogin();
                            String txtIDDetailing="0";
                            if (Id.equals("") == false) {
                                txtIDDetailing= dtclsMobile_trVisitPlan_Detail.intVisitPlan_DetailID.toString();
                            }
                            bridgeAPI.intentWithPackageName(getActivity(), "com.edetailing","com.edetailing.MainActivity", dtUser, txtIDDetailing);
                        }else{
                            new clsMainBL().showToastWarning(getActivity(),"user login null, please contact admin PRM!!");
                        }
                    }else{
                        new clsMainBL().showToastWarning(getActivity(),"please installed Application E Detailing!!");
                    }
                }else{
                    new clsMainBL().showToastWarning(getActivity(),"Name App E Detailing is null. please contact Admin PRM!!");
                }
            }
        });
        imgPrevNoImg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                captureImage1();
            }
        });

        imgPrevNoImg2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                captureImage2();
            }
        });
        btnPopupMap.setVisibility(View.INVISIBLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            btnPopupMap.setVisibility(View.VISIBLE);
        }
        btnCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getContext())
                        .setMessage("Do you really want to Save?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                String strWarning = "";
                                textDescription.setErrorEnabled(false);
                                textPreNC.setErrorEnabled(false);
                                textBobot.setErrorEnabled(false);
                                clsMobile_ValidationNo _clsMobile_ValidationNo=new clsMobile_ValidationNo();

                                clsMobile_trVisitPlan_Header _clsMobile_trVisitPlan_Header = new clsMobile_trVisitPlan_Header();
                                List<clsMobile_trVisitPlan_Header> ListOfHeaderActive = new Select().from(clsMobile_trVisitPlan_Header.class).where(_clsMobile_trVisitPlan_Header.txtConstintActivePeriode + "=1").execute();

                                List<clsMobile_ValidationNo> ListOfCheckData=null;
                                String strErrorValidationNo="";
                                ListOfCheckData=new Select().from(clsMobile_ValidationNo.class).where(_clsMobile_ValidationNo.txtConsttxtValidationNo+"='"+etRealisasi.getText().toString()+"'").execute();
                                if(ListOfCheckData.size()==0){
                                    strErrorValidationNo="Validation_No_Invalid";
                                }else{
                                    ListOfCheckData=new Select().from(clsMobile_ValidationNo.class).where(_clsMobile_ValidationNo.txtConsttxtValidationNo+"='"+etRealisasi.getText().toString()+"' AND "+_clsMobile_ValidationNo.txtConstBitUse+"=0").execute();
                                    if(ListOfCheckData.size()==0){
                                        strErrorValidationNo="Validation_No_Not_Available";
                                    }
                                }
                                if(strErrorValidationNo.equals("")==false){
                                    if(strErrorValidationNo.equals("Validation_No_Invalid")){
                                        strWarning="Validasi Tidak Valid";
                                        etRealisasi.setError(strWarning);
                                    }else if(strErrorValidationNo.equals("Validation_No_Not_Available")){
                                        strWarning="Validasi Sudah Digunakan";
                                        etRealisasi.setError(strWarning);
                                    }
                                    etRealisasi.requestFocus();
                                }else if (txtPathImage1 == null && txtPathImage2 == null ){
                                    if((txtIdCategory.equals(enum_VisitPlanCategory.CUTI.getValue()+"") || txtIdCategory.equals(enum_VisitPlanCategory.PERJALANAN_DINAS.getValue()+"") )) {
                                        if(strWarning.equals("")){
                                            strWarning="";
                                        }
                                    }else{
                                        strWarning = "photo minimal 1";
                                    }
                                }else if (txtIdCategory.equals(enum_VisitPlanCategory.OUTLET.getValue()+"") && tvDistance.getText().toString().equals("0")){
                                    strWarning = "Please Set Location Outlet";
                                } else if (etDesc.getText().toString().equals("")) {
                                    strWarning = "Description Tidak Boleh Kosong";
                                    textDescription.setErrorEnabled(true);
                                    textDescription.setError("Description Tidak Boleh Kosong");
                                    textDescription.requestFocus();
                                } else if (txtIdCategory.equals(enum_VisitPlanCategory.ACTIVITY.getValue()+"") && (etPreNC.getText().toString().equals("")||etPreNC.getText().toString().equals("0"))) {
                                    strWarning = "PreNc Tidak Boleh Kosong";
                                    textPreNC.setErrorEnabled(true);
                                    textPreNC.setError("PreNc Tidak Boleh Kosong");
                                    textPreNC.requestFocus();
                                } else if (etBobot.getText().toString().equals("")) {
                                    strWarning = "POA Tidak Boleh Kosong";
                                    textBobot.setErrorEnabled(true);
                                    textBobot.setError("POA Tidak Boleh Kosong");
                                    textBobot.requestFocus();
                                } else if (txtIdCategory.equals(enum_VisitPlanCategory.OUTLET.getValue()+"") && etOutletName.getText().toString().equals("")) {
                                    strWarning = "Outlet Tidak Boleh Kosong";
                                } else if (txtIdCategory.equals(enum_VisitPlanCategory.ACTIVITY.getValue()+"") && etPOA.getText().toString().equals("")) {
                                    strWarning = "POA Tidak Boleh Kosong";
                                } else if((txtIdCategory.equals(enum_VisitPlanCategory.CUTI.getValue()+"") == false || txtIdCategory.equals(enum_VisitPlanCategory.PERJALANAN_DINAS.getValue()+"") == false) &&
                                        (lblLong.toString().equals("") || lblLong.toString().equals("0") || lblLang.toString().equals("") || lblLang.toString().equals("0"))){
                                    strWarning="Koordinate Tidak Ketemu";
                                }else if(etRealisasi.getText().toString().equals("")){
                                    strWarning="No Validasi Harus Diisi";
                                }else  if(txtIdCategory.equals(enum_VisitPlanCategory.OUTLET.getValue()+"")){
                                    String txtDistance=tvDistance.getText().toString().replace(" meters","");
                                    int intDistiance= Integer.valueOf(txtDistance);
                                    String txtVISITPLAN_GEOTAGING_RADIUS= new Mobile_mConfigBL().getValue(enum_mconfig.VISITPLAN_GEOTAGING_RADIUS.getValue());
                                    if(txtVISITPLAN_GEOTAGING_RADIUS.equals("0")==false){
                                        int intVISITPLAN_GEOTAGING_RADIUS=Integer.valueOf(txtVISITPLAN_GEOTAGING_RADIUS);
                                        if(intVISITPLAN_GEOTAGING_RADIUS<intDistiance ){
                                            strWarning="Jarak Anda Ke Outlet Terlalu Jauh, Radius :"+txtVISITPLAN_GEOTAGING_RADIUS+" meters";
                                        }
                                    }
                                }
                                if (strWarning.equals("")) {
                                    List<clsMobile_trVisitPlan_Header> ListOfHeaderVisitPlan = new Select().from(clsMobile_trVisitPlan_Header.class).execute();
                                    if(Id.equals("")){
                                        if(ListOfHeaderActive.size() == 0){
                                            strWarning="Tidak Bisa Simpan Karena Belum membuat Plan minggu berjalan!!";
                                        }
                                    }
                                }
                                if(etPreNC.getText().toString().equals("")){
                                    etPreNC.setText("0");
                                }
                                if (strWarning.equals("")) {
                                    if(ListOfCheckData.size()>0){
                                        _clsMobile_ValidationNo=ListOfCheckData.get(0);
                                    }

                                    if (Id.equals("") == false) {
                    /*
                    clsMobile_trVisitPlan_Header _clsMobile_trVisitPlan_Header=new clsMobile_trVisitPlan_Header();
                    List<clsMobile_trVisitPlan_Header> ListOfclsMobile_trVisitPlan_Header=new Select().from(clsMobile_trVisitPlan_Header.class).where(_clsMobile_trVisitPlan_Header.txtConstIntVisitPlan_HeaderID+"=?",dtclsMobile_trVisitPlan_Detail.intVisitPlan_HeaderID).execute();
                    if(ListOfclsMobile_trVisitPlan_Header.size()>0){
                        List<clsMobile_trVisitPlan_Detail> ListOfCheckDataDetail=new Select().from(clsMobile_trVisitPlan_Detail.class).where(dtclsMobile_trVisitPlan_Detail.txtConstintVisitPlan_HeaderID+"=?",dtclsMobile_trVisitPlan_Detail.intVisitPlan_HeaderID).execute();
                        List<clsMobile_trVisitPlan_Detail> ListOfCheckDataDetailSubmit=new Select().from(clsMobile_trVisitPlan_Detail.class).where(dtclsMobile_trVisitPlan_Detail.txtConstintVisitPlan_HeaderID+"=?",dtclsMobile_trVisitPlan_Detail.intVisitPlan_HeaderID).where(dtclsMobile_trVisitPlan_Detail.txtConstintSubmit+"=2").execute();
                        if(ListOfCheckDataDetail.size()==ListOfCheckDataDetailSubmit.size()){
                            _clsMobile_trVisitPlan_Header=ListOfclsMobile_trVisitPlan_Header.get(0);
                            _clsMobile_trVisitPlan_Heademer.intSubmit="2";
                            _clsMobile_trVisitPlan_Header.save();
                        }
                    }
                    */
                                        if (dtclsMobile_MPartnerProfile != null) {
                                            dtclsMobile_trVisitPlan_Detail.txtLongitude_Knis = dtclsMobile_MPartnerProfile.txtLongitude;
                                            dtclsMobile_trVisitPlan_Detail.txtLatitude_Knis = dtclsMobile_MPartnerProfile.txtLatitude;
                                            dtclsMobile_trVisitPlan_Detail.txtAccuracy_Knis = "0";
                                            dtclsMobile_trVisitPlan_Detail.intPartnerID=dtclsMobile_MPartnerProfile.IntPartnerID.toString();
                                            dtclsMobile_trVisitPlan_Detail.intPartnerIDCheckIn=txtIdOutletAlias;
                                        }
                                        dtclsMobile_trVisitPlan_Detail.intSubmit = "2";
                                        dtclsMobile_trVisitPlan_Detail.txtValidationNo=etRealisasi.getText().toString();
                                        if(txtHDActualDate.getText().toString().equals("")){
                                            dtclsMobile_trVisitPlan_Detail.dtActualDate = _clsMainBL.GetDateNowFromLogin();
                                        }else{
                                            dtclsMobile_trVisitPlan_Detail.dtActualDate = txtHDActualDate.getText().toString();
                                        }

                                        dtclsMobile_trVisitPlan_Detail.dtValidated = _clsMainBL.GetDateNowFromLogin();
                                        dtclsMobile_trVisitPlan_Detail.intJumlahPreNC=etPreNC.getText().toString();
                                        dtclsMobile_trVisitPlan_Detail.save();
                                        clsMobile_trVisitPlan_Detail_Item _clsMobile_trVisitPlan_Detail_Item = new clsMobile_trVisitPlan_Detail_Item();
                                        List<clsMobile_trVisitPlan_Detail_Item> ListDataclsMobile_trVisitPlan_Detail_Item = null;
                                        _clsMobile_trVisitPlan_Detail_Item.txtGUI_Detail_Item = _clsMainBL.GenerateGuid();
                                        if (txtPathImage1 != null) {
                                            ListDataclsMobile_trVisitPlan_Detail_Item = new Select().from(clsMobile_trVisitPlan_Detail_Item.class)
                                                    .where(_clsMobile_trVisitPlan_Detail_Item.txtGUI_Detail + "=?", dtclsMobile_trVisitPlan_Detail.txtGUI_Detail)
                                                    .where(_clsMobile_trVisitPlan_Detail_Item.txtConsintNo + "=1").execute();
                                            if (ListDataclsMobile_trVisitPlan_Detail_Item.size() > 0) {
                                                _clsMobile_trVisitPlan_Detail_Item = ListDataclsMobile_trVisitPlan_Detail_Item.get(0);
                                            } else {
                                                _clsMobile_trVisitPlan_Detail_Item.txtGUI_Detail_Item = _clsMainBL.GenerateGuid();
                                            }
                                            _clsMobile_trVisitPlan_Detail_Item.intNo = "1";
                                            _clsMobile_trVisitPlan_Detail_Item.txtFileName = txtPathImage1;

                                        } else if (txtPathImage2 != null) {
                                            ListDataclsMobile_trVisitPlan_Detail_Item = new Select().from(clsMobile_trVisitPlan_Detail_Item.class)
                                                    .where(_clsMobile_trVisitPlan_Detail_Item.txtConsttxtGUI_Detail + "=?", dtclsMobile_trVisitPlan_Detail.txtGUI_Detail)
                                                    .where(_clsMobile_trVisitPlan_Detail_Item.txtConsintNo + "=2").execute();
                                            if (ListDataclsMobile_trVisitPlan_Detail_Item.size() > 0) {
                                                _clsMobile_trVisitPlan_Detail_Item = ListDataclsMobile_trVisitPlan_Detail_Item.get(0);
                                            } else {
                                                _clsMobile_trVisitPlan_Detail_Item.txtGUI_Detail_Item = _clsMainBL.GenerateGuid();
                                            }
                                            _clsMobile_trVisitPlan_Detail_Item.txtFileName = txtPathImage2;
                                            _clsMobile_trVisitPlan_Detail_Item.intNo = "2";
                            /*
                            _clsMobile_trVisitPlan_Detail_Item.intVisitPlan_DetailID = dtclsMobile_trVisitPlan_Detail.intVisitPlan_DetailID;
                            _clsMobile_trVisitPlan_Detail_Item.txtLongitude = lblLong.getText().toString();
                            _clsMobile_trVisitPlan_Detail_Item.txtLatitude = lblLang.getText().toString();
                            _clsMobile_trVisitPlan_Detail_Item.txtAccuracy = lblAcc.getText().toString();
                            _clsMobile_trVisitPlan_Detail_Item.dtCaptureGeotagging = _clsMainBL.GetDateNow();
                            _clsMobile_trVisitPlan_Detail_Item.txtGUI_Detail = dtclsMobile_trVisitPlan_Detail.txtGUI_Detail;
                            _clsMobile_trVisitPlan_Detail_Item.save();

                            clsMobile_mBinaryFile _clsMobile_mBinaryFile = new clsMobile_mBinaryFile();
                            List<clsMobile_mBinaryFile> ListOfclsMobile_mBinaryFile = new Select().from(clsMobile_mBinaryFile.class).where(_clsMobile_mBinaryFile.txtConsttxtGUI_IDTable + "=?", _clsMobile_trVisitPlan_Detail_Item.txtGUI_Detail_Item).where(_clsMobile_mBinaryFile.txtConsttxtFileName + "=?", _clsMobile_trVisitPlan_Detail_Item.txtFileName).execute();
                            if (ListOfclsMobile_mBinaryFile.size() > 0) {
                                _clsMobile_mBinaryFile = ListOfclsMobile_mBinaryFile.get(0);
                            } else {
                                _clsMobile_mBinaryFile.txtGUI_ID = _clsMainBL.GenerateGuid();
                            }
                            _clsMobile_mBinaryFile.txtGUI_IDTable = _clsMobile_trVisitPlan_Detail_Item.txtGUI_Detail_Item;
                            _clsMobile_mBinaryFile.txtFileName = _clsMobile_trVisitPlan_Detail_Item.txtFileName;
                            _clsMobile_mBinaryFile.txtBinary = txtBinaryImage2;
                            _clsMobile_mBinaryFile.save();
                            */
                                        }
                                        _clsMobile_trVisitPlan_Detail_Item.intVisitPlan_DetailID = dtclsMobile_trVisitPlan_Detail.intVisitPlan_DetailID;
                                        _clsMobile_trVisitPlan_Detail_Item.txtLongitude = lblLong.getText().toString();
                                        _clsMobile_trVisitPlan_Detail_Item.txtLatitude = lblLang.getText().toString();
                                        _clsMobile_trVisitPlan_Detail_Item.txtAccuracy = lblAcc.getText().toString();
                                        _clsMobile_trVisitPlan_Detail_Item.dtCaptureGeotagging = _clsMainBL.GetDateNowFromLogin();
                                        _clsMobile_trVisitPlan_Detail_Item.txtGUI_Detail = dtclsMobile_trVisitPlan_Detail.txtGUI_Detail;
                                        _clsMobile_trVisitPlan_Detail_Item.save();
                                        if (txtPathImage1 != null||txtPathImage2 != null) {
                                            clsMobile_mBinaryFile _clsMobile_mBinaryFile = new clsMobile_mBinaryFile();
                                            List<clsMobile_mBinaryFile> ListOfclsMobile_mBinaryFile = new Select().from(clsMobile_mBinaryFile.class).where(_clsMobile_mBinaryFile.txtConsttxtGUI_IDTable + "=?", _clsMobile_trVisitPlan_Detail_Item.txtGUI_Detail_Item).where(_clsMobile_mBinaryFile.txtConsttxtFileName + "=?", _clsMobile_trVisitPlan_Detail_Item.txtFileName).execute();
                                            if (ListOfclsMobile_mBinaryFile.size() > 0) {
                                                _clsMobile_mBinaryFile = ListOfclsMobile_mBinaryFile.get(0);
                                            } else {
                                                _clsMobile_mBinaryFile.txtGUI_ID = _clsMainBL.GenerateGuid();
                                            }
                                            _clsMobile_mBinaryFile.txtGUI_IDTable = _clsMobile_trVisitPlan_Detail_Item.txtGUI_Detail_Item;
                                            _clsMobile_mBinaryFile.txtFileName = _clsMobile_trVisitPlan_Detail_Item.txtFileName;
                                            _clsMobile_mBinaryFile.txtBinary = txtBinaryImage1;
                                            _clsMobile_mBinaryFile.save();
                                        }

                                        _clsMobile_ValidationNo.bitUse="1";
                                        _clsMobile_ValidationNo.save();
                                        ReportingFragment frag = new ReportingFragment();
                                        FragmentManager fragmentManager = getFragmentManager();
                                        fragmentManager.beginTransaction().replace(R.id.frame
                                                , frag).commit();
                                    } else {
                                        _clsMobile_trVisitPlan_Header = ListOfHeaderActive.get(0);
                                        dtclsMobile_trVisitPlan_Detail = new clsMobile_trVisitPlan_Detail();
                                        dtclsMobile_trVisitPlan_Detail.intVisitPlan_HeaderID = String.valueOf(_clsMobile_trVisitPlan_Header.IntVisitPlan_HeaderID);
                                        dtclsMobile_trVisitPlan_Detail.intVisitPlan_DetailID = java.lang.Long.valueOf("0");
                                        dtclsMobile_trVisitPlan_Detail.txtValidationNo=etRealisasi.getText().toString();
                                        dtclsMobile_trVisitPlan_Detail.intCategoryID = txtIdCategory;
                                        if (dtclsMobile_MPartnerProfile != null) {
                                            dtclsMobile_trVisitPlan_Detail.txtLongitude_Knis = dtclsMobile_MPartnerProfile.txtLongitude;
                                            dtclsMobile_trVisitPlan_Detail.txtLatitude_Knis = dtclsMobile_MPartnerProfile.txtLatitude;
                                            dtclsMobile_trVisitPlan_Detail.txtAccuracy_Knis = "0";
                                            dtclsMobile_trVisitPlan_Detail.intPartnerID=dtclsMobile_MPartnerProfile.IntPartnerID.toString();
                                            dtclsMobile_trVisitPlan_Detail.intPartnerIDCheckIn=txtIdOutletAlias;
                                        }
                                        if (txtIdCategory.equals(enum_VisitPlanCategory.ACTIVITY.getValue()+"")) {
                                            dtclsMobile_trVisitPlan_Detail.txtNoKode = dtclsMobile_trPOA.intProgramID + "";
                                        } else if (txtIdCategory.equals(enum_VisitPlanCategory.OUTLET.getValue()+"")) {
                                            if (dtclsMobile_MPartnerProfile != null) {
                                                dtclsMobile_trVisitPlan_Detail.txtNoKode = dtclsMobile_MPartnerProfile.txtPartnerCRM + "";
                                                dtclsMobile_trVisitPlan_Detail.txtAccuracy_Knis = "0";
                                                dtclsMobile_trVisitPlan_Detail.txtLatitude_Knis = dtclsMobile_MPartnerProfile.txtLatitude;
                                                dtclsMobile_trVisitPlan_Detail.txtLongitude_Knis = dtclsMobile_MPartnerProfile.txtLongitude;
                                                dtclsMobile_trVisitPlan_Detail.intPartnerID=dtclsMobile_MPartnerProfile.IntPartnerID.toString();
                                                dtclsMobile_trVisitPlan_Detail.intPartnerIDCheckIn=txtIdOutletAlias;
                                            }
                                        }
                                        dtclsMobile_trVisitPlan_Detail.dtPlanDate = null;
                                        if(dtclsMobile_trVisitPlan_Detail.dtActualDate==null){
                                            dtclsMobile_trVisitPlan_Detail.dtActualDate = _clsMainBL.GetDateNowFromLogin();
                                        }
                                        if(dtclsMobile_trVisitPlan_Detail.dtActualDate.equals("")){
                                            dtclsMobile_trVisitPlan_Detail.dtActualDate = _clsMainBL.GetDateNowFromLogin();
                                        }
                                        dtclsMobile_trVisitPlan_Detail.dtValidated = _clsMainBL.GetDateNowFromLogin();
                                        dtclsMobile_trVisitPlan_Detail.txtDescription = etDesc.getText().toString();
                                        dtclsMobile_trVisitPlan_Detail.intJumlahPreNC = etPreNC.getText().toString();
                                        dtclsMobile_trVisitPlan_Detail.intBobot = etBobot.getText().toString();
                                        dtclsMobile_trVisitPlan_Detail.intSubmit = "2";
                                        dtclsMobile_trVisitPlan_Detail.intLOBID = txtIdLOB;
                                        dtclsMobile_trVisitPlan_Detail.txtCategoryName = spnCategory.getSelectedItem().toString();
                                        dtclsMobile_trVisitPlan_Detail.txtGUI_Detail = _clsMainBL.GenerateGuid();
                                        dtclsMobile_trVisitPlan_Detail.txtLOBName = spnLOB.getSelectedItem().toString();
                                        dtclsMobile_trVisitPlan_Detail.save();
                                        clsMobile_trVisitPlan_Detail_Item _clsMobile_trVisitPlan_Detail_Item = new clsMobile_trVisitPlan_Detail_Item();
                                        List<clsMobile_trVisitPlan_Detail_Item> ListDataclsMobile_trVisitPlan_Detail_Item = null;
                                        _clsMobile_trVisitPlan_Detail_Item.txtGUI_Detail_Item = _clsMainBL.GenerateGuid();
                                        if (txtPathImage1 != null) {
                                            ListDataclsMobile_trVisitPlan_Detail_Item = new Select().from(clsMobile_trVisitPlan_Detail_Item.class)
                                                    .where(_clsMobile_trVisitPlan_Detail_Item.txtGUI_Detail + "=?", dtclsMobile_trVisitPlan_Detail.txtGUI_Detail)
                                                    .where(_clsMobile_trVisitPlan_Detail_Item.txtConsintNo + "=1").execute();
                                            if (ListDataclsMobile_trVisitPlan_Detail_Item.size() > 0) {
                                                _clsMobile_trVisitPlan_Detail_Item = ListDataclsMobile_trVisitPlan_Detail_Item.get(0);
                                            } else {
                                                _clsMobile_trVisitPlan_Detail_Item.txtGUI_Detail_Item = _clsMainBL.GenerateGuid();
                                            }
                                            _clsMobile_trVisitPlan_Detail_Item.txtFileName = txtPathImage1;
                                            _clsMobile_trVisitPlan_Detail_Item.intNo = "1";

                                        } else if (txtPathImage2 != null) {
                                            ListDataclsMobile_trVisitPlan_Detail_Item = new Select().from(clsMobile_trVisitPlan_Detail_Item.class)
                                                    .where(_clsMobile_trVisitPlan_Detail_Item.txtConsttxtGUI_Detail + "=?", dtclsMobile_trVisitPlan_Detail.txtGUI_Detail)
                                                    .where(_clsMobile_trVisitPlan_Detail_Item.txtConsintNo + "=2").execute();
                                            if (ListDataclsMobile_trVisitPlan_Detail_Item.size() > 0) {
                                                _clsMobile_trVisitPlan_Detail_Item = ListDataclsMobile_trVisitPlan_Detail_Item.get(0);
                                            } else {
                                                _clsMobile_trVisitPlan_Detail_Item.txtGUI_Detail_Item = _clsMainBL.GenerateGuid();
                                            }
                                            _clsMobile_trVisitPlan_Detail_Item.txtFileName = txtPathImage2;
                                            _clsMobile_trVisitPlan_Detail_Item.intNo = "2";
                            /*
                            _clsMobile_trVisitPlan_Detail_Item.intVisitPlan_DetailID = dtclsMobile_trVisitPlan_Detail.intVisitPlan_DetailID;
                            _clsMobile_trVisitPlan_Detail_Item.txtLongitude = lblLong.getText().toString();
                            _clsMobile_trVisitPlan_Detail_Item.txtLatitude = lblLang.getText().toString();
                            _clsMobile_trVisitPlan_Detail_Item.txtAccuracy = lblAcc.getText().toString();
                            _clsMobile_trVisitPlan_Detail_Item.dtCaptureGeotagging = _clsMainBL.GetDateNow();
                            _clsMobile_trVisitPlan_Detail_Item.txtGUI_Detail = dtclsMobile_trVisitPlan_Detail.txtGUI_Detail;
                            _clsMobile_trVisitPlan_Detail_Item.save();

                            clsMobile_mBinaryFile _clsMobile_mBinaryFile = new clsMobile_mBinaryFile();
                            List<clsMobile_mBinaryFile> ListOfclsMobile_mBinaryFile = new Select().from(clsMobile_mBinaryFile.class).where(_clsMobile_mBinaryFile.txtConsttxtGUI_IDTable + "=?", _clsMobile_trVisitPlan_Detail_Item.txtGUI_Detail_Item).where(_clsMobile_mBinaryFile.txtConsttxtFileName + "=?", _clsMobile_trVisitPlan_Detail_Item.txtFileName).execute();
                            if (ListOfclsMobile_mBinaryFile.size() > 0) {
                                _clsMobile_mBinaryFile = ListOfclsMobile_mBinaryFile.get(0);
                            } else {
                                _clsMobile_mBinaryFile.txtGUI_ID = _clsMainBL.GenerateGuid();
                            }
                            _clsMobile_mBinaryFile.txtGUI_IDTable = _clsMobile_trVisitPlan_Detail_Item.txtGUI_Detail_Item;
                            _clsMobile_mBinaryFile.txtFileName = _clsMobile_trVisitPlan_Detail_Item.txtFileName;
                            _clsMobile_mBinaryFile.txtBinary = txtBinaryImage2;
                            _clsMobile_mBinaryFile.save();
                            */
                                        }

                                        _clsMobile_trVisitPlan_Detail_Item.intVisitPlan_DetailID = dtclsMobile_trVisitPlan_Detail.intVisitPlan_DetailID;
                                        _clsMobile_trVisitPlan_Detail_Item.txtLongitude = lblLong.getText().toString();
                                        _clsMobile_trVisitPlan_Detail_Item.txtLatitude = lblLang.getText().toString();
                                        _clsMobile_trVisitPlan_Detail_Item.txtAccuracy = lblAcc.getText().toString();
                                        _clsMobile_trVisitPlan_Detail_Item.dtCaptureGeotagging = _clsMainBL.GetDateNowFromLogin();
                                        _clsMobile_trVisitPlan_Detail_Item.txtGUI_Detail = dtclsMobile_trVisitPlan_Detail.txtGUI_Detail;
                                        _clsMobile_trVisitPlan_Detail_Item.save();

                                        if (txtPathImage1 != null||txtPathImage2 != null) {
                                            clsMobile_mBinaryFile _clsMobile_mBinaryFile = new clsMobile_mBinaryFile();
                                            List<clsMobile_mBinaryFile> ListOfclsMobile_mBinaryFile = new Select().from(clsMobile_mBinaryFile.class).where(_clsMobile_mBinaryFile.txtConsttxtGUI_IDTable + "=?", _clsMobile_trVisitPlan_Detail_Item.txtGUI_Detail_Item).where(_clsMobile_mBinaryFile.txtConsttxtFileName + "=?", _clsMobile_trVisitPlan_Detail_Item.txtFileName).execute();
                                            if (ListOfclsMobile_mBinaryFile.size() > 0) {
                                                _clsMobile_mBinaryFile = ListOfclsMobile_mBinaryFile.get(0);
                                            } else {
                                                _clsMobile_mBinaryFile.txtGUI_ID = _clsMainBL.GenerateGuid();
                                            }
                                            _clsMobile_mBinaryFile.txtGUI_IDTable = _clsMobile_trVisitPlan_Detail_Item.txtGUI_Detail_Item;
                                            _clsMobile_mBinaryFile.txtFileName = _clsMobile_trVisitPlan_Detail_Item.txtFileName;
                                            _clsMobile_mBinaryFile.txtBinary = txtBinaryImage1;
                                            _clsMobile_mBinaryFile.save();
                                        }
                        /*
                        clsMobile_mBinaryFile _clsMobile_mBinaryFile = new clsMobile_mBinaryFile();
                        List<clsMobile_mBinaryFile> ListOfclsMobile_mBinaryFile = new Select().from(clsMobile_mBinaryFile.class).where(_clsMobile_mBinaryFile.txtConsttxtGUI_IDTable + "=?", _clsMobile_trVisitPlan_Detail_Item.txtGUI_Detail_Item).where(_clsMobile_mBinaryFile.txtConsttxtFileName + "=?", _clsMobile_trVisitPlan_Detail_Item.txtFileName).execute();
                        if (ListOfclsMobile_mBinaryFile.size() > 0) {
                            _clsMobile_mBinaryFile = ListOfclsMobile_mBinaryFile.get(0);
                        } else {
                            _clsMobile_mBinaryFile.txtGUI_ID = _clsMainBL.GenerateGuid();
                        }
                        _clsMobile_mBinaryFile.txtGUI_IDTable = _clsMobile_trVisitPlan_Detail_Item.txtGUI_Detail_Item;
                        _clsMobile_mBinaryFile.txtFileName = _clsMobile_trVisitPlan_Detail_Item.txtFileName;
                        _clsMobile_mBinaryFile.txtBinary = txtBinaryImage1;
                        _clsMobile_mBinaryFile.save();
                        */
                                        _clsMobile_ValidationNo.bitUse="1";
                                        _clsMobile_ValidationNo.save();
                                        ReportingFragment frag = new ReportingFragment();
                                        FragmentManager fragmentManager = getFragmentManager();
                                        fragmentManager.beginTransaction().replace(R.id.frame
                                                , frag).commit();
                                    }
                                } else {
                                    _clsMainBL.showToastWarning(getContext(), strWarning);
                                }

                            }})
                        .setNegativeButton(android.R.string.no, null).show();
            }
        });
        /*
        spnNoRealisasi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                clsSpinner _clsSpinner=(clsSpinner)parent.getSelectedItem();
                txtIdNoRealisasi=_clsSpinner.getID();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        */
        btnPopupMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lblLang.getText().equals("") || lblLong.getText().equals("") || lblAcc.getText().equals("")) {
                    _clsMainBL.showToast(getContext(), "Your Location Not Found");
                } else {
                    LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                    final View promptView = layoutInflater.inflate(R.layout.popup_map_absen, null);

                    mMap = null;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
//                        mMap = ((MapFragment) (getActivity()).getFragmentManager().findFragmentById(R.id.map)).getMap();
                        if (mMap == null) {
//                            mMap = ((MapFragment) (getActivity()).getFragmentManager().findFragmentById(R.id.map)).getMap();
                        }

                        double latitude = Double.parseDouble(String.valueOf(lblLang.getText()));
                        double longitude = Double.parseDouble(String.valueOf(lblLong.getText()));
                        double accurate = Double.parseDouble(String.valueOf(lblAcc.getText()));

                        double latitudeOutlet = Double.parseDouble("232.23");
                        double longitudeOutlet = Double.parseDouble("-232.23");
                        if (txtHDLati.getText().toString().equals("")==false && txtHDLong.getText().toString().equals("")==false) {
                            latitudeOutlet = Double.parseDouble(txtHDLati.getText().toString());
                            longitudeOutlet = Double.parseDouble(txtHDLong.getText().toString());
                        }
                        MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title("Your Location");

                        MarkerOptions markerOutlet = new MarkerOptions().position(new LatLng(latitudeOutlet, longitudeOutlet)).title("Outlet Location");

                        marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));

                        final LatLngBounds.Builder builder = new LatLngBounds.Builder();
                        builder.include(marker.getPosition());
                        builder.include(markerOutlet.getPosition());
                        LatLngBounds bounds = builder.build();

                        mMap.clear();
                        mMap.addMarker(marker);
                        mMap.addMarker(markerOutlet);
                        //CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(latitude, longitude)).zoom(19).build();

                        final GoogleMap finalMMap = mMap;
                        mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {

                            @Override
                            public void onCameraChange(CameraPosition arg0) {
                                // Move camera.
                                finalMMap.moveCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 60));
                                // Remove listener to prevent position reset on camera move.
                                finalMMap.setOnCameraChangeListener(null);
                            }
                        });


                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                        alertDialogBuilder.setView(promptView);
                        alertDialogBuilder
                                .setCancelable(false)
                                .setPositiveButton("OK",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                MapFragment f = null;
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                                                    f = (MapFragment) (getActivity()).getFragmentManager().findFragmentById(R.id.map);
                                                }
                                                if (f != null) {
                                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                                                        (getActivity()).getFragmentManager().beginTransaction().remove(f).commit();
                                                    }
                                                }

                                                dialog.dismiss();
                                            }
                                        });
                        final AlertDialog alertD = alertDialogBuilder.create();

                        Location locationA = new Location("point A");

                        locationA.setLatitude(latitude);
                        locationA.setLongitude(longitude);

                        Location locationB = new Location("point B");

                        locationB.setLatitude(latitudeOutlet);
                        locationB.setLongitude(longitudeOutlet);

                        float distance = locationA.distanceTo(locationB);
                        intdistance=(int) Math.ceil(distance);
                        alertD.setTitle(String.valueOf((int) Math.ceil(distance)) + " meters");
                        alertD.show();
                    }
                }
            }
        });
        LoadData();
        return v;
    }

    private void ShowForLocationAndPhoto(boolean param){
        if(param){
            llimgview.setVisibility(View.VISIBLE);
            trLocation.setVisibility(View.VISIBLE);
            btnRefreshMaps.setVisibility(View.VISIBLE);
            btnPopupMap.setVisibility(View.VISIBLE);
        }else{
            llimgview.setVisibility(View.GONE);
            trLocation.setVisibility(View.GONE);
            btnRefreshMaps.setVisibility(View.GONE);
            btnPopupMap.setVisibility(View.GONE);
        }
    }
    private String GetBobot() {
        clsMobile_mVisitPlanCategory _clsMobile_mVisitPlanCategory = new clsMobile_mVisitPlanCategory();
        List<clsMobile_mVisitPlanCategory> ListCategoryPlan = new Select().from(clsMobile_mVisitPlanCategory.class).where(_clsMobile_mVisitPlanCategory.txtConstintCategoryID + "=?", txtIdCategory).execute();
        int Bobot = 0;
        String txtCategory = spnCategory.getSelectedItem().toString().toUpperCase();
        clsMobile_UserJabatan _clsMobile_UserJabatan = new clsMobile_UserJabatan();
        List<clsMobile_UserJabatan> ListOfclsMobile_UserJabatan = new Select().from(clsMobile_UserJabatan.class).where(_clsMobile_UserJabatan.txtConstBitPrimary + "=1").execute();
        if (ListCategoryPlan.size() > 0) {
            clsMobile_mVisitPlanCategoryDetail _clsMobile_mVisitPlanCategoryDetail = new clsMobile_mVisitPlanCategoryDetail();
            List<clsMobile_mVisitPlanCategoryDetail> ListCategoryPlanDetail = new Select().from(clsMobile_mVisitPlanCategoryDetail.class).where(_clsMobile_mVisitPlanCategoryDetail.txtConstintCategoryID + "=?", txtIdCategory).where(_clsMobile_mVisitPlanCategoryDetail.txtConstIntJabatanID + "=?", ListOfclsMobile_UserJabatan.get(0).IntJabatanID).execute();
            if(ListCategoryPlanDetail.size()>0){
                Bobot = Integer.valueOf(ListCategoryPlanDetail.get(0).IntBobot + "");
                if (cbFullDay.isChecked()) {
                    Bobot = Bobot * 2;
                }
            }
        }
        return Bobot + "";
    }

    private void viewList(Context ctx, int position) {

    }

    private void buildGoogleApiClient() {
        // TODO Auto-generated method stub
        mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();

    }

    @SuppressWarnings("deprecation")
    private boolean checkPlayServices() {
        // TODO Auto-generated method stub
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getContext());
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, getActivity(), PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
            }
            return false;
        }
        return true;
    }

    private void displayLocation() {
        // TODO Auto-generated method stub
        if(getContext()!=null){
            if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }

            if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }

            //mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

            mLastLocation=getLocation();
            if (mLastLocation != null) {
                double latitude = mLastLocation.getLatitude();
                double longitude = mLastLocation.getLongitude();
                double accurate = mLastLocation.getAccuracy();
                lblLong.setText(longitude + "");
                lblLang.setText(latitude + "");
                lblAcc.setText(accurate + "");

                Long = String.valueOf(longitude);
                Lat = String.valueOf(latitude);
                Acc = String.valueOf(accurate);
                if((txtHDLong.getText().toString().equals("")==false && txtHDLati.getText().toString().equals("")==false)){
                    if(txtHDLong.getText().toString().equals("No Location")==false && txtHDLati.getText().toString().equals("No Location")==false){
                        double latitudeOutlet = Double.parseDouble("0");
                        double longitudeOutlet = Double.parseDouble("0");
                        clsMobile_MPartnerProfile _clsMobile_MPartnerProfile=new clsMobile_MPartnerProfile();

                        latitudeOutlet = Double.parseDouble(txtHDLati.getText().toString());
                        longitudeOutlet= Double.parseDouble(txtHDLong.getText().toString());
                        Location locationA = new Location("point A");

                        locationA.setLatitude(latitude);
                        locationA.setLongitude(longitude);

                        Location locationB = new Location("point B");

                        locationB.setLatitude(latitudeOutlet);
                        locationB.setLongitude(longitudeOutlet);

                        float distance = locationA.distanceTo(locationB);
                        intdistance=(int) Math.ceil(distance);
                        tvDistance.setText(String.valueOf((int) Math.ceil(distance)) + " meters");
                    }
                }
            }else{
                if(dtclsMobile_MPartnerProfile!=null){
                    if(dtclsMobile_MPartnerProfile.txtPartnerCRM!=null && lblLang.getText().toString().equals("") && lblLong.getText().toString().equals("")){
                        double latitudeOutlet = Double.parseDouble("0");
                        double longitudeOutlet = Double.parseDouble("0");
                        clsMobile_MPartnerProfile _clsMobile_MPartnerProfile=new clsMobile_MPartnerProfile();
                        if(lblLang.getText().equals("")||lblLong.getText().equals("")){
                            lblLang.setText("No Location");
                            lblLong.setText("No Location");
                            txtHDLati.setText("");
                            txtHDLong.setText("");
                        }else{
                            latitudeOutlet = Double.parseDouble(dtclsMobile_MPartnerProfile.txtLatitude);
                            longitudeOutlet= Double.parseDouble(dtclsMobile_MPartnerProfile.txtLongitude);
                            Location locationA = new Location("point A");

                            locationA.setLatitude(Double.valueOf(lblLang.getText().toString()));
                            locationA.setLongitude(Double.valueOf(lblLong.getText().toString()));

                            Location locationB = new Location("point B");

                            locationB.setLatitude(latitudeOutlet);
                            locationB.setLongitude(longitudeOutlet);

                            float distance = locationA.distanceTo(locationB);
                            intdistance=(int) Math.ceil(distance);
                            tvDistance.setText(String.valueOf((int) Math.ceil(distance)) + " meters");
                        }

                    }
                }
            }
        }
    }

    public static Bitmap resizeBitMapImage1(String filePath, int targetWidth, int targetHeight) {
        Bitmap bitMapImage = null;
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(filePath, options);
            double sampleSize = 0;
            Boolean scaleByHeight = Math.abs(options.outHeight - targetHeight) >= Math.abs(options.outWidth
                    - targetWidth);
            if (options.outHeight * options.outWidth * 2 >= 1638) {
                sampleSize = scaleByHeight ? options.outHeight / targetHeight : options.outWidth / targetWidth;
                sampleSize = (int) Math.pow(2d, Math.floor(Math.log(sampleSize) / Math.log(2d)));
            }
            options.inJustDecodeBounds = false;
            options.inTempStorage = new byte[128];
            while (true) {
                try {
                    options.inSampleSize = (int) sampleSize;
                    bitMapImage = BitmapFactory.decodeFile(filePath, options);
                    break;
                } catch (Exception ex) {
                    try {
                        sampleSize = sampleSize * 2;
                    } catch (Exception ex1) {

                    }
                }
            }
        } catch (Exception ex) {

        }
        return bitMapImage;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();

        SupportMapFragment mapFragment = ((SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.map));

        if (mapFragment != null) {
            FragmentManager fM = getFragmentManager();
            fM.beginTransaction().remove(mapFragment).commit();

        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        SupportMapFragment mapFragment = ((SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.map));

        if (mapFragment != null) {
            FragmentManager fM = getFragmentManager();
            fM.beginTransaction().remove(mapFragment).commit();
        }
    }
    @Override
    public void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Absen Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://callplan.prm.kalbe.kalbecallplanmobile/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //initilizeMap();
        //checkPlayServices();
    }

    private Uri getOutputMediaFileUri() {
        //return Uri.fromFile(getOutputMediaFile());
        if (Build.VERSION.SDK_INT >= 24) {
            return FileProvider.getUriForFile(getActivity(),
                    BuildConfig.APPLICATION_ID + ".provider",
                    getOutputMediaFile());
        }else{
            return Uri.fromFile(getOutputMediaFile());
        }
    }

    private File getOutputMediaFile() {
        // External sdcard location

        File mediaStorageDir = new File(IMAGE_DIRECTORY_NAME);
        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            mediaStorageDir.mkdirs();
            if (!mediaStorageDir.mkdirs()) {
                Log.d(IMAGE_DIRECTORY_NAME, "Failed create " + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }
        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        File mediaFile;
        mediaFile = new File(mediaStorageDir.getPath() + "/tmp_absen" + timeStamp + ".jpg");
        mCurrentPhotoPath = "file:" + mediaFile.getAbsolutePath();
        return mediaFile;
    }

    protected void captureImage1() {

        uriImage = getOutputMediaFileUri();
        Intent intentCamera1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intentCamera1.putExtra(MediaStore.EXTRA_OUTPUT, uriImage);
        getActivity().startActivityForResult(intentCamera1, CAMERA_CAPTURE_IMAGE1_REQUEST_CODE);
    }

    protected void captureImage2() {
        uriImage = getOutputMediaFileUri();
        Intent intentCamera2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intentCamera2.putExtra(MediaStore.EXTRA_OUTPUT, uriImage);
        getActivity().startActivityForResult(intentCamera2, CAMERA_CAPTURE_IMAGE2_REQUEST_CODE);
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
    public void searchOutlet() {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        final View promptView = layoutInflater.inflate(R.layout.layout_popup_outlet, null);
        final Spinner spnBranch = (Spinner) promptView.findViewById(R.id.spnBranch);
        final Spinner spnSearchBy = (Spinner) promptView.findViewById(R.id.spnSearchBy);
        lvSearch = (ListView) promptView.findViewById(R.id.listDataOutlet);

        viewEmpty = promptView.findViewById(R.id.listNoData);

        etSearch = (EditText) promptView.findViewById(R.id.etSearch);
        tblData = (TableLayout) promptView.findViewById(R.id.tblData);
        final Button btnSearch = (Button) promptView.findViewById(R.id.btnSearch);
        ArrayList<String> arrySpinner = new ArrayList<>();
        etSearch.setText(etOutletName.getText().toString());
        arrySpinner.add("NAME");
        arrySpinner.add("ID");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, arrySpinner);
        spnSearchBy.setAdapter(adapter);

        etSearch.setOnTouchListener(new DrawableClickListener.RightDrawableClickListener(etSearch) {
            public boolean onDrawableClick() {
                etSearch.setText("");
                return true;
            }
        });

        if (ListOfclsMobile_MBranch.size() > 0) {
            List<clsSpinner> listSpinner = new ArrayList<clsSpinner>();
            for (clsMobile_MBranch dtclsMobile_MBranch : ListOfclsMobile_MBranch) {
                clsSpinner _clsSpinner = new clsSpinner(dtclsMobile_MBranch.IntCabangID.toString(), dtclsMobile_MBranch.TxtNamaCabang);
                listSpinner.add(_clsSpinner);
            }
            ArrayAdapter<clsSpinner> adapterBranch = new ArrayAdapter<clsSpinner>(getContext(), android.R.layout.simple_spinner_item, listSpinner);
            spnBranch.setAdapter(adapterBranch);
        }
        spnSearchBy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                txtSearchBy = spnSearchBy.getSelectedItem().toString();
                TextInputLayout textInputLayout = (TextInputLayout) promptView.findViewById(R.id.input_layout_poa);
                textInputLayout.setHint("Search " + txtSearchBy.toLowerCase());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
                if(etSearch.getText().length()>=3){
                    AsyncCallSearchOutlet _AsyncCallSearchOutlet = new AsyncCallSearchOutlet();
                    _AsyncCallSearchOutlet.execute();
                }else{
                    _clsMainBL.showToastWarning(getContext(), "Isi Minimal 3 karakter");
                    etSearch.requestFocus();
                }

            }
        });
        android.support.v7.app.AlertDialog.Builder alertDialogBuilder = new android.support.v7.app.AlertDialog.Builder(getContext());
        alertDialogBuilder.setView(promptView);
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        alertD = alertDialogBuilder.create();
        alertD.show();
    }
    public void searchNoRealisai() {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        final View promptView = layoutInflater.inflate(R.layout.layout_popup_norealisasi, null);
        TableLayout tlRealisasi=(TableLayout)promptView.findViewById(R.id.tlRealisasi);
        TextView tvTotRealisasi=(TextView)promptView.findViewById(R.id.tvTotRealisasi);
        final TableRow row = new TableRow(getContext());
        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1f);
        params.setMargins(1, 1, 1, 1);
        TableRow tr = new TableRow(getContext());
        String[] colTextHeader = {"No.", "No Validate"};
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
        tlRealisasi.addView(tr,0);
        if(ListOfclsMobile_ValidationNoe!=null){
            clsMobile_ValidationNo _clsMobile_ValidationNo=new clsMobile_ValidationNo();
            List<clsMobile_ValidationNo> ListDisplayNo=new Select().from(clsMobile_ValidationNo.class).where(_clsMobile_ValidationNo.txtConstBitUse+"=0").limit(10).execute();
            int index = 1;
            for (clsMobile_ValidationNo dtclsMobile_ValidationNo: ListDisplayNo) {
                tr = new TableRow(getContext());
                TextView tv_index = new TextView(getContext());
                tv_index.setTextSize(12);
                tv_index.setWidth(20);
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
                String txtPlanDate=dtclsMobile_ValidationNo.txtValidationNo;
                outlet_code.setText(txtPlanDate);
                outlet_code.setLayoutParams(params);
                tr.addView(outlet_code);
                tlRealisasi.addView(tr,index++);
            }
        }
        android.support.v7.app.AlertDialog.Builder alertDialogBuilder = new android.support.v7.app.AlertDialog.Builder(getContext());
        alertDialogBuilder.setView(promptView);
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        alertD = alertDialogBuilder.create();
        alertD.show();
    }
    public void searchPOA() {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        final View promptView = layoutInflater.inflate(R.layout.layout_popup_poa, null);
        etSearch = (EditText) promptView.findViewById(R.id.etSearch);
        final Button btnSearch = (Button) promptView.findViewById(R.id.btnSearch);
        listData = (ListView) promptView.findViewById(R.id.listData);
        etSearch.setText(etPOA.getText().toString());
        clsMobile_trPOA _dt = new clsMobile_trPOA();
        viewListEmpty = promptView.findViewById(R.id.listNoData);
        ArrayList<clsMobile_trPOA> arrayOfPOA = new ArrayList<>();
        ListOfclsMobile_trPOA = new Select().from(clsMobile_trPOA.class).where(_dt.txtConsttxtNamaProgram + " LIKE ?", "%" + etSearch.getText().toString() + "%").execute();
        if (ListOfclsMobile_trPOA.size() > 0) {
            for (final clsMobile_trPOA _dtclsMobile_trPOA : ListOfclsMobile_trPOA) {
                arrayOfPOA.add(_dtclsMobile_trPOA);
            }
        }

        etSearch.setOnTouchListener(new DrawableClickListener.RightDrawableClickListener(etSearch) {
            public boolean onDrawableClick() {
                etSearch.setText("");
                return true;
            }
        });

        ListViewPOAAdapter adapter = new ListViewPOAAdapter(getContext(), arrayOfPOA);
        listData.setAdapter(adapter);

        View viewListEmpty = promptView.findViewById(R.id.listNoData);
        if(arrayOfPOA.size() == 0)
            viewListEmpty.setVisibility(View.VISIBLE);
        else
            viewListEmpty.setVisibility(View.GONE);

        listData.setEmptyView(viewListEmpty);

        listData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                dtclsMobile_trPOA = ListOfclsMobile_trPOA.get(i);
                etPOA.setText(ListOfclsMobile_trPOA.get(i).txtNamaProgram);
                if (alertD != null) {
                    alertD.cancel();
                }
            }
        });


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncCallSearchPOA _AsyncCallSearchPOA=new AsyncCallSearchPOA();

                _AsyncCallSearchPOA.execute();
                /*
                listData.setAdapter(null);

                clsMobile_trPOA _dt = new clsMobile_trPOA();
                ListOfclsMobile_trPOA = new Select().from(clsMobile_trPOA.class).where(_dt.txtConsttxtNamaProgram + " LIKE ?", "%" + etSearch.getText().toString() + "%").execute();
                ArrayList<clsMobile_trPOA> arrayOfPOA = new ArrayList<>();

                if (ListOfclsMobile_trPOA.size() > 0) {

                    for (final clsMobile_trPOA _dtclsMobile_trPOA : ListOfclsMobile_trPOA) {
                        arrayOfPOA.add(_dtclsMobile_trPOA);
                    }
                }

                ListViewPOAAdapter adapter = new ListViewPOAAdapter(getContext(), arrayOfPOA);
                listData.setAdapter(adapter);

                View viewListEmpty = promptView.findViewById(R.id.listNoData);
                if(arrayOfPOA.size() == 0)
                    viewListEmpty.setVisibility(View.VISIBLE);
                else
                    viewListEmpty.setVisibility(View.GONE);

                listData.setEmptyView(viewListEmpty);

                listData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        dtclsMobile_trPOA = ListOfclsMobile_trPOA.get(i);
                        etPOA.setText(ListOfclsMobile_trPOA.get(i).txtNamaProgram);
                        if (alertD != null) {
                            alertD.cancel();
                        }
                    }
                });
                */
            }
        });
        android.support.v7.app.AlertDialog.Builder alertDialogBuilder = new android.support.v7.app.AlertDialog.Builder(getContext());
        alertDialogBuilder.setView(promptView);
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        alertD = alertDialogBuilder.create();
        alertD.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        // if the result is capturing Image
        //_clsMainBL.showToast(getContext(),Id);
        LoadData();
        if (requestCode == CAMERA_CAPTURE_IMAGE1_REQUEST_CODE) {
            if (resultCode == -1) {
                try {

                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                    String uri = uriImage.getPath().toString();

                    if (Build.VERSION.SDK_INT >= 24) {
                        Uri imageUri = Uri.parse(mCurrentPhotoPath);
                        File file = new File(imageUri.getPath());
                        uri= file.getPath().toString();
                    }
                    txtPathImage1 = uri;
                    bitmap = BitmapFactory.decodeFile(uri, bitmapOptions);
                    txtBinaryImage1 = _clsMainBL.getStringFrombitmap(bitmap);
                    previewCapturedImage1(bitmap);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else if (resultCode == 0) {
                _clsMainBL.showToastWarning(getContext(), "User canceled photo");
            } else {
                _clsMainBL.showToastWarning(getContext(), "Something error");
            }
        } else if (requestCode == CAMERA_CAPTURE_IMAGE2_REQUEST_CODE) {
            if (resultCode == -1) {
                try {

                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                    String uri = uriImage.getPath().toString();

                    if (Build.VERSION.SDK_INT >= 24) {
                        Uri imageUri = Uri.parse(mCurrentPhotoPath);
                        File file = new File(imageUri.getPath());
                        uri= file.getPath().toString();
                    }
                    txtPathImage2 = uri;
                    bitmap = BitmapFactory.decodeFile(uri, bitmapOptions);
                    txtBinaryImage2 = _clsMainBL.getStringFrombitmap(bitmap);
                    previewCapturedImage2(bitmap);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (resultCode == 0) {
                _clsMainBL.showToastWarning(getContext(), "User canceled photo");
            } else {
                _clsMainBL.showToastWarning(getContext(), "Something error");
            }
        }

    }

    private void previewCapturedImage1(Bitmap photo) {
        try {
//            dttAbsenUserData = _tAbsenUserBL.getDataCheckInActive();
            Bitmap bitmap = _clsMainBL.resizeImageForBlob(photo);
            imgPrevNoImg1.setVisibility(View.VISIBLE);
            ByteArrayOutputStream out = null;
            try {
                out = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 0, out); // bmp is your Bitmap instance
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (out != null) {
                        out.flush();
                        out.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Bitmap photo_view = Bitmap.createScaledBitmap(bitmap, 150, 150, true);
            ByteArrayOutputStream blob = new ByteArrayOutputStream();
//            photo.compress(Bitmap.CompressFormat.JPEG, 0 /*ignored for PNG*/, blob);
//            Bitmap bitmap1 = Bitmap.createScaledBitmap(photo, 150, 150, false);
//            bitmap1.compress(Bitmap.CompressFormat.JPEG, 100, blob);
            byte[] pht = out.toByteArray();
            imgPrevNoImg1.setImageBitmap(photo_view);
            /*
            if (dttAbsenUserData != null) {
                dttAbsenUserData.set_txtImg1(pht);
            } else {
                dttAbsenUserData.set_txtImg1(pht);
//                dttAbsenUserData.set_txtImg2(null);
                dttAbsenUserData.set_intId(txtHDId.getText().toString());
            }
            dttAbsenUserData.set_intSubmit("0");
            dttAbsenUserData.set_intSync("0");
            dttAbsenUserData.set_txtAbsen("0");
            List<tAbsenUserData> Listdata = new ArrayList<tAbsenUserData>();
            Listdata.add(dttAbsenUserData);
            _tAbsenUserBL.saveData(Listdata);
            */

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void previewCapturedImage2(Bitmap photo) {
        try {
//            dttAbsenUserData = _tAbsenUserBL.getDataCheckInActive();
            Bitmap bitmap = _clsMainBL.resizeImageForBlob(photo);
            imgPrevNoImg2.setVisibility(View.VISIBLE);
            ByteArrayOutputStream out = null;
            try {
                out = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 0, out); // bmp is your Bitmap instance
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (out != null) {
                        out.flush();
                        out.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            Bitmap photo_view = Bitmap.createScaledBitmap(bitmap, 150, 150, true);
            ByteArrayOutputStream blob = new ByteArrayOutputStream();
//            photo.compress(Bitmap.CompressFormat.JPEG, 0 /*ignored for PNG*/, blob);
//            Bitmap bitmap1 = Bitmap.createScaledBitmap(photo, 150, 150, false);
//            bitmap1.compress(Bitmap.CompressFormat.JPEG, 100, blob);
            byte[] pht = out.toByteArray();
            imgPrevNoImg2.setImageBitmap(photo_view);
            /*
            if (dttAbsenUserData != null) {
                dttAbsenUserData.set_txtImg2(pht);
            } else {
                dttAbsenUserData.set_txtImg1(null);
                dttAbsenUserData.set_txtImg2(pht);
                dttAbsenUserData.set_intId(txtHDId.getText().toString());
            }
            dttAbsenUserData.set_intSubmit("0");
            dttAbsenUserData.set_intSync("0");
            dttAbsenUserData.set_txtAbsen("0");//
            List<tAbsenUserData> Listdata = new ArrayList<tAbsenUserData>();
            Listdata.add(dttAbsenUserData);
            _tAbsenUserBL.saveData(Listdata);
            */
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult result) {
        // TODO Auto-generated method stub
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = "
                + result.getErrorCode());

    }

    @Override
    public void onConnected(@Nullable Bundle arg0) {
        // TODO Auto-generated method stub
        // Once connected with google api, get the location
        displayLocation();
    }

    @Override
    public void onConnectionSuspended(int arg0) {
        // TODO Auto-generated method stub
        mGoogleApiClient.connect();

    }

    private boolean isDeviceSupportCamera() {
        if (getContext().getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }


    @Override
    public void onLocationChanged(Location location) {
        // TODO Auto-generated method stub
        mLastLocation = location;
        displayLocation();

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public class ListViewPOAAdapter extends ArrayAdapter<clsMobile_trPOA> {
        public ListViewPOAAdapter(Context context, ArrayList<clsMobile_trPOA> data) {
            super(context, 0, data);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            clsMobile_trPOA data = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_custom_poa, parent, false);
            }
            // Lookup view for data population
            TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
            TextView tvDesc = (TextView) convertView.findViewById(R.id.tvDesc);
            TextView tvProgramId = (TextView) convertView.findViewById(R.id.tvProgramId);
            // Populate the data into the template view using the data object
            tvName.setText(data.txtNamaProgram);
            tvDesc.setText(data.txtProgramDescription);
            tvProgramId.setText(String.valueOf(data.intProgramID));
            // Return the completed view to render on screen
            return convertView;
        }
    }

    public class ListViewOutletAdapter extends ArrayAdapter<clsMobile_MPartnerProfile> {
        public ListViewOutletAdapter(Context context, ArrayList<clsMobile_MPartnerProfile> data) {
            super(context, 0, data);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            clsMobile_MPartnerProfile data = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_custom_outlet, parent, false);
            }
            // Lookup view for data population
            TextView tvCode = (TextView) convertView.findViewById(R.id.tvCode);
            TextView tvNama = (TextView) convertView.findViewById(R.id.tvNama);
            TextView tvProgramId = (TextView) convertView.findViewById(R.id.tvAlamat);
            // Populate the data into the template view using the data object
            tvCode.setText(data.txtPartnerCRM);
            tvNama.setText(data.txtNamaPartner);
            tvProgramId.setText(String.valueOf(data.txtAlamat));
            // Return the completed view to render on screen
            return convertView;
        }
    }

    private class AsyncCallSearchOutletAlias extends AsyncTask<JSONObject, Void, JSONObject> {
        @SuppressWarnings("WrongThread")
        @Override
        protected JSONObject doInBackground(JSONObject... params) {
            JSONArray _JSONArray = null;
            JSONObject JsonOutlet = null;
            JSONObject JsonResult = null;
            clsMobile_mVersionApp _clsMobile_mVersionApp = new clsMobile_mVersionApp();
            List<clsMobile_mVersionApp> listOfclsMobile_mVersionApp = new Select().from(clsMobile_mVersionApp.class).where(_clsMobile_mVersionApp.txtConstidVersion + "=?", 1).execute();
            clsMobile_trUserLogin dtclsMobile_trUserLogin = new Mobile_trUserLoginBL().CheckUserLogin();
            try {
                JsonOutlet = new JSONObject();
                _JSONArray = new JSONArray();
                JsonOutlet.put("TxtGUI_trUserLogin", dtclsMobile_trUserLogin.txtGUI.toString());
                JsonOutlet.put("TxtUserID", dtclsMobile_trUserLogin.txtUserID.toString());
                JsonOutlet.put("TxtGUI_mVersionApp", listOfclsMobile_mVersionApp.get(0).txtGUI);
                JsonOutlet.put("IntCabangID", txtIdBranchSearch);
                JsonOutlet.put("TxtSearchName", "");
                JsonOutlet.put("TxtOutletCode", dtclsMobile_MPartnerProfile.txtPartnerCRM);
                _JSONArray.put(JsonOutlet);
                JsonResult = _clsMainBL.PushData("DownloadDataOutletAliasByBranch_J", _JSONArray.toString());

                //EditText txt = (EditText) findViewById(R.id.txtLoginEmail);
                //roledata = new mUserRoleBL().getRoleAndOutlet(txtEmail1, pInfo.versionName);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                _clsMainBL.showToastWarning(getContext(), e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                _clsMainBL.showToastWarning(getContext(), e.getMessage());
            }

            return JsonResult;
        }

        private ProgressDialog Dialog = new ProgressDialog(getContext());

        @Override
        protected void onCancelled() {
            Dialog.dismiss();
            _clsMainBL.showToast(getContext(), _clsWarning.txtConstCancelRequest);
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected void onPostExecute(JSONObject JsonRes) {
            lvSearch.setAdapter(null);
            if (JsonRes != null) {
                if (_clsMainBL.ValidJSON(JsonRes)) {
                    try {
                        String txtvalidJson = (String.valueOf(JsonRes.get("validJson")));
                        JSONObject validJson = new JSONObject(txtvalidJson);
                        String intresult = (String.valueOf(validJson.get("TxtResult")));
                        if (intresult.equals("1")) {
                            String txtGetDataJson = String.valueOf(validJson.get("TxtData"));
                            JSONObject JsonDataTxtData=new JSONObject(txtGetDataJson);
                            String txtGetDataJsonOutlet = String.valueOf(JsonDataTxtData.get("MPartnerProfileAlias"));
                            JSONArray JSONArrayOutlet = new JSONArray(txtGetDataJsonOutlet);
                            ArrayList<clsMobile_MPartnerProfile> arrayOfPartnerProfile = new ArrayList<>();
                            ListTitle=new ArrayList<String>();
                            ListDesc=new ArrayList<String>();
                            int IndexSearhOutletAlias=0;
                            if (JSONArrayOutlet.length() > 0) {
                                ListOfclsMobile_MPartnerProfileAliasSearch = new ArrayList<clsMobile_MPartnerProfileAlias>();
                                for (int i = 0; i < JSONArrayOutlet.length(); i++) {
                                    JSONObject JSONObjectOutlet = JSONArrayOutlet.getJSONObject(i);
                                    clsMobile_MPartnerProfileAlias _clsMobile_MPartnerProfile = new clsMobile_MPartnerProfileAlias();
                                    Long IntPartnerID = java.lang.Long.valueOf(String.valueOf(JSONObjectOutlet.get("IntPartnerID")));
                                    _clsMobile_MPartnerProfile.IntPartnerID = IntPartnerID;
                                    _clsMobile_MPartnerProfile.txtPartnerCRM = String.valueOf(JSONObjectOutlet.get("TxtPartnerCRM"));
                                    _clsMobile_MPartnerProfile.txtNamaPartner = String.valueOf(JSONObjectOutlet.get("TxtNamaPartner"));
                                    _clsMobile_MPartnerProfile.txtChannelName = String.valueOf(JSONObjectOutlet.get("TxtChannelName"));
                                    _clsMobile_MPartnerProfile.txtAlamat = String.valueOf(JSONObjectOutlet.get("TxtAlamat"));
                                    _clsMobile_MPartnerProfile.intBranchID = java.lang.Long.valueOf(String.valueOf(JSONObjectOutlet.get("IntBranchID")));
                                    _clsMobile_MPartnerProfile.txtBranchName = String.valueOf(JSONObjectOutlet.get("TxtBranchName"));
                                    _clsMobile_MPartnerProfile.txtLongitude = String.valueOf(JSONObjectOutlet.get("TxtLongitude"));
                                    _clsMobile_MPartnerProfile.txtLatitude = String.valueOf(JSONObjectOutlet.get("TxtLatitude"));
                                    _clsMobile_MPartnerProfile.txtAcc=String.valueOf(JSONObjectOutlet.get("TxtAcc"));
                                    _clsMobile_MPartnerProfile.txtTipePartner=String.valueOf(JSONObjectOutlet.get("TxtTipePartner"));
                                    ListOfclsMobile_MPartnerProfileAliasSearch.add(_clsMobile_MPartnerProfile);
                                    ListTitle.add(_clsMobile_MPartnerProfile.txtPartnerCRM +" - "+ _clsMobile_MPartnerProfile.txtNamaPartner);
                                    ListDesc.add(_clsMobile_MPartnerProfile.txtAlamat);
                                    if(_clsMobile_MPartnerProfile.IntPartnerID.equals(dtclsMobile_MPartnerProfile.IntPartnerID)){
                                    }else{
                                        IndexSearhOutletAlias+=1;
                                    }
                                }
                                if(spnOutletAlias!=null){
                                    spnOutletAlias.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, ListTitle, ListDesc));
                                    spnOutletAlias.setEnabled(true);
                                    spnOutletAlias.setSelection(IndexSearhOutletAlias);
                                }
                            }
                        } else {
                            String txtvalidJsonError = (String.valueOf(JsonRes.get("validJson")));
                            JSONObject validJsonError = new JSONObject(txtvalidJsonError);
                            String txtWarn = (String) validJsonError.get("TxtWarn");
                            _clsMainBL.showToastWarning(getContext(), txtWarn);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        _clsMainBL.showToastWarning(getContext(), e.getMessage());
                    }

                }
            } else {
                _clsMainBL.showToastWarning(getContext(), "Failed Connection");
            }

            Dialog.dismiss();
        }

        int intProcesscancel = 0;

        @Override
        protected void onPreExecute() {
            //Make ProgressBar invisible
            //pg.setVisibility(View.VISIBLE);
            Dialog.setMessage(_clsWarning.txtConstGettingOutletHCI);
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

    private class AsyncCallSearchOutlet extends AsyncTask<JSONObject, Void, JSONObject> {
        @SuppressWarnings("WrongThread")
        @Override
        protected JSONObject doInBackground(JSONObject... params) {
            JSONArray _JSONArray = null;
            JSONObject JsonOutlet = null;
            JSONObject JsonResult = null;
            clsMobile_mVersionApp _clsMobile_mVersionApp = new clsMobile_mVersionApp();
            List<clsMobile_mVersionApp> listOfclsMobile_mVersionApp = new Select().from(clsMobile_mVersionApp.class).where(_clsMobile_mVersionApp.txtConstidVersion + "=?", 1).execute();
            clsMobile_trUserLogin dtclsMobile_trUserLogin = new Mobile_trUserLoginBL().CheckUserLogin();
            try {
                JsonOutlet = new JSONObject();
                _JSONArray = new JSONArray();
                JsonOutlet.put("TxtGUI_trUserLogin", dtclsMobile_trUserLogin.txtGUI.toString());
                JsonOutlet.put("TxtUserID", dtclsMobile_trUserLogin.txtUserID.toString());
                JsonOutlet.put("TxtGUI_mVersionApp", listOfclsMobile_mVersionApp.get(0).txtGUI);
                JsonOutlet.put("IntCabangID", txtIdBranchSearch);
                if (txtSearchBy.equals("NAME")) {
                    JsonOutlet.put("TxtSearchName", etSearch.getText().toString());
                    JsonOutlet.put("TxtOutletCode", "");
                } else if (txtSearchBy.equals("ID")) {
                    JsonOutlet.put("TxtSearchName", "");
                    JsonOutlet.put("TxtOutletCode", etSearch.getText().toString());
                }
                _JSONArray.put(JsonOutlet);
                JsonResult = _clsMainBL.PushData("DownloadDataOutletByBranch_J", _JSONArray.toString());

                //EditText txt = (EditText) findViewById(R.id.txtLoginEmail);
                //roledata = new mUserRoleBL().getRoleAndOutlet(txtEmail1, pInfo.versionName);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                _clsMainBL.showToastWarning(getContext(), e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                _clsMainBL.showToastWarning(getContext(), e.getMessage());
            }

            return JsonResult;
        }

        private ProgressDialog Dialog = new ProgressDialog(getContext());

        @Override
        protected void onCancelled() {
            Dialog.dismiss();
            _clsMainBL.showToast(getContext(), _clsWarning.txtConstCancelRequest);
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected void onPostExecute(JSONObject JsonRes) {
            lvSearch.setAdapter(null);
            if (JsonRes != null) {
                if (_clsMainBL.ValidJSON(JsonRes)) {
                    try {
                        String txtvalidJson = (String.valueOf(JsonRes.get("validJson")));
                        JSONObject validJson = new JSONObject(txtvalidJson);
                        String intresult = (String.valueOf(validJson.get("TxtResult")));
                        if (intresult.equals("1")) {
                            String txtGetDataJson = String.valueOf(validJson.get("TxtData"));
                            JSONObject JsonDataTxtData=new JSONObject(txtGetDataJson);
                            String txtGetDataJsonOutlet = String.valueOf(JsonDataTxtData.get("MPartnerProfile"));
                            JSONArray JSONArrayOutlet = new JSONArray(txtGetDataJsonOutlet);
                            ArrayList<clsMobile_MPartnerProfile> arrayOfPartnerProfile = new ArrayList<>();

                            if (JSONArrayOutlet.length() > 0) {

                                ListOfclsMobile_MPartnerProfileSearch = new ArrayList<clsMobile_MPartnerProfile>();
                                for (int i = 0; i < JSONArrayOutlet.length(); i++) {
                                    JSONObject JSONObjectOutlet = JSONArrayOutlet.getJSONObject(i);
                                    _clsMobile_MPartnerProfile = new clsMobile_MPartnerProfile();
                                    Long IntPartnerID = java.lang.Long.valueOf(String.valueOf(JSONObjectOutlet.get("IntPartnerID")));
                                    _clsMobile_MPartnerProfile.IntPartnerID = IntPartnerID;
                                    _clsMobile_MPartnerProfile.txtPartnerCRM = String.valueOf(JSONObjectOutlet.get("TxtPartnerCRM"));
                                    _clsMobile_MPartnerProfile.txtNamaPartner = String.valueOf(JSONObjectOutlet.get("TxtNamaPartner"));
                                    _clsMobile_MPartnerProfile.txtChannelName = String.valueOf(JSONObjectOutlet.get("TxtChannelName"));
                                    _clsMobile_MPartnerProfile.txtAlamat = String.valueOf(JSONObjectOutlet.get("TxtAlamat"));
                                    _clsMobile_MPartnerProfile.intBranchID = java.lang.Long.valueOf(String.valueOf(JSONObjectOutlet.get("IntBranchID")));
                                    _clsMobile_MPartnerProfile.txtBranchName = String.valueOf(JSONObjectOutlet.get("TxtBranchName"));
                                    _clsMobile_MPartnerProfile.txtLongitude = String.valueOf(JSONObjectOutlet.get("TxtLongitude"));
                                    _clsMobile_MPartnerProfile.txtLatitude = String.valueOf(JSONObjectOutlet.get("TxtLatitude"));
                                    _clsMobile_MPartnerProfile.txtAcc=String.valueOf(JSONObjectOutlet.get("TxtAcc"));
                                    _clsMobile_MPartnerProfile.txtTipePartner=String.valueOf(JSONObjectOutlet.get("TxtTipePartner"));
                                    ListOfclsMobile_MPartnerProfileSearch.add(_clsMobile_MPartnerProfile);

                                    arrayOfPartnerProfile.add(_clsMobile_MPartnerProfile);

                                    if (tblData != null) {
                                        final TableRow row = new TableRow(getContext());
                                        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                                        row.setLayoutParams(lp);

                                        TextView tvNamaPartner = new TextView(getContext());
                                        tvNamaPartner.setText(_clsMobile_MPartnerProfile.txtPartnerCRM +" - "+ _clsMobile_MPartnerProfile.txtTipePartner + System.getProperty("line.separator") + _clsMobile_MPartnerProfile.txtNamaPartner + System.getProperty("line.separator") + _clsMobile_MPartnerProfile.txtAlamat);
                                        tvNamaPartner.setGravity(Gravity.LEFT);
                                        row.addView(tvNamaPartner);
                                        row.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                dtclsMobile_MPartnerProfile = _clsMobile_MPartnerProfile;
                                                etOutletName.setText(_clsMobile_MPartnerProfile.txtNamaPartner);
                                                String txtLatitudeKNIS=_clsMobile_MPartnerProfile.txtLatitude;
                                                String txtLongitudeKNIS=_clsMobile_MPartnerProfile.txtLongitude;
                                                if(_clsMobile_MPartnerProfile.txtLatitude==null||_clsMobile_MPartnerProfile.txtLatitude.equals("null")){
                                                    txtLatitudeKNIS="No Location";
                                                    txtHDLati.setText("");
                                                }
                                                if(_clsMobile_MPartnerProfile.txtLongitude==null||_clsMobile_MPartnerProfile.txtLongitude.equals("null")){
                                                    txtLongitudeKNIS="No Location";
                                                    txtHDLong.setText("");
                                                }
                                                tvLatKNIS.setText("Lati : " + txtLatitudeKNIS);
                                                tvLongKNIS.setText("Long : " + txtLongitudeKNIS);

                                                txtHDLati.setText(txtLatitudeKNIS);
                                                txtHDLong.setText(txtLongitudeKNIS);
                                                if (alertD != null) {
                                                    alertD.cancel();
                                                }

                                            }
                                        });
                                        TextView tvintPartner = new TextView(getContext());
                                        tvintPartner.setText(_clsMobile_MPartnerProfile.IntPartnerID.toString());
                                        tvintPartner.setVisibility(View.INVISIBLE);
                                        View _View = new View(getContext());
                                        LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                        lp1.setMargins(0, 10, 0, 10);
                                        _View.setLayoutParams(lp);
                                        tvintPartner.setLayoutParams(lp);
                                        _View.setBackgroundColor(Color.BLUE);
                                        row.addView(tvintPartner);
                                        row.addView(_View);
                                        tblData.addView(row);
                                    }
                                }
                            }

                            ListViewOutletAdapter adapter = new ListViewOutletAdapter(getContext(), arrayOfPartnerProfile);
                            lvSearch.setAdapter(adapter);

                            if(arrayOfPartnerProfile.size() == 0)
                                viewEmpty.setVisibility(View.VISIBLE);
                            else
                                viewEmpty.setVisibility(View.GONE);

                            lvSearch.setEmptyView(viewEmpty);

                            lvSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    dtclsMobile_MPartnerProfile = ListOfclsMobile_MPartnerProfileSearch.get(i);
                                    etOutletName.setText(ListOfclsMobile_MPartnerProfileSearch.get(i).txtNamaPartner);
                                    String txtLatitudeKNIS=ListOfclsMobile_MPartnerProfileSearch.get(i).txtLatitude;
                                    String txtLongitudeKNIS=ListOfclsMobile_MPartnerProfileSearch.get(i).txtLongitude;
                                    if(ListOfclsMobile_MPartnerProfileSearch.get(i).txtLatitude==null||ListOfclsMobile_MPartnerProfileSearch.get(i).txtLatitude.equals("null")){
                                        txtLatitudeKNIS="No Location";
                                        txtHDLati.setText("");
                                    }else{
                                        txtHDLati.setText(dtclsMobile_MPartnerProfile.txtLatitude);
                                    }
                                    if(ListOfclsMobile_MPartnerProfileSearch.get(i).txtLongitude==null||ListOfclsMobile_MPartnerProfileSearch.get(i).txtLongitude.equals("null")){
                                        txtLongitudeKNIS="No Location";
                                        txtHDLong.setText("");
                                    }else{
                                        txtHDLong.setText(dtclsMobile_MPartnerProfile.txtLongitude);
                                    }
                                    tvLatKNIS.setText("Lati : " + txtLatitudeKNIS);
                                    tvLongKNIS.setText("Long : " + txtLongitudeKNIS);
                                    if(txtHDLati.getText().toString().equals("")==false || txtHDLong.getText().toString().equals("")==false){
                                        double latitudeOutlet = Double.parseDouble("0");
                                        double longitudeOutlet = Double.parseDouble("0");
                                           latitudeOutlet = Double.parseDouble(dtclsMobile_MPartnerProfile.txtLatitude);
                                        longitudeOutlet= Double.parseDouble(dtclsMobile_MPartnerProfile.txtLongitude);
                                        Location locationA = new Location("point A");
                                        locationA.setLatitude(Double.valueOf(lblLang.getText().toString()));
                                        locationA.setLongitude(Double.valueOf(lblLong.getText().toString()));
                                        Location locationB = new Location("point B");

                                        locationB.setLatitude(latitudeOutlet);
                                        locationB.setLongitude(longitudeOutlet);

                                        float distance = locationA.distanceTo(locationB);
                                        intdistance=(int) Math.ceil(distance);
                                        tvDistance.setText(String.valueOf((int) Math.ceil(distance)) + " meters");
                                    }else{
                                        tvDistance.setText("0 meters");
                                    }
                                    tvTypeOutlet.setText(dtclsMobile_MPartnerProfile.txtTipePartner);
                                    if(dtclsMobile_MPartnerProfile.txtTipePartner.equals("HCP")){
                                        spnOutletAlias.setVisibility(View.VISIBLE);
                                        AsyncCallSearchOutletAlias _AsyncCallSearchOutletAlias=new AsyncCallSearchOutletAlias();
                                        _AsyncCallSearchOutletAlias.execute();
                                    }else{
                                        spnOutletAlias.setAdapter(null);
                                        spnOutletAlias.setVisibility(View.GONE);
                                    }
                                    if (alertD != null) {
                                        alertD.cancel();
                                    }
                                }
                            });

                        } else {
                            String txtvalidJsonError = (String.valueOf(JsonRes.get("validJson")));
                            JSONObject validJsonError = new JSONObject(txtvalidJsonError);
                            String txtWarn = (String) validJsonError.get("TxtWarn");
                            _clsMainBL.showToastWarning(getContext(), txtWarn);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        _clsMainBL.showToastWarning(getContext(), e.getMessage());
                    }
                }
            } else {
                _clsMainBL.showToastWarning(getContext(), "Failed Connection");
            }

            Dialog.dismiss();
        }

        int intProcesscancel = 0;

        @Override
        protected void onPreExecute() {
            //Make ProgressBar invisible
            //pg.setVisibility(View.VISIBLE);
            Dialog.setMessage(_clsWarning.txtConstGettingOutlet);
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

    private class AsyncCallSearchPOA extends AsyncTask<JSONObject, Void, JSONObject> {
        @SuppressWarnings("WrongThread")
        @Override
        protected JSONObject doInBackground(JSONObject... params) {
            JSONArray _JSONArray = null;
            JSONObject JsonOutlet = null;
            JSONObject JsonResult = null;
            clsMobile_mVersionApp _clsMobile_mVersionApp = new clsMobile_mVersionApp();
            List<clsMobile_mVersionApp> listOfclsMobile_mVersionApp = new Select().from(clsMobile_mVersionApp.class).where(_clsMobile_mVersionApp.txtConstidVersion + "=?", 1).execute();
            clsMobile_trUserLogin dtclsMobile_trUserLogin = new Mobile_trUserLoginBL().CheckUserLogin();
            String txtCabang ="";
            List<clsMobile_MBranch> ListOfCabang=new Select().from(clsMobile_MBranch.class).execute();
            try {
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
                JsonOutlet = new JSONObject();
                _JSONArray = new JSONArray();
                JsonOutlet.put("TxtGUI_trUserLogin", dtclsMobile_trUserLogin.txtGUI.toString());
                JsonOutlet.put("TxtUserID", dtclsMobile_trUserLogin.txtUserID.toString());
                JsonOutlet.put("TxtGUI_mVersionApp", listOfclsMobile_mVersionApp.get(0).txtGUI);
                JsonOutlet.put("IntCabangID", txtCabang);
                JsonOutlet.put("TxtSearchName", etSearch.getText().toString());
                _JSONArray.put(JsonOutlet);
                JsonResult = _clsMainBL.PushData("DownloadDataPOAByBranch_J", _JSONArray.toString());

                //EditText txt = (EditText) findViewById(R.id.txtLoginEmail);
                //roledata = new mUserRoleBL().getRoleAndOutlet(txtEmail1, pInfo.versionName);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                _clsMainBL.showToastWarning(getContext(), e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                _clsMainBL.showToastWarning(getContext(), e.getMessage());
            }

            return JsonResult;
        }

        private ProgressDialog Dialog = new ProgressDialog(getContext());

        @Override
        protected void onCancelled() {
            Dialog.dismiss();
            _clsMainBL.showToast(getContext(), _clsWarning.txtConstCancelRequest);
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected void onPostExecute(JSONObject JsonRes) {
            ArrayList<clsMobile_trPOA> arrayOfPOA = null;
            if (JsonRes != null) {
                if (_clsMainBL.ValidJSON(JsonRes)) {
                    try {

                        String txtvalidJson = (String.valueOf(JsonRes.get("validJson")));
                        JSONObject validJson = new JSONObject(txtvalidJson);
                        String intresult = (String.valueOf(validJson.get("TxtResult")));
                        if (intresult.equals("1")) {
                            arrayOfPOA=new ArrayList<clsMobile_trPOA>();
                            String txtGetDataJson = String.valueOf(validJson.get("TxtData"));
                            //JSONObject JsonData=new JSONObject(txtGetDataJson);
                            JSONArray JSONArrayPOA = new JSONArray(txtGetDataJson);
                            if (JSONArrayPOA.length() > 0) {
                                for (int i = 0; i < JSONArrayPOA.length(); i++) {
                                    JSONObject JSONObjectPOA = JSONArrayPOA.getJSONObject(i);
                                    clsMobile_trPOA _clsMobile_trPOA=new clsMobile_trPOA();
                                    Long IntProgramID = java.lang.Long.valueOf(String.valueOf(JSONObjectPOA.get("IntProgramID")));
                                    _clsMobile_trPOA.intProgramID = IntProgramID;
                                    _clsMobile_trPOA.txtNamaProgram = String.valueOf(JSONObjectPOA.get("TxtNamaProgram"));
                                    _clsMobile_trPOA.dtStartDate = String.valueOf(JSONObjectPOA.get("DtStartDate"));
                                    _clsMobile_trPOA.dtEndDate = String.valueOf(JSONObjectPOA.get("DtEndDate"));
                                    _clsMobile_trPOA.txtProgramDescription = String.valueOf(JSONObjectPOA.get("TxtProgramDescription"));
                                    _clsMobile_trPOA.txtServer = String.valueOf(String.valueOf(JSONObjectPOA.get("TxtServer")));
                                    ListOfclsMobile_trPOA.add(_clsMobile_trPOA);
                                    arrayOfPOA.add(_clsMobile_trPOA);
                                    ListViewPOAAdapter adapter = new ListViewPOAAdapter(getContext(), arrayOfPOA);
                                    listData.setAdapter(adapter);
                                    if(arrayOfPOA.size() == 0)
                                        viewListEmpty.setVisibility(View.VISIBLE);
                                    else
                                        viewListEmpty.setVisibility(View.GONE);

                                    listData.setEmptyView(viewListEmpty);

                                    listData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                            dtclsMobile_trPOA = ListOfclsMobile_trPOA.get(i);
                                            etPOA.setText(ListOfclsMobile_trPOA.get(i).txtNamaProgram);
                                            if (alertD != null) {
                                                alertD.cancel();
                                            }
                                        }
                                    });
                                }
                            }

                        } else {
                            String txtvalidJsonError = (String.valueOf(JsonRes.get("validJson")));
                            JSONObject validJsonError = new JSONObject(txtvalidJsonError);
                            String txtWarn = (String) validJsonError.get("TxtWarn");
                            _clsMainBL.showToastWarning(getContext(), txtWarn);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        _clsMainBL.showToastWarning(getContext(), e.getMessage());
                    }


                }
            } else {
                _clsMainBL.showToastWarning(getContext(), "Failed Connection");
            }

            Dialog.dismiss();
        }

        int intProcesscancel = 0;

        @Override
        protected void onPreExecute() {
            //Make ProgressBar invisible
            //pg.setVisibility(View.VISIBLE);
            Dialog.setMessage(_clsWarning.txtConstGettingPOA);
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
    private void LoadData() {
        _clsMainBL = new clsMainBL();
        String name = "";
        try {
            IMAGE_DIRECTORY_NAME = new clsHardCode().txtPathApp ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        int intIndex = 0;
        client = new GoogleApiClient.Builder(getContext()).addApi(AppIndex.API).build();
        //_clsMainBL.showToast(getContext(),Id);
        etOutletName.setEnabled(true);
        etOutletName.setClickable(true);
        etDesc.setEnabled(true);
        etPOA.setEnabled(true);
        etPOA.setClickable(true);
        etPreNC.setEnabled(true);
        etPreNC.setClickable(true);
        txtHDLati.setText("");
        txtHDLong.setText("");

        dtclsMobile_trVisitPlan_Detail = new clsMobile_trVisitPlan_Detail();
        final List<clsMobile_trVisitPlan_Detail> ListOfclsMobile_trVisitPlan_Detail = new Select().from(clsMobile_trVisitPlan_Detail.class).where(dtclsMobile_trVisitPlan_Detail.txtConsttxtGUI_Detail + "=?", Id).execute();
        if (ListOfclsMobile_trVisitPlan_Detail.size() > 0) {

//            trdtPlan.setVisibility(View.VISIBLE);
            rlDatePlan.setVisibility(View.VISIBLE);
            spnCategory.setEnabled(false);
            spnBranch.setEnabled(false);
            spnLOB.setEnabled(false);
            etBobot.setEnabled(false);
            etOutletName.setEnabled(false);
            etOutletName.setClickable(false);
            etDesc.setEnabled(false);
            etPOA.setEnabled(false);
            cbFullDay.setClickable(false);
            cbFullDay.setEnabled(false);
            etPOA.setClickable(false);

            input_layout_outlet_search.setVisibility(View.VISIBLE);
            input_layout_poa_search.setVisibility(View.VISIBLE);
            lnOutletLocation.setVisibility(View.VISIBLE);
            RLPreNC.setVisibility(View.GONE);
            ShowForLocationAndPhoto(true);

            //etPreNC.setEnabled(false);
            //etPreNC.setClickable(false);
            dtclsMobile_trVisitPlan_Detail = ListOfclsMobile_trVisitPlan_Detail.get(0);
            clsMobile_trVisitPlan_Header _clsMobile_trVisitPlan_Header = new clsMobile_trVisitPlan_Header();
            List<clsMobile_trVisitPlan_Header> ListOfclsMobile_trVisitPlan_Header = new Select().from(clsMobile_trVisitPlan_Header.class).where(_clsMobile_trVisitPlan_Header.txtConstIntVisitPlan_HeaderID + "=?", dtclsMobile_trVisitPlan_Detail.intVisitPlan_HeaderID).execute();
            if (ListOfclsMobile_trVisitPlan_Header.size() > 0) {
                _clsMobile_trVisitPlan_Header = ListOfclsMobile_trVisitPlan_Header.get(0);
                intIndex = _clsMainBL.getIndexListOfclsSpinnerByname(_clsMobile_trVisitPlan_Header.intBranchID, "", listSpinnerBranch);
                if (intIndex != -1)
                    spnBranch.setSelection(intIndex);
            }
            clsMobile_trVisitPlan_Detail_Item _clsMobile_trVisitPlan_Detail_Item = new clsMobile_trVisitPlan_Detail_Item();
            ListOfclsMobile_trVisitPlan_Detail_Item = new Select().from(clsMobile_trVisitPlan_Detail_Item.class).where(_clsMobile_trVisitPlan_Detail_Item.txtConsttxtGUI_Detail + "=?", dtclsMobile_trVisitPlan_Detail.txtGUI_Detail).orderBy(_clsMobile_trVisitPlan_Detail_Item.txtConsintNo + " ASC ").execute();
            /*
            intIndex=_clsMainBL.getIndexListOfclsSpinnerByname(dtclsMobile_trVisitPlan_Detail.txtValidationNo,"",listSpinnerNoRealiasasi);
            if(intIndex!=-1)
                spnNoRealisasi.setSelection(intIndex);
                */
            intIndex = _clsMainBL.getIndexListOfclsSpinnerByname(dtclsMobile_trVisitPlan_Detail.intCategoryID, "", listSpinnermVisitPlanCategory);
            if (intIndex != -1)
                spnCategory.setSelection(intIndex);
            txtIdCategory=dtclsMobile_trVisitPlan_Detail.intCategoryID;
            if (dtclsMobile_trVisitPlan_Detail.intCategoryID.equals(enum_VisitPlanCategory.OUTLET.getValue()+"")) {
                tvDistance.setText("0");
                input_layout_outlet_search.setVisibility(View.VISIBLE);
                input_layout_poa_search.setVisibility(View.GONE);
                lnOutletLocation.setVisibility(View.VISIBLE);

            } else if (dtclsMobile_trVisitPlan_Detail.intCategoryID.equals(enum_VisitPlanCategory.ACTIVITY.getValue()+"")) {
                tvDistance.setText("0");
                RLPreNC.setVisibility(View.VISIBLE);
                input_layout_poa_search.setVisibility(View.VISIBLE);
                input_layout_outlet_search.setVisibility(View.GONE);
                lnOutletLocation.setVisibility(View.GONE);
            }else if(dtclsMobile_trVisitPlan_Detail.intCategoryID.equals(enum_VisitPlanCategory.CUTI.getValue()+"")||dtclsMobile_trVisitPlan_Detail.intCategoryID.equals(enum_VisitPlanCategory.PERJALANAN_DINAS.getValue()+"")){
                ShowForLocationAndPhoto(false);
                input_layout_outlet_search.setVisibility(View.GONE);
                input_layout_poa_search.setVisibility(View.GONE);
                lnOutletLocation.setVisibility(View.GONE);
                RLPreNC.setVisibility(View.GONE);
            } else {
                tvDistance.setText("0");
                input_layout_poa_search.setVisibility(View.GONE);
                input_layout_outlet_search.setVisibility(View.GONE);
                lnOutletLocation.setVisibility(View.GONE);
            }
            /*
            if(dtclsMobile_trVisitPlan_Detail.intCategoryID.equals(enum_VisitPlanCategory.CUTI.getValue()+"")||dtclsMobile_trVisitPlan_Detail.intCategoryID.equals(enum_VisitPlanCategory.PERJALANAN_DINAS.getValue()+"")){
                ShowForLocationAndPhoto(false);
            }else{
                ShowForLocationAndPhoto(true);
            }
            if(dtclsMobile_trVisitPlan_Detail.intCategoryID.equals(enum_VisitPlanCategory.ACTIVITY.getValue()+"")){
                RLPreNC.setVisibility(View.VISIBLE);
            }else{
                RLPreNC.setVisibility(View.GONE);
            }
            */
            intIndex = _clsMainBL.getIndexListOfclsSpinnerByname(dtclsMobile_trVisitPlan_Detail.intLOBID, "", listSpinnerLOB);
            if (intIndex != -1)
                spnLOB.setSelection(intIndex);
            etDesc.setText(dtclsMobile_trVisitPlan_Detail.txtDescription);
            clsMobile_MPartnerProfile _clsMobile_MPartnerProfile = new clsMobile_MPartnerProfile();
            clsMobile_MPartnerProfileAlias _clsMobile_MPartnerProfileAlias = new clsMobile_MPartnerProfileAlias();
            clsMobile_trPOA _clsMobile_trPOA = new clsMobile_trPOA();
            List<clsMobile_MPartnerProfile> ListDataclsMobile_MPartnerProfile = new Select().from(clsMobile_MPartnerProfile.class).where(_clsMobile_MPartnerProfile.txtConsttxtPartnerCRM + "=?", dtclsMobile_trVisitPlan_Detail.txtNoKode).execute();

            List<clsMobile_trPOA> ListDataclsMobile_trPOA = new Select().from(clsMobile_trPOA.class).where(_clsMobile_trPOA.txtConstintProgramID + "=?", dtclsMobile_trVisitPlan_Detail.txtNoKode).execute();
            if (ListDataclsMobile_MPartnerProfile.size() > 0 && spnCategory.getSelectedItem().toString().equals("OUTLET")) {
                dtclsMobile_MPartnerProfile = ListDataclsMobile_MPartnerProfile.get(0);
                etOutletName.setText(ListDataclsMobile_MPartnerProfile.get(0).txtNamaPartner);
                btnCheckIn.setVisibility(View.VISIBLE);
//                trOutlet.setVisibility(View.VISIBLE);
                tvTypeOutlet.setText(dtclsMobile_MPartnerProfile.txtTipePartner);
                spnOutletAlias.setVisibility(View.VISIBLE);
                String txtLatitudeKNIS=null;
                String txtLongitudeKNIS=null;
                if(dtclsMobile_MPartnerProfile.txtTipePartner.equals("HCP")){
                    List<clsMobile_MPartnerProfileAlias> ListDataclsMobile_MPartnerProfileAlias = new Select().from(clsMobile_MPartnerProfileAlias.class).where(_clsMobile_MPartnerProfileAlias.txtConstintPartnerIDSub + "=?", dtclsMobile_MPartnerProfile.IntPartnerID.toString()).execute();
                    if(ListDataclsMobile_MPartnerProfileAlias.size()>0){
                        ListOfclsMobile_MPartnerProfileAliasSearch=ListDataclsMobile_MPartnerProfileAlias;
                        ListTitle=new ArrayList<String>();
                        ListDesc=new ArrayList<String>();
                        int IndexSearhOutletAlias=0;
                        for (clsMobile_MPartnerProfileAlias _dtclsMobile_MPartnerProfileAlias : ListDataclsMobile_MPartnerProfileAlias) {
                            ListTitle.add(_dtclsMobile_MPartnerProfileAlias.txtPartnerCRM +" - "+ _dtclsMobile_MPartnerProfileAlias.txtNamaPartner);
                            ListDesc.add(_dtclsMobile_MPartnerProfileAlias.txtAlamat);
                            if(_dtclsMobile_MPartnerProfileAlias.IntPartnerID.equals(dtclsMobile_MPartnerProfile.IntPartnerID)){
                                break;
                            }else{
                                IndexSearhOutletAlias+=1;
                            }
                        }
                        spnOutletAlias.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, ListTitle, ListDesc));
                        spnOutletAlias.setEnabled(true);
                        spnOutletAlias.setSelection(IndexSearhOutletAlias);
                    }
                }else{
                    spnOutletAlias.setVisibility(View.GONE);
                    txtLatitudeKNIS=ListDataclsMobile_MPartnerProfile.get(0).txtLatitude;
                    txtLongitudeKNIS=ListDataclsMobile_MPartnerProfile.get(0).txtLongitude;
                    if(txtLatitudeKNIS==null||txtLatitudeKNIS.equals("null")){
                        txtLatitudeKNIS="No Location";
                        txtHDLati.setText("");
                    }else{
                        txtHDLati.setText(txtLatitudeKNIS);
                    }
                    if(txtLongitudeKNIS==null||txtLongitudeKNIS.equals("null")){
                        txtLongitudeKNIS="No Location";
                        txtHDLong.setText("");
                    }else{
                        txtHDLong.setText(txtLongitudeKNIS);
                    }
                    //tvLatKNIS.setText("Lati : " + ListDataclsMobile_MPartnerProfile.get(0).txtLatitude);
                    //tvLongKNIS.setText("Long : " + ListDataclsMobile_MPartnerProfile.get(0).txtLongitude);
                    tvLatKNIS.setText("Lati : " + txtLatitudeKNIS);
                    tvLongKNIS.setText("Long : " + txtLongitudeKNIS);
                    txtHDLati.setText(txtLatitudeKNIS);
                    txtHDLong.setText(txtLongitudeKNIS);
                    /*
                    if (checkPlayServices()) {
                        buildGoogleApiClient();
                        displayLocation();
                    }
                    */
                    displayLocation();
                }
            } else if (ListDataclsMobile_trPOA.size() > 0 && spnCategory.getSelectedItem().toString().equals("ACTIVITY")) {
                dtclsMobile_trPOA = ListDataclsMobile_trPOA.get(0);
                etPOA.setText(dtclsMobile_trPOA.txtNamaProgram);
                etPOA.setVisibility(View.VISIBLE);
            } else {
                if (dtclsMobile_trVisitPlan_Detail.txtNoKode.equals("0")) {
                    btnCheckIn.setVisibility(View.VISIBLE);
                    //_clsMainBL.showToast(getContext(),"Cordinate Outlet Not Found!!");
                } else {
                    btnCheckIn.setVisibility(View.VISIBLE);
                }
                etOutletName.setText("-");
            }
            if (ListOfclsMobile_trVisitPlan_Detail_Item.size() > 0) {
                _clsMobile_trVisitPlan_Detail_Item = ListOfclsMobile_trVisitPlan_Detail_Item.get(0);
                lblLong.setText(_clsMobile_trVisitPlan_Detail_Item.txtLongitude);
                lblLang.setText(_clsMobile_trVisitPlan_Detail_Item.txtLatitude);
                lblAcc.setText(_clsMobile_trVisitPlan_Detail_Item.txtAccuracy);
                for (clsMobile_trVisitPlan_Detail_Item dt : ListOfclsMobile_trVisitPlan_Detail_Item) {
                    clsMobile_mBinaryFile dtclsMobile_mBinaryFile = new clsMobile_mBinaryFile();
                    List<clsMobile_mBinaryFile> ListOfclsMobile_mBinaryFile = new Select().from(clsMobile_mBinaryFile.class).where(dtclsMobile_mBinaryFile.txtConsttxtGUI_IDTable + "=?", dt.txtGUI_Detail_Item).execute();
                    if (dt.intNo.equals("1")) {
                        if (ListOfclsMobile_mBinaryFile.size() > 0) {
                            dtclsMobile_mBinaryFile = ListOfclsMobile_mBinaryFile.get(0);
                            Bitmap dtBitmap = _clsMainBL.getImageFromclsMobile_mBinaryFile(dtclsMobile_mBinaryFile);
                            if (dtBitmap != null) {
                                Bitmap photo_view = Bitmap.createScaledBitmap(dtBitmap, 150, 150, true);
                                imgPrevNoImg1.setImageBitmap(photo_view);
                                dtBitmap.recycle();
                                dtBitmap=null;
                                photo_view.recycle();
                                photo_view=null;
                            }
                        }
                    } else if (dt.intNo.equals("2")) {
                        if (ListOfclsMobile_mBinaryFile.size() > 0) {
                            dtclsMobile_mBinaryFile = ListOfclsMobile_mBinaryFile.get(0);
                            Bitmap dtBitmap = _clsMainBL.getImageFromclsMobile_mBinaryFile(dtclsMobile_mBinaryFile);
                            if (dtBitmap != null) {
                                Bitmap photo_view = Bitmap.createScaledBitmap(dtBitmap, 150, 150, true);
                                imgPrevNoImg2.setImageBitmap(photo_view);
                                dtBitmap.recycle();
                                dtBitmap=null;
                                photo_view.recycle();
                                photo_view=null;
                            }
                        }
                    }
                }
            }
            txtHDActualDate.setText(dtclsMobile_trVisitPlan_Detail.dtActualDate);
            etDatePlan.setText(dtclsMobile_trVisitPlan_Detail.dtPlanDate);
            etPreNC.setText(dtclsMobile_trVisitPlan_Detail.intJumlahPreNC);
            etBobot.setText(dtclsMobile_trVisitPlan_Detail.intBobot);
        }
    }


}
