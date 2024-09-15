package sistemAnalizi;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Islemler {
	private static final String URL = "jdbc:mysql://localhost:3306/kultur_merkezi";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";
    private static final String INSERT_EVENT_SQL_BILETLER = "INSERT INTO biletler (etkinlikAdi, fiyat, gun, ay, yıl, saat, kullanici_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String INSERT_EVENT_SQL = "INSERT INTO etkinlik (name, bilgi, kontenjan, gun, ay, yıl, bas_saat, bit_saat, sure, fiyat, resim) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE_EVENT_SQL = "DELETE FROM etkinlik WHERE name = ?";
    private static final String DELETE_EXPIRED_EVENTS_SQL = "DELETE FROM etkinlik WHERE yıl < ? OR (yıl = ? AND ay < ?) OR (yıl = ? AND ay = ? AND gun <= ?)";
    private static final String SELECT_EVENTS_SQL = "SELECT * FROM etkinlik";
    private static final String INSERT_USER_SQL = "INSERT INTO kullanici (isim, soyisim, sifre, mail, bakiye, cardNr, expYear, expMonth, CCV) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_USER_BY_USERNAME_PASSWORD_SQL = "SELECT * FROM kullanici WHERE mail = ? AND sifre = ?";
    private static final String SELECT_KULLANICI_SQL = "SELECT * FROM Kullanici";
    private static final String INSERT_ADMIN_SQL = "INSERT INTO yonetici (mail, isim, soyisim, sifre) VALUES (?, ?, ?, ?)";
    private static final String SELECT_ADMIN_BY_USERNAME_PASSWORD_SQL = "SELECT * FROM yonetici WHERE mail = ? AND sifre = ?";
    private static final String CHECK_ADMIN_EXISTENCE_SQL = "SELECT * FROM yonetici";
    
    
    public void addBilet(Bilet bilet, int kullanici_id) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EVENT_SQL_BILETLER)) {
        	preparedStatement.setString(1, bilet.getEtkinlikAdi());
        	preparedStatement.setInt(2, bilet.getFiyat());
            preparedStatement.setInt(3, bilet.getGun());
            preparedStatement.setInt(4, bilet.getAy());
            preparedStatement.setInt(5, bilet.getYil());
            preparedStatement.setInt(6, bilet.getSaat());
            preparedStatement.setInt(7, kullanici_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } 
    public List<Bilet> getBiletIdsByKullaniciId(int kullanici_id) {
        List<Bilet> biletler = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "SELECT id, etkinlikAdi, fiyat, gun, ay, yıl, saat FROM biletler WHERE kullanici_id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, kullanici_id);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {             
                        String etkinlikAdi = resultSet.getString("etkinlikAdi");
                        int fiyat = resultSet.getInt("fiyat");
                        int gun = resultSet.getInt("gun");
                        int ay = resultSet.getInt("ay");
                        int yil = resultSet.getInt("yıl");
                        int saat = resultSet.getInt("saat");
                        Bilet b = new Bilet(fiyat, etkinlikAdi,  saat, gun, ay, yil);
                        biletler.add(b);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return biletler;
    }
    
    public void katilimciGoster(String etkinlikAdi) {
    	Object[] columns = {"Ad", "Soyad", "E-mail"};

        DefaultTableModel model = new DefaultTableModel(columns, 0);
        
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String sql = "SELECT kullanici.isim, kullanici.soyisim, kullanici.mail " +
                         "FROM biletler " +
                         "INNER JOIN kullanici ON biletler.kullanici_id = kullanici.kullanici_id " +
                         "WHERE biletler.etkinlikAdi = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, etkinlikAdi); 
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String ad = resultSet.getString("isim");
                String soyad = resultSet.getString("soyisim");
                String mail = resultSet.getString("mail");
                model.addRow(new Object[]{ad, soyad, mail});
            }
            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        JTable table = new JTable(model);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        JFrame frame = new JFrame();
        frame.setSize(300, 200);
        frame.setTitle("Etkinlik Katılımcıları");
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(EtkinlikSayfasiYonetici.class.getResource("/resimler/logo2.png")));
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        table.setGridColor(Color.GRAY);
        table.setBackground(Color.pink);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.addWindowListener((WindowListener) new WindowAdapter() {
	        @Override
	        public void windowClosing(WindowEvent e) {
	            try {
	                EtkinlikSayfasiYonetici ka = new EtkinlikSayfasiYonetici();
	                ka.setExtendedState(JFrame.MAXIMIZED_BOTH);
	                ka.setVisible(true);
	                frame.setVisible(false);
	            } catch (IOException ex) {
	                ex.printStackTrace();
	            }
	        }
	    });
    }
    
    public void deleteExpiredEvents() {
    	LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        int currentMonth = currentDate.getMonthValue();
        int currentDay = currentDate.getDayOfMonth();
        
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EXPIRED_EVENTS_SQL)) {
            preparedStatement.setInt(1, currentYear);
            preparedStatement.setInt(2, currentYear);
            preparedStatement.setInt(3, currentMonth);
            preparedStatement.setInt(4, currentYear);
            preparedStatement.setInt(5, currentMonth);
            preparedStatement.setInt(6, currentDay);
            
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " etkinlik silindi.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEvent(String name) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EVENT_SQL)) {
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
            System.out.println("Etkinlik basariyla silindi.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addEvent(Etkinlik event) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EVENT_SQL)) {
        	preparedStatement.setString(1, event.getName());
            preparedStatement.setString(2, event.getBilgi());
            preparedStatement.setInt(3, event.getKontenjan());
            preparedStatement.setInt(4, event.getGun());
            preparedStatement.setInt(5, event.getAy());
            preparedStatement.setInt(6, event.getYil());
            preparedStatement.setInt(7, event.getBasSaat());
            preparedStatement.setInt(8, event.getBitSaat());
            preparedStatement.setInt(9, event.getSure());
            preparedStatement.setInt(10, event.getFiyat());
            preparedStatement.setBytes(11, event.getResim());
            
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int getEventIdByName(String eventName) {
        int eventId = -1; 

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "SELECT id FROM etkinlik WHERE name = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, eventName);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        eventId = resultSet.getInt("id");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return eventId;
    }

    public List<Etkinlik> getAllEvents() {
        List<Etkinlik> events = new ArrayList<>();
        
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EVENTS_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String bilgi = resultSet.getString("bilgi");
                int kontenjan = resultSet.getInt("kontenjan");
                int gun = resultSet.getInt("gun");
                int ay = resultSet.getInt("ay");
                int yil = resultSet.getInt("yıl");
                int basSaat = resultSet.getInt("bas_saat");
                int bitSaat = resultSet.getInt("bit_saat");
                int sure = resultSet.getInt("sure");
                int fiyat = resultSet.getInt("fiyat");
                byte[] resim = resultSet.getBytes("resim");

                Etkinlik event = new Etkinlik(name, bilgi, kontenjan, gun, ay, yil, basSaat, bitSaat, sure, fiyat,resim);
                events.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return events;
    }
    public boolean loginUser(String mail, String sifre) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_USERNAME_PASSWORD_SQL)) {
            preparedStatement.setString(1, mail);
            preparedStatement.setString(2, sifre);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e1){
            e1.printStackTrace();
            return false;
        }
    }
    public void addUser(Kullanici user) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)) {
            preparedStatement.setString(1, user.getIsim());
            preparedStatement.setString(2, user.getSoyisim());          
            preparedStatement.setString(3, user.getSifre());
            preparedStatement.setString(4, user.getMail());
            preparedStatement.setInt(5, user.getBakiye());
            preparedStatement.setString(6, user.getCardNr());
            preparedStatement.setInt(7, user.getSonYil());
            preparedStatement.setInt(8, user.getSonAy());         
            preparedStatement.setInt(9, user.getCCV());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public int getKullaniciBakiye(String mail) {
        int kulBakiye = 0;

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "SELECT bakiye FROM kullanici WHERE mail = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, mail);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        kulBakiye = resultSet.getInt("bakiye");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return kulBakiye;
    }

    public int getKullaniciId(String mail) {
        int id = 0;

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "SELECT kullanici_id FROM kullanici WHERE mail = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, mail);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        id = resultSet.getInt("kullanici_id");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }
    public void bakiyeGuncelle(String mail, int bakiye) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "UPDATE kullanici SET bakiye = ? WHERE mail = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, bakiye);
                preparedStatement.setString(2, mail);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Bakiye güncellendi.");
                } else {
                    System.out.println("Bakiye güncelleme işlemi başarısız oldu.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
       
    public Kullanici getKullaniciBilgileri(String mail) {
    	String isim, soyisim, cardNr, sifre;
    	int expYear, expMonth, ccv, bakiye;
    	Kullanici kullanici = new Kullanici(null, null, null, null, null, 0, 0, 0);
        
    	try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
    		String sql = "SELECT expYear, expMonth, ccv, bakiye, isim, soyisim, cardNr, sifre FROM kullanici WHERE mail = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, mail);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        expYear = resultSet.getInt("expYear");
                        expMonth = resultSet.getInt("expMonth");
                        ccv = resultSet.getInt("ccv");
                        bakiye = resultSet.getInt("bakiye");
                        isim = resultSet.getString("isim");
                        soyisim = resultSet.getString("soyisim");
                        cardNr = resultSet.getString("cardNr");
                        sifre = resultSet.getString("sifre");
                        kullanici.setIsim(isim);
                        kullanici.setSoyisim(soyisim);
                        kullanici.setSifre(sifre);
                        kullanici.setMail(mail);
                        kullanici.setBakiye(bakiye);
                        kullanici.setCardNr(cardNr);
                        kullanici.setSonYil(expYear);
                        kullanici.setSonAy(expMonth);
                        kullanici.setCCV(ccv);                       		                     
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return kullanici;
    }
    public List<Kullanici> getAllUsers() {
        List<Kullanici> kullanicilar = new ArrayList<>();
        
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_KULLANICI_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            
            while (resultSet.next()) {
                String name = resultSet.getString("isim");
                String surname = resultSet.getString("soyisim");
                String sifre = resultSet.getString("sifre");
                String mail = resultSet.getString("mail");
                String cardNr = resultSet.getString("cardNr");
                int ay = resultSet.getInt("expMonth");
                int yil = resultSet.getInt("expYear");
                int ccv = resultSet.getInt("ccv");

                Kullanici kul = new Kullanici(name, surname, sifre, mail, cardNr, yil, ay, ccv);
                kullanicilar.add(kul);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return kullanicilar;
    }
    public void displayUsersInTable(List<Kullanici> users,JTable userTable) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("İsim");
        model.addColumn("Soyisim");
        model.addColumn("Mail");

        for (Kullanici user : users) {
            model.addRow(new Object[]{user.getIsim(),user.getSoyisim(), user.getMail()});
        }

        userTable.setModel(model);
    }
    public boolean isAdminExists() {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(CHECK_ADMIN_EXISTENCE_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addAdmin(String mail, String isim, String soyisim, String sifre) {
    	if (isAdminExists()) {
            System.out.println("Zaten bir yönetici var.");
            return false;
        }

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ADMIN_SQL)) {
            preparedStatement.setString(1, mail);
            preparedStatement.setString(2, isim);
            preparedStatement.setString(3, soyisim);
            preparedStatement.setString(4, sifre);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean loginAdmin(String mail, String sifre) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ADMIN_BY_USERNAME_PASSWORD_SQL)) {
            preparedStatement.setString(1, mail);
            preparedStatement.setString(2, sifre);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

