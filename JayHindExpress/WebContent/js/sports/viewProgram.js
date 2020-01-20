/**
 * 
 */
function actions(data, type, full) {
		var onClickStr = "onclick=\"select('"+full.id+"')\"";
		var f='<a href="#" '+onClickStr+' >Select</a>&nbsp;&nbsp; ';
		
		var onClickStr3 = "onclick=\"edit('"+full.id+"')\"";
		f+='<a href="#" '+onClickStr3+'>Edit</a> &nbsp; &nbsp; ';
		
		var onClickStr2 = "onclick=\"add('"+full.id+"')\"";
		f+='<a href="#" '+onClickStr2+'>Add More Photo</a> &nbsp; &nbsp';
		//alert(full.id);<a href="morePhotos">Add More Photo</a>
		var onClickStr2 = "onclick=\"edit('"+full.id+"')";
		f+='<a id="delete"  hreF="deleteSport?id='+full.id+'" onclick="return (confirm(\'Are you sure?\'))" >Delete</a>';
	   return f;
	}

function edit(id){
	document.getElementById('programId').value=id;
	document.getElementById('editForm').submit();	
}
function select(id){	
	document.getElementById('programIdsel').value=id;
	document.getElementById('selectForm').submit();	
}
function add(id){
	//alert(id);
	document.getElementById('photoId').value=id;
	document.getElementById('addPhotoForm').submit();	
	
}

