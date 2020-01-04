var totop=document.getElementById("head1");
			var timer=null;
			window.onscroll=function(){
				var sctop=document.documentElement.scrollTop;
				/*console.log(sctop);*/
				if(sctop>=200){
					totop.style.backgroundColor="white";
					totop.style.boxShadow="2px 2px 2px gainsboro";
				}else{
					totop.style.backgroundColor="transparent";
					totop.style.boxShadow="none";
				}
			}