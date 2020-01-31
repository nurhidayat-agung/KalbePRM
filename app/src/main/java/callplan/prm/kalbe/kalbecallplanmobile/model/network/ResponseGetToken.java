package callplan.prm.kalbe.kalbecallplanmobile.model.network;

import com.google.gson.annotations.SerializedName;

public class ResponseGetToken{

	@SerializedName("access_token")
	public String access_token;

	@SerializedName("token_type")
	public String token_type;

	@SerializedName("expires_in")
	public long expires_in;

	@SerializedName("userName")
	public String userName;

	@SerializedName(".issued")
	public String issued;

	@SerializedName(".expires")
	public String expires;
}