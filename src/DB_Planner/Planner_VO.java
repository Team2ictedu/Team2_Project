package DB_Planner;

import java.io.Serializable;

public class Planner_VO implements Serializable {
	private String PLAN_NUM, PLAN_TITLE, PLAN_DATE, PLAN_DAYS;

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

	public String getPLAN_DAYS() {
		return PLAN_DAYS;
	}

	public void setPLAN_DAYS(String pLAN_DAYS) {
		PLAN_DAYS = pLAN_DAYS;
	}
}