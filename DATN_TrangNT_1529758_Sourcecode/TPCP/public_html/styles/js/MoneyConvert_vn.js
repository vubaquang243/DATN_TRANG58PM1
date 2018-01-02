
var flag = true;
var arr1=new Array(""," nghìn"," triệu"," tỷ")
var arr2=new Array("không","mười","hai mươi","ba mươi","bốn mươi","năm mươi","sau mươi","bảy mươi","tám mươi","chín mươi")
var arr3=new Array("không","một","hai","ba","bốn","năm","sáu","bảy","tám","chín");
var arr4=new Array("mười","mười một","mười hai","mười ba","mười bốn","mười lăm","mười sáu","mười bảy","mười tám","mười chín");
var _point="phẩy";

 var mangso = ['không','một','hai','ba','bốn','năm','sáu','bảy','tám','chín'];
 
 function dochangchuc(so,daydu)
    {
        var chuoi = "";
        chuc = Math.floor(so/10);
        donvi = so%10;
        if (chuc>1) {
            chuoi = " " + mangso[chuc] + " mươi";
            if (donvi==1) {
                chuoi += " mốt";
            }
        } else if (chuc==1) {
            chuoi = " mười";
            if (donvi==1) {
                chuoi += " một";
            }
        } else if (daydu && donvi>0) {
            chuoi = " lẻ";
        }
        if (donvi==5 && chuc>1) {
            chuoi += " lăm";
        } else if (donvi>1||(donvi==1&&chuc==0)) {
            chuoi += " " + mangso[ donvi ];
        } 
        return chuoi;
    }
    function docblock(so,daydu)
    {
        var chuoi = "";
        tram = Math.floor(so/100);
        so = so%100;
        if (daydu || tram>0) {
            chuoi = " " + mangso[tram] + " trăm";
            chuoi += dochangchuc(so,true);
        } else {
            chuoi = dochangchuc(so,false);
        }
        return chuoi;
    }
    function dochangtrieu(so,daydu)
    {
        var chuoi = "";
        trieu = Math.floor(so/1000000);
        so = so%1000000;
        if (trieu>0) {
            chuoi = docblock(trieu,daydu) + " triệu";
            daydu = true;
        }
        nghin = Math.floor(so/1000);
        so = so%1000;
        if (nghin>0) {
            chuoi += docblock(nghin,daydu) + " nghìn";
            daydu = true;
        }
        if (so>0) {
            chuoi += docblock(so,daydu);
        }
        return chuoi;
    }
    function docso(so)
    {
        if (so==0) return " không";
        var chuoi = "", hauto = "";
        do {
            ty = so%1000000000;
            so = Math.floor(so/1000000000);
            if (so>0) {
                chuoi = dochangtrieu(ty,true) + hauto + chuoi;
            } else {
                chuoi = dochangtrieu(ty,false) + hauto + chuoi;
            }
            hauto = " tỷ";
        } while (so>0);
        return chuoi;
    }

function English(num){
	/*strRet=""
	if((num.length==3) && (num.substr(0,3)!="000")){
		if((num.substr(0,1)!="0")){
			strRet+=arr3[num.substr(0,1)]+" trăm"
			if(num.substr(1,2)!="00")strRet+=" và "
		}
		num=num.substring(1);
	}
	if((num.length==2)){
		if((num.substr(0,1)=="0")){
			num=num.substring(1)
		}
		else if((num.substr(0,1)=="1")){
			strRet+=arr4[num.substr(1,2)]
		}
		else{
			strRet+=arr2[num.substr(0,1)]
			if(num.substr(1,1)!="0")strRet+="-"
			num=num.substring(1)
		}
	}
	if((num.length==1) && (num.substr(0,1)!="0")){
		strRet+=arr3[num.substr(0,1)]
	}
	return strRet;
	*/	
}

//function toEnglishCash(sAmount){
function toEnglishCash(so){
    //so = so.replace(/[^0-9.]/g, '');	
	 if (so==0) return " không đồng";
        var chuoi = "", hauto = "";
        do {
            ty = so%1000000000;
            so = Math.floor(so/1000000000);
            if (so>0) {
                chuoi = dochangtrieu(ty,true) + hauto + chuoi;
            } else {
                chuoi = dochangtrieu(ty,false) + hauto + chuoi;
            }
            hauto = " tỷ";
        } while (so>0);
//        if(chuoi.substr(1, 3).toUpperCase() === "LĂM" || chuoi.substr(0, 2).toUpperCase() === "LĂM"){
//        	return chuoi.toUpperCase().replace("LĂM", "NĂM")+" ĐỒNG";
//        }else{
//        	return chuoi.toUpperCase()+" ĐỒNG";
//        }
        return chuoi.trim().substring(0,1).toUpperCase()+ chuoi.trim().substring(1) + " đồng";
}
function toEnglishCashUSD(so){
    //so = so.replace(/[^0-9.]/g, '');	
	 if (so==0) return " không đồng";
        var chuoi = "", hauto = "";
        do {
            ty = so%1000000000;
            so = Math.floor(so/1000000000);
            if (so>0) {
                chuoi = dochangtrieu(ty,true) + hauto + chuoi;
            } else {
                chuoi = dochangtrieu(ty,false) + hauto + chuoi;
            }
            hauto = " tỷ";
        } while (so>0);
//        if(chuoi.substr(1, 3).toUpperCase() === "LĂM" || chuoi.substr(0, 2).toUpperCase() === "LĂM"){
//        	return chuoi.toUpperCase().replace("LĂM", "NĂM")+" ĐỒNG";
//        }else{
//        	return chuoi.toUpperCase()+" ĐỒNG";
//        }
        return chuoi.trim().substring(0,1).toUpperCase()+ chuoi.trim().substring(1) + " đô la Mỹ";
}
function docthapphan(sothapphan) {
	var chuoi="";
	switch (sothapphan) {
	case '0':
		chuoi=mangso[0];
		break;
	case '1':
		chuoi=mangso[1];
		break;
	case '2':
		chuoi=mangso[2];
		break;
	case '3':
		chuoi=mangso[3];
		break;
	case '4':
		chuoi=mangso[4];
		break;
	case '5':
		chuoi=mangso[5];
		break;
	case '6':
		chuoi=mangso[6];
		break;
	case '7':
		chuoi=mangso[7];
		break;	
	case '8':
		chuoi=mangso[8];
		break;	
	case '9':
		chuoi=mangso[9];
		break;
	default:
		
		break;
	}
	return chuoi
}

function toEnglishCashCurrency(so,currencyCode){
	so = so.replace(/[^0-9.]/g, '');	
	if(currencyCode==null){
		currencyCode='';
	}
	var sothapphan=so.split(".");
	var chuoithapphan="";
	if(sothapphan.length==2){
//		if(sothapphan[1]=='00') 
	}
//	if(sothapphan.length==2){
//		if (sothapphan[1]=='00') {
//			chuoithapphan='';
//		}else{
//			do {
//				var ty = sothapphan[1]%1000000000;
//				chuoithapphan = dochangtrieu(ty,false) + chuoithapphan;
//	        } while (sothapphan[1]>0);
//		}
//	}
	var chuoi = "", hauto = "";
	if(sothapphan.length<2){
		if (so==0) return " không "+ currencyCode;
        
        do {
            ty = so%1000000000;
            so = Math.floor(so/1000000000);
            if (so>0) {
                chuoi = dochangtrieu(ty,true) + hauto + chuoi;
            } else {
                chuoi = dochangtrieu(ty,false) + hauto + chuoi;	
            }
            hauto = " tỷ";
        } while (so>0);
	}else{
		so=sothapphan[0];
		if (so==0) return " không "+ currencyCode;
        
        do {
            ty = so%1000000000;
            so = Math.floor(so/1000000000);
            if (so>0) {
                chuoi = dochangtrieu(ty,true) + hauto + chuoi;
            } else {
                chuoi = dochangtrieu(ty,false) + hauto + chuoi;	
            }
            hauto = " tỷ";
        } while (so>0);
		chuoi=chuoi +" ";
		var so1 =sothapphan[1].substring(0, 1);
		var so2 =sothapphan[1].substring(1, 2);
		if(so1==0&&so2!=0){
			chuoi=chuoi +_point+" "+ docthapphan(so1)+" "+docthapphan(so2);
		}else if(so1==0 && so2==0){
			chuoi=chuoi;
		}else if(so1!=0 && so2==0){
			chuoi=chuoi +_point+" "+ docthapphan(so1);
		}else{
			chuoi=chuoi +" "+_point+ " "+docthapphan(so1)+" "+docthapphan(so2);
		}
//		chuoi=docthapphan(sothapphan[1]);
	}
	 
//        if(chuoi.substr(1, 3).toUpperCase() === "LĂM" || chuoi.substr(0, 2).toUpperCase() === "LĂM"){
//        	return chuoi.toUpperCase().replace("LĂM", "NĂM")+" ĐỒNG";
//        }else{
//        	return chuoi.toUpperCase()+" ĐỒNG";
//        }
        return chuoi.toUpperCase()+" "+ currencyCode;
}

function checkNumber(sAmount){
	var amtExp=/^([0-9,.])*$/;
	return amtExp.test(sAmount);
}




