let statusScroll = false;
let ScrollPosition = 0;
let ScrollType = true;
let detect = false;

$(window).scroll(() => {
	let Scroll = $(window).scrollTop();
	
	if(Scroll < ScrollPosition) {
		ScrollType = false;
	} else {
		ScrollType = true;
	}
	
	ScrollPosition = Scroll;
	statusScroll = true;
});

setInterval(() => {
	if(statusScroll) {
		hasScrolled();
		statusScroll = false;
	}
}, 250);


function hasScrolled() {
	if(ScrollType) {
		if(!detect) {
			detect = true;
			$(".cate_btn").slideToggle();
		}
	} else {
		if(detect) {
			detect = false;
			$(".cate_btn").slideToggle();
		}
	}
}

//$(document).ready(() => {
//	$(".cate_btn").on('click', (e)=> {
//		let object = $(".category");
//		let d_left = $(document).width();
//		let d_width = object.width();
//		
//		if(object.hasClass("disable")) {
//			object.removeClass("disable");
//			object.offset({ top: 0, left: 940});
//			console.log(object.offset().left);
//		}
//		
//		console.log(object.offset().left);
//		
//		if(object.offset().left > d_left) {
//			object.animate({
//				right: 100
//			});
//		} else {
//			$('.category').animate({
//				right: -d_width - 1
//			});
//		}
//	});
//});
