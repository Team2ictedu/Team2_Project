package project_admin;

import java.io.Serializable;

public class AdminUserVO implements Serializable{
	private String m_id, m_pw,  m_email, m_name, m_birth, m_phone, m_terms, m_class;

	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	
	public String getM_pw() {
		return m_pw;
	}
	public void setM_pw(String m_pw) {
		this.m_pw = m_pw;
	}
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	public String getM_birth() {
		return m_birth;
	}
	public void setM_birth(String m_birth) {
		this.m_birth = m_birth;
	}
	public String getM_email() {
		return m_email;
	}
	public void setM_email(String m_email) {
		this.m_email = m_email;
	}
	public String getM_phone() {
		return m_phone;
	}
	public void setM_phone(String m_phone) {
		this.m_phone = m_phone;
	}
	public String getM_terms() {
		return m_terms;
	}
	public void setM_terms(String m_terms) {
		this.m_terms = m_terms;
	}
	public String getM_class() {
		return m_class;
	}
	public void setM_class(String m_class) {
		this.m_class = m_class;
	}
//	public String getM_lastlogin() {
//		return m_lastlogin;
//	}
//	public void setM_lastlogin(String m_lastlogin) {
//		this.m_lastlogin = m_lastlogin;
//	}
//	public String getDelete_con() {
//		return delete_con;
//	}
//	public void setDelete_con(String delete_con) {
//		this.delete_con = delete_con;
//	}
//	public String getDelete_time() {
//		return delete_time;
//	}
//	public void setDelete_time(String delete_time) {
//		this.delete_time = delete_time;
//	}

}
