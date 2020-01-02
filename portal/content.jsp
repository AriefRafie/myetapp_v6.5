    <div id="content">
    	#if ($page == "")
    		#parse("portal/utama.jsp")
    	#else
    		#parse("portal/${page}.jsp")
    	#end
    </div>