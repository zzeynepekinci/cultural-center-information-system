package sistemAnalizi;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

import java.awt.Toolkit;
import javax.swing.SwingConstants;

public class KullaniciAnaSayfasi extends JFrame {
	private String globalMail;
	int count=0;
	int count2=0;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public String getGlobalMail() {
        return globalMail;
    }

    public void setGlobalMail(String email) {
        globalMail = email;
    }

	

	public static void main(String[] args) {
		String globalMail = null;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KullaniciAnaSayfasi frame = new KullaniciAnaSayfasi(globalMail);
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

	public KullaniciAnaSayfasi(String globalMail) throws IOException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(KullaniciAnaSayfasi.class.getResource("/resimler/logo2.png")));
		setTitle("Yıldız Kültür Merkezi");
		this.globalMail = globalMail;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 492);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
				
				EtkinlikSayfasi et;
				try {
					et = new EtkinlikSayfasi(globalMail);
					et.setExtendedState(JFrame.MAXIMIZED_BOTH);
					et.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					setVisible(false);
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
		//btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setFont(new Font("Arial Black", Font.BOLD, 20));
		btnNewButton_1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton_1.setBackground(new Color(255, 155, 205));
		//btnNewButton_1.setBounds(285, 310, 121, 21);
		btnNewButton_1.setBounds(905, 180, 270, 100);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Bakiye Bilgisi");
		lblNewLabel.setForeground(new Color(117, 117, 117));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(285, 72, 121, 27);
		
		Islemler k = new Islemler();
		
		System.out.println(globalMail);
		
		int bakiyeAlinan = k.getKullaniciBakiye(globalMail);
		String bakiye1 = String.valueOf(bakiyeAlinan);
		JLabel lblNewLabel_1 = new JLabel(bakiye1+" tl");
		lblNewLabel_1.setForeground(new Color(117, 117, 117));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(285, 100, 101, 27);
		
		
		JTextArea textField = new JTextArea();
		textField.setFont(new Font("Monospaced", Font.PLAIN, 13));
		textField.setOpaque(false);
		textField.setBounds(5,5,200,30);

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

		roundedPanel.add(textField);

		roundedPanel.setBounds(285, 210, 200, 30);
		roundedPanel.setVisible(false);

		//contentPane.add(roundedPanel);
		
		JLabel lblNewLabel_2 = new JLabel("Miktar giriniz.");
		lblNewLabel_2.setForeground(new Color(117, 117, 117));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_2.setBounds(285, 182, 121, 21);
		lblNewLabel_2.setVisible(false);
		
		JButton btnNewButton_2 = new JButton("Yükle") {
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
		btnNewButton_2.setForeground(Color.WHITE);
	    btnNewButton_2.setFocusPainted(false);
	    btnNewButton_2.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
	    btnNewButton_2.setOpaque(true);
	    btnNewButton_2.setBorderPainted(false);
	    btnNewButton_2.setContentAreaFilled(false);
		btnNewButton_2.setFont(new Font("Arial Black", Font.BOLD, 13));
		btnNewButton_2.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton_2.setBackground(new Color(255, 89, 172));
		btnNewButton_2.setBounds(383, 250, 100, 50);
		btnNewButton_2.setVisible(false);
		
		JButton btnNewButton = new JButton("Bakiye Yükle") {
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
				roundedPanel.setVisible(true);
				textField.setVisible(true);
				lblNewLabel_2.setVisible(true);
				btnNewButton_2.setVisible(true);
			}
		});
		btnNewButton.setForeground(Color.WHITE);
	    btnNewButton.setFocusPainted(false);
	    btnNewButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
	    btnNewButton.setOpaque(true);
	    btnNewButton.setBorderPainted(false);
	    btnNewButton.setContentAreaFilled(false);
		btnNewButton.setFont(new Font("Arial Black", Font.BOLD, 13));
		btnNewButton.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton.setBackground(new Color(255, 155, 205));
		btnNewButton.setBounds(285, 134, 200, 40);
		
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int intValue = Integer.parseInt(textField.getText());							
				int toplam = intValue+bakiyeAlinan;			
				String toplam1 = String.valueOf(toplam);
				lblNewLabel_1.setText(toplam1+" tl");
				k.bakiyeGuncelle(globalMail, toplam);
				roundedPanel.setVisible(false);
				textField.setVisible(false);
				lblNewLabel_2.setVisible(false);
				btnNewButton_2.setVisible(false);
				
			}
		});
		JPanel altPanel = new JPanel();
	    altPanel.setLayout(null); 
	    altPanel.setBounds(940, 100, 1000, 1000); 
	    altPanel.add(btnNewButton_2);
	    altPanel.add(btnNewButton);
	    altPanel.add(lblNewLabel_2);
	    altPanel.add(lblNewLabel_1);
	    altPanel.add(lblNewLabel);
	    altPanel.add(roundedPanel);
	    altPanel.setPreferredSize(new Dimension(300,300));
	    altPanel.setOpaque(false);
	    contentPane.add(altPanel);
		JButton btnNewButton_6 = new JButton("Çıkış Yap") {
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
		btnNewButton_6.setForeground(Color.WHITE);
	    btnNewButton_6.setFocusPainted(false);
	    btnNewButton_6.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
	    btnNewButton_6.setOpaque(true);
	    btnNewButton_6.setBorderPainted(false);
	    btnNewButton_6.setContentAreaFilled(false);
		btnNewButton_6.setFont(new Font("Arial Black", Font.BOLD, 13));
		btnNewButton_6.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton_6.setBackground(new Color(255, 89, 172));
		btnNewButton_6.setBounds(1300, 77, 150, 65);
		contentPane.add(btnNewButton_6);
		
		JLabel lblNewLabel_3 = new JLabel("YILDIZ KÜLTÜR MERKEZİ");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3.setForeground(new Color(117, 117, 117));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblNewLabel_3.setBounds(234, 77, 550, 71);
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
		
		JLabel lblNewLabel_1_1 = new JLabel("Kullanıcı Ana Sayfası");
		lblNewLabel_1_1.setForeground(new Color(117, 117, 117));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(244, 143, 227, 33);
		contentPane.add(lblNewLabel_1_1);
		
		JTextPane textPane = new JTextPane();
        textPane.setEditable(false);
        textPane.setFont(new Font("Arial Black", Font.BOLD, 13));
        textPane.setForeground(new Color(117, 117, 117));
        textPane.setBackground(new Color(255, 155, 205));
        textPane.setPreferredSize(new Dimension(500, 400));
        textPane.setBounds(905, 560, 380, 190);
        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();

        Islemler kd = new Islemler();
        Kullanici kullanici = new Kullanici(null, null, null, null, null, 0, 0, 0);
        kullanici = kd.getKullaniciBilgileri(globalMail);

        try {
            doc.insertString(doc.getLength(), kullanici.toString(), null);
            doc.setParagraphAttributes(0, doc.getLength(), center, false);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }

        textPane.setVisible(false);
        contentPane.add(textPane);
		JButton btnNewButton_3 = new JButton("Kişisel Bilgileri Göster") {

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
			public void actionPerformed(ActionEvent e) {
				count=count+1;
				if(count%2==1)
					textPane.setVisible(true);
				else
					textPane.setVisible(false);
			}
		});
		btnNewButton_3.setForeground(Color.WHITE);
	    btnNewButton_3.setFocusPainted(false);
	    btnNewButton_3.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
	    btnNewButton_3.setOpaque(true);
	    btnNewButton_3.setBorderPainted(false);
	    btnNewButton_3.setContentAreaFilled(false);
		btnNewButton_3.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton_3.setBackground(new Color(255, 155, 205));
		btnNewButton_3.setFont(new Font("Arial Black", Font.BOLD, 20));
		btnNewButton_3.setBounds(905, 300, 270, 100);
		contentPane.add(btnNewButton_3);
		
		JTextPane textPane2 = new JTextPane();
        textPane2.setEditable(false);
        textPane2.setFont(new Font("Arial Black", Font.BOLD, 13));
        textPane2.setForeground(new Color(117, 117, 117));
        textPane2.setBackground(new Color(255, 155, 205));
        textPane2.setPreferredSize(new Dimension(500, 400));
        textPane2.setBounds(905, 560, 380, 190);
        contentPane.add(textPane2);
        //textPane2.setVisible(false);
        JScrollPane scrollPane2 = new JScrollPane(textPane2);
        scrollPane2.setPreferredSize(new Dimension(500, 400));
        scrollPane2.setBounds(905, 560, 380, 190);
        contentPane.add(scrollPane2); // contentPane yerine scrollPane2 eklenmeli
        scrollPane2.setVisible(false);

        StyledDocument doc2 = textPane2.getStyledDocument();
        SimpleAttributeSet center2 = new SimpleAttributeSet();

        
        Islemler bd = new Islemler();
        List <Bilet> biletler = bd.getBiletIdsByKullaniciId(kd.getKullaniciId(globalMail));

        try {
        	for(Bilet b : biletler) {
	        	doc2.insertString(doc2.getLength(), b.toString(), null);
	            doc2.setParagraphAttributes(0, doc2.getLength(), center2, false);	
        	}
            
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
		
		JButton btnNewButton_10 = new JButton("Biletlerim") {
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
		
		btnNewButton_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				count2 =count2 +1;
				if(count2 %2==1) {
					//textPane2.setVisible(true);
					scrollPane2.setVisible(true);
				}
					
				else {
					//textPane2.setVisible(false);
					scrollPane2.setVisible(false);
				}
					
			}
		});
		btnNewButton_10.setForeground(Color.WHITE);
	    btnNewButton_10.setFocusPainted(false);
	    btnNewButton_10.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
	    btnNewButton_10.setOpaque(true);
	    btnNewButton_10.setBorderPainted(false);
	    btnNewButton_10.setContentAreaFilled(false);
		btnNewButton_10.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton_10.setBackground(new Color(255, 155, 205));
		btnNewButton_10.setFont(new Font("Arial Black", Font.BOLD, 20));
		btnNewButton_10.setBounds(905, 422, 270, 100);
		contentPane.add(btnNewButton_10);
		JLabel lblNewJgoodiesLabel1 = DefaultComponentFactory.getInstance().createLabel("New JGoodies label");

		try {
		    BufferedImage img1 = ImageIO.read(new File("C:/Users/iclal/eclipse-workspace/sistemAnalizi/src/resimler/etkinlik1.png"));

		    int newWidth1 = 700; 
		    int newHeight1 = 500; 
		    Image scaledImg1 = img1.getScaledInstance(newWidth1, newHeight1, Image.SCALE_SMOOTH);

		    ImageIcon icon1 = new ImageIcon(scaledImg1);
		    lblNewJgoodiesLabel1.setIcon(icon1);

		} catch (IOException e) {
		    e.printStackTrace();
		}

		lblNewJgoodiesLabel1.setBounds(100, 250, 700, 500); 
		contentPane.add(lblNewJgoodiesLabel1);
		
		
		JLabel arka = new JLabel();
        
		arka.setBackground(new Color(255, 185, 220));
		arka.setForeground(new Color(255, 128, 192));
		BufferedImage img2 = ImageIO.read(new File("C:\\\\Users\\\\iclal\\\\eclipse-workspace\\\\sistemAnalizi\\\\src\\\\resimler\\\\kullanici.jpg"));
		Image image = img2.getScaledInstance(10000, 1000, Image.SCALE_SMOOTH);
		arka.setIcon(new ImageIcon(image));
		arka.setBounds(0, 0, 10000, 1000);
		contentPane.add(arka);
		
		
	
		
		
		
		
		
		
		
	}
}
