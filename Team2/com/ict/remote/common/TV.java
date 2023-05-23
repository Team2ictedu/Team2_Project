package Team2.com.ict.remote.common;

import java.util.Scanner;

public class TV {
	private String name = "TV";
	private int volume = 3;
	Scanner sc = new Scanner(System.in);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public void vol(int volumeUpDown) {
		if (volumeUpDown == 1) {
			if (volume == 10) {
				System.out.println("볼륨은 최대 10까지만 올릴 수 있습니다.");
				volume = 10;
			} else {
				this.volume++;
			}
		} else if (volumeUpDown == 2) {
			if (volume == 0) {
				System.out.println("볼륨은 최소 0까지만 내릴 수 있습니다.");
				volume = 0;
			} else {
				this.volume--;
			}
		}
		System.out.println("현재 볼륨: " + volume);
	}

}
