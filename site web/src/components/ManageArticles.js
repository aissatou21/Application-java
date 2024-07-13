import React, { useState, useEffect } from 'react';
import axios from 'axios';

const ManageArticles = ({ authToken }) => {
    const [articles, setArticles] = useState([]);
    const [newArticle, setNewArticle] = useState({ title: '', summary: '', content: '' });

    useEffect(() => {
        fetchArticles();
    }, []);

    const fetchArticles = () => {
        axios.get('/api/articles')
            .then(response => setArticles(response.data))
            .catch(error => console.error('Erreur lors de la récupération des articles :', error));
    };

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setNewArticle(prevState => ({ ...prevState, [name]: value }));
    };

    const handleAddArticle = () => {
        axios.post('/api/articles', newArticle, {
            headers: {
                Authorization: `Bearer ${authToken}`
            }
        })
        .then(response => {
            setArticles([...articles, response.data]);
            setNewArticle({ title: '', summary: '', content: '' });
        })
        .catch(error => console.error('Erreur lors de l\'ajout de l\'article :', error));
    };

    const handleDeleteArticle = (id) => {
        axios.delete(`/api/articles/${id}`, {
            headers: {
                Authorization: `Bearer ${authToken}`
            }
        })
        .then(() => setArticles(articles.filter(article => article.id !== id)))
        .catch(error => console.error('Erreur lors de la suppression de l\'article :', error));
    };

    return (
        <div>
            <h1>Gérer les Articles</h1>
            <ul>
                {articles.map(article => (
                    <li key={article.id}>
                        {article.title}
                        <button onClick={() => handleDeleteArticle(article.id)}>Supprimer</button>
                    </li>
                ))}
            </ul>
            <div>
                <h2>Ajouter un Article</h2>
                <input
                    type="text"
                    name="title"
                    placeholder="Titre"
                    value={newArticle.title}
                    onChange={handleInputChange}
                />
                <textarea
                    name="summary"
                    placeholder="Résumé"
                    value={newArticle.summary}
                    onChange={handleInputChange}
                />
                <textarea
                    name="content"
                    placeholder="Contenu"
                    value={newArticle.content}
                    onChange={handleInputChange}
                />
                <button onClick={handleAddArticle}>Ajouter</button>
            </div>
        </div>
    );
};

export default ManageArticles;
