const copy = require('recursive-copy');

copy('src/main/resources/__flow__', 'dist/__flow__', {
  overwrite: true,
  rename: filepath => filepath.replace('.fjs', '.js')
});
