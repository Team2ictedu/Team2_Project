package DB_Travel_Location;

import java.io.Serializable;

public class Travel_Location_VO implements Serializable {
	private String TL_NUM, CITY, TOWN;

	public String getTL_NUM() {
		return TL_NUM;
	}

	public void setTL_NUM(String tL_NUM) {
		TL_NUM = tL_NUM;
	}

	public String getCITY() {
		return CITY;
	}

	public void setCITY(String cITY) {
		CITY = cITY;
	}

	public String getTOWN() {
		return TOWN;
	}

	public void setTOWN(String tOWN) {
		TOWN = tOWN;
	}
}