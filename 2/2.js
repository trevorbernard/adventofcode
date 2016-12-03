var lookup = {
  1 : {'U': 1, 'D':4, 'L': 1, 'R': 2},
  2 : {'U': 2, 'D':5, 'L': 1, 'R': 3},
  3 : {'U': 3, 'D':6, 'L': 2, 'R': 3},
  4 : {'U': 1, 'D':7, 'L': 4, 'R': 5},
  5 : {'U': 2, 'D':8, 'L': 4, 'R': 6},
  6 : {'U': 3, 'D':9, 'L': 5, 'R': 6},
  7 : {'U': 4, 'D':7, 'L': 7, 'R': 8},
  8 : {'U': 5, 'D':8, 'L': 7, 'R': 9},
  9 : {'U': 6, 'D':9, 'L': 8, 'R': 9}
};

var lookup2 = {
  1   : {'D':3},
  2   : {'D':6,'R':3},
  3   : {'U':1, 'D':7, 'L':2, 'R':4},
  4   : {'D':8, 'L':3},
  5   : {'R':6},
  6   : {'U':2, 'D':'A', 'L':5, 'R':7},
  7   : {'U':3, 'D':'B', 'L':6, 'R':8},
  8   : {'U':4, 'D':'C', 'L':7, 'R':9},
  9   : {'L':8},
  'A' : {'U':6, 'R':'B'},
  'B' : {'U':7, 'D':'D', 'L':'A', 'R':'C'},
  'C' : {'U':8, 'L':'B'},
  'D' : {'U':'B'}
};

var lineReader = require('readline').createInterface({
  input: process.stdin
});

var num = 5;
lineReader.on('line', function (line) {
  line = line.trim();
  for(var i=0; i<line.length; i++) {
    //num = lookup[num][line.charAt(i)];
    if( line.charAt(i) in lookup2[num]) {
      num = lookup2[num][line.charAt(i)];
    }
  }
  process.stdout.write(num + '');
});
lineReader.on('close', function() {
  console.log();
});
