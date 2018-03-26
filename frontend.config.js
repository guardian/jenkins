// @flow
const webpack = require('webpack');
const path = require('path');
const Uglify = require('uglifyjs-webpack-plugin');
const atomTypes = require('./webpack/atomTypes');

const createJsSettings = rendering => atomType => ({
  mode: 'production',
  entry: {
    [atomType]: `./${atomType}/${rendering}/index.fjs`
  },
  module: {
    rules: [
      {
        test: /\.fjs$/,
        exclude: /node_modules/,
        loader: 'babel-loader'
      }
    ]
  },
  resolve: {
    extensions: ['.fjs'],
    modules: [path.join(__dirname, 'core', 'src', 'main', 'resources', 'lib')]
  },
  context: path.resolve(__dirname, 'core', 'src', 'main', 'resources'),
  plugins: [
    new Uglify({
      parallel: true
    }),
    new webpack.EnvironmentPlugin(['NODE_ENV'])
  ],
  output: {
    filename: 'index.js',
    path: path.resolve(__dirname, 'dist', atomType, rendering),
    libraryTarget: 'commonjs',
    library: atomType
  }
});

module.exports = atomTypes.map(createJsSettings('article'));
