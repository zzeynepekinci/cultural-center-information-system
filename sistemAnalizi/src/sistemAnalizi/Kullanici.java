package sistemAnalizi;

public class Kullanici {
		
	private String isim;
	private String soyisim;
	private String sifre;
	private String mail;
	private int bakiye;
	private String cardNr;
	private int sonYil;
	private int sonAy;
	private int CCV;
	public Kullanici(String isim, String soyisim, String sifre, String mail,
			String cardNr, int sonYil, int sonAy, int cCV) {
		super();
		this.isim = isim;
		this.soyisim = soyisim;
		this.sifre = sifre;
		this.mail = mail;
		this.bakiye = 0;
		this.cardNr = cardNr;
		this.sonYil = sonYil;
		this.sonAy = sonAy;
		CCV = cCV;	
	}
	@Override
	public String toString() {
		return "\n Kullanici Bilgileri\n İsim: " + isim + " " +soyisim + "\n E-mail:" + mail
				+ "\n Kredi Kartı Numarası: " + cardNr + "\n Kart Ay/Yıl: " + sonAy + "/" + sonYil + " CCV: " + CCV ;
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
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public int getBakiye() {
		return bakiye;
	}
	public void setBakiye(int bakiye) {
		this.bakiye = bakiye;
	}
	public String getCardNr() {
		return cardNr;
	}
	public void setCardNr(String cardNr) {
		this.cardNr = cardNr;
	}
	public int getSonYil() {
		return sonYil;
	}
	public void setSonYil(int sonYil) {
		this.sonYil = sonYil;
	}
	public int getSonAy() {
		return sonAy;
	}
	public void setSonAy(int sonAy) {
		this.sonAy = sonAy;
	}
	public int getCCV() {
		return CCV;
	}
	public void setCCV(int cCV) {
		CCV = cCV;
	}
	
}
