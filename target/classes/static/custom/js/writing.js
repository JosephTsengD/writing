 
//文字編輯器CKeditor, Hightlight, toggle
        CKEDITOR.disableAutoInline = true;

		$( document ).ready( function() {
			$( '#editor1' ).ckeditor(); // Use CKEDITOR.replace() if element is <textarea>.
			$( '#editable' ).ckeditor(); // Use CKEDITOR.inline().
		} );

		function setValue() {
			$( '#editor1' ).val( $( 'input#val' ).val() );
		}

$(document).ready(function(){  
   
   	var $forum_box = $(".forum_box"),
   	$forum_answer_btn = $forum_box.find("button"),
   	$forum_answer = $forum_box.find(".ans-area");
   	
   	$forum_answer_btn.click(function(){
   		$(this).parent().find("[class^='ans']").toggle("fast");
   		$(this).parent().find("[class^='ans']").find('span').toggle('fast');
   	});
   	
   	$forum_answer.click(function(){
   		var ans = "",index,className;
   		if($(this).data("pbox") === undefined){
   			ans = $(this).find('.answer-text').text().split('、');
	   		for(var i in ans){
		   		$(".apple").jmHighlight(ans[i],{
		   	        "element": "span",
		   	        "className": "customHighlight"
		   	    });
	   		}
   		}else{
   			index = $(this).data("index");
   			className = $(this).data("pbox");
   			$(".p-box").eq(index).addClass(className);
   		}
   	});
   	
   	$forum_answer.dblclick(function(){
   		var ans = "",index,className;
   		if($(this).data("pbox") === undefined){
   			ans = $(this).find('.answer-text').text().split('、');
   			for(var i in ans){
		   		$(".apple").jmRemoveHighlight({
		   	        "element": "span",
		   	        "className": "customHighlight"
		   	    }, ans);
   			}
   		}else{
   			index = $(this).data("index");
   			className = $(this).data("pbox");
   			$(".p-box").eq(index).removeClass(className);
   		}
   	});
   
   $("#editable").blur(function(){
   	var _this = $(this);
   	var answer = "";
   	if(_this.text().trim().length === 0){
   		answer = ""
   	}else{
   		answer = _this.html();
   	}
   	$.post("/saveTopicRecord",{answer:answer,tid:_this.data("id")}).done(function(data){
   		console.log(data);
   	});
   });
   
   $("textarea[name^='thinkrecord']").blur(function(){
       var _this = $(this);
       $.post("/saveForumRecord",{answer:_this.val(),fid:_this.data("id")}).done(function(data){
           console.log(data);
       })
   });
   

	// Checkbox
	$(function() {
		$("#check").button();
		$("#format").buttonset();
	});

	// toggle
	$(".header").click(function() {

		$header = $(this);
		// getting the next element
		$content = $header.next();
		// open up the content needed - toggle the slide- if visible, slide up,
		// if not slidedown.
		$content.slideToggle(500, function() {
			// execute this after slideToggle is done
		});
	});

	$(".reference").click(function() {

		$header = $(this);
		// getting the next element
		$content = $header.next();
		// open up the content needed - toggle the slide- if visible, slide up,
		// if not slidedown.
		$content.slideToggle(500, function() {
			// execute this after slideToggle is done
		});
	});

	// tooltip
	$('[data-toggle="tooltip"]').tooltip({

	});
	$('[data-toggle="popover"]').popover({

	});
	
}); 