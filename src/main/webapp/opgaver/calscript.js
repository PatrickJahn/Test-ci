/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


document.getElementById("buttons").addEventListener('click', function(){buttonPressed(event)});
document.getElementById("calculate").addEventListener('click', calc);


function buttonPressed(button){
    event.stopPropagation();
var value = button.target.innerText;
if (value !== "="){
document.getElementById("display").innerHTML += value;
} 
   
}

function calc(){
    event.stopPropagation();
    var displayStr = document.getElementById("display").innerHTML;
    
    var arr = ["/","*","-","+"]
    var indexO =[];
    for (var i = 0; i < arr.length; i++){
        if (displayStr.indexOf(arr[i]) !== -1){
         indexO.push(arr[i]); 
   
        }
    }
    var arrOf = displayStr;
    var res;
   for (var i = 0; i < indexO.length; i++){
      
           arrOf = arrOf.split(indexO[i]);
    res = indexO[i];
   }
   var result; 
   console.log(arrOf);
   switch (res){
    
       case "/": result = Number(arrOf[0]) / Number(arrOf[1]);
        
       break;
           case "*": result = Number(arrOf[0]) * Number(arrOf[1]);
           break;
                    case "+": result = Number(arrOf[0]) + Number(arrOf[1]);
                    break;
                                  case "-": result = Number(arrOf[0]) - Number(arrOf[1]);
                                      break;
        defalut: result = Number(arrOf[0]);
   }
   

 
  document.getElementById("display").innerHTML = result;
    
    
    
}