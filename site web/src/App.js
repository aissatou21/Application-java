import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import Home from './components/Home';
import ArticleDetail from './components/ArticleDetail';
import Category from './components/Category';
import Login from './components/Login';
import ManageArticles from './components/ManageArticles';
import UserManagement from './components/UserManagement';

const App = () => {
  const userRole = localStorage.getItem('role'); // 'visitor', 'editor', 'admin'

  return (
    <Router>
      <div className="App">
        <header className="header">
          <div className="header-content">
            <div className="nav-links">
              <a href="/">SN-news</a>
              <a href="/category">Catégories</a>
              <a href="/login">Connexion</a>
              {userRole === 'editor' || userRole === 'admin' ? (
                <a href="/manage-articles">Gérer les Articles</a>
              ) : null}
              {userRole === 'admin' && (
                <a href="/manage-users">Gérer les Utilisateurs</a>
              )}
            </div>
            <div className="search-container">
              <input
                type="text"
                placeholder="Rechercher des articles"
                className="search-bar"
              />
              <button type="button" className="search-button">
                <img src="/path/to/search-icon.png" alt="Recherche" />
              </button>
            </div>
          </div>
        </header>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/article/:id" element={<ArticleDetail />} />
          <Route path="/category/:name" element={<Category />} />
          <Route path="/login" element={<Login />} />
          {userRole === 'editor' || userRole === 'admin' ? (
            <Route path="/manage-articles" element={<ManageArticles />} />
          ) : null}
          {userRole === 'admin' && (
            <Route path="/manage-users" element={<UserManagement />} />
          )}
          <Route path="*" element={<Navigate to="/" />} />
        </Routes>
      </div>
    </Router>
  );
};

export default App;
