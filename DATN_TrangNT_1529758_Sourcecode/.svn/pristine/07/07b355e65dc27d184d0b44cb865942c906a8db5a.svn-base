package com.seatech.tp.qlytinphieu.action;

import com.google.gson.Gson;

import com.seatech.framework.strustx.AppAction;
import com.seatech.tp.qlytp.action.QuanLyTPCPDelegate;
import com.seatech.tp.qlytp.vo.QuanLyTPCPVO;

import java.io.PrintWriter;

import java.sql.Connection;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GetAjaxKyHanAction extends AppAction {

    private static String SUCCESS = "success";
    private static String FAILURE = "failure";

    public GetAjaxKyHanAction() {
        super();
    }

    public ActionForward executeAction(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {

        Connection conn = null;
        String errMess = "";
        try {
            conn = getConnection();
            String ma_tp = request.getParameter("longid").trim();
            QuanLyTPCPDelegate delegate = new QuanLyTPCPDelegate(conn);
            Map<String, Object> map = new HashMap();
            map.put("MA_TP", ma_tp);
            QuanLyTPCPVO tpcpVo = delegate.getTTDTObject(map);
            response.setContentType("text/text;charset=utf-8");
            response.setHeader("cache-control", "no-cache");
            PrintWriter out = response.getWriter();
            Gson gson = new Gson();
            out.println(gson.toJson(tpcpVo));
            out.flush();

        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            close(conn);

        }
        return null;
    }

}
