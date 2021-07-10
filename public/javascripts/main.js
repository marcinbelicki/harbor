//import anime from './libraries/anime-master/lib/anime.es.js';

var animationsMap = new Map()

function calculateAnimeJs(divButton) {
  var destTop = - divButton.getBoundingClientRect().top
  
}





function calculateAnimation(divButton, rect) {
    var destTop = - rect.top
    
    console.log(destTop)
    console.log(rect.top)
    var goLeft = 300
    var turn = 100*Math.PI/190*goLeft + goLeft
    var goUp = Math.abs(destTop)/190*goLeft + turn
    var aniArray = new Array
    if (divButton.parentElement.className.endsWith("leftstuff")) {
        aniArray =[
            { transform: 'translateY(0px) translateX(0px) rotate(0deg)'},
            { transform: 'translateY(0px) translateX(-190px) rotate(0deg)', offset: (goLeft/goUp)},
            { transform: 'translateY(0px) translateX(-190px) rotate(90deg)', offset: (turn/goUp)},
            { transform: `translateY(${destTop}px) translateX(-190px) rotate(90deg)`, offset: 1}
          ]
    } else {
        aniArray = [
            { transform: 'translateY(0px) translateX(0px) rotate(0deg)'},
            { transform: 'translateY(0px) translateX(190px) rotate(0deg)', offset: (goLeft/goUp)},
            { transform: 'translateY(0px) translateX(190px) rotate(-90deg)', offset: (turn/goUp)},
            { transform: `translateY(${destTop}px) translateX(190px) rotate(-90deg)`, offset: 1}
        ]
    }
    return [aniArray,goUp]

}

function changeColor(divButton) {
   var anim = calculateAnimation(divButton,divButton.getBoundingClientRect())
   divButton.animate(
       anim[0],
       {
           duration: anim[1],
           fill: 'forwards',
           easing: 'ease-in'
       }
   )
     return false
}
// var mapa = new Map()
// mapa.set(document.getElementById("1"),2)

// console.log(mapa.get(document.getElementById("1")))
// let anim2 = anime.timeline({
//                             loop: false,
//                             duration: 500,
//                             easing: 'linear',
                    
// });
// anim2.add(
//   {
//     targets: document.getElementById("1"),
//     translateX: -270
//   }
// )
// anim2.add(
//   {
//     targets: document.getElementById("1"),
//     rotate: 90
//   }
// )




// console.log(anim2)
//   anim2.play();
//   anim2.pause();
//   anim2.seek(500)
//   anim2.play();
// console.log(anim2.progress)


// var animation = anime({
//   targets: document.getElementById("1"),
//   translateX: 270,
//   duration: 4000,
//   loop: true,
//   delay: function(el, i) { return i * 200; },
//   easing: 'easeInOutSine'
// });

// function changeColor(divButton) {
//    animation = anime({
//                  targets: divButton,
//                  translateX: 10000,
//                  loop: true,
//                  delay: function(el, i) { return i * 200; },
//                  easing: 'easeInOutSine',
//                  duration: 4000
//                });
//       return false
// }


// function reverseColor(divButton, button) {
//   delete anim2
//   console.log(anim2)
//   console.log(mapa.get(divButton))
//   let anim = anime({targets: divButton, translateX: 0, autoplay:false, duration:1000, rotate: 0,easing: 'linear'}); 
//   anim.play()
//       return false
// }

function reverseColor(divButton, button) {
   console.log("cien")
   divButton.style.visibility = 'visible'
   var anim = calculateAnimation(divButton,button.getBoundingClientRect())
   divButton.animate(
       anim[0],
       {
           direction: 'reverse',
           duration: anim[1],
           fill: 'forwards',
           easing: 'ease-in'
       }
   )
     return false
}