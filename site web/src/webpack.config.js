const path = require('path');

module.exports = {
  // Autres configurations Webpack

  resolve: {
    extensions: ['.js', '.jsx'],
    modules: [path.resolve(__dirname, 'src'), 'node_modules'],
    fallback: {
      fs: false, // Ne pas polyfiller fs
      net: false, // Ne pas polyfiller net
      dns: false, // Ne pas polyfiller dns
      tls: false, // Ne pas polyfiller tls
      crypto: require.resolve("crypto-browserify"), // Utiliser crypto-browserify pour polyfiller crypto
      stream: require.resolve("stream-browserify"), // Utiliser stream-browserify pour polyfiller stream
      timers: require.resolve("timers-browserify"), // Utiliser timers-browserify pour polyfiller timers
      path: require.resolve("path-browserify") // Utiliser path-browserify pour polyfiller path
    }
  },

  // Autres configurations Webpack
};
