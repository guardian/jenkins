module.exports = {
  parser: "@babel/eslint-parser",
  extends: ["airbnb", "plugin:prettier/recommended"],
  plugins: ["flowtype"],
  rules: {
    "import/no-extraneous-dependencies": "off",
    "no-use-before-define": "off",

    // prettier settings
    "prettier/prettier": ["error"],
    "no-extend-native": "error",
    "func-style": ["error", "expression", { allowArrowFunctions: true }]
  },
  // don't look for eslintrcs above here
  root: true
};
