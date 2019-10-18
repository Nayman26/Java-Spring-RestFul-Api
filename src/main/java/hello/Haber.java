package hello;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Haber {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String baslik;
	private String icerik;
	private String resim;
	private String tur;
	private String tarih;
	private int begenme;
	private int begenmeme;
	private int goruntulenme;

	public Haber() {
	}

	public Haber(Integer id, String baslik, String icerik, String resim, String tur, String tarih, int begenme,
			int begenmeme, int goruntulenme) {
		this.id = id;
		this.baslik = baslik;
		this.icerik = icerik;
		this.resim = resim;
		this.tur = tur;
		this.tarih = tarih;
		this.begenme = begenme;
		this.begenmeme = begenmeme;
		this.goruntulenme = goruntulenme;
	}

	public Integer getId() {
		return id;
	}

	public String getBaslik() {
		return baslik;
	}

	public String getIcerik() {
		return icerik;
	}

	public String getResim() {
		return resim;
	}

	public String getTur() {
		return tur;
	}

	public String getTarih() {
		return tarih;
	}

	public int getBegenme() {
		return begenme;
	}

	public int getBegenmeme() {
		return begenmeme;
	}

	public int getGoruntulenme() {
		return goruntulenme;
	}

}
