module.exports = {
    parser: 'babel-eslint',
    extends: ['airbnb', 'prettier'],
    plugins: ['flowtype', 'prettier'],
    rules: {
        'import/no-extraneous-dependencies': 'off',
        'no-use-before-define': 'off',

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
    },
    // don't look for eslintrcs above here
    root: true,
};