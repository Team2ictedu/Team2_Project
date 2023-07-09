package DB_Place_All;

import java.io.Serializable;

public class Place_All_VO implements Serializable {
	private String PA_NUM, PA_NAME, PA_LOCATION, PA_CON, PA_PRICE;

	public String getPA_NUM() {
		return PA_NUM;
	}

	public void setPA_NUM(String pA_NUM) {
		PA_NUM = pA_NUM;
	}

	public String getPA_NAME() {
		return PA_NAME;
	}

	public void setPA_NAME(String pA_NAME) {
		PA_NAME = pA_NAME;
	}

	public String getPA_LOCATION() {
		return PA_LOCATION;
	}

	public void setPA_LOCATION(String pA_LOCATION) {
		PA_LOCATION = pA_LOCATION;
	}

	public String getPA_CON() {
		return PA_CON;
	}

	public void setPA_CON(String pA_CON) {
		PA_CON = pA_CON;
	}

	public String getPA_PRICE() {
		return PA_PRICE;
	}

	public void setPA_PRICE(String pA_PRICE) {
		PA_PRICE = pA_PRICE;
	}
}