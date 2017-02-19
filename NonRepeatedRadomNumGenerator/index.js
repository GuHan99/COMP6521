/**
 * Created by ERIC_LAI on 2017-01-25.
 */

var fs = require('fs');
var random = require('random-number-generator');
var readline = require('readline');


// the number of non-repeated random number
var numOfT1 = 500000;
var numOfT2 = 1000000;
// the file path
var path1 = './tt1.txt';
var path2 = './tt2.txt';
var path3 = './nameList.txt';

var nameList = [];

var rl = readline.createInterface({
    input: fs.createReadStream(path3)
});

rl.on('line', function (line) {
    nameList.push(line);
});

rl.on('close', function () {
    createNonRepeatRandomNum(path1, numOfT1);
    createNonRepeatRandomNum(path2, numOfT2);
});


function createNonRepeatRandomNum(path, numOfItem) {
    var map = new Map();
    var index = 0;
    var len = nameList.length;
    fs.open(path, 'w', function (err, fd) {
        for (var j = 0; j < numOfItem; j++) {
            var num = getRandomNum();
            while (checkExistance(num, map)) {
                num = getRandomNum();
            }
            map.set(num, num);
            if (index > len - 1) index = 0;
            var nameStr = nameList[index++];
            var nameCharNum = nameStr.length;
            for (var i = nameCharNum; i <= 12; i++) {
                nameStr += ' ';
            }
            var otherStr = '101234567891455 Maisonneuve West, Montreal, QC, H3G 1M8';
            fs.write(fd, num + nameStr + otherStr + '\n');
        }
    });
}

function getRandomNum() {
    return random(9999999, 1000000);
}

function checkExistance(num, map) {
    if (map.get(num) != undefined) {
        return true
    }
    return false
}

