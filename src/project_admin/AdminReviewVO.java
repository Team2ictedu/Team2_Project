package project_admin;

import java.io.Serializable;

public class AdminReviewVO implements Serializable {
	private String  pr_con, m_id, pa_num, pa_name;


	public String getPr_con() {
		return pr_con;
	}

	public String getPa_name() {
		return pa_name;
	}

	public void setPa_name(String pa_name) {
		this.pa_name = pa_name;
	}

	public void setPr_con(String pr_con) {
		this.pr_con = pr_con;
	}

	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	public String getPa_num() {
		return pa_num;
	}

	public void setPa_num(String pa_num) {
		this.pa_num = pa_num;
	}
	
	
	
}
