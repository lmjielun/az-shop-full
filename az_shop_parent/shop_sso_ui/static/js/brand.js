var app = new Vue({
    el:"#app",
    data:{
        ids:[],//table表格选中的记录id集合
        dialogVisible:false, //dialog是否显示
        pageInfo:{} ,//分页对象
        brandForm:{  //添加、修改品牌表的表单对象
            id:'' ,
            name:'' ,
            letter:'' ,
            image:'' ,
            seq:''
        },
        formData:{// 查询条件
            pageNo:1,
            pageSize:10,
            name:"",
            letter:"",
        },// 列表查询表单对象=查询条件+分页信息
        rules:{
            name:[
                { required:true, message: '请输入品牌名称', trigger: 'blur' },
            ]
        }

    },
    // 在页面初始化完成的时候调用
    created:function () {
        this.loadBrands();
    },
    methods:{

        //图片上传成功后，为imageUrl属性赋值
        handleAvatarSuccess(res, file) {
            this.brandForm.image = file.response;
            console.log(this.brandForm.image);
        },
        //图片上传校验格式以及大小
        beforeAvatarUpload(file) {
            const isJPG = file.type === 'image/jpeg';
            const isLt2M = file.size / 1024 / 1024 < 2;

            if (!isJPG) {
                this.$message.error('上传头像图片只能是 JPG 格式!');
            }
            if (!isLt2M) {
                this.$message.error('上传头像图片大小不能超过 2MB!');
            }
            return isJPG && isLt2M;
        },

        // 关闭dialog之前执行
        handleClose:function (done) {
            app.$confirm("确认关闭吗?","提示")
                .then(function() { // 点击确定执行
                    done(); // 关闭dialog
                })
        },
        // 修改
        handleEdit:function (row) {
            app.brandForm = row;
            app.dialogVisible = true;
        },

        // 获取选中的行
        handleSelectionChange:function (val) {
            app.ids = [];//每次发生变化，初始化ids数组
            //遍历新选中的记录，添加到ids数组中
            for(var i =0 ;i<val.length ; i++){
                app.ids.push( val[i].id );
            }
        },
        // 添加纪录数据
        saveBrand:function (formName) {
            this.$refs[formName].validate(function (valid) { // valid : 验证结果
                if(valid) {
                    // 允许提交
                    axios.post(baseUrl +"/api/brand/brand",app.brandForm).then(function(reason){
                        let json  =reason.data;//接受结果信息，给uses赋值
                        if(json.success){
                            app.dialogVisible = false;//关闭dialog
                            //清空分页信息，显示第一页
                            app.loadBrands();//刷新表格
                            app.$message('信息：添加品牌表成功');
                        }else{
                            app.$message.error('出错了，添加品牌表失败');
                        }
                    }).catch(function (reason) {// 错误响应的处理函数 , reason: 错误信息  400 404 502 302 500..
                        app.$message.error('出错了，添加品牌表失败');
                    });
                }
            });

        },
        // 修改纪录数据
        updateBrand:function (formName) {
            this.$refs[formName].validate(function (valid) { // valid : 验证结果
                if(valid) {
                    // 允许提交
                    axios.put(baseUrl +"/api/brand/brand",app.brandForm).then(function(reason){
                        let json  =reason.data;//接受结果信息，给uses赋值
                        if(json.success){
                            app.dialogVisible = false;//关闭dialog
                            app.loadBrands();//刷新表格
                            app.$message('信息：修改品牌表成功');
                        }else{
                            app.$message.error('出错了，修改品牌表失败');
                        }
                    }).catch(function (reason) {// 错误响应的处理函数 , reason: 错误信息  400 404 502 302 500..
                        app.$message.error('出错了，修改品牌表失败');
                    });
                }
            });
        },
        handleDelete:function (row) {
            this.$confirm("确认删除吗?","提示")
                .then(function() {

                    axios.delete(baseUrl +"/api/brand/"+row.id).then(function(reason){
                        let json  =reason.data;//接受结果信息，给uses赋值
                        if(json.success){
                            app.loadBrands();
                            app.$message.success("删除成功");
                        }else{
                            app.$message.error('错了哦，删除品牌表失败');
                        }
                    }).catch(function (reason) {// 错误响应的处理函数 , reason: 错误信息  400 404 502 302 500..
                        app.$message.error('错了哦，删除品牌表失败');
                    });
                })
        },
        // 批量删除
        batchDel:function () {
            this.$confirm("确认删除吗?","提示")
                .then(function() {
                    axios.delete(baseUrl +"/api/brand/batch/"+app.ids).then(function(reason){
                        let json  =reason.data;//接受结果信息，给uses赋值
                        if(json.success){
                            app.loadBrands();//刷新表格
                            app.$message.success("删除成功");
                        }else{
                            app.$message.error('错了哦，删除多个品牌表失败');
                        }
                    }).catch(function (reason) {// 错误响应的处理函数 , reason: 错误信息  400 404 502 302 500..
                        app.$message.error('错了哦，删除多个品牌表失败');
                    });
                })
        },
        // 清空查询数据
        clear:function () {

        },
        loadBrands:function () {
            //this。formDate是json对象，分装分页查询跳进
            let  urlStr = Qs.stringify(this.formData);
            axios.get(baseUrl +"/api/brand/page?"+urlStr).then(function(reason){
                app.pageInfo  =reason.data;//接受结果信息，给uses赋值
            });
        },

        handleSizeChange(val) { //分页函数：页面大小变化
            app.formData.pageSize = val;
            this.loadBrands();
        },
        handleCurrentChange(val) {// 分页函数：当前页变化
            app.formData.pageNo = val;
            this.loadBrands();
        },
        formatter:function (row, column, cellValue, index) {//格式化列
            if(column.property =="sex"){
                if(cellValue ==1) return "男";
                if(cellValue == 0) return  "女";
                return "保密";
            }
            if(column.property =="status"){
                if(cellValue ==1 ) return  "停用";
                if(cellValue==0) return "正常";
                return "-";
            }
        }
    }
});