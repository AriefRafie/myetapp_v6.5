// JavaScript Document<script type="text/javascript">
//Bogus Counter script - http://www.btinternet.com/~kurt.grigg/javascript

if ((document.getElementById && document.createElement("img")) && 
window.addEventListener || window.attachEvent){

(function(){

//Customize the counter here.

//How many digits do you want in your counter?
var counter_length = 7;

//Choose a start value. If your "counter_length" is 7, "counter_start_from" MUST be 7 numbers long!
var counter_start_from = "9999980";

//Gives a random hit anywhere from 0 to "random_timer" seconds.
var random_timer = 2;


//Nothing needs altering past here!

var pics = [];
var load = [];
for(i = 0; i < 10; i++){
 pics[i]=i+".gif";
}              
for(i = 0; i < pics.length; i++){
 load[i] = new Image();
 load[i].src = "counterdigits/"+pics[i];
}

var temp_stop = [];
var stop;
var c = "";
for(i = 0; i < counter_length; i++){
temp_stop[i] = 9;
stop = c+=temp_stop[i];
}

var counter_end_at = parseInt(stop);

if (counter_start_from.length != counter_length){
alert("You want the counter to be "+counter_length+" digits long.\nThe variable \"counter_start_from\" must be "+counter_length+" digits long too!\nYou have it set to "+counter_start_from.length+".");
return false;
}

//Stop Opera selecting anything whilst dragging! Not ideal, keep eye on this for future version probs.
if (window.opera){
document.write("<input type='hidden' id='Q' value=' '>");
}

var y,x,z,temp1,temp2,dom,temp3,temp4;
var n = 500;
var dragOK = false;
var timer = 1000;
random_timer *= 1000;
var disp = [];

document.write('<div id="infobox" style="position:absolute;top:125px;left:100px;height:160px;width:251px;padding-top:23px;border:2px outset window;background-color:#ece9d8;text-align:center;visibility:hidden">'
+'<textarea style="height:90px;width:200px;font-family:verdana,arial,sans-serif;font-size:11px;border:2px inset window">'
+'Home version 0.13 Acme Live Counter cannot cope with the popularity of this web page.'
+'\n\n'
+'Upgrade to Pro version 0.02 recommended. Site owner has been informed.</textarea>'
+'<br/><input id="trick" type="button" value="Visit Acme web site" style="width:140px;font-family:verdana,arial,sans-serif;font-size:11px;margin-top:20px;"> <input id="cont" type="button" value="Close" style="font-family:verdana,arial,sans-serif;font-size:11px;margin-top:20px;">'
+'<\/div>'
+'<div id="infobar" style="position:absolute;top:100px;left:100px;height:25px;width:251px;border:2px outset window;font-family:verdana,arial,sans-serif;font-size:11px;font-weight:bold;color:#000000;line-height:25px;text-align:center;background-color:#ece9d8;visibility:hidden;cursor:move">Acme notice \- counter overload<\/div>');

function do_counter(){
var v1,v2,v3;
timer = Math.round(Math.random()*random_timer);
counter_start_from++;
if (counter_start_from > counter_end_at){ 
 clearTimeout(timer);
 vis(true,false);
 return false;
 }
v1 = counter_start_from.toString();
v2 = v1.split("");
v3 = counter_length-v2.length;
for(i = 0; i < v2.length; i++){
 disp[i+v3].setAttribute("src", load[v2[i]].src);
}
setTimeout(do_counter,timer);
}

function check(e){
 if (!e) e = window.event;
 dom = (typeof e.pageY == "number");
 if (dom){
  dragOK = (e.target.id == "infobar");
  }
 else{
  dragOK = (e.srcElement.id == "infobar");
  }
 if (dragOK){ 
  if (window.opera){
   document.getElementById("Q").focus();
  }
  z = (dom)? e.target.style:e.srcElement.style;
  z.zIndex = n++;
  down(e);
 }
}

function down(e){
 if (dom){
  y = e.pageY - parseInt(z.top);
  x = e.pageX - parseInt(z.left);
 }
 else{
  temp1 = z.pixelLeft;
  temp2 = z.pixelTop;
  y = e.clientY;
  x = e.clientX;
 }
 document.onmousemove = move;
 if (dom) return false;
}

function move(e){
 if (!e) e = window.event;
 if (dom){
  z.top = parseInt(e.pageY)-y +'px';
  z.left = parseInt(e.pageX)-x +'px';
 }
 else{
  z.left = temp1 + e.clientX-x;
  z.top = temp2 + e.clientY-y;
 }
 temp4.top = 25 + parseInt(temp3.top) +'px';
 temp4.left = parseInt(temp3.left) +'px';
 return false;
}

function up(){
 document.onmousemove = null;
 z = null;
 dragOK = false;
}

function ext(){
 vis(false,true);
}

function vis(a,b){
 var c = (!a)?'hidden':'visible';
 temp3.visibility = c;
 temp4.visibility = c;
 if (b){
  temp3.top = 0 +'px';
  temp3.left = 0 +'px';
  temp4.top = 0 +'px';
  temp4.left = 0 +'px';
 }
}

function exp(){
if (confirm("Apparently, there is no such site and no such thing as a\"live\" hit counter either.\n\nGet this bogus hit counter and lots of other web page tomfoolery from Kurt's DHTML.")) 
window.location.href = "http://www.btinternet.com/~kurt.grigg/javascript";
}

function init(){
if (document.getElementById("thecounter")){
 for(i = 0; i < counter_length; i++){
  disp[i] = document.createElement("img"); 
  disp[i].setAttribute("src", load[0].src); 
  disp[i].setAttribute("id", "dgts'+i+'");
  disp[i].setAttribute("width", "26"); 
  disp[i].setAttribute("height", "24"); 
  document.getElementById("thecounter").appendChild(disp[i]);
 }
 temp3 = document.getElementById("infobar").style;
 temp4 = document.getElementById("infobox").style;
 do_counter();
 }
}

if (window.addEventListener){
 document.addEventListener("mousedown",check,false);
 document.addEventListener("mouseup",up,false);
 document.getElementById("trick").addEventListener("click",exp,false);
 document.getElementById("cont").addEventListener("click",ext,false);
 window.addEventListener("load",init,false);
}  
else if (window.attachEvent){
 window.attachEvent("onload",init);
 document.getElementById("trick").attachEvent("onclick",exp);
 document.getElementById("cont").attachEvent("onclick",ext);
 document.attachEvent("onmousedown",check);
 document.attachEvent("onmouseup",up);
} 
})();
}//End.
</script>