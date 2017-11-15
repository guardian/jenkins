module.exports = (varFile) => {
  const variables = require(`babel-loader!postcss-variables-loader!sass-loader!$varFile`)

  return [
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
                  require('autoprefixer')(),
                  require('postcss-css-variables')({ variables })
                ]
              }
            }
        ]
    },
  ]
};