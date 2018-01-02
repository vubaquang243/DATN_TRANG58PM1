package com.seatech.tp.ttlaigoc.action;

import com.seatech.framework.datamanager.BaseDelegate;
import com.seatech.tp.ttlaigoc.dao.TTLaiGocDao;

import java.sql.Connection;

import java.util.Collection;
import java.util.Map;

public class TTGocTinPhieuDelegate extends BaseDelegate {
    public TTGocTinPhieuDelegate(Connection conn) {
        super();
        this.conn = conn;
    }

    public Collection getListTTGocTinPhieu(Map<String, Object> map, Integer page,
                                        Integer count,
                                        Integer[] totalCount) throws Exception {
        Collection reval = null;

        try {
            TTLaiGocDao dao = new TTLaiGocDao(conn);
            reval = dao.getListGocTinPhieu(map, page, count, totalCount);
            conn.commit();
        } catch (Exception ex) {
            conn.rollback();
            throw ex;
        }
        return reval;
    }
}
