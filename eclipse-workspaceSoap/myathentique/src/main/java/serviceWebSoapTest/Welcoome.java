package serviceWebSoapTest;

	import javax.swing.*;
	import java.awt.*;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.BevelBorder;

	public class Welcoome extends JFrame {

		private static final long serialVersionUID = 1L;

		public Welcoome() {
	        // Configuration de la fenêtre
	        setTitle("Page d'accueil");
	        setSize(1000, 500);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLocationRelativeTo(null);

	        // Couleur personnalisée pour le texte de bienvenue
	        Color welcomeTextColor = new Color(250, 128, 114);

	        // Créer un JPanel personnalisé pour le fond d'écran
	        JPanel backgroundPanel = new JPanel() {
	            @Override
	            protected void paintComponent(Graphics g) {
	                super.paintComponent(g);
	                // Dessiner l'image de fond redimensionnée pour remplir la fenêtre
//	                try {
////	                    BufferedImage img = ImageIO.read(new URL("file:///C:\\Users\\EliteBook 840\\eclipse-workspace2\\mangone\\images\\mast.jpg"));
//	                    g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
//	                } catch (IOException e) {
//	                    e.printStackTrace();
//	                }

//	                // Dessiner le texte de bienvenue avec la couleur personnalisée
//	                Graphics2D g2d = (Graphics2D) g.create();
//	                g2d.setColor(welcomeTextColor); // Utilisation de la couleur personnalisée
//	                g2d.setFont(new Font("Segoe", Font.BOLD, 32)); // Taille et police du texte
//	                String welcomeText = "Bienvenue dans notre application de dimensionnement LTE";
//	                FontMetrics fm = g2d.getFontMetrics();
//	                int textWidth = fm.stringWidth(welcomeText);
//	                int x = (getWidth() - textWidth) / 2;
//	                int y = 100; // Position verticale ajustée
//	                g2d.drawString(welcomeText, x, y);

//	                // Ajouter un logo de l'application (exemple)
//	                try {
//	                    BufferedImage logo = ImageIO.read(new URL("file:///C:\\Users\\EliteBook 840\\eclipse-workspace2\\mangone\\imagesimages.png"));
//	                    int logoWidth = logo.getWidth();
//	                    int logoHeight = logo.getHeight();
//	                    g2d.drawImage(logo, getWidth() - logoWidth - 20, getHeight() - logoHeight - 20, logoWidth, logoHeight, this);
//	                } catch (IOException e) {
//	                    e.printStackTrace();
//	                }

//	                g2d.dispose();
	            }
	        };
	        backgroundPanel.setBackground(SystemColor.control);
	        getContentPane().add(backgroundPanel, BorderLayout.SOUTH);
	        
	        JPanel panel = new JPanel();
	        panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
	        panel.setBackground(new Color(255, 255, 255));
	        
	        JButton btnNewButton = new JButton("Se connecter");
	        btnNewButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                connectPage();
	            }
	        });
	        btnNewButton.setFont(new Font("Tempus Sans ITC", Font.BOLD | Font.ITALIC, 14));
	        GroupLayout gl_backgroundPanel = new GroupLayout(backgroundPanel);
	        gl_backgroundPanel.setHorizontalGroup(
	        	gl_backgroundPanel.createParallelGroup(Alignment.LEADING)
	        		.addGroup(gl_backgroundPanel.createSequentialGroup()
	        			.addGroup(gl_backgroundPanel.createParallelGroup(Alignment.LEADING)
	        				.addGroup(gl_backgroundPanel.createSequentialGroup()
	        					.addGap(448)
	        					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE))
	        				.addGroup(gl_backgroundPanel.createSequentialGroup()
	        					.addGap(270)
	        					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 602, GroupLayout.PREFERRED_SIZE)))
	        			.addContainerGap(271, Short.MAX_VALUE))
	        );
	        gl_backgroundPanel.setVerticalGroup(
	        	gl_backgroundPanel.createParallelGroup(Alignment.LEADING)
	        		.addGroup(gl_backgroundPanel.createSequentialGroup()
	        			.addGap(89)
	        			.addComponent(panel, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
	        			.addPreferredGap(ComponentPlacement.RELATED)
	        			.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
	        			.addContainerGap(61, Short.MAX_VALUE))
	        );
	        SpringLayout sl_panel = new SpringLayout();
	        panel.setLayout(sl_panel);
	        
	        JLabel lblNewLabel = new JLabel("Bienvenue sur notre plateforme de gestion des utilisateurs");
	        sl_panel.putConstraint(SpringLayout.NORTH, lblNewLabel, 11, SpringLayout.NORTH, panel);
	        sl_panel.putConstraint(SpringLayout.WEST, lblNewLabel, 95, SpringLayout.WEST, panel);
	        sl_panel.putConstraint(SpringLayout.SOUTH, lblNewLabel, 49, SpringLayout.NORTH, panel);
	        sl_panel.putConstraint(SpringLayout.EAST, lblNewLabel, 546, SpringLayout.WEST, panel);
	        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
	        panel.add(lblNewLabel);
	        
	        JLabel lblNewLabel_1 = new JLabel("Gérez vos utilisateurs avec facilité et efficacité.");
	        sl_panel.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 6, SpringLayout.SOUTH, lblNewLabel);
	        sl_panel.putConstraint(SpringLayout.WEST, lblNewLabel_1, 180, SpringLayout.WEST, panel);
	        sl_panel.putConstraint(SpringLayout.EAST, lblNewLabel_1, -184, SpringLayout.EAST, panel);
	        panel.add(lblNewLabel_1);
	        
	        
	        JLabel lblNewLabel_1_1 = new JLabel("Veuillez vous connecter pour accéder à votre espace sécurisé.\n");
	        sl_panel.putConstraint(SpringLayout.SOUTH, lblNewLabel_1, -43, SpringLayout.NORTH, lblNewLabel_1_1);
	        sl_panel.putConstraint(SpringLayout.EAST, lblNewLabel_1_1, -97, SpringLayout.EAST, panel);
	        sl_panel.putConstraint(SpringLayout.NORTH, lblNewLabel_1_1, 120, SpringLayout.NORTH, panel);
	        sl_panel.putConstraint(SpringLayout.WEST, lblNewLabel_1_1, 149, SpringLayout.WEST, panel);
	        lblNewLabel_1_1.setFont(new Font("Sitka Small", Font.BOLD, 11));
	        panel.add(lblNewLabel_1_1);
	        
	        JTextPane txtpnEntrezVotreLogin = new JTextPane();
	        sl_panel.putConstraint(SpringLayout.SOUTH, lblNewLabel_1_1, -7, SpringLayout.NORTH, txtpnEntrezVotreLogin);
	        sl_panel.putConstraint(SpringLayout.NORTH, txtpnEntrezVotreLogin, 151, SpringLayout.NORTH, panel);
	        sl_panel.putConstraint(SpringLayout.WEST, txtpnEntrezVotreLogin, 43, SpringLayout.WEST, panel);
	        sl_panel.putConstraint(SpringLayout.SOUTH, txtpnEntrezVotreLogin, -53, SpringLayout.SOUTH, panel);
	        sl_panel.putConstraint(SpringLayout.EAST, txtpnEntrezVotreLogin, -21, SpringLayout.EAST, panel);
	        txtpnEntrezVotreLogin.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 12));
	        txtpnEntrezVotreLogin.setText("Entrez votre login et mot de passe pour vérifier vos droits d'accès. Seuls les administrateurs ont la possibilité de gérer les utilisateurs. Assurez-vous de disposer des droits nécessaires pour effectuer des opérations de gestion telles que l'ajout, la modification ou la suppression d'utilisateurs.");
	        panel.add(txtpnEntrezVotreLogin);
	        
	        JLabel lblNewLabel_1_2 = new JLabel("Votre sécurité est notre priorité.");
	        sl_panel.putConstraint(SpringLayout.NORTH, lblNewLabel_1_2, 6, SpringLayout.SOUTH, txtpnEntrezVotreLogin);
	        sl_panel.putConstraint(SpringLayout.WEST, lblNewLabel_1_2, 218, SpringLayout.WEST, panel);
	        sl_panel.putConstraint(SpringLayout.SOUTH, lblNewLabel_1_2, -33, SpringLayout.SOUTH, panel);
	        sl_panel.putConstraint(SpringLayout.EAST, lblNewLabel_1_2, -170, SpringLayout.EAST, panel);
	        lblNewLabel_1_2.setFont(new Font("Tahoma", Font.ITALIC, 11));
	        panel.add(lblNewLabel_1_2);
	        backgroundPanel.setLayout(gl_backgroundPanel);

	    

	       
	    }

	    private void connectPage() {
	    	ConnexionPage A = new ConnexionPage(); // Ouvre la page d'inscription
	        A.setVisible(true);
	        this.setVisible(false);
			
		}

		public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> {
	            try {
	                Welcoome welcoomePage = new Welcoome();
	                welcoomePage.setVisible(true);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        });
	    }
	}


