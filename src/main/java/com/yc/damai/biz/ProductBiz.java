package com.yc.damai.biz;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.damai.dao.ProductDao;
import com.yc.damai.po.Product;
import com.yc.damai.util.Utils;

@Service
public class ProductBiz {
	
	@Resource
	private ProductDao pdao;
	
	public void create(Product p) throws BizException {
		Utils.checkNull(p.getPname(), "商品名称不能为空");
		pdao.insert(p);
	}
	
	public void update(Product p) throws BizException {
		Utils.checkNull(p.getPname(), "商品名称不能为空");
		Utils.checkNull(p.getShopPrice(), "商品价格不能为空");
		Utils.checkNull(p.getPdesc(), "商品详情不能为空");
		Utils.checkNull(p.getImage(), "商品图片不能为空");
		pdao.update(p);
	}
	
}
