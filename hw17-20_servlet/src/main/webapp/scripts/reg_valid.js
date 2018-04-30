//alert("hi");
function showError(container, errorMessage) {
    container.className = 'error';
    var msgElem = document.createElement('span');
    msgElem.className = "error-message";
    msgElem.innerHTML = errorMessage;
    container.appendChild(msgElem);
}

function resetError(container) {
    container.className = '';
    if (container.lastChild.className == "error-message") {
        container.removeChild(container.lastChild);
    }
}

function changeColor(element) {
    element.style.backgroundColor = "#ffc8cd";

}
function normalizeColor(element) {
    element.style.backgroundColor = "#FFFFFF";
}

function validate(form) {
    var elems = form.elements;
    var errors =0;

    resetError(elems.name.parentNode);
    normalizeColor(elems.name);
    if (!elems.name.value) {
        errors++;
        changeColor(elems.name);
        elems.name.placeholder="! Введите Имя !";
    }else if(elems.name.value.length<4){
        errors++;
        showError(elems.name.parentNode, ' Имя меньше 4 символов.')
    }

    resetError(elems.pass1.parentNode);
    normalizeColor(elems.pass1);
    if (!elems.pass1.value) {
        errors++;
        changeColor(elems.pass1);
        elems.pass1.placeholder="! Введите пароль !";
    }

    resetError(elems.pass2.parentNode);
    normalizeColor(elems.pass2);
    if (!elems.pass2.value) {
        errors++;
        changeColor(elems.pass2);
        elems.pass2.placeholder="! Введите пароль !";
    } else if (elems.pass1.value != elems.pass2.value) {
        errors++;
        showError(elems.pass1.parentNode, ' Пароли не совпадают.');
        showError(elems.pass2.parentNode, ' Пароли не совпадают.');
    }

    resetError(elems.email.parentNode);
    normalizeColor(elems.email);
    if (!elems.email.value) {
        errors++;
        changeColor(elems.email);
        elems.email.placeholder="! Введите имэйл !";
    }
    if(errors==0){
        form.submit()
    }
}