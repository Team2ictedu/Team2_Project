package Team2.com.ict.remote.interfaces;

public class Earphone implements Volume{
	public String name = "";
	public int volume = 0;
	
	public Earphone() {
		this.name = "Earphone";
		this.volume = 3;
	}
	
	@Override
	public void volumeUp() {
		if (this.volume == 10) {
			System.out.printf("%s의 볼륨이 %d입니다. 더이상 올릴 수 없음%n", this.name, this.volume);
		} else {
			this.volume++;
			System.out.printf("%s의 볼륨이 증가되었습니다. 현재 볼륨이 %d입니다%n", this.name, this.volume);
		}
	}

	@Override
	public void volumeDown() {
		if (this.volume == 0) {
			System.out.printf("%s의 볼륨이 %d입니다. 더이상 낮출수 없음%n", this.name, this.volume);
		} else {
			this.volume--;
			System.out.printf("%s의 볼륨이 감소되었습니다. 현재 볼륨이 %d입니다%n", this.name, this.volume);
		}

	}

	@Override
	public String getName() {
		return name;
	}
}
