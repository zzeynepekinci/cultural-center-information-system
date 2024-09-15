package sistemAnalizi;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import javax.swing.SwingConstants;

public class KullaniciKayit extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField passwordField;

	String url = "jdbc:mysql://localhost:3306/kultur_merkezi";
    String username = "root";
    String password1 = "123456";
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KullaniciKayit frame = new KullaniciKayit();
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public KullaniciKayit() throws IOException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(KullaniciKayit.class.getResource("/resimler/logo2.png")));
		setTitle("Yıldız Kültür Merkezi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 488);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setOpaque(false);
		
		JButton btnNewButton_3 = new JButton("Geri Dön") {
			private static final long serialVersionUID = 1L;
			protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(Color.pink); 
                } else {
                    g.setColor(getBackground());
                }
                g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                g.setColor(getForeground());
                g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
            }
		};
		btnNewButton_3.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Anasayfa m;
				try {
					m = new Anasayfa();
					m.setExtendedState(JFrame.MAXIMIZED_BOTH);
					m.show();
				} catch (IOException e1) {
					e1.printStackTrace();
				}							
			}
			
		});
		btnNewButton_3.setForeground(Color.WHITE);
	    btnNewButton_3.setFocusPainted(false);
	    btnNewButton_3.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
	    btnNewButton_3.setOpaque(true);
	    btnNewButton_3.setBorderPainted(false);
	    btnNewButton_3.setContentAreaFilled(false);		
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton_3.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton_3.setBackground(new Color(255, 89, 172));
		//btnNewButton_6.setBounds(263, 381, 2000, 1000);
		btnNewButton_3.setBounds(1300, 77, 150, 65);
		contentPane.add(btnNewButton_3);
		
		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("New JGoodies label");
		BufferedImage img = ImageIO.read(new File("C:\\\\Users\\\\iclal\\\\eclipse-workspace\\\\sistemAnalizi\\\\src\\\\resimler\\\\logo.png"));

        int newWidth = 400; 
        int newHeight = 250; 
        Image scaledImg = img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        ImageIcon icon = new ImageIcon(scaledImg);
        
		lblNewJgoodiesLabel.setIcon(icon);
		
		lblNewJgoodiesLabel.setBounds(-79, 0, 319, 214);
		contentPane.add(lblNewJgoodiesLabel);
		
		JLabel lblNewLabel_3 = new JLabel("YILDIZ KÜLTÜR MERKEZİ");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3.setForeground(new Color(117, 117, 117));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblNewLabel_3.setBounds(222, 80, 550, 71);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_1_1 = new JLabel("Kullanıcı Giriş Sayfası");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1.setForeground(new Color(117, 117, 117));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(232, 139, 315, 33);
		contentPane.add(lblNewLabel_1_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(550, 250, 600, 600);
		panel_1.setOpaque(false);
		contentPane.add(panel_1);
		
		panel_1.setVisible(true);
		
		JLabel lblNewLabel = new JLabel("e-mail");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setForeground(new Color(117, 117, 117));
		lblNewLabel.setBounds(10, 65, 54, 25);
		
		JLabel lblNewLabel_2 = new JLabel("İsim");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setForeground(new Color(117, 117, 117));
		lblNewLabel_2.setBounds(10, 8, 80, 25);
		panel_1.setLayout(null);
		panel_1.add(lblNewLabel);
		panel_1.add(lblNewLabel_2);
		
		JTextArea nameArea = new JTextArea();
		nameArea.setFont(new Font("Monospaced", Font.PLAIN, 17));
		nameArea.setForeground(new Color(82, 82, 82));
		nameArea.setOpaque(false);
		nameArea.setBounds(5, 1, 200, 30);

		JPanel roundedPanel1 = new JPanel() {
			private static final long serialVersionUID = 1L;
			@Override
		    protected void paintComponent(Graphics g) {
		        super.paintComponent(g);
		        Dimension arcs = new Dimension(15, 15);
		        int width = getWidth();
		        int height = getHeight();
		        Graphics2D graphics = (Graphics2D) g;
		        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		        graphics.setColor(getBackground());
		        graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
		        graphics.setColor(Color.WHITE);
		        graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
		    }
		};
		roundedPanel1.setLayout(null);
		roundedPanel1.setOpaque(false);
		roundedPanel1.add(nameArea);
		roundedPanel1.setBounds(10, 35, 200, 30);
		panel_1.add(roundedPanel1);
		
		JTextArea emailArea = new JTextArea();
		emailArea.setFont(new Font("Monospaced", Font.PLAIN, 17));
		emailArea.setForeground(new Color(82, 82, 82));
		emailArea.setOpaque(false);
		emailArea.setBounds(5, 1, 420, 30);

		JPanel roundedPanel = new JPanel() {
			private static final long serialVersionUID = 1L;
			@Override
		    protected void paintComponent(Graphics g) {
		        super.paintComponent(g);
		        Dimension arcs = new Dimension(15, 15);
		        int width = getWidth();
		        int height = getHeight();
		        Graphics2D graphics = (Graphics2D) g;
		        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		        graphics.setColor(getBackground());
		        graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
		        graphics.setColor(Color.WHITE);
		        graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
		    }
		};
		roundedPanel.setLayout(null);
		roundedPanel.setOpaque(false);
		roundedPanel.add(emailArea);
		roundedPanel.setBounds(10, 90, 420, 30);
		panel_1.add(roundedPanel);
		
		JLabel lblSurname = new JLabel("Soyisim");
		lblSurname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSurname.setForeground(new Color(117, 117, 117));
		lblSurname.setBounds(230, 8, 80, 24);
		panel_1.add(lblSurname);
		
		JTextArea surnameArea = new JTextArea();
		surnameArea.setFont(new Font("Monospaced", Font.PLAIN, 17));
		surnameArea.setForeground(new Color(82, 82, 82));
		surnameArea.setOpaque(false);
		surnameArea.setBounds(5, 1, 200, 30);

		JPanel roundedPanel2 = new JPanel() {
			private static final long serialVersionUID = 1L;
			@Override
		    protected void paintComponent(Graphics g) {
		        super.paintComponent(g);
		        Dimension arcs = new Dimension(15, 15);
		        int width = getWidth();
		        int height = getHeight();
		        Graphics2D graphics = (Graphics2D) g;
		        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		        graphics.setColor(getBackground());
		        graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
		        graphics.setColor(Color.WHITE);
		        graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
		    }
		};
		roundedPanel2.setLayout(null);
		roundedPanel2.setOpaque(false);
		roundedPanel2.add(surnameArea);
		roundedPanel2.setBounds(230, 35, 200, 30);
		panel_1.add(roundedPanel2);
		
		JTextArea kartNoArea = new JTextArea();
		kartNoArea.setFont(new Font("Monospaced", Font.PLAIN, 17));
		kartNoArea.setForeground(new Color(82, 82, 82));
		kartNoArea.setOpaque(false);
		kartNoArea.setBounds(5, 1, 420, 30);

		JPanel roundedPanel11 = new JPanel() {
			private static final long serialVersionUID = 1L;
			@Override
		    protected void paintComponent(Graphics g) {
		        super.paintComponent(g);
		        Dimension arcs = new Dimension(15, 15);
		        int width = getWidth();
		        int height = getHeight();
		        Graphics2D graphics = (Graphics2D) g;
		        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		        graphics.setColor(getBackground());
		        graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
		        graphics.setColor(Color.WHITE);
		        graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
		    }
		};
		roundedPanel11.setLayout(null);
		roundedPanel11.setOpaque(false);
		roundedPanel11.add(kartNoArea);
		roundedPanel11.setBounds(10, 204, 420, 30);
		panel_1.add(roundedPanel11);
		
		JTextArea ccvArea = new JTextArea();
		ccvArea.setFont(new Font("Monospaced", Font.PLAIN, 17));
		ccvArea.setForeground(new Color(82, 82, 82));
		ccvArea.setOpaque(false);
		ccvArea.setBounds(5, 1, 128, 30);

		JPanel roundedPanel111 = new JPanel() {
			private static final long serialVersionUID = 1L;
			@Override
		    protected void paintComponent(Graphics g) {
		        super.paintComponent(g);
		        Dimension arcs = new Dimension(15, 15);
		        int width = getWidth();
		        int height = getHeight();
		        Graphics2D graphics = (Graphics2D) g;
		        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		        graphics.setColor(getBackground());
		        graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
		        graphics.setColor(Color.WHITE);
		        graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
		    }
		};
		roundedPanel111.setLayout(null);
		roundedPanel111.setOpaque(false);
		roundedPanel111.add(ccvArea);
		roundedPanel111.setBounds(10, 262, 128, 30);
		panel_1.add(roundedPanel111);
		
		JLabel lblNewLabel_1 = new JLabel("Kredi Kartı Numarası");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setForeground(new Color(117, 117, 117));
		lblNewLabel_1.setBounds(10, 172, 200, 36);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3_1 = new JLabel("CCV");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3_1.setForeground(new Color(117, 117, 117));
		lblNewLabel_3_1.setBounds(10, 235, 54, 30);
		panel_1.add(lblNewLabel_3_1);
		
		JTextArea ayArea = new JTextArea();
		ayArea.setFont(new Font("Monospaced", Font.PLAIN, 17));
		ayArea.setForeground(new Color(82, 82, 82));
		ayArea.setOpaque(false);
		ayArea.setBounds(5, 1, 128, 30);

		JPanel roundedPanel3 = new JPanel() {
			private static final long serialVersionUID = 1L;
			@Override
		    protected void paintComponent(Graphics g) {
		        super.paintComponent(g);
		        Dimension arcs = new Dimension(15, 15);
		        int width = getWidth();
		        int height = getHeight();
		        Graphics2D graphics = (Graphics2D) g;
		        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		        graphics.setColor(getBackground());
		        graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
		        graphics.setColor(Color.WHITE);
		        graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
		    }
		};
		roundedPanel3.setLayout(null);
		roundedPanel3.setOpaque(false);
		roundedPanel3.add(ayArea);
		roundedPanel3.setBounds(156, 260, 128, 30);
		panel_1.add(roundedPanel3);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("Ay");
		lblNewLabel_3_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3_1_1.setForeground(new Color(117, 117, 117));
		lblNewLabel_3_1_1.setBounds(156, 233, 128, 30);
		panel_1.add(lblNewLabel_3_1_1);
		
		JLabel lblNewLabel_3_1_2 = new JLabel("Yıl");
		lblNewLabel_3_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3_1_2.setForeground(new Color(117, 117, 117));
		lblNewLabel_3_1_2.setBounds(302, 233, 128, 30);
		panel_1.add(lblNewLabel_3_1_2);
		
		JTextArea yılArea = new JTextArea();
		yılArea.setFont(new Font("Monospaced", Font.PLAIN, 17));
		yılArea.setForeground(new Color(82, 82, 82));
		yılArea.setOpaque(false);
		yılArea.setBounds(5, 1, 128, 30);

		JPanel roundedPanel31 = new JPanel() {
			private static final long serialVersionUID = 1L;
			@Override
		    protected void paintComponent(Graphics g) {
		        super.paintComponent(g);
		        Dimension arcs = new Dimension(15, 15);
		        int width = getWidth();
		        int height = getHeight();
		        Graphics2D graphics = (Graphics2D) g;
		        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		        graphics.setColor(getBackground());
		        graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
		        graphics.setColor(Color.WHITE);
		        graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
		    }
		};
		roundedPanel31.setLayout(null);
		roundedPanel31.setOpaque(false);
		roundedPanel31.add(yılArea);
		roundedPanel31.setBounds(302, 260, 128, 30);
		panel_1.add(roundedPanel31);
		
		JLabel lblNewLabel_3_1_3 = new JLabel("Password");
		lblNewLabel_3_1_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3_1_3.setForeground(new Color(117, 117, 117));
		lblNewLabel_3_1_3.setBounds(10, 118, 93, 33);
		panel_1.add(lblNewLabel_3_1_3);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passwordField.setBounds(10, 147, 420, 30);
		passwordField.setBackground(new Color(240, 241, 242));
		panel_1.add(passwordField);
		
		JLabel kayit = new JLabel("Bu e-mail adresi zaten mevcut.");
		kayit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		kayit.setForeground(new Color(117, 117, 117));
		kayit.setBounds(220, 350, 300, 30);
		panel_1.add(kayit);
		kayit.setVisible(false);
		JLabel kayit2 = new JLabel("Bilgilerinizi doğru girdiğinizden emin olun.");
		kayit2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		kayit2.setForeground(new Color(117, 117, 117));
		kayit2.setBounds(150, 350, 400, 30);
		panel_1.add(kayit2);
		kayit2.setVisible(false);
		JButton btnNewButton_2 = new JButton("Kaydol") {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(Color.pink); 
                } else {
                    g.setColor(getBackground());
                }
                g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                g.setColor(getForeground());
                g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
            }
		};
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
				  int ccv = Integer.parseInt(ccvArea.getText()); 
				  char[] password = passwordField.getPassword();
				  String passWord2 = new String(password);
				  int ay1 = Integer.parseInt(ayArea.getText()); 
				  int yil = Integer.parseInt(yılArea.getText()); 
				  
				  try (Connection connection = DriverManager.getConnection(url, username, password1)) {
			            System.out.println("Veritabanına bağlanıldı.");
			           
			                String query = "SELECT COUNT(*) AS count FROM kullanici WHERE mail = ?";
			                
			                PreparedStatement statement = connection.prepareStatement(query);
			                statement.setString(1, emailArea.getText());
			                
			                ResultSet resultSet = statement.executeQuery();
			                resultSet.next();
			                int count = resultSet.getInt("count");

			                if (count > 0) {
			                	kayit.setVisible(true);
			                    System.out.println("Bu e-posta adresi zaten mevcut.");
			                } else {
			                	kayit2.setVisible(false);
					            Islemler kul = new Islemler();					            
					            Kullanici kullanici = new Kullanici(nameArea.getText(), surnameArea.getText(), passWord2, emailArea.getText(), kartNoArea.getText(),yil,ay1,ccv);
					            kul.addUser(kullanici);
					            kayit.setVisible(true);    				            
					            System.out.println("Kullanici eklendi: " + kullanici.getIsim());
					            try {
					            	KullaniciAnaSayfasi ka;
									ka = new KullaniciAnaSayfasi(emailArea.getText());
									ka.setExtendedState(JFrame.MAXIMIZED_BOTH);
									ka.setVisible(true);
								} catch (IOException e1) {
									e1.printStackTrace();
									
								}				 							        
			                }			         
			        } catch (SQLException e1) {
			            System.out.println("Veritabanına bağlanırken hata oluştu: " + e1.getMessage());
			        }				
			}
		});
		btnNewButton_2.setForeground(Color.WHITE);
	    btnNewButton_2.setFocusPainted(false);
	    btnNewButton_2.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
	    btnNewButton_2.setOpaque(true);
	    btnNewButton_2.setBorderPainted(false);
	    btnNewButton_2.setContentAreaFilled(false);
		btnNewButton_2.setFont(new Font("Arial Black", Font.BOLD, 17));
		btnNewButton_2.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton_2.setBackground(new Color(255, 89, 172));
		btnNewButton_2.setBounds(320, 310, 110, 55);
		panel_1.add(btnNewButton_2);
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel arka = new JLabel();
		arka.setBackground(new Color(255, 185, 220));
		arka.setForeground(new Color(255, 128, 192));
		ImageIcon img2 = new ImageIcon("C:\\Users\\iclal\\eclipse-workspace\\sistemAnalizi\\src\\resimler\\kullanici.jpg");
		
		Image scaledImg2 = img2.getImage().getScaledInstance(10000, 1000, Image.SCALE_SMOOTH);
		img2 = new ImageIcon(scaledImg2);
		arka.setBounds(0, 0, 10000, 1000);arka.setIcon(img2);
		contentPane.add(arka);
			
	}
}
