package com.seatech.tp.ttindthau.dao;

import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.DateParameter;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;
import com.seatech.framework.exception.TPCPException;
import com.seatech.framework.utils.StringUtil;

import com.seatech.tp.ttindthau.vo.ThongBaoVO;
import com.seatech.tp.ttindthau.vo.ThongTinDauThauVO;

import java.sql.Connection;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class QLyTTinDauThauDAO extends AppDAO {
    private Connection conn = null;
    private String CLASS_NAME_DAO = "com.seatech.tp.ttindthau.dao.QLyTTinDauThauDAO";
    private String CLASS_NAME_VO = "com.seatech.tp.ttindthau.vo.ThongTinDauThauVO";
    private String CLASS_THONG_BAO = "com.seatech.tp.ttindthau.vo.ThongBaoVO";
    private static String STRING_EMPTY = "";

    public QLyTTinDauThauDAO(Connection conn) {
        super();
        this.conn = conn;
    }

    public Long  insert(ThongTinDauThauVO vo) throws Exception {
        Vector v_param = new Vector();
        //L?y ID t? seq
        Long lID = new Long("0");
        StringBuffer strSQL = new StringBuffer();
        String sql = "";
        if (vo.getGuid() != null && !vo.getGuid().equals("")) {
            sql = generateSQLUpdate(vo, v_param, "TP_THONG_TIN_DAU_THAU", conn);
            strSQL.append(sql);
            strSQL.append("WHERE GUID = ?");
            v_param.add(new Parameter(vo.getGuid(), true));
            lID = new Long(vo.getGuid());
        } else {
            lID = getSeqNextValue("TP_MA_TPCP_SEQ", conn);
            vo.setGuid(String.valueOf(lID));
            sql = generateSQLInsert(vo, v_param, "TP_THONG_TIN_DAU_THAU", conn);
            strSQL.append(sql);
        }
        if (executeStatement(strSQL.toString(), v_param, conn) == 1)
            return lID;
        else
            return new Long("0");
    }

    public Collection getListTTDTPaging(ThongTinDauThauVO vo, Integer page, Integer count, Integer[] totalCount) throws Exception {
        Collection reval = null;
        String whereClause = "";
        Vector vParam = new Vector();
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT GUID,  DOT_DAU_THAU,  DOT_BO_SUNG,  MA_TPCP,(KHOI_LUONG_TP + KHOI_LUONG_THEM) AS KHOI_LUONG_TP,KHOI_LUONG_THEM,  LOAI_TIEN,  (SELECT (KY_HAN || ' ' ||LOAI_KY_HAN) NAME_KY_HAN FROM TP_DM_KY_HAN where GUID=a.KY_HAN) AS KY_HAN," +
                    "  to_char(NGAY_TO_CHUC_PH,'dd/MM/yyyy') NGAY_TO_CHUC_PH,  to_char(NGAY_PH,'dd/MM/yyyy') NGAY_PH,  " +
                    "  to_char(NGAY_TT_TIEN_MUA,'dd/MM/yyyy') NGAY_TT_TIEN_MUA,  " +
                    "  to_char(NGAY_DAO_HAN,'dd/MM/yyyy') NGAY_DAO_HAN, PHUONG_THUC_THANH_TOAN,  HINH_THUC_DT, PTHUC_XACDINH_KQDT, SO_TK_NHAN, " +
                    "  SO_TBAO_GOI_THAU,  NGAY_TBAO_GOI_THAU, to_char(NGAY_TBAO_GOI_THAU,'dd/MM/yyyy') NGAY_TBAO_GOI_THAU,  " +
                    "  GHI_CHU,  TRANG_THAI,  NGUOI_TAO, to_char(NGAY_TAO,'dd/MM/yyyy') NGAY_TAO,  NGUOI_SUA_CUOI,to_char(NGAY_SUA_CUOI,'dd/MM/yyyy')  NGAY_SUA_CUOI, " +
                    "  NGUOI_PHE_DUYET, to_char(NGAY_PHE_DUYET,'dd/MM/yyyy') NGAY_PHE_DUYET,  LY_DO_TU_CHOI,to_char(NGAY_TT_LAI_1,'dd/MM/yyyy') NGAY_TT_LAI_1, to_char(NGAY_TT_LAI_2,'dd/MM/yyyy') NGAY_TT_LAI_2,MENH_GIA, KY_TRA_LAI, " +
                    " (select b.name from TP_DM_TRANG_THAI b where b.ID_STATUS= A.TRANG_THAI) AS TEN_TRANG_THAI " + "  FROM TP_THONG_TIN_DAU_THAU a");
            if (vo.getMa_tpcp() != null && !"".equals(vo.getMa_tpcp())) {
                whereClause += " and  UPPER(a.MA_TPCP) like '%" + vo.getMa_tpcp().toUpperCase().trim() + "%'";
            }

            if (vo.getDot_dau_thau() != null && !"".equals(vo.getDot_dau_thau())) {
                whereClause += " and  a.DOT_DAU_THAU  like '%" + vo.getDot_dau_thau() + "%'";

            }

            if (vo.getTrang_thai() != null && !"".equals(vo.getTrang_thai())) {
                whereClause += " and  a.TRANG_THAI  = ?";
                vParam.add(new Parameter(vo.getTrang_thai(), true));
            }

            if (vo.getTu_ngay() != null && !"".equals(vo.getTu_ngay())) {
                whereClause += " and trunc(a.NGAY_TO_CHUC_PH) >= ?";

                vParam.add(new DateParameter(StringUtil.StringToDate(vo.getTu_ngay(), "dd/MM/yyyy"), true));
            }
            if (vo.getDen_ngay() != null && !"".equals(vo.getDen_ngay())) {
                whereClause += " and trunc(a.NGAY_TO_CHUC_PH) <= ?";

                vParam.add(new DateParameter(StringUtil.StringToDate(vo.getDen_ngay(), "dd/MM/yyyy"), true));
            }
            if (whereClause != null && !whereClause.equalsIgnoreCase("")) {
                strSQL.append(" WHERE 1=1 " + whereClause);
            }
            strSQL.append(" ORDER BY a.NGAY_TAO DESC");
            reval = executeSelectWithPaging(conn, strSQL.toString(), vParam, CLASS_NAME_VO, page, count, totalCount);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getListTPCPPaging(): " + ex.getMessage(), ex);
        }
        return reval;
    }

    public Collection getListTTDT_PDPaging(ThongTinDauThauVO vo, Integer page, Integer count, Integer[] totalCount) throws Exception {
        Collection reval = null;
        String whereClause = "";
        Vector vParam = new Vector();
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT GUID,  DOT_DAU_THAU,  DOT_BO_SUNG,  MA_TPCP, (KHOI_LUONG_TP + KHOI_LUONG_THEM) AS KHOI_LUONG_TP,KHOI_LUONG_THEM,  LOAI_TIEN,  (SELECT (KY_HAN || ' ' ||LOAI_KY_HAN) NAME_KY_HAN FROM TP_DM_KY_HAN where GUID=a.KY_HAN) AS KY_HAN," +
                    "  to_char(NGAY_TO_CHUC_PH,'dd/MM/yyyy') NGAY_TO_CHUC_PH,  to_char(NGAY_PH,'dd/MM/yyyy') NGAY_PH,  " +
                    "  to_char(NGAY_TT_TIEN_MUA,'dd/MM/yyyy') NGAY_TT_TIEN_MUA,  " +
                    "  to_char(NGAY_DAO_HAN,'dd/MM/yyyy') NGAY_DAO_HAN, PHUONG_THUC_THANH_TOAN,  HINH_THUC_DT, PTHUC_XACDINH_KQDT, SO_TK_NHAN, " +
                    "  SO_TBAO_GOI_THAU,  NGAY_TBAO_GOI_THAU, to_char(NGAY_TBAO_GOI_THAU,'dd/MM/yyyy') NGAY_TBAO_GOI_THAU,  " +
                    "  GHI_CHU,  TRANG_THAI,  NGUOI_TAO, to_char(NGAY_TAO,'dd/MM/yyyy') NGAY_TAO,  NGUOI_SUA_CUOI,to_char(NGAY_SUA_CUOI,'dd/MM/yyyy')  NGAY_SUA_CUOI, " +
                    "  NGUOI_PHE_DUYET, to_char(NGAY_PHE_DUYET,'dd/MM/yyyy') NGAY_PHE_DUYET,  LY_DO_TU_CHOI,to_char(NGAY_TT_LAI_1,'dd/MM/yyyy') NGAY_TT_LAI_1, to_char(NGAY_TT_LAI_2,'dd/MM/yyyy') NGAY_TT_LAI_2,MENH_GIA, KY_TRA_LAI, " +
                    " (select b.name from TP_DM_TRANG_THAI b where b.ID_STATUS= A.TRANG_THAI) AS TEN_TRANG_THAI " +
                    "  FROM TP_THONG_TIN_DAU_THAU a WHERE a.TRANG_THAI in ('01','02','03','05') ");
            if (vo.getMa_tpcp() != null && !"".equals(vo.getMa_tpcp())) {
                whereClause += " and  UPPER(a.MA_TPCP) like '%" + vo.getMa_tpcp().toUpperCase().trim() + "%'";
            }

            if (vo.getDot_dau_thau() != null && !"".equals(vo.getDot_dau_thau())) {
                whereClause += " and  a.DOT_DAU_THAU  like '%" + vo.getDot_dau_thau().toUpperCase() + "%'";

            }

            if (vo.getTrang_thai() != null && !"".equals(vo.getTrang_thai())) {
                whereClause += " and  a.TRANG_THAI  = ?";
                vParam.add(new Parameter(vo.getTrang_thai(), true));
            }

            if (vo.getTu_ngay() != null && !"".equals(vo.getTu_ngay())) {
                whereClause += " and trunc(a.NGAY_TO_CHUC_PH) >= ?";

                vParam.add(new DateParameter(StringUtil.StringToDate(vo.getTu_ngay(), "dd/MM/yyyy"), true));
            }
            if (vo.getDen_ngay() != null && !"".equals(vo.getDen_ngay())) {
                whereClause += " and trunc(a.NGAY_TO_CHUC_PH) <= ?";

                vParam.add(new DateParameter(StringUtil.StringToDate(vo.getDen_ngay(), "dd/MM/yyyy"), true));
            }
            if (whereClause != null && !whereClause.equalsIgnoreCase("")) {
                strSQL.append(whereClause);
            }
            strSQL.append(" ORDER BY a.NGAY_TAO DESC");
            reval = executeSelectWithPaging(conn, strSQL.toString(), vParam, CLASS_NAME_VO, page, count, totalCount);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getListTPCPPaging(): " + ex.getMessage(), ex);
        }
        return reval;
    }

    public ThongTinDauThauVO getTTDTObject(Map<String, Object> map) throws Exception, TPCPException {
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = new StringBuffer();
        Vector vParam = new Vector();
        ThongTinDauThauVO vo = null;
        try {
            strSQL.append("SELECT GUID,  DOT_DAU_THAU,  DOT_BO_SUNG,  MA_TPCP, (KHOI_LUONG_TP + KHOI_LUONG_THEM) AS KHOI_LUONG_TP,KHOI_LUONG_THEM,  LOAI_TIEN,  (SELECT (b.KY_HAN || ' ' ||b.LOAI_KY_HAN) NAME_KY_HAN FROM TP_DM_KY_HAN b where b.GUID=a.KY_HAN) AS KY_HAN," +
                    "  to_char(NGAY_TO_CHUC_PH,'dd/MM/yyyy') NGAY_TO_CHUC_PH,  to_char(NGAY_PH,'dd/MM/yyyy') NGAY_PH,  " +
                    "  to_char(NGAY_TT_TIEN_MUA,'dd/MM/yyyy') NGAY_TT_TIEN_MUA,  vietnamesenumbertowords.fnc_doc_tien(KHOI_LUONG_TP+KHOI_LUONG_THEM,LOAI_TIEN) KHOI_LUONG_TP_CHU ," +
                    "  to_char(NGAY_DAO_HAN,'dd/MM/yyyy') NGAY_DAO_HAN, PHUONG_THUC_THANH_TOAN,  HINH_THUC_DT, PTHUC_XACDINH_KQDT, SO_TK_NHAN, " +
                    "  SO_TBAO_GOI_THAU,  NGAY_TBAO_GOI_THAU, to_char(NGAY_TBAO_GOI_THAU,'dd/MM/yyyy') NGAY_TBAO_GOI_THAU,  " +
                    "  GHI_CHU,  TRANG_THAI,  NGUOI_TAO, to_char(NGAY_TAO,'dd/MM/yyyy') NGAY_TAO,  NGUOI_SUA_CUOI,to_char(NGAY_SUA_CUOI,'dd/MM/yyyy')  NGAY_SUA_CUOI, " +
                    "  NGUOI_PHE_DUYET, to_char(NGAY_PHE_DUYET,'dd/MM/yyyy') NGAY_PHE_DUYET,  LY_DO_TU_CHOI,to_char(NGAY_TT_LAI_1,'dd/MM/yyyy') NGAY_TT_LAI_1, to_char(NGAY_TT_LAI_2,'dd/MM/yyyy') NGAY_TT_LAI_2 ,MENH_GIA, KY_TRA_LAI,LS_DANH_NGHIA " +
                    "  FROM TP_THONG_TIN_DAU_THAU a");
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
            vo = (ThongTinDauThauVO)findByPK(strSQL.toString(), vParam, CLASS_NAME_VO, conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getTTDTObject(): " + ex.getMessage(), ex);
        }
        return vo;
    }

    public ThongTinDauThauVO getTTDTObject_check(Map<String, Object> map, String... kqph) throws Exception, TPCPException {
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = new StringBuffer();
        Vector vParam = new Vector();
        ThongTinDauThauVO vo = null;
        try {
            strSQL.append("SELECT GUID,  DOT_DAU_THAU,  DOT_BO_SUNG,  MA_TPCP, (KHOI_LUONG_TP + KHOI_LUONG_THEM) AS KHOI_LUONG_TP,KHOI_LUONG_THEM,  LOAI_TIEN,  (SELECT (b.KY_HAN || ' ' ||b.LOAI_KY_HAN) NAME_KY_HAN FROM TP_DM_KY_HAN b where b.GUID=a.KY_HAN) AS KY_HAN," +
                    "  to_char(NGAY_TO_CHUC_PH,'dd/MM/yyyy') NGAY_TO_CHUC_PH,  to_char(NGAY_PH,'dd/MM/yyyy') NGAY_PH,  " +
                    "  to_char(NGAY_TT_TIEN_MUA,'dd/MM/yyyy') NGAY_TT_TIEN_MUA,  " +
                    "  to_char(NGAY_DAO_HAN,'dd/MM/yyyy') NGAY_DAO_HAN, PHUONG_THUC_THANH_TOAN,  HINH_THUC_DT, PTHUC_XACDINH_KQDT, SO_TK_NHAN, " +
                    "  SO_TBAO_GOI_THAU,  NGAY_TBAO_GOI_THAU, to_char(NGAY_TBAO_GOI_THAU,'dd/MM/yyyy') NGAY_TBAO_GOI_THAU,  " +
                    "  GHI_CHU,  TRANG_THAI,  NGUOI_TAO, to_char(NGAY_TAO,'dd/MM/yyyy') NGAY_TAO,  NGUOI_SUA_CUOI,to_char(NGAY_SUA_CUOI,'dd/MM/yyyy')  NGAY_SUA_CUOI, " +
                    "  NGUOI_PHE_DUYET, to_char(NGAY_PHE_DUYET,'dd/MM/yyyy') NGAY_PHE_DUYET,  LY_DO_TU_CHOI,to_char(NGAY_TT_LAI_1,'dd/MM/yyyy') NGAY_TT_LAI_1, to_char(NGAY_TT_LAI_2,'dd/MM/yyyy') NGAY_TT_LAI_2 ,MENH_GIA, KY_TRA_LAI,LS_DANH_NGHIA " +
                    "  FROM TP_THONG_TIN_DAU_THAU a");
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
                if (kqph.length > 0) {
                    strSQL.append(" WHERE 1 = 1 " + strSQL2.toString() + " and a.trang_thai = '05' and  a.DOT_DAU_THAU not in (select DOT_DT FROM TP_KQPH)");
                } else {
                    strSQL.append(" WHERE 1 = 1 " + strSQL2.toString() + " and a.trang_thai = '05' and a.DOT_DAU_THAU not in (select DOT_DT FROM TP_KQ_DU_THAU)");
                }
            }
            vo = (ThongTinDauThauVO)findByPK(strSQL.toString(), vParam, CLASS_NAME_VO, conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getTTDTObject(): " + ex.getMessage(), ex);
        }
        return vo;
    }
    
    public ThongTinDauThauVO getTong_kl_da_goi_thau(Map<String,Object> map)throws Exception, TPCPException{
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = new StringBuffer();
        ThongTinDauThauVO vo= null;
        Vector vParam = new Vector();
        
        try{
          strSQL.append("SELECT SUM(KHOI_LUONG_TP+KHOI_LUONG_THEM) AS KHOI_LUONG_TP FROM TP_THONG_TIN_DAU_THAU WHERE TRANG_THAI='05' ");
          Set<String> keySet = map.keySet();
          Iterator itr = keySet.iterator();
            while(itr.hasNext()){
              String key= (String)itr.next();
              String value = map.get(key).toString();
                if(value!=null&& !STRING_EMPTY.equals(value)){
                  strSQL2.append(" and " + key + " = ? ");
                  vParam.add(new Parameter(value, true));
                }
            }
            if(strSQL2.toString().length()>0){
              strSQL.append(strSQL2.toString());
            }
            return (ThongTinDauThauVO)findByPK(strSQL.toString(), vParam, CLASS_NAME_VO, conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getTong_kl_da_goi_thau(): " + ex.getMessage(), ex);
        }
        
    }

    public ThongTinDauThauVO getTTDTObject2(Map<String, Object> map) throws Exception, TPCPException {
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = new StringBuffer();
        Vector vParam = new Vector();
        try {
            strSQL.append("SELECT GUID,  DOT_DAU_THAU,  DOT_BO_SUNG,  MA_TPCP, (KHOI_LUONG_TP + KHOI_LUONG_THEM) AS KHOI_LUONG_TP,KHOI_LUONG_THEM,  LOAI_TIEN, KY_HAN, " +
                    "  to_char(NGAY_TO_CHUC_PH,'dd/MM/yyyy') NGAY_TO_CHUC_PH,  to_char(NGAY_PH,'dd/MM/yyyy') NGAY_PH,  " +
                    "  to_char(NGAY_TT_TIEN_MUA,'dd/MM/yyyy') NGAY_TT_TIEN_MUA,  " +
                    "  to_char(NGAY_DAO_HAN,'dd/MM/yyyy') NGAY_DAO_HAN, PHUONG_THUC_THANH_TOAN,  HINH_THUC_DT, PTHUC_XACDINH_KQDT, SO_TK_NHAN, " +
                    "  SO_TBAO_GOI_THAU,  NGAY_TBAO_GOI_THAU, to_char(NGAY_TBAO_GOI_THAU,'dd/MM/yyyy') NGAY_TBAO_GOI_THAU,  " +
                    "  GHI_CHU,  TRANG_THAI,  NGUOI_TAO, to_char(NGAY_TAO,'dd/MM/yyyy') NGAY_TAO,  NGUOI_SUA_CUOI,to_char(NGAY_SUA_CUOI,'dd/MM/yyyy')  NGAY_SUA_CUOI, " +
                    "  NGUOI_PHE_DUYET, to_char(NGAY_PHE_DUYET,'dd/MM/yyyy') NGAY_PHE_DUYET,  LY_DO_TU_CHOI,to_char(NGAY_TT_LAI_1,'dd/MM/yyyy') NGAY_TT_LAI_1, to_char(NGAY_TT_LAI_2,'dd/MM/yyyy') NGAY_TT_LAI_2 ,MENH_GIA, KY_TRA_LAI,to_char(decode(LS_DANH_NGHIA,'0','',LS_DANH_NGHIA) , 'fm9999.00') LS_DANH_NGHIA" +
                    "  FROM TP_THONG_TIN_DAU_THAU ");
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
            return (ThongTinDauThauVO)findByPK(strSQL.toString(), vParam, CLASS_NAME_VO, conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getTTDTObject(): " + ex.getMessage(), ex);
        }
    }

    public ThongTinDauThauVO getTTDTDotDauFromDotBS(String ma_tpcp,String dot_bo_sung, String phuong_thuc_ph) throws Exception, TPCPException {
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = new StringBuffer();
        Vector vParam = new Vector();
        try {
            if (phuong_thuc_ph != null) {
                if (phuong_thuc_ph.equals("TD")) {
                    strSQL.append("SELECT GUID,  DOT_DAU_THAU,  DOT_BO_SUNG,  MA_TPCP, (KHOI_LUONG_TP + KHOI_LUONG_THEM) AS KHOI_LUONG_TP,KHOI_LUONG_THEM,  LOAI_TIEN, KY_HAN, " +
                            "  to_char(NGAY_TO_CHUC_PH,'dd/MM/yyyy') NGAY_TO_CHUC_PH,  to_char(NGAY_PH,'dd/MM/yyyy') NGAY_PH,  " +
                            "  to_char(NGAY_TT_TIEN_MUA,'dd/MM/yyyy') NGAY_TT_TIEN_MUA,  " +
                            "  to_char(NGAY_DAO_HAN,'dd/MM/yyyy') NGAY_DAO_HAN, PHUONG_THUC_THANH_TOAN,  HINH_THUC_DT, PTHUC_XACDINH_KQDT, SO_TK_NHAN, " +
                            "  SO_TBAO_GOI_THAU,  NGAY_TBAO_GOI_THAU, to_char(NGAY_TBAO_GOI_THAU,'dd/MM/yyyy') NGAY_TBAO_GOI_THAU,  " +
                            "  GHI_CHU,  TRANG_THAI,  NGUOI_TAO, to_char(NGAY_TAO,'dd/MM/yyyy') NGAY_TAO,  NGUOI_SUA_CUOI,to_char(NGAY_SUA_CUOI,'dd/MM/yyyy')  NGAY_SUA_CUOI, " +
                            "  NGUOI_PHE_DUYET, to_char(NGAY_PHE_DUYET,'dd/MM/yyyy') NGAY_PHE_DUYET,  LY_DO_TU_CHOI,to_char(NGAY_TT_LAI_1,'dd/MM/yyyy') NGAY_TT_LAI_1, to_char(NGAY_TT_LAI_2,'dd/MM/yyyy') NGAY_TT_LAI_2 ,MENH_GIA, KY_TRA_LAI,LS_DANH_NGHIA" +
                            "  FROM TP_THONG_TIN_DAU_THAU where MA_TPCP='"+ma_tpcp+"' and DOT_DAU_THAU = (select DOT_BO_SUNG from TP_THONG_TIN_DAU_THAU where ma_tpcp='"+ma_tpcp+"' and DOT_DAU_THAU = '" + dot_bo_sung + "')");
                    return (ThongTinDauThauVO)findByPK(strSQL.toString(), vParam, CLASS_NAME_VO, conn);
                } else if (phuong_thuc_ph.equals("TL")) {
                    strSQL.append("SELECT GUID,  DOT_PH DOT_DAU_THAU,  DOT_BO_SUNG,  MA_TPCP,   to_char(NGAY_PH,'dd/MM/yyyy') NGAY_PH,  " +
                            "  to_char(NGAY_TT_TIEN_MUA,'dd/MM/yyyy') NGAY_TT_TIEN_MUA " +
                            "  FROM TP_KQPH_BAN_LE where MA_TPCP='"+ma_tpcp+"' and DOT_PH = (select DOT_BO_SUNG from TP_KQPH_BAN_LE where MA_TPCP='"+ma_tpcp+"' and DOT_PH = '" + dot_bo_sung + "')");
                    return (ThongTinDauThauVO)findByPK(strSQL.toString(), vParam, CLASS_NAME_VO, conn);
                } else
                    return null;
            }
            return null;

        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getTTDTObject(): " + ex.getMessage(), ex);
        }
    }

    public ThongBaoVO getTbaoObject(Map<String, Object> map) throws Exception, TPCPException {
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = new StringBuffer();
        Vector vParam = new Vector();
        try {
            strSQL.append("SELECT email_content FROM TP_THONGBAO ");
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
            return (ThongBaoVO)findByPK(strSQL.toString(), vParam, CLASS_THONG_BAO, conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getTTDTObject(): " + ex.getMessage(), ex);
        }
    }

    public Long delete(ThongTinDauThauVO vo) throws Exception {
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        strSQL.append(" DELETE TP_THONG_TIN_DAU_THAU WHERE GUID = ?");
        int nExc = 0;
        if (vo.getGuid() != null) {
            v_param.add(new Parameter(vo.getGuid(), true));
            nExc = executeStatement(strSQL.toString(), v_param, conn);
            if (nExc == 1)
                return Long.parseLong(vo.getGuid());
        }
        return new Long("0");
    }


    public Collection getTTDTObject_DotDT() throws Exception {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT GUID,  DOT_DAU_THAU,  DOT_BO_SUNG,  MA_TPCP,(KHOI_LUONG_TP + KHOI_LUONG_THEM) AS  KHOI_LUONG_TP,KHOI_LUONG_THEM,  LOAI_TIEN, KY_HAN, " +
                    "  to_char(NGAY_TO_CHUC_PH,'dd/MM/yyyy') NGAY_TO_CHUC_PH,  to_char(NGAY_PH,'dd/MM/yyyy') NGAY_PH,  " +
                    "  to_char(NGAY_TT_TIEN_MUA,'dd/MM/yyyy') NGAY_TT_TIEN_MUA,  " +
                    "  to_char(NGAY_DAO_HAN,'dd/MM/yyyy') NGAY_DAO_HAN, PHUONG_THUC_THANH_TOAN,  HINH_THUC_DT, PTHUC_XACDINH_KQDT, SO_TK_NHAN, " +
                    "  SO_TBAO_GOI_THAU,  NGAY_TBAO_GOI_THAU, to_char(NGAY_TBAO_GOI_THAU,'dd/MM/yyyy') NGAY_TBAO_GOI_THAU,  " +
                    "  GHI_CHU,  TRANG_THAI,  NGUOI_TAO, to_char(NGAY_TAO,'dd/MM/yyyy') NGAY_TAO,  NGUOI_SUA_CUOI,to_char(NGAY_SUA_CUOI,'dd/MM/yyyy')  NGAY_SUA_CUOI, " +
                    "  NGUOI_PHE_DUYET, to_char(NGAY_PHE_DUYET,'dd/MM/yyyy') NGAY_PHE_DUYET,  LY_DO_TU_CHOI,to_char(NGAY_TT_LAI_1,'dd/MM/yyyy') NGAY_TT_LAI_1, to_char(NGAY_TT_LAI_2,'dd/MM/yyyy') NGAY_TT_LAI_2 ,MENH_GIA, KY_TRA_LAI " +
                    "  FROM TP_THONG_TIN_DAU_THAU WHERE DOT_DAU_THAU NOT IN (SELECT DOT_DT FROM TP_KQPH)");
            reval = executeSelectStatement(strSQL.toString(), null, CLASS_NAME_VO);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getListTTDTPaging(): " + ex.getMessage(), ex);
        }
        return reval;
    }

    public Collection getTTDTObject_DotDuThau() throws Exception {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT GUID,  DOT_DAU_THAU,  DOT_BO_SUNG,  MA_TPCP, (KHOI_LUONG_TP + KHOI_LUONG_THEM) AS KHOI_LUONG_TP,KHOI_LUONG_THEM,  LOAI_TIEN, KY_HAN, " +
                    "  to_char(NGAY_TO_CHUC_PH,'dd/MM/yyyy') NGAY_TO_CHUC_PH,  to_char(NGAY_PH,'dd/MM/yyyy') NGAY_PH,  " +
                    "  to_char(NGAY_TT_TIEN_MUA,'dd/MM/yyyy') NGAY_TT_TIEN_MUA,  " +
                    "  to_char(NGAY_DAO_HAN,'dd/MM/yyyy') NGAY_DAO_HAN, PHUONG_THUC_THANH_TOAN,  HINH_THUC_DT, PTHUC_XACDINH_KQDT, SO_TK_NHAN, " +
                    "  SO_TBAO_GOI_THAU,  NGAY_TBAO_GOI_THAU, to_char(NGAY_TBAO_GOI_THAU,'dd/MM/yyyy') NGAY_TBAO_GOI_THAU,  " +
                    "  GHI_CHU,  TRANG_THAI,  NGUOI_TAO, to_char(NGAY_TAO,'dd/MM/yyyy') NGAY_TAO,  NGUOI_SUA_CUOI,to_char(NGAY_SUA_CUOI,'dd/MM/yyyy')  NGAY_SUA_CUOI, " +
                    "  NGUOI_PHE_DUYET, to_char(NGAY_PHE_DUYET,'dd/MM/yyyy') NGAY_PHE_DUYET,  LY_DO_TU_CHOI,to_char(NGAY_TT_LAI_1,'dd/MM/yyyy') NGAY_TT_LAI_1, to_char(NGAY_TT_LAI_2,'dd/MM/yyyy') NGAY_TT_LAI_2 ,MENH_GIA, KY_TRA_LAI " +
                    "  FROM TP_THONG_TIN_DAU_THAU WHERE DOT_DAU_THAU NOT IN (SELECT DOT_DT FROM TP_KQ_DU_THAU)");
            reval = executeSelectStatement(strSQL.toString(), null, CLASS_NAME_VO);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getListTPKQ_DUTHAUPaging(): " + ex.getMessage(), ex);
        }
        return reval;
    }

    public Collection getListTTDT(Map<String, Object> map) throws Exception {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = new StringBuffer();
        Vector v_param = new Vector();
        try {
            strSQL.append("SELECT GUID,  DOT_DAU_THAU,  DOT_BO_SUNG,  MA_TPCP, (KHOI_LUONG_TP + KHOI_LUONG_THEM) AS KHOI_LUONG_TP, KHOI_LUONG_THEM, LOAI_TIEN, KY_HAN, " +
                    "  to_char(NGAY_TO_CHUC_PH,'dd/MM/yyyy') NGAY_TO_CHUC_PH,  to_char(NGAY_PH,'dd/MM/yyyy') NGAY_PH,  " +
                    "  to_char(NGAY_TT_TIEN_MUA,'dd/MM/yyyy') NGAY_TT_TIEN_MUA,  " +
                    "  to_char(NGAY_DAO_HAN,'dd/MM/yyyy') NGAY_DAO_HAN, PHUONG_THUC_THANH_TOAN,  HINH_THUC_DT, PTHUC_XACDINH_KQDT, SO_TK_NHAN, " +
                    "  SO_TBAO_GOI_THAU,  NGAY_TBAO_GOI_THAU, to_char(NGAY_TBAO_GOI_THAU,'dd/MM/yyyy') NGAY_TBAO_GOI_THAU,  " +
                    "  GHI_CHU,  TRANG_THAI,  NGUOI_TAO, to_char(NGAY_TAO,'dd/MM/yyyy') NGAY_TAO,  NGUOI_SUA_CUOI,to_char(NGAY_SUA_CUOI,'dd/MM/yyyy')  NGAY_SUA_CUOI, " +
                    "  NGUOI_PHE_DUYET, to_char(NGAY_PHE_DUYET,'dd/MM/yyyy') NGAY_PHE_DUYET,  LY_DO_TU_CHOI,to_char(NGAY_TT_LAI_1,'dd/MM/yyyy') NGAY_TT_LAI_1, to_char(NGAY_TT_LAI_2,'dd/MM/yyyy') NGAY_TT_LAI_2 ,MENH_GIA, KY_TRA_LAI " +
                    "  FROM TP_THONG_TIN_DAU_THAU ");

            Set<String> keySet = map.keySet();
            Iterator<String> keySetIterator = keySet.iterator();
            while (keySetIterator.hasNext()) {
                String key = keySetIterator.next();
                String value = map.get(key).toString();
                if (value != null && !STRING_EMPTY.equals(value)) {
                    strSQL2.append("and " + key + " = ? ");
                    v_param.add(new Parameter(value, true));
                }
            }
            if (strSQL2.toString().length() > 0) {
                strSQL.append(" WHERE 1 = 1 " + strSQL2.toString());
            }

            reval = executeSelectStatement(strSQL.toString(), v_param, CLASS_NAME_VO);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getListTTDT(): " + ex.getMessage(), ex);
        }
        return reval;
    }

}
