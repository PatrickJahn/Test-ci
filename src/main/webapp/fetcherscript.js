/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


document.getElementById("form1").addEventListener('click', getInfo);

  function getInfo(){
    var url = "https://patrickcph.dk/devops-starter/api/movie/";
    if (event.target.id !== "fid"){
    event.preventDefault();
    

 
  var id = document.getElementById("fid").value;
  url += id;
    
fetch(url)
  .then(res => res.json()) //in flow1, just do it
  .then(data => {
   // Inside this callback, and only here, the response data is available
   console.log("data",data);
  /* data now contains the response, converted to JavaScript
     Observe the output from the log-output above
     Now, just build your DOM changes using the data*/   
             
           document.getElementById("movieDsc").innerHTML = createHtmlById(data);
             
});


    }
}


var createHtmlById = function(json){
     var html = "<p> <b>Title:</b> " + json.year + "<br>"+
            "Phone: " + json.title + "</p> "+
            "Street: " + json.genre + "<br>"+ 
            "</p>";
                
    return html;
    
};