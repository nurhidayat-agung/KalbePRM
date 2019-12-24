package callplan.prm.kalbe.kalbecallplanmobile;


import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.graphics.Bitmap;
import com.activeandroid.query.Select;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.List;

import callplan.prm.kalbe.callplanlibrary.ENUM.enum_mconfig;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_MBranch;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_mBinaryFile;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_trUserLogin;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_trVisitPlan_Detail;
import callplan.prm.kalbe.callplanlibrary.common.clsMobile_trVisitPlan_Header;
import callplan.prm.kalbe.kalbecallplanmobile.bl.Mobile_mConfigBL;
import callplan.prm.kalbe.kalbecallplanmobile.bl.clsMainBL;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    View v;
    TextView tvTotalCallPlan,tvTotalRealisasi,tvTotalUnplan, tvUsername, tvEmail, tvBranch, tvLatestCallPlanDesc, tvLatestCallPlanDate,tvLatestCallPlanTitle;
    clsMainBL _clsMainBL;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_home, container, false);
        _clsMainBL=new clsMainBL();
        tvTotalCallPlan = (TextView) v.findViewById(R.id.tvTotalCallPlan);
        tvTotalRealisasi = (TextView) v.findViewById(R.id.tvTotalRealisasi);
        tvTotalUnplan = (TextView) v.findViewById(R.id.tvTotalUnplan);
        tvUsername = (TextView) v.findViewById(R.id.tvUsername);
        tvEmail = (TextView) v.findViewById(R.id.tvEmail);
        tvBranch = (TextView) v.findViewById(R.id.tvBranch);
        tvLatestCallPlanDesc = (TextView) v.findViewById(R.id.tvLatestCallPlanText);
        tvLatestCallPlanDate = (TextView) v.findViewById(R.id.tvLatestCallPlanDate);
        tvLatestCallPlanTitle= (TextView) v.findViewById(R.id.tvLatestCallPlanTitle);
        final NavigationView nv = (NavigationView) getActivity().findViewById(R.id.navigation_view);
        final Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        final FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        clsMobile_trVisitPlan_Detail _clsMobile_trVisitPlan_Detail=new clsMobile_trVisitPlan_Detail();
        final List<clsMobile_trVisitPlan_Header> ListOfDetailCallHeader = new Select().from(clsMobile_trVisitPlan_Header.class).execute();
        final List<clsMobile_trVisitPlan_Detail> ListOfDetailCall = new Select().from(clsMobile_trVisitPlan_Detail.class).where(_clsMobile_trVisitPlan_Detail.txtConstdtPlanDate+" is not null").orderBy(_clsMobile_trVisitPlan_Detail.txtConstdtPlanDate+" DESC" ).execute();
        final List<clsMobile_trVisitPlan_Detail> ListOfDetailUnplan = new Select().from(clsMobile_trVisitPlan_Detail.class).where(_clsMobile_trVisitPlan_Detail.txtConstdtPlanDate+" is null").execute();
        final List<clsMobile_trVisitPlan_Detail> ListOfDetailRealisasi = new Select().from(clsMobile_trVisitPlan_Detail.class).where(_clsMobile_trVisitPlan_Detail.txtConstdtPlanDate+" is not null AND "+_clsMobile_trVisitPlan_Detail.txtConstintSubmit+" in (2,3)").execute();
        if(ListOfDetailCall.size()>0){
            clsMobile_trVisitPlan_Detail dtLatestCallPlan = ListOfDetailCall.get(0);
            tvTotalRealisasi.setText(String.valueOf(ListOfDetailRealisasi.size()));
            int plan=ListOfDetailCall.size()-ListOfDetailRealisasi.size();
            tvTotalCallPlan.setText(String.valueOf(plan));
            //tvLatestCallPlanDesc.setText(dtLatestCallPlan.txtDescription);
            //tvLatestCallPlanDate.setText(dtLatestCallPlan.dtPlanDate);
        }
        if(ListOfDetailUnplan.size()>0){
            tvTotalUnplan.setText(String.valueOf(ListOfDetailUnplan.size()));
        }
        if(ListOfDetailRealisasi.size()>0){
            tvTotalRealisasi.setText(String.valueOf(ListOfDetailRealisasi.size()));
        }
        Mobile_mConfigBL _Mobile_mConfigBL=new Mobile_mConfigBL();
        String txtLink=_Mobile_mConfigBL.getValue(enum_mconfig.API.getValue());
        String txtAPILink =txtLink.replace("/VisitPlan/API/VisitPlanAPI/","");
        tvLatestCallPlanTitle.setText(txtAPILink);
        String txtLASTSYN=_Mobile_mConfigBL.getValue(enum_mconfig.LAST_SYNC.getValue());
        tvLatestCallPlanDate.setText(txtLASTSYN);
        tvTotalCallPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(ListOfDetailCallHeader.size()>0){
                    toolbar.setTitle("Call Plan Data");
                    nv.setCheckedItem(R.id.mncallPlan);
                    ListCallPlanFragment fragmentListCallPlan = new ListCallPlanFragment();
                    fragmentTransaction.replace(R.id.frame, fragmentListCallPlan);
                    fragmentTransaction.commit();
                }
                /*

                toolbar.setTitle("Call Plan Data");
                nv.setCheckedItem(R.id.mncallPlan);
                ListCallPlanFragment fragmentListCallPlan = new ListCallPlanFragment();
                fragmentTransaction.replace(R.id.frame, fragmentListCallPlan);
                fragmentTransaction.commit();
                */
            }
        });
        int valid= _clsMainBL.CheckValidApps(getContext());
        //_clsMainBL.showToast(getContext(),valid+"");
        tvTotalRealisasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ListOfDetailRealisasi.size()>0){
                    toolbar.setTitle("Realisasi");
                    nv.setCheckedItem(R.id.mnReporting);
                    ReportingFragment fragmentListRealisasi = new ReportingFragment();
                    fragmentTransaction.replace(R.id.frame, fragmentListRealisasi);
                    fragmentTransaction.commit();
                }
            }
        });
        tvTotalUnplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ListOfDetailUnplan.size()>0){
                    toolbar.setTitle("Realisasi");
                    nv.setCheckedItem(R.id.mnReporting);
                    ReportingFragment fragmentListRealisasi = new ReportingFragment();
                    fragmentTransaction.replace(R.id.frame, fragmentListRealisasi);
                    fragmentTransaction.commit();
                }
            }
        });
        CircleImageView ivProfile = (CircleImageView) v.findViewById(R.id.profile_image);

        clsMobile_mBinaryFile dtclsMobile_mBinaryFile = new clsMobile_mBinaryFile();
        List<clsMobile_mBinaryFile> ListOfclsMobile_mBinaryFile = new Select().from(clsMobile_mBinaryFile.class).where(dtclsMobile_mBinaryFile.txtConsttxtGUI_IDTable + "=?", "1").execute();

        if (ListOfclsMobile_mBinaryFile.size() != 0) {
            Bitmap dtBitmap = _clsMainBL.getImageFromclsMobile_mBinaryFile(ListOfclsMobile_mBinaryFile.get(0));
            Bitmap photo_view = Bitmap.createScaledBitmap(dtBitmap, 600, 600, true);
            ivProfile.setImageBitmap(photo_view);
        }

        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CropImage.startPickImageActivity(getActivity());
            }
        });

        List<clsMobile_trUserLogin> _ListOfUserLogin = new Select().from(clsMobile_trUserLogin.class).execute();
        clsMobile_trUserLogin dt =_ListOfUserLogin.get(0);

        List<clsMobile_MBranch> _ListOfMBranch = new Select().from(clsMobile_MBranch.class).execute();
        if(_ListOfMBranch.size()>0){
            clsMobile_MBranch dtBranch =_ListOfMBranch.get(0);
            tvBranch.setText(dtBranch.TxtNamaCabang);
        }else{
            tvBranch.setText("");
        }



        tvUsername.setText(dt.txtName);
        tvEmail.setText(dt.txtEmail.toLowerCase());



        return v;

    }

}
