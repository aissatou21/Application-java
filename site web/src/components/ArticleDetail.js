import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';

const ArticleDetail = () => {
    const { id } = useParams(); // Extracts the 'id' parameter from the URL
    const [article, setArticle] = useState(null); // State to hold the article data

    useEffect(() => {
        // Fetches the article data when the component mounts or 'id' changes
        axios.get(`/api/articles/${id}`)
            .then(response => setArticle(response.data))
            .catch(error => console.error('Erreur lors de la récupération de l\'article :', error));
    }, [id]); // Dependency array ensures useEffect runs when 'id' changes

    // Renders a loading message if article data is not yet fetched
    if (!article) return <div>Chargement...</div>;

    // Renders the article details once fetched
    return (
        <div className="article-detail-container">
            <h1 className="article-titre">{article.titre}</h1>
            <p className="article-content">{article.content}</p>
        </div>
    );
};

export default ArticleDetail;
