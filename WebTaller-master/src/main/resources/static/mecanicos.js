window.addEventListener("load", function() {
	getRows();
});
function getRows() {
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.open("get", "mecanicos.xml", true);
	xmlhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			showResult(this);
		}
	};
	xmlhttp.send(null);
}
function showResult(xmlhttp) {
    var xmlDoc = xmlhttp.responseXML.documentElement;
    var rowData = xmlDoc.getElementsByTagName("empleado");
    var wrapper = document.getElementById("mecanicos");
    console.log(rowData.length);
    for (i=0; i<rowData.length;i++){
        
        var url,name,desc;
        url = rowData[i].getElementsByTagName("imagen")[0].childNodes[0].nodeValue;
        console.log(url);
        name = rowData[i].getElementsByTagName("nombre")[0].childNodes[0].nodeValue;
        desc = rowData[i].getElementsByTagName("descripcion")[0].childNodes[0].nodeValue;
        if (i%2 == 0){
            imagenIzq(wrapper,url,name,desc);
        } else {
            imagenDer(wrapper,url,name,desc);
        }
    }
}



function imagenIzq(wrapper,url,name,desc){
    var texto = "<img src="+url+" alt=''><div class='info'><h6>"+name+"</h6><p>"+desc+"</p></div>";
    wrapper.insertAdjacentHTML("beforeend",texto)
}

function imagenDer(wrapper,url,name,desc){
    var texto = "<div class='info'><h6>"+name+"</h6><p>"+desc+"</p></div><img src="+url+" alt=''>";
    wrapper.insertAdjacentHTML("beforeend",texto)
}

	