window.onload=function () {
    var dom4 = document.getElementById("t4");
    var Chart4 = echarts.init(dom4);
    option4 = {
        title: {
            text: '基础雷达图'
        },
        tooltip: {},
        legend: {
            data: ['武汉', '深圳','北京','上海','广州','杭州']
        },
        radar: {
            // shape: 'circle',
            name: {
                textStyle: {
                    color: '#fff',
                    backgroundColor: '#999',
                    borderRadius: 3,
                    padding: [3, 5]
                }
            },
            indicator: [
                { name: '五险一金', max: 0.8},
                { name: '节日福利', max: 0.4},
                { name: '团建旅游', max: 0.5},
                { name: '绩效奖金', max: 0.8},
                { name: '带薪年假', max: 0.4},
                { name: '住行补贴', max: 0.6}
            ]
        },
        series: [{
            name: '城市pk',
            type: 'radar',
            // areaStyle: {normal: {}},
            data : [
                {
                    value : [0.6752, 0.34, 0.34, 0.73, 0.29, 0.5284],
                    name : '武汉'
                },
                {
                    value : [0.7881, 0.3115, 0.4249,0.7211, 0.2890, 0.4618],
                    name : '深圳'
                },
                {
                    value : [0.7598, 0.1833, 0.22278, 0.5991, 0.2135, 0.5819],
                    name : '北京'
                },
                {
                    value : [0.7872, 0.2504, 0.3772, 0.7050, 0.2999, 0.552],
                    name : '上海'
                },
                {
                    value : [0.7379, 0.3265, 0.4140, 0.7321, 0.31, 0.5085],
                    name : '广州'
                },
                {
                    value : [0.7621, 0.2545, 0.3677,0.6791,0.2316, 0.5948],
                    name : '杭州'
                }
            ]
        }]
    };
    Chart4.setOption(option4);
    $.get("/trend/citysa",function (data) {
        var dom = document.getElementById("t1");
        var Chart = echarts.init(dom);
        var app = {};
        option = null;
        app.title = '坐标轴刻度与标签对齐';

        option = {
            title: {
                text: '热门城市大数据职业平均薪资'
            },
            color: ['#3398DB'],
            tooltip: {
                trigger: 'axis',
                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: [
                {
                    type: 'category',
                    data: data[0],
                    axisTick: {
                        alignWithLabel: true
                    }
                }
            ],
            yAxis: [
                {
                    type: 'value'
                }
            ],
            series: [
                {
                    name: '平均薪资',
                    type: 'bar',
                    barWidth: '60%',
                    data: data[1]
                }
            ]
        };
        Chart.setOption(option, true);
    })

    $.get("/trend/edusa",function (data) {
        var dom = document.getElementById("t2");
        var Chart = echarts.init(dom);
        var app = {};
        option = null;
        app.title = '坐标轴刻度与标签对齐';

        option = {
            title: {
                text: '各学历平均薪资'
            },
            color: ['red'],
            tooltip: {
                trigger: 'axis',
                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: [
                {
                    type: 'category',
                    data: data[0],
                    axisTick: {
                        alignWithLabel: true
                    }
                }
            ],
            yAxis: [
                {
                    type: 'value'
                }
            ],
            series: [
                {
                    name: '平均薪资',
                    type: 'bar',
                    barWidth: '60%',
                    data: data[1]
                }
            ]
        };
        Chart.setOption(option, true);
    })

    $.get("trend/sdata",function (data) {



    var dom = document.getElementById("t0");
    var myChart = echarts.init(dom);
    var app = {};
    option = null;


    setTimeout(function () {
        option = {
            legend: {},
            tooltip: {
                trigger: 'axis',
                showContent: false
            },
            dataset: {
                source: data
            },
            xAxis: {type: 'category'},
            yAxis: {gridIndex: 0},
            grid: {top: '55%'},
            series: [
                {type: 'line', smooth: true, seriesLayoutBy: 'row'},
                {type: 'line', smooth: true, seriesLayoutBy: 'row'},
                {type: 'line', smooth: true, seriesLayoutBy: 'row'},
                {type: 'line', smooth: true, seriesLayoutBy: 'row'},
                {type: 'line', smooth: true, seriesLayoutBy: 'row'},
                {type: 'line', smooth: true, seriesLayoutBy: 'row'},
                {
                    type: 'pie',
                    id: 'pie',
                    radius: '30%',
                    center: ['50%', '25%'],
                    label: {
                        formatter: '{b}: {@2012} ({d}%)'
                    },
                    encode: {
                        itemName: 'product',
                        value: '2012',
                        tooltip: '2012'
                    }
                }
            ]
        };

        myChart.on('updateAxisPointer', function (event) {
            var xAxisInfo = event.axesInfo[0];
            if (xAxisInfo) {
                var dimension = xAxisInfo.value + 1;
                myChart.setOption({
                    series: {
                        id: 'pie',
                        label: {
                            formatter: '{b}: {@[' + dimension + ']} ({d}%)'
                        },
                        encode: {
                            value: dimension,
                            tooltip: dimension
                        }
                    }
                });
            }
        });

        myChart.setOption(option);

    });;
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
    });


    $.get("trend/jobnum",function (data) {
        console.log(data);

    var dom = document.getElementById("t3");
    var myChart = echarts.init(dom);
    var app = {};
    option = null;
    app.title = '堆叠条形图';

    option = {
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        legend: {
            data: ['硬件开发', '后端开发','移动开发','前端开发','Java',
                '运维/技术支持','测试工程师','数据','测试','运维工程师','项目管理',
                '人工智能','电子/半导体','硬件','高端技术职位','算法工程师',
                '售前工程师','C++','嵌入式','Android','C','电子工程师','电气工程师',
                '项目助理','IOS']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis:  {
            type: 'value'
        },
        yAxis: {
            type: 'category',
            data: ['深圳','北京','上海','广州','武汉']
        },
        series: [
            {
                name: '硬件开发',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'insideRight'
                    }
                },
                data: data[1]
            },
            {
                name: '后端开发',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'insideRight'
                    }
                },
                data: data[3]
            },
            {
                name: '移动开发',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'insideRight'
                    }
                },
                data: data[5]
            },
            {
                name: '前端开发',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'insideRight'
                    }
                },
                data: data[7]
            },
            {
                name: 'Java',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'insideRight'
                    }
                },
                data: data[9]
            },
            {
                name: '运维/技术支持',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'insideRight'
                    }
                },
                data: data[11]
            },
            {
                name: '测试工程师',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'insideRight'
                    }
                },
                data: data[13]
            },
            {
                name: '数据',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'insideRight'
                    }
                },
                data: data[15]
            },
            {
                name: '测试',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'insideRight'
                    }
                },
                data: data[17]
            },
            {
                name: '运维工程师',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'insideRight'
                    }
                },
                data: data[19]
            },
            {
                name: '项目管理',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'insideRight'
                    }
                },
                data: data[21]
            },
            {
                name: '人工智能',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'insideRight'
                    }
                },
                data: data[23]
            },
            {
                name: '电子/半导体',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'insideRight'
                    }
                },
                data: data[25]
            },
            {
                name: '硬件',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'insideRight'
                    }
                },
                data: data[27]
            },
            {
                name: '高端技术职位',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'insideRight'
                    }
                },
                data: data[29]
            },
            {
                name: '算法工程师',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'insideRight'
                    }
                },
                data: data[31]
            },
            {
                name: '售前工程师',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'insideRight'
                    }
                },
                data: data[33]
            },

            {
                name: 'C++',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'insideRight'
                    }
                },
                data: data[35]
            },
            {
                name: '嵌入式',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'insideRight'
                    }
                },
                data: data[37]
            },
            {
                name: 'Android',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'insideRight'
                    }
                },
                data: data[39]
            },
            {
                name: 'C',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'insideRight'
                    }
                },
                data: data[41]
            },
            {
                name: '电子工程师',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'insideRight'
                    }
                },
                data: data[43]
            },
            {
                name: '电气工程师',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'insideRight'
                    }
                },
                data: data[45]
            },
            {
                name: '项目助理',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'insideRight'
                    }
                },
                data: data[47]
            },
            {
                name: 'IOS',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'insideRight'
                    }
                },
                data: data[49]
            },
        ]
    };;
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }

    });
}


    /*["product", "0+", "1+", "2年+", "3年+", "5年+", "8年+"],
    ["3K以下", 4319, 907, 447, 467, 220, 31],
    ["3K-5K", 26844, 18170, 7305, 3134, 353, 15],
    ["5K-10K", 98692, 59965, 51046, 41633, 8576, 481],
    ["10K-20K", 30337, 17545, 30596, 62957, 27846, 2138,
    ["20K-50K", 4607, 2972, 2844, 10500, 13414, 2551],
    ["50K+", 304, 251, 53, 179, 373, 120]*/
