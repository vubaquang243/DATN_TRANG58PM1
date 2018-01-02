package com.seatech.tp.tiente.action;

import com.seatech.framework.datamanager.BaseDelegate;
import com.seatech.tp.tiente.dao.TienTeDao;
import com.seatech.tp.tiente.vo.TienTeVo;
import com.seatech.tp.ttindthau.action.QLyTTinDauThauDelegate;

import com.seatech.tp.ttindthau.dao.QLyTTinDauThauDAO;
import com.seatech.tp.ttindthau.vo.ThongTinDauThauVO;

import java.sql.Connection;

import java.util.Collection;

public class TienTeDelegate extends BaseDelegate {
    public TienTeDelegate(Connection conn) {
        super();
        this.conn = conn;
    }
    public Collection getListTienTe(TienTeVo vo, Integer page,
                                        Integer count,
                                        Integer[] totalCount) throws Exception {
        Collection reval = null;
  
        try {
            TienTeDao dao = new TienTeDao(conn);
            reval = dao.getListTienTe(vo, page, count, totalCount);
  
        } catch (Exception ex) {
            throw ex;
        }
        return reval;
  }
    
}
