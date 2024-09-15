package sistemAnalizi;

public class Yonetici {
	private String mail;
	private String isim;	
	private String soyisim;
	private String sifre;

	public Yonetici(String mail, String isim, String soyisim, String sifre) {
		super();
		this.mail = mail;
		this.isim = isim;
		this.soyisim = soyisim;
		this.sifre = sifre;		
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getIsim() {
		return isim;
	}
	public void setIsim(String isim) {
		this.isim = isim;
	}
	public String getSoyisim() {
		return soyisim;
	}
	public void setSoyisim(String soyisim) {
		this.soyisim = soyisim;
	}
	public String getSifre() {
		return sifre;
	}
	public void setSifre(String sifre) {
		this.sifre = sifre;
	}
	
}

