package Team2.com.ict.remote.extends_dynamic;

public class Remote {
	private String name = "";
	private int volume = 3;
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
	public int vol(int volume) {
		int a = 0;
		if(volume == 1) {
			System.out.println("볼륨증가");
			a += 1;
		} else if(volume ==2) {
			System.out.println("볼륨감소");
			a -= 1;
		}
		return a;
	}

}
