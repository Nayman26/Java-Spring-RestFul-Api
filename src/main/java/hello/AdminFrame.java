package hello;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import hello.FileTypeFilter;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class AdminFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textBaslık;
	private File file;
//	private InputStream is;
//	private String uzantı;
//	public static byte byteImg[];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		// Application.main(args);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminFrame frame = new AdminFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 735, 497);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblBaslık = new JLabel("HABER BASLIK :");
		lblBaslık.setBounds(105, 14, 99, 14);
		contentPane.add(lblBaslık);

		textBaslık = new JTextField();
		textBaslık.setBounds(214, 11, 293, 20);
		contentPane.add(textBaslık);
		textBaslık.setColumns(10);

		JLabel lblIcerik = new JLabel("İCERİK :");
		lblIcerik.setBounds(50, 46, 46, 14);
		contentPane.add(lblIcerik);

		JTextArea textIcerik = new JTextArea();
		textIcerik.setBounds(50, 71, 511, 360);
		contentPane.add(textIcerik);
		
		JScrollPane scrollPane = new JScrollPane(textIcerik);
		scrollPane.setBounds(50, 71, 511, 360);
		contentPane.add(scrollPane);
		
		JLabel lblHaberTr = new JLabel("Haber Türü:");
		lblHaberTr.setBounds(571, 71, 71, 14);
		contentPane.add(lblHaberTr);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(571, 90, 112, 20);
		contentPane.add(comboBox);
		comboBox.addItem("Seciniz");
		comboBox.addItem("gundem");
		comboBox.addItem("spor");
		comboBox.addItem("egitim");
		comboBox.addItem("ekonomi");

		JButton btnResimEkle = new JButton("Resim Ekle");
		btnResimEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser resimAc = new JFileChooser(new File("C:\\xampp\\htdocs\\img"));
				resimAc.setFileFilter(new FileTypeFilter(".jpeg", "Resim Dosyası"));
				resimAc.setFileFilter(new FileTypeFilter(".png", "Resim Dosyası"));
				resimAc.setFileFilter(new FileTypeFilter(".PNG", "Resim Dosyası"));
				resimAc.setFileFilter(new FileTypeFilter(".jpg", "Resim Dosyası"));

				if (resimAc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					file = resimAc.getSelectedFile();
//						uzantı = file.getAbsolutePath();
//						is = new FileInputStream(new File(uzantı));
				}
			}
		});

		btnResimEkle.setBounds(571, 220, 112, 23);
		contentPane.add(btnResimEkle);
		
		JButton btnHaberEkle = new JButton("Haber Ekle");
		btnHaberEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String db = "jdbc:mysql://localhost/yazlab2?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
					Class.forName("com.mysql.jdbc.Driver");
					Connection baglanti = (Connection) DriverManager.getConnection(db, "root", "");
					try {
						PreparedStatement ps = baglanti.prepareStatement("INSERT INTO `haber`(`baslik`, `icerik` , `resim`, `tur`) VALUES(?,?,?,?)");
						ps.setString(1,textBaslık.getText());
						ps.setString(2,textIcerik.getText());
						ps.setString(3,file.getName());
						ps.setString(4,comboBox.getSelectedItem().toString());
						ps.execute();
					} catch (Exception e) {
						System.err.println("Hata 1! ");
						System.err.println(e.getMessage());
					}
				} catch (Exception e) { 
					System.err.println("Hata 2! ");
					System.err.println(e.getMessage());
				}
			}
		});
		btnHaberEkle.setBounds(571, 408, 112, 23);
		contentPane.add(btnHaberEkle);
	}
}
