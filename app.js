// simple scaffold that uses harness code to preview chnages locally

const express = require('express');
const path = require('path');
const app = express();

app.use(express.static(path.join(__dirname,'dist')))
app.use(express.static(path.join(__dirname, 'harness')))


app.get('/', (req, res) => {
  res.send('')
})

app.listen(3000, () => console.log('Preview Atom Renderer app listening on port 3000!'))