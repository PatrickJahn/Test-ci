/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var cars = [
  { id: 1, year: 1997, make: 'Ford', model: 'E350', price: 3000 },
  { id: 2, year: 1999, make: 'Chevy', model: 'Venture', price: 4900 },
  { id: 3, year: 2000, make: 'Chevy', model: 'Venture', price: 5000 },
  { id: 4, year: 1996, make: 'Jeep', model: 'Grand Cherokee', price: 4799 },
  { id: 5, year: 2005, make: 'Volvo', model: 'V70', price: 44799 }
];

// Cars newer than 1999
var arr1 = cars.filter(car => car.year > 1999);
console.log(arr1);

// Al  Volvo’s
var arr2 = cars.filter(car => car.make === "Volvo");
console.log(arr2);
// All cars with a price below 5000
var arr3 = cars.filter(car => car.price < 5000);



var tableInside = function(cars){
 return cars.map(function(a){
        return "<tr><td>"+a.id+"</td>"+
                "<td>"+a.year+"</td>"+
                "<td>"+a.make+"</td>"+
                "<td>"+a.model+"</td>"+
                "<td>"+a.price+"</td></tr>";
                
    
    }).join();
}

function getTable(arr){
    
return "<table class=\"table\"><thead><tr><th>id</th><th>year</th><th>make</th><th>model</th><th>price</th><tbody>" + tableInside(arr) + "</tbody></table>" ;

   
}

document.getElementById("divTable").innerHTML = getTable(cars);



document.getElementById("sub").addEventListener('click', filterByPrice);
function filterByPrice(){
    event.preventDefault();
    console.log(document.getElementById("fprice").value);
    var cars2 = cars.filter(car => car.price < document.getElementById("fprice").value);
    document.getElementById("divTable").innerHTML = getTable(cars2);
}


