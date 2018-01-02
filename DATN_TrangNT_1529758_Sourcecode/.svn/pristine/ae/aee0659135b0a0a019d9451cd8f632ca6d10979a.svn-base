package com.seatech.tp.ttlaigoc.action;

import com.seatech.framework.datamanager.BaseDelegate;
import com.seatech.tp.ttlaigoc.dao.TTLaiGocDao;
import com.seatech.tp.ttlaigoc.vo.TTLaiGocVo;

import java.sql.Connection;

import java.util.Collection;

public class TTLaiGocDelegate extends BaseDelegate {
    public TTLaiGocDelegate(Connection conn) {
        super();
        this.conn = conn;
    }

    public Collection getListTTDTPaging(TTLaiGocVo vo, Integer page,
                                        Integer count,
                                        Integer[] totalCount) throws Exception {
        Collection reval = null;

        try {
            TTLaiGocDao dao = new TTLaiGocDao(conn);
            reval = dao.tinhLSDN(vo, page, count, totalCount);
            conn.commit();
        } catch (Exception ex) {
            conn.rollback();
            throw ex;
        }
        return reval;
    }
}
