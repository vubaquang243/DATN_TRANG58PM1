package com.seatech.framework.datamanager;
import com.seatech.tp.user.UserHistoryDAO;
import com.seatech.tp.user.UserHistoryVO;

import java.sql.Connection;
import java.sql.SQLException;

public class BaseDelegate {
    public Connection conn;
    protected void close(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn = null;
        }
    }

    public void insertHistoryUser(UserHistoryVO userHisVO) throws Exception{
        try {
            UserHistoryDAO userHisDAO = new UserHistoryDAO(conn);
            userHisDAO.insert(userHisVO);
            conn.commit();
        } catch (Exception ex) {
            conn.rollback();
            ex.printStackTrace();
        }
    }
}
