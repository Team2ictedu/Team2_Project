package DB_Planner;

import java.io.Serializable;

public class Planner_VO implements Serializable {
	private String PLAN_NUM, PLAN_TITLE, PLAN_DATE, M_ID, TL_NUM, PLAN_LASTDATE;
	int PLAN_DAYS;

	public String getPLAN_LASTDATE() {
		return PLAN_LASTDATE;
	}

	public void setPLAN_LASTDATE(String pLAN_LASTDATE) {
		PLAN_LASTDATE = pLAN_LASTDATE;
	}

	public String getPLAN_NUM() {
		return PLAN_NUM;
	}

	public void setPLAN_NUM(String pLAN_NUM) {
		PLAN_NUM = pLAN_NUM;
	}

	public String getPLAN_TITLE() {
		return PLAN_TITLE;
	}

	public void setPLAN_TITLE(String pLAN_TITLE) {
		PLAN_TITLE = pLAN_TITLE;
	}

	public String getPLAN_DATE() {
		return PLAN_DATE;
	}

	public void setPLAN_DATE(String pLAN_DATE) {
		PLAN_DATE = pLAN_DATE;
	}

	public int getPLAN_DAYS() {
		return PLAN_DAYS;
	}

	public void setPLAN_DAYS(int pLAN_DAYS) {
		PLAN_DAYS = pLAN_DAYS;
	}

	public String getM_ID() {
		return M_ID;
	}

	public void setM_ID(String m_ID) {
		M_ID = m_ID;
	}

	public String getTL_NUM() {
		return TL_NUM;
	}

	public void setTL_NUM(String tL_NUM) {
		TL_NUM = tL_NUM;
	}
	
	
}
