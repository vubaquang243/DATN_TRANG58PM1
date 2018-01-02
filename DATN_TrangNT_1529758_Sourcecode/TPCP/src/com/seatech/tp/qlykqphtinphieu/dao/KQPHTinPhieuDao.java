package com.seatech.tp.qlykqphtinphieu.dao;

import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.DateParameter;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;
import com.seatech.framework.exception.TPCPException;
import com.seatech.framework.utils.StringUtil;
import com.seatech.tp.kqphathanh.dao.QLyKQPhatHanhDAO;

import com.seatech.tp.kqphathanh.vo.QLyKQPhatHanhCTiet_SoHuuVO;
import com.seatech.tp.kqphathanh.vo.QLyKQPhatHanhCTiet_TpcpVO;
import com.seatech.tp.kqphathanh.vo.QLyKQPhatHanhVO;

import com.seatech.tp.qlykqphtinphieu.vo.KQPHTinPhieuCTSoHuuVo;
import com.seatech.tp.qlykqphtinphieu.vo.KQPHTinPhieuCTTPCPVo;
import com.seatech.tp.qlykqphtinphieu.vo.KQPHTinPhieuVo;

import com.seatech.tp.quanlykehoachquy.vo.KHPHChiTietVO;

import java.sql.Blob;
import java.sql.Connection;

import java.sql.PreparedStatement;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class KQPHTinPhieuDao extends AppDAO {
    private Connection conn = null;
    private String CLASS_NAME_DAO =
        "com.seatech.tp.qlykqphtinphieu.dao.KQPHTinPhieuDao";
    private String CLASS_NAME_VO =
        "com.seatech.tp.qlykqphtinphieu.vo.KQPHTinPhieuVo";
    private String CLASS_NAME_VO_CTIET_TPCP =
        "com.seatech.tp.qlykqphtinphieu.vo.KQPHTinPhieuCTTPCPVo";
    private String CLASS_NAME_VO_CTIET_SoHuu =
        "com.seatech.tp.qlykqphtinphieu.vo.KQPHTinPhieuCTSoHuuVo";
    private static String STRING_EMPTY = "";

    public KQPHTinPhieuDao(Connection conn) {
        super();
        this.conn = conn;
    }

    public Long update(KQPHTinPhieuVo vo) throws Exception {
        Vector v_param = new Vector();
        Vector v_param2 = null;
        Vector v_param3 = null;
        //Lay ID tu seq
        Long lID = new Long("0");
        int trangthai = 0;
        //0: sua
        //1: them
        try {
            StringBuffer strSQL = new StringBuffer();
            String sql = "";
            if (vo.getGuid() != null && !vo.getGuid().equals("")) {
                sql = generateSQLUpdate(vo, v_param, "TP_KQPH_TIN_PHIEU", conn);
                strSQL.append(sql);
                strSQL.append("WHERE GUID = ?");
                v_param.add(new Parameter(vo.getGuid(), true));
                lID = new Long(vo.getGuid());
                trangthai = 0;
            } else {
                lID = getSeqNextValue("TP_KQPH_TIN_PHIEU_SEQ", conn);
                vo.setGuid(String.valueOf(lID));
                sql = generateSQLInsert(vo, v_param, "TP_KQPH_TIN_PHIEU", conn);
                strSQL.append(sql);
                trangthai = 1;
            }
            if (executeStatement(strSQL.toString(), v_param, conn) == 1) {
                //insert list ctiet_tpcp
                if (trangthai == 1) {
                    if (vo.getLstCTPH_TPCP() != null &&
                        !vo.getLstCTPH_TPCP().isEmpty()) {
                        //delete first
                        executeStatement("DELETE FROM TP_KQPH_TIN_PHIEU_CTIET_TPCP WHERE TP_KQPH_TIN_PHIEU_ID = '" +
                                         lID + "'", v_param2, conn);
                        KQPHTinPhieuCTTPCPVo ctiet_TPCPVO = null;
                        Iterator ito = vo.getLstCTPH_TPCP().iterator();
                        while (ito.hasNext()) {
                            v_param2 = new Vector();
                            ctiet_TPCPVO = (KQPHTinPhieuCTTPCPVo)ito.next();
                            ctiet_TPCPVO.setKy_han(vo.getKy_han());
                            ctiet_TPCPVO.setTp_kqph_tin_phieu_id(String.valueOf(lID));
                            ctiet_TPCPVO.setGuid(String.valueOf(getSeqNextValue("TP_KQPH_TP_CT_TPCP_SEQ",
                                                                                conn)));
                       //     String sql1="";
                            sql = generateSQLInsert(ctiet_TPCPVO, v_param2, "TP_KQPH_TIN_PHIEU_CTIET_TPCP",conn);
                            executeStatement(sql, v_param2, conn);
                        }
                    }
                }
                //insert list ctiet_sohuu
                if (vo.getListCTSHTinPhieu() != null &&
                    !vo.getListCTSHTinPhieu().isEmpty()) {
                    //delete first
                    executeStatement("DELETE FROM TP_KQPH_TIN_PHIEU_CTIET_SO_HUU WHERE TP_KQPH_TIN_PHIEU_ID = '" +
                                     lID + "'", v_param3, conn);
                    KQPHTinPhieuCTSoHuuVo ctiet_SoHuuVO = null;
                    Iterator ito = vo.getListCTSHTinPhieu().iterator();
                    while (ito.hasNext()) {
                        v_param3 = new Vector();
                        ctiet_SoHuuVO = (KQPHTinPhieuCTSoHuuVo)ito.next();
                        ctiet_SoHuuVO.setTp_kqph_tin_phieu_id(String.valueOf(lID));
                        ctiet_SoHuuVO.setGuid(String.valueOf(getSeqNextValue("TP_KQPH_TP_CT_SH_SEQ",
                                                                             conn)));
                        
                        sql =generateSQLInsert(ctiet_SoHuuVO, v_param3, "TP_KQPH_TIN_PHIEU_CTIET_SO_HUU",conn);
                        executeStatement(sql, v_param3, conn);
                    }
                }
                return lID;
            } else
                return new Long("0");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void insertTP_KQPH_TIN_PHIEU_FILE(byte[] kqphPDFFile, long id_kq_ph,
                                             String ngay_imp,
                                             long nguoi_imp) throws Exception {

        PreparedStatement pstm = null;
        StringBuffer sql = new StringBuffer();
        Blob fileBLOB;
        sql.append("INSERT INTO TP_KQPH_TIN_PHIEU_FILE(KQPH_ID,NGAY_IMP,NGUOI_IMP,FILE_IMP) values(");
        sql.append(id_kq_ph);
        sql.append(", to_date('" + ngay_imp + "', 'DDMMRRRRHH24MISS')");
        sql.append(", ");
        sql.append(nguoi_imp);
        sql.append(",?)");
        pstm = conn.prepareStatement(sql.toString());
        fileBLOB = conn.createBlob();
        fileBLOB.setBytes(1, kqphPDFFile);
        pstm.setBlob(1, fileBLOB);
        pstm.execute();
        pstm.close();
        fileBLOB = null;
    }

    public Collection getListKQPHTinPhieuPaging(KQPHTinPhieuVo vo,
                                                Integer page, Integer count,
                                                Integer[] totalCount) throws Exception {
        Collection reval = null;
        String whereClause = "";
        Vector vParam = new Vector();
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT a.GUID,a.SO_TB_DE_NGHI_PH,to_char(a.NGAY_TB_DE_NGHI_PH,'dd/MM/yyyy') NGAY_TB_DE_NGHI_PH,to_char(a.NGAY_DT,'dd/MM/yyyy') NGAY_DT, to_char(a.NGAY_PH,'dd/MM/yyyy') NGAY_PH," +
                          "a.DOT_DT,a.MA_TPCP,(b.KY_HAN || ' ' || b.LOAI_KY_HAN) KY_HAN,c.TONG_KLPH,a.TONG_KLPH_THEM," +
                          "a.TONG_KL_TRUNG_THAU,a.TONG_TIEN_BAN,a.LOAI_TIEN,a.TRANG_THAI" +
                          ",d.NAME as TEN_TRANG_THAI ,a.NGUOI_TAO,to_char(a.NGAY_TAO,'dd/MM/yyyy') NGAY_TAO,a.NGUOI_SUA_CUOI,to_char(a.NGAY_SUA_CUOI,'dd/MM/yyyy') NGAY_SUA_CUOI, " +
                          "a.NGUOI_DUYET,to_char(a.NGAY_DUYET,'dd/MM/yyyy') NGAY_DUYET,a.LY_DO_TU_CHOI,to_char(a.NGAY_TO_CHUC_PH,'dd/MM/yyyy') NGAY_TO_CHUC_PH,a.DOT_PH,to_char(c.NGAY_DAO_HAN,'dd/MM/yyyy') NGAY_DAO_HAN,c.LS_BINH_QUAN  " +
                          "FROM TP_KQPH_TIN_PHIEU a \n" +
                          "inner join TP_DM_KY_HAN b  on a.KY_HAN = b.GUID \n" +
                          "inner join TP_KQPH_TIN_PHIEU_CTIET_TPCP c  on c.TP_KQPH_TIN_PHIEU_ID = a.GUID " +
                          "inner join TP_DM_TRANG_THAI d on a.TRANG_THAI = d.ID_STATUS");
            if (vo.getMa_tpcp() != null && !"".equals(vo.getMa_tpcp())) {
                whereClause += " and  a.MA_TPCP  like '%" +vo.getMa_tpcp() + "%'";
                
            }
            if (vo.getDot_ph() != null && !"".equals(vo.getDot_ph())) {
                whereClause += " and  a.DOT_PH like '%" + vo.getDot_ph() + "%'";
                
            }
            if (vo.getTrang_thai() != null && !"".equals(vo.getTrang_thai())) {
                whereClause += " and  a.TRANG_THAI = ?";
                vParam.add(new Parameter(vo.getTrang_thai(), true));
            }
            if (vo.getNgay_phat_hanh_tu_ngay() != null &&
                !"".equals(vo.getNgay_phat_hanh_tu_ngay())) {
                whereClause += " and trunc(a.NGAY_PH) >= ? ";
                vParam.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_phat_hanh_tu_ngay(),
                                                                     "dd/MM/yyyy"),
                                             true));
            }

            if (vo.getNgay_phat_hanh_den_ngay() != null &&
                !"".equals(vo.getNgay_phat_hanh_den_ngay())) {
                whereClause += " and trunc(a.NGAY_PH) <= ? ";

                vParam.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_phat_hanh_den_ngay(),
                                                                     "dd/MM/yyyy"),
                                             true));
                
            }
            if (whereClause != null && !whereClause.equalsIgnoreCase("")) {
                strSQL.append(" WHERE 1=1 " + whereClause);
            }
            strSQL.append(" ORDER BY a.NGAY_TAO DESC");
            reval =
                    executeSelectWithPaging(conn, strSQL.toString(), vParam, CLASS_NAME_VO,
                                            page, count, totalCount);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO +
                                   ".getListKQPHTinPhieuPaging(): " +
                                   ex.getMessage(), ex);
        }
        return reval;
    }

    public Collection getListKQPHTinPhieuPheDuyetPagings(KQPHTinPhieuVo vo,
                                                         Integer page,
                                                         Integer count,
                                                         Integer[] totalCount) throws Exception {
        Collection reval = null;
        String whereClause = "";
        Vector vParam = new Vector();
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT a.GUID,a.SO_TB_DE_NGHI_PH,to_char(a.NGAY_TB_DE_NGHI_PH,'dd/MM/yyyy') NGAY_TB_DE_NGHI_PH,to_char(a.NGAY_DT,'dd/MM/yyyy') NGAY_DT,to_char(a.NGAY_PH,'dd/MM/yyyy') NGAY_PH, " +
                          "a.DOT_DT,a.MA_TPCP,(b.KY_HAN || ' ' || b.LOAI_KY_HAN) KY_HAN,c.TONG_KLPH,a.TONG_KLPH_THEM," +
                          "a.TONG_KL_TRUNG_THAU,a.TONG_TIEN_BAN,a.LOAI_TIEN,a.TRANG_THAI" +
                          ",d.NAME as TEN_TRANG_THAI ,a.NGUOI_TAO,to_char(a.NGAY_TAO,'dd/MM/yyyy') NGAY_TAO,a.NGUOI_SUA_CUOI,to_char(a.NGAY_SUA_CUOI,'dd/MM/yyyy') NGAY_SUA_CUOI, " +
                          "a.NGUOI_DUYET,to_char(a.NGAY_DUYET,'dd/MM/yyyy') NGAY_DUYET,a.LY_DO_TU_CHOI,to_char(a.NGAY_TO_CHUC_PH,'dd/MM/yyyy') NGAY_TO_CHUC_PH,a.DOT_PH,to_char(c.NGAY_DAO_HAN,'dd/MM/yyyy') NGAY_DAO_HAN,c.LS_BINH_QUAN  " +
                          "FROM TP_KQPH_TIN_PHIEU a \n" +
                    "inner join TP_DM_KY_HAN b  on a.KY_HAN = b.GUID \n" +
                    "inner join TP_KQPH_TIN_PHIEU_CTIET_TPCP c  on c.TP_KQPH_TIN_PHIEU_ID = a.GUID " +
                    "inner join TP_DM_TRANG_THAI d on a.TRANG_THAI = d.ID_STATUS");
            if (vo.getMa_tpcp() != null && !"".equals(vo.getMa_tpcp())) {
              whereClause += " and  a.MA_TPCP  like '%" + vo.getMa_tpcp()+ "%'";
                
            }
            if (vo.getDot_ph() != null && !"".equals(vo.getDot_ph())) {
                whereClause += " and  a.DOT_PH  like '%" + vo.getDot_ph()+ "%'";
               
            }
            if (vo.getTrang_thai() != null && !"".equals(vo.getTrang_thai())) {
                whereClause += " and  a.TRANG_THAI = ?";
                vParam.add(new Parameter(vo.getTrang_thai(), true));
            } else if (vo.getTrang_thai() == null ||
                       vo.getTrang_thai().equals("")) {
                whereClause += " and  a.TRANG_THAI != '00'";
            }
            if (vo.getNgay_phat_hanh_tu_ngay() != null &&
                !"".equals(vo.getNgay_phat_hanh_tu_ngay())) {
                whereClause += " and trunc(a.NGAY_PH) >= ? ";

                vParam.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_phat_hanh_tu_ngay(),
                                                                     "dd/MM/yyyy"),
                                             true));
            }

            if (vo.getNgay_phat_hanh_den_ngay() != null &&
                !"".equals(vo.getNgay_phat_hanh_den_ngay())) {
                whereClause += " and trunc(a.NGAY_PH) <= ? ";

                vParam.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_phat_hanh_den_ngay(),
                                                                     "dd/MM/yyyy"),
                                             true));
            }

            if (whereClause != null && !whereClause.equalsIgnoreCase("")) {
                strSQL.append(" WHERE 1=1 " + whereClause);
            }
            strSQL.append(" ORDER BY a.DOT_PH DESC");
            reval =
                    executeSelectWithPaging(conn, strSQL.toString(), vParam, CLASS_NAME_VO,
                                            page, count, totalCount);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO +
                                   ".getListKQPHTinPhieuPaging(): " +
                                   ex.getMessage(), ex);
        }
        return reval;
    }

    public KQPHTinPhieuVo gettKQPHTinPhieuObject(Map<String, Object> map) throws Exception,
                                                                                 TPCPException {
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = new StringBuffer();
        Vector vParam = new Vector();
        try {
            strSQL.append("SELECT a.GUID,a.SO_TB_DE_NGHI_PH,to_char(a.NGAY_TB_DE_NGHI_PH,'dd/MM/yyyy') NGAY_TB_DE_NGHI_PH,to_char(a.NGAY_DT,'dd/MM/yyyy') NGAY_DT, " +
                          "a.DOT_DT,a.MA_TPCP,a.TONG_KLPH,a.TONG_KLPH_THEM," +
                          "a.TONG_KL_TRUNG_THAU,a.TONG_TIEN_BAN,a.LOAI_TIEN,a.TRANG_THAI" +
                          " ,a.NGUOI_TAO,to_char(a.NGAY_TAO,'dd/MM/yyyy') NGAY_TAO,a.NGUOI_SUA_CUOI,to_char(a.NGAY_SUA_CUOI,'dd/MM/yyyy') NGAY_SUA_CUOI, " +
                          " a.KY_HAN ," +
                          " a.NGUOI_DUYET,to_char(a.NGAY_DUYET,'dd/MM/yyyy') NGAY_DUYET,a.LY_DO_TU_CHOI,to_char(a.NGAY_TO_CHUC_PH,'dd/MM/yyyy') NGAY_TO_CHUC_PH,a.DOT_PH,to_char(a.NGAY_DAO_HAN,'dd/MM/yyyy') NGAY_DAO_HAN " +
                          " FROM TP_KQPH_TIN_PHIEU a \n");
            Set<String> keySet = map.keySet();
            Iterator<String> keySetIterator = keySet.iterator();
            while (keySetIterator.hasNext()) {
                String key = keySetIterator.next();
                String value = map.get(key).toString();
                if (value != null && !STRING_EMPTY.equals(value) && !"NGAY_PH".equals(key)) {
                    strSQL2.append("and " + key + " = ? ");
                    vParam.add(new Parameter(value, true));
                }else{
                 strSQL2.append( " and trunc(a.NGAY_PH) = ? ");

                  vParam.add(new DateParameter(StringUtil.StringToDate(value,
                                                                       "dd/MM/yyyy"),
                                               true));
                }
            }
            if (strSQL2.toString().length() > 0) {
                strSQL.append(" WHERE 1 = 1 " + strSQL2.toString());
            }
            return (KQPHTinPhieuVo)findByPK(strSQL.toString(), vParam,
                                            CLASS_NAME_VO, conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO +
                                   ".gettKQPHTinPhieuObject(): " +
                                   ex.getMessage(), ex);
        }
    }
 
    public Collection getListKQPH_CTIET_TPCP(Map<String, Object> map) throws Exception,
                                                                             TPCPException {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = new StringBuffer();
        Vector vParam = new Vector();
        try {
            strSQL.append("SELECT \n" +
                    "   T.GUID, T.KLPH, T.KLPH_THEM, \n" +
                    "   (b.KY_HAN || ' ' || b.LOAI_KY_HAN) KY_HAN, to_char(decode(t.LS_BINH_QUAN,'0','',t.LS_BINH_QUAN) , 'fm9999.00') LS_BINH_QUAN, \n" +
                    "   T.MA_TPCP, to_char(T.NGAY_DAO_HAN,'dd/MM/yyyy') NGAY_DAO_HAN,to_char(T.NGAY_PH,'dd/MM/yyyy') NGAY_PH , \n" +
                    "   T.TONG_KLPH, T.TP_KQPH_TIN_PHIEU_ID \n" +
                    "   FROM TP_KQPH_TIN_PHIEU_CTIET_TPCP T " +
                    "   inner join TP_DM_KY_HAN b  on T.KY_HAN = b.GUID \n");
            Set<String> keySet = map.keySet();
            Iterator<String> keySetIterator = keySet.iterator();
            while (keySetIterator.hasNext()) {
                String key = keySetIterator.next();
                String value = map.get(key).toString();
                if (value != null && !STRING_EMPTY.equals(value)) {
                    strSQL2.append("and " + key + " = ? ");
                    vParam.add(new Parameter(value, true));
                }
            }
            if (strSQL2.toString().length() > 0) {
                strSQL.append(" WHERE 1 = 1 " + strSQL2.toString());
            }
            reval =
                    executeSelectStatement(strSQL.toString(), vParam, CLASS_NAME_VO_CTIET_TPCP,
                                           this.conn);
            return reval;
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO +
                                   ".getListKQPHChiTiet_TPCPPaging(): " +
                                   ex.getMessage(), ex);
        }
    }
    
    
    
   

    public Collection getListKQPH_CTIET_SHTPCP(Map<String, Object> map) throws Exception,
                                                                               TPCPException {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = new StringBuffer();
        Vector vParam = new Vector();
        try {
            strSQL.append("SELECT \n" +
                    "    T.GUID, T.TP_KQPH_TIN_PHIEU_ID, T.STT, \n" +
                    "    T.MA_TPCP, T.THANH_VIEN_DT, \n" +
                    "    T.MA_CHU_SO_HUU, T.TEN_CHU_SO_HUU, \n" +
                    "    T.SO_TK_TT, T.KL_TRUNG_THAU, to_char(decode(t.LS_TRUNG_THAU,'0','',t.LS_TRUNG_THAU) , 'fm9999.00') LS_TRUNG_THAU  , T.TIEN_TT_MUA,T.TRANG_THAI_TT \n" +
                    "    FROM TP_KQPH_TIN_PHIEU_CTIET_SO_HUU T");
            Set<String> keySet = map.keySet();
            Iterator<String> keySetIterator = keySet.iterator();
            while (keySetIterator.hasNext()) {
                String key = keySetIterator.next();
                String value = map.get(key).toString();
                if (value != null && !STRING_EMPTY.equals(value)) {
                    strSQL2.append("and " + key + " = ? ");
                    vParam.add(new Parameter(value, true));
                }
            }
            if (strSQL2.toString().length() > 0) {
                strSQL.append(" WHERE 1 = 1 " + strSQL2.toString());
            }
            reval =
                    executeSelectStatement(strSQL.toString(), vParam, CLASS_NAME_VO_CTIET_SoHuu,
                                           this.conn);
            return reval;
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO +
                                   ".getListKQPHChiTiet_TPCPPaging(): " +
                                   ex.getMessage(), ex);
        }
    }

    public Long delete(KQPHTinPhieuVo vo) throws Exception {
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        strSQL.append(" DELETE TP_KQPH_TIN_PHIEU WHERE GUID = ?");
        int nExc = 0;
        if (vo.getGuid() != null) {
            v_param.add(new Parameter(vo.getGuid(), true));
            nExc = executeStatement(strSQL.toString(), v_param, conn);
            if (nExc == 1)
                return Long.parseLong(vo.getGuid());
        }
        return new Long("0");
    }

    public Long deleteChiTiet_TPCP(KQPHTinPhieuVo vo) throws Exception {
        Vector v_param = new Vector();
        StringBuffer strSQLChiTiet = new StringBuffer();
        strSQLChiTiet.append("DELETE TP_KQPH_TIN_PHIEU_CTIET_TPCP WHERE TP_KQPH_TIN_PHIEU_ID = ?");
        int nExc = 0;
        if (vo.getGuid() != null) {
            v_param.add(new Parameter(vo.getGuid(), true));
            nExc = executeStatement(strSQLChiTiet.toString(), v_param, conn);
            if (nExc == 1)
                return Long.parseLong(vo.getGuid());
        }
        return new Long("0");
    }

    public Long deleteChiTiet_SoHuu(KQPHTinPhieuVo vo) throws Exception {
        Vector v_param = new Vector();
        StringBuffer strSQLChiTiet = new StringBuffer();
        strSQLChiTiet.append("DELETE TP_KQPH_TIN_PHIEU_CTIET_SO_HUU WHERE TP_KQPH_TIN_PHIEU_ID = ?");
        int nExc = 0;
        if (vo.getGuid() != null) {
            v_param.add(new Parameter(vo.getGuid(), true));
            nExc = executeStatement(strSQLChiTiet.toString(), v_param, conn);
            if (nExc == 1)
                return Long.parseLong(vo.getGuid());
        }
        return new Long("0");
    }

    public Long deleteTP_KQPH_FILE(KQPHTinPhieuVo vo) throws Exception {
        Vector v_param = new Vector();
        StringBuffer strSQLChiTiet = new StringBuffer();
        strSQLChiTiet.append("DELETE TP_KQPH_TIN_PHIEU_FILE WHERE KQPH_ID = ?");
        int nExc = 0;
        if (vo.getGuid() != null) {
            v_param.add(new Parameter(vo.getGuid(), true));
            nExc = executeStatement(strSQLChiTiet.toString(), v_param, conn);
            if (nExc == 1)
                return Long.parseLong(vo.getGuid());
        }
        return new Long("0");
    }

}
