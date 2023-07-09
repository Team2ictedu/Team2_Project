package DB_Place_Select;

import java.io.Serializable;

public class Place_Select_VO implements Serializable {
	private String PS_DAY, PS_TIME, PS_CON, PLAN_NUM, PA_NUM;

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

	public String getPLAN_NUM() {
		return PLAN_NUM;
	}

	public void setPLAN_NUM(String pLAN_NUM) {
		PLAN_NUM = pLAN_NUM;
	}

	public String getPA_NUM() {
		return PA_NUM;
	}

	public void setPA_NUM(String pA_NUM) {
		PA_NUM = pA_NUM;
	}
}