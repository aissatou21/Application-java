import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const Home = () => {
    const [articles, setArticles] = useState([]);
    const [categories, setCategories] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const [totalPages, setTotalPages] = useState(1);
    const [selectedCategory, setSelectedCategory] = useState('');
    const [searchTerm, setSearchTerm] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        fetchCategories();
        fetchArticles(currentPage, selectedCategory, searchTerm);
    }, [currentPage, selectedCategory, searchTerm]);

    const fetchCategories = () => {
        axios.get('/api/categories')
            .then(response => setCategories(response.data))
            .catch(error => console.error('Erreur lors de la récupération des catégories :', error));
    };

    const fetchArticles = (page, category, term) => {
        let url = `/api/articles?page=${page}`;
        if (category) {
            url += `&category=${category}`;
        }
        if (term) {
            url += `&search=${term}`;
        }
        axios.get(url)
            .then(response => {
                setArticles(response.data.articles);
                setTotalPages(response.data.totalPages);
            })
            .catch(error => console.error('Erreur lors de la récupération des articles :', error));
    };

    const handleNextPage = () => {
        if (currentPage < totalPages) {
            setCurrentPage(currentPage + 1);
        }
    };

    const handlePreviousPage = () => {
        if (currentPage > 1) {
            setCurrentPage(currentPage - 1);
        }
    };

    const handleArticleClick = (id) => {
        navigate(`/article/${id}`);
    };

    const handleCategoryChange = (event) => {
        setSelectedCategory(event.target.value);
        setCurrentPage(1); // Reset to first page when changing category
    };

    const handleSearchChange = (event) => {
        setSearchTerm(event.target.value);
    };

    const handleSearchSubmit = (event) => {
        event.preventDefault();
        setCurrentPage(1); // Reset to first page when searching
    };

    return (
        <div className="home-container">
            <header className="header">
                <div className="header-content">
                    <div className="nav-links">
                        <a href="#">Accueil</a>
                        <a href="#">Connexion</a>
                    </div>
                    <div className="search-container">
                        <div className="search-wrapper">
                            <input
                                type="text"
                                placeholder="Rechercher des articles"
                                className="search-bar"
                                value={searchTerm}
                                onChange={handleSearchChange}
                            />
                            <span className="search-icon">
                                <img src="/path/to/search-icon.png" alt="Recherche" />
                            </span>
                        </div>
                    </div>
                </div>
            </header>
            <h1>Derniers Articles</h1>
            <ul>
                {articles.map(article => (
                    <li key={article.id}>
                        <a href="#" onClick={() => handleArticleClick(article.id)}>{article.title}</a>
                        <p>{article.summary}</p>
                    </li>
                ))}
            </ul>
            <div>
                <button onClick={handlePreviousPage} disabled={currentPage === 1}>Précédent</button>
                <button onClick={handleNextPage} disabled={currentPage === totalPages}>Suivant</button>
            </div>
        </div>
    );
};

export default Home;
