/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var divs = document.getElementsByTagName("div");
console.log(divs.length)
for (var i = 0; i < divs.length; i++){
    if (i === 0){
           divs[i].style.backgroundColor = "green";
    }
    if (i === 1){
    divs[i].style.backgroundColor = "blue";
} 
 if (i === 2){
    divs[i].style.backgroundColor = "red";
} 

function changeColor(){
    var div1 = document.getElementById("div1");
    var div2 = document.getElementById("div2");
    var div3 = document.getElementById("div3");
    
    div1.style.backgroundColor = "purple";
     div2.style.backgroundColor = "black";
        div3.style.backgroundColor = "brown";
}

document.getElementById("outer").addEventListener('click', logId);
function logId(){
    document.getElementById("title").innerHTML = "Hi from " + event.target.id + " and " + this.id;
  
}



}

