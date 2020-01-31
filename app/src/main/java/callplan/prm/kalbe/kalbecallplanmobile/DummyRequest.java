package callplan.prm.kalbe.kalbecallplanmobile;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import callplan.prm.kalbe.kalbecallplanmobile.addons.util.PDialog;
import callplan.prm.kalbe.kalbecallplanmobile.viewmodel.DummyActivityVM;

public class DummyRequest extends AppCompatActivity {
    private DummyActivityVM dummyActivityVM;
    private PDialog pDialog;
    private Button btnPing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy_request);
        btnPing = findViewById(R.id.btn_ping);
        dummyActivityVM = ViewModelProviders.of(this).get(DummyActivityVM.class);
        pDialog = new PDialog(this);

        dummyActivityVM.isShowPdialog.observe(this,aBoolean -> {
            pDialog.setDialog(aBoolean);
        });

        btnPing.setOnClickListener(view -> {
            dummyActivityVM.getToken();
        });

    }
}
