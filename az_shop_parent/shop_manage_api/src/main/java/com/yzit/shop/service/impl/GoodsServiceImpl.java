package com.yzit.shop.service.impl;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.yzit.shop.dao.SKUDao;
import com.yzit.shop.entity.Category;
import com.yzit.shop.entity.SKU;
import com.yzit.shop.service.CategoryService;
import com.yzit.shop.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import com.yzit.shop.entity.Goods;
import com.yzit.shop.dao.GoodsDao;

/**
 * 
 * <br>
 * <b>功能：</b>Goods--服务实现类<br>
 * <b>作者：</b>Administrator<br>
 * <b>日期：</b> 2020-05-08 <br>
 * <b>版权所有： 2020，云优众<br>
 */ 
@Service("goodsService")
@Transactional
public class GoodsServiceImpl   implements GoodsService {
	//private final static Logger log= Logger.getLogger(GoodsService.class);
	@Autowired
	private GoodsDao  goodsDao;

	@Autowired
	private SKUDao skuDao;

	@Autowired
	private CategoryService categoryService;

	/**
	 * 保持对象
	 * 
	 * @param goods
	 */
	public int save(Goods  goods){

		// 将前台传递的goods里的 paraItemsList 转为 json字符串 存入到数据库
		goods.setParaItems(JSON.toJSONString(goods.getParaItemsList()));
		// 将前台传递的goods里的 attrItemsList 转为 json字符串 存入到数据库
		goods.setAttrItems(JSON.toJSONString(goods.getAttrItemsList()));
		// 将前台传递的goods里的 specItemsList 转为 json字符串 存入到数据库
		goods.setSpecItems(JSON.toJSONString(goods.getSpecItemsList()));
		// 将前台传递的goods里的 imageList 转为json字符串 存入到数据库
		goods.setItemImages(JSON.toJSONString(goods.getImageList()));


		int count = goodsDao.save(goods);
		// 循环goodsSkuList
		for (SKU sku : goods.getSkuList()){
			sku.setGoodsId(goods.getId());
			sku.setName(goods.getGoodsName());
			// 将sku的规格list转为json字符串
			sku.setSpecs(JSON.toJSONString(sku.getSpecList()));
		}
		// 调用skuDao进行批量添加 *****
		skuDao.insertBatch(goods.getSkuList());
		return count;
	}

	/**
	 * 根据id删除对象
	 *
	 * @param id
	 */
	public int del(Long id){
		return goodsDao.delById(id);
	}

	/**
	 * 修改对象
	 *
	 * @param goods
	 */
	 
	public int update(Goods  goods){
		return goodsDao.update(goods);
	}

	/**
	 * 检索所有的对象
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Goods> findAll(){
		return goodsDao.findAll();
	}

	/**
	 * 根据条件检索对象
	 * 
	 * @param goods
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Goods> findByList(Goods  goods){
		return goodsDao.findByList(goods);
	}

	/**
	 * 根据id检索对象
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public Goods  findById(Long id){
        // 根据id获取一个商品
		Goods goods = goodsDao.findById(id);

		// 实例化一个SKU
        SKU sku = new SKU();
        if(goods != null){
            // 设置sku的商品id
            sku.setGoodsId(goods.getId());
            // 调用sku的dao 根据商品的id查询sku
            List<SKU> skuList = skuDao.findByList(sku);
            // 然后将skuList 赋值给goods的SkuList
            goods.setSkuList(skuList);
            // 然后根据商品的默认sku的id，查询sku，DefaultItemId就是默认的sku的id
            if(goods.getDefaultItemId() != null){ // 如果默认的sku的id不是空值，就赋值
                SKU defaultSKU = skuDao.findById(goods.getDefaultItemId().longValue());
                goods.setDefaultSKU(defaultSKU);
            }else{//如果是空值的话，那么列表中的第一个sku为商品默认sku信息
               // 判断skuList集合是否为空
                if(!skuList.isEmpty() && skuList !=null){
                    goods.setDefaultSKU(skuList.get(0));
                }
            }
        }
        return goods;
	}

	/**
	 *  根据商品id 查询到商品的分类
	 * @param id
	 * @return
	 */
	@Override
	public Category findByGoodsId(Long id) {
        Goods goods = goodsDao.findById(id);
        return categoryService.findById(Long.parseLong(goods.getCategory3Id()+""));
	}

	/**
     * 批量删除信息
     * 
     * @param ids 需要删除的ID集合
     * @return 结果
     */
   public boolean batchDel(Long[] ids){
        if(ids != null && ids.length > 0){
            for(Long id : ids){
                goodsDao.delById(id);
            }
        }
        return true;
    }
}
