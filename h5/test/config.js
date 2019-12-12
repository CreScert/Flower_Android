
Config = {
 petalCount: {
  min: 8,
  max: 15
 },
 petalStretch: {
  min: 1,
  max: 3
 },
 growFactor: {
  min: 0.1,
  max: 1
 },
 bloomRadius: {
  min: 20,
  max: 30
 },
 density: 10,
 growSpeed: 1000 / 60,
 color: {
  rmin: 128,
  rmax: 255,
  gmin: 0,
  gmax: 128,
  bmin: 0,
  bmax: 128,
  opacity: 0.1
 },
 tanAngle: 60
};
Config.random = function (min, max) {
 return Math.random() * (max - min) + min;
};
Config.randomInt = function (min, max) {
 return Math.floor(Math.random() * (max - min + 1)) + min;
};
Config.circle = 2 * Math.PI;
Config.degrad = function (angle) {
 return Config.circle / 360 * angle;
};
Config.raddeg = function (angle) {
 return angle / Config.circle * 360;
};
Config.rgba = function (r, g, b, a) {
 return 'rgba(' + r + ',' + g + ',' + b + ',' + a + ')';
};
Config.randomrgba = function (rmin, rmax, gmin, gmax, bmin, bmax, a) {
 var r = Math.round(Config.random(rmin, rmax));
 var g = Math.round(Config.random(gmin, gmax));
 var b = Math.round(Config.random(bmin, bmax));
 var limit = 5;
 if (Math.abs(r - g) <= limit && Math.abs(g - b) <= limit && Math.abs(b - r) <= limit) {

  return Config.randomrgba(rmin, rmax, gmin, gmax, bmin, bmax, a);
 } else {
  return Config.rgba(r, g, b, a);
 }
};
