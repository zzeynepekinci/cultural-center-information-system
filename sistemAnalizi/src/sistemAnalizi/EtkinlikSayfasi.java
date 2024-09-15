package sistemAnalizi;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.Toolkit;

public class EtkinlikSayfasi extends JFrame {
	private String globalMail;
	private static final long serialVersionUID = 1L;
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
					EtkinlikSayfasi frame = new EtkinlikSayfasi(globalMail);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			        
			        // Tam ekran moduna geçme
			        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			        
			        // Çerçeve boyutunu tekrar ayarlamamak için boyutları sabitleme
			        frame.setResizable(false);
			        
			        frame.getContentPane().setBackground(Color.WHITE);
			        
			        frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public EtkinlikSayfasi(String globalMail) throws IOException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(EtkinlikSayfasi.class.getResource("/resimler/logo2.png")));
		addWindowListener((WindowListener) new WindowAdapter() {
	        @Override
	        public void windowClosing(WindowEvent e) {
	            try {
	                KullaniciAnaSayfasi ka = new KullaniciAnaSayfasi(globalMail);
	                ka.setExtendedState(JFrame.MAXIMIZED_BOTH);
	                ka.setVisible(true);
	                setVisible(false);
	            } catch (IOException ex) {
	                ex.printStackTrace();
	            }
	        }
	    });
		
		setTitle("Yıldız Kültür Merkezi");
		this.globalMail = globalMail;
		Islemler et = new Islemler();
        List<Etkinlik> events = et.getAllEvents();

        
        JPanel mainPanel = new JPanel(new GridLayout(events.size(), 1));
        mainPanel.setOpaque(false);
        
        for (Etkinlik event : events) {
            
            byte[] resimBytes = event.getResim();
            ImageIcon icon = new ImageIcon(resimBytes);
          
            JLabel label = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH)));
          
            JTextPane textPane = new JTextPane();
            textPane.setEditable(false);
            textPane.setFont(new Font("Arial Black", Font.BOLD, 13));
            textPane.setForeground(new Color(117, 117, 117));
            textPane.setOpaque(false);
            textPane.setPreferredSize(new Dimension(500, 400));

            StyledDocument doc = textPane.getStyledDocument();
            SimpleAttributeSet center = new SimpleAttributeSet();
            StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);

            try {
                doc.insertString(doc.getLength(), event.toString(), null);               
                doc.setParagraphAttributes(0, doc.getLength(), center, false);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }

            textPane.setAlignmentX(JTextPane.CENTER_ALIGNMENT);
            
            JButton button = new JButton("Bilet Al") {
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
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);
    	    button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    	    button.setOpaque(true);
    	    button.setBorderPainted(false);
    	    button.setContentAreaFilled(false);
    	    button.setFont(new Font("Arial Black", Font.BOLD, 13));
    		button.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
    		button.setBackground(new Color(255, 89, 172));
    		button.setPreferredSize(new Dimension(250,50));
            button.addActionListener(new ActionListener() {
                @SuppressWarnings("deprecation")
				@Override
                public void actionPerformed(ActionEvent e) {
                	Islemler k = new Islemler();
    				int bakiye = k.getKullaniciBakiye(globalMail);
    				int fiyat= event.getFiyat();
    				String etkinlikAdi = event.getName();
    				int saat = event.getBasSaat();
    				int gun = event.getGun();
    				int ay = event.getAy();
    				int yil = event.getYil();
    				int kullaniciId = k.getKullaniciId(globalMail);
    				System.out.println(fiyat);
    				if(bakiye>=fiyat) {
    					Bilet bilet = new Bilet(fiyat,etkinlikAdi, saat, gun, ay,yil);
    					Islemler b = new Islemler();
    					b.addBilet(bilet, kullaniciId);
    					bakiye=bakiye-fiyat;
    					k.bakiyeGuncelle(globalMail, bakiye);
    					JOptionPane.showMessageDialog(null, "Biletiniz oluşturuldu. Biletlerim bölümünden görebilirsiniz.");
    					System.out.println(bakiye);
    				}
    				else
    					JOptionPane.showMessageDialog(null, "Bakiye yetersiz.");
                    
                    KullaniciAnaSayfasi ka;
					try {
						ka = new KullaniciAnaSayfasi(globalMail);
						ka.setExtendedState(JFrame.MAXIMIZED_BOTH);
						ka.show();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
                    
                    setVisible(false);
                }
            });

            JPanel eventPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
            eventPanel.add(label);
            eventPanel.add(textPane);
            eventPanel.add(button);
            eventPanel.setOpaque(false);
            mainPanel.add(eventPanel);
            
        }

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setPreferredSize(new Dimension(600, 400));
        scrollPane.setOpaque(false);    
        scrollPane.getViewport().setOpaque(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(scrollPane);

        pack();
        JLabel arka = new JLabel();
        
		arka.setBackground(new Color(255, 185, 220));
		arka.setForeground(new Color(255, 128, 192));
		BufferedImage img = ImageIO.read(new File("C:\\\\Users\\\\iclal\\\\eclipse-workspace\\\\sistemAnalizi\\\\src\\\\resimler\\\\kullanici.jpg"));
		Image image = img.getScaledInstance(10000, 10000, Image.SCALE_SMOOTH);
		arka.setIcon(new ImageIcon(image));
		arka.setBounds(0, 0, 10000, 1000);
		scrollPane.add(arka);
		
		
	}

}
