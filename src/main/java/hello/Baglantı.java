package hello;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Baglantı {

	public static void ekleCıkar(String sorgu) {

	    		
		try {
			String db = "jdbc:mysql://localhost/yazlab2?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			Class.forName("com.mysql.jdbc.Driver");
			Connection baglanti = (Connection) DriverManager.getConnection(db, "root", "");
			try {
				Statement durum = (Statement) baglanti.createStatement();
				durum.execute(sorgu);
			} catch (Exception e) {
				System.err.println("Hata 1! ");
				System.err.println(e.getMessage());
			}
		} catch (Exception e) {
			System.err.println("Hata 2! ");
			System.err.println(e.getMessage());
		}
	}
//	public static byte byteImg[];
//	static String a;
	public static ArrayList<String> sorgula(String sorgu) {
		ArrayList<String> ds = new ArrayList<>();
		try {
			String db = "jdbc:mysql://localhost/yazlab2?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC\r\n";
			Class.forName("com.mysql.jdbc.Driver");
			Connection baglanti = (Connection) DriverManager.getConnection(db, "root", "");
			try {
				Statement durum = (Statement) baglanti.createStatement();
				ResultSet rs = (ResultSet) durum.executeQuery(sorgu);
				while (rs.next()) {
					for (int i = 1; i < 10; i++) {
						if (i == 4)
							ds.add(new String(rs.getBytes("resim")));
						else 
							ds.add(rs.getString(i).toString());
					}
				}
			} catch (Exception e) {
				System.err.println("Hata 1! ");
				System.err.println(e.getMessage());
			}
		} catch (Exception e) {
			System.err.println("Hata 2! ");
			System.err.println(e.getMessage());
		}
		return ds;
	}

}
