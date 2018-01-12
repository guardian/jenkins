// simple scaffold that uses harness code to preview chnages locally

const express = require('express');
const path = require('path');
const app = express();
const atomTypes = require('./webpack/atomTypes');

const dataAtoms = require('./harness/data/all.js');

app.use(express.static(path.join(__dirname, 'dist')))
app.use(express.static(path.join(__dirname, 'harness')))

// add data to each page
atomTypes.map(atom => {
  app.get('/:atom', (req, res) => {
    res.send({
      "atom": atom,
      "data": dataAtoms[atom]
    })
  })
})

app.get('/qanda', (req, res) => {
  res.send({
    "data": dataAtoms[qanda]
  })
})

app.listen(3000, () => console.log('Preview Atom Renderer app listening on port 3000!'))
