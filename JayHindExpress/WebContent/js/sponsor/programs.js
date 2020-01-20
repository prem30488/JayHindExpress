/**
 * 
 */
function getPage(i)
{
	document.getElementById("page").value = i;
	document.getElementById("form").submit();
}

function getNextPage(i)
{
	document.getElementById("page").value = i;	
	document.getElementById("form").submit();
}

function openchild(url,height,width,left,top){
	var features= "height="+height+",width="+width+",left="+left+",top="+top;
	window.open(url,"slideshow",features);
}