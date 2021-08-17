let valueMissingText = document.getElementsByClassName('valueMissing')[0].childNodes[0].textContent;
let typeMismatchText = document.getElementsByClassName('typeMismatch')[0].childNodes[0].textContent;
let patternMismatchingText = document.getElementsByClassName('patternMismatching')[0].childNodes[0].textContent;

$('input').on('input invalid', function () {
    this.setCustomValidity('')
    if (this.validity.valueMissing) {
        this.setCustomValidity(valueMissingText)
    }
    if (this.validity.typeMismatch) {
        this.setCustomValidity(typeMismatchText)
    }
    if (this.validity.patternMismatch) {
        this.setCustomValidity(patternMismatchingText)
    }
})