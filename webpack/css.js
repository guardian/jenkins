module.exports = () => [
  {
    test: /stylesheets\/atoms\.scss$/,
    use: ['babel-loader']
  },
  {
    test: /@guardian\/atom-renderer\/dist\/.+\.css$/,
    use: [
      'style-loader',
      'css-loader',
      {
        loader: 'postcss-loader',
        options: {
          ident: 'postcss',
          plugins: () => [
            require('postcss-import')(),
            require('autoprefixer')()
          ]
        }
      }
    ]
  }
];
