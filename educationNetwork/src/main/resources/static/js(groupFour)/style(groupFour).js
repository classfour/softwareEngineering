window.onload=function(){
    var objs=document.getElementById("p").getElementsByTagName("span");
    for(var i=0;i<objs.length;i++){
        objs[i].id="p"+i;
        var grade=document.getElementById("p"+i).innerHTML;
        grade=parseInt(grade+0.5);
        if(grade>=95){
            document.getElementById("p"+i).className="round A";
            document.getElementById("p"+i).innerHTML="A+";
        }
        else if(grade>=89&&grade<=94){
            document.getElementById("p"+i).className="round A";
            document.getElementById("p"+i).innerHTML="A";
        }
        else if(grade>=85&&grade<=89){
            document.getElementById("p"+i).className="round A";
            document.getElementById("p"+i).innerHTML="A-";
        }
        else if(grade>=82&&grade<=84){
            document.getElementById("p"+i).className="round B";
            document.getElementById("p"+i).innerHTML="B+";
        }
        else if(grade>=78&&grade<=81){
            document.getElementById("p"+i).className="round B";
            document.getElementById("p"+i).innerHTML="B";
        }
        else if(grade>=75&&grade<=77){
            document.getElementById("p"+i).className="round B";
            document.getElementById("p"+i).innerHTML="B-";
        }
        else if(grade>=72&&grade<=74){
            document.getElementById("p"+i).className="round C";
            document.getElementById("p"+i).innerHTML="C+";
        }
        else if(grade>=68&&grade<=71){
            document.getElementById("p"+i).className="round C";
            document.getElementById("p"+i).innerHTML="C";
        }
        else if(grade>=64&&grade<=67){
            document.getElementById("p"+i).className="round C";
            document.getElementById("p"+i).innerHTML="C-";
        }
        else if(grade>=61&&grade<=63){
            document.getElementById("p"+i).className="round D";
            document.getElementById("p"+i).innerHTML="D+";
        }
        else if(grade==60){
            document.getElementById("p"+i).className="round D";
            document.getElementById("p"+i).innerHTML="D";
        }
        else{
            document.getElementById("p"+i).className="round D";
            document.getElementById("p"+i).innerHTML="F";
        }
    }


    objs=document.getElementById("select");
    var s=document.getElementById("select")[9].text;
    for(var i=0;i<8;i++){
        if(s==objs[i].value){
            objs[i].selected=true;
            break;
        }
    }

    //为p标签动态添加id
    objs=document.getElementById("p").getElementsByTagName("p");
    for(var i=0;i<objs.length;i++){
        objs[i].id="detail"+i;
    }

    //蔡秉岐的js样式
    //为查询button编写动态ID
    var button_id=document.getElementById("p").getElementsByTagName("button");
    for(var i=0;i<button_id.length;i++)
    {
        button_id[i].id="button"+i;
    }

}

function show_score_detail(id) {
    var num=0;

    //分割出buttonid的数字部分,便于看出是第几个button
    for(var i=0;i<id.length;i++){
        if(id[i]>='0'&&id[i]<='9'){
            num=num*10+(parseInt)(id[i]-'0');
        }
    }
    var ret=num*7;
    var s=[];
    for(var i=0;i<7;i++){
        s.push(document.getElementById("detail"+(ret+i)).innerHTML);
    }
    swal({
        title: '<h4>成绩明细</h4>',
        showCloseButton: true,
        html:'<table class="table">' +
            '<tr>' +
            '<th>平时成绩</th>' +
            '<th>平时成绩占比</th>'+
            '</tr>' +
            '<tr><td>'+s[0]+'</td><td>'+s[1]+'%'+'</td></tr>'+
            '<tr>' +
            '<th>期中成绩</th>' +
            '<th>期中成绩占比</th>'+
            '</tr>' +
            '<tr><td>'+s[2]+'</td><td>'+s[3]+'%'+'</td></tr>'+
            '<tr>' +
            '<th>期末成绩</th>' +
            '<th>期末成绩占比</th>'+
            '</tr>' +
            '<tr><td>'+s[4]+'</td><td>'+s[5]+'%'+'</td></tr>'+
            '</table>'+
            '<h4>总评成绩'+s[6]+'</h4>',
    })
}

function GetSelect() {
    var myselect=document.getElementById("select");
    var value=myselect.options[9].text;
    return value;
}


function Gets(path) {
    var s;
    if(path=="all"){
        s="全部成绩";
    }
    else if(path=="2016-2017-1"){
        s="2016-2017学年第1学期成绩";
    }
    else if(path=="2016-2017-2"){
        s="2016-2017学年第2学期成绩";
    }
    else if(path=="2017-2018-1"){
        s="2017-2018学年第1学期成绩";
    }
    else if(path=="2017-2018-2"){
        s="2017-2018学年第2学期成绩";
    }
    else if(path=="2018-2019-1"){
        s="2018-2019学年第1学期成绩";
    }
    else if(path=="2018-2019-2"){
        s="2018-2019学年第2学期成绩";
    }
    else if(path=="2019-2020-1"){
        s="2019-2020学年第1学期成绩";
    }
    else if(path="2019-2020-2"){
        s="2019-2020学年第2学期成绩";
    }
    return s;
}