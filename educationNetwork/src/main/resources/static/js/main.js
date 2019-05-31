const conditionData = {
    name: {
        grade: "年级",
        college: "学院",
        major: "专业",
        courseCollege: "开课学院",
        courseType: "课程类别",
        // courseNature: "课程性质",
        // teachModel: "教学模式",
        // courseAssignment: "课程归属",
        // courseWeek: "上课星期",
        // courseSessions: "上课节次",
        // courseClass: "教学班",
        // whetherRebuild: "是否重修",
        hasLeft: "有无余量"
    },
    data: {
        grade: [2022, 2021, 2020, 2019, 2018, 2017, 2016, 2015, 2014, 2013],
       // college: ['化学工程学院', '材料科学与工程学院', '机电工程学院', '信息科学与技术学院', '经济管理学院', '理学院', '文法学院', '生命科学与技术学院', '巴黎居里工程师学院', '国际教育学院', '侯德榜工程师学院'],
        college: ['信息学院', '材料学院', '机电学院'],

        major: [
            ['计算机科学与技术', '自动化', '测控技术与仪器'],
            ['高分子材料与工程', '功能材料'],
            ['机械设计制造及其自动化', '安全工程']
        ],
        courseCollege: ['化学工程学院', '材料学与工程学院', '机电工程学院', '信息科学与技术学院', '经济管理学院', '理学院', '文法学院', '生命科学与技术学院', '巴黎居里工程师学院', '国际教育学院', '侯德榜工程师学院'],
        courseType: ['实验课','非实验课'],
        // courseNature: ['双学位必修', '实践环节选修', '创新创业选修', '学科基础必修', '通识教育课程', '公共基础必修'],
        // teachModel: ['双语教学', '中文教学'],
        // courseAssignment: ['管理类', '经济类', '人文社科类', '体育类', '艺术类', '创新类'],
        // courseWeek: ['星期一', '星期二', '星期三', '星期四', '星期五', '星期六', '星期日'],
        // courseSessions: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13],
        // courseClass: [],
        // whetherRebuild: ["是", "否"],
        hasLeft: ["有", "无"]
    },
    presentCollege: 0, // 当前选中的专业
    selectedConditions: { // 选中的条件
        grade: [],
        major: [],
        courseCollege: [],
        courseType: [],
        hasLeft: []
    }
}

window.onload = function () {
//    console.log("hello world")
    /**
     * 初始化查询课程信息表内容
     * 查询的table由多个tr构成，每个tr包含两个td, 其中第一个td是 “年级”，“专业” 等说明文字，
     * 第二个td有若干个span，span都有condition-value-item类，又分为selectable和switchable，
     * college是switchable，点击major切换当前college的所有major, 每个college的id是专业对应在数组中的编号
     * 其他所有项都是selectable，点击后 将其选中放入 已选条件中
     * */
    const initTable = () => {
        let table = $('#conditionTable')[0];
        let tableText = '';
//        console.log(table)
        for(let eachName in conditionData.name) {
            tableText += `<tr id="${eachName}"><td>${conditionData.name[eachName]}：</td><td>`;
            if(eachName === 'major') { // 遍历到专业只输出某个院系的所有专业
                for(let item of conditionData.data[eachName][conditionData.presentCollege]) {
                    tableText += `<span class="condition-value-item selectable">${item}</span>`;
                }

            } else if(eachName === 'college') { // 遍历到院系，所有院系都是可切换的
                let presentCollege = 0;
                for(let item of conditionData.data[eachName]) {
                    tableText += `<span class="condition-value-item switchable" id="${presentCollege++}">${item}</span>`;
                }

            }else {
                for(let item of conditionData.data[eachName]) {
                    tableText += `<span class="condition-value-item selectable">${item}</span>`;
                }
            }

            tableText += `</td></tr>`;
        }
        table.innerHTML += tableText;

        // 添加点击事件，点击院系切换专业为当前院系的专业，点击其他添加到选中条件
        table.onclick = function (e) {
//            console.log("isok")
            let target = e.target; // 点击的具体元素
            let targetTr = target.parentNode.parentNode; // 产生点击元素的tr元素
            if(target.className.indexOf('selectable') !== -1) { // 选中到已选条件中
                if(conditionData.selectedConditions[targetTr.id].indexOf(target.innerText) === -1) {
                    conditionData.selectedConditions[targetTr.id].push(target.innerText);
                    addSelectedCondition(targetTr.id, target.innerText);
                    handleClick(conditionData.selectedConditions[targetTr.id], target.innerText);
                }
            } else if(target.className.indexOf('switchable') !== -1) { // 切换为当前院系的专业
                conditionData.presentCollege = target.id;
                changeCollege();
            }
        }
    }
    initTable();
}

let addSelectedCondition = (type, condition) => {
    let conditionUl = document.getElementsByClassName('selected-conditions')[0];
    let li = $(`<li class="selected-item"><span class="selected-item-question">${conditionData.name[type]}</span>:<span class="selected-item-answer">${condition}</span><span class="deselect-cross" data-type-name="${type}"></span></li>`)[0];
    conditionUl.appendChild(li);
}

// 切换院系，改变所有当前显示的专业名称
let changeCollege = () => {
    // 包含该院系所有专业名称的td
    let t = document.querySelectorAll('#conditionTable #major td')[1];
    let str = '';
    for(let item of conditionData.data['major'][conditionData.presentCollege]) {
        str += `<span class="condition-value-item selectable">${item}</span>`;
    }
    t.innerHTML = str;
}

// 点击收起或展开按钮切换课程选择详情页面的展示状态
let toggleBtn = $('#toggleBtn');
let conditionTable = $('#conditionTable');
toggleBtn.click(() => {
    if(conditionTable.hasClass('hide')) {
        toggleBtn.css('background-position-y', '-15px');
        toggleBtn.innerText = "收起";
    } else {
        toggleBtn.css('background-position-y', '3px');
        toggleBtn.innerText = "展开";
    }
    conditionTable.toggleClass('hide');
})

// 点击显示或隐藏侧边栏
let toggleArrow = $('.toggle-arrow');
let courseInfo = $('.course-info-container');
toggleArrow.click(() => {
    toggleArrow.css('background-position-x', courseInfo.hasClass('hide-aside') ? '-30px' : '4px');
    courseInfo.toggleClass('hide-aside');
})

// 已选条件ul的点击事件，将点击叉号将某项删除
let selectedConditionUl = $('.selected-conditions');
selectedConditionUl.click((e) => {
    let target = e.target;
    let targetLi = target.parentNode;
    // 点击叉号则删除该项
    if(target.className.indexOf('deselect-cross') !== -1) {
        let name = target.getAttribute('data-type-name');
        let val = targetLi.children[1].innerText;
        let pos = conditionData.selectedConditions[name].indexOf(val);
        // 从conditionData中去掉
        conditionData.selectedConditions[name].splice(pos, 1);
        // 从页面上去除
        targetLi.outerHTML = '';
            handleClick(conditionData.selectedConditions[name], pos);
    }

})
function refrush(to_server){
     $.ajax({
                        async:false,
                        type:"post",
                        data:{
                              garde:","+to_server,
                        },
                        url:"http://localhost:8080//chooseClass/search",
                        success:function (data) {
                              $("#content").empty();
                            if(data)
                            {
                                data.forEach(v=>{
                                     var tr = $("<tr></tr>")
                                     var name = $("<td></td>").text(v.name);
                                     var teacher_number = $("<td></td>").text(v.teacher_number);
                                     var time = $("<td></td>").text(v.occupation);
                                     var location = $("<td></td>").text(v.location);
                                     var type = $("<td></td>").text(v.type);
                                     var people = $("<td></td>").text(v.people);
                                     var max_number = $("<td></td>").text(v.max_number);
                                      var btn;
                                      var status ;
                                      if( v.choosestatus=="0"   )
                                      {
                                      status= $("<td></td>").text("未选");
                                      btn = $("<button type='button' class='btn btn-default'></button>").text("选课");
                                      }
                                      else
                                      {
                                       status= $("<td></td>").text("已选");
                                       btn = $("<button type='button' class='btn btn-default'></button>").text("退选");

                                      }

                     //                var status = $("<td th:if="${v.choosestatus == 0 }"></td>").text("未选 ");
                       //                 status = $("<td th:if="${v.choosestatus == 1 }"></td>").text("已选");
                                     //<td th:if="${temp.choosestatus == 0 }" >未选</td>

                                     //var btn = $("<button type='button' class='btn btn-default'></button>").text("选课");

                                                             btn.on("click", function () {


                                                             if( v.choosestatus=="0"   )
                                                             {
                                                     $.ajax({
                                                              async:false,
                                                              data: {
                                                                  "number":v.number
                                                                    },
                                                              type:"post",
                                                              url:"http://localhost:8080//chooseClass/exit",
                                                              success:function (data) {
                                                                  if(data=="true") {
                                                                      alert("选课成功");
                                                                        refrush(to_server);

                                                                      console.log("hhh");
                                                                  }else{
                                                                       console.log(data);
                                                                      alert("选课失败");
                                                                  }

                                                              }
                                                          })
                                                             }
                                                             else
                                                             {
                                                                $.ajax({
                                                                            async:false,
                                                                            data:{
                                                                                "number":v.number
                                                                            },
                                                                            type:"post",
                                                                            url:"http://localhost:8080//chooseClass/retire",
                                                                            success:function (data) {
                                                                                if(data=="true") {
                                                                                    alert("退选成功")
                                                                                    refrush(to_server);
                                                                                }else{
                                                                                    alert("退选失败")
                                                                                }
                                                                                location.reload();
                                                                            }
                                                                        })

                                                             }


                                                             })
                                    console.log(name);
                                    tr.append( name,teacher_number,time,location,type,people,max_number,status,btn);
                                    $("#content").append(tr);
                             });

                                console.log(to_server);

                            }else{
                                alert("打印失败")
                            }
                           // location.reload();
                        }
                    })

}

let handleClick = (array, pos) => {
//    console.log(array, pos);
    var to_server="";
 //   to_server=to_server+
    to_server=to_server+conditionData.selectedConditions.grade+",";
    to_server=to_server+conditionData.selectedConditions.major+",";
    to_server=to_server+conditionData.selectedConditions.courseCollege+",";
    to_server=to_server+conditionData.selectedConditions.courseType+",";
    to_server=to_server+conditionData.selectedConditions.hasLeft+"";
    var temp = conditionData.selectedConditions.hasLeft;
    console.log(temp);
    console.log(conditionData.selectedConditions.grade)
    // 接收参数
    // 发送后端http请求

    $.ajax({
                       async:false,
                       type:"post",
                       data:{
                             garde:","+to_server,
                       },
                       url:"http://localhost:8080//chooseClass/search",
                       success:function (data) {
                             $("#content").empty();
                           if(data)
                           {
                               data.forEach(v=>{
                                    var tr = $("<tr></tr>")
                                    var name = $("<td></td>").text(v.name);
                                    var teacher_number = $("<td></td>").text(v.teacher_number);
                                    var time = $("<td></td>").text(v.occupation);
                                    var location = $("<td></td>").text(v.location);
                                    var type = $("<td></td>").text(v.type);
                                    var people = $("<td></td>").text(v.people);
                                    var max_number = $("<td></td>").text(v.max_number);
                                     var btn;
                                     var status ;
                                     if( v.choosestatus=="0"   )
                                     {
                                     status= $("<td></td>").text("未选");
                                     btn = $("<button type='button' class='btn btn-default'></button>").text("选课");
                                     }
                                     else
                                     {
                                      status= $("<td></td>").text("已选");
                                      btn = $("<button type='button' class='btn btn-default'></button>").text("退选");

                                     }

                    //                var status = $("<td th:if="${v.choosestatus == 0 }"></td>").text("未选 ");
                      //                 status = $("<td th:if="${v.choosestatus == 1 }"></td>").text("已选");
                                    //<td th:if="${temp.choosestatus == 0 }" >未选</td>

                                    //var btn = $("<button type='button' class='btn btn-default'></button>").text("选课");

                                                            btn.on("click", function () {


                                                            if( v.choosestatus=="0"   )
                                                            {
                                                    $.ajax({
                                                             async:false,
                                                             data: {
                                                                 "number":v.number
                                                                   },
                                                             type:"post",
                                                             url:"http://localhost:8080//chooseClass/exit",
                                                             success:function (data) {
                                                                 if(data=="true") {
                                                                     alert("选课成功");

                                                                     refrush(to_server);
                                                                     console.log("hhh");
                                                                 }else{
                                                                      console.log(data);
                                                                     alert("选课失败");


                                                                 }

                                                             }
                                                         })
                                                            }
                                                            else
                                                            {
                                                               $.ajax({
                                                                           async:false,
                                                                           data:{
                                                                               "number":v.number
                                                                           },
                                                                           type:"post",
                                                                           url:"http://localhost:8080//chooseClass/retire",
                                                                           success:function (data) {
                                                                               if(data=="true") {
                                                                                   alert("退选成功")
                                                                                    refrush(to_server);
                                                                               }else{
                                                                                   alert("退选失败")
                                                                               }

                                                                           }
                                                                       })

                                                            }


                                                            })
                                   console.log(name);
                                   tr.append( name,teacher_number,time,location,type,people,max_number,status,btn);
                                   $("#content").append(tr);
                            });

                               console.log(to_server);

                           }else{
                               alert("打印失败")
                           }
                          // location.reload();
                       }
                   })


}

