package com.yzit.shop.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yzit.framework.web.ui.FileVo;
import com.yzit.framework.web.ui.ValueNodes;
import com.yzit.shop.entity.Category;
import com.yzit.shop.entity.Goods;
import com.yzit.shop.entity.SKU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexAction {

    @Autowired
    private RestTemplate restTemplate;

    // 主页面
    @GetMapping({"/","index"})
    public String index(){

        return "index";
    }

    // 商品列表页面
    @GetMapping("/category/{id}")
    public String goods(@PathVariable Integer id , Model model){
        // 将前台传递过来的id 放入到 model里
        model.addAttribute("id",id);
        return "goods_list";
    }

    // 商品列表页面
    @GetMapping("/goods/{id}")
    public String goodsDetail(Model model,@PathVariable Integer id){
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
        model.addAttribute("goods",goods);

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
        model.addAttribute("category1",category1);
        model.addAttribute("category2",category2);
        model.addAttribute("category3",category3);

        /**
         *  三、加载sku列表
         */
        // 1、根据商品获取到商品对象的sku集合，将sku集合转为string，到前台解析为正规格式的json
        String skuListJson = JSON.toJSONString(goods.getSkuList());
        // 2、存入作用域
        model.addAttribute("skuList",skuListJson);


        /**
         *  四、加载商品默认sku信息
         */
        // 1、获取商品对象里的默认sku 赋值给sku对象
        SKU  defaultSku = goods.getDefaultSKU();
        // 2、将defaultSku里的specs转为list集合
        List<ValueNodes> valueNodes = JSONObject.parseArray(defaultSku.getSpecs(), ValueNodes.class);
        // 3、将转为的集合赋值给specList
        defaultSku.setSpecList(valueNodes);

        // 4、将获取到的sku对象转为string
        String skuJson  = JSON.toJSONString(defaultSku);

        // 5、存入model
        model.addAttribute("sku",skuJson);

        return "goods_detail";
    }




}
