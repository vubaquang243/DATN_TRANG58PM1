package com.seatech.tp;


import com.seatech.framework.AppError;
import com.seatech.framework.AppKeys;
import com.seatech.framework.common.AppNotificationListener;
import com.seatech.framework.common.AppServletContextListener;
import com.seatech.framework.utils.TPCPUtils;
import com.seatech.tp.thamso.ThamSoHThongDAO;

import java.sql.Connection;

import java.util.Collection;
import java.util.HashMap;

import javax.management.timer.Timer;

import javax.servlet.ServletContextEvent;

import javax.sql.DataSource;


public class TPCPServletContextListener extends AppServletContextListener {
    public void contextInitialized(ServletContextEvent event) {
        super.contextInitialized(event);
    }

    protected void notifyTask(Timer timer) {
        AppNotificationListener lsnr;
        lsnr = new AppNotificationListener();
        timer.addNotificationListener(lsnr, null, null);
    }

    protected HashMap getAppError() throws Exception {
        DataSource ds =
            (DataSource)sc.getAttribute(AppKeys.DATASOURCE_APPLICATION_KEY);
        Connection conn = ds.getConnection();
        //**********************
        //        String[] metrics =
        //            new String[OracleConnection.END_TO_END_STATE_INDEX_MAX];
        //        metrics[OracleConnection.END_TO_END_ACTION_INDEX] = "ManhNVACtion";
        //        metrics[OracleConnection.END_TO_END_CLIENTID_INDEX] = "MANHNV_CLIENT";
        //        metrics[OracleConnection.END_TO_END_ECID_INDEX] = "MANHNV_ECID";
        //        metrics[OracleConnection.END_TO_END_MODULE_INDEX] = "MANHNV_MODULE";
        //
        //        ((OracleConnection)conn).setEndToEndMetrics(metrics, (short)0);
        //
        //        CallableStatement cstmt =
        //            conn.prepareCall("{ call TTSP_OWNER.GRANT_ACCESS() }");
        //        cstmt.execute();
        //

        //**********************
        //Set quyen cho user cua ung dung
        TPCPUtils tpcpUtils = new TPCPUtils(conn);
        tpcpUtils.grantAccess();

        AppError appError = new AppError();
        HashMap errorHashMap = appError.getAppErrorList(conn);

        conn.close();

        return errorHashMap;
    }

    protected Collection getAppParam() throws Exception {
        DataSource ds =
            (DataSource)sc.getAttribute(AppKeys.DATASOURCE_APPLICATION_KEY);
        Connection conn = ds.getConnection();
        //      //**********************
        //      String[] metrics =
        //          new String[OracleConnection.END_TO_END_STATE_INDEX_MAX];
        //      //      metrics[OracleConnection.END_TO_END_ACTION_INDEX] = "ManhNVACtion";
        //      metrics[OracleConnection.END_TO_END_CLIENTID_INDEX] = "MANHNV_CLIENT";
        //      metrics[OracleConnection.END_TO_END_ECID_INDEX] = "MANHNV_ECID";
        //
        //      ((OracleConnection)conn).setEndToEndMetrics(metrics, (short)0);
        //
        //      CallableStatement cstmt = conn.prepareCall("{ call TTSP_OWNER.GRANT_ACCESS() }");
        //      cstmt.execute();
        //
        //      //**********************
        //Set quyen cho user cua ung dung
        TPCPUtils tpcpUtils = new TPCPUtils(conn);
        tpcpUtils.grantAccess();

        ThamSoHThongDAO paramDAO = new ThamSoHThongDAO(conn);
        Collection appParam = paramDAO.getThamSoList(null, null);

        conn.close();

        return appParam;
    }
}
