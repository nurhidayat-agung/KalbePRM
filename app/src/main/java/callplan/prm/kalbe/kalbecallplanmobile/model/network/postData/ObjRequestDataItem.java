package callplan.prm.kalbe.kalbecallplanmobile.model.network.postData;

import com.google.gson.annotations.SerializedName;

public class ObjRequestDataItem{

	@SerializedName("TxtDevice")
	private String txtDevice;

	@SerializedName("TxtPassword")
	private String txtPassword;

	@SerializedName("TxtModel")
	private String txtModel;

	@SerializedName("TxtPegawaiID")
	private String txtPegawaiID;

	@SerializedName("TxtGUI_mVersionApp")
	private String txtGUIMVersionApp;

	@SerializedName("TxtVersion")
	private String txtVersion;

	public void setTxtDevice(String txtDevice){
		this.txtDevice = txtDevice;
	}

	public String getTxtDevice(){
		return txtDevice;
	}

	public void setTxtPassword(String txtPassword){
		this.txtPassword = txtPassword;
	}

	public String getTxtPassword(){
		return txtPassword;
	}

	public void setTxtModel(String txtModel){
		this.txtModel = txtModel;
	}

	public String getTxtModel(){
		return txtModel;
	}

	public void setTxtPegawaiID(String txtPegawaiID){
		this.txtPegawaiID = txtPegawaiID;
	}

	public String getTxtPegawaiID(){
		return txtPegawaiID;
	}

	public void setTxtGUIMVersionApp(String txtGUIMVersionApp){
		this.txtGUIMVersionApp = txtGUIMVersionApp;
	}

	public String getTxtGUIMVersionApp(){
		return txtGUIMVersionApp;
	}

	public void setTxtVersion(String txtVersion){
		this.txtVersion = txtVersion;
	}

	public String getTxtVersion(){
		return txtVersion;
	}

	@Override
 	public String toString(){
		return 
			"ObjRequestDataItem{" + 
			"txtDevice = '" + txtDevice + '\'' + 
			",txtPassword = '" + txtPassword + '\'' + 
			",txtModel = '" + txtModel + '\'' + 
			",txtPegawaiID = '" + txtPegawaiID + '\'' + 
			",txtGUI_mVersionApp = '" + txtGUIMVersionApp + '\'' + 
			",txtVersion = '" + txtVersion + '\'' + 
			"}";
		}
}