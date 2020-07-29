var modal = document.getElementById('myModal');
// var modal = $modal();

var myBtn = document.getElementsByClassName('myBtn');
// var myBtn = document.getElementById('myBtn-1');
var close = document.getElementsByClassName('close')[0];

var restId;
function getRest(t) {
    restId = $(t);
    alert("REST ID JS = "+restId);
}
/*
А это в цикле прокруциваем те элементы которыми мы открываем модал окно
и обработчик события который открывет окно
*/
for (var i = 0; i < myBtn.length; i++) {
    myBtn[i].addEventListener('click', function() {
        openModalWindow();
    })
}
// myBtn.addEventListener('click', function () {
//     openModalWindow();
// })


// это обработчик события, который в нутри этой функции выполнят ту функцию
//которая закрывает окно модальное
close.addEventListener('click', function() {
    closeModalWindow();
})

//это функция, которая открывает окно
function openModalWindow() {
    modal.style.display = "block";
    // modal.show();
}

//это функция, которая закрывает окно
function closeModalWindow() {
    modal.style.display = "none";
}