module.exports = ({ cssVarsPath }) => [
  {
    test: /stylesheets\/atoms\.scss$/,
    use: [
      'babel-loader',
      'postcss-variables-loader', 
      'sass-loader',
    ]
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
            require('autoprefixer')(),
            require('postcss-css-variables')()
          ]
        }
      },
      {
        loader: 'sass-loader',
        options: {
          data: `@import '${cssVarsPath}';`
        }
      },
    ]
  }
];