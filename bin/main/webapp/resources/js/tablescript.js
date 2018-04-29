var id_old;

function edit_row(no)
{
 document.getElementById("edit_button"+no).style.display="none";
 document.getElementById("save_button"+no).style.display="block";
 console.log(no);
 /*var id=document.getElementById("id"+no);
 var name=document.getElementById("name"+no);
 var desc=document.getElementById("desc"+no);*/
	
 /*var id_old=id.innerHTML;
 var name_old=name.innerHTML;
 var desc_old=desc.innerHTML;
	
 id.innerHTML="<input type='text' id='id_text"+no+"' value='"+id_old+"'>";
 name.innerHTML="<input type='text' id='name_text"+no+"' value='"+name_old+"'>";
 desc.innerHTML="<input type='text' id='desc_text"+no+"' value='"+desc_old+"'>";*/
 
 id_old = document.getElementById("id"+no).value;
 console.log("current prodID:"+id_old);
 document.getElementById("id"+no).removeAttribute('readonly');
 document.getElementById("name"+no).removeAttribute('readonly');
 document.getElementById("desc"+no).removeAttribute('readonly');
 
 
}

function save_row(no)
{
	console.log(no);
 /*var name_val=document.getElementById("id_text"+no).innerHTML;
 console.log("id"+no);
 console.log(name_val);
 var country_val=document.getElementById("name_text"+no).innerText;
 console.log(country_val);
 var age_val=document.getElementById("desc_text"+no).value;
 console.log(age_val);

 document.getElementById("id_text"+no).innerHTML=name_val.value;
 document.getElementById("name_text"+no).innerHTML=country_val;
 document.getElementById("desc_text"+no).innerHTML=age_val;*/
if(!id_old){
	alert("Please click edit before saving");
	return false;	
}
else{
 document.getElementById("edit_button"+no).style.display="block";
 document.getElementById("save_button"+no).style.display="none";
	
	document.getElementById("id"+no).setAttribute('readonly','readonly');
	 document.getElementById("name"+no).setAttribute('readonly','readonly');
	 document.getElementById("desc"+no).setAttribute('readonly','readonly');
	 
	 var id = document.getElementById("id"+no).value;
	 var name = document.getElementById("name"+no).value;
	 var desc = document.getElementById("desc"+no).value;
	 //Call ajax to update the record
	 var data = {};
     data["id"] = id;
     console.log("newID:"+id);
     data["name"]=name;
     data["desc"]= desc;
     console.log(data);
     console.log(JSON.stringify(data));
     var updateObj = {product:data, id:id_old};
     console.log(JSON.stringify(updateObj));
$.ajax({
url : 'updateProcess',
data : JSON.stringify(updateObj),
type : 'POST',
beforeSend: function(xhr) {
    xhr.setRequestHeader("Accept", "application/json");
    xhr.setRequestHeader("Content-Type", "application/json");
},

success : function(response) {
    /*alert( response );*/
    console.log("SUCCESS: "+response);
   /* var obj = JSON.parse(response);*/
    /*var resp = "<table> <tr> <th>ProductID</th> <th>Product Name</th> <th>Product Description</th></tr> </tr> <td>"+response.id+"</td> <td>"+response.name+"</td> <td>"+response.desc+"</td> </tr> </table>";*/
   	//console.log(resp); 
    var len = $('#data_table tr').length-1;
    console.log(len);
    alert("Product updated");
},
error : function(xhr, status, error) {
    alert(xhr.responseText);
}
});	 
}
	
}


function delete_row(no)
{
	console.log(no);
	
	var id= document.getElementById("id"+no);
	 if(!id){
   	  alert("Please enter the product ID");
   	  return false;
     }
     
     var data = document.getElementById("id"+no).value;
      
     console.log(data);		     
     console.log(JSON.stringify(data));
$.ajax({
url : 'delete/'+data,
data : JSON.stringify(data),
type : 'GET',
beforeSend: function(xhr) {
    xhr.setRequestHeader("Accept", "application/json");
    xhr.setRequestHeader("Content-Type", "application/json");
},

success : function(response) {
    /*alert( response );*/
	    console.log(len);
		alert("response:"+response);
},
error : function(xhr, status, error) {
    alert(xhr.responseText);
}
});
 document.getElementById("row"+no+"").outerHTML="";
}
