// Importer le module mysql
const mysql = require('mysql');

// Configurer la connexion à la base de données MySQL
const connection = mysql.createConnection({
  host: 'localhost',     // Adresse de l'hôte MySQL (par défaut: 'localhost')
  user: 'root',          // Nom d'utilisateur MySQL
  password: '',          // Mot de passe MySQL
  database: 'sn_news'  // Nom de votre base de données
});

// Établir la connexion à MySQL
connection.connect((err) => {
  if (err) {
    console.error('Erreur de connexion à MySQL : ' + err.stack);
    return;
  }
  console.log('Connecté à MySQL avec l\'identifiant ' + connection.threadId);
});

// Exporter la connexion MySQL pour l'utiliser dans d'autres parties de votre application
module.exports = connection;
