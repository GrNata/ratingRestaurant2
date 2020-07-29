// Модальное окно

$(document).ready(function() {
    $('a[name=modal]').click(function(e) {
        e.preventDefault();
        var id = $(this).attr('href');
        var maskHeight = $(document).height();
        var maskWidth = $(window).width();
        $('#mask').css({'width':maskWidth,'height':maskHeight});
        $('#mask').fadeIn(1000);
        $('#mask').fadeTo("slow",0.8);
        var winH = $(window).height();
        var winW = $(window).width();
        $(id).css('top',  winH/2-$(id).height()/2);
        $(id).css('left', winW/2-$(id).width()/2);
        $(id).fadeIn(2000);
    });
    $('.window .close').click(function (e) {
        e.preventDefault();
        $('#mask, .window').hide();
    });
    $('#mask').click(function () {
        $(this).hide();
        $('.window').hide();
    });
});






// var myBtn = document.getElementById('myBtn');
//
// // Открыть по кнопке
// var modal = document.getElementById('myModal');
//
// myBtn.addEventListener('click', function () {
//     openModalWindow();
// })
// //это функция, которая открывает окно
// function openModalWindow() {
//     modal.style.display = "block";
// }
// это обработчик события, который в нутри этой функции выполнят ту функцию
//которая закрывает окно модальное
// close.addEventListener('click', function() {
//     closeModalWindow();
// })
//это функция, которая закрывает окно
// function closeModalWindow() {
//     modal.style.display = "none";
// }

// // function openWin() {
// $('.js-button-campaign').click(function () {
//     $('main').css('filter', 'blur(5px)');
//     $('.js-overlay-campaign').fadeIn();
//     $('.is-popup-campaign').addClass('disabled');
// });
//
// $('.js-button-campaign').click(function () {
//     $('main').css('filter', 'blur(5px)');
//     $('.js-overlay-campaign').fadeIn();
//     $('.js-overlay-campaign').addClass('disabled');
// });
//
// // Закрыть на крестик
// $('.js-close-campaign').click(function () {
//     $('.js-overlay-campaign').fadeOut();
//     $('main').css('filter', 'none');
// });
//
// // Закрыть по клику в окне
// $(document).mouseup(function (e) {
//     var popup = $('.js-popup-campaign');
//     if (e.target != popup[0]&&popup.has(e.target).length === 0) {
//         $('.js-overlay-campaign').fadeOut();
//         $('main').css('filter', 'none');
//     }
// });


//
//
// // Открыть окно по таймеру
// $(window).on('load', function () {
//     setTimeout(function () {
//         if ($('.js-overlay-campaign').hasClass('disabled')) {
//             return false;
//         } else {
//             $('main').css('filter', 'blur(5px)');
//             $('.js-overlay-campaign').fadeIn();
//         }
//     }, 2000);
// });
