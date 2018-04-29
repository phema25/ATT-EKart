function edit_row(no)
{
 document.getElementById("edit_button"+no).style.display="none";
 document.getElementById("save_button"+no).style.display="block";
	
 var id=document.getElementById("id"+no);
 var name=document.getElementById("name"+no);
 var desc=document.getElementById("desc"+no);
	
 var id_old=id.innerHTML;
 var name_old=name.innerHTML;
 var desc_old=desc.innerHTML;
	
 id.innerHTML="<input type='text' id='id"+no+"' value='"+id_old+"'>";
 name.innerHTML="<input type='text' id='name"+no+"' value='"+name_old+"'>";
 desc.innerHTML="<input type='text' id='desc"+no+"' value='"+desc_old+"'>";
}

function save_row(no)
{
	var id=document.getElementById("id"+no).value;
	var name=document.getElementById("name"+no).value;
	var desc=document.getElementById("desc"+no).value;

 document.getElementById("id"+no).innerHTML=id;
 document.getElementById("name"+no).innerHTML=name;
 document.getElementById("desc"+no).innerHTML=desc;

 document.getElementById("edit_button"+no).style.display="block";
 document.getElementById("save_button"+no).style.display="none";
}
function delete_row(no)
{
 document.getElementById("row"+no+"").outerHTML="";
}
