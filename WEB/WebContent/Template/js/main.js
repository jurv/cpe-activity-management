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
});

function loadPage(thepage, element) {
	// if( $('#' + element).html() == '') {
	$.ajax({
		url : admin_root + 'ajax/' + thepage + '.php',
		type : 'GET',
		data : {},
		cache : false,
		beforeSend : function() {
			$('#loading').show();
		},
		success : function(data) {

			if (data == 'error') {
				$('#loading').hide();
				alert('Sorry we have some problem please try again');
				return false;
			} else {
				// $('.control_path').hide();
				// $('#' + element).show();
				$('#content').html(data);
				$('#loading').hide();
			}
			
			// Ex�cution des JS d'initialisation de la page charg�e
	        doJSWork();
		}
	});
}

function loadPage_nocache(thepage, element, projectid) {
	$.ajax({
		url : admin_root + 'ajax/' + thepage + '.php',
		type : 'GET',
		data : {
			projectid : projectid
		},
		cache : false,
		beforeSend : function() {
			$('#loading').show();
		},
		success : function(data) {
			if (data == 'error') {
				$('#loading').hide();
				alert('Sorry we have some problem please try again');
				return false;
			} else {
				$('.control_path').hide();
				// $('#' + element).show();
				$('#content').html(data);
				$('#loading').hide();
			}
                    doJSWork();
		}
	});
}

function ReplaceUrl(rtitle, rurl, data) {
	var title = rtitle + " - Capseo";

	if (window.history.replaceState) {
		url = admin_root + rurl;
		History.pushState(data, title, admin_root + rurl)
	} else {
		url = "#" + rurl;
		window.location.href = url;
	}
}

function drag_drop(element) {
	var $stats_content = $('#' + element);

	$stats_content.find('div.widgets').wl_Widget();

	$stats_content.find('div.accordion').accordion({
		collapsible : true,
		autoHeight : false
	});
}

function datatable(element) {
	$("table." + element).dataTable({});
}

function accordion(classname) {
	$('div.' + classname).accordion({
		collapsible : true,
		autoHeight : false
	});
}