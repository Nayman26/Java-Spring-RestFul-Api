package hello;

import java.util.ArrayList;

public class DonenHaber {
	private final boolean durum;
	private ArrayList<Haber> haberler;

	public DonenHaber(boolean durum, ArrayList<Haber> haberler) {
		super();
		this.durum = durum;
		this.haberler = haberler;
	}

	public boolean getDurum() {
		return durum;
	}

	public ArrayList<Haber> getHaberler() {
		return haberler;
	}

}
