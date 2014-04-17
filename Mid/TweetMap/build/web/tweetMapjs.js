/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



   
    var map;
    var lat= 0;
    var long= 0;
    var myLatlng;
    function initialize() {
    myLatlng = new google.maps.LatLng(lat,long);
    var mapOptions = {
    zoom: 4,
    center: myLatlng
    }
    map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
    
    //loop to add data to map
        
        callURL();
        
        
    
    }
    function callURL(){
        //making an ajax call
        var id =1;
        var temp = 285;
        while (id <=temp){
        var url = "http://localhost:8080/TweetRest/webresources/generic?id="+id;
        $.ajax({
        type: 'GET',
        contentType: "application/json; charset=utf-8",
        dataType: 'json',
        url: url,
        success: function (data){
            marker(data.latitude,data.longitude,data.numberOfTweet_s,data.tweetType_s);
        },
        error: function(msg,url,line){
            alert("error");
        }
        });
        
        id++;
        }
    }
    
function marker(lat,long,numberoftweets,tweettype){
    var point = new google.maps.LatLng(lat,long);
var marker;
  if(tweettype=="good"){
     marker= new google.maps.Marker({
       position: point,
       map: map,
       icon: 'smiley_happy.png',
       title: 'count '+numberoftweets
  });
  }
  else if(tweettype=="bad"){
     marker= new google.maps.Marker({
       position: point,
       map: map,
       icon: 'smiley_sad.png',
       title: 'count '+numberoftweets
  });
  }
  else if(tweettype=="nuetral"){
     marker= new google.maps.Marker({
       position: point,
       map: map,
       icon: 'childmuseum01.png',
       title: 'count '+numberoftweets
  });
  }else{
      
      marker= new google.maps.Marker({
       position: point,
       map: map,
       icon: 'smiley_sad.png',
       title: 'count '+numberoftweets
  });
      
  }
  
  }
   google.maps.event.addDomListener(window, 'load', initialize);
   
    