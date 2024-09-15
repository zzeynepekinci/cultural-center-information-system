package sistemAnalizi;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.border.*;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Font;
import java.awt.Toolkit;


public class Anasayfa extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Anasayfa frame = new Anasayfa();
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

	public Anasayfa() throws IOException {
				
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Anasayfa.class.getResource("/resimler/logo2.png")));

		Islemler yon = new Islemler();
		yon.addAdmin("yonetici@hotmail.com", "isim","soyisim","yonetici123");	
		
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        scheduler.scheduleAtFixedRate(() -> {
            Islemler eventDAO = new Islemler();
            eventDAO.deleteExpiredEvents();
        }, 0, 24, TimeUnit.HOURS);

		
		setTitle("Yıldız Kültür Merkezi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 982, 493);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBounds(0, 0, 10000, 1000);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JButton btnNewButton = new JButton("KULLANICI GİRİŞİ") {
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
	    btnNewButton.setForeground(Color.WHITE);
	    btnNewButton.setFocusPainted(false);
	    btnNewButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
	    btnNewButton.setOpaque(true);
	    btnNewButton.setBorderPainted(false);
	    btnNewButton.setContentAreaFilled(false);
		btnNewButton.setFont(new Font("Arial Black", Font.BOLD, 20));
		btnNewButton.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton.setBackground(new Color(255, 155, 205));
		
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				KullaniciGiris kg;
				try {
					kg = new KullaniciGiris();
					kg.setExtendedState(JFrame.MAXIMIZED_BOTH);
					kg.show();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				setVisible(false);
			}
		});
		btnNewButton.setBounds(572, 300, 400, 80);
		
		
		contentPane.add(btnNewButton, BorderLayout.CENTER);
		
		JButton btnNewButton_1 = new JButton("YÖNETİCİ GİRİŞİ") {
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
		btnNewButton_1.setForeground(Color.WHITE);
	    btnNewButton_1.setFocusPainted(false);
	    btnNewButton_1.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
	    btnNewButton_1.setOpaque(true);
	    btnNewButton_1.setBorderPainted(false);
	    btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setFont(new Font("Arial Black", Font.BOLD, 20));
		btnNewButton_1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton_1.setBackground(new Color(255, 155, 205));
		
		btnNewButton_1.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				YoneticiGiris y;
				try {
					y = new YoneticiGiris();
					y.setExtendedState(JFrame.MAXIMIZED_BOTH);
					y.show();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				setVisible(false);
			}
		});
		btnNewButton_1.setBounds(572, 400, 400, 80);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Kaydol") {
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
		btnNewButton_2.setForeground(Color.WHITE);
	    btnNewButton_2.setFocusPainted(false);
	    btnNewButton_2.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
	    btnNewButton_2.setOpaque(true);
	    btnNewButton_2.setBorderPainted(false);
	    btnNewButton_2.setContentAreaFilled(false);
		btnNewButton_2.setFont(new Font("Arial Black", Font.BOLD, 20));
		btnNewButton_2.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton_2.setBackground(new Color(255, 89, 172));
		
		btnNewButton_2.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				KullaniciKayit k;
				try {
					k = new KullaniciKayit();
					k.setExtendedState(JFrame.MAXIMIZED_BOTH);
					k.show();
					setVisible(false);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_2.setBounds(673, 500, 200, 50);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel = new JLabel("YILDIZ KÜLTÜR MERKEZİ");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 40));
		lblNewLabel.setForeground(new Color(159, 159, 159));
		lblNewLabel.setBounds(252, 68, 599, 90);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("New JGoodies label");
		BufferedImage img = ImageIO.read(new File("C:\\\\Users\\\\iclal\\\\eclipse-workspace\\\\sistemAnalizi\\\\src\\\\resimler\\\\logo.png"));

        int newWidth = 400; 
        int newHeight = 250; 
        Image scaledImg = img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        ImageIcon icon = new ImageIcon(scaledImg);
      
		lblNewJgoodiesLabel.setIcon(icon);
		
		lblNewJgoodiesLabel.setBounds(-79, 0, 319, 214);
		contentPane.add(lblNewJgoodiesLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBackground(new Color(255, 185, 220));
		lblNewLabel_1.setForeground(new Color(255, 128, 192));
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\iclal\\eclipse-workspace\\sistemAnalizi\\src\\resimler\\arkaplanpembe.jpg"));
		lblNewLabel_1.setBounds(0, 0, 10000, 1000);
		contentPane.add(lblNewLabel_1);

	}
}
