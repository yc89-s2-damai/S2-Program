package com.yc.damai.dao;

import org.springframework.stereotype.Repository;

import com.yc.damai.po.Orders;

@Repository
public class OrdersDao extends BaseDao{

	public void insertOrders(Orders orders) {
		String sql="insert into orders values(null,?,now(),?,?,?,?,?)";
		jt.update(sql,orders.getTotal(),
					  orders.getState(),
					  orders.getAddr(),
					  orders.getPhone(),
					  orders.getUid(),
					  orders.getName());	
	}

	public void insertItems(Orders orders) {

		String sql="insert into orderitem select "+
		" null, a.acount , a.acount * b.shop_price, a.pid, LAST_INSERT_ID() "+
		" from cart a join product b on a.pid = b.pid where a.uid=?";
		jt.update(sql,orders.getUid());
	}

}
