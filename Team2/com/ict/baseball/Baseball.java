package Team2.com.ict.baseball;

import java.util.Scanner;

public class Baseball {
	public static void main(String[] args) {
		Baseball_Method base = new Baseball_Method();
		Scanner sc = new Scanner(System.in);

		// 게임 시작
		base.startBaseball();

		// 게임 중
		game: while (true) {
			//유저 선택
			base.userSelect();
			
			//시스템
			base.compare();
			
			//열번째마다 물어보기
			while (base.getCount()%10 ==0) {
				System.out.print("계속 하시겠습니까?(y/n)");
				String goStop = sc.next();
				if (goStop.equalsIgnoreCase("y")) {
					continue game;
				} else if (goStop.equalsIgnoreCase("n")) {
					base.effect();
					System.out.println("게임을 종료합니다. ");
					System.out.println("\t정답");
					System.out.println("1번째\t2번째\t3번째");
					for(int i=0; i<3; i++) {
						System.out.print(base.getCom(i) + "\t");
					}
					break game;
				} else
					System.out.println("잘못 입력하였습니다.");
				continue;
			}
			
			//게임종료
			if (base.getStrike() == 3) {
				break game;
			}

		}

	}
}
