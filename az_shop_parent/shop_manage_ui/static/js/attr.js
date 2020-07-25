var app = new Vue({
    el:"#app",
    data:{
        //标准tree格式数据
        categoryTree:[],
        //简单的分类树json格式数据
        categoryList:[],
        //树结构默认显示结构
        defaultProps: {
            children: 'children',
            label: 'text'
        },
        // 商品属性列表
        attrList:[],
        // 定义【商品分类】的主键id,默认为0
        categoryId:0,
        // 定义【商品分类】list面包屑集合
        categoryMianBao:{},

        // 控制dialog开启与关闭
        dialogVisible:false,
        // 定义提交表单对象
        attrForm:{
            id:0,
            itemList:[{text:""},],// 定义【属性值】集合，需要在实体类中也有该属性
        },
    },
    created:function () {
        this.loadCategroyTree();
        this.loadAttrList();
    },
    methods:{
        // 首页点击事件
        shouye:function () {
            app.categoryId=0;
            app.loadAttrList();
            app.categoryMianBao={};

        },
        // 加载分类对象，面包屑使用
        loadCategorys:function () {
            axios.get(baseUrl+"/api/category/"+app.categoryId).then(function (value) {
                app.categoryMianBao = value.data;
            })
        },
        // 判断是保存， 还是 修改
        save:function () {
            // 不管是修改，还是添加，都需要拿到 商品分类，意思：是修改哪个商品的属性
            app.attrForm.categoryId=app.categoryId;

            //属性的主键id如果有值，就执行修改方法，否则就执行添加方法
            if(app.attrForm.id > 0 ){//执行修改请求
                app.updateAttr();
            }else{//执行添加请求
                app.saveAttr();
            }
        },
        // 根据主键id查询属性
        handleEdit:function (row) {
            app.dialogVisible = true;
            axios.get(baseUrl + "/api/attr/"+row.id).then(function (value) {
                app.attrForm = value.data;
                // 将获取到的attrItems 转换为 list 集合
                app.attrForm.itemList = $.parseJSON(app.attrForm.attrItems)
            })
        },
        // 执行修改
        updateAttr:function () {
            axios.put(baseUrl +"/api/attr/attr",app.attrForm).then(function (value) {
                if(value.data.success){
                    app.dialogVisible = false;//关闭dialog
                    app.loadAttrList(); // 重新加载属性页面
                    app.$message('修改属性成功');
                }else{
                    app.$message.error('修改属性失败');
                }
            })
        },
        // 添加保存方法
        saveAttr:function () {
            axios.post(baseUrl +"/api/attr/attr",app.attrForm).then(function (value) {
                if(value.data.success){
                    app.dialogVisible = false;//关闭dialog
                    app.loadAttrList(); // 重新加载属性页面
                    app.$message('添加属性成功');
                }else{
                    app.$message.error('添加属性失败');
                }
            })
        },
        //增加属性选项
        addItem:function () {
            app.attrForm.itemList.push({});
        },
        // 删除属性选项
        delItem:function (index) {
            app.attrForm.itemList.splice(index,1)
        },
        // 加载商品属性列表
        loadAttrList:function () {
            axios.get(baseUrl + "/api/attr/list?categoryId="+this.categoryId).then(function (value) {
                app.attrList = value.data;
            })
        },
        //左侧分类树的点击函数
        handleNodeClick(data) {
            console.log(data)
            // 当点击商品分类的时候，获取到【商品分类】id，赋值给categoryId
            app.categoryId = data.id;
            app.loadAttrList();
            app.loadCategorys();
        },
        // 加载商品分类
        loadCategroyTree:function () {
            axios.get(baseUrl+"/api/category/list").then(function (value) {
                app.categoryList = value.data;
                app.categoryTree = app.convert(app.categoryList);
            })
        },
        //转换函数
        convert(rows){
            function exists(rows, parentId){
                for(var i=0; i<rows.length; i++){
                    if (rows[i].id == parentId) return true;
                }
                return false;
            }

            var nodes = [];
            // get the top level nodes
            for(var i=0; i<rows.length; i++){
                var row = rows[i];
                if (!exists(rows, row.parentId)){
                    nodes.push({
                        id:row.id,
                        text:row.name
                    });
                }
            }

            var toDo = [];
            for(var i=0; i<nodes.length; i++){
                toDo.push(nodes[i]);
            }
            while(toDo.length){
                var node = toDo.shift();   // the parent node
                // get the children nodes
                for(var i=0; i<rows.length; i++){
                    var row = rows[i];
                    if (row.parentId == node.id){
                        var child = {id:row.id,text:row.name};
                        if (node.children){
                            node.children.push(child);
                        } else {
                            node.children = [child];
                        }
                        toDo.push(child);
                    }
                }
            }
            return nodes;
        },
        // 关闭dialog之前执行
        handleClose:function (done) {
            app.$confirm("确认关闭吗?","提示")
                .then(function() { // 点击确定执行
                    done(); // 关闭dialog
                })
        },
        //格式化列
        formatter:function (row, column, cellValue, index) {
            if (column.property == "attrItems") {
                // 1、定义一个 json结果字符串
                let resultJson = "";

                // 2、判断cellValue的值是否为空
                if (cellValue.length > 0) {

                    // 3、使用jquery  将获取到的cellValue(此时值是字符串类型)值转为json类型
                    var jsonObj = $.parseJSON(cellValue);

                    // 4、循环jsonObj
                    for (let i = 0; i < jsonObj.length; i++) {
                        // 5、循环将值赋值给resultJson
                        resultJson += jsonObj[i].text + ",";
                    }
                }
                // 6、将resultJson返回
                return resultJson;
            }
        },
    }
});