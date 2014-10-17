$(document).ready(function() {

    /* ======= jQuery Placeholder ======= */
    $('input, textarea').placeholder();    
    
    /* ======= jQuery FitVids - Responsive Video ======= */
    $(".video-container").fitVids();
    
    /* ======= FAQ accordion ======= */

    function toggleIcon(e) {
    $(e.target)
        .prev('.panel-heading')
        .find('.panel-title a')
        .toggleClass('active')
        .find("i.fa")
        .toggleClass('fa-plus-square fa-minus-square');
    }
    $('.panel').on('hidden.bs.collapse', toggleIcon);
    $('.panel').on('shown.bs.collapse', toggleIcon);
    
    /* ======= Fixed header when scrolled ======= */
    
    // $(window).bind('scroll', function() {
    //      if ($(window).scrollTop() > 50) {
    //          $('#header').addClass('navbar-fixed-top');
    //      }
    //      else {
    //          $('#header').removeClass('navbar-fixed-top');
    //      }
    // });
    
    
    
    /* ======= Style Switcher ======= */
    
    $('#config-trigger').on('click', function(e) {
        var $panel = $('#config-panel');
        var panelVisible = $('#config-panel').is(':visible');
        if (panelVisible) {
            $panel.hide();          
        } else {
            $panel.show();
        }
        e.preventDefault();
    });
    
    $('#config-close').on('click', function(e) {
        e.preventDefault();
        $('#config-panel').hide();
    });
    
    
    $('#color-options a').on('click', function(e) { 
        var $styleSheet = $(this).attr('data-style');
		$('#theme-style').attr('href', $styleSheet);	
				
		var $listItem = $(this).closest('li');
		$listItem.addClass('active');
		$listItem.siblings().removeClass('active');
		
		e.preventDefault();
		
	});

    $('#requestSubmit').click(function(e){
	e.preventDefault();
	$('#requestSubmit').html("<i class='fa fa-circle-o-notch fa-spin'></i> Submitting...");
	$.post('ciqmail', 
         $('#requestForm').serialize(), 
         function(data, status, xhr){
	     console.log(data);
      	     $('#requestModal').modal('hide');
           // do something here with response;
         });
});


});
