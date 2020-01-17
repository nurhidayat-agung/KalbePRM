package callplan.prm.kalbe.kalbecallplanmobile.model.network;

import com.google.gson.annotations.SerializedName;

import callplan.prm.kalbe.kalbecallplanmobile.model.network.ProgramDat;

public class ResponseGetToken{

	@SerializedName("programDat")
	private ProgramDat programDat;

	@SerializedName("txtLangID")
	private String txtLangID;

	@SerializedName("objData")
	private String objData;

	@SerializedName("txtGUID")
	private String txtGUID;

	@SerializedName("txtProgramCode")
	private String txtProgramCode;

	@SerializedName("txtErrorMessage")
	private String txtErrorMessage;

	@SerializedName("bitError")
	private boolean bitError;

	@SerializedName("txtMessage")
	private String txtMessage;

	@SerializedName("txtStackTrace")
	private String txtStackTrace;

	@SerializedName("txtUsername")
	private String txtUsername;

	@SerializedName("txtFunction")
	private String txtFunction;

	@SerializedName("objRequestData")
	private String objRequestData;

	@SerializedName("txtToken")
	private String txtToken;

	@SerializedName("bitSuccess")
	private boolean bitSuccess;

	public void setProgramDat(ProgramDat programDat){
		this.programDat = programDat;
	}

	public ProgramDat getProgramDat(){
		return programDat;
	}

	public void setTxtLangID(String txtLangID){
		this.txtLangID = txtLangID;
	}

	public String getTxtLangID(){
		return txtLangID;
	}

	public void setObjData(String objData){
		this.objData = objData;
	}

	public String getObjData(){
		return objData;
	}

	public void setTxtGUID(String txtGUID){
		this.txtGUID = txtGUID;
	}

	public String getTxtGUID(){
		return txtGUID;
	}

	public void setTxtProgramCode(String txtProgramCode){
		this.txtProgramCode = txtProgramCode;
	}

	public String getTxtProgramCode(){
		return txtProgramCode;
	}

	public void setTxtErrorMessage(String txtErrorMessage){
		this.txtErrorMessage = txtErrorMessage;
	}

	public String getTxtErrorMessage(){
		return txtErrorMessage;
	}

	public void setBitError(boolean bitError){
		this.bitError = bitError;
	}

	public boolean isBitError(){
		return bitError;
	}

	public void setTxtMessage(String txtMessage){
		this.txtMessage = txtMessage;
	}

	public String getTxtMessage(){
		return txtMessage;
	}

	public void setTxtStackTrace(String txtStackTrace){
		this.txtStackTrace = txtStackTrace;
	}

	public String getTxtStackTrace(){
		return txtStackTrace;
	}

	public void setTxtUsername(String txtUsername){
		this.txtUsername = txtUsername;
	}

	public String getTxtUsername(){
		return txtUsername;
	}

	public void setTxtFunction(String txtFunction){
		this.txtFunction = txtFunction;
	}

	public String getTxtFunction(){
		return txtFunction;
	}

	public void setObjRequestData(String objRequestData){
		this.objRequestData = objRequestData;
	}

	public String getObjRequestData(){
		return objRequestData;
	}

	public void setTxtToken(String txtToken){
		this.txtToken = txtToken;
	}

	public String getTxtToken(){
		return txtToken;
	}

	public void setBitSuccess(boolean bitSuccess){
		this.bitSuccess = bitSuccess;
	}

	public boolean isBitSuccess(){
		return bitSuccess;
	}

	@Override
 	public String toString(){
		return 
			"ResponseGetToken{" + 
			"programDat = '" + programDat + '\'' + 
			",txtLangID = '" + txtLangID + '\'' + 
			",objData = '" + objData + '\'' + 
			",txtGUID = '" + txtGUID + '\'' + 
			",txtProgramCode = '" + txtProgramCode + '\'' + 
			",txtErrorMessage = '" + txtErrorMessage + '\'' + 
			",bitError = '" + bitError + '\'' + 
			",txtMessage = '" + txtMessage + '\'' + 
			",txtStackTrace = '" + txtStackTrace + '\'' + 
			",txtUsername = '" + txtUsername + '\'' + 
			",txtFunction = '" + txtFunction + '\'' + 
			",objRequestData = '" + objRequestData + '\'' + 
			",txtToken = '" + txtToken + '\'' + 
			",bitSuccess = '" + bitSuccess + '\'' + 
			"}";
		}
}