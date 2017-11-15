
/**
 * Created by xiewu on 2016/9/11.
 */
//浮点数加减乘除法对象
var jsFloat={
    'add':addCalc,//加法运算
    'subtract':subtractCalc,//减法运算
    'ride':rideCalc,//乘法运算
    'divide':divideCalc//除法运算
};

//加法运算
function addCalc(num0,num1){
    var numLen0=getPointBackLen(num0),//第一个值的的小数位数
        numLen1=getPointBackLen(num1),//第二个值的的小数位数
        maxLen=Math.max(numLen0,numLen1),//小数位数的最大值
        numLenDiff=0,//小数的差数
        num0Str='',//第一个值去掉小数点转字符
        num1Str='',//第二个值去掉小数点转字符
        operationReault=0;//返回计算后的值

    if(maxLen==0){
        operationReault=Number(num0)+Number(num1);
        return operationReault;
    }
    numLenDiff=numLen0-numLen1;
    num0Str=clearPoint(num0,".");
    num1Str=clearPoint(num1,".");

    //根据numLenDiff的正负来判断哪个数字的位数是短的，来走补全
    if(numLenDiff>0){
        num1Str=addZeroBack(num1Str,numLenDiff);
    }else if(numLenDiff<0){
        num0Str=addZeroBack(num0Str,Math.abs(numLenDiff));
    }
    //执行计算
    operationReault=addZeroFront((Number(num0Str)+Number(num1Str)).toString(),maxLen);
    return Number(operationReault.slice(0,-maxLen)+"."+operationReault.slice(-maxLen));
}

//减法运算
function subtractCalc(num0,num1){
    var numLen0=getPointBackLen(num0),//第一个值的的小数位数
        numLen1=getPointBackLen(num1),//第二个值的的小数位数
        maxLen=Math.max(numLen0,numLen1),//小数位数的最大值
        numLenDiff=0,//小数的差数
        num0Str='',//第一个值去掉小数点转字符
        num1Str='',//第二个值去掉小数点转字符
        operationReault=0;//返回计算后的值

    if(maxLen==0){
        operationReault=Number(num0)-Number(num1);
        return operationReault;
    }

    numLenDiff=numLen0-numLen1;
    num0Str=clearPoint(num0,".");
    num1Str=clearPoint(num1,".");

    //根据numLenDiff的正负来判断哪个数字的位数是短的，来走补全
    if(numLenDiff>0){
        num1Str=addZeroBack(num1Str,numLenDiff);
    }else if(numLenDiff<0){
        num0Str=addZeroBack(num0Str,Math.abs(numLenDiff));
    }
    //执行计算
    operationReault=addZeroFront((Number(num0Str)-Number(num1Str)).toString(),maxLen);
    return Number(operationReault.slice(0,-maxLen)+"."+operationReault.slice(-maxLen));
}

//乘法运算
function rideCalc(num0,num1){
    var num0Len=getPointBackLen(num0),//第一个值的的小数位数
        num1Len=getPointBackLen(num1),//第二个值的的小数位数
        maxLen=Math.max(num0Len,num1Len),//取得小数位数中的最大数
        digitLen=0,//小数位数的统计值
        num0Str='',//第一个值去掉小数点转字符存偖
        num1Str='',//第二个值去掉小数点转字符存偖
        operationReault=0;//计算结果

    if(maxLen===0){//如果数字原本就是整形，直接执行计算
        operationReault=Number(num0)*Number(num1);
        return operationReault;
    }

    num0Str=clearPoint(num0,".");
    num1Str=clearPoint(num1,".");
    digitLen=num0Len+num1Len;//小数位数的总数
    
    operationReault=addZeroFront((Number(num0Str)*Number(num1Str)).toString(),digitLen);//对乘法运算后的结果执行位数补全
    return Number(operationReault.slice(0,-digitLen)+"."+operationReault.slice(-digitLen));
}

//除法运算
function divideCalc(num0,num1){
    var num0Len=getPointBackLen(num0),//第一个值的的小数位数
        num1Len=getPointBackLen(num1),//第二个值的的小数位数
        maxLen=Math.max(num0Len,num1Len),//取得小数位数中的最大数
        digitLen,//小数位数的统计值
        num0Str,//第一个值去掉小数点转字符存偖
        num1Str,//第二个值去掉小数点转字符存偖
        operationReault,//计算结果
        resultPointNum;//除法后结果的小数位数

    if(maxLen===0){//如果数字原本就是整形，直接执行计算
        operationReault=Number(num0)/Number(num1);
        return operationReault;
    }

    num0Str=clearPoint(num0,".");
    num1Str=clearPoint(num1,".");
    digitLen=num0Len-num1Len;//小数位数的差数
    operationReault=Number(num0Str)/Number(num1Str);
    if(digitLen==0){//如果除数，被除除数小数位相同，即直接返回计算值
        return operationReault;
    }
    resultPointNum=getPointBackLen(operationReault);//除法计算后可能的小数位数
    operationReault=clearPoint(operationReault,".");//除法运算结果去小数位数
    digitLen=digitLen+resultPointNum;//最后应该保留的小数位数
    if(digitLen>0){//如果要保留的小数位数不够
        operationReault=addZeroFront(operationReault,digitLen);//对计算的结果前补0
        return Number(operationReault.slice(0,-digitLen)+"."+operationReault.slice(-digitLen));
    }else {//如果要保留的小数位数小于0
        digitLen=Math.abs(digitLen);
        operationReault=addZeroBack(operationReault,digitLen);//对计算的结果后补0
        return Number(operationReault);
    }

}
//后补0补够位数,str是当前的字符串，len是应该补的位数
function addZeroBack(str,len){
    for(var i=0;i<len;i++){
        str=str+"0";
    }
    return str;
}
//前置0补够位数,str是当前的字符串，len是处理后应该有的位数
function addZeroFront(str,len){
    var getStr='',
        saveStr='',
        strLen=0,//存储当前字符串的长度
        addLen=0,//应该补位的个数
        returnStr='';
    //如果传进去的值是负值，那就要先存储符号
    if(str.indexOf('-')!=-1){
        getStr=str.replace('-','');
        saveStr='-';
    }else{
        getStr=str;
    }
    strLen=getStr.length;
    addLen=len-strLen;
    if(addLen<=0){
        returnStr=str;
    } else {
        for(var i=0;i<addLen;i++){
            getStr="0"+getStr;
        }
        returnStr=saveStr+getStr;
    }
    return returnStr;
}
//取得小数位数
function getPointBackLen(num){
    var returnLen=0;
    try {
        returnLen=num.toString().split(".")[1].length;//获取小数位数
    }catch(e){
        returnLen=0;
    }
    return returnLen;
}
//浮点型数去小数点转字符串
function clearPoint(num,str){
    var getStr=num.toString();
    if(getStr.indexOf(str)!=-1){
        return getStr.replace(str,"");
    }
    return getStr;
}

//保留两位小数
function toDecimal2(x){
	try{
		if(x.toString().split(".")[1].length >=3){
			x = (((Math.floor(x * 100) / 100)*1000)+ (0.01*1000))/1000;
		}
	}catch(e){}
    var f = parseFloat(x);
    if (isNaN(f)) {    
        return false;    
    };
    var f = Math.round(x*100)/100;    
    var s = f.toString();    
    var rs = s.indexOf('.');    
    if (rs < 0) {    
        rs = s.length;    
        s += '.';    
    }    
    while (s.length <= rs + 2) {    
        s += '0';    
    }    
    return s;    
}