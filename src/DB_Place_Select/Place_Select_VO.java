package DB_Place_Select;

import java.io.Serializable;

public class Place_Select_VO implements Serializable {
	private String PS_DAY, PS_TIME, PS_CON;

	public String getPS_DAY() {
		return PS_DAY;
	}

	public void setPS_DAY(String pS_DAY) {
		PS_DAY = pS_DAY;
	}

	public String getPS_TIME() {
		return PS_TIME;
	}

	public void setPS_TIME(String pS_TIME) {
		PS_TIME = pS_TIME;
	}

	public String getPS_CON() {
		return PS_CON;
	}

	public void setPS_CON(String pS_CON) {
		PS_CON = pS_CON;
	}
}