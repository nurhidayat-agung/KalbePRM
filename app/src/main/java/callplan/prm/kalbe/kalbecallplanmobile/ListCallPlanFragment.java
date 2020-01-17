package callplan.prm.kalbe.kalbecallplanmobile;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import callplan.prm.kalbe.callplanlibrary.common.clsMobile_MPartnerProfile;
//import callplan.prm.kalbe.callplanlibrary.common.clsMobile_mBinaryFile;
//import callplan.prm.kalbe.callplanlibrary.common.clsMobile_trVisitPlan_Detail;
//import callplan.prm.kalbe.callplanlibrary.common.clsMobile_trVisitPlan_Detail_Item;
//import callplan.prm.kalbe.callplanlibrary.common.clsMobile_trVisitPlan_Header;
//import callplan.prm.kalbe.callplanlibrary.common.clsSwipeList;
import callplan.prm.kalbe.kalbecallplanmobile.bl.clsMainBL;
import callplan.prm.kalbe.kalbecallplanmobile.model.clsMobile_MPartnerProfile;
import callplan.prm.kalbe.kalbecallplanmobile.model.clsMobile_mBinaryFile;
import callplan.prm.kalbe.kalbecallplanmobile.model.clsMobile_trVisitPlan_Detail;
import callplan.prm.kalbe.kalbecallplanmobile.model.clsMobile_trVisitPlan_Detail_Item;
import callplan.prm.kalbe.kalbecallplanmobile.model.clsSwipeList;
import callplan.prm.kalbe.kalbecallplanmobile.app.AppDatabase;
import edu.swu.pulltorefreshswipemenulistview.library.PullToRefreshSwipeMenuListView;
import edu.swu.pulltorefreshswipemenulistview.library.pulltorefresh.interfaces.IXListViewListener;
import edu.swu.pulltorefreshswipemenulistview.library.swipemenu.bean.SwipeMenu;
import edu.swu.pulltorefreshswipemenulistview.library.swipemenu.interfaces.OnMenuItemClickListener;
import edu.swu.pulltorefreshswipemenulistview.library.swipemenu.interfaces.SwipeMenuCreator;
import edu.swu.pulltorefreshswipemenulistview.library.util.RefreshTime;


public class ListCallPlanFragment extends Fragment implements IXListViewListener {
    View v;
    FloatingActionButton floatingActionButton;
    private Handler mHandler;
    private AppAdapter mAdapter;
    //Button btnAdd;
    PullToRefreshSwipeMenuListView mListView;
    private static List<clsSwipeList> swipeList = new ArrayList<clsSwipeList>();
    private static List<clsMobile_trVisitPlan_Detail> ListOfDetailCall = null;
    clsMainBL _clsMainBL = null;
    private static Map<String, HashMap> mapMenu;
    private AppDatabase appDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_list_call_plan, container, false);
        appDatabase = AppDatabase.getDatabase(getActivity());
        //btnAdd=(Button) v.findViewById(R.id.btnAdd);
        floatingActionButton = (FloatingActionButton) v.findViewById(R.id.fab);
        Intent i = getActivity().getIntent();
        clsSwipeList swplist;
        _clsMainBL = new clsMainBL();
        swipeList.clear();
        clsMobile_trVisitPlan_Detail _clsMobile_trVisitPlan_Detail = new clsMobile_trVisitPlan_Detail();
        // ListOfDetailCall = new Select().from(clsMobile_trVisitPlan_Detail.class)
        // .where(_clsMobile_trVisitPlan_Detail.txtConstintSubmit + "=1").execute();
        ListOfDetailCall = appDatabase.daoMobileTrVisitPlanDetail().getByIntSubmit(1);

        for (clsMobile_trVisitPlan_Detail dtclsMobile_trVisitPlan_Detail : ListOfDetailCall) {
            swplist = new clsSwipeList();
            String txtDatePlan = "";
            if (dtclsMobile_trVisitPlan_Detail.dtPlanDate.equals("") || dtclsMobile_trVisitPlan_Detail.dtPlanDate == null) {
                txtDatePlan = "[No Planning ] - Actual Date : " + dtclsMobile_trVisitPlan_Detail.dtActualDate;
            } else {
                txtDatePlan = "Date Plan : " + dtclsMobile_trVisitPlan_Detail.dtPlanDate;
            }
            swplist.set_txtTitle(txtDatePlan + System.getProperty("line.separator") + "Category : " + dtclsMobile_trVisitPlan_Detail.txtCategoryName);
            swplist.set_txtDescription(dtclsMobile_trVisitPlan_Detail.txtDescription);
            swipeList.add(swplist);
        }
        mListView = (PullToRefreshSwipeMenuListView) v.findViewById(R.id.listView);
        mAdapter = _clsMainBL.setList(getActivity().getApplicationContext(), swipeList);
        mListView.setAdapter(mAdapter);
        mListView.setPullRefreshEnable(false);
        mListView.setPullLoadEnable(true);
        mListView.setXListViewListener(this);
        mHandler = new Handler();
        HashMap<String, String> mapView = new HashMap<String, String>();
        mapMenu = new HashMap<String, HashMap>();
        mapView.put("name", "View");
        mapView.put("bgColor", "#3498db");
        mapMenu.put("0", mapView);
        mapView = new HashMap<String, String>();
        mapView.put("name", "Edit");
        mapView.put("bgColor", "#f4c542");
        mapMenu.put("1", mapView);
        SwipeMenuCreator creator = _clsMainBL.setCreator(getActivity().getApplicationContext(), mapMenu);
        mListView.setMenuCreator(creator);
        mListView.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            @Override
            public void onMenuItemClick(int position, SwipeMenu menu, int index) {
                clsSwipeList item = swipeList.get(position);
                switch (index) {
                    case 0:
                        viewList(getActivity().getApplicationContext(), position);
                        break;
                    case 1:
                        editList(getActivity().getApplicationContext(), position);
                        break;
                }
            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallPlanFragment frag = new CallPlanFragment("");
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.frame
                        , frag).commit();
            }
        });
        return v;
    }

    @Override
    public void onRefresh() {

    }

    private void editList(Context ctx, int position) {
        CallPlanFragment frag = new CallPlanFragment(ListOfDetailCall.get(position).txtGUI_Detail);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame
                , frag).commit();
    }

    private void viewList(Context ctx, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(this.getContext());
        final View promptView = layoutInflater.inflate(R.layout.layout_popup_datacallplan, null);
        clsMobile_trVisitPlan_Detail dtclsMobile_trVisitPlan_Detail = ListOfDetailCall.get(position);
        clsMobile_MPartnerProfile _clsMobile_MPartnerProfile = new clsMobile_MPartnerProfile();
        // List<clsMobile_MPartnerProfile> ListDataclsMobile_MPartnerProfile = new Select()
        // .from(clsMobile_MPartnerProfile.class)
        // .where(_clsMobile_MPartnerProfile.txtConsttxtPartnerCRM + "=?", dtclsMobile_trVisitPlan_Detail.txtNoKode).execute();
        List<clsMobile_MPartnerProfile> ListDataclsMobile_MPartnerProfile = appDatabase.daoMobileMPartnerProfile()
                .getBytxtConsttxtPartnerCRM(dtclsMobile_trVisitPlan_Detail.txtNoKode);

        final TextView tvNamaOutlet = (TextView) promptView.findViewById(R.id.tvNamaOutlet);
        final TextView tvCategoryName = (TextView) promptView.findViewById(R.id.tvCategoryName);
        final TextView tvDescName = (TextView) promptView.findViewById(R.id.tvDescName);
        final TextView tvdtPlanValue = (TextView) promptView.findViewById(R.id.tvdtPlanValue);
        final TextView tvintBobot = (TextView) promptView.findViewById(R.id.tvintBobot);
        final TextView tvdtPreNC = (TextView) promptView.findViewById(R.id.tvdtPreNC);
        final TextView tvDate = (TextView) promptView.findViewById(R.id.tvDate);
        final TextView tvLong = (TextView) promptView.findViewById(R.id.tvLong);
        final TextView tvLat = (TextView) promptView.findViewById(R.id.tvLat);
        final TextView tvAcc = (TextView) promptView.findViewById(R.id.tvAcc);
        final ImageButton image1 = (ImageButton) promptView.findViewById(R.id.image1);
        final ImageButton image2 = (ImageButton) promptView.findViewById(R.id.image2);
        final LinearLayout LLRealisasi = (LinearLayout) promptView.findViewById(R.id.LLRealisasi);
        final android.support.v7.widget.CardView CVPhoto = (android.support.v7.widget.CardView) promptView.findViewById(R.id.CVPhoto);
        LLRealisasi.setVisibility(View.GONE);
        CVPhoto.setVisibility(View.GONE);
        if (ListDataclsMobile_MPartnerProfile.size() > 0) {
            tvNamaOutlet.setText(ListDataclsMobile_MPartnerProfile.get(0).txtNamaPartner);
        } else {
            tvNamaOutlet.setText("-");
        }
        tvCategoryName.setText(dtclsMobile_trVisitPlan_Detail.txtCategoryName);
        tvDescName.setText(dtclsMobile_trVisitPlan_Detail.txtDescription);
        String txtDatePlan = "";
        if (dtclsMobile_trVisitPlan_Detail.dtPlanDate.equals("") || dtclsMobile_trVisitPlan_Detail.dtPlanDate == null) {
            txtDatePlan = "Unplan";
        } else {
            txtDatePlan = dtclsMobile_trVisitPlan_Detail.dtPlanDate;
        }
        tvdtPlanValue.setText(txtDatePlan);
        tvintBobot.setText("Bobot : " + dtclsMobile_trVisitPlan_Detail.intBobot);
        tvdtPreNC.setText("PreNC : " + dtclsMobile_trVisitPlan_Detail.intJumlahPreNC);
        tvDate.setText(dtclsMobile_trVisitPlan_Detail.dtActualDate);
        clsMobile_trVisitPlan_Detail_Item _clsMobile_trVisitPlan_Detail_Item = new clsMobile_trVisitPlan_Detail_Item();
        // List<clsMobile_trVisitPlan_Detail_Item> ListDataclsMobile_trVisitPlan_Detail_Item = new Select()
        // .from(clsMobile_trVisitPlan_Detail_Item.class)
        // .where(_clsMobile_trVisitPlan_Detail_Item.txtConstintVisitPlan_DetailID + "=?", dtclsMobile_trVisitPlan_Detail.intVisitPlan_DetailID)
        // .orderBy(_clsMobile_trVisitPlan_Detail_Item.txtConsintNo + " ASC ").execute();
        List<clsMobile_trVisitPlan_Detail_Item> ListDataclsMobile_trVisitPlan_Detail_Item = appDatabase
                .daoMobileTrVisitPlanDetailItem()
                .getBytxtConstintVisitPlan_DetailIDorderBytxtConsintNo(dtclsMobile_trVisitPlan_Detail.intVisitPlan_DetailID);

        if (ListDataclsMobile_trVisitPlan_Detail_Item.size() > 0) {
            tvLong.setText(ListDataclsMobile_trVisitPlan_Detail_Item.get(0).txtLongitude);
            tvLat.setText(ListDataclsMobile_trVisitPlan_Detail_Item.get(0).txtLatitude);
            tvAcc.setText(ListDataclsMobile_trVisitPlan_Detail_Item.get(0).txtAccuracy);
            for (clsMobile_trVisitPlan_Detail_Item dt : ListDataclsMobile_trVisitPlan_Detail_Item) {
                clsMobile_mBinaryFile dtclsMobile_mBinaryFile = new clsMobile_mBinaryFile();
                // List<clsMobile_mBinaryFile> ListOfclsMobile_mBinaryFile = new Select()
                // .from(clsMobile_mBinaryFile.class)
                // .where(dtclsMobile_mBinaryFile.txtGUI_IDTable + "=", dt.txtGUI_Detail_Item).execute();
                List<clsMobile_mBinaryFile> ListOfclsMobile_mBinaryFile = appDatabase.daoMobile_mBinaryFile()
                        .getbytxtGUI_IDTable(dt.txtGUI_Detail_Item);

                if (dt.intNo.equals("1")) {
                    if (ListOfclsMobile_mBinaryFile.size() > 0) {
                        dtclsMobile_mBinaryFile = ListOfclsMobile_mBinaryFile.get(0);
                        Bitmap dtBitmap = _clsMainBL.getImageFromclsMobile_mBinaryFile(dtclsMobile_mBinaryFile);
                        if (dtBitmap != null) {
                            image1.setImageBitmap(dtBitmap);
                        }
                    }
                } else if (dt.intNo.equals("2")) {
                    if (ListOfclsMobile_mBinaryFile.size() > 0) {
                        dtclsMobile_mBinaryFile = ListOfclsMobile_mBinaryFile.get(0);
                        Bitmap dtBitmap = _clsMainBL.getImageFromclsMobile_mBinaryFile(dtclsMobile_mBinaryFile);
                        if (dtBitmap != null) {
                            image1.setImageBitmap(dtBitmap);
                        }
                    }
                }

            }
        } else {
            tvLong.setText("-");
            tvLat.setText("-");
            tvAcc.setText("-");
        }

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this.getContext());
        alertDialogBuilder.setView(promptView);
        alertDialogBuilder
                .setCancelable(false)
                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alertD = alertDialogBuilder.create();
        alertD.show();
    }

    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                onLoad();
            }
        }, 1);
    }

    private void onLoad() {
        mListView.setRefreshTime(RefreshTime.getRefreshTime(getActivity().getApplicationContext()));
        mListView.stopRefresh();

        mListView.stopLoadMore();

    }
}
