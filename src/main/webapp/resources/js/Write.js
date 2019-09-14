function file_Event(obj) {
			console.log(obj.files);
			var dt = new DataTransfer();
			let cnt = 0;
			
			for(var file of obj.files) {
				var fileName = file.name;
				var ext = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length);
		if("txt" == ext || "zip" == ext || "exe" == ext) {
			dt.items.add(file);
		} else {
			cnt++;
		}
	}
	
	if(cnt > 0) alert("올린파일중에 " + cnt + "개 확장자 틀림 txt, zip, exe 파일만 올려주세요")
	console.log(dt.items);
	obj.files = dt.files;
	console.log(obj.files);
}

/*function file_Event2(obj) {
	var dt = new DataTransfer();
	let cnt = 0;
	
	for(var file of obj.files) {
		var fileName = file.name;
		var ext = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length);
		if("jpg" == ext || "png" == ext) {
			dt.items.add(file);
		} else {
			cnt++;
		}
	}
	
	if(cnt > 0) alert("올린파일중에 " + cnt + "개 확장자 틀림 jpg, png 파일만 올려주세요")
	console.log(dt.items);
	obj.files = dt.files;
	console.log(obj.files);
}*/