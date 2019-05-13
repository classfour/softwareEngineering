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
        college: ['化学工程学院', '材料科学与工程学院', '机电工程学院', '信息科学与技术学院', '经济管理学院', '理学院', '文法学院', '生命科学与技术学院', '巴黎居里工程师学院', '国际教育学院', '侯德榜工程师学院'],
        major: [
            ['环境工程', '化学工程与工艺', '能源化学工程'],
            ['高分子材料与工程', '材料科学与工程', '功能材料', '无机非金属材料工程'],
            ['机械设计制造及其自动化', '安全工程', '工业设计（艺术类）', '过程装备与控制工程', '机械工程及自动化', '包装工程', '产品设计'],
            ['计算机科学与技术', '自动化', '测控技术与仪器', '电子信息工程', '电子信息科学与技术', '通信工程', '软件工程', '数字媒体艺术'],
            ['国际经济与贸易', '经济学', '工商管理', '市场营销', '会计学', '信息管理与信息系统', '财务管理', '旅游管理', '电子商务', '物流管理'],
            ['应用化学', '材料化学', '化学', '信息与计算科学', '数学与应用数学', '电子科学与技术', '金融数学'],
            ['公共事业管理', '英语', '法学', '行政管理', '社会体育指导与管理'],
            ['生物工程', '生物技术', '制药工程', '生物医学工程'],
            ['化学工程与工艺（中法合作办学）', '高分子材料与工程（中法合作办学）', '生物工程（中法合作办学）'],
            ['机械设计制造及其自动化（中美）', '生物工程（中美）', '工业设计（中意合作办学）'],
            ['数据科学与大数据技术']
        ],
        courseCollege: ['化学工程学院', '材料科学与工程学院', '机电工程学院', '信息科学与技术学院', '经济管理学院', '理学院', '文法学院', '生命科学与技术学院', '巴黎居里工程师学院', '国际教育学院', '侯德榜工程师学院'],
        courseType: ['创新创业教育', '双学位', '通识及公共基础', '学科基础', '学科方向', '实践环节'],
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
            let target = e.target; // 点击的具体元素
            let targetTr = target.parentNode.parentNode; // 产生点击元素的tr元素
            if(target.className.indexOf('selectable') !== -1) { // 选中到已选条件中
                if(conditionData.selectedConditions[targetTr.id].indexOf(target.innerText) === -1) {
                    conditionData.selectedConditions[targetTr.id].push(target.innerText);
                    addSelectedCondition(targetTr.id, target.innerText);
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
    }
})
