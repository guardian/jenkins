//@flow
const webpack = require('webpack');
const path = require('path');
const atomTypes = require('./atomTypes');

const createJsSettings = (rendering) => (atomType) => ({
  entry: {
    [atomType]: `./${atomType}/${rendering}/index.fjs`,
  },
  module: {
    rules: [{
      test: /\.fjs$/,
      exclude: /node_modules/,
      loader: 'babel-loader'
    }]
  },
  resolve: {
    extensions: [".fjs"],
    modules: ["js"] 
  },
  context: path.resolve(__dirname, '..', 'src', 'main', 'resources'),
  output: {
    filename: 'index.js',
    path: path.resolve(__dirname, '..', 'build', atomType, rendering),
    libraryTarget: 'this',
    library: atomType
  }
});

module.exports = atomTypes.map(createJsSettings('article'));


