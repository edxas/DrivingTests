

var pressed = false;
function backButtonEvent() {
 console.log("Browser back button is clicked...");
 pressed = true;
}
$(document).ready(function(){
	// Enable popovers everywhere
    $('[data-bs-toggle="popover"]').popover();
});




