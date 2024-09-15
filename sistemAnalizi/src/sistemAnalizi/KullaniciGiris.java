package sistemAnalizi;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.SwingConstants;

public class KullaniciGiris extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField passwordField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KullaniciGiris frame = new KullaniciGiris();
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			        
			        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

			        frame.setResizable(false);

			        frame.getContentPane().setBackground(Color.WHITE);

			        frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public KullaniciGiris() throws IOException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(KullaniciGiris.class.getResource("/resimler/logo2.png")));
		setTitle("Yıldız Kültür Merkezi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 887, 496);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBounds(0, 0, 10000, 1000);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
		
		JTextArea emailArea = new JTextArea();
		emailArea.setFont(new Font("Monospaced", Font.PLAIN, 17));
		emailArea.setOpaque(false);
		emailArea.setBounds(10, 12, 361, 52);

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

		roundedPanel.setBounds(611, 300, 361, 52);

		contentPane.add(roundedPanel);
		JLabel lblNewLabel = new JLabel("e-mail");
		lblNewLabel.setForeground(new Color(117, 117, 117));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(611, 260, 93, 40);
		contentPane.add(lblNewLabel);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		passwordField.setBounds(611, 400, 361, 52);
		passwordField.setBackground(new Color(240, 241, 242));
		contentPane.add(passwordField);
	
		JLabel lblNewLabel_3_1_3 = new JLabel("Password");
		lblNewLabel_3_1_3.setForeground(new Color(117, 117, 117));
		lblNewLabel_3_1_3.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_3_1_3.setBounds(611, 367, 139, 33);
		contentPane.add(lblNewLabel_3_1_3);
		
		JLabel lblNewLabel_3 = new JLabel("YILDIZ KÜLTÜR MERKEZİ");
		lblNewLabel_3.setForeground(new Color(117, 117, 117));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblNewLabel_3.setBounds(235, 70, 550, 71);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("New JGoodies label");
		BufferedImage img = ImageIO.read(new File("C:\\\\Users\\\\iclal\\\\eclipse-workspace\\\\sistemAnalizi\\\\src\\\\resimler\\\\logo.png"));

        int newWidth = 400;
        int newHeight = 250;
        Image scaledImg = img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        ImageIcon icon = new ImageIcon(scaledImg);
       
		lblNewJgoodiesLabel.setIcon(icon);
		
		lblNewJgoodiesLabel.setBounds(-79, 0, 319, 214);
		contentPane.add(lblNewJgoodiesLabel);
		
		JLabel lblNewLabel_4 = new JLabel("E-mail veya şifre yanlış. Tekrar deneyiniz.");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4.setForeground(new Color(106, 106, 106));
		lblNewLabel_4.setBounds(697, 540, 300, 20);
		lblNewLabel_4.setVisible(false);
		contentPane.add(lblNewLabel_4);
		
		JButton btnNewButton_2 = new JButton("Giriş Yap") {
			private static final long serialVersionUID = 1L;
			protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(Color.PINK);
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
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				char[] password23 = passwordField.getPassword();
				String passWord2 = new String(password23);
				Islemler kullaniciDAO = new Islemler();
				if(kullaniciDAO.loginUser(emailArea.getText(), passWord2 )) {
					System.out.println("basarili");
					
					KullaniciAnaSayfasi k2;
					try {
						k2 = new KullaniciAnaSayfasi(emailArea.getText());
						k2.setExtendedState(JFrame.MAXIMIZED_BOTH);
						k2.show();
					} catch (IOException e1) {
						e1.printStackTrace();
					}

					setVisible(false);
				}
				else {
					lblNewLabel_4.setVisible(true);
					System.out.println("basarisiz");
				}
				 
			}
		});
		btnNewButton_2.setForeground(Color.WHITE);
	    btnNewButton_2.setFocusPainted(false);
	    btnNewButton_2.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
	    btnNewButton_2.setOpaque(true);
	    btnNewButton_2.setBorderPainted(false);
	    btnNewButton_2.setContentAreaFilled(false);
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnNewButton_2.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton_2.setBackground(new Color(255, 89, 172));
		btnNewButton_2.setBounds(805, 475, 162, 52);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_1 = new JLabel("Kullanıcı Giriş Sayfası");
		lblNewLabel_1.setForeground(new Color(117, 117, 117));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(250, 136, 315, 33);
		contentPane.add(lblNewLabel_1);
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
