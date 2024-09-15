package sistemAnalizi;

public class Etkinlik {
	private String bilgi;
	private String name;
	private int kontenjan;
	private int gun;
	private int ay;
	private int yil;
	private int sure;
	private int basSaat;
	private int bitSaat;
	private int fiyat;
	private byte[] resim;
	
	public Etkinlik(String name, String bilgi, int kontenjan, int gun, int ay, int yil, int basSaat, int bitSaat, int sure, int fiyat,byte[] resim) {
		super();
		this.name = name;
		this.bilgi = bilgi;
		this.kontenjan = kontenjan;
		this.gun = gun;
		this.ay = ay;
		this.yil = yil;
		this.sure = sure;
		this.basSaat = basSaat;
		this.bitSaat = bitSaat;
		this.fiyat = fiyat;
		this.resim = resim;
	}
	 @Override
	public String toString() {
		return "\n\n\n\n\n\n"+ name + "\n" + bilgi + "\nKontenjan: " + kontenjan + "\nTarih: " + gun + "/"
				+ ay + "/" + yil + "\nEtkinlik Süresi: " + sure + " saat" + "\nBaşlangıç Saati: " + basSaat + ":00"
				+ "\nFiyat: " + fiyat + "tl";
	}
	public byte[] getResim() {
		 return resim;
	 }
	public void setResim(byte[] resim) {
	    this.resim = resim;
	}
	public int getFiyat() {
		return fiyat;
	}
	public void setFiyat(int fiyat) {
		this.fiyat = fiyat;
	}
	public String getBilgi() {
		return bilgi;
	}
	public void setBilgi(String bilgi) {
		this.bilgi = bilgi;
	}
	public int getKontenjan() {
		return kontenjan;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBasSaat() {
		return basSaat;
	}
	public void setBasSaat(int basSaat) {
		this.basSaat = basSaat;
	}
	public int getBitSaat() {
		return bitSaat;
	}
	public void setBitSaat(int bitSaat) {
		this.bitSaat = bitSaat;
	}
	public void setKontenjan(int kontenjan) {
		this.kontenjan = kontenjan;
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
	public int getSure() {
		return sure;
	}
	public void setSure(int sure) {
		this.sure = sure;
	}	
}
