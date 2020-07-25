package com.yzit.shop.api;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yzit.framework.web.ui.AjaxResult;
import com.yzit.framework.web.ui.FileVo;
import com.yzit.framework.web.ui.ValueNodes;
import com.yzit.shop.entity.Category;
import com.yzit.shop.entity.Goods;
import com.yzit.shop.entity.SKU;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/static/")
@CrossOrigin // 支持跨域
public class StaticController {

    // 注入freemarker自带的configuration类
    @Autowired
    private FreeMarkerConfig freeMarkerConfig;

    // 注入restTemplate
    @Autowired
    private RestTemplate restTemplate;

    // 定义生成静态页面的路径地址
    // 然后配置SwitchHosts添加一个地址，127.0.0.1 item.shop.com
    // 然后在去nginx的conif文件里，配置一个静态地址
    private String ITEM_PATH = "D:\\temp\\staticPage\\item_html\\";

    /**
     *  静态化首页
     * @return
     * @throws Exception
     */
    @GetMapping("/index")
    public String staticTest() throws Exception {

        // 1、调用freeMarkerConfig 获取freemarker的配置类
        Configuration configuration = freeMarkerConfig.getConfiguration();

        // 2、定义存储数据用的map容器
        Map<String,Object> dataModel = new HashMap<String, Object>();

        // 3、从Configuration中获取freemarker模板对象
        //    参数必须带后缀.html,不能只是test，
        Template template = configuration.getTemplate("index.html");

        // 4、准备模板数据：因为index页面不需要传递参数，直接访问，所以不需要准备数据

        // 5、创建一个路径,用来存放生成的静态文件
        String filePath = ITEM_PATH+"index.html";

        // 6、执行，生成文件，将静态页面，将页面放入指定的文件中
        template.process(dataModel,new FileWriter(new File(filePath)));

        return "操作成功";
    }


    @GetMapping("goods/{id}")
    public AjaxResult staticGoodsDetail(@PathVariable Integer id){

        try{
            // 1、调用freeMarkerConfig 获取freemarker的配置类
            Configuration configuration = freeMarkerConfig.getConfiguration();

            // 2、定义存储数据用的map容器
            Map<String,Object> dataModel = new HashMap<String, Object>();

            // 3、从Configuration中获取freemarker模板对象
            //    参数必须带后缀.html,不能只是test，
            Template template = configuration.getTemplate("goods_detail.html");

            // 4、准备模板需要的数据
            /****************************************************************************/
            /***************************************************************************/

            // 定义基础路径
            String BaseUrl = "http://localhost:90";

            /**
             *  一、首先根据id获取某个商品信息
             */
            // 1、定义url，发送请求
            String goodsUrl = BaseUrl + "api/goods/"+id;
            // 2、调用restTemplate 发送请求，获取goods商品对象
            Goods goods = restTemplate.getForObject(goodsUrl,Goods.class);
            // 3、使用fastJson将goods对象里的itemImages转为List集合
            List<FileVo> imageList = JSONArray.parseArray((String) goods.getItemImages(),FileVo.class);
            // 4、将imageList集合赋值给goods对象的imageList集合
            goods.setImageList(imageList);
            // 5、将商品对象添加到model里
            dataModel.put("goods",goods);

            /**
             *  二、加载商品分类，根据商品的各个分类进行查询
             */
            // 1、获取一到三级分类的id
            Integer category1Id = goods.getCategory1Id();
            Integer category2Id = goods.getCategory2Id();
            Integer category3Id = goods.getCategory3Id();
            // 2、每一级商品分类都要调用restTemplate进行一次查询数据，获取数
            String category_url = BaseUrl + "/api/category/"+category1Id;
            Category category1 = restTemplate.getForObject(category_url,Category.class);

            category_url = BaseUrl + "/api/category/"+category2Id;
            Category category2 = restTemplate.getForObject(category_url,Category.class);

            category_url = BaseUrl + "/api/category/"+category3Id;
            Category category3 = restTemplate.getForObject(category_url,Category.class);

            // 3、然后将查询到的商品分类放入到model作用域里
            dataModel.put("category1",category1);
            dataModel.put("category2",category2);
            dataModel.put("category3",category3);

            /**
             *  三、加载sku列表
             */
            // 1、根据商品获取到商品对象的sku集合，将sku集合转为string，到前台解析为正规格式的json
            String skuListJson = JSON.toJSONString(goods.getSkuList());
            // 2、存入作用域
            dataModel.put("skuList",skuListJson);

            /**
             *  四、加载商品默认sku信息
             */
            // 1、获取商品对象里的默认sku 赋值给sku对象
            SKU defaultSku = goods.getDefaultSKU();
            // 2、将defaultSku里的specs转为list集合
            List<ValueNodes> valueNodes = JSONObject.parseArray(defaultSku.getSpecs(), ValueNodes.class);
            // 3、将转为的集合赋值给specList
            defaultSku.setSpecList(valueNodes);

            // 4、将获取到的sku对象转为string
            String skuJson  = JSON.toJSONString(defaultSku);

            // 5、存入model
            dataModel.put("sku",skuJson);

            /***************************************************************************/
            /***************************************************************************/


            // 5、创建一个路径,用来存放生成的静态文件  注意的是：这里生成文件后是goods_detail数字.html
            //      因为每个商品都会有一个detail页面的
            String filePath = ITEM_PATH+id+".html";

            // 6、执行，生成文件，将静态页面，将页面放入指定的文件中
            template.process(dataModel,new FileWriter(new File(filePath)));


        }catch (Exception e){
            return AjaxResult.ERROR(e.getMessage());
        }

        return  AjaxResult.OK();

   }



}
