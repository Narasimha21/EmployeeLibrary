package com.imcs.project.dao;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.imcs.project.entities.Bonus;
import com.imcs.project.jdbcconnection.AbstractDAOImpl;
import com.imcs.project.jdbcconnection.JdbcConnectionFactory;

public class BonusDao extends AbstractDAOImpl implements BonusDaoInterface {
	static Logger logger = Logger.getLogger(BonusDao.class.getName());

	List<Bonus> list = new ArrayList<>();

	public BonusDao() {
		super();
	}

	@Override
	public void save(List<Bonus> list) {

		try (Connection connection = JdbcConnectionFactory.getConnection();

				PreparedStatement statement = connection.prepareStatement("INSERT INTO imcs.bonus VALUES(?,?,?)")) {
			connection.setAutoCommit(false);
			Iterator<Bonus> it = list.iterator();
			while (it.hasNext()) {
				Bonus b = it.next();
				statement.setInt(1, b.getDeptNo());
				statement.setFloat(2, b.getAmount());
				statement.setFloat(3, b.getRemainingAmount());
				statement.addBatch();
			}

			statement.executeBatch();
			logger.info("Executing batch of bonus list from file");
			connection.commit();
		} catch (BatchUpdateException b) {
			// process BatchUpdateException
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public Float get(int deptNo) {
		ResultSet rs = null;
		try (Connection connection = getConnection();

				PreparedStatement statement = connection
						.prepareStatement("select remaining_amount from imcs.bonus where deptNo=?")) {

			statement.setInt(1, deptNo);
			rs = statement.executeQuery();
			while (rs.next()) {

				return rs.getFloat("remaining_amount");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	@Override
	public List<Bonus> getBonusList() {
		ResultSet rs = null;
		List<Bonus> bonuslist = new ArrayList<>();
		try (Connection connection = getConnection();

				PreparedStatement statement = connection
						.prepareStatement("select deptNo,amount,remaining_amount from imcs.bonus ");) {

			rs = statement.executeQuery();
			while (rs.next()) {

				bonuslist.add(new Bonus(rs.getInt("deptNo"), rs.getFloat("amount"), rs.getFloat(3)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return bonuslist;

	}

	@Override
	public void updateBonus(float remainingAmount, int deptNo) {

		try (Connection connection = JdbcConnectionFactory.getConnection();

				PreparedStatement statement = connection
						.prepareStatement("UPDATE imcs.bonus set remaining_amount=? where deptNo=?")) {
			statement.setFloat(1, remainingAmount);
			statement.setInt(2, deptNo);
			statement.execute();

		} catch (BatchUpdateException b) {
			logger.error("error", b);
		} catch (SQLException e) {
			logger.warn("error", e);
			e.printStackTrace();
		}

	}

	@Override
	public void addBonus(Bonus b) {
		list.add(b);

	}

	@Override
	public void saveBonus() {
		// TODO Auto-generated method stub
		save(list);
	}

}
