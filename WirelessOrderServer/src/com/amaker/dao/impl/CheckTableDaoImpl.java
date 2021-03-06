package com.amaker.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.amaker.dao.CheckTableDao;
import com.amaker.entity.CheckTable;
import com.amaker.util.DBUtil;
/**
 * 
 * 查台DAO实现类
 */
public class CheckTableDaoImpl implements CheckTableDao {

	// 获得餐桌列表
	public List getTableList() {
		// 查询SQL语句
		String sql =" select id,num,flag,personNum from tableTbl";
		//String sql="select a.*,b.* from tabletbl a left join ordertbl b on a.id=b.tableId and b.isPay = 0";
		// 数据库连接工具类
		DBUtil util = new DBUtil();
		// 获得连接
		Connection conn = util.openConnection();
		try {
			// 获得预定义语句
			Statement pstmt = conn.createStatement();
			ResultSet rs = pstmt.executeQuery(sql);
			List list = new ArrayList();
			while (rs.next()) {
				int num = rs.getInt(2);
				int flag = rs.getInt(3);
				CheckTable ct = new CheckTable();
				ct.setId(rs.getInt(1));
				ct.setFlag(flag);
				ct.setNum(num);
				ct.setPersonNum(rs.getInt(4));
				list.add(ct);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		return null;
	}
}
