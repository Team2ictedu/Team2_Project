package Team2.com.ict.baseball;

import java.util.Random;
import java.util.Scanner;

public class Baseball_Method {
	private int count = 0;
	private int strike = 0;
	private int ball = 0;
	private int[] com = new int[3];
	private int[] user = new int[3];

	public int[] getUser() {
		return user;
	}

	public void setUser(int[] user) {
		this.user = user;
	}

	public int getCom(int i) {
		return com[i];
	}

	public void setCom(int[] com) {
		this.com = com;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getStrike() {
		return strike;
	}

	public void setStrike(int strike) {
		this.strike = strike;
	}

	public int getBall() {
		return ball;
	}

	public void setBall(int ball) {
		this.ball = ball;
	}

	// 메소드
	// 랜덤 0~9 숫자 뽑음
	// 조건) 중복숫자는 제외
	public void startBaseball() {
		Random comRandom = new Random();
		for (int i = 0; i < 3; i++) {
			com[i] = comRandom.nextInt(9) + 1; // 0~9까지
			for (int j = 0; j < i; j++) {
				if (com[i] == com[j]) { // 중복되면 다시 랜덤값 줌
					i--;
				}
			}
			// 주석해제시 정상작동 확인을 위한 정답미리보기
			// System.out.print(com[i] + " ");
		}
		System.out.println("게임 시작!!");
	}

	public void userSelect() {
		count++;
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < 3; i++) {
			System.out.print(i + 1 + "번째 선택: ");
			user[i] = sc.nextInt();
		}
	}
	public void effect() {
		for(int i=0; i<10; i++) {
			System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
		}
	}
	public void compare() {
		strike = 0;
		ball = 0;
		// 스트라이크 판단 (위치와 숫자 모두 맞음)
		for (int i = 0; i < 3; i++) {
			if (com[i] == user[i]) {
				strike++;
			}
			for (int j = 0; j < 3; j++) {
				if (i != j) { // 볼 판단(숫자만 맞음, 중복가능)
					if (com[i] == user[j]) {
						ball++;
					}
				}
			}
		}
		
		// 결과 출력
		if (ball + strike == 0) { // 아웃
			System.out.println("아웃!!");
		} else if (strike == 3) { // 게임종료
			effect();
			System.out.println("!홈런!");
			System.out.println("총 카운트: " + count);

		} else // 볼, 스트라이크 출력
			System.out.println(strike + " 스트라이크, " + ball + " 볼 입니다.");
	}
}
