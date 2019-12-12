function Garden(ctx, element) {
   this.blooms = [];
   this.element = element;
   this.ctx = ctx;
}



Garden.prototype = {
   render() {

      for (var i = 0; i < this.blooms.length; i++) {
         this.blooms[i].draw();

      }

   },
   addBloom(blooms) {
      this.blooms.push(blooms)
   },
   removeBloom: function (b) {
      var bloom;
      for (var i = 0; i < this.blooms.length; i++) {
         bloom = this.blooms[i];
         if (bloom === b) {
            this.blooms.splice(i, 1);
            return this;
         }
      }
   },
}




let isFirst = true;
function Bloom(p, r, c, pc, garden) {
   this.p = p; // 坐标
   this.r = r; // 大小
   this.c = c; // 颜色
   this.pc = pc; // 花瓣数量
   this.petals = [];
   this.garden = garden;
   this.init();
   this.garden.addBloom(this);
}

Bloom.prototype = {
   draw: function () {
      var p, isfinished = true;

      this.garden.ctx.save();
      this.garden.ctx.translate(this.p.x, this.p.y);
      for (var i = 0; i < this.petals.length; i++) {
         p = this.petals[i];
         p.render();
         isfinished *= p.isfinished;
      }

      this.garden.ctx.restore();
      if (isfinished == true) {
         this.garden.removeBloom(this);
      }
   },
   init() {
      var angle = 360 / this.pc;
      var startAngle = Config.randomInt(0, 90);

      for (var i = 0; i < this.pc; i++) {
         this.petals.push(
            new Petal(
               Config.random(Config.petalStretch.min, Config.petalStretch.max),
               Config.random(Config.petalStretch.min, Config.petalStretch.max),
               startAngle + i * angle,
               angle,
               Config.random(Config.growFactor.min, Config.growFactor.max), this));
      }

      console.error(this.petals)
   }
}



function Petal(stretchA, stretchB, startAngle, angle, growFactor, bloom) {
   this.stretchA = stretchA;
   this.stretchB = stretchB;
   this.startAngle = startAngle;
   this.angle = angle;
   this.bloom = bloom;
   this.growFactor = growFactor;
   this.r = 1;
   this.isfinished = false;
   //this.tanAngleA = Garden.random(-Garden.degrad(Config.tanAngle), Garden.degrad(Config.tanAngle));
   //this.tanAngleB = Garden.random(-Garden.degrad(Config.tanAngle), Garden.degrad(Config.tanAngle));
}


Petal.prototype = {
   draw: function () {
      var ctx = this.bloom.garden.ctx;
      var v1, v2, v3, v4;
      v1 = new Vector(0, this.r).rotate(Config.degrad(this.startAngle));
      v2 = v1.clone().rotate(Config.degrad(this.angle));
      v3 = v1.clone().mult(this.stretchA); //.rotate(this.tanAngleA);
      v4 = v2.clone().mult(this.stretchB); //.rotate(this.tanAngleB);
      ctx.strokeStyle = this.bloom.c;


      ctx.beginPath();
      ctx.moveTo(v1.x, v1.y);
      ctx.bezierCurveTo(v3.x, v3.y, v4.x, v4.y, v2.x, v2.y);
      ctx.stroke();
   },
   render: function () {
      if (this.r <= this.bloom.r) {
         this.r += this.growFactor; // / 10;
         this.draw();
         // console.error("111")
      } else {
         this.isfinished = true;
      }
   }
}



function Vector(x, y) {
   this.x = x;
   this.y = y;
};

Vector.prototype = {
   rotate: function (theta) {
      var x = this.x;
      var y = this.y;
      this.x = Math.cos(theta) * x - Math.sin(theta) * y;
      this.y = Math.sin(theta) * x + Math.cos(theta) * y;
      return this;
   },
   mult: function (f) {
      this.x *= f;
      this.y *= f;
      return this;
   },
   clone: function () {
      return new Vector(this.x, this.y);
   },
   length: function () {
      return Math.sqrt(this.x * this.x + this.y * this.y);
   },
   subtract: function (v) {
      this.x -= v.x;
      this.y -= v.y;
      return this;
   },
   set: function (x, y) {
      this.x = x;
      this.y = y;
      return this;
   }
};
