// src/services/articleService.js
const client = require('../db');

const getArticles = async () => {
  try {
    const res = await client.query('SELECT * FROM articles');
    return res.rows;
  } catch (err) {
    console.error('Erreur lors de la récupération des articles', err.stack);
    throw err;
  }
};

module.exports = {
  getArticles,
};
