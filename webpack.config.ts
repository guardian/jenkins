const path = require('path');
// const UglifyJSPlugin = require('uglifyjs-webpack-plugin');
const fs = require('fs');

const isAtomType = (s: string): boolean => {
  const atomTypes: string[] = [
    'cta',
    'explainer',
    'guide',
    'interactive',
    'media',
    'profile',
    'qanda',
    'quiz',
    'recipe',
    'review',
    'storyquestions',
    'timeline'
  ];
  return atomTypes.includes(s);
}

const commonSettings = {
  resolve: {
    extensions: [".ts"]
  },
  module: {
    rules: [{
      test: /\.ts$/,
      exclude: /node_modules/,
      use: [
        {
          loader: 'babel-loader'
        },
        {
          loader: 'ts-loader'
        }
      ]
    }]
  },
  context: path.resolve(__dirname, 'src', 'main', 'resources'),
  // plugins: [
  //   new UglifyJSPlugin({
  //     parallel: true
  //   })
  // ]
};

const guides = Object.assign({  
  entry: {
    guide: './guide/article/index.ts',
  },
  output: {
    filename: '[name].js',
    path: path.resolve(__dirname, 'src', 'main', 'resources', 'guide', 'article'),
    libraryTarget: 'var'
  },
}, commonSettings);

const qandas = Object.assign({  
  entry: {
    qanda: './qanda/article/index.ts',
  },
  output: {
    filename: '[name].transpiled.js',
    path: path.resolve(__dirname, 'src', 'main', 'resources', 'qanda', 'article'),
    libraryTarget: 'var'
  },
}, commonSettings);

const profiles = Object.assign({  
  entry: {
    profile: './profile/article/index.ts',
  },
  output: {
    filename: '[name].transpiled.js',
    path: path.resolve(__dirname, 'src', 'main', 'resources', 'profile', 'article'),
    libraryTarget: 'var'
  },
}, commonSettings);

const timelines = Object.assign({  
  entry: {
    timeline: './timeline/article/index.ts',
  },
  output: {
    filename: '[name].transpiled.js',
    path: path.resolve(__dirname, 'src', 'main', 'resources', 'timeline', 'article'),
    libraryTarget: 'var'
  },
}, commonSettings);

module.exports = [
  guides,
  qandas,
  profiles,
  timelines
];


