//@flow
const webpack = require('webpack');
const path = require('path');
const Uglify = require('uglifyjs-webpack-plugin');
const atomTypes = require('./atomTypes');

const createJsSettings = (rendering) => (atomType) => ({
  entry: {
    [atomType]: `./${atomType}/${rendering}/index.js`,
  },
  module: {
    rules: [{
      test: /\.js$/,
      exclude: /node_modules/,
      loader: 'babel-loader'
    }]
  },
  context: path.resolve(__dirname, '..', 'src', 'main', 'resources'),
  plugins: [
    new Uglify({
      parallel: true
    }),
    new webpack.EnvironmentPlugin(['NODE_ENV'])
  ],
  output: {
    filename: 'index.js',
    path: path.resolve(__dirname, '..', 'build', atomType, rendering),
    libraryTarget: 'this',
    library: atomType
  }
});

module.exports = atomTypes.map(createJsSettings('article'));


