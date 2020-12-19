package com.yc.damai.biz;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.damai.dao.CartDao;
import com.yc.damai.dao.OrdersDao;
import com.yc.damai.po.Orders;
import com.yc.damai.util.Utils;

@Service
public class OrdersBiz {
	
	@Resource
	private CartDao cdao;
	
	@Resource
	private OrdersDao odao;
	
	public void pay(Orders orders) throws BizException {
		Utils.checkNull(orders.getAddr(), "请填写收货地址");
		Utils.checkNull(orders.getAddr(), "请填写收货人电话");
		Utils.checkNull(orders.getAddr(), "请填写收货人姓名");
		
		Double total =cdao.selectTotalByUid(orders.getUid());
		orders.setTotal(total);
		orders.setState(0);
		
		//订单主表
		odao.insertOrders(orders);
		//订单明细
		odao.insertItems(orders);
		//清空购物车
		cdao.deleteByUid(orders.getUid());
		
	}
	
}
