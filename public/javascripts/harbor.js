
var animationsMap = new Map()

function f(x) {
    animationsMap.set(x,anime.timeline({
        loop: false
    }))
}


Array.from(document
.getElementsByClassName("o"))
.forEach(f)

function sendPost(divButton) {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", '/changeStatus', true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//    xhr.onreadystatechange = function() { // Call a function when the state changes.
//        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
//            // Request finished. Do processing here.
//        }
//    }
    xhr.send(`id=${divButton.id}`)
}

function goOut(divButton) {
    let anim = animationsMap.get(divButton);
    sendPost(divButton);
    console.log(anim.progress);
    function g(x) {
        anim.add(x);
    }
    if (anim.direction=="reverse" || anim.progress > 0) {
        anim.reverse();
    } else {
        calculateAnimeJs(divButton).forEach(g);
    }
    anim.play();
}


function goIn(divButton) {
    let anim = animationsMap.get(divButton);
    sendPost(divButton);
    function g(x) {
        anim.add(x);
    }
    if (divButton.style.visibility == "hidden") {
        divButton.style.visibility = "visible";
        calculateAnimeJs(divButton).forEach(g);
        anim.seek(anim.duration);
    }
    anim.reverse();
    anim.play();
}


function calculateAnimeJs(divButton) {
    let timeCoef = 1.5;
    let yOffset =  document.body.getBoundingClientRect().top - divButton.getBoundingClientRect().top;
    let xOffset = 270;
    let rotation = -90;
    if (divButton.parentElement.className.endsWith("leftstuff")) {
        xOffset = - xOffset;
        rotation = - rotation;
    }
    return [{
        targets: divButton,
        translateX: xOffset,
        translateY: 0,
        easing: 'easeInSine',
        duration: timeCoef*Math.abs(xOffset)
    },{
        targets: divButton,
        rotate: rotation,
        translateY: 0,
        easing: 'linear',
        duration: timeCoef*Math.abs(rotation)/180*Math.PI*200
    },{
        targets: divButton,
        rotate: rotation,
        easing: 'linear',
        translateX: xOffset,
        translateY: yOffset,
        duration: timeCoef*Math.abs(yOffset)
    }]
}







