package serviceWebSoapTest;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ConnexionPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldUsername;
    private JPasswordField passwordField;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ConnexionPage frame = new ConnexionPage();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ConnexionPage() {
        setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 813, 480);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Panneau Latéral
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 308, 480);
        panel.setBackground(Color.DARK_GRAY);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setBounds(0, 0, 308, 301);
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\EliteBook 840\\Downloads\\user_143447.png"));
        panel.add(lblNewLabel_1);

        JLabel lblLoginUi = new JLabel("Bienvenue");
        lblLoginUi.setBounds(87, 310, 124, 25);
        lblLoginUi.setHorizontalAlignment(SwingConstants.CENTER);
        lblLoginUi.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblLoginUi.setForeground(Color.WHITE);
        panel.add(lblLoginUi);

        JLabel lblWelcomeYou = new JLabel("Gestion des utilisateurs");
        lblWelcomeYou.setBounds(71, 351, 161, 25);
        lblWelcomeYou.setHorizontalAlignment(SwingConstants.CENTER);
        lblWelcomeYou.setForeground(Color.WHITE);
        lblWelcomeYou.setFont(new Font("Tahoma", Font.PLAIN, 12));
        panel.add(lblWelcomeYou);
        
        // Bouton de Connexion
        JButton buttonLogin = new JButton("Se connecter");
        buttonLogin.setBounds(382, 223, 283, 35);
        buttonLogin.setFont(new Font("Tahoma", Font.PLAIN, 12));
        buttonLogin.addActionListener(e -> authenticateUser());
        buttonLogin.setForeground(Color.WHITE);
        buttonLogin.setBackground(new Color(0, 128, 0));
        contentPane.add(buttonLogin);

        textFieldUsername = new JTextField();
        textFieldUsername.setBounds(382, 74, 283, 29);
        contentPane.add(textFieldUsername);
        textFieldUsername.setColumns(10);

        JLabel lblUsername = new JLabel("Nom d'Utilisateur");
        lblUsername.setBounds(382, 48, 215, 25);
        lblUsername.setFont(new Font("Tahoma", Font.BOLD, 16));
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("Mot de Passe");
        lblPassword.setBounds(382, 114, 175, 25);
        lblPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
        contentPane.add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setBounds(382, 139, 283, 29);
        contentPane.add(passwordField);

        JLabel lblCopyright = new JLabel("Copyright(c) M1GLSI 2024.");
        lblCopyright.setBounds(484, 424, 181, 20);
        lblCopyright.setFont(new Font("Tahoma", Font.PLAIN, 9));
        contentPane.add(lblCopyright);

        JLabel lblAllRightsReserved = new JLabel("All rights reserved.");
        lblAllRightsReserved.setBounds(709, 427, 78, 14);
        lblAllRightsReserved.setFont(new Font("Tahoma", Font.PLAIN, 9));
        contentPane.add(lblAllRightsReserved);
    }
    
    private void authenticateUser() {
        String username = textFieldUsername.getText();
        String password = new String(passwordField.getPassword());

        UserService service = new UserService();
        String authResponse = service.authenticate(username, password);

        if (authResponse.startsWith("Authentication successful")) {
            String authToken = authResponse.split(": ")[1]; // Extract the token
            JOptionPane.showMessageDialog(this, "Connecté! Token: " + authToken);
            // Lancer l'application de gestion des utilisateurs avec le token
            GestionUser gestionUserFrame = new GestionUser(authToken);
            gestionUserFrame.setVisible(true);
            dispose(); // Fermer la fenêtre de connexion
        } else {
            JOptionPane.showMessageDialog(this, "Échec de l'authentification", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
