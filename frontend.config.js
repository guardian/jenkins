// @flow
const webpack = require('webpack');
const path = require('path');
const Uglify = require('uglifyjs-webpack-plugin');
const atomTypes = require('./webpack/atomTypes');

const createJsSettings = rendering => atomType => ({
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
    modules: [
      path.join(__dirname, 'src', 'main', 'resources')
    ]
  },
  context: path.resolve(__dirname, 'src', 'main', 'resources'),
  plugins: [
    new Uglify({
      parallel: true
    }),
    new webpack.EnvironmentPlugin(['NODE_ENV'])
  ],
  output: {
    filename: 'index.js',
    path: path.resolve(__dirname, 'dist', atomType, rendering),
    // libraryTarget: 'commonjs',
    libraryTarget: 'var', // for previewing to work, this needs to be like this. Does it break production 
    library: atomType
  },
});

module.exports = atomTypes.map(createJsSettings('article'));
