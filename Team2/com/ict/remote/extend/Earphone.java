package Team2.com.ict.remote.extend;

public class Earphone extends Remote {
	private int volume = 3;
	
	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume += super.vol(volume);	
		if(this.volume >= 10) {
			this.volume = 10;
			System.out.println("최대 볼륨은 10입니다. 더이상 올릴 수 없습니다.");
		} else if(this.volume <= 0) {
			this.volume = 0;
			System.out.println("최소 볼륨은 0입니다. 더이상 내릴 수 없습니다.");
		} 		
		System.out.println("현재 볼륨: " + getVolume());
	}
}