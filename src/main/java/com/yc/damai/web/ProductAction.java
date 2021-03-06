package com.yc.damai.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yc.damai.biz.BizException;
import com.yc.damai.biz.ProductBiz;
import com.yc.damai.dao.ProductDao;
import com.yc.damai.po.Product;
import com.yc.damai.po.Result;

@RestController
public class ProductAction {
	
	@Resource
	private ProductDao pdao;
	
	@Resource
	private ProductBiz pbiz;
	
	@Resource
	private StringRedisTemplate srt;
	
	@RequestMapping(path="product.s",params = "op=queryHot")
	public List<Product> queryHot(){
		return pdao.selectHot();
	}
	
	@RequestMapping(path="product.s",params = "op=queryProductByCid")
	public List<Product> queryProductByCid(int cid){
		return pdao.queryProductByCid(cid);
	}
	@RequestMapping(path="product.s",params = "op=queryProductByCsid")
	public List<Product> queryProductByCsid(int csid){
		return pdao.queryProductByCsid(csid);
	}
	
	@RequestMapping(path="product.s",params = "op=queryNewProduct")
	public List<Product> queryNqueryNewProductew(){
		return pdao.queryNewProduct();
	}
	
	@RequestMapping(path="product.s",params = "op=queryProductById")
	public Product queryProductById(int pid){
		String key ="product_bcount_"+pid;
		srt.opsForValue().increment(key);
		return pdao.queryProductById(pid);
	}
	
	@RequestMapping("createProduct")
	public Result create(Product p) {
		try {
			pbiz.create(p);
			return Result.success("商品添加成功!");
		} catch (BizException e) {
			e.printStackTrace();
			return Result.failure(e.getMessage());
		}
	}
	
	@RequestMapping("updateProduct")
	public Result update(Product p) {
		try {
			pbiz.update(p);
			return Result.success("商品修改成功!");
		} catch (BizException e) {
			e.printStackTrace();
			return Result.failure(e.getMessage());
		}
	}
	
	
	
}
