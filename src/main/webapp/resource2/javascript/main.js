'use strict';
function isSure() {
    let sure = confirm("Are you sure you want to delete the user? " +
        "Cancel the action will not be possible.");
    console.log(sure);
    if(sure) {
        location.href = '/button?command=USER_DELETE';
    }
}

function changeBackground() {
    let myBackground = document.getElementsByClassName('myBackground')[0];
    console.log(myBackground);
    myBackground.style.backgroundImage = "url('/resource2/img/background/" + getRandomInt(1, 6) + ".jpg')";
}

function getRandomInt(min, max) {
    return Math.floor(Math.random() * (max - min + 1)) + min;
}

let timer  = setInterval(changeBackground, 15000);
