package com.seatech.tp.sotonghoptpcp.dao;

import com.seatech.framework.datamanager.AppDAO;

import com.seatech.framework.datamanager.DateParameter;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;
import com.seatech.framework.utils.StringUtil;
import com.seatech.tp.banletraiphieutw.vo.BanLeTraiPhieuTwVO;

import com.seatech.tp.sotonghoptpcp.vo.SoTongHopTpcpVO;

import java.sql.Connection;

import java.sql.ResultSet;

import java.util.Collection;
import java.util.Vector;

public class SoTongHopTpcpDao extends AppDAO {
    private Connection conn = null;
    private String CLASS_NAME_DAO =
        "com.seatech.tp.sotonghoptpcp.dao.SoTongHopTpcpDao";
    private String CLASS_NAME_VO =
        "com.seatech.tp.sotonghoptpcp.vo.SoTongHopTpcpVO";
    private static String STRING_EMPTY = "";

    public SoTongHopTpcpDao(Connection conn) {
        super();
        this.conn = conn;
    }

    public Collection getSoTongHopTpcpPaging(SoTongHopTpcpVO vo, Integer page,
                                             Integer count,
                                             Integer[] totalCount) throws Exception {
        Collection reval = null;
        String whereClause = "";
        Vector vParam = new Vector();
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT  TO_CHAR(NGAY_DT, 'dd/MM/yyyy') NGAY_DT, PHUONG_THUC_PH , MA_TP ,TO_CHAR(NGAY_PH, 'dd/MM/yyyy') NGAY_PH,TO_CHAR(NGAY_DAO_HAN, 'dd/MM/yyyy') NGAY_DAO_HAN,KY_HAN, LS_TRUNG_THAU, LS_DANH_NGHIA, THANH_VIEN_DAU_THAU, TEN_NGUOI_SO_HUU , KL_TRUNG_THAU  FROM TP_SOTONGHOPTPCP_VIEW");
            if (vo.getPhuong_thuc_tong_hop() != null &&
                !"".equals(vo.getPhuong_thuc_tong_hop())) {
                whereClause += " and  PHUONG_THUC_PH = ?";
                vParam.add(new Parameter(vo.getPhuong_thuc_tong_hop(), true));
            }
            if (vo.getNgay_phat_hanh_tu_ngay() != null &&
                !"".equals(vo.getNgay_phat_hanh_tu_ngay()) &&
                vo.getNgay_phat_hanh_den_ngay() != null &&
                !"".equals(vo.getNgay_phat_hanh_den_ngay())) {
                whereClause +=
                        " and trunc(NGAY_PH) >= ? and trunc(NGAY_PH)<= ?";

                vParam.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_phat_hanh_tu_ngay(),
                                                                     "dd/MM/yyyy"),
                                             true));
                vParam.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_phat_hanh_den_ngay(),
                                                                     "dd/MM/yyyy"),
                                             true));
            }
            if (whereClause != null && !whereClause.equalsIgnoreCase("")) {
                strSQL.append(" WHERE 1=1 " + whereClause);
            }
            reval =
                    executeSelectWithPaging(conn, strSQL.toString(), vParam, CLASS_NAME_VO,
                                            page, count, totalCount);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO +
                                   ".getListBanLeTwPaging(): " +
                                   ex.getMessage(), ex);
        }
        return reval;
    }


    public ResultSet getSoTongHopTpcpList(SoTongHopTpcpVO vo) throws Exception {
        ResultSet reval = null;
        String whereClause = "";
        Vector vParam = new Vector();
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT  TO_CHAR(NGAY_DT, 'dd/MM/yyyy') NGAY_DT, PHUONG_THUC_PH , MA_TP ,TO_CHAR(NGAY_PH, 'dd/MM/yyyy') NGAY_PH,TO_CHAR(NGAY_DAO_HAN, 'dd/MM/yyyy') NGAY_DAO_HAN,KY_HAN, LS_TRUNG_THAU, LS_DANH_NGHIA, THANH_VIEN_DAU_THAU, TEN_NGUOI_SO_HUU , KL_TRUNG_THAU  FROM TP_SOTONGHOPTPCP_VIEW");
            if (vo.getPhuong_thuc_tong_hop() != null &&
                !"".equals(vo.getPhuong_thuc_tong_hop())) {
                whereClause += " and  PHUONG_THUC_PH = ?";
                vParam.add(new Parameter(vo.getPhuong_thuc_tong_hop(), true));
            }
            if (vo.getNgay_phat_hanh_tu_ngay() != null &&
                !"".equals(vo.getNgay_phat_hanh_tu_ngay()) &&
                vo.getNgay_phat_hanh_den_ngay() != null &&
                !"".equals(vo.getNgay_phat_hanh_den_ngay())) {
                whereClause +=
                        " and trunc(NGAY_PH) >= ? and trunc(NGAY_PH)<= ?";

                vParam.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_phat_hanh_tu_ngay(),
                                                                     "dd/MM/yyyy"),
                                             true));
                vParam.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_phat_hanh_den_ngay(),
                                                                     "dd/MM/yyyy"),
                                             true));
            }
            if (whereClause != null && !whereClause.equalsIgnoreCase("")) {
                strSQL.append(" WHERE 1=1 " + whereClause);
            }
            reval = executeSelectStatement(strSQL.toString(), vParam, conn);

        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getUserList(): " +
                                   ex.getMessage(), ex);
        }

        return reval;
    }
}
