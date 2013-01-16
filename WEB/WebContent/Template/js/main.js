$(document).ready(function() {
	var content = $('#content');
		
	//Main Navigation		
	var nav = $('#nav');
			
	nav.delegate('li','click.wl', function(event){
		var 	_this = $(this),
				_parent = _this.parent(),
				a = _parent.find('a');
				_parent.find('ul').slideUp('fast');
				a.removeClass('active');
				_this.find('ul:hidden').slideDown('fast');
				_this.find('a').eq(0).addClass('active');
				event.stopPropagation();
	});
	
    content.find('div.tab').tabs({
        fx: {
            opacity: 'toggle',
            duration: 'fast',
            height:'auto'
        }
    });
    content.find("table.datatable").dataTable({'bRetrieve' : true});
    $("select, textarea, input").not('input[type=submit], textarea.html').uniform();
    
    // Dessinage des charts
    $('table.chart').wl_Chart({});
});