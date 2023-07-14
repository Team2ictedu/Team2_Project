package DB_User;

import java.util.List;

import project_admin.AdminUserVO;
import project_admin.AdminUsersDAO;

public class TEST {
	
	public TEST() {
		
	}
	public static void main(String[] args) {
		System.out.println("test");
		
		UserVO vo = new UserVO();
		vo.setM_ID("kjun");
		vo.setM_PW("1111");
		UserVO vo2 = UserDAO.getLogin(vo);
		System.out.println(vo2.getM_BIRTH());
		
//		List<AdminUserVO> list;
		
//		list = AdminUsersDAO.getList();
//		System.out.println(list.get(0).getM_id());
	}
}
