package com.seatech.tp.tttmua.dao;

import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.DateParameter;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;
import com.seatech.framework.utils.StringUtil;
import com.seatech.tp.tttmua.vo.QLyTToanTienMuaVO;

import com.seatech.tp.tttmua.vo.TTinDViSoHuuVO;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

public class QLyTToanTienMuaDAO extends AppDAO {
    private String CLASS_NAME_DAO = "com.seatech.tp.tttmua.action.dao.QLyTToanTienMuaDAO";
    private String CLASS_NAME_DVI_SO_HUU_VO = "com.seatech.tp.tttmua.vo.TTinDViSoHuuVO";
    private String CLASS_NAME_TTOAN_TMUA_VO = "com.seatech.tp.tttmua.vo.QLyTToanTienMuaVO";

    public QLyTToanTienMuaDAO(Connection conn) {
        this.conn = conn;
    }

    public Collection traCuuTTinTToan(QLyTToanTienMuaVO vo, Integer page, Integer count, Integer[] totalCount) throws Exception {
        Collection reval = null;
        Vector vParam = new Vector();
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT tt.DOT_PH, to_char(tt.NGAY_PH,'DD/MM/YYYY') NGAY_PH,  tt.MA_TPCP,  tt.MA_NGUOI_SO_HUU,  tt.TEN_NGUOI_SO_HUU,  tt.tong_tien_phai_tt, nvl(tm.tong_tien_da_tt,0) tong_tien_da_tt, nvl(tm.nop_cham,1) nop_cham, nvl(bk.tong_tien_da_lap_bke,0) tong_tien_da_lap_bke, loai_tien from (\n" +
                    "SELECT ph.DOT_DT DOT_PH,  ph.NGAY_PH,  ph.MA_TPCP,  ct.MA_NGUOI_SO_HUU,  ct.TEN_NGUOI_SO_HUU,  SUM(ct.TIEN_TT_MUA) tong_tien_phai_tt\n" +
                    "FROM TP_KQPH ph INNER JOIN TP_KQPH_CTIET_SO_HUU ct ON ph.GUID          = ct.TP_KQ_PH_ID\n" +
                    "WHERE ph.TRANG_THAI = '02' GROUP BY ph.DOT_DT,  ph.NGAY_PH,  ph.MA_TPCP,  ct.MA_NGUOI_SO_HUU,  ct.TEN_NGUOI_SO_HUU\n" +
                    "UNION ALL\n" +
                    "SELECT ph.DOT_PH DOT_PH,  ph.NGAY_PH,  ph.MA_TPCP,  ct.MA_DVI_SO_HUU MA_NGUOI_SO_HUU,  ct.DVI_SO_HUU TEN_NGUOI_SO_HUU,  SUM(ct.SO_TIEN_TT) tong_tien_phai_tt\n" +
                    "FROM TP_KQPH_BAN_LE ph INNER JOIN TP_KQPH_BAN_LE_CTIET ct ON ph.GUID            = ct.KQPH_BAN_LE_ID\n" +
                    "WHERE ph.TRANG_THAI   = '02' GROUP BY ph.DOT_PH, ph.NGAY_PH,  ph.MA_TPCP,  ct.MA_DVI_SO_HUU,  ct.DVI_SO_HUU\n" +
                    "UNION ALL\n" +
                    "SELECT ph.DOT_PH DOT_PH,  ph.NGAY_PH,  ph.MA_TPCP,  ct.MA_CHU_SO_HUU MA_NGUOI_SO_HUU,  ct.TEN_CHU_SO_HUU TEN_NGUOI_SO_HUU,  SUM(ct.TIEN_TT_MUA) tong_tien_phai_tt\n" +
                    "FROM TP_KQPH_TIN_PHIEU ph INNER JOIN TP_KQPH_TIN_PHIEU_CTIET_SO_HUU ct ON ph.GUID          = ct.TP_KQPH_TIN_PHIEU_ID\n" +
                    "WHERE ph.TRANG_THAI = '02' GROUP BY ph.DOT_PH,  ph.NGAY_PH,  ph.MA_TPCP,  ct.MA_CHU_SO_HUU,  ct.TEN_CHU_SO_HUU\n" +
                    "UNION ALL\n" +
                    "SELECT ph.SO_HD DOT_PH,  ph.NGAY_PH,  ph.MA_TP MA_TPCP,  ph.MA_DVI_DAU_TU MA_NGUOI_SO_HUU,  NVL(ph.TEN_DVI_DAU_TU,'Ngân hàng nhà nước Việt Nam') TEN_NGUOI_SO_HUU,\n" +
                    "  SUM(ph.GIA_BAN) tong_tien_phai_tt FROM TP_HD_BAN_TIN_PHIEU ph WHERE ph.TRANG_THAI = '02'\n" +
                    "GROUP BY ph.SO_HD,  ph.NGAY_PH,  ph.MA_TP,  ph.MA_DVI_DAU_TU,  ph.TEN_DVI_DAU_TU) tt\n" +
                    "left join (SELECT a.NGAY_PH,a.DOT_PH, a.MA_TPCP,a.MA_NGUOI_SO_HUU, SUM(a.so_tien) tong_tien_da_tt, a.NOP_CHAM, nvl(a.loai_tien,'VND') loai_tien\n" +
                    "FROM TP_TT_TIEN_MUA a\n" +
                    "GROUP BY a.NGAY_PH,a.DOT_PH, a.MA_TPCP,a.MA_NGUOI_SO_HUU,a.NOP_CHAM,a.loai_tien) tm\n" +
                    "on tt.DOT_PH = tm.DOT_PH and trunc(tt.NGAY_PH) = TRUNC(tm.NGAY_PH) and tt.MA_TPCP = tm.MA_TPCP and tt.MA_NGUOI_SO_HUU = tm.MA_NGUOI_SO_HUU\n" +
                    "LEFT JOIN (SELECT e.NGAY_PH, e.DOT_PH,e.MA_TPCP,  f.MA_TRAI_CHU,  SUM(f.SO_TIEN_PHAI_TT) tong_tien_da_lap_bke\n" +
                    "FROM TP_BKE_CHUYEN_TIEN e INNER JOIN TP_BKE_CHUYEN_TIEN_CTIET f\n" +
                    "ON e.GUID              = f.BKE_CHUYEN_TIEN_ID\n" +
                    "GROUP BY e.NGAY_PH, e.DOT_PH,e.MA_TPCP,  f.MA_TRAI_CHU) bk\n" +
                    "on tt.DOT_PH = bk.DOT_PH and trunc(tt.NGAY_PH) = TRUNC(bk.NGAY_PH) and tt.MA_TPCP = bk.MA_TPCP and tt.MA_NGUOI_SO_HUU = bk.MA_TRAI_CHU where 1 = 1");
            if (vo.getMa_tpcp() != null && !vo.getMa_tpcp().equals("")) {
                strSQL.append("AND tt.ma_tpcp = ?");
                vParam.add(new Parameter(vo.getMa_tpcp(), true));
            }
            if (vo.getNop_cham() != null && !vo.getNop_cham().equals("")) {
                if (vo.getNop_cham().equalsIgnoreCase("0")) {
                    strSQL.append("AND nvl(tm.tong_tien_da_tt,0) > 0");
                } else if (vo.getNop_cham().equalsIgnoreCase("1")) {
                    strSQL.append("AND nvl(tm.tong_tien_da_tt,0) = 0");
                } else if (vo.getNop_cham().equalsIgnoreCase("2")) {
                    strSQL.append("AND nvl(tm.tong_tien_da_tt,0) > 0 and nvl(tm.nop_cham,1) = 0");
                }
            }
            if (vo.getTu_ngay_ph() != null && !vo.getTu_ngay_ph().equals("")) {
                strSQL.append("AND TRUNC(tt.NGAY_PH) >= ?");
                vParam.add(new DateParameter(StringUtil.StringToDate(vo.getTu_ngay_ph(), "dd/MM/yyyy"), true));
            }
            if (vo.getDen_ngay_ph() != null && !vo.getDen_ngay_ph().equals("")) {
                strSQL.append("AND TRUNC(tt.NGAY_PH) <= ?");
                vParam.add(new DateParameter(StringUtil.StringToDate(vo.getDen_ngay_ph(), "dd/MM/yyyy"), true));
            }
            if (vo.getMa_nguoi_so_huu() != null && !vo.getMa_nguoi_so_huu().equals("")) {
                strSQL.append("AND upper(tt.TEN_NGUOI_SO_HUU) like '%" + vo.getMa_nguoi_so_huu().toUpperCase() + "%'");
            }

            reval = executeSelectWithPaging(strSQL.toString(), vParam, CLASS_NAME_DVI_SO_HUU_VO, page, count, totalCount);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getgetListDonViSoHuuThanhToan(): " + ex.getMessage(), ex);
        }
        return reval;
    }

    public Collection getListDonViSoHuuThanhToan(QLyTToanTienMuaVO vo) throws Exception {
        Collection reval = new ArrayList();
        Vector vParam = new Vector();
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL1 = null;
        try {
            if (vo.getPhuong_thuc_ph() != null) {
                if (vo.getPhuong_thuc_ph().equalsIgnoreCase("TD")) {
                    strSQL1 = new StringBuffer();
                    strSQL1.append("SELECT ph.DOT_DT DOT_PH,  ph.NGAY_PH,  ph.MA_TPCP,  ct.MA_NGUOI_SO_HUU,\n" +
                            "  ct.TEN_NGUOI_SO_HUU,  SUM(ct.TIEN_TT_MUA) tong_tien_phai_tt\n" +
                            "FROM TP_KQPH ph INNER JOIN TP_KQPH_CTIET_SO_HUU ct\n" +
                            "ON ph.GUID = ct.TP_KQ_PH_ID\n" +
                            "WHERE ph.TRANG_THAI = '02' AND ph.MA_TPCP = ? AND TRUNC(ph.NGAY_PH) = ? \n" +
                            "GROUP BY ph.DOT_DT,  ph.NGAY_PH,  ph.MA_TPCP,  ct.MA_NGUOI_SO_HUU,  ct.TEN_NGUOI_SO_HUU");
                } else if (vo.getPhuong_thuc_ph().equalsIgnoreCase("TL")) {
                    strSQL1 = new StringBuffer();
                    strSQL1.append("SELECT ph.DOT_PH DOT_PH,  ph.NGAY_PH,  ph.MA_TPCP,  ct.MA_DVI_SO_HUU MA_NGUOI_SO_HUU,\n" +
                            "                            ct.DVI_SO_HUU TEN_NGUOI_SO_HUU,  SUM(ct.SO_TIEN_TT) tong_tien_phai_tt\n" +
                            "                          FROM TP_KQPH_BAN_LE ph INNER JOIN TP_KQPH_BAN_LE_CTIET ct\n" +
                            "                          ON ph.GUID = ct.KQPH_BAN_LE_ID\n" +
                            "                            WHERE ph.TRANG_THAI = '02' AND ph.MA_TPCP = ? AND TRUNC(ph.NGAY_PH) = ? \n" +
                            "                            GROUP BY ph.DOT_PH,  ph.NGAY_PH,  ph.MA_TPCP,  ct.MA_DVI_SO_HUU,  ct.DVI_SO_HUU");
                } else if (vo.getPhuong_thuc_ph().equalsIgnoreCase("TPKB")) {
                    strSQL1 = new StringBuffer();
                    strSQL1.append("SELECT ph.DOT_PH DOT_PH,  ph.NGAY_PH,  ph.MA_TPCP,  ct.MA_CHU_SO_HUU MA_NGUOI_SO_HUU,\n" +
                            "                          ct.TEN_CHU_SO_HUU TEN_NGUOI_SO_HUU,  SUM(ct.TIEN_TT_MUA) tong_tien_phai_tt\n" +
                            "                          FROM TP_KQPH_TIN_PHIEU ph INNER JOIN TP_KQPH_TIN_PHIEU_CTIET_SO_HUU ct\n" +
                            "                          ON ph.GUID = ct.TP_KQPH_TIN_PHIEU_ID\n" +
                            "                            WHERE ph.TRANG_THAI = '02' AND ph.MA_TPCP = ? AND TRUNC(ph.NGAY_PH) = ? \n" +
                            "                            GROUP BY ph.DOT_PH,  ph.NGAY_PH,  ph.MA_TPCP,  ct.MA_CHU_SO_HUU,  ct.TEN_CHU_SO_HUU");
                } else if (vo.getPhuong_thuc_ph().equalsIgnoreCase("PHTT")) {
                    strSQL1 = new StringBuffer();
                    strSQL1.append("SELECT ph.SO_HD DOT_PH,  ph.NGAY_PH,  ph.MA_TP MA_TPCP,  ph.MA_DVI_DAU_TU MA_NGUOI_SO_HUU,\n" +
                            "                          nvl(ph.TEN_DVI_DAU_TU,'Ngân hàng nhà nước Việt Nam') TEN_NGUOI_SO_HUU,  SUM(ph.GIA_BAN) tong_tien_phai_tt\n" +
                            "                          FROM TP_HD_BAN_TIN_PHIEU ph \n" +
                            "                            WHERE ph.TRANG_THAI = '02' AND ph.MA_TP = ? AND TRUNC(ph.NGAY_PH) = ? \n" +
                            "                            GROUP BY ph.SO_HD, ph.NGAY_PH,  ph.MA_TP,  ph.MA_DVI_DAU_TU,  ph.TEN_DVI_DAU_TU");
                } else
                    return reval;
                strSQL.append("SELECT tt.*, nvl(tm.tong_tien_da_tt,0) tong_tien_da_tt, nvl(bk.tong_tien_da_lap_bke,0) tong_tien_da_lap_bke from (\n" +
                        strSQL1.toString() + ") tt\n" +
                        "left join (SELECT a.NGAY_PH,a.DOT_PH, a.MA_TPCP,a.MA_NGUOI_SO_HUU, SUM(a.so_tien) tong_tien_da_tt\n" +
                        "FROM TP_TT_TIEN_MUA a\n" +
                        "WHERE a.MA_TPCP = ? AND TRUNC(a.NGAY_PH) = ? \n" +
                        "GROUP BY a.NGAY_PH,a.DOT_PH, a.MA_TPCP,a.MA_NGUOI_SO_HUU) tm\n" +
                        "on tt.DOT_PH = tm.DOT_PH and trunc(tt.NGAY_PH) = TRUNC(tm.NGAY_PH) and tt.MA_TPCP = tm.MA_TPCP and tt.MA_NGUOI_SO_HUU = tm.MA_NGUOI_SO_HUU\n" +
                        "LEFT JOIN (SELECT e.NGAY_PH, e.DOT_PH,e.MA_TPCP,  f.MA_TRAI_CHU,  SUM(f.SO_TIEN_PHAI_TT) tong_tien_da_lap_bke\n" +
                        "FROM TP_BKE_CHUYEN_TIEN e INNER JOIN TP_BKE_CHUYEN_TIEN_CTIET f\n" +
                        "ON e.GUID              = f.BKE_CHUYEN_TIEN_ID\n" +
                        "WHERE  e.MA_TPCP = ? AND TRUNC(e.NGAY_PH) = ? \n" +
                        "GROUP BY e.NGAY_PH, e.DOT_PH,e.MA_TPCP,  f.MA_TRAI_CHU) bk\n" +
                        "on tt.DOT_PH = bk.DOT_PH and trunc(tt.NGAY_PH) = TRUNC(bk.NGAY_PH) and tt.MA_TPCP = bk.MA_TPCP and tt.MA_NGUOI_SO_HUU = bk.MA_TRAI_CHU");
                vParam.add(new Parameter(vo.getMa_tpcp(), true));
                vParam.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_ph(), "dd/MM/yyyy"), true));
                vParam.add(new Parameter(vo.getMa_tpcp(), true));
                vParam.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_ph(), "dd/MM/yyyy"), true));
                vParam.add(new Parameter(vo.getMa_tpcp(), true));
                vParam.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_ph(), "dd/MM/yyyy"), true));
                reval = executeSelectStatement(strSQL.toString(), vParam, CLASS_NAME_DVI_SO_HUU_VO, conn);
            }
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getgetListDonViSoHuuThanhToan(): " + ex.getMessage(), ex);
        }
        return reval;
    }

    public Collection getListDonViSoHuuThanhToan(QLyTToanTienMuaVO vo, Integer page, Integer count, Integer[] totalCount) throws Exception {
        Collection reval = null;
        Vector vParam = new Vector();
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL1 = null;
        try {
            if (vo.getPhuong_thuc_ph() != null) {
                if (vo.getPhuong_thuc_ph().equalsIgnoreCase("TD")) {
                    strSQL1 = new StringBuffer();
                    strSQL1.append("SELECT ph.DOT_DT DOT_PH,  ph.NGAY_PH,  ph.MA_TPCP,  ct.MA_NGUOI_SO_HUU,\n" +
                            "  ct.TEN_NGUOI_SO_HUU,  SUM(ct.TIEN_TT_MUA) tong_tien_phai_tt\n" +
                            "FROM TP_KQPH ph INNER JOIN TP_KQPH_CTIET_SO_HUU ct\n" +
                            "ON ph.GUID = ct.TP_KQ_PH_ID\n" +
                            "WHERE ph.TRANG_THAI = '02' AND ph.MA_TPCP = ? AND TRUNC(ph.NGAY_PH) = ? \n" +
                            "GROUP BY ph.DOT_DT,  ph.NGAY_PH,  ph.MA_TPCP,  ct.MA_NGUOI_SO_HUU,  ct.TEN_NGUOI_SO_HUU");
                } else {

                }
                strSQL.append("SELECT tt.*, nvl(tm.tong_tien_da_tt,0) tong_tien_da_tt, nvl(bk.tong_tien_da_lap_bke,0) tong_tien_da_lap_bke from (\n" +
                        strSQL1.toString() + ") tt\n" +
                        "left join (SELECT a.NGAY_PH,a.DOT_PH, a.MA_TPCP,a.MA_NGUOI_SO_HUU, SUM(a.so_tien) tong_tien_da_tt\n" +
                        "FROM TP_TT_TIEN_MUA a\n" +
                        "WHERE a.MA_TPCP = ? AND TRUNC(a.NGAY_PH) = ? \n" +
                        "GROUP BY a.NGAY_PH,a.DOT_PH, a.MA_TPCP,a.MA_NGUOI_SO_HUU) tm\n" +
                        "on tt.DOT_PH = tm.DOT_PH and trunc(tt.NGAY_PH) = TRUNC(tm.NGAY_PH) and tt.MA_TPCP = tm.MA_TPCP and tt.MA_NGUOI_SO_HUU = tm.MA_NGUOI_SO_HUU\n" +
                        "LEFT JOIN (SELECT e.NGAY_PH, e.DOT_PH,e.MA_TPCP,  f.MA_TRAI_CHU,  SUM(f.SO_TIEN_PHAI_TT) tong_tien_da_lap_bke\n" +
                        "FROM TP_BKE_CHUYEN_TIEN e INNER JOIN TP_BKE_CHUYEN_TIEN_CTIET f\n" +
                        "ON e.GUID              = f.BKE_CHUYEN_TIEN_ID\n" +
                        "WHERE  e.MA_TPCP = ? AND TRUNC(e.NGAY_PH) = ? \n" +
                        "GROUP BY e.NGAY_PH, e.DOT_PH,e.MA_TPCP,  f.MA_TRAI_CHU) bk\n" +
                        "on tt.DOT_PH = bk.DOT_PH and trunc(tt.NGAY_PH) = TRUNC(bk.NGAY_PH) and tt.MA_TPCP = bk.MA_TPCP and tt.MA_NGUOI_SO_HUU = bk.MA_TRAI_CHU");
                vParam.add(new Parameter(vo.getMa_tpcp(), true));
                vParam.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_ph(), "dd/MM/yyyy"), true));
                vParam.add(new Parameter(vo.getMa_tpcp(), true));
                vParam.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_ph(), "dd/MM/yyyy"), true));
                vParam.add(new Parameter(vo.getMa_tpcp(), true));
                vParam.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_ph(), "dd/MM/yyyy"), true));
                reval = executeSelectWithPaging(strSQL.toString(), vParam, CLASS_NAME_DVI_SO_HUU_VO, page, count, totalCount);
            }
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getgetListDonViSoHuuThanhToan(): " + ex.getMessage(), ex);
        }
        return reval;
    }

    public Collection getTTinTrungThauDViSoHuu(QLyTToanTienMuaVO vo) throws Exception {
        Collection reval = null;
        Vector vParam = new Vector();
        StringBuffer strSQL = new StringBuffer();
        try {
            if (vo.getPhuong_thuc_ph() != null) {
                if (vo.getPhuong_thuc_ph().equalsIgnoreCase("TD")) {
                    strSQL.append("select a.GUID, a.MA_TPCP, a.TIEN_TT_MUA, a.MA_NGUOI_SO_HUU, nvl(a.TRANG_THAI_TT,'00') TRANG_THAI_TT \n" +
                            "from TP_KQPH_CTIET_SO_HUU a INNER JOIN TP_KQPH_CTIET_TPCP b on a.TP_KQ_PH_ID = b.TP_KQ_PH_ID INNER JOIN TP_KQPH ph on a.TP_KQ_PH_ID = ph.GUID\n" +
                            "where ph.TRANG_THAI = '02' and trunc(ph.NGAY_PH) = ? and a.MA_NGUOI_SO_HUU = ? and a.MA_TPCP = ? ");
                } else if (vo.getPhuong_thuc_ph().equalsIgnoreCase("TL")) {
                    strSQL.append(" select c.GUID,d.MA_TPCP,c.SO_TIEN_TT TIEN_TT_MUA,c.MA_DVI_SO_HUU MA_NGUOI_SO_HUU,nvl(c.TRANG_THAI_TT,'00') TRANG_THAI_TT,'TP_BANLE' AS dthau_type " +
                            " FROM TP_KQPH_BAN_LE_CTIET c, TP_KQPH_BAN_LE d where d.guid = c.KQPH_BAN_LE_ID and d.trang_thai = '02' and TRUNC(d.NGAY_PH) = ? and c.MA_DVI_SO_HUU = ? and d.MA_TPCP = ?");
                } else if (vo.getPhuong_thuc_ph().equalsIgnoreCase("TPKB")) {
                    strSQL.append(" select tp.GUID, tp.MA_TPCP,tp.TIEN_TT_MUA, tp.MA_CHU_SO_HUU MA_NGUOI_SO_HUU, nvl(TP.TRANG_THAI_TT,'00') TRANG_THAI_TT, 'TPHIEU_DTHAU' AS dthau_type\n" +
                            "from TP_KQPH_TIN_PHIEU_CTIET_SO_HUU tp INNER JOIN TP_KQPH_TIN_PHIEU tp2 on tp.TP_KQPH_TIN_PHIEU_ID = tp2.GUID INNER join TP_KQPH_TIN_PHIEU_CTIET_TPCP tp3 on tp2.GUID = tp3.TP_KQPH_TIN_PHIEU_ID " +
                            " where tp2.TRANG_THAI = '02' and trunc(tp3.NGAY_PH) = ? and tp.MA_CHU_SO_HUU = ? and tp.MA_TPCP = ?");
                } else if (vo.getPhuong_thuc_ph().equalsIgnoreCase("PHTT")) {
                    strSQL.append("select hd.GUID, hd.MA_TP MA_TPCP,hd.GIA_BAN TIEN_TT_MUA, hd.MA_DVI_DAU_TU MA_CHU_SO_HUU, nvl(hd.TRANG_THAI_TT,'00') TRANG_THAI_TT,'TPHIEU_HD' AS dthau_type    from \n" +
                            "TP_HD_BAN_TIN_PHIEU hd where hd.TRANG_THAI = '02' and hd.NGAY_PH = ? and hd.MA_DVI_DAU_TU = ? and hd.MA_TP = ? ");
                }
                vParam.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_ph(), "dd/MM/yyyy"), true));
                vParam.add(new Parameter(vo.getMa_nguoi_so_huu(), true));
                vParam.add(new Parameter(vo.getMa_tpcp(), true));
                reval = executeSelectStatement(strSQL.toString(), vParam, CLASS_NAME_DVI_SO_HUU_VO, conn);
            }
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getTTinTrungThauDViSoHuu(): " + ex.getMessage(), ex);
        }
        return reval;
    }

    public Collection getTTinThanhToanDViSoHuu(QLyTToanTienMuaVO vo) throws Exception {
        Collection reval = null;
        Vector vParam = new Vector();
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append(" SELECT GUID,  DOT_PH, to_char(NGAY_PH,'dd/MM/yyyy') NGAY_PH,  MA_TPCP,  SO_GD,  SO_TIEN, decode(to_char(NGAY_TT,'HH24:MI'),'00:00',to_char(NGAY_TT,'DD/MM/YYYY'),to_char(NGAY_TT,'DD/MM/YYYY HH24:MI')) NGAY_TT,  MA_NGUOI_SO_HUU, TRANG_THAI_TT, NOP_CHAM ");
            strSQL.append(" FROM TP_TT_TIEN_MUA ");
            strSQL.append(" WHERE TRUNC(NGAY_PH) = ? and MA_NGUOI_SO_HUU = ? AND DOT_PH = ? AND MA_TPCP = ? ");
            vParam.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_ph(), "dd/MM/yyyy"), true));
            vParam.add(new Parameter(vo.getMa_nguoi_so_huu(), true));
            vParam.add(new Parameter(vo.getDot_ph(), true));
            vParam.add(new Parameter(vo.getMa_tpcp(), true));
            strSQL.append(" ORDER BY GUID, SO_GD, NGAY_TT");
            reval = executeSelectStatement(strSQL.toString(), vParam, CLASS_NAME_TTOAN_TMUA_VO, conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getTTinThanhToanDViSoHuu(): " + ex.getMessage(), ex);
        }
        return reval;
    }

    public Long update(Collection lstTTinTToan, QLyTToanTienMuaVO vo, String lstMaDviCu) throws Exception {
        Vector v_param = null;
        Long lID = new Long("0");
        try {
            String sql = "";
            //insert list ctiet
            //delete first
            StringBuffer strSQL = new StringBuffer();
            strSQL.append("DELETE TP_TT_TIEN_MUA WHERE DOT_PH = ? AND TRUNC(NGAY_PH) =  ? AND MA_TPCP = ? and TRANG_THAI_TT = '00'");
            if (lstMaDviCu != "") {
                strSQL.append(" and MA_NGUOI_SO_HUU in (" + lstMaDviCu + ")");
            }
            v_param = new Vector();
            v_param.add(new Parameter(vo.getDot_ph(), true));
            v_param.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_ph(), "dd/MM/yyyy"), true));
            v_param.add(new Parameter(vo.getMa_tpcp(), true));
            executeStatement(strSQL.toString(), v_param, conn);
            //update trang thai thanh toan
            if (vo.getPhuong_thuc_ph() != null) {
                if (vo.getPhuong_thuc_ph().equalsIgnoreCase("TD")) {
                    strSQL = new StringBuffer();
                    strSQL.append("update TP_KQPH_CTIET_SO_HUU set TRANG_THAI_TT = '00' where nvl(TRANG_THAI_TT,'00') != '03' and TP_KQ_PH_ID in (select guid from TP_KQPH ph where ph.dot_dt = ? and TRUNC(ph.NGAY_PH) = ? and ph.MA_TPCP = ?)");
                    if (lstMaDviCu != "") {
                        strSQL.append(" and MA_NGUOI_SO_HUU in (" + lstMaDviCu + ")");
                    }
                } else if (vo.getPhuong_thuc_ph().equalsIgnoreCase("TL")) {
                    strSQL = new StringBuffer();
                    strSQL.append("update TP_KQPH_BAN_LE_CTIET set TRANG_THAI_TT = '00' where nvl(TRANG_THAI_TT,'00') != '03' and KQPH_BAN_LE_ID in (select guid from TP_KQPH_BAN_LE ph where ph.DOT_PH = ? and TRUNC(ph.NGAY_PH) = ? and ph.MA_TPCP = ?)");
                    if (lstMaDviCu != "") {
                        strSQL.append(" and MA_DVI_SO_HUU in (" + lstMaDviCu + ")");
                    }
                } else if (vo.getPhuong_thuc_ph().equalsIgnoreCase("TPKB")) {
                    strSQL = new StringBuffer();
                    strSQL.append("update TP_KQPH_TIN_PHIEU_CTIET_SO_HUU set TRANG_THAI_TT = '00' where nvl(TRANG_THAI_TT,'00') != '03' and TP_KQPH_TIN_PHIEU_ID in (select guid from TP_KQPH_TIN_PHIEU ph where ph.DOT_PH = ? and TRUNC(ph.NGAY_PH) = ? and ph.MA_TPCP = ?)");
                    if (lstMaDviCu != "") {
                        strSQL.append(" and MA_CHU_SO_HUU in (" + lstMaDviCu + ")");
                    }
                } else if (vo.getPhuong_thuc_ph().equalsIgnoreCase("PHTT")) {
                    strSQL = new StringBuffer();
                    strSQL.append("update TP_HD_BAN_TIN_PHIEU set TRANG_THAI_TT = '00' where nvl(TRANG_THAI_TT,'00') != '03' and SO_HD = ? and TRUNC(NGAY_PH) = ? and MA_TP = ?");
                    if (lstMaDviCu != "") {
                        strSQL.append(" and MA_DVI_DAU_TU in (" + lstMaDviCu + ")");
                    }
                }
                executeStatement(strSQL.toString(), v_param, conn);
            }

            QLyTToanTienMuaVO ctietVO = null;
            Iterator ito = lstTTinTToan.iterator();
            while (ito.hasNext()) {
                v_param = new Vector();
                ctietVO = (QLyTToanTienMuaVO)ito.next();
                ctietVO.setGuid(String.valueOf(getSeqNextValue("TP_TT_TIEN_MUA_SEQ", conn)));
                sql = generateSQLInsert(ctietVO, v_param, "TP_TT_TIEN_MUA", conn);
                executeStatement(sql, v_param, conn);
                //update trang thai thanh toan
                vo.setMa_nguoi_so_huu(ctietVO.getMa_nguoi_so_huu());
                updateTTTTForDviSoHuu(vo);

            }

            return lID;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Long update(Collection lstTTinTToan, Collection lstTToanDvi, QLyTToanTienMuaVO vo) throws Exception {
        Vector v_param = null;
        Long lID = new Long("0");
        try {
            String sql = "";
            //insert list ctiet
            //delete first
            StringBuffer strSQL = new StringBuffer();
            strSQL.append("DELETE TP_TT_TIEN_MUA WHERE MA_NGUOI_SO_HUU = ? and DOT_PH = ? AND TRUNC(NGAY_PH) =  ? AND MA_TPCP = ?");
            v_param = new Vector();
            v_param.add(new Parameter(vo.getMa_nguoi_so_huu(), true));
            v_param.add(new Parameter(vo.getDot_ph(), true));
            v_param.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_ph(), "dd/MM/yyyy"), true));
            v_param.add(new Parameter(vo.getMa_tpcp(), true));
            executeStatement(strSQL.toString(), v_param, conn);
            //update trang thai thanh toan all ban ghi ve trang thai chưa thanh toán
            if (vo.getPhuong_thuc_ph() != null) {
                if (vo.getPhuong_thuc_ph().equalsIgnoreCase("TD")) {
                    strSQL = new StringBuffer();
                    strSQL.append("update TP_KQPH_CTIET_SO_HUU set TRANG_THAI_TT = '00' where MA_NGUOI_SO_HUU = ? AND TP_KQ_PH_ID in (select guid from TP_KQPH ph where ph.dot_dt = ? and TRUNC(ph.NGAY_PH) = ? and ph.MA_TPCP = ?)");
                } else if (vo.getPhuong_thuc_ph().equalsIgnoreCase("TL")) {
                    strSQL = new StringBuffer();
                    strSQL.append("update TP_KQPH_BAN_LE_CTIET set TRANG_THAI_TT = '00' where MA_DVI_SO_HUU = ? AND KQPH_BAN_LE_ID in (select guid from TP_KQPH_BAN_LE ph where ph.DOT_PH = ? and TRUNC(ph.NGAY_PH) = ? and ph.MA_TPCP = ?)");
                } else if (vo.getPhuong_thuc_ph().equalsIgnoreCase("TPKB")) {
                    strSQL = new StringBuffer();
                    strSQL.append("update TP_KQPH_TIN_PHIEU_CTIET_SO_HUU set TRANG_THAI_TT = '00' where MA_CHU_SO_HUU = ? and TP_KQPH_TIN_PHIEU_ID in (select guid from TP_KQPH_TIN_PHIEU ph where ph.DOT_PH = ? and TRUNC(ph.NGAY_PH) = ? and ph.MA_TPCP = ?)");
                } else if (vo.getPhuong_thuc_ph().equalsIgnoreCase("PHTT")) {
                    strSQL = new StringBuffer();
                    strSQL.append("update TP_HD_BAN_TIN_PHIEU set TRANG_THAI_TT = '00' where MA_DVI_DAU_TU = ? and SO_HD = ? and TRUNC(NGAY_PH) = ? and MA_TP = ?");
                }
                executeStatement(strSQL.toString(), v_param, conn);
            }
            QLyTToanTienMuaVO ctietVO = null;
            Iterator ito = lstTTinTToan.iterator();
            while (ito.hasNext()) {
                v_param = new Vector();
                ctietVO = (QLyTToanTienMuaVO)ito.next();
                ctietVO.setGuid(String.valueOf(getSeqNextValue("TP_TT_TIEN_MUA_SEQ", conn)));
                sql = generateSQLInsert(ctietVO, v_param, "TP_TT_TIEN_MUA", conn);
                executeStatement(sql, v_param, conn);
            }
            //update thong tin thanh toan don vi so huu o cac bang chi tiet thanh 02: da thanh toan
            if (!lstTToanDvi.isEmpty()) {
                ito = lstTToanDvi.iterator();
                while (ito.hasNext()) {
                    v_param = new Vector();
                    TTinDViSoHuuVO voTToanDvi = (TTinDViSoHuuVO)ito.next();
                    update(voTToanDvi, vo.getPhuong_thuc_ph());
                }
            }
            return lID;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void updateTTTTForDviSoHuu(QLyTToanTienMuaVO vo) throws Exception {
        Vector v_param = new Vector();
        Long lID = new Long("0");
        StringBuffer strSQL = new StringBuffer();
        try {
            if (vo.getPhuong_thuc_ph() != null) {
                if (vo.getPhuong_thuc_ph().equalsIgnoreCase("TD")) {
                    strSQL = new StringBuffer();
                    strSQL.append("update TP_KQPH_CTIET_SO_HUU set TRANG_THAI_TT = '02' where MA_NGUOI_SO_HUU = ? AND TP_KQ_PH_ID in (select guid from TP_KQPH ph where ph.DOT_DT = ? and TRUNC(ph.NGAY_PH) = ? and ph.MA_TPCP = ?)");
                } else if (vo.getPhuong_thuc_ph().equalsIgnoreCase("TL")) {
                    strSQL = new StringBuffer();
                    strSQL.append("update TP_KQPH_BAN_LE_CTIET set TRANG_THAI_TT = '02' where  MA_DVI_SO_HUU = ? AND KQPH_BAN_LE_ID in (select guid from TP_KQPH_BAN_LE ph where ph.DOT_PH = ? and TRUNC(ph.NGAY_PH) = ? and ph.MA_TPCP = ?)");
                } else if (vo.getPhuong_thuc_ph().equalsIgnoreCase("TPKB")) {
                    strSQL = new StringBuffer();
                    strSQL.append("update TP_KQPH_TIN_PHIEU_CTIET_SO_HUU set TRANG_THAI_TT = '02' where MA_CHU_SO_HUU = ? AND TP_KQPH_TIN_PHIEU_ID in (select guid from TP_KQPH_TIN_PHIEU ph where ph.DOT_PH = ? and TRUNC(ph.NGAY_PH) = ? and ph.MA_TPCP = ?)");
                } else if (vo.getPhuong_thuc_ph().equalsIgnoreCase("PHTT")) {
                    strSQL = new StringBuffer();
                    strSQL.append("update TP_HD_BAN_TIN_PHIEU set TRANG_THAI_TT = '02' where MA_DVI_DAU_TU = ? and SO_HD = ? AND TRUNC(NGAY_PH) = ? and MA_TP = ?");
                }
                v_param = new Vector();
                v_param.add(new Parameter(vo.getMa_nguoi_so_huu(), true));
                v_param.add(new Parameter(vo.getDot_ph(), true));
                v_param.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_ph(), "dd/MM/yyyy"), true));
                v_param.add(new Parameter(vo.getMa_tpcp(), true));
                executeStatement(strSQL.toString(), v_param, conn);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Long update(TTinDViSoHuuVO vo, String pt_ph) throws Exception {
        Vector v_param = new Vector();
        Long lID = new Long("0");
        StringBuffer strSQL = new StringBuffer();
        String sql = "";
        String tableName = "";
        try {
            if (vo.getGuid() != null && !vo.getGuid().equals("") && pt_ph != null && !pt_ph.equals("")) {
                if ("TD".equals(pt_ph)) {
                    tableName = "TP_KQPH_CTIET_SO_HUU";
                } else if ("TL".equals(pt_ph)) {
                    tableName = "TP_KQPH_BAN_LE_CTIET";
                } else if ("TPKB".equals(pt_ph)) {
                    tableName = "TP_KQPH_TIN_PHIEU_CTIET_SO_HUU";
                } else if ("PHTT".equals(pt_ph)) {
                    tableName = "TP_HD_BAN_TIN_PHIEU";
                }
                sql = generateSQLUpdate(vo, v_param, tableName, conn);
                strSQL.append(sql);
                strSQL.append("WHERE GUID = ? ");
                v_param.add(new Parameter(vo.getGuid(), true));
                lID = new Long(vo.getGuid());
                executeStatement(strSQL.toString(), v_param, conn);
            }
            return lID;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Collection getListDonViLapBKe(QLyTToanTienMuaVO vo, Integer page, Integer count, Integer[] totalCount) throws Exception {
        Collection reval = null;
        Vector vParam = new Vector();
        StringBuffer strSQL = new StringBuffer();
        try {
            if (vo.getPtph_tpcp() != null && "TD".equals(vo.getPtph_tpcp())) {
                strSQL = new StringBuffer();
                strSQL.append(" SELECT a.GUID,  a.MA_TPCP,  a.TIEN_TT_MUA,  \n" +
                        "nvl(a.TRANG_THAI_TT,'00') TRANG_THAI_TT,  a.MA_NGUOI_SO_HUU,  a.TEN_NGUOI_SO_HUU,  a.THANH_VIEN_DAU_THAU, b.dot_dt dot_ph \n" +
                        "FROM TP_KQPH_CTIET_SO_HUU a, TP_KQPH b\n" +
                        "where  a.TP_KQ_PH_ID = b.GUID and b.TRANG_THAI = '02' and a.MA_TPCP = ?  and b.dot_dt = ? ");
                vParam.add(new Parameter(vo.getMa_tpcp(), true));
                vParam.add(new Parameter(vo.getDot_ph(), true));
                strSQL.append(" ORDER BY a.TEN_NGUOI_SO_HUU, a.GUID ");
            } else if (vo.getPtph_tpcp() != null && "TL".equals(vo.getPtph_tpcp())) {
                strSQL = new StringBuffer();
                strSQL.append(" SELECT c.GUID,  d.MA_TPCP,  c.SO_TIEN_TT TIEN_TT_MUA,  nvl(c.TRANG_THAI_TT,'00') TRANG_THAI_TT,  c.MA_DVI_SO_HUU MA_NGUOI_SO_HUU,  c.DVI_SO_HUU TEN_NGUOI_SO_HUU,  c.DVI_SO_HUU THANH_VIEN_DAU_THAU, d.DOT_PH\n" +
                        " FROM TP_KQPH_BAN_LE_CTIET c, TP_KQPH_BAN_LE d\n" +
                        " where  c.KQPH_BAN_LE_ID = d.GUID and d.TRANG_THAI = '02' and d.MA_TPCP = ? and d.DOT_PH = ? ");
                vParam.add(new Parameter(vo.getMa_tpcp(), true));
                vParam.add(new Parameter(vo.getDot_ph(), true));
                strSQL.append(" ORDER BY c.DVI_SO_HUU, c.GUID ");
            } else if (vo.getPtph_tpcp() != null && "TPKB".equals(vo.getPtph_tpcp())) {
                strSQL = new StringBuffer();
                strSQL.append(" SELECT a.GUID,  a.MA_TPCP,  a.TIEN_TT_MUA TIEN_TT_MUA, nvl(a.TRANG_THAI_TT,'00') TRANG_THAI_TT,  a.MA_CHU_SO_HUU MA_NGUOI_SO_HUU,\n" +
                        " a.TEN_CHU_SO_HUU TEN_NGUOI_SO_HUU,  a.THANH_VIEN_DT THANH_VIEN_DAU_THAU,  b.DOT_PH \n" +
                        " FROM TP_KQPH_TIN_PHIEU_CTIET_SO_HUU a,\n" +
                        " TP_KQPH_TIN_PHIEU b\n" +
                        "WHERE a.TP_KQPH_TIN_PHIEU_ID = b.GUID AND b.TRANG_THAI = '02' \n" +
                        "AND a.MA_TPCP       = ? AND b.DOT_PH        = ? ");
                vParam.add(new Parameter(vo.getMa_tpcp(), true));
                vParam.add(new Parameter(vo.getDot_ph(), true));
                strSQL.append(" ORDER BY a.TEN_CHU_SO_HUU, a.GUID ");
            } else if (vo.getPtph_tpcp() != null && "PHTT".equals(vo.getPtph_tpcp())) {
                strSQL = new StringBuffer();
                strSQL.append("SELECT a.GUID,  a.MA_TP MA_TPCP,  a.KL_TP TIEN_TT_MUA, nvl(a.TRANG_THAI_TT,'00') TRANG_THAI_TT,  a.MA_DVI_DAU_TU MA_NGUOI_SO_HUU,\n" +
                        "nvl(a.TEN_DVI_DAU_TU,'Ngân hàng nhà nước Việt Nam') TEN_NGUOI_SO_HUU,  a.MA_DVI_DAU_TU THANH_VIEN_DAU_THAU,  a.SO_HD DOT_PH\n" +
                        "FROM TP_HD_BAN_TIN_PHIEU a where a.TRANG_THAI = '02' and  a.MA_TP  = ? and a.SO_HD = ? ");
                vParam.add(new Parameter(vo.getMa_tpcp(), true));
                vParam.add(new Parameter(vo.getDot_ph(), true));
            } else
                return new ArrayList();
            reval = executeSelectWithPaging(strSQL.toString(), vParam, CLASS_NAME_DVI_SO_HUU_VO, page, count, totalCount);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getListDonViLapBKe(): " + ex.getMessage(), ex);
        }
        return reval;
    }

    public Collection getListDonViLapBKe(QLyTToanTienMuaVO vo) throws Exception {
        Collection reval = null;
        Vector vParam = new Vector();
        StringBuffer strSQL = new StringBuffer();
        try {
            if (vo.getPtph_tpcp() != null && "TD".equals(vo.getPtph_tpcp())) {
                strSQL = new StringBuffer();
                strSQL.append(" SELECT a.GUID,  a.MA_TPCP,  a.TIEN_TT_MUA,  \n" +
                        "nvl(a.TRANG_THAI_TT,'00') TRANG_THAI_TT,  a.MA_NGUOI_SO_HUU,  a.TEN_NGUOI_SO_HUU,  a.THANH_VIEN_DAU_THAU, b.dot_dt dot_ph \n" +
                        "FROM TP_KQPH_CTIET_SO_HUU a, TP_KQPH b\n" +
                        "where  a.TP_KQ_PH_ID = b.GUID and b.TRANG_THAI = '02' and a.MA_TPCP = ?  and b.dot_dt = ? ");
                vParam.add(new Parameter(vo.getMa_tpcp(), true));
                vParam.add(new Parameter(vo.getDot_ph(), true));
                strSQL.append(" ORDER BY a.TEN_NGUOI_SO_HUU, a.GUID ");
            } else if (vo.getPtph_tpcp() != null && "TL".equals(vo.getPtph_tpcp())) {
                strSQL = new StringBuffer();
                strSQL.append(" SELECT c.GUID,  d.MA_TPCP,  c.SO_TIEN_TT TIEN_TT_MUA,  nvl(c.TRANG_THAI_TT,'00') TRANG_THAI_TT,  c.MA_DVI_SO_HUU MA_NGUOI_SO_HUU,  c.DVI_SO_HUU TEN_NGUOI_SO_HUU,  c.DVI_SO_HUU THANH_VIEN_DAU_THAU, d.DOT_PH\n" +
                        " FROM TP_KQPH_BAN_LE_CTIET c, TP_KQPH_BAN_LE d\n" +
                        " where  c.KQPH_BAN_LE_ID = d.GUID and d.TRANG_THAI = '02' and d.MA_TPCP = ? and d.DOT_PH = ? ");
                vParam.add(new Parameter(vo.getMa_tpcp(), true));
                vParam.add(new Parameter(vo.getDot_ph(), true));
                strSQL.append(" ORDER BY c.DVI_SO_HUU, c.GUID ");
            } else if (vo.getPtph_tpcp() != null && "TPKB".equals(vo.getPtph_tpcp())) {
                strSQL = new StringBuffer();
                strSQL.append(" SELECT a.GUID,  a.MA_TPCP,  a.TIEN_TT_MUA TIEN_TT_MUA, nvl(a.TRANG_THAI_TT,'00') TRANG_THAI_TT,  a.MA_CHU_SO_HUU MA_NGUOI_SO_HUU,\n" +
                        " a.TEN_CHU_SO_HUU TEN_NGUOI_SO_HUU,  a.THANH_VIEN_DT THANH_VIEN_DAU_THAU,  b.DOT_PH \n" +
                        " FROM TP_KQPH_TIN_PHIEU_CTIET_SO_HUU a,\n" +
                        " TP_KQPH_TIN_PHIEU b\n" +
                        "WHERE a.TP_KQPH_TIN_PHIEU_ID = b.GUID AND b.TRANG_THAI = '02' \n" +
                        "AND a.MA_TPCP       = ? AND b.DOT_PH        = ? ");
                vParam.add(new Parameter(vo.getMa_tpcp(), true));
                vParam.add(new Parameter(vo.getDot_ph(), true));
                strSQL.append(" ORDER BY a.TEN_CHU_SO_HUU, a.GUID ");
            } else if (vo.getPtph_tpcp() != null && "PHTT".equals(vo.getPtph_tpcp())) {
                strSQL = new StringBuffer();
                strSQL.append("SELECT a.GUID,  a.MA_TP MA_TPCP,  a.GIA_BAN TIEN_TT_MUA, nvl(a.TRANG_THAI_TT,'00') TRANG_THAI_TT,  a.MA_DVI_DAU_TU MA_NGUOI_SO_HUU,\n" +
                        "nvl(a.TEN_DVI_DAU_TU,'Ngân hàng nhà nước Việt Nam') TEN_NGUOI_SO_HUU,  a.MA_DVI_DAU_TU THANH_VIEN_DAU_THAU,  a.SO_HD DOT_PH\n" +
                        "FROM TP_HD_BAN_TIN_PHIEU a where a.TRANG_THAI = '02' and  a.MA_TP  = ? and a.SO_HD = ? ");
                vParam.add(new Parameter(vo.getMa_tpcp(), true));
                vParam.add(new Parameter(vo.getDot_ph(), true));
            } else
                return new ArrayList();
            reval = executeSelectStatement(strSQL.toString(), vParam, CLASS_NAME_DVI_SO_HUU_VO, conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getListDonViLapBKe(): " + ex.getMessage(), ex);
        }
        return reval;
    }

    public Collection getListDonViLapBKe(String ptph_tpcp, String ma_tpcp, String dot_ph, String lstDviSoHuu) throws Exception {
        Collection reval = null;
        StringBuffer strSQL = null;
        try {
            if (ptph_tpcp != null && "TD".equals(ptph_tpcp)) {
                strSQL = new StringBuffer();
                strSQL.append("SELECT t.*,  tt.ngay_chuyen_tien, tt.SO_TIEN_DA_TT \n" +
                        "FROM  (SELECT b.NGAY_PH,    b.MA_TPCP,    SUM(a.KL_TRUNG_THAU) KL_TRUNG_THAU, sum(a.TIEN_TT_MUA) TIEN_TT_MUA ,  a.MA_NGUOI_SO_HUU,\n" +
                        "    a.TEN_NGUOI_SO_HUU,    a.THANH_VIEN_DAU_THAU,    b.dot_dt DOT_PH\n" +
                        "  FROM TP_KQPH_CTIET_SO_HUU a,    TP_KQPH b\n" +
                        "  WHERE a.TP_KQ_PH_ID = b.GUID  AND b.MA_TPCP          = '" + ma_tpcp + "' AND b.dot_dt = '" + dot_ph + "' AND a.GUID            IN (" + lstDviSoHuu +
                        ")\n" +
                        "  GROUP BY b.MA_TPCP,    a.MA_NGUOI_SO_HUU, a.TEN_NGUOI_SO_HUU,   a.THANH_VIEN_DAU_THAU,    b.dot_dt,    b.NGAY_PH\n" +
                        "  ) t\n" +
                        "INNER JOIN\n" +
                        "  (SELECT SUM(so_tien) SO_TIEN_DA_TT,decode(to_char(MAX(NGAY_TT),'HH24:MI'),'00:00',to_char(MAX(NGAY_TT),'DD/MM/YYYY'),to_char(MAX(NGAY_TT),'DD/MM/YYYY HH24:MI')) ngay_chuyen_tien,    MA_NGUOI_SO_HUU,    NGAY_PH , MA_TPCP, DOT_PH\n" +
                        "  FROM TP_TT_TIEN_MUA  GROUP BY MA_NGUOI_SO_HUU,    NGAY_PH , MA_TPCP, DOT_PH )tt\n" +
                        "ON t.MA_NGUOI_SO_HUU = tt.MA_NGUOI_SO_HUU and t.MA_TPCP = tt.MA_TPCP and  t.DOT_PH = tt.DOT_PH\n" +
                        "AND TRUNC(t.NGAY_PH) = TRUNC(tt.NGAY_PH)");
            } else if (ptph_tpcp != null && "TL".equals(ptph_tpcp)) {
                strSQL = new StringBuffer();
                strSQL.append("SELECT t.*,  tt.ngay_chuyen_tien, tt.SO_TIEN_DA_TT\n" +
                        "FROM  (SELECT b.NGAY_PH,    b.MA_TPCP,    SUM(a.KL_DKY_MUA) KL_TRUNG_THAU,  sum(a.SO_TIEN_TT) TIEN_TT_MUA , a.MA_DVI_SO_HUU MA_NGUOI_SO_HUU,\n" +
                        "    a.DVI_SO_HUU TEN_NGUOI_SO_HUU,    a.MA_DVI_SO_HUU THANH_VIEN_DAU_THAU,    b.DOT_PH \n" +
                        "  FROM TP_KQPH_BAN_LE_CTIET a,    TP_KQPH_BAN_LE b\n" +
                        "  WHERE a.KQPH_BAN_LE_ID = b.GUID  AND b.MA_TPCP          = '" + ma_tpcp + "'  AND b.DOT_PH = '" + dot_ph + "' AND a.GUID            IN (" + lstDviSoHuu +
                        ")\n" +
                        "  GROUP BY b.MA_TPCP,    a.MA_DVI_SO_HUU,    a.DVI_SO_HUU,    b.DOT_PH,    b.NGAY_PH\n" +
                        "  ) t\n" +
                        "INNER JOIN\n" +
                        "  (SELECT SUM(so_tien) SO_TIEN_DA_TT, decode(to_char(MAX(NGAY_TT),'HH24:MI'),'00:00',to_char(MAX(NGAY_TT),'DD/MM/YYYY'),to_char(MAX(NGAY_TT),'DD/MM/YYYY HH24:MI')) ngay_chuyen_tien,    MA_NGUOI_SO_HUU,    NGAY_PH , MA_TPCP, DOT_PH\n" +
                        "  FROM TP_TT_TIEN_MUA  GROUP BY MA_NGUOI_SO_HUU,    NGAY_PH , MA_TPCP, DOT_PH )tt\n" +
                        "ON t.MA_NGUOI_SO_HUU = tt.MA_NGUOI_SO_HUU and t.MA_TPCP = tt.MA_TPCP and  t.DOT_PH = tt.DOT_PH \n" +
                        "AND TRUNC(t.NGAY_PH) = TRUNC(tt.NGAY_PH)");
            } else if (ptph_tpcp != null && "TPKB".equals(ptph_tpcp)) {
                strSQL = new StringBuffer();
                strSQL.append("SELECT t.*,   tt.ngay_chuyen_tien, tt.SO_TIEN_DA_TT\n" +
                        "FROM  (SELECT b.NGAY_PH,    b.MA_TPCP,    SUM(a.KL_TRUNG_THAU) KL_TRUNG_THAU,  sum(a.TIEN_TT_MUA) TIEN_TT_MUA,  a.MA_CHU_SO_HUU MA_NGUOI_SO_HUU,\n" +
                        "    a.TEN_CHU_SO_HUU TEN_NGUOI_SO_HUU,    a.THANH_VIEN_DT THANH_VIEN_DAU_THAU,    b.DOT_PH DOT_PH\n" +
                        "  FROM TP_KQPH_TIN_PHIEU_CTIET_SO_HUU a,    TP_KQPH_TIN_PHIEU b\n" +
                        "  WHERE a.TP_KQPH_TIN_PHIEU_ID = b.GUID  AND b.MA_TPCP          = '" + ma_tpcp + "'  AND b.DOT_PH = '" + dot_ph + "' AND a.GUID            IN (" +
                        lstDviSoHuu + ")\n" +
                        "  GROUP BY b.MA_TPCP,    a.MA_CHU_SO_HUU, a.TEN_CHU_SO_HUU,   a.THANH_VIEN_DT,    b.DOT_PH,    b.NGAY_PH\n" +
                        "  ) t\n" +
                        "INNER JOIN\n" +
                        "  (SELECT SUM(so_tien) SO_TIEN_DA_TT, decode(to_char(MAX(NGAY_TT),'HH24:MI'),'00:00',to_char(MAX(NGAY_TT),'DD/MM/YYYY'),to_char(MAX(NGAY_TT),'DD/MM/YYYY HH24:MI')) ngay_chuyen_tien,    MA_NGUOI_SO_HUU,    NGAY_PH , MA_TPCP, DOT_PH\n" +
                        "  FROM TP_TT_TIEN_MUA  GROUP BY MA_NGUOI_SO_HUU,    NGAY_PH , MA_TPCP, DOT_PH )tt\n" +
                        "ON t.MA_NGUOI_SO_HUU = tt.MA_NGUOI_SO_HUU and t.MA_TPCP = tt.MA_TPCP and  t.DOT_PH = tt.DOT_PH\n" +
                        "AND TRUNC(t.NGAY_PH) = TRUNC(tt.NGAY_PH)");
            } else if (ptph_tpcp != null && "PHTT".equals(ptph_tpcp)) {
                strSQL = new StringBuffer();
                strSQL.append("SELECT t.*,   tt.ngay_chuyen_tien, tt.SO_TIEN_DA_TT\n" +
                        "FROM  (SELECT a.NGAY_PH,    a.MA_TP MA_TPCP,    SUM(a.KL_TP) KL_TRUNG_THAU, sum(a.GIA_BAN) TIEN_TT_MUA,    a.MA_DVI_DAU_TU MA_NGUOI_SO_HUU,\n" +
                        "    nvl(a.TEN_DVI_DAU_TU,'Ngân hàng nhà nước Việt Nam') TEN_NGUOI_SO_HUU,    a.MA_DVI_DAU_TU THANH_VIEN_DAU_THAU,    a.SO_HD DOT_PH\n" +
                        "  FROM TP_HD_BAN_TIN_PHIEU a   \n" +
                        "  WHERE a.MA_TP          = '" + ma_tpcp + "' AND a.SO_HD = '" + dot_ph + "' AND a.GUID            IN (" + lstDviSoHuu + ")\n" +
                        "  GROUP BY a.MA_TP, a.MA_DVI_DAU_TU, a.TEN_DVI_DAU_TU,    a.SO_HD,    a.NGAY_PH\n" +
                        "  ) t\n" +
                        "INNER JOIN\n" +
                        "  (SELECT SUM(so_tien) SO_TIEN_DA_TT, decode(to_char(MAX(NGAY_TT),'HH24:MI'),'00:00',to_char(MAX(NGAY_TT),'DD/MM/YYYY'),to_char(MAX(NGAY_TT),'DD/MM/YYYY HH24:MI')) ngay_chuyen_tien,    MA_NGUOI_SO_HUU,    NGAY_PH , MA_TPCP, DOT_PH\n" +
                        "  FROM TP_TT_TIEN_MUA  GROUP BY MA_NGUOI_SO_HUU,    NGAY_PH ,MA_TPCP,DOT_PH )tt\n" +
                        "ON t.MA_NGUOI_SO_HUU = tt.MA_NGUOI_SO_HUU and t.MA_TPCP = tt.MA_TPCP and  t.DOT_PH = tt.DOT_PH \n" +
                        "AND TRUNC(t.NGAY_PH) = TRUNC(tt.NGAY_PH)");
            } else
                return new ArrayList();
            reval = executeSelectStatement(strSQL.toString(), null, CLASS_NAME_DVI_SO_HUU_VO, conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getListDonViLapBKe(): " + ex.getMessage(), ex);
        }
        return reval;
    }

    public QLyTToanTienMuaVO getThongTinPH(String ma_tpcp, String ngay_ph) throws Exception {
        QLyTToanTienMuaVO reval = null;
        StringBuffer strSQL = new StringBuffer();
        Vector vParam = new Vector();
        try {
            strSQL.append("SELECT DOT_DT DOT_PH " + "  FROM TP_KQPH WHERE 1= 1 ");
            strSQL.append(" AND MA_TPCP = ?");
            vParam.add(new Parameter(ma_tpcp, true));
            strSQL.append(" AND trunc(NGAY_PH) = ?");
            vParam.add(new DateParameter(StringUtil.StringToDate(ngay_ph, "dd/MM/yyyy"), true));
            reval = (QLyTToanTienMuaVO)findByPK(strSQL.toString(), vParam, CLASS_NAME_TTOAN_TMUA_VO, conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getListDonViLapBKe(): " + ex.getMessage(), ex);
        }
        return reval;
    }

    public String getDonViTinhFromMa(QLyTToanTienMuaVO vo) throws Exception {
        String reval = "VND";
        QLyTToanTienMuaVO voKQ = null;
        StringBuffer strSQL = new StringBuffer();
        Vector vParam = new Vector();
        try {
            if (vo.getPhuong_thuc_ph() != null) {
                if (vo.getPhuong_thuc_ph().equalsIgnoreCase("TD")) {
                    strSQL = new StringBuffer();
                    strSQL.append("SELECT LOAI_TIEN FROM TP_KQPH where 1= 1 ");
                    if (vo.getDot_ph() != null && !vo.getDot_ph().equals("")) {
                        strSQL.append(" AND DOT_DT = ?");
                        vParam.add(new Parameter(vo.getDot_ph(), true));
                    }
                } else if (vo.getPhuong_thuc_ph().equalsIgnoreCase("TL")) {
                    strSQL = new StringBuffer();
                    strSQL.append("SELECT LOAI_TIEN FROM TP_KQPH_BAN_LE where 1 = 1");
                    if (vo.getDot_ph() != null && !vo.getDot_ph().equals("")) {
                        strSQL.append(" AND DOT_PH = ?");
                        vParam.add(new Parameter(vo.getDot_ph(), true));
                    }
                } else
                    return reval;

                if (vo.getMa_tpcp() != null && !vo.getMa_tpcp().equals("")) {
                    strSQL.append(" AND MA_TPCP = ?");
                    vParam.add(new Parameter(vo.getMa_tpcp(), true));
                }
                if (vo.getNgay_ph() != null && !vo.getNgay_ph().equals("")) {
                    strSQL.append(" AND TRUNC(NGAY_PH) = ?");
                    vParam.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_ph(), "dd/MM/yyyy"), true));
                }
                voKQ = (QLyTToanTienMuaVO)findByPK(strSQL.toString(), vParam, CLASS_NAME_TTOAN_TMUA_VO, conn);
                if (voKQ != null)
                    reval = voKQ.getLoai_tien();
            }
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getListDonViLapBKe(): " + ex.getMessage(), ex);
        }
        return reval;
    }
}
