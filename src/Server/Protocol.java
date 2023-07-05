package Server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import UserDB.UserVO;

public class Protocol implements Serializable{
	// 1.전체보기, 2.삽입하기, 3.삭제, 4.검색, 5.고치기
	int cmd;
	int result ;
	List<UserVO> list;
	UserVO vo ;
	
	public int getCmd() {
		return cmd;
	}
	public void setCmd(int cmd) {
		this.cmd = cmd;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public List<UserVO> getList() {
		return list;
	}
	public void setList(List<UserVO> list) {
		this.list = list;
	}
	public UserVO getVo() {
		return vo;
	}
	public void setVo(UserVO vo) {
		this.vo = vo;
	}
}
