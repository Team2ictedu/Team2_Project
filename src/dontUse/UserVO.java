package dontUse;

import java.io.Serializable;

public class UserVO implements Serializable {
	private String M_ID, M_PW, M_NAME, M_BIRTH, M_EMAIL, M_PHONE, M_TERMS, M_CLASS, M_LASTLOGIN, DELETE_CON,
			DELETE_TIME;

	public String getM_ID() {
		return M_ID;
	}

	public void setM_ID(String m_ID) {
		M_ID = m_ID;
	}

	public String getM_PW() {
		return M_PW;
	}

	public void setM_PW(String m_PW) {
		M_PW = m_PW;
	}

	public String getM_NAME() {
		return M_NAME;
	}

	public void setM_NAME(String m_NAME) {
		M_NAME = m_NAME;
	}

	public String getM_BIRTH() {
		return M_BIRTH;
	}

	public void setM_BIRTH(String m_BIRTH) {
		M_BIRTH = m_BIRTH;
	}

	public String getM_EMAIL() {
		return M_EMAIL;
	}

	public void setM_EMAIL(String m_EMAIL) {
		M_EMAIL = m_EMAIL;
	}

	public String getM_PHONE() {
		return M_PHONE;
	}

	public void setM_PHONE(String m_PHONE) {
		M_PHONE = m_PHONE;
	}

	public String getM_TERMS() {
		return M_TERMS;
	}

	public void setM_TERMS(String m_TERMS) {
		M_TERMS = m_TERMS;
	}

	public String getM_CLASS() {
		return M_CLASS;
	}

	public void setM_CLASS(String m_CLASS) {
		M_CLASS = m_CLASS;
	}

	public String getM_LASTLOGIN() {
		return M_LASTLOGIN;
	}

	public void setM_LASTLOGIN(String m_LASTLOGIN) {
		M_LASTLOGIN = m_LASTLOGIN;
	}

	public String getDELETE_CON() {
		return DELETE_CON;
	}

	public void setDELETE_CON(String dELETE_CON) {
		DELETE_CON = dELETE_CON;
	}

	public String getDELETE_TIME() {
		return DELETE_TIME;
	}

	public void setDELETE_TIME(String dELETE_TIME) {
		DELETE_TIME = dELETE_TIME;
	}

}