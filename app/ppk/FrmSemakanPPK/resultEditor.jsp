<br />
#if($!htmlPaging != "")

$!htmlPaging

#end
<div class="boxViewShadow cal_scroller"  >
    <div id="containerScroll"  class="adjust cal_container">
 	$html
    </div>
</div>



   

<script>
/*
(function () {
    var thElm;
    var startOffset;
	var startOffsetCont;
	
	//document.getElementById("containerScroll").style.width = document.getElementById("containerScroll").offsetWidth + 'px';
	//var currWidthCont = document.getElementById("containerScroll").offsetWidth;
	
	//alert('1 : '+document.getElementById("containerScroll").offsetWidth);
    Array.prototype.forEach.call(
      document.querySelectorAll(" .adjust"),
      function (th) {
        th.style.position = 'relative';
		document.getElementById("containerScroll").style.position = 'relative';

        var grip = document.createElement('div');
        grip.innerHTML = "&nbsp;";
        grip.style.top = 0;
        grip.style.right = 0;
        grip.style.bottom = 0;
        grip.style.width = '5px';
        grip.style.position = 'absolute';
        grip.style.cursor = 'col-resize';
        grip.addEventListener('mousedown', function (e) {
			//alert('2');
            thElm = th;
			//startOffsetCont = document.getElementById("containerScroll").offsetWidth - e.pageX;
            startOffset = th.offsetWidth - e.pageX;
			
			//alert('2 : '+startOffsetCont);
        });

        th.appendChild(grip);
      });

    document.addEventListener('mousemove', function (e) {
      if (thElm) {
		//document.getElementById("containerScroll").style.width = startOffsetCont + e.pageX + 'px';
        thElm.style.width = startOffset + e.pageX + 'px';
		
		//alert('3 : '+document.getElementById("containerScroll").thElm.style.width);
      }
    });

    document.addEventListener('mouseup', function () {
        thElm = undefined;
    });
})();
*/
</script>