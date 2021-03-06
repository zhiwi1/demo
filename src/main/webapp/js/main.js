const time = document.querySelector('time');
const background = document.querySelector('body');
let x = 0;
let statIndex = -1;
function colorLinks(hex) {
    let links = document.getElementsByTagName("a");
    for (let i = 0; i < links.length; i++) {
        if (links[i].href) {
            links[i].style.color = hex;
        }
    }
}


function getRandomInt(max) {
    return Math.floor(Math.random() * max);
}

function showTime() {
    let today = new Date(),
        hour = today.getHours(),
        min = today.getMinutes(),
        sec = today.getSeconds();
    time.innerHTML = `${addZero(hour)}<span>:</span>${addZero(min)}<span>:</span>${addZero(sec)}`;
    setTimeout(showTime, 1000);
}

const bases = ["assets/images/night/", 'assets/images/morning/', 'assets/images/day/', 'assets/images/evening/'];
let images = ['01.jpg', '02.jpg', '03.jpg', '04.jpg', '05.jpg', '06.jpg', '07.jpg', '08.jpg', '09.jpg', '10.jpg', '11.jpg', '12.jpg', '13.jpg', '14.jpg', '15.jpg', '16.jpg', '17.jpg', '18.jpg', '19.jpg', '20.jpg'];

let i = 0;
const body = document.querySelector('body');
const btn = document.querySelector('.btn');

function viewBgImage(src) {
    const img = new Image();
    img.src = src;
    img.onload = () => {
        body.style.backgroundImage = `url(${src})`;

        document.body.style.color = 'white';
        colorLinks('#ffffff');

    };
}

function shuffle(array) {
    var currentIndex = array.length, temporaryValue, randomIndex;
    while (0 !== currentIndex) {
        randomIndex = Math.floor(Math.random() * currentIndex);
        currentIndex -= 1;
        temporaryValue = array[currentIndex];
        array[currentIndex] = array[randomIndex];
        array[randomIndex] = temporaryValue;
    }
    return array;
}

function getImage() {

    document.body.style.color = 'white';

    colorLinks('#ffffff');

    let images2 = shuffle(images);
    let index = 0;
    do {
        index = getRandomInt(images2.length - 1);
    }
    while (statIndex == images2[index]);

    const imageSrc = bases[x] + images2[index];

    viewBgImage(imageSrc);
    statIndex = images2[index];
    i++;
    if (i == 20) {
        i = 0;
        x++;
    }
    if (x == 4) {
        x = 0;
    }
    btn.disabled = true;
    setTimeout(function () {
        btn.disabled = false
    }, 1000);
    setTimeout(getImage, 3600000);
}

function addZero(n) {

    return (parseInt(n, 10) < 10 ? '0' : '') + n;
}

function setBgGreet() {
    let today = new Date(),
        hour = today.getHours();

    if (hour < 6) {
        x = 0;
        getImage();
    } else if (hour < 12) {
        x = 1;
        getImage();
    } else if (hour < 18) {
        x = 2;
        getImage();
    } else {
        x = 3;
        getImage();
    }
    setTimeout(setBgGreet, 3600000);
}

function getCity() {
    if (localStorage.getItem('city') === null || localStorage.getItem('city') === '') {
        city.textContent = '[Enter Name]';
    } else {
        city.textContent = localStorage.getItem('city');
    }
}

function setCity(e) {
    if (e.type === 'keypress') {
        if (e.which === 13 || e.keyCode === 13) {
            if (city.innerText.length === 0 || !city.innerText.trim()) {
                city.innerText = localStorage.getItem('city');
            } else {
                localStorage.setItem('city', e.target.innerText);
                getWeather();
                city.blur();
            }
        }
    } else {
        if (city.innerText.length === 0 || !city.innerText.trim()) {
            city.innerText = localStorage.getItem('city');
        }
        getWeather();
        localStorage.setItem('city', e.target.innerText);
    }
}

const weatherIcon = document.querySelector('.weather-icon');
const temperature = document.querySelector('.temperature');
const weatherDescription = document.querySelector('.weather-description');
const city = document.querySelector('.city');
const weatherWind = document.querySelector('.weatherWind');
const airHumidity = document.querySelector('.airHumidity');

async function getWeather() {
    const url = `https://api.openweathermap.org/data/2.5/weather?q=${city.textContent}&lang=ru&appid=08f2a575dda978b9c539199e54df03b0&units=metric`;
    const res = await fetch(url);
    const data = await res.json();
    if (data.cod != '404') {
        weatherIcon.className = 'weather-icon owf';
        weatherIcon.classList.add(`owf-${data.weather[0].id}`);
        temperature.textContent = `${data.main.temp.toFixed(0)} C`;
        airHumidity.textContent = data.main.humidity + ' %';

    } else {
        weatherDescription.textContent = '';
        temperature.textContent = '';
        airHumidity.textContent = '';
        weatherWind.textContent = '';
        weatherIcon.className = null;
    }
}

const createLinksForPagination = () => {
    let paginationItems = document.querySelectorAll("a.pagination__item");
    if (paginationItems.length > 0) {
        paginationItems.forEach((link) => {
            let page = link.innerText;
            let str2 = window.location.href;
            console.log(str2);
            let str = str2.replace(/&page=\d+/, '');
            link.href = str + "&page=" + page;
        });
    }
}
var speed = 'slow';
$('html, body').hide();
$(document).ready(function () {
    $('html, body').fadeIn(speed, function () {
        $('a[href], button[href]').click(function (event) {
            var url = $(this).attr('href');
            if (url.indexOf('#') == 0 || url.indexOf('javascript:') == 0) return;
            event.preventDefault();
            $('html, body').fadeOut(speed, function () {
                window.location = url;
            });
        });
    });
});

const createLinksForPaginationComments = () => {
    let paginationItems = document.querySelectorAll("a.pagination_item_comment");

    if (paginationItems.length > 0) {


        paginationItems.forEach((link) => {
            let page = link.innerText;
            let str2 = window.location.href;
            console.log(str2);
            let str = str2.replace(/&comment_page=\d+/, '');
            link.href = str + "&comment_page=" + page;
        });
    }
}
document.addEventListener('DOMContentLoaded', getWeather);

city.addEventListener('keypress', setCity);
city.addEventListener('blur', setCity);

showTime();
setBgGreet();
getCity();
getWeather();
btn.addEventListener('click', getImage);
createLinksForPagination();
createLinksForPaginationComments();
