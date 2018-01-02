package com.seatech.framework.tbao;

import com.seatech.framework.email.TPCPEMail;
import com.seatech.tp.thamso.ThamSoHThong;
import com.seatech.tp.thamso.ThamSoHThongDAO;

import java.util.Collection;

import javax.mail.util.ByteArrayDataSource;
import javax.activation.DataSource;

public class ThongBaoSender {
    protected void sendEMailTBao(String emailAddr,
                                 String emailHTMLContent,byte[] tbaoAttachment,Collection tsoHeThong) throws Exception {

        ThamSoHThong tsoHThong = new ThamSoHThong();
        String emailSupportAdd =  tsoHThong.getThamSoHThong("EMAIL_SUPPORT",tsoHeThong);
        String toEmailList = emailAddr + ";" + emailSupportAdd;
        String emailServerAdd = tsoHThong.getThamSoHThong("EMAIL_SERVER", tsoHeThong);
        String emailUser = tsoHThong.getThamSoHThong("EMAIL_USERNAME", tsoHeThong);
        String emailPassword = tsoHThong.getThamSoHThong("EMAIL_PASSWORD", tsoHeThong);
        String emailFrom = "He thong quan ly TPCP";
        String emailSubject = "Thong bao cong bo goi thau";

        TPCPEMail email =
            new TPCPEMail(emailServerAdd,emailUser,emailPassword);
        
        ByteArrayDataSource attachment =  null;
        DataSource[] attachments = null;
        if(tbaoAttachment !=null){
            attachment = email.createPDFDataSource(tbaoAttachment, "Thong bao.pdf");
            attachments = new ByteArrayDataSource[1];
            attachments[0] = attachment;
        }            
        
        email.sendEMail(emailFrom, emailSupportAdd ,toEmailList, null,
                        emailSubject, emailHTMLContent, attachments);
    }
}
