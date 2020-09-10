/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


document.getElementById("form1").addEventListener('click', getInfo);

  function getInfo(){
    var url = "https://patrickcph.dk/devops-starter/api/movie/";
    event.stopPropagation();
    if (event.target.id !== "ftextbox"){
    event.preventDefault();
    
    
    var getBy = event.target.id;
    
  var urlAddOn = getUrlAddOn(event.target.id);
  url += urlAddOn;
    
fetch(url)
  .then(res => res.json()) //in flow1, just do it
  .then(data => {
   // Inside this callback, and only here, the response data is available
   console.log("data",data);
  /* data now contains the response, converted to JavaScript
     Observe the output from the log-output above
     Now, just build your DOM changes using the data*/   
             switch (getBy) {
                 case "id":
                      document.getElementById("movieDsc").innerHTML = createHtmlForOne(data);
                     break;
                 case "genre":
                      document.getElementById("movieDsc").innerHTML = createHtmlForMore(data);
                     break;
                 case "title":
                      document.getElementById("movieDsc").innerHTML = createHtmlForOne(data);
                      break;
                 
                 default:
                      document.getElementById("movieDsc").innerHTML = createHtmlForMore(data);   
             }
         
             
});


    }
}


function getUrlAddOn(id){
    switch (id) {
        case "id":
          return document.getElementById("ftextbox").value;
      case "title":
          return "title/"+ document.getElementById("ftextbox").value;
      case "genre": 
           return "genre/"+ document.getElementById("ftextbox").value;
       case "all":
       return "";
       default: 
           return "";
    }
    
   
}

var createHtmlForOne = function(json){
     var html = "<p> <b>Title:</b> " + json.title + "<br>"+
            "Year: " + json.year + "<br> "+
            "G: " + json.genre +  
            "</p>";
                
    return html;
    
};

var createHtmlForMore = function(json){
    // Bruger dårlig form for string builder... 
     var genHtml = "";
    for (obj in json){
   var res =  genHtml.concat("<tr><td>"+json[obj].title+"</td>"+
           "<td>"+json[obj].year+"</td>"+
                "<td>"+json[obj].genre+"</td></tr>"); 
    genHtml = res;
    }
    
     var table ="<table><thead><tr><th>Title</th><th>Year</th><th>Genre</th><tbody>" + genHtml + "</tbody></table>" ;
     
    return table;
};