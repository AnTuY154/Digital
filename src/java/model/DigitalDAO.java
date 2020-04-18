/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Digital;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Darkness_King
 */
public class DigitalDAO {

    public Digital getTop1() throws Exception {
        DBContext db = new DBContext();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "select top 1 * from digital\n"
                    + "order by timePost desc";
            conn = db.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Digital d = new Digital(rs.getInt("ID"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getString("author"),
                        rs.getDate("timePost"),
                        rs.getString("shortDes"));
                return d;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            db.closeConnection(rs, ps, conn);
        }
        return null;
    }

    public Digital getOne(int id) throws Exception {
        DBContext db = new DBContext();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "select * from digital where id = ?";
            conn = db.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Digital d = new Digital(rs.getInt("ID"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getString("author"),
                        rs.getDate("timePost"),
                        rs.getString("shortDes"));
                return d;
            }
        } catch (Exception e) {
             throw e;
        } finally {
            db.closeConnection(rs, ps, conn);
        }
        return null;
    }

    public List<Digital> getTop5() throws Exception {
        DBContext db = new DBContext();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            List<Digital> list = new ArrayList<>();
            String sql = "select top 5 * from digital\n"
                    + "where timePost not in(\n"
                    + "select max(timepost) from digital\n"
                    + ")\n"
                    + "order by timePost desc";
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Digital d = new Digital(rs.getInt("ID"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getString("author"),
                        rs.getDate("timePost"),
                        rs.getString("shortDes"));
                list.add(d);
            }
            return list;
        } catch (Exception e) {
              throw e;
        } finally {
            db.closeConnection(rs, ps, conn);
        }

    }

    public List<Digital> search(String txt, int rowFrom, int rowTo) throws Exception {
        DBContext db = new DBContext();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            List<Digital> list = new ArrayList<>();
            String sql = "select * from (\n"
                    + "                    SELECT *, ROW_NUMBER() OVER (ORDER BY timePost DESC) as row_num from digital where title like ? )abc\n"
                    + "                        WHERE row_num >= ? AND row_num<= ?";
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + txt + "%");
            ps.setInt(2, rowFrom);
            ps.setInt(3, rowTo);
            rs = ps.executeQuery();
            while (rs.next()) {
                Digital d = new Digital(rs.getInt("ID"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getString("author"),
                        rs.getDate("timePost"),
                        rs.getString("shortDes"));
                list.add(d);
            }
            return list;
        } catch (Exception e) {
             throw e;
        } finally {
            db.closeConnection(rs, ps, conn);
        }
    }

    public int count(String txt) throws Exception {
        DBContext db = new DBContext();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select count(*) from digital \n"
                    + "where title like ?";
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + txt + "%");
            rs =ps .executeQuery();;

            int count = 0;
            while (rs.next()) {
                count = rs.getInt(1);
            }
            return count;
        } catch (Exception e) {
              throw e;
        }finally{
            db.closeConnection(rs, ps, conn);
        }
    }

}
