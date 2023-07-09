package project_admin;

import java.io.Serializable;

public class AdminConnectionVO implements Serializable {
	private String p_date, p_connection;

	public String getP_date() {
		return p_date;
	}

	public void setP_date(String p_date) {
		this.p_date = p_date;
	}

	public String getP_connection() {
		return p_connection;
	}

	public void setP_connection(String p_connection) {
		this.p_connection = p_connection;
	}
	
	
}
