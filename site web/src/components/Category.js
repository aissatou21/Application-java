import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams, Link } from 'react-router-dom';

const Category = () => {
    const { name } = useParams();
    const [articles, setArticles] = useState([]);
    const [loading, setLoading] = useState(false); // State for loading indicator

    useEffect(() => {
        setLoading(true);
        axios.get(`/api/categories/${name}/articles`)
            .then(response => {
                setArticles(response.data);
                setLoading(false);
            })
            .catch(error => {
                console.error('Erreur lors de la récupération des articles par catégorie :', error);
                setLoading(false);
            });
    }, [name]);

    return (
        <div>
            <h1>Articles dans la catégorie : {name}</h1>

            {loading && <p>Loading...</p>}

            <ul>
                {articles.map(article => (
                    <li key={article.id}>
                        <Link to={`/articles/${article.id}`}>{articles.titre}</Link>
                        <p>{articles.summary}</p>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default Category;
