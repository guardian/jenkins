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
  devServer: {
    contentBase: path.join(__dirname, "dist", atomType),
    compress: true,
    port: 9000
  },
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
  },
});

module.exports = atomTypes.map(createJsSettings('article'));
