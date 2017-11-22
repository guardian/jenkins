module.exports = {
    parser: 'babel-eslint',
    extends: ['airbnb', 'prettier'],
    plugins: ['flowtype', 'prettier'],
    rules: {
        'import/no-extraneous-dependencies': 'off',
        // prettier settings
        'prettier/prettier': [
            'error',
            {
                trailingComma: 'none',
                singleQuote: true,
                bracketSpacing: true,
                tabWidth: 2,
                parser: 'flow',
            },
        ],
        'no-extend-native': 'error',
        'func-style': ['error', 'expression', { allowArrowFunctions: true }],

        'prefer-destructuring': 'off',
    },
    // don't look for eslintrcs above here
    root: true,
};