package DB_Place_Review;

import java.io.Serializable;

public class Place_Review_VO implements Serializable {
	private String PR_CON, PLAN_TITLE, PLAN_NUM, M_ID, PA_NUM;

	public String getM_ID() {
		return M_ID;
	}

	public void setM_ID(String m_ID) {
		M_ID = m_ID;
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

	public String getPLAN_TITLE() {
		return PLAN_TITLE;
	}

	public void setPLAN_TITLE(String pLAN_TITLE) {
		PLAN_TITLE = pLAN_TITLE;
	}

	public String getPR_CON() {
		return PR_CON;
	}

	public void setPR_CON(String pR_CON) {
		PR_CON = pR_CON;
	}
}