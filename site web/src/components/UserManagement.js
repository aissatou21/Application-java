import React, { useState, useEffect } from 'react';
import axios from 'axios';

const UserManagement = () => {
    const [users, setUsers] = useState([]);

    useEffect(() => {
        // Chargement initial des utilisateurs depuis le backend
        axios.get('/api/users')
            .then(response => setUsers(response.data))
            .catch(error => console.error('Erreur lors du chargement des utilisateurs :', error));
    }, []);

    const deleteUser = (userId) => {
        // Supprimer un utilisateur via une requête DELETE
        axios.delete(`/api/users/${userId}`)
            .then(response => {
                console.log('Utilisateur supprimé avec succès :', response.data);
                // Mettre à jour la liste des utilisateurs après la suppression
                setUsers(users.filter(user => user.id !== userId));
            })
            .catch(error => console.error('Erreur lors de la suppression de l\'utilisateur :', error));
    };

    return (
        <div>
            <h2>Gestion des Utilisateurs</h2>
            <ul>
                {users.map(user => (
                    <li key={user.id}>
                        <strong>Nom d'utilisateur :</strong> {user.username}
                        {/* Affiche le bouton "Supprimer" uniquement pour les administrateurs */}
                        {localStorage.getItem('role') === 'admin' && (
                            <button onClick={() => deleteUser(user.id)}>Supprimer</button>
                        )}
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default UserManagement;
