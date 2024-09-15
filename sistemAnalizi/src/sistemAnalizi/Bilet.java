package sistemAnalizi;

public class Bilet {
	private int fiyat;
	private String etkinlikAdi;
	private int saat;
	private int gun, ay, yil;
	
	public Bilet(int fiyat, String etkinlikAdi, int saat, int gun, int ay, int yil) {
		super();
		this.fiyat = fiyat;
		this.etkinlikAdi = etkinlikAdi;
		this.saat = saat;
		this.gun = gun;
		this.ay = ay;
		this.yil = yil;
	}
	@Override
	public String toString() {
		return "-> Etkinlik Adi: " + etkinlikAdi + " Fiyat: " + fiyat + "tl Saat:" + saat +":00"+ " Tarih: " + gun + "/"
				+ ay + "/" + yil + "\n";
	}
	public int getGun() {
		return gun;
	}
	public void setGun(int gun) {
		this.gun = gun;
	}
	public int getAy() {
		return ay;
	}
	public void setAy(int ay) {
		this.ay = ay;
	}
	public int getYil() {
		return yil;
	}
	public void setYil(int yil) {
		this.yil = yil;
	}
	public int getFiyat() {
		return fiyat;
	}
	public void setFiyat(int fiyat) {
		this.fiyat = fiyat;
	}
	public String getEtkinlikAdi() {
		return etkinlikAdi;
	}
	public void setEtkinlikAdi(String etkinlikAdi) {
		this.etkinlikAdi = etkinlikAdi;
	}
	public int getSaat() {
		return saat;
	}
	public void setSaat(int saat) {
		this.saat = saat;
	}
	
}
