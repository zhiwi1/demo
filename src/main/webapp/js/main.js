//lol\

// DOM Elements
const time = document.getElementById('time'),
    greeting = document.getElementById('greeting');
let x = 0;
let statIndex = -1;
let background = document.querySelector('body');
// Options
// const showAmPm = true;
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


const btnText = document.querySelector('.btn-text');

// если в ссылке заменить lang=en на lang=ru, цитаты будут на русском языке
// префикс https://cors-anywhere.herokuapp.com используем для доступа к данным с других сайтов если браузер возвращает ошибку Cross-Origin Request Blocked
// async function getQuote() {
//     const url = ' https://cors-anywhere.herokuapp.com/https://api.forismatic.com/api/1.0/?method=getQuote&format=json&lang=en';
//     const res = await fetch(url);
//     const data = await res.json();
//     blockquote.textContent = data.quoteText;
//     figcaption.textContent = data.quoteAuthor;
// }
// Show Time
function showTime() {
    let today = new Date(),

        hour = today.getHours(),
        min = today.getMinutes(),
        sec = today.getSeconds();
    // Output Time
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
        if (x == 0 || x == 3 || x == 2) {
            document.body.style.color = 'white';
            colorLinks('#ffffff');
        } else {
            colorLinks('#000000');
            document.body.style.color = 'black';
        }
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
    if (x == 0 || x == 3 || x == 2) {
        document.body.style.color = 'white';

        colorLinks('#ffffff');
    } else {
        colorLinks('#000000');
        document.body.style.color = 'black';
    }
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

//Name of day pf the week

//Add Zeros
function addZero(n) {

    return (parseInt(n, 10) < 10 ? '0' : '') + n;
}

//Set Background and Greeting
function setBgGreet() {
    let today = new Date(),
        hour = today.getHours();

    if (hour < 6) {
        // Night
        x = 0;
        getImage();
        greeting.textContent = 'Good Night'

    } else if (hour < 12) {
        // Morning
        x = 1;
        getImage();
        greeting.textContent = 'Good Morning';
    } else if (hour < 18) {
        // Afternoon
        x = 2;
        getImage();
        greeting.textContent = 'Good Afternoon';
    } else {
        //evening
        x = 3;
        getImage();
        greeting.textContent = 'Good Evening';

    }
    setTimeout(setBgGreet, 3600000);
}

function getCity() {
    if (localStorage.getItem('city') === null || localStorage.getItem('city') == '') {
        city.textContent = '[Enter Name]';
    } else {
        city.textContent = localStorage.getItem('city');
    }
}

// Set Name
function setCity(e) {
    if (e.type === 'keypress') {
        // Make sure enter is pressed
        if (e.which == 13 || e.keyCode == 13) {
            if (city.innerText.length == 0 || !city.innerText.trim()) {
                city.innerText = localStorage.getItem('city');
            } else {
                localStorage.setItem('city', e.target.innerText);
                getWeather();
                city.blur();
            }
        }
    } else {
        if (city.innerText.length == 0 || !city.innerText.trim()) {
            city.innerText = localStorage.getItem('city');
        }
        getWeather();
        localStorage.setItem('city', e.target.innerText);
    }
}

// Get Focus
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

        let command = window.location.search.match(/command=([a-z_])+/i)

        let currentPageLink = window.location.pathname + ((command != null) ? `?${command[0]}&` : '?');

        paginationItems.forEach((link) => {
            let page = link.innerText;
            link.href = currentPageLink + "page=" + page;
        });
    }
}
// const createLinksForPaginationComments = () => {
//     let paginationItems = document.querySelectorAll("a.pagination__item");
//
//     if (paginationItems.length > 0) {
//
//         let command = window.location.search.match(/command=([a-z_])+/i)
//         let currentPageLink = window.location.pathname + ((command != null) ? `?${command[0]}&` : '?');
//
//         paginationItems.forEach((link) => {
//             let page = link.innerText;
//             link.href = currentPageLink + "comment_page=" + page;
//         });
//     }
// }
document.addEventListener('DOMContentLoaded', getWeather);

city.addEventListener('keypress', setCity);
city.addEventListener('blur', setCity);


// Run
showTime();

setBgGreet();

getCity();
getWeather();
btn.addEventListener('click', getImage);
createLinksForPagination();
