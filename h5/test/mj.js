
/**
 * 开始动画
 */
let startHeartAnimation = function () {
  let interval = 50;
  let angle = 10;
  let heart = new Array();
  let garden = getGraden();


  let animationTimer = setInterval(function () {
    let bloom = getHeartPoint(angle);
    let draw = true;
    for (let i = 0; i < heart.length; i++) {
      let p = heart[i];
      let distance = Math.sqrt(Math.pow(p[0] - bloom[0], 2) + Math.pow(p[1] - bloom[1], 2));
      if (distance < Config.bloomRadius.max * 1.3) {
        draw = false;
        break;
      }
    }
    if (draw) {
      heart.push(bloom);
      // console.error(bloom) 
      createRandomBloom(bloom[0], bloom[1]);
      // clearInterval(animationTimer);
      // return
    }
    if (angle >= 30) {
      clearInterval(animationTimer);

    } else {
      angle += 0.2;
    }
  }, interval);

}

let gardenData;
let getGraden = function () {
  let garden = $("#garden");

  let gardenCanvas = garden[0];
  gardenCanvas.width = $("#loveHeart").width();
  gardenCanvas.height = $("#loveHeart").height()
  gardenCtx = gardenCanvas.getContext("2d");
  gardenCtx.globalCompositeOperation = "lighter";
  gardenData = new Garden(gardenCtx, gardenCanvas);

  setInterval(function () {
    gardenData.render()
  }, 1)
  return gardenData;
}




function getHeartPoint(angle) {
  let t = angle / Math.PI;
  let x = 10 * (16 * Math.pow(Math.sin(t), 3));
  let y = - 10 * (13 * Math.cos(t) - 5 * Math.cos(2 * t) - 2 * Math.cos(3 * t) - Math.cos(4 * t));
  return new Array(offsetX + x, offsetY + y);
}



let createRandomBloom = function (x, y) {
  createBloom(x, y,
    Config.randomInt(Config.bloomRadius.min, Config.bloomRadius.max), // 随机大小
    // 随机颜色
    Config.randomrgba(Config.color.rmin, Config.color.rmax, Config.color.gmin, Config.color.gmax, Config.color.bmin, Config.color.bmax, Config.color.opacity),
    // 随机花瓣数量
    Config.randomInt(Config.petalCount.min, Config.petalCount.max));
}
let createBloom = function (x, y, r, c, pc) {
  new Bloom(new Vector(x, y), r, c, pc, gardenData);
}
