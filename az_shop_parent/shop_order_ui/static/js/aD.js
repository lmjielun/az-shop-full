var app = new Vue({
	el:"#app",
	data:{
			dialogVisible:false, //dialog是否显示
			pageInfo:{} ,//分页对象
			aDForm:{ }, //添加、修改AD的表单对象
			formData:{// 查询条件
				pageNo:1,
				pageSize:10
			},// 列表查询表单对象=查询条件+分页信息

		//广告分类
        categoryList:[],
    },
	// 在页面初始化完成的时候调用
	created:function () {
		this.loadADs();
        this.loadCategoryList();
	},
	methods:{

		// 查询广告分类
        loadCategoryList:function () {
            axios.get(baseUrl +"/api/adCategory/list").then(function(reason){
                app.categoryList  =reason.data;//接受结果信息，给uses赋值
            });
        },
		
        //图片上传成功后，为imageUrl属性赋值
        handleAvatarSuccess(res, file) {
            app.aDForm.pic = file.response;
            console.log(this.image);
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
		// 关闭dialog之前执行
		handleClose:function (done) {
			app.$confirm("确认关闭吗?","提示")
				.then(function() { // 点击确定执行
					done(); // 关闭dialog
			})
		},
		// 修改
		handleEdit:function (id) {
			app.dialogVisible = true;//打开弹出窗口
			axios.get(baseUrl +"/api/aD/"+id).then(function(reason){
				app.aDForm  =reason.data;//接受结果信息，为表单对象赋值
			}).catch(function (reason) {// 错误响应的处理函数 , reason: 错误信息  400 404 502 302 500..
				app.$message.error('出错了，加载参数失败');
			});
		},//加载参数数据

		// 获取选中的行
		handleSelectionChange:function (val) {
			app.ids = [];//每次发生变化，初始化ids数组
			//遍历新选中的记录，添加到ids数组中
			for(var i =0 ;i<val.length ; i++){
				app.ids.push( val[i].id );
			}
		},
		save:function () {
			//属性的主键id如果有值，就执行修改方法，否则就执行添加方法
			if(app.aDForm.id > 0 ){//执行修改请求
				app.updateAD();
			}else{//执行添加请求
				app.saveAD();
			}
		},
		// 添加纪录数据
		saveAD:function () {

		// 允许提交
			axios.post(baseUrl +"/api/aD/aD",app.aDForm).then(function(reason){
				let json  =reason.data;//接受结果信息，给uses赋值
				if(json.success){
					app.dialogVisible = false;//关闭dialog
					//清空分页信息，显示第一页
					app.loadADs();//刷新表格
					app.$message('信息：添加AD成功');
				}else{
					app.$message.error('出错了，添加AD失败');
				}
				}).catch(function (reason) {// 错误响应的处理函数 , reason: 错误信息  400 404 502 302 500..
					app.$message.error('出错了，添加AD失败');
			});
		},
		// 修改纪录数据
		updateAD:function () {
		// 允许提交
			axios.put(baseUrl +"/api/aD/aD",app.aDForm).then(function(reason){
				let json  =reason.data;//接受结果信息，给uses赋值
				if(json.success){
					app.dialogVisible = false;//关闭dialog
					//清空分页信息，显示第一页
					app.loadADs();//刷新表格
					app.$message('信息：修改AD成功');
				}else{
					app.$message.error('出错了，修改AD失败');
				}
			}).catch(function (reason) {// 错误响应的处理函数 , reason: 错误信息  400 404 502 302 500..
				app.$message.error('出错了，修改AD失败');
			});
		},
		handleDelete:function (id) {
			this.$confirm("确认删除吗?","提示")
				.then(function() {

				axios.delete(baseUrl +"/api/aD/"+id).then(function(reason){
					let json  =reason.data;//接受结果信息，给uses赋值
					if(json.success){
						app.loadADs();
						app.$message.success("删除成功");
					}else{
						app.$message.error('错了哦，删除AD失败');
					}
				}).catch(function (reason) {// 错误响应的处理函数 , reason: 错误信息  400 404 502 302 500..
					app.$message.error('错了哦，删除AD失败');
				});
			})
		},

		// 清空查询数据
		clear:function () {

		},
		loadADs:function () {
			//this。formDate是json对象，分装分页查询跳进
			let   urlStr = Qs.stringify(this.formData);
			axios.get(baseUrl +"/api/aD/page?"+urlStr).then(function(reason){
				app.pageInfo  =reason.data;//接受结果信息，给uses赋值
			});
		},

		handleSizeChange(val) { //分页函数：页面大小变化
			app.formData.pageSize = val;
			this.loadADs();
		},
		handleCurrentChange(val) {// 分页函数：当前页变化
			app.formData.pageNo = val;
			this.loadADs();
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