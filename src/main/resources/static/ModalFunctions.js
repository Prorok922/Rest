function buttonWork() {

let linkArray = document.querySelectorAll('.js-open-modal');

//обработка клика для модального окна
linkArray.forEach(function (button) {
    button.addEventListener('click', function (event) {
        event.preventDefault();
        let typeForm = button.getAttribute("data-bs-toggle")

        let form = document.getElementById(typeForm);
        //получение данных из кнопки
        const buttonUserId = button.getAttribute('data-bs-userId')
        const buttonUserFirstName = button.getAttribute('data-bs-userFirstName')
        const buttonUserLastName = button.getAttribute('data-bs-userLastName')
        const buttonUserAge = button.getAttribute('data-bs-userAge')
        const buttonUserEmail = button.getAttribute('data-bs-userEmail')


        //получение переменных из формы
        let modalUserId;
        let modalUserFirstName;
        let modalUserLastName;
        let modalUserAge;
        let modalUserEmail;

        if (typeForm === "modalEdit"){
            modalUserId = form.querySelector('#id')
            modalUserFirstName = form.querySelector('#firstName')
            modalUserLastName = form.querySelector('#lastName')
            modalUserAge = form.querySelector('#age')
            modalUserEmail = form.querySelector('#email')
        } else {
            modalUserId = form.querySelector('#idDel')
            modalUserFirstName = form.querySelector('#firstNameDel')
            modalUserLastName = form.querySelector('#lastNameDel')
            modalUserAge = form.querySelector('#ageDel')
            modalUserEmail = form.querySelector('#emailDel')
        }

        //сохранение данных в переменные
        modalUserId.value = buttonUserId;
        modalUserFirstName.value = buttonUserFirstName;
        modalUserLastName.value = buttonUserLastName;
        modalUserAge.value = buttonUserAge;
        modalUserEmail.value = buttonUserEmail;
    });
});
}