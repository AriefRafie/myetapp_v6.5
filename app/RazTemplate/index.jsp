
<style>

.popup-lightbox {
overflow: auto;
position: fixed;
top: 0;   left: 0;
right: 0;
bottom: 0;
width: 100%;
height: 100%;
display: table;
table-layout: fixed;
background-color: rgba(0,0,0,0.7);
z-index: 999; 
}

.popup-lightbox > .popup-lightbox-inner { 
display: table-cell;
text-align: center;
vertical-align: middle;
width: 100%;
} 

.popup-lightbox-inner > .popup-content {
display: inline-block;
outline: none;
text-align: left;
min-width: 980px;
max-width: 90%;
}

.popup-content {
	height: 40em;
    overflow-y: scroll;
	background:#FFF;
		
}
	

	

.blue{    
    color : blue;    
}
.red{    
    color : red;    
}

.parentDiv {
    width: 50% !important; 
    overflow-x:  auto;
    white-space: nowrap;
    
}


.link{
	color : blue; 
	text-decoration: underline;
}

.boxViewShadow {
	margin-bottom:3px;
    padding: 5px; 	
	box-shadow: 2px 2px 5px black;
}

.box_shadow {
	box-shadow: 2px 2px 5px black;
}

.underline_td_main {
	border-bottom: 1px solid #000;
    padding: 3px 1px;
    font-size: 100%;
    color : white;
    text-shadow: 2px 2px 5px black;
}

.underline_td_sub {
	border-bottom: 1px solid #000;
    padding: 3px 1px;
    font-size: 80%;
    color : white;
    text-shadow: 2px 2px 5px black;
}

.arrow {
    border: solid white;
    border-width: 0 2px 2px 0;
    display: inline-block;
    padding: 2px;
	float:right;
	margin-top:3px;	
}

.classFade{
    
    animation: fadein 2s;
    -moz-animation: fadein 2s; /* Firefox */
    -webkit-animation: fadein 2s; /* Safari and Chrome */
    -o-animation: fadein 2s; /* Opera */
}

.right {
    transform: rotate(-45deg);
    -webkit-transform: rotate(-45deg);
}

.left {
    transform: rotate(135deg);
    -webkit-transform: rotate(135deg);
}

.up {
    transform: rotate(-135deg);
    -webkit-transform: rotate(-135deg);
}

.down {
    transform: rotate(45deg);
    -webkit-transform: rotate(45deg);
	
	
	
}

.cal_scroller {
	/*
	width:100%;
    padding: 10px 0;
    overflow: auto;
	white-space: nowrap;
	*/
	width: 99%;
    overflow: auto;
   
}

.cal_container {
	/*
    width:1200px;
	white-space: nowrap;
	*/
	display: table;
    table-layout: fixed;
    width: 100%;
}


.fullwidth_input {
     width: 100%; 
     box-sizing: border-box;
     -webkit-box-sizing:border-box;
     -moz-box-sizing: border-box;
}

.fullwidthWC_input {
     width: 24%; 
     box-sizing: border-box;
     -webkit-box-sizing:border-box;
     -moz-box-sizing: border-box;
	  float: right;
}


.fullwidthCarian_input {
     width: 74%; 
     box-sizing: border-box;
     -webkit-box-sizing:border-box;
     -moz-box-sizing: border-box;
}

.columnSort {
	cursor: pointer;
}

.infoMsg{
	margin:3px;
	font-size: 100%;	
}


</style>

<!-- contoh nak buat popup box ->
<!--

<div class='lightbox'>
<div class='lightbox-inner'>
<div class='content'>
content
</div>
</div>
</div>
-->

<script>


function setPageLocation(val)
{
	$jquery(window).scrollTop(val);
}
function getPageLocation()
{	
	var tempScrollTop = $jquery(window).scrollTop();
	return tempScrollTop;
}

function recheckNumber(elem)
{
	elem.value = elem.value.replace(/[^0-9]/g, '');
}

function validateNumber(evt) {
	
	
	
    var e = evt || window.event;
    var key = e.keyCode || e.which;
    //if (e.keyCode == ctrlKey || e.keyCode == cmdKey)
	
    if (!e.shiftKey && !e.altKey 
	//&& !e.ctrlKey 
	&&
	key >= 86 ||
    // numbers   
    key >= 48 && key <= 57 ||
    // Numeric keypad
    key >= 96 && key <= 105 ||
    // Backspace and Tab and Enter
    key == 8 || key == 9 || key == 13 ||
    // Home and End
    key == 35 || key == 36 ||
    // left and right arrows
    key == 37 || key == 39 ||
    // Del and Ins
    key == 46 || key == 45) {
        // input is VALID
    }
    else {
        // input is INVALID
        e.returnValue = false;
        if (e.preventDefault) e.preventDefault();
    }
}

function validateCurrency(e,elmnt,content) {
	//if it is character, then remove it..
	var keycode;
	if(window.event)
	{
		keycode = window.event.keyCode;
	}
	else if (e) 
	{
		keycode = e.which;
	}
	else 
	{
		return true;
	}
	
	if((keycode >= 37 && keycode <= 40) || (keycode == 9)) 
	{
		return false;
	}
	
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}	
}



function validateCurrencyBlur(e,elmnt,content) 
{
	//alert('x');
	//alert(Math.round(RemoveNonNumeric(content), 2));
	//elmnt.value = Math.round(RemoveNonNumeric(content), 2);
	//return;
	if(content!="")
	{
		elmnt.value = +(Math.round(content + "e+"+2)  + "e-"+2);
	}
}

function validateAreaBlur(e,elmnt,content) 
{
	//alert('x');
	//alert(Math.round(RemoveNonNumeric(content), 2));
	//elmnt.value = Math.round(RemoveNonNumeric(content), 2);
	//return;
	if(content!="")
	{
		elmnt.value = +(Math.round(content + "e+"+4)  + "e-"+4);
	}
}

function checkCursorLocation(elem,namaList,cmd)
{
	document.getElementById('sortColumnPosition'+namaList+cmd).value = elem.selectionStart;
	//console.log('Caret at: ', elem.selectionStart);	
}

function getColumnSearchType(cmd,columnName,namaSkrin)
{
	document.getElementById('sortColumn'+namaSkrin+cmd).value = columnName;
}

function setCopyToOther(val,fieldID)
{
	document.getElementById(fieldID).value = val;
}

function getSortType(cmd,columnName,columnType,namaSkrin,dateFormat)
{
	
	var currentType = document.getElementById('sortType'+namaSkrin+cmd).value;
	var currentDateFormat = document.getElementById('sortDateFormat'+namaSkrin+cmd).value;
	//alert("currentDateFormat : "+currentDateFormat+" nama field : "+'sortDateFormat'+namaSkrin+cmd);
	var currentColumn = document.getElementById('sortColumn'+namaSkrin+cmd).value;
	//alert("currentType : "+currentType+" currentColumn : "+currentColumn+" cmd : "+cmd);
	if(currentColumn == "" || currentColumn != currentColumn || currentType == "" || currentType == "DESC")
	{
		 document.getElementById('sortType'+namaSkrin+cmd).value = "ASC";
	}
	else
	{
		 document.getElementById('sortType'+namaSkrin+cmd).value = "DESC";
	}
	//alert("2");
	document.getElementById('sortColumn'+namaSkrin+cmd).value = columnName;
	document.getElementById('sortDateFormat'+namaSkrin+cmd).value = dateFormat;
	//alert("3");
	document.getElementById('sortColumnType'+namaSkrin+cmd).value = columnType;
	//alert("4");
}

$jquery.fn.scrollView = function () {
  return this.each(function () {
    $jquery('html, body').animate({
      scrollTop: $jquery(this).offset().top - 50
    }, 0);
  });
}

function sortOnClick(listSize,cmd,columnName,columnType,div,skrinName,param)
{
	sortOnClick(listSize,cmd,columnName,columnType,div,skrinName,param,"");
}

function sortOnClick(listSize,cmd,columnName,columnType,div,skrinName,param,dateFormat)
{
	if(listSize > 1)
	{
		//alert("1");
		getSortType(cmd,columnName,columnType,skrinName,dateFormat);
		//alert("2");
		doDivAjaxCall$formname(div,cmd,'div='+div+'&skrinName='+skrinName+param+'&actionajax=sortColumn&scrolPosition='+getPageLocation());
		//alert("3");
	}
}

var refreshInterval_columnSearch = [];
function searchColumn(listSize,cmd,columnName,columnType,div,skrinName,param)
{
		var divUnique_id = "cs"+columnName+skrinName;
		if (typeof(refreshInterval_columnSearch[divUnique_id]) != 'undefined' && refreshInterval_columnSearch[divUnique_id] != null)
		{
			window.clearInterval(refreshInterval_columnSearch[divUnique_id]);
			refreshInterval_columnSearch[divUnique_id] = null;			
		}
	
	    //alert("columnName : "+"cs"+columnName+skrinName);
		//getSortType(cmd,columnName,columnType);
		//alert("2");
		getColumnSearchType(cmd,columnName,skrinName);
		/*
		setTimeout(function() {
		doDivAjaxCall3$formname(div,cmd,'div='+div+'&skrinName='+skrinName+param+'&actionajax=searchColumn&scrolPosition='+getPageLocation());
		 }, 3000);
		 */
		 
		var timeAutoSave = 2;//5 minit
		//alert("timeAutoSave : "+timeAutoSave);
		refreshInterval_columnSearch[divUnique_id] = setInterval(function(){	
		timeAutoSave = timeAutoSave-1;		
		if(timeAutoSave <= 0)
		{	
			window.clearInterval(refreshInterval_columnSearch[divUnique_id]);
			refreshInterval_columnSearch[divUnique_id] = null;
			//alert("y : "+refreshInterval_showtime[divUnique_id]);
					
			//alert("divUnique_id : "+divUnique_id);			
			doDivAjaxCall3$formname(div,cmd,'div='+div+'&skrinName='+skrinName+param+'&actionajax=searchColumn&scrolPosition='+getPageLocation());	
		}
				
		}, 1000);
			
		//alert("3");
	
}

function setingTrDivTR(fieldFlagName,divID)
{
	//alert("fieldFlagName : "+fieldFlagName+" divID : "+divID);
	var current_divID = document.getElementById(fieldFlagName).value;
	if(current_divID!="")
	{
		if( $jquery("#"+current_divID).length)         // use this if you are using id to check
		{
			$jquery("#"+current_divID).html("");
		}
	}
	//alert("2");	
	if(divID!="")
	{
		document.getElementById(fieldFlagName).value=divID;
	}
	//alert("3");
}

	function openCrudSkrinRT(skrinName,seqName,tableName,filter,pkField){
		if(document.getElementById("flag_"+skrinName).value == "close")
		{
			$jquery("#icon_"+skrinName).html("< ");
			document.getElementById("flag_"+skrinName).value = "open";
			$jquery(document).ready(function () {
			doDivAjaxCall$formname('viewCrud'+skrinName,'openCrudSkrin','seqName='+seqName+'&skrinName='+skrinName+'&pkField='+pkField+'&tableName='+tableName+'&scrolPosition='+getPageLocation()+'&div=viewCrud'+skrinName+filter+'&filterDB='
			+filter.replace("&",""));  
			});
		}
		else
		{
			$jquery("#icon_"+skrinName).html("> ");
			document.getElementById("flag_"+skrinName).value = "close";
			$jquery("#viewCrud"+skrinName).html("");
		}	
	}


</script>


