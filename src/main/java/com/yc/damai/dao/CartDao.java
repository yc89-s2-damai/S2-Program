package com.yc.damai.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.yc.damai.po.Cart;

@Repository
public class CartDao extends BaseDao {
	public List<?> queryCart(Integer uid) throws SQLException {
		String sql = "select product.*,cart.* from cart,product " + "where cart.pid=product.pid and cart.uid=?";
		List<?> list = null;
		// list = jt.query(sql, uid);
		System.out.println("list==" + list);
		return list;
	}

	public void addCart(String pid, String count, Integer uid) throws SQLException {
		/*
		 * String sql = "select * from cart where uid=? and pid=?"; if
		 * (DBHelper.selectOne(sql, uid,pid) != null) { sql =
		 * "update cart set count=count+? where uid=? and pid=?"; DBHelper.update(sql,
		 * count, uid, pid); } else { sql = "insert into cart values(null,?,?,?)";
		 * DBHelper.update(sql, uid, pid, count); }
		 */

		String sql = "update cart set count=count+? where uid=? and pid=?";
		if (jt.update(sql, count, uid, pid) == 0) {
			sql = "insert into cart values(null,?,?,?)";
			jt.update(sql, uid, pid, count);
		}
	}

	public void deleteCart(int pid,int uid) {
		jt.update("delete from cart where pid=? and uid=?", pid,uid);
	}

	public void clearCart(Integer iUid) throws SQLException {
		String sql = "delete from cart where uid = ?";
		jt.update(sql, iUid);
	}

	public void insert(int uid, int pid, int count) {
		/*
		 * if(jt.query("select *from cart where uid=? and pid=?", cartRowMapper)!=null)
		 * { jt.update("insert into cart values(null,?,?,?)", uid, pid, count); }
		 */
		jt.update("insert into cart values(null,?,?,?)", uid, pid, count);
	}
	public int addCart(int count,int uid, int pid ) {
		return jt.update("update cart set count=count+? where uid=? and pid=?",count, uid, pid);
	}

	public List<Map<String,Object>> selectCart(Integer uid) {
		return jt.queryForList("select * from cart a"
				+ " left join user b on a.uid=b.uid"
				+ " left join product c on a.pid=c.pid"
				+ " where a.uid=?", uid);
	}
	

	private RowMapper<Cart> cartRowMapper = new RowMapper<Cart>() {

		@Override
		public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
			Cart cart = new Cart();
			cart.setCiid(rs.getInt("ciid"));
			cart.setUid(rs.getInt("uid"));
			cart.setPid(rs.getInt("pid"));
			cart.setCount(rs.getInt("count"));
			return cart;
		}
	};


	public Double selectTotalByUid(Integer uid) {
		String sql="select sum(count * b.shop_price) from "+
				   " cart a join product b on a.pid = b.pid "+
				   " where uid=?";
		
		return jt.queryForObject(sql, Double.class,uid);
	}

	public void deleteByUid(Integer uid) {
		
		jt.update("delete from cart where uid=?",uid);
		
	}

}
