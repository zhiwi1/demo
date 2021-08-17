function changeLocaleOnRU(event) {
    let currentPage = encodeURIComponent(window.location.pathname + window.location.search);
    document.location.href =  "controller?command=change_locale_command&locale=" + 'ru' + "&prev_request=" + currentPage;
}

function changeLocaleOnEn(event) {
    let currentPage = encodeURIComponent(window.location.pathname + window.location.search);
    document.location.href =  "controller?command=change_locale_command&locale=" + 'en' + "&prev_request=" + currentPage;

}