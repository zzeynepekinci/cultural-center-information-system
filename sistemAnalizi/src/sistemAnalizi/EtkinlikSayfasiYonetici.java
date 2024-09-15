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

public class EtkinlikSayfasiYonetici extends JFrame {
    private static final long serialVersionUID = 1L;
    private JFrame self;
    

    public JFrame getSelf() {
		return self;
	}

	public void setSelf(JFrame self) {
		this.self = self;
	}

	public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    EtkinlikSayfasiYonetici frame = new EtkinlikSayfasiYonetici();
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

    public EtkinlikSayfasiYonetici() throws IOException {
        setIconImage(Toolkit.getDefaultToolkit().getImage(EtkinlikSayfasiYonetici.class.getResource("/resimler/logo2.png")));
        setTitle("Yıldız Kültür Merkezi");
        self = this; // Kendi referansımızı saklayalım

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                try {
                    YoneticiAnaSayfasi ka = new YoneticiAnaSayfasi();
                    ka.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    ka.setVisible(true);
                    setVisible(false);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

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

            JButton button = new JButton("Katılımcıları Görüntüle") {
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
            button.setPreferredSize(new Dimension(250, 50));
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Islemler bb = new Islemler();
                    bb.katilimciGoster(event.getName());
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
        scrollPane.setPreferredSize(new Dimension(1536, 850));
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().add(scrollPane);

        pack();
        JLabel arka = new JLabel();
        arka.setBackground(new Color(255, 185, 220));
        arka.setForeground(new Color(255, 128, 192));
        BufferedImage img = ImageIO.read(new File("C:\\Users\\iclal\\eclipse-workspace\\sistemAnalizi\\src\\resimler\\kullanici.jpg"));
        Image image = img.getScaledInstance(10000, 10000, Image.SCALE_SMOOTH);
        arka.setIcon(new ImageIcon(image));
        arka.setBounds(0, 0, 10000, 1000);
        getContentPane().add(arka);
    }
}
