// JavaScript Document
$(window).scroll(function() {
    if ($(this).scrollTop()) {
        $('#go-top:hidden').stop(true, true).fadeIn();
    } else {
        $('#go-top').stop(true, true).fadeOut();
    }
});