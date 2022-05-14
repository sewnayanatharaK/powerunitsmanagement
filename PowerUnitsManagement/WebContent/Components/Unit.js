$(document).ready(function()
{

	$("#alertSuccess").hide();
	$("#alertError").hide();
});


$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts---------------------

	
	 $("#alertSuccess").text("");
 	 $("#alertSuccess").hide();
 	 $("#alertError").text("");
 	 $("#alertError").hide();
 	 
 	 
   	// Form validation-------------------
  	
	var status = validateItemForm();
	if (status != true)
	{
		 $("#alertError").text(status);
 		 $("#alertError").show();
 		 
         return;
    }
 
	// If valid------------------------
	
	
	var type = ($("#hidUnitIDSave").val() == "") ? "POST" : "PUT";

	 $.ajax(
 	 {
 		url : "UnitsAPI",
 		type : type,
 		data : $("#formItem").serialize(),
 		dataType : "text",
	    complete : function(response, status)
        {
   
      			onItemSaveComplete(response.responseText, status);
	    }
	    
     });
     
});
function onItemSaveComplete(response, status)
{
	if (status == "success")
	{
		 var resultSet = JSON.parse(response);
		 
	 	 if (resultSet.status.trim() == "success")
		 {
 				$("#alertSuccess").text("Successfully saved.");
		    	$("#alertSuccess").show();
 				$("#divItemsGrid").html(resultSet.data);
 			
 	 	  } else if (resultSet.status.trim() == "error")
 	 	  {
 	 
 				$("#alertError").text(resultSet.data);
 				$("#alertError").show();
		  }
		  
    } else if (status == "error")
    {
 			$("#alertError").text("Error while saving.");
 			$("#alertError").show();
 			
 	} else
 	{
 			$("#alertError").text("Unknown error while saving..");
 			$("#alertError").show();
    } 

 	$("#hidUnitIDSave").val("");
	 $("#formItem")[0].reset();
}
$(document).on("click", ".btnUpdate", function(event)
{
		var id = event.target.id;

		$("#hidUnitIDSave").val(id.substring(0, id.length-1));
		
 		$("#unitcode").val($(this).closest("tr").find('td:eq(0)').text());
 		$("#name").val($(this).closest("tr").find('td:eq(1)').text());
 		$("#address").val($(this).closest("tr").find('td:eq(2)').text());
 		$("#date").val($(this).closest("tr").find('td:eq(3)').text());
		$("#nunits").val($(this).closest("tr").find('td:eq(4)').text());
		$("#period").val($(this).closest("tr").find('td:eq(5)').text());
		$("#pricep").val($(this).closest("tr").find('td:eq(6)').text());
		$("#tprice").val($(this).closest("tr").find('td:eq(7)').text());
		
});
$(document).on("click", ".btnRemove", function(event)
{
	 $.ajax(
 	{
 		url : "UnitsAPI",
 		type : "DELETE",
	    data : "uid=" + $(this).data("uid"),
 		dataType : "text",
 		complete : function(response, status)
		{
			 onItemDeleteComplete(response.responseText, status);
 		}
	 });
});



function onItemDeleteComplete(response, status)
{
	if (status == "success")
    {
 			var resultSet = JSON.parse(response);
 			
		   if (resultSet.status.trim() == "success")
 		   {
 		   
 				$("#alertSuccess").text("Successfully deleted.");
 				$("#alertSuccess").show();
 				
			    $("#divItemsGrid").html(resultSet.data);
			    
			    setTimeout( (function(){alert(43)}, 1000));
 			} else if (resultSet.status.trim() == "error")
 			{
				 $("#alertError").text(resultSet.data);
 				 $("#alertError").show();
		    }
 	} else if (status == "error")
    {
		 $("#alertError").text("Error while deleting.");
 		 $("#alertError").show();
    } else
    {
 		$("#alertError").text("Unknown error while deleting..");
 		$("#alertError").show();
 	}
}
function validateItemForm()
{
	// CODE
	if ($("#unitcode").val().trim() == "")
 	{
		 return "Insert Unit Code.";
    }
    
    
	// NAME
	if ($("#name").val().trim() == "")
    {
		 return "Insert  Name.";
 	} 
 	

	// ADDRESS-------------------------------
	if ($("#address").val().trim() == "")
    {
 		return "Insert Address.";
 	}

	//Date------------------------------
	if ($("#date").val().trim() == "")
    {
 		return "Insert Date.";
 	}
 	
 	// is numerical value
	var nunits = $("#nunits").val().trim();
	if (!$.isNumeric(nunits))
 	{
 		return "Insert a numerical value for No of Units.";
 	}
	
	//Period
	if ($("#period").val().trim() == "")
    {
 		return "Insert Period.";
 	}
	
	// is numerical value
	var tmpPrice = $("#pricep").val().trim();
	if (!$.isNumeric(tmpPrice))
 	{
 		return "Insert a numerical value for Price per Units.";
 	}
 	
 	
	// convert to decimal price
 	$("#pricep").val(parseFloat(tmpPrice).toFixed(2));
 	
	// is numerical value
	var tPrice = $("#tprice").val().trim();
	if (!$.isNumeric(tPrice))
 	{
 		return "Insert a numerical value for Total Price.";
 	}
 	
 	
	// convert to decimal price
 	$("#tprice").val(parseFloat(tPrice).toFixed(2));
 	
 	
	
   return true;
}
