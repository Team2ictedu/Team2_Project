package Team2.com.ict.remote.interfaces;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Volume vol = null;
		
		esc: while (true) {
			System.out.println("장치를 선택하세요 >> 1.Tv, 2.Speaker, 3.Earphone, 4. 종료");
			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				if (vol == null || !(vol.getName().equalsIgnoreCase("tv"))) {
					vol = new Tv();
					break;
				} else
					break;
			case 2:
				if (vol == null || !(vol.getName().equalsIgnoreCase("speaker"))) {
					vol = new Speaker();
					break;
				} else
					break;
			case 3:
				if (vol == null || !(vol.getName().equalsIgnoreCase("earphone"))) {
					vol = new Earphone();
					break;
				} else
					break;
			case 4:
				break esc;
			}
			
			vol: while (true) {
				System.out.println("1. 볼륨올리기, 2. 볼륨내리기, 3. 나가기");
				int upDown = sc.nextInt();
				
				switch (upDown) {
				case 1:
					vol.volumeUp();
					break;
				case 2:
					vol.volumeDown();
					break;
				case 3:
					break vol;
				}
			}
		}
	}
}
