package com.seatech.tp.dmkb.action;


import com.seatech.framework.strustx.AppAction;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class DMKBacAction extends AppAction {

  public ActionForward executeAction(ActionMapping mapping, ActionForm form,
                            HttpServletRequest request,
                            HttpServletResponse response) throws Exception {
      Connection conn = null;
      try {
          conn = getConnection(request);
          

      } catch (Exception e) {
          throw e;

      } finally {
          close(conn);
      }
      return mapping.findForward("success");
  }

}
