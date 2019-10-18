package hello;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import hello.Haber;
import hello.HaberRepository;

@Controller 
@RequestMapping(path = "/haberler") // 
public class MainController {
	@Autowired
	private HaberRepository haberRepository;

	@GetMapping(path = "/begen")
	public @ResponseBody Optional<Haber> postBegen(@RequestParam int id,
			@RequestParam(required = false, defaultValue = "true") boolean onay) {
		if (onay)
			Baglantı.ekleCıkar("UPDATE `haber` SET `begenme`=`begenme`+1 WHERE id=" + id);
		else
			Baglantı.ekleCıkar("UPDATE `haber` SET `begenme`=`begenme`-1 WHERE id=" + id);
		return haberRepository.findById(id);
	}

	@GetMapping(path = "/begenme")
	public @ResponseBody Optional<Haber> postBegenme(@RequestParam int id,
			@RequestParam(required = false, defaultValue = "true") boolean onay) {
		if (onay)
			Baglantı.ekleCıkar("UPDATE `haber` SET `begenmeme`=`begenmeme`+1 WHERE id=" + id);
		else
			Baglantı.ekleCıkar("UPDATE `haber` SET `begenmeme`=`begenmeme`-1 WHERE id=" + id);

		return haberRepository.findById(id);
	}
	
	@GetMapping(path = "/goruntulenme")
	public @ResponseBody Optional<Haber> postGoruntulenme(@RequestParam int id) {
			Baglantı.ekleCıkar("UPDATE `haber` SET `goruntulenme`=`goruntulenme`+1 WHERE id=" + id);
		return haberRepository.findById(id);
	}

	@GetMapping(path = "/filtrele")
	public @ResponseBody DonenHaber HaberFiltrele(@RequestParam(required = false, defaultValue = "1000") int limit,
			@RequestParam(required = false, defaultValue = "0") int offset,
			@RequestParam(required = false) String tur) {
		ArrayList<String> ds = new ArrayList<>();
		String sorgu = "Select * From `haber` ";
		if (tur != null)
			sorgu += "where tur= '" + tur + "'";
		sorgu += " order by id desc LIMIT " + limit + " OFFSET " + offset;
		ds = Baglantı.sorgula(sorgu);
		ArrayList<Haber> hler = new ArrayList<Haber>();
		for (int i = 0, a = 0; a < ds.size() / 9; a++) {
			Haber h = new Haber(Integer.parseInt(ds.get(i)), ds.get(i + 1), ds.get(i + 2), ds.get(i + 3), ds.get(i + 4),
					ds.get(i + 5), Integer.parseInt(ds.get(i + 6)), Integer.parseInt(ds.get(i + 7)),
					Integer.parseInt(ds.get(i + 8)));
			hler.add(h);
			i += 9;
		}
		return new DonenHaber(true, hler);
		// return hler;
	}
}
