const conditionData = {
    name: {
        grade: "年级",
        college: "学院",
        major: "专业",
        courseCollege: "开课学院",
        courseType: "课程类别",
        courseNature: "课程性质",
        teachModel: "教学模式",
        courseAssignment: "课程归属",
        courseWeek: "上课星期",
        courseSessions: "上课节次",
        courseClass: "教学班",
        whetherRebuild: "是否重修",
        hasLeft: "有无余量"
    },
    data: {
        grade: [2022, 2021, 2020, 2019, 2018, 2017, 2016, 2015, 2014, 2013],
        college: ['化学工程学院', '材料科学与工程学院', '机电工程学院', '信息科学与技术学院', '经济管理学院', '理学院'],
        major: [
            ['环境工程', '化学工程与工艺'],
            ['材料', '高分子材料'],
            ['机械'],
            ['计算机科学与技术', '自动化', '信息'],
            ['工商管理', '法学'],
            ['应用化学']
        ],
        courseCollege: ['化学工程学院', '材料科学与工程学院', '机电工程学院', '信息科学与技术学院', '经济管理学院', '理学院'],
        courseType: ['创新创业教育', '双学位', '通识及公共基础', '学科基础', '学科方向', '实践环节'],
        courseNature: ['双学位必修', '实践环节选修', '创新创业选修', '学科基础必修', '通识教育课程', '公共基础必修'],
        teachModel: ['双语教学', '中文教学'],
        courseAssignment: ['管理类', '经济类', '人文社科类', '体育类', '艺术类', '创新类'],
        courseWeek: ['星期一', '星期二', '星期三', '星期四', '星期五', '星期六', '星期日'],
        courseSessions: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13],
        courseClass: [],
        whetherRebuild: ["是", "否"],
        hasLeft: ["有", "无"]
    }
}

window.onload = function () {
    const initTable = () => {
        let table = document.getElementById('conditionTable');
        let tableText = '';
        for(let eachName in conditionData.name) {
            tableText += `<tr id="${eachName}"><td>${conditionData.name[eachName]}：</td><td>`
            for(let item of conditionData.data[eachName]) {
                tableText += `<span class="condition-value-item">${item}</span>`
            }
            tableText += `</td></tr>`
        }
        table.innerHTML += tableText;
    }
    initTable();
}

let toggleBtn = document.getElementById('toggleBtn');
let conditionTable = document.getElementById('conditionTable');

// 收起课程查询详细信息，改变切换标志
toggleBtn.onclick = () => {
    if(conditionTable.className.indexOf("hide") != -1) {
        conditionTable.className = "condition-container";
        toggleBtn.style.backgroundPositionY = "-15px"
        toggleBtn.innerText = "收起";
    } else {
        conditionTable.className += " hide";
        toggleBtn.style.backgroundPositionY = "3px"
        toggleBtn.innerText = "展开";
    }
}
