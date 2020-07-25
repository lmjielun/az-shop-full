var app = new Vue({
    el:"#app",
    data:{
        activeName:"first",

        // 商品属性的数组，他不需要提交到表单，所以不需要定义在goodsForm表单里，循环要用到他
        attrList:[],

        // 商品提交表单
        goodsForm:{
            // 定义参数集合
            paraItemsList:[],
            // 定义属性集合,这个属性值是需要提交表单的，所以定义在goodsForm里
            attrItemsList:[],
            // 定义规格集合，这个集合的值是要提交到表单的，所以定义在goodsForm里
            specItemsList:[],
            // sku列表
            skuList:[],

            // 商品首张图片
            image:"",
            // 商品图片列表
            imageList:[],

        },
        // 定义商品分类的集合
        categoryTree:[],
        categoryList:[],

        // 级联操作的id
        categoryId:[],

        // 品牌的数组，下拉框选择
        brandList:[],

        // 定义规格数组，不需要提交到数据库，只是用他来进行第一次for循环
        specList:[],

        // 上传图片使用
        dialogImageUrl:"",
        dialogVisible:false,


    },
    // 在页面初始化完成的时候调用
    created:function () {
        // 加载商品分类 级联操作使用
        this.loadCategoryTree();
        // 加载品牌
        this.loadBrands();
        // 加载商品规格
        this.loadSpecList();
    },
    methods:{
        /**
         * @param response   文件上传后，返回的信息
         * @param file  一个文件对象，一个对象里包含response等各种信息，想要的信息这里边都有
         * @param fileList 一个文件集合，存储的是多个文件对象
         */
        handleAvatarSuccess:function (response, file, fileList) {
            console.log(fileList)
            // 每次上传要先清空一次图片集合
            app.goodsForm.imageList=[]
            // 循环文件集合
            for(let i = 0 ; i < fileList.length;i++){
                // 如果i==0 说明是第一张图片
                if(i==0){
                    // 将第一张图片包含所有的信息 赋值给image
                    app.goodsForm.image = fileList[i].response;
                }
                // 将图片循环添加到imageList集合中  imgSrc是FileVo扩展类里的属性
                // 结果是： [{"imgSrc": "图片所在磁盘或者其他地址/文件夹路径/文件名称.jpg"},{"imageSrc":..}...]
                app.goodsForm.imageList.push({imgSrc:fileList[i].response})
            }
        },
        // 删除一张图片
        handleRemove(file, fileList) {
            app.goodsForm.imageList=[];//清空商品图片集合
            // 循环fileList 里边存储的是当前图片的集合
            for(let i = 0 ; i < fileList.length; i++){
                // 删除还要有首页图，所以不能丢，要判断该图是不是第一张
                if(i==0){
                    //获取第一张图片
                    app.goodsForm.image = fileList[i].response;
                }
                // 然后改变goodsForm.itemList的值 imgSrc是FileVo扩展类里的属性
                app.goodsForm.imageList.push({imgSrc:fileList[i].response})
            }
        },

        // 获取已经存在的图片的信息，预览图片时调用
        handlePictureCardPreview(file) {
            this.dialogImageUrl = file.url;
            this.dialogVisible = true;
        },

        // 添加sku
        addSKU:function () {
            // 存入用户选中的规格值
            let itemList = [];
            // 循环specItemsList集合
            for(let i = 0 ; i < app.goodsForm.specItemsList.length;i++){
                // 点击添加的时候，将specItemsList的text的值赋值给itemList 也就是【规格名称】 value就是【规格值】
                itemList.push({text:app.goodsForm.specItemsList[i].text,value:''})
            }
            // 点击添加的时候，将规格名称添加到skuList集合中
            app.goodsForm.skuList.push({specList:itemList,price:'',num:''})
        },

        // checkbox 改变事件
        changeSpecCheckbox:function (specName,text) {
            // 1 根据规格名称，查询specItemsList变量，是否存在，如果规格存在，在规格选项集合添加规格选项，如果不存在，那就添加规格以及规格选项
            // specItemsList:[],//选中的规格以及规格值， 格式 ：[ {text:规格名称，items:[ {text：规格值} ,{text：规格值2} ]  } ]
            let specObj  = selectObjByList( app.goodsForm.specItemsList,"text",specName);
            if(specObj == null){// 表示规格不存在
                app.goodsForm.specItemsList.push({text:specName,items:[{text:text}]}) ;
            }else{
                //根据规格选项查询，如果不存在就添加，如果存在就删除
                let  specItemIndex = selectIndexByList(specObj.items,"text",text);
                if(specItemIndex == null){ //表示规格选项不存在，那么就添加
                    specObj.items.push({text:text})
                }else{//表示规格选项存在，就删除
                    specObj.items.splice( specItemIndex,1);
                }
            }

        },

        // 加载商品规格
        loadSpecList:function () {
          axios.get(baseUrl + "/api/spec/list").then(function (value) {
              // 循环得到的value集合
              for(let i = 0 ; i < value.data.length; i++){
                  // 定义个临时数组，存放value的items转换后的数据
                  let list = [];
                  list = $.parseJSON(value.data[i].specItems);
                  // 将value的name 和 转换后的specItems添加到 specList集合中
                  app.specList.push({text:value.data[i].name,items:list})
              }

          })
        },

        // 加载品牌
        loadBrands:function () {
            axios.get(baseUrl + "/api/brand/list").then(function (value) {
                app.brandList = value.data;
            })
        },

        // 级联操作获取当前节点的数据
        handleChange(value) {
            // 获取value中的 最后选中的值的id
            // value 级联操作发生改变后的商品分类的id数组
            // [value.length-1] 是 数组的长度-1，也就是index下标
            let categoryId = value[value.length-1];
            // 每次change事件的时候，要清空参数集合
            app.goodsForm. paraItemsList=[];
            // 获取到 商品分类的id后，根据商品分类的id查询参数,得到一个集合
            axios.get(baseUrl + "/api/param/list?categoryId="+categoryId).then(function (value) {
                // 定义一个空数组
                let itemTemp  = [];
                // 循环得到的集合
                for(let i = 0 ; i < value.data.length; i ++){
                    // 将集合里的 paramItems 解析为数组
                    itemTemp = $.parseJSON(value.data[i].paramItems);
                    // 然后循环的push放入到goodsForm里的paraItemsList集合中
                    app.goodsForm. paraItemsList.push({text:value.data[i].name,items:itemTemp});
                    // 打印下看值是否正确
                    //console.log(app.goodsForm. paraItemsList)
                }
            });

            // 每次change事件的发生，要清空属性集合
            app.goodsForm.attrItemsList = [];
            app.attrList = [];
            // 获取到 商品分类的id后，根据商品分类的id查询属性，得到一个属性集合
            axios.get(baseUrl + "/api/attr/list?categoryId="+categoryId).then(function (value) {
                // 和参数一样，定义一个空数组
                let itemTemp  = [];
                for (let i = 0 ; i < value.data.length;i++){
                    // 将attrItems转为数组
                    itemTemp = $.parseJSON(value.data[i].attrItems);
                    // 将attrList 和  goodsForm里的attrItemsList进行同步添加，你加一个 我加一个，
                    app.attrList.push({text:value.data[i].name,items:itemTemp});
                    // attrItemsList 只要属性名 其他不加
                    app.goodsForm.attrItemsList.push({text:value.data[i].name});
                }
            })
        },

        // 加载商品分类 树 数据
        loadCategoryTree:function () {
            axios.get(baseUrl+"/api/category/list").then(function (value) {
                let categoryList = value.data;
                app.categoryTree = app.convert(categoryList);
            })
        },

        //转换函数 rows是集合，
        convert(rows){
            // 遍历rows集合
            function exists(rows, parentId){
                // 循环rows集合
                for(var i=0; i<rows.length; i++){
                    // 如果rows集合id  == parentId 就直接返回，不往下走，说明没有子类了
                    if (rows[i].id == parentId) return true;
                }
                return false;
            }

            // 定义一个节点
            var nodes = [];
            // get the top level nodes
            // 说明上边的循环判断不成功，说明没有子类，直接显示
            for(var i=0; i<rows.length; i++){
                // 获取rows集合中的 一个 元素
                var row = rows[i];
                // 集合的
                if (!exists(rows, row.parentId)){
                    nodes.push({
                        id:row.id,  // id 还是 id
                        value:row.id, // value 就是 id
                        label:row.name // label 就是name
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
                        // 这里边填的是子类的数，依然有value 和 label，主要是显示的作用
                        var child = {id:row.id,value:row.id,label:row.name};
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

        // 添加纪录数据
        saveGoods:function () {
            alert(app.categoryId)
            for (let i = 0 ; i < app.categoryId.length;i++){
                app.goodsForm.category1Id = app.categoryId[0];
                app.goodsForm.category2Id = app.categoryId[1];
                app.goodsForm.category3Id = app.categoryId[2];
            }
            // 允许提交
            axios.post(baseUrl +"/api/goods/goods",app.goodsForm).then(function(reason){
                let json  =reason.data;//接受结果信息，给uses赋值
                if(json.success){
                    app.$message('信息：添加Goods成功');
                }else{
                    app.$message.error('出错了，添加Goods失败');
                }
            }).catch(function (reason) {// 错误响应的处理函数 , reason: 错误信息  400 404 502 302 500..
                app.$message.error('出错了，添加Goods失败');
            });
        },

    }
});