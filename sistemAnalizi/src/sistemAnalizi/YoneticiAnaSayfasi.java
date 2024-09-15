package sistemAnalizi;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;

public class YoneticiAnaSayfasi extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					YoneticiAnaSayfasi frame = new YoneticiAnaSayfasi();
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			        
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

	public YoneticiAnaSayfasi() throws IOException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(YoneticiAnaSayfasi.class.getResource("/resimler/logo2.png")));
		setTitle("Yıldız Kültür Merkezi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 491);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Etkinlik Ekle") {

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
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    JFileChooser fileChooser = new JFileChooser();
			    fileChooser.setDialogTitle("Resim Seç");

			    int result = fileChooser.showOpenDialog(null);

			    if (result == JFileChooser.APPROVE_OPTION) {
			        File file = fileChooser.getSelectedFile();

			        try {

			            String eventName = JOptionPane.showInputDialog(null, "Etkinlik Adı:");
			            String bilgi = JOptionPane.showInputDialog(null, "Etkinlik Bilgisi:");
			            int kontenjan = Integer.parseInt(JOptionPane.showInputDialog(null, "Kontenjan:"));
			            int gun = Integer.parseInt(JOptionPane.showInputDialog(null, "Gün:"));
			            int ay = Integer.parseInt(JOptionPane.showInputDialog(null, "Ay:"));
			            int yil = Integer.parseInt(JOptionPane.showInputDialog(null, "Yıl:"));
			            int basSaat = Integer.parseInt(JOptionPane.showInputDialog(null, "Başlangıç Saati:"));
			            int bitSaat = Integer.parseInt(JOptionPane.showInputDialog(null, "Bitiş Saati:"));
			            int sure = Integer.parseInt(JOptionPane.showInputDialog(null, "Süre (saat):"));
			            int fiyat = Integer.parseInt(JOptionPane.showInputDialog(null, "Fiyat (tl):"));
			            FileInputStream fis = new FileInputStream(file);

			            byte[] resimBytes = new byte[(int) file.length()];
			            fis.read(resimBytes);
			            Etkinlik etkinlik = new Etkinlik(eventName, bilgi, kontenjan, gun, ay, yil, basSaat, bitSaat, sure, fiyat, resimBytes);

			            Islemler etkinlikDAO = new Islemler();
			            etkinlikDAO.addEvent(etkinlik);

			            fis.close();

			            JOptionPane.showMessageDialog(null, "Etkinlik veritabanına eklendi.");

			        } catch (IOException | NumberFormatException ex) {
			            ex.printStackTrace();
			            JOptionPane.showMessageDialog(null, "Bir hata oluştu. Etkinlik veritabanına eklenemedi.");
			        }
			    }
			}
		});
		btnNewButton.setForeground(Color.WHITE);
	    btnNewButton.setFocusPainted(false);
	    btnNewButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
	    btnNewButton.setOpaque(true);
	    btnNewButton.setBorderPainted(false);
	    btnNewButton.setContentAreaFilled(false);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton.setBackground(new Color(255, 155, 205));
		btnNewButton.setPreferredSize(new Dimension(275,75));
		btnNewButton.setBounds(31, 277, 198, 71);
		
		JButton btnNewButton_1 = new JButton("Etkinlik Görüntüle") {
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
		btnNewButton_1.addActionListener(new ActionListener() {
		    @SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				
		    	EtkinlikSayfasiYonetici et;
				try {
					et = new EtkinlikSayfasiYonetici();
					et.setExtendedState(JFrame.MAXIMIZED_BOTH);
					et.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					et.show();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		    	
		    }
		});

		btnNewButton_1.setForeground(Color.WHITE);
	    btnNewButton_1.setFocusPainted(false);
	    btnNewButton_1.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
	    btnNewButton_1.setOpaque(true);
	    btnNewButton_1.setBorderPainted(false);
	    btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton_1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton_1.setBackground(new Color(255, 155, 205));
		btnNewButton_1.setBounds(55, 381, 217, 63);
		btnNewButton_1.setPreferredSize(new Dimension(275,75));
		
		JLabel lblNewLabel_3 = new JLabel("YILDIZ KÜLTÜR MERKEZİ");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3.setForeground(new Color(117, 117, 117));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblNewLabel_3.setBounds(234, 77, 550, 71);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_1 = new JLabel("Yönetici Ana Sayfası");
		lblNewLabel_1.setForeground(new Color(117, 117, 117));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(244, 140, 227, 33);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton_2 = new JButton("Etkinlik Sil") {
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
				String eventName = JOptionPane.showInputDialog(null, "Etkinlik Adı:");
				Islemler enew = new Islemler();
		        enew.deleteEvent(eventName);
		        JOptionPane.showMessageDialog(null, "etkinlik silindi.\n" );
			}
		});
		btnNewButton_2.setForeground(Color.WHITE);
	    btnNewButton_2.setFocusPainted(false);
	    btnNewButton_2.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
	    btnNewButton_2.setOpaque(true);
	    btnNewButton_2.setBorderPainted(false);
	    btnNewButton_2.setContentAreaFilled(false);
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton_2.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton_2.setBackground(new Color(255, 155, 205));
		btnNewButton_2.setPreferredSize(new Dimension(275,75));
		btnNewButton_2.setBounds(239, 295, 166, 53);
		
		JButton btnNewButton_6 = new JButton("Kullanıcıları Göster") {
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
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        JFrame userTableFrame = new JFrame("Kullanıcılar");
		        userTableFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(YoneticiAnaSayfasi.class.getResource("/resimler/logo2.png")));
		        userTableFrame.setTitle("Yıldız Kültür Merkezi");
		        userTableFrame.setSize(600, 400);
		        userTableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        userTableFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		        JTable userTable = new JTable();
		        userTable.setGridColor(Color.GRAY);
		        userTable.setBackground(Color.pink);
		        JScrollPane scrollPane = new JScrollPane(userTable);
		        Islemler k = new Islemler();
		        k.displayUsersInTable(k.getAllUsers(), userTable);
		        userTableFrame.add(scrollPane);
		        userTableFrame.setVisible(true);
			}
		});
		
		btnNewButton_6.setForeground(Color.WHITE);
	    btnNewButton_6.setFocusPainted(false);
	    btnNewButton_6.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
	    btnNewButton_6.setOpaque(true);
	    btnNewButton_6.setBorderPainted(false);
	    btnNewButton_6.setContentAreaFilled(false);		
		btnNewButton_6.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton_6.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton_6.setBackground(new Color(255, 155, 205));
		btnNewButton_6.setBounds(263, 381, 2000, 1000);
		btnNewButton_6.setPreferredSize(new Dimension(275,75));
		
		JButton btnNewButton_3 = new JButton("Çıkış Yap") {
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
		
		
		JPanel ustPanel = new JPanel();
	    ustPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); 
	    ustPanel.setBounds(450, 350, 600, 100); 
	    ustPanel.add(btnNewButton); 
	    ustPanel.add(btnNewButton_1);
	    ustPanel.setOpaque(false);
	    contentPane.add(ustPanel); 

	    JPanel altPanel = new JPanel();
	    altPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); 
	    altPanel.setBounds(450, 450, 600, 100); 
	    altPanel.add(btnNewButton_2); 
	    altPanel.add(btnNewButton_6); 
	    altPanel.setOpaque(false);
	    contentPane.add(altPanel); 
		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("New JGoodies label");
		BufferedImage img = ImageIO.read(new File("C:\\\\Users\\\\iclal\\\\eclipse-workspace\\\\sistemAnalizi\\\\src\\\\resimler\\\\logo.png"));

        int newWidth = 400;
        int newHeight = 250; 
        Image scaledImg = img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        ImageIcon icon = new ImageIcon(scaledImg);
        
		lblNewJgoodiesLabel.setIcon(icon);
		
		lblNewJgoodiesLabel.setBounds(-79, 0, 319, 214);
		contentPane.add(lblNewJgoodiesLabel);
		JLabel lblNewLabel_2 = new JLabel("New label");
		ImageIcon img2 = new ImageIcon("C:\\Users\\iclal\\eclipse-workspace\\sistemAnalizi\\src\\resimler\\kullanici.jpg");
		
		Image scaledImg2 = img2.getImage().getScaledInstance(10000, 1000, Image.SCALE_SMOOTH);
		img2 = new ImageIcon(scaledImg2);
		lblNewLabel_2.setBounds(0, 0, 10000, 1000);lblNewLabel_2.setIcon(img2);
		contentPane.add(lblNewLabel_2);
		
		
			
	}
}
