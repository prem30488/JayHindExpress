/**
 * 
 */
function actions(data, type, full) {
		var onClickStr = "onclick=\"select('"+full.id+"')\"";
		var f="";
		//var f='<a href="#" '+onClickStr+' >Select</a>&nbsp;&nbsp; ';

		//alert(full.id);<a href="morePhotos">Add More Photo</a>
		//var onClickStr2 = "onclick=\"edit('"+full.id+"')";
		//f+='<a id="delete"  hreF="deleteMetaData?layerId='+full.id+'" onclick="return (confirm(\'Are you sure?\'))" >Delete</a>';
	   return f;
	}

function edit(id){
	document.getElementById('contactId').value=id;
	document.getElementById('editForm').submit();	
}
function select(id){	
	document.getElementById('contactIdsel').value=id;
	document.getElementById('selectForm').submit();	
}
function add(id){
	//alert(id);
	document.getElementById('photoId').value=id;
	document.getElementById('addPhotoForm').submit();	
	
}

