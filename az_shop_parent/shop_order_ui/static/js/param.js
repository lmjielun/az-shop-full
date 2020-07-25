var app = new Vue({
    el:"#app",
    data:{
        // 转换后的树结构
        categoryTree:[],
        // 转换前的json结构
        categoryList:[],
        // 树结构默认绑定属性为text
        defaultProps:{
            children: 'children',
            label: 'text'
        },
        // 定义参数 的 数组
        paramList:[],
        // 定义【商品】类型的主键id
        categoryId:0,
        // 控制dialog开关
        dialogVisible:false,
        // 定义【参数】提交表单数据
        paramForm:{
            paramList:[],
        },
    },
    created:function () {
        // 加载【商品】分类数据
        this.loadCategoryTree();
        // 加载【参数】
        this.loadParams();
    },
    methods:{
        // 删除
        del:function (row) {
          axios.delete(baseUrl +"/api/param/"+row.id).then(function (value) {
              if(value.data.success){
                  app.loadParams(); // 重新加载属性页面
                  app.$message('删除成功');
              }else{
                  app.$message.error('删除失败');
              }
          })
        },
        // 执行修改
        updateParam:function () {
          axios.put(baseUrl +"/api/param/param",app.paramForm).then(function (value) {
              if(value.data.success){
                  app.dialogVisible = false;//关闭dialog
                  app.loadParams(); // 重新加载属性页面
                  app.$message('修改属性成功');
              }else{
                  app.$message.error('修改属性失败');
              }
          })
        },
        // 根据主键查询一条参数记录
        handleEdit:function (row) {
            app.dialogVisible = true;
            app.categoryId = row.categoryId
            axios.get(baseUrl + "/api/param/"+row.id).then(function (value) {
                app.paramForm = value.data;
            })
        },
        // 添加参数
        saveParam:function () {
            axios.post(baseUrl +"/api/param/param",app.paramForm).then(function (value) {
                if(value.data.success){
                    app.dialogVisible = false;//关闭dialog
                    app.loadParams(); // 重新加载页面
                    app.$message('添加属性成功');
                    app.paramForm={}
                }else{
                    app.$message.error('添加属性失败');
                }
            })
        },
        // 保存：保存前判断是添加还是修改
        save:function () {
            // 不管是修改，还是添加，都需要拿到 商品分类的id，意思：是修改哪个分类商品的属性
            // 该categoryId 在 你点击tree的时候，已经被修改过了
            app.paramForm.categoryId=app.categoryId;

            //属性的主键id如果有值，就执行修改方法，否则就执行添加方法
            if(app.paramForm.id > 0 ){//执行修改请求
                app.updateParam();
            }else{//执行添加请求
                app.saveParam();
            }
        },
        // 删除一行参数
        delItem:function (index) {
            app.paramForm.paramList.splice(index,1)
        },
        // 添加一行参数
        addItem:function () {
            app.paramForm.paramList.push({text:""})
        },
        // 加载参数
        loadParams:function () {
            axios.get(baseUrl + "/api/param/list?categoryId="+this.categoryId).then(function (value) {
                app.paramList = value.data;
            })
        },
        // tree的点击事件,data是自带的属性，可以获取到当前节点的数据
        handleNodeClick:function (data) {
            console.log(data)
            // 给【商品】分类id赋值为当前属性的Id
            app.categoryId = data.id;
            // 然后再调用loadCategoryTree进行查询
            app.loadParams();
        },
        // 加载【商品】分类数据
        loadCategoryTree:function () {
            // 什么参数都不传，直接拿到所有的数据，然后进行 经过转换函数进行转换
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
        //格式化列
        formatter:function (row, column, cellValue, index) {
            if (column.property == "paramItems") {
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
        // 关闭dialog
        handleClose:function (done) {
            app.$confirm("确认关闭吗?","提示")
                .then(function() { // 点击确定执行
                    done(); // 关闭dialog
                })
        },
    }
});