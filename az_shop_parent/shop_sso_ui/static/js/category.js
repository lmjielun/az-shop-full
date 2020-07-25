var app = new Vue({
    el:"#app",
    data:{
        dialogVisible:false,
        // 不需要分页了，所以不用PageInfo，用数组接受参数
        categoryList:[],
        // 传递父类id用，外边一定要定义的有
        parentId:0,
        // 定义面包屑数组集合
        breadcrumbList:[],
        // 添加步骤4 定义提交表单
        categoryForm:{
            // 默认id 和 parentId是 0
            id:0,
            parentId:0,
            isMenu:0,
            isShow:0,
        },
    },
    created:function () {
        this.loadCategoryList();
    },
    methods:{

        // 删除
        del:function (row) {
            this.$confirm("确认删除吗","警告！").then(function () {
                axios.delete(baseUrl +"/api/category/"+row.id).then(function (value) {
                    if(value.data.success){
                        app.loadCategoryList();
                        app.$message('信息：删除成功');
                    }else{
                        app.$message.error(value.data.message);
                    }
                }).catch(function (res) {
                    app.$message.error(res.message);
                })
            })
        },

        // 判断是去 保存 还是  去修改
        save:function () {
            // 因为添加以后，将categoryForm刷新了，所以要重新给categoryForm的parentId赋值，
            // 不然你每次添加 parentId 都是顶级
            app.categoryForm.parentId = app.parentId;

            // 添加的时候判断商品分类的主键id是否有值，有值是修改，没有值是添加
            if(app.categoryForm.id > 0 ){
                app.updateCategory();
            }else{
                app.saveCategory();
            }
        },

        // 根据id查询一条记录 后去修改
        handleEdit:function (row) {
            app.dialogVisible = true;
            axios.get(baseUrl +"/api/category/"+row.id).then(function (value) {
                app.categoryForm = value.data;
            })
        },

        // 执行修改方法
        updateCategory:function () {
            axios.put(baseUrl +"/api/category/category",app.categoryForm).then(function (value) {
                if(value.data.success){
                    app.dialogVisible=false;
                    // 重新加载页面
                    app.loadCategoryList();
                    app.$message('信息：修改商品分类成功');
                }else{
                    app.$message.error('出错了，修改商品分类失败');
                }
            }).catch(function (res) {
                app.$message.error('出错了，修改商品分类失败');
            })
        },

        // 添加步骤6：实现分类的添加
        saveCategory:function () {
            axios.post(baseUrl +"/api/category/category",app.categoryForm).then(function (value) {
                if(value.data.success){
                    // 关闭dialog
                    app.dialogVisible = false;
                    // 重新加载页面
                    app.loadCategoryList();
                    // 将categoryForm提交的表单清空
                    app.categoryForm={};
                    app.$message('信息：添加商品分类成功');
                }else{
                    app.$message.error('出错了，添加商品分类失败');
                }
            }).catch(function (res) {
                app.$message.error('出错了，添加商品分类失败');
            })
        },

        // 加载分类 此时加载的是 父类id为0的
        loadCategoryList:function () {
            axios.get(baseUrl+"/api/category/list?parentId="+this.parentId).then(function (value) {
                app.categoryList = value.data;
            })
        },

        // 加载分类 此时是动态加载，父类Id是几，他就去查几
        queryChildren:function(row){
            // 给面包屑添加值
            app.breadcrumbList.push({id:row.id,name:row.name})

            // 给parentId重新赋值 为row.id
            app.parentId = row.id;
            // 赋值完成后，在调用loadCategoryList 进行查询
            app.loadCategoryList();
        },
        // 面包屑改变事件
        changeBreadcrumb:function (id,index) {
            //更改table的表格数据
            app.parentId  = id;
            app.loadCategoryList();//table加载子分类
            //更改面包屑：删除选中选项的后面所有记录
            app.breadcrumbList.splice(index+1);
        },

        // 关闭dialog之前执行
        handleClose:function (done) {
            app.$confirm("确认关闭吗?","提示")
                .then(function() { // 点击确定执行
                    done(); // 关闭dialog
                })
        },

        // 格式化列
        formatter:function (row, column, cellValue, index) {
            if(column.property == "isShow"){
                if(cellValue == 0 ) return "是";
                if(cellValue == 1) return "否";
                return "-";
            }
            if(column.property == "isMenu"){
                if(cellValue == 0 ) return "是";
                if(cellValue == 1) return "否";
                return "-";
            }
        },
    }
});