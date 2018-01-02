package com.seatech.framework.tbao;

import com.seatech.framework.datamanager.AppDAO;

import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class TBaoDAO extends AppDAO{
    public TBaoDAO(Connection conn) {
        super();
        this.conn = conn;
    }

    public void insertTBao(String id_dau_thau, String emailAddr, String soTBao, String ngayGui, String HTMLContent,
                           byte[] tbaoPDFFile) throws Exception {

        PreparedStatement pstm = null;
        StringBuffer sql = new StringBuffer();
        Clob fileCLOB;
        Blob fileBLOB;
        long tbaoID = 0;
        tbaoID = getSeqNextValue("TP_TBAO_SEQ", conn);      
        sql.append("INSERT INTO TP_THONGBAO(GUID, ID_DAU_THAU,TO_EMAIL,SO_THONGBAO,NGAY_GUI,EMAIL_CONTENT,BYTE_CONTENT) values(");
        sql.append(tbaoID);
        sql.append(",'");
        sql.append(id_dau_thau);
        sql.append("', '");
        sql.append(emailAddr);
        sql.append("', '");
        sql.append(soTBao);
        sql.append("', to_date('" + ngayGui + "', 'DDMMRRRRHH24MISS')");
        sql.append(", ?, ? )");
        pstm = conn.prepareStatement(sql.toString());
        fileCLOB = conn.createClob();
        fileCLOB.setString(1, HTMLContent);
        pstm.setClob(1, fileCLOB);
        fileBLOB = conn.createBlob();
        fileBLOB.setBytes(1, tbaoPDFFile);
        pstm.setBlob(2, fileBLOB);
        pstm.execute();
        pstm.close();
        fileBLOB = null;
        fileCLOB = null;
    }
}
