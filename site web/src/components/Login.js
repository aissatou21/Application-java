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
    const [loading, setLoading] = useState(false); // State for loading indicator
    const navigate = useNavigate();

    useEffect(() => {
        fetchCategories();
        fetchArticles(currentPage, selectedCategory, searchTerm);
    }, [currentPage, selectedCategory, searchTerm]);

    const fetchCategories = () => {
        setLoading(true);
        axios.get('/api/categories')
            .then(response => {
                setCategories(response.data);
                setLoading(false);
            })
            .catch(error => {
                console.error('Erreur lors de la récupération des catégories :', error);
                setLoading(false);
            });
    };

    const fetchArticles = (page, category, term) => {
        setLoading(true);
        let url = `/api/articles?page=${page}`;
        if (category) url += `&category=${category}`;
        if (term) url += `&search=${term}`;

        axios.get(url)
            .then(response => {
                setArticles(response.data.articles);
                setTotalPages(response.data.totalPages);
                setLoading(false);
            })
            .catch(error => {
                console.error('Erreur lors de la récupération des articles :', error);
                setLoading(false);
            });
    };

    const handleNextPage = () => {
        if (currentPage < totalPages) setCurrentPage(currentPage + 1);
    };

    const handlePreviousPage = () => {
        if (currentPage > 1) setCurrentPage(currentPage - 1);
    };

    const handleArticleClick = (id) => {
        navigate(`/article/${id}`);
    };

    const handleCategoryChange = (event) => {
        setSelectedCategory(event.target.value);
        setCurrentPage(1); // Réinitialisation à la première page en changeant la catégorie
    };

    const handleSearchChange = (event) => {
        setSearchTerm(event.target.value);
    };

    const handleSearchSubmit = (event) => {
        event.preventDefault();
        setCurrentPage(1); // Réinitialisation à la première page lors de la recherche
    };

    return (
        <div className="home-container">
            <section className="articles">
                <h1>Derniers Articles</h1>

                {loading && <p>Loading...</p>}

                <ul className="article-list">
                    {articles.map(article => (
                        <li key={article.id} className="article-item">
                            <button onClick={() => handleArticleClick(article.id)}>{article.title}</button>
                            <p>{article.summary}</p>
                        </li>
                    ))}
                </ul>

                {totalPages > 1 && (
                    <div className="pagination">
                        <button onClick={handlePreviousPage} disabled={currentPage === 1}>Précédent</button>
                        <span>Page {currentPage} de {totalPages}</span>
                        <button onClick={handleNextPage} disabled={currentPage === totalPages}>Suivant</button>
                    </div>
                )}
            </section>
        </div>
    );
};

export default Home;
