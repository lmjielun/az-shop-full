package com.yzit.shop.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yzit.framework.web.ui.AjaxResult;
import com.yzit.framework.web.ui.ValueNodes;
import com.yzit.shop.common.CookieUtils;
import com.yzit.shop.entity.Cart;
import com.yzit.shop.entity.Category;
import com.yzit.shop.entity.Preferential;
import com.yzit.shop.entity.SKU;
import com.yzit.shop.service.PreferentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 *  购物车
 */
@RestController
@CrossOrigin // 支持跨域
@RequestMapping("api/cart")
public class CartController {

    // 注入restTemplate
    @Autowired
    private RestTemplate restTemplate;

    // 注入redisTemplate  自带的不要再创建config类
    @Autowired
    private RedisTemplate redisTemplate;

    // 注入满减规则 的service
    @Autowired
    private PreferentialService pftService;

    // 作为redis中的key
    private String CartKey = "USER_MYCART";



    /**
     *  添加购物车到redis中
     * @param userId
     * @param cart
     * @return
     */
    @PostMapping("/addCart/{userId}")
    public AjaxResult addCart(@PathVariable Integer userId,@RequestBody  Cart cart){
        // 由于不能确定是否添加到redis成功，没办法测试，所以try catch起来
        try {

            // 根据传递过来的用户编号，查询redis中的用户的购物车
            List<Cart> cartList = (List<Cart>)redisTemplate.opsForHash().get(CartKey,"USER_INFO_"+userId);
            //List<Cart> cartList = (List<Cart>)redisTemplate.opsForValue().get(CartKey+"USER_INFO_"+userId);

            // 判断获取的cartList是否为Null
            if(cartList == null ){
                // 如果为null 直接初始化该购物车
                cartList = new ArrayList<Cart>();
            }

            // 创建一个标识，标识商品不存在于购物车中
            boolean ieExit = false;
            if(!cartList.isEmpty()){ // 购物车不为空
                Cart cart1 = null;
                for(int i  =0 ; i<cartList.size() ; i++){
                    System.out.println("--------"+cartList.get(i));
                }

                for (Cart cartTemp : cartList) {
                    // 判断购物车内的商品  与  新购物车内的 商品skui是否相等
                    if(cartTemp.getSkuId() == cart.getSkuId() || cartTemp.getSkuId().equals(cart.getSkuId()) ){
                        // 相等了 直接将该商品的数量相加
                        cartTemp.setNum(cartTemp.getNum() + cart.getNum());
                        // 将ieExit标识符改为true
                        ieExit = true;
                        break;
                    }
                }
            }
            // 此时再判断ieExit的值,如果还是false，说明商品不存在
            if(!ieExit){
                // 商品不存在，添加到购物车
                cart.setUserId(userId); // 添加用户的编号
                cartList.add(cart);
            }

            // 将商品添加到redis中  参数为：key值，字段，购物车信息


            redisTemplate.opsForHash().put(CartKey,"USER_INFO_"+userId,cartList);

            //redisTemplate.opsForValue().set(CartKey+"USER_INFO_"+userId,"USER_INFO_"+userId);

            redisTemplate.expire(CartKey,7, TimeUnit.DAYS);

            return AjaxResult.OK();

        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.ERROR("Redis添加购物车失败");
        }
    }

    /**
     *  购物车数量的加减
     * @param userId
     * @param cartList
     * @return
     */
    @PostMapping("/addCartList/{userId}")
    public AjaxResult addCartList(@PathVariable Integer userId , @RequestBody  List<Cart> cartList){
        try {
            // 判断cartList是否为空
            System.out.println(cartList.size());
                // 调用redis进行添加 CartKey就是key值，User_info_+userId 就是字段，cartList要添加的数据
               redisTemplate.opsForHash().put(CartKey,"USER_INFO_"+userId,cartList);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.ERROR(e.getMessage());
        }
        return AjaxResult.OK();
    }


    /**
     *  根据用户Id查询用户下的购物车
     * @param userId
     * @return
     */
    @GetMapping("/myCart/{userId}")
    public List<Cart> myCart(@PathVariable Integer userId){

        // 根据用户的Id查询redis中的数据
        List<Cart> cartList = (List<Cart>) redisTemplate.opsForHash().get(CartKey,"USER_INFO_"+userId);
        //List<Cart> cartList = (List<Cart>)redisTemplate.opsForValue().get(CartKey+"USER_INFO_"+userId);
        // 如果查询出的购物车为空，就初始化购物车，创建一个空的购物车出来
        if(cartList == null){
            cartList = new ArrayList<Cart>();
        }
        return cartList;
    }

    /**
     *  合并购物车，将cookie的购物车合并到redis的用户名下
     * @param userId
     * @param cartListCookie
     * @return
     */
    @PostMapping("/mergeCart/{userId}")
    public AjaxResult mergeCart(@PathVariable Integer userId ,@RequestBody  List<Cart> cartListCookie){
        try {
            // 调用redisTemplate,根据token，获取redis购物车
            List<Cart> redisCartList  =(List<Cart>) redisTemplate.opsForHash().get(CartKey, "USER_INFO_" + userId);
            // 判断从redis获取到用户的购物车是Null值，就没有添加功能了，所以初始化一个购物车
            if(redisCartList == null){
                redisCartList = new ArrayList<Cart>();
            }
            // 判断传递过来的cookieList购物车集合是否为空
            boolean isExit = false;
            if(cartListCookie != null && !cartListCookie.isEmpty()){
                // 第一层For循环：遍历传递过来的，也就是cookie中的购物车集合
                for(Cart cookieCart : cartListCookie ){
                    // 每次For循环cookie的集合前，isExit都得是false
                    // 因为第一个商品存在，修改为ture,那么第二个商品是否存在，所以还得在判断一次
                    isExit = false;
                    // 判断从redis获取到的购物车是否为null
                    if(redisCartList!=null && !redisCartList.isEmpty()){
                        //第二层for循环：遍历用户的redisCartList集合,也就是从redis中获取到的集合
                        for(Cart redisCart : redisCartList){
                            // 判断cookieCart 和  redisCart 的sku是否相同
                            if(cookieCart.getSkuId()== redisCart.getSkuId() || cookieCart.getSkuId().equals(redisCart.getSkuId())){
                               // 如果相等，redis的购物车的该件商品数量增加
                                redisCart.setNum(redisCart.getNum() + cookieCart.getNum());
                                isExit = true;
                            }
                        }
                    }
                    // 一定在一层for循环内部
                    if(!isExit){ // 表示商品不存在
                        // 不存在，redist的购物车吞并他
                        redisCartList.add(cookieCart);
                    }
                }
            }
            // 将新购物车保存到redis中
            redisTemplate.opsForHash().put(CartKey,"USER_INFO_" + userId,redisCartList);

        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.ERROR(e.getMessage());
        }
        return AjaxResult.OK();
    }

    /**
     * 计算优惠金额
     * @param cartList
     * @return
     */
    @PostMapping("/discount")
    public Integer discount(@RequestBody List<Cart> cartList){
        Integer preMoney = 0 ; // 优惠金额
        /***************************根据商品的分类  进行分组*********************************/
        // Map<商品分类,消费金额>
        SKU sku = null;
        Category category = null;
        Integer money = null;

        Map<Integer,Integer> consumptionMap = new HashMap<Integer,Integer>();
        // 判断传递的集合是否为空
        if(cartList != null && !cartList.isEmpty()){
            // 遍历传递过来到购物车
            for(Cart cart : cartList){
                //根据skuId 获取 价格
                sku = restTemplate.getForObject("http://api.shop.com/api/sku/"+cart.getSkuId(), SKU.class);
                // 获取价格,从数据库中获取
                BigDecimal price = BigDecimal.valueOf(sku.getPrice());
                cart.setPrice(price);

                // 根据goodsId 获取商品分类
                System.out.println("http://api.shop.com/api/goods/goodsId/"+cart.getGoodsId());
                category = restTemplate.getForObject("http://api.shop.com/api/goods/goodsId/"+cart.getGoodsId(), Category.class);
                cart.setCategoryId(category.getId().intValue());
                System.out.println("赋值后的cart的商品分类id：cart.getCategoryId() == "+cart.getCategoryId());

                // 根据商品分类(也就是map的key)，获得消费金额（也就是value)，如果有值，消费金额累加，如果没有值，consumptionMap添加新记录
                money = consumptionMap.get(cart.getCategoryId());
                System.out.println("根据key："+cart.getCategoryId()+"：获取到的值"+money);

                if(money != null){ // 不是0
                    //  消费金额累加
                    consumptionMap.put(cart.getCategoryId(),money + (cart.getNum() * cart.getPrice().intValue()));
                }else{
                    // consumptionMap添加新记录：将商品分类以及消费金额 存储到 consumptionMap
                    System.out.println("要添加的key值"+cart.getCategoryId()+"；要添加的value:"+(cart.getNum() * cart.getPrice().intValue()));
                    consumptionMap.put(cart.getCategoryId(),(cart.getNum() * cart.getPrice().intValue()));
                }

            }
        }

        /************************根据商品分类，查询优惠金额*********************************/

        Integer multiple =0;  // 翻倍金额
        Preferential preferential; // 优惠规则对象
        System.out.println("map的长度===" + consumptionMap.size());
        // 遍历consumptionMap
        for(Map.Entry<Integer,Integer> entry : consumptionMap.entrySet()){
            // 根据商品分类 得到 满减规则  对象
            preferential = pftService.findByCategoryId(entry.getKey().intValue());

            System.out.println("*********"+(entry.getValue() > preferential.getBuyMoney()));
            System.out.println("消费金额"+entry.getValue());
            System.out.println("要超过的金额"+preferential.getBuyMoney());
            // 判断分类下消费金额是否 >  满减规则定义的消费金额
            if(entry.getValue() > preferential.getBuyMoney()){
                System.out.println("****计算金额*****");
                multiple = preferential.getPreMoney();
                // 判断是否翻倍
                if(preferential.getType() == 2){
                    multiple = preferential.getPreMoney() * (entry.getValue() / preferential.getBuyMoney());
                }
            }

            preMoney = preMoney + multiple; // 优惠金额累加
        }
        System.out.println("******优惠金额******"+ preMoney);

        return  preMoney;
    }



}
