package serviceWebSoapTest;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import jakarta.xml.ws.WebServiceException;
import javax.swing.table.DefaultTableModel;

public class GestionUser extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private static JTable table;
    private JTextField textFieldFirstName;
    private JTextField textFieldLastName;
    private JTextField textFieldGender;
    private JTextField textFieldCity;
    private JTextField textFieldBirthDate;
    private JTextArea textAreaAddress;

    private static String authToken; // // Stocke le token d'authentification

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                // Authenticate and get the auth token
                UserService service = new UserService();
                String tokenResponse = service.authenticate("username", "password"); // Remplacez par les véritables credentials

                if (tokenResponse.startsWith("Authentication successful")) {
                    authToken = tokenResponse.split(": ")[1]; // Extract the token from the response
                    GestionUser frame = new GestionUser(authToken);
                     // Charger les utilisateurs lors de l'initialisation
                    frame.setVisible(true);
                    frame.loadUsers();
                } else {
                    JOptionPane.showMessageDialog(null, "Authentication failed");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    //Il prend en paramètre un authToken pour l'authentification auprès du service web.
    public GestionUser(String authToken) {
        GestionUser.authToken = authToken; // Reçoit le token d'authentification

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 898, 439);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(192, 192, 192));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 882, 60);
        panel.setBackground(new Color(255, 255, 255));
        contentPane.add(panel);
        panel.setLayout(null);
        
        JTextPane txtpnGestionDesEtudiants = new JTextPane();
        txtpnGestionDesEtudiants.setBounds(45, 0, 344, 38);
        txtpnGestionDesEtudiants.setFont(new Font("Tahoma", Font.ITALIC, 24));
        txtpnGestionDesEtudiants.setForeground(new Color(0, 0, 0));
        txtpnGestionDesEtudiants.setBackground(new Color(255, 255, 255));
        txtpnGestionDesEtudiants.setText("Gestion des Utilisateurs");
        panel.add(txtpnGestionDesEtudiants);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(10, 47, 842, 2);
        panel.add(separator);
        
        table = new JTable();
        table.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null, "", null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        	},
        	new String[] {
        		"Prenom", "Nom", "Genre", "Cit\u00E9 ", "Date de naiss", "Adresse"
        	}
        ));
        table.setBounds(366, 100, 506, 162);
        contentPane.add(table);
        
        JPanel panel_1 = new JPanel();
        panel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "User", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 153, 153)));
        panel_1.setBounds(10, 71, 346, 318);
        contentPane.add(panel_1);
        panel_1.setLayout(null);
        
        JLabel lblFirstName = new JLabel("Prénom:");
        lblFirstName.setFont(new Font("Sylfaen", Font.PLAIN, 15));
        lblFirstName.setBounds(10, 27, 89, 22);
        panel_1.add(lblFirstName);
        
        textFieldFirstName = new JTextField();
        textFieldFirstName.setBounds(78, 26, 223, 23);
        panel_1.add(textFieldFirstName);
        textFieldFirstName.setColumns(10);
        
        JLabel lblLastName = new JLabel("Nom:");
        lblLastName.setFont(new Font("Sylfaen", Font.PLAIN, 15));
        lblLastName.setBounds(10, 60, 89, 22);
        panel_1.add(lblLastName);
        
        textFieldLastName = new JTextField();
        textFieldLastName.setBounds(78, 60, 223, 23);
        panel_1.add(textFieldLastName);
        textFieldLastName.setColumns(10);
        
        JLabel lblGender = new JLabel("Genre:");
        lblGender.setFont(new Font("Sylfaen", Font.PLAIN, 15));
        lblGender.setBounds(10, 90, 89, 22);
        panel_1.add(lblGender);
        
        textFieldGender = new JTextField();
        textFieldGender.setBounds(78, 89, 223, 23);
        panel_1.add(textFieldGender);
        textFieldGender.setColumns(10);
        
        JLabel lblCity = new JLabel("Cité:");
        lblCity.setFont(new Font("Sylfaen", Font.PLAIN, 15));
        lblCity.setBounds(10, 123, 89, 22);
        panel_1.add(lblCity);
        
        textFieldCity = new JTextField();
        textFieldCity.setBounds(78, 122, 223, 23);
        panel_1.add(textFieldCity);
        textFieldCity.setColumns(10);
        
        JLabel lblBirthDate = new JLabel("Date de naissance:");
        lblBirthDate.setFont(new Font("Sylfaen", Font.PLAIN, 15));
        lblBirthDate.setBounds(10, 156, 120, 22);
        panel_1.add(lblBirthDate);
        
        textFieldBirthDate = new JTextField();
        textFieldBirthDate.setBounds(150, 155, 151, 23);
        panel_1.add(textFieldBirthDate);
        textFieldBirthDate.setColumns(10);
        
        JLabel lblAddress = new JLabel("Adresse:");
        lblAddress.setFont(new Font("Sylfaen", Font.PLAIN, 15));
        lblAddress.setBounds(10, 189, 89, 22);
        panel_1.add(lblAddress);
        
        textAreaAddress = new JTextArea();
        textAreaAddress.setBounds(78, 186, 223, 57);
        panel_1.add(textAreaAddress);
        
        JButton btnAdd = new JButton("Ajouter");
        btnAdd.setBounds(88, 250, 89, 23);
        btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnAdd.setBackground(new Color(255, 255, 255));
        btnAdd.addActionListener(e -> addUser());
        panel_1.add(btnAdd);
        
        JButton btnDelete = new JButton("Supprimer");
        btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnDelete.setBackground(Color.WHITE);
        btnDelete.setBounds(88, 284, 89, 23);
        btnDelete.addActionListener(e -> deleteUser());
        panel_1.add(btnDelete);
        
        JButton btnModify = new JButton("Modifier");
        btnModify.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnModify.setBackground(Color.WHITE);
        btnModify.setBounds(212, 250, 89, 23);
        btnModify.addActionListener(e -> updateUser());
        panel_1.add(btnModify);
        
        JButton btnClear = new JButton("Effacer");
        btnClear.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnClear.setBackground(Color.WHITE);
        btnClear.setBounds(212, 284, 89, 23);
        btnClear.addActionListener(e -> clearFields());
        panel_1.add(btnClear);
    }

    //la methode ajouter
    private void addUser() {
        User newUser = new User();
        newUser.setLastName(textFieldLastName.getText());
        newUser.setPassword("defaultPassword"); // Or use a password input
        // Set other user attributes as needed...

        try {
            UserService service = new UserService();
        String response = service.addUser(newUser, authToken);
        JOptionPane.showMessageDialog(this, response);
    } catch (WebServiceException e) {
        JOptionPane.showMessageDialog(this, "Error adding user: " + e.getMessage());
    }}

    //la methode supprimer
    private void deleteUser() {
        String username = textFieldLastName.getText(); // Assuming last name is used as username

        try {
        	UserService service = new UserService();
            String response = service.deleteUser(username, authToken);
            JOptionPane.showMessageDialog(this, response);
        } catch (WebServiceException e) {
            JOptionPane.showMessageDialog(this, "Error deleting user: " + e.getMessage());
        }
    }

  //la methode modifier
    private void updateUser() {
        User updatedUser = new User();
        updatedUser.setLastName(textFieldLastName.getText());
        updatedUser.setPassword("updatedPassword"); // Update as necessary
        // Set other user attributes as needed...

        try {
        	  UserService service = new UserService();
              String response = service.updateUser(updatedUser, authToken);
            JOptionPane.showMessageDialog(this, response);
        } catch (WebServiceException e) {
            JOptionPane.showMessageDialog(this, "Error updating user: " + e.getMessage());
        }
    }

    //la methode effacer
    private void clearFields() {
        textFieldFirstName.setText("");
        textFieldLastName.setText("");
        textFieldGender.setText("");
        textFieldCity.setText("");
        textFieldBirthDate.setText("");
        textAreaAddress.setText("");
    }
    
    private void loadUsers() {
    	
    	 DefaultTableModel model = new DefaultTableModel();
    	    Object[] column = {"Prénom", "Nom", "Genre", "Cité", "Date de naiss", "Adresse"};
    	    model.setColumnIdentifiers(column);
        try {
            UserService service = new UserService();
            List<User> users = service.listUsers(authToken); // Récupérer la liste des utilisateurs

         // Récupérer les utilisateurs et les ajouter au modèle
           
                for (User user : users) {
                    Object[] row = {
                        user.getFirstName(),
                        user.getLastName(),
                        user.getGender(),
                        user.getCity(),
                        new SimpleDateFormat("yyyy-MM-dd").format(user.getBirthDate()), // Format de la date
                        user.getAddress()
                    };
                    model.addRow(row);
                }

                table.setModel(model); // Définir le modèle de la table
            } catch (WebServiceException e) {
                JOptionPane.showMessageDialog(this, "Error loading users: " + e.getMessage());
            }
        
    }
}
