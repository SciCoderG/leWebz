
function filterImg()
	{
		
		var images =document.getElementsByTagName("img");
		
		var alt;
				
		for(var i = 0; i < images.length; i)		
		{		
			alt = document.createElement("p");
			alt.innerHTML = images[i].alt;
			images[i].parentNode.replaceChild(alt, images[i]);	  	 
		}
	}
			