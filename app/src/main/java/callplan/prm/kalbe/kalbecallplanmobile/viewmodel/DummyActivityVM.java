package callplan.prm.kalbe.kalbecallplanmobile.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;

import callplan.prm.kalbe.kalbecallplanmobile.addons.util.SharedPref;
import callplan.prm.kalbe.kalbecallplanmobile.app.AppPRM;
import callplan.prm.kalbe.kalbecallplanmobile.app.NetworkContext;
import callplan.prm.kalbe.kalbecallplanmobile.model.clsHardCode;
import callplan.prm.kalbe.kalbecallplanmobile.model.network.postData.ObjRequestDataItem;
import callplan.prm.kalbe.kalbecallplanmobile.model.network.postData.PostData;
import callplan.prm.kalbe.kalbecallplanmobile.model.network.postData.ProgramDat;
import callplan.prm.kalbe.kalbecallplanmobile.networkDAO.TokenService;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class DummyActivityVM extends AndroidViewModel {

    private TokenService tokenService;
    private AppPRM appPRM;
    private SharedPref sharedPref;
    public MutableLiveData<Boolean> isShowPdialog = new MutableLiveData<>();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();


    public DummyActivityVM(@NonNull Application application) {
        super(application);
        appPRM = AppPRM.create(application);
        sharedPref = new SharedPref(application);
        tokenService = NetworkContext.createService(TokenService.class,application);
        isShowPdialog.setValue(false);
    }

    public void getToken()
    {
        isShowPdialog.setValue(true);
        Disposable disposable = tokenService.getToken(clsHardCode.ConstGetToken.grant_type,
                clsHardCode.ConstGetToken.username,
                clsHardCode.ConstGetToken.password)
                .subscribeOn(appPRM.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseGetToken -> {
                    isShowPdialog.setValue(false);
                    String token = responseGetToken.access_token;
                    sharedPref.setString(clsHardCode.ConstGetToken.tagToken,responseGetToken.access_token);
                    testPing();
                },throwable -> {
                    isShowPdialog.setValue(false);
                });
        compositeDisposable.add(disposable);
    }

    public void testPing()
    {
        isShowPdialog.setValue(true);
        PostData postData = new PostData();
        postData.setTxtToken(sharedPref.getString(clsHardCode.ConstGetToken.tagToken));
        ProgramDat programDat = new ProgramDat();
        postData.setProgramDat(programDat);
        postData.setTxtUsername("");
        postData.setTxtLangID("");
        postData.setTxtProgramCode("PRM");
        postData.setTxtFunction("LogIn_J");
        ObjRequestDataItem objRequestDataItem = new ObjRequestDataItem();
        objRequestDataItem.setTxtPegawaiID("MUHAMMAD.MUKTI");
        objRequestDataItem.setTxtPassword("Akangneng1401");
        objRequestDataItem.setTxtGUIMVersionApp("20BF6A8E-A5AB-4E77-9A30-34AC906E6FD1");
        objRequestDataItem.setTxtVersion("AND 2017.002 ");
        objRequestDataItem.setTxtDevice("a5y17lte");
        objRequestDataItem.setTxtModel("samsung SM-A520F");
        postData.setObjRequestData(new ArrayList<ObjRequestDataItem>(Arrays.asList(objRequestDataItem)));
        postData.setBitSuccess(false);
        postData.setObjData(null);
        postData.setTxtMessage("");
        postData.setBitError(false);
        postData.setTxtErrorMessage("");
        postData.setTxtGUID("653168c1-87cc-40c5-9dc7-ff4f55bf3d88");
        postData.setTxtStackTrace("");

        Disposable disposable = tokenService.samplePost(postData)
                .subscribeOn(appPRM.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response ->
                {
                    isShowPdialog.setValue(false);
                    String cek = response.getTxtGUID();
                }, throwable -> {
                    isShowPdialog.setValue(false);
                    String msgError = throwable.getMessage();
                });
        compositeDisposable.add(disposable);
    }


}
