var    app = new Vue({
    el:"#goods_list_main_app",
    data:{
        category:{},//商品分类
        brandList :[],//品牌list集合
        attrList:[],//属性集合  [{text :属性名称，items:[{text：选项名称}]}]
    },
    // 在页面初始化完成的时候调用
    created:function () {
        //通过钩子函数加载商品分类对象
        this.loadCategory();
        //加载品牌列表
        this.loadBrandList();

        //根据商品分类加载商品属性以及属性选项
        this.loadAttrList();
    },
    methods:{
        loadCategory:function(){
            axios.get(baseUrl +"/api/category/"+categoryId).then(function(reason){
                app.category  =reason.data;//接受结果信息，给uses赋值
            });
        },
        loadBrandList:function(){
            axios.get(baseUrl +"/api/brand/list").then(function(reason){
                app.brandList  =reason.data;//接受结果信息，给uses赋值
            });
        },
        loadAttrList:function(){
            axios.get(baseUrl +"/api/attr/list?categoryId="+categoryId).then(function(reason){
                let  atrrListTemp =reason.data;//接受结果信息，给uses赋值
                let itemsTemp  = [];
                for(let  i =  0 ; i< atrrListTemp.length ; i++){

                    itemsTemp  =$.parseJSON(  atrrListTemp[i].attrItems );
                    // attrList:[],//属性集合  [{text :属性名称，items:[{text：选项名称}]}]
                    app.attrList.push({text:  atrrListTemp[i].name ,items: itemsTemp });
                }
            });
        }
    }
});