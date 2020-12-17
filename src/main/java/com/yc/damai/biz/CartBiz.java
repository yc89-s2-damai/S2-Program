package com.yc.damai.biz;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.damai.dao.CartDao;
import com.yc.damai.po.Cart;

@Service
public class CartBiz {

	@Resource
	private CartDao cdao;
	
	public void addCart(int count,int uid, int pid) {
		if(cdao.addCart(count,uid, pid)==0){
			 cdao.insert(uid, pid, count);
		}
	}
}
