package callplan.prm.kalbe.kalbecallplanmobile.addons.response;

public class ProgramDat{
	private String txtProgramName;
	private int intDevelopmentYear;
	private String txtDescription;
	private String txtGUID;
	private String txtProgramCode;
	private boolean bitActive;
	private int intGoLiveYear;
	private int intProgramID;
	private String txtSourceRepository;
	private String txtSolutionName;

	public void setTxtProgramName(String txtProgramName){
		this.txtProgramName = txtProgramName;
	}

	public String getTxtProgramName(){
		return txtProgramName;
	}

	public void setIntDevelopmentYear(int intDevelopmentYear){
		this.intDevelopmentYear = intDevelopmentYear;
	}

	public int getIntDevelopmentYear(){
		return intDevelopmentYear;
	}

	public void setTxtDescription(String txtDescription){
		this.txtDescription = txtDescription;
	}

	public String getTxtDescription(){
		return txtDescription;
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

	public void setBitActive(boolean bitActive){
		this.bitActive = bitActive;
	}

	public boolean isBitActive(){
		return bitActive;
	}

	public void setIntGoLiveYear(int intGoLiveYear){
		this.intGoLiveYear = intGoLiveYear;
	}

	public int getIntGoLiveYear(){
		return intGoLiveYear;
	}

	public void setIntProgramID(int intProgramID){
		this.intProgramID = intProgramID;
	}

	public int getIntProgramID(){
		return intProgramID;
	}

	public void setTxtSourceRepository(String txtSourceRepository){
		this.txtSourceRepository = txtSourceRepository;
	}

	public String getTxtSourceRepository(){
		return txtSourceRepository;
	}

	public void setTxtSolutionName(String txtSolutionName){
		this.txtSolutionName = txtSolutionName;
	}

	public String getTxtSolutionName(){
		return txtSolutionName;
	}

	@Override
 	public String toString(){
		return 
			"ProgramDat{" + 
			"txtProgramName = '" + txtProgramName + '\'' + 
			",intDevelopmentYear = '" + intDevelopmentYear + '\'' + 
			",txtDescription = '" + txtDescription + '\'' + 
			",txtGUID = '" + txtGUID + '\'' + 
			",txtProgramCode = '" + txtProgramCode + '\'' + 
			",bitActive = '" + bitActive + '\'' + 
			",intGoLiveYear = '" + intGoLiveYear + '\'' + 
			",intProgramID = '" + intProgramID + '\'' + 
			",txtSourceRepository = '" + txtSourceRepository + '\'' + 
			",txtSolutionName = '" + txtSolutionName + '\'' + 
			"}";
		}
}
