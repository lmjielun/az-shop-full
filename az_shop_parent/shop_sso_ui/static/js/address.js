var app = new Vue({
	el:"#app",
	data:{
			dialogVisible:false, //dialog是否显示
			pageInfo:{} ,//分页对象
			addressForm:{ }, //添加、修改地址表的表单对象
			formData:{// 查询条件
				pageNo:1,
				pageSize:10
			},// 列表查询表单对象=查询条件+分页信息
	},
	// 在页面初始化完成的时候调用
	created:function () {
		this.loadAddresss();
	},
	methods:{
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
			axios.get(baseUrl +"/api/address/"+id).then(function(reason){
				app.addressForm  =reason.data;//接受结果信息，为表单对象赋值
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
			if(app.addressForm.id > 0 ){//执行修改请求
				app.updateAddress();
			}else{//执行添加请求
				app.saveAddress();
			}
		},
		// 添加纪录数据
		saveAddress:function () {

		// 允许提交
			axios.post(baseUrl +"/api/address/address",app.addressForm).then(function(reason){
				let json  =reason.data;//接受结果信息，给uses赋值
				if(json.success){
					app.dialogVisible = false;//关闭dialog
					//清空分页信息，显示第一页
					app.loadAddresss();//刷新表格
					app.$message('信息：添加地址表成功');
				}else{
					app.$message.error('出错了，添加地址表失败');
				}
				}).catch(function (reason) {// 错误响应的处理函数 , reason: 错误信息  400 404 502 302 500..
					app.$message.error('出错了，添加地址表失败');
			});
		},
		// 修改纪录数据
		updateAddress:function () {
		// 允许提交
			axios.put(baseUrl +"/api/address/address",app.addressForm).then(function(reason){
				let json  =reason.data;//接受结果信息，给uses赋值
				if(json.success){
					app.dialogVisible = false;//关闭dialog
					//清空分页信息，显示第一页
					app.loadAddresss();//刷新表格
					app.$message('信息：修改地址表成功');
				}else{
					app.$message.error('出错了，修改地址表失败');
				}
			}).catch(function (reason) {// 错误响应的处理函数 , reason: 错误信息  400 404 502 302 500..
				app.$message.error('出错了，修改地址表失败');
			});
		},
		handleDelete:function (id) {
			this.$confirm("确认删除吗?","提示")
				.then(function() {

				axios.delete(baseUrl +"/api/address/"+id).then(function(reason){
					let json  =reason.data;//接受结果信息，给uses赋值
					if(json.success){
						app.loadAddresss();
						app.$message.success("删除成功");
					}else{
						app.$message.error('错了哦，删除地址表失败');
					}
				}).catch(function (reason) {// 错误响应的处理函数 , reason: 错误信息  400 404 502 302 500..
					app.$message.error('错了哦，删除地址表失败');
				});
			})
		},

		// 清空查询数据
		clear:function () {

		},
		loadAddresss:function () {
			//this。formDate是json对象，分装分页查询跳进
			let   urlStr = Qs.stringify(this.formData);
			axios.get(baseUrl +"/api/address/page?"+urlStr).then(function(reason){
				app.pageInfo  =reason.data;//接受结果信息，给uses赋值
			});
		},

		handleSizeChange(val) { //分页函数：页面大小变化
			app.formData.pageSize = val;
			this.loadAddresss();
		},
		handleCurrentChange(val) {// 分页函数：当前页变化
			app.formData.pageNo = val;
			this.loadAddresss();
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