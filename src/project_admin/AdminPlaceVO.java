package project_admin;

import java.io.Serializable;

public class AdminPlaceVO implements Serializable {
	private String pa_num, pa_name, pa_location, pa_con, pa_price;

	public String getPa_num() {
		return pa_num;
	}

	public void setPa_num(String pa_num) {
		this.pa_num = pa_num;
	}

	public String getPa_name() {
		return pa_name;
	}

	public void setPa_name(String pa_name) {
		this.pa_name = pa_name;
	}

	public String getPa_location() {
		return pa_location;
	}

	public void setPa_location(String pa_location) {
		this.pa_location = pa_location;
	}

	public String getPa_con() {
		return pa_con;
	}

	public void setPa_con(String pa_con) {
		this.pa_con = pa_con;
	}

	public String getPa_price() {
		return pa_price;
	}

	public void setPa_price(String pa_price) {
		this.pa_price = pa_price;
	}
	
}
