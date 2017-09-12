const path = require('path');

const babelOptions = {
  "presets": ["env"]
};

module.exports = {  
  entry: {
    guide: './guide/article/index.js',
    qanda: './qanda/article/index.js',
    profile: './profile/article/index.js',
    timeline: './timeline/article/index.js',
  },
  output: {
    filename: '[resource-path]/[name].transpiled.js',
    path: path.resolve(__dirname, 'src', 'main', 'resources'),
    libraryTarget: 'var'
  },
  resolve: {
      // Add '.ts' and '.tsx' as a resolvable extension.
      extensions: [".ts", ".tsx", ".js"]
  },
  module: {
    rules: [{
      test: /\.ts(x?)$/,
      exclude: /node_modules/,
      use: [
        {
          loader: 'babel-loader',
          options: babelOptions
        },
        {
          loader: 'ts-loader'
        }
      ]
    }, {
      test: /\.js$/,
      exclude: /node_modules/,
      use: [
        {
          loader: 'babel-loader',
          options: babelOptions
        }
      ]
    }]
  },
  context: path.resolve(__dirname, 'src', 'main', 'resources')
};
