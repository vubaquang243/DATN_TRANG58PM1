package com.seatech.framework;

public class AppConstants {
    public static final String NNT_APP_CONTEXT_ROOT =
                "/TPCP";
    public static final String APP_REPORT_PATH =
        //        "C:\\Documents and Settings\\khanghoa\\Application Data\\JDeveloper\\system11.1.1.3.37.56.60\\o.j2ee\\drs\\KBNN\\TTSPWebApp.war/report/";
        "C:\\Documents and Settings\\Administrator\\Application Data\\JDeveloper\\system11.1.1.3.37.56.60\\o.j2ee\\drs\\KBNN\\TTSPWebApp.war/report/";
    //"/apps/domains/TCSTTDEV/servers/AdminServer/upload/TTSPWebApp.war/report/";
    public static final String APP_TEMPLATE_PATH =
        //        "C:\\Documents and Settings\\khanghoa\\Application Data\\JDeveloper\\system11.1.1.3.37.56.60\\o.j2ee\\drs\\KBNN\\TTSPWebApp.war/templates/";
        "C:\\Documents and Settings\\Administrator\\Application Data\\JDeveloper\\system11.1.1.3.37.56.60\\o.j2ee\\drs\\KBNN\\TTSPWebApp.war/templates/";
    //"/apps/domains/TCSTTDEV/servers/AdminServer/upload/TTSPWebApp.war/templates/";
    
    //Ten ung dung
    public static final String APP_NAME = "TPCP";    
    public static final String APP_ID = "";
    
    //********Sesion key********
    //Nguoi su dung
    public static final String APP_USER_ID_SESSION = "id_nsd";
    public static final String APP_USER_CODE_SESSION = "ma_nsd";
    public static final String APP_DOMAIN_SESSION = "domain_session";
    public static final String APP_USER_NAME_SESSION = "ten_nsd";
    public static final String APP_CHUC_NANG_LIST_SESSION = "chuc_nang_list";
    public static final String APP_IP_SESSION = "ip_truy_cap";
    public static final String APP_MAC_SESSION = "mac_truy_cap";
    public static final String APP_OS_USER_SESSION = "os_user_truy_cap";
    public static final String APP_COMPUTER_NAME_SESSION = "ten_may_truy_cap";
    public static final String APP_THAM_SO_SESSION = "APP_THAM_SO_SESSION";
    //Thong tin kho bac
    public static final String APP_KB_ID_SESSION = "id_kb";
    public static final String APP_KB_CODE_SESSION = "ma_kb";
    public static final String APP_KB_NAME_SESSION = "ten_kb";
        
    //Danh sach role
    public static final String APP_ROLE_LIST_SESSION = "role_list";
    public static final String APP_MENU_LIST_SESSION = "menu_list";
    //Phan trang
    public static final int APP_NUMBER_ROW_ON_PAGE = 15;
    
    //*****Loai lenh*****//
    
    //****** Bien Dieu Huong *****//
    public static final String FAILURE = "failure";
    public static final String SUCCESS = "success";
    public static final String CHECKPERMISSION = "permission";
    public static final String MAPING_NO_RIGHT = "notRight";
    //****** Loai User *****//
    public static final String NSD_CB_QLNQ = "CB_QLNQ";
    public static final String NSD_LD_QLNQ = "LD_QLNQ";
    public static final String NSD_QTHT = "QTHT";
    //*************************
   
    //****** ACTION BUTTON *****//
    public static final String REQUEST_ACTION = "action";
    public static final String ACTION_ACCEPT = "ACCEPT";
    public static final String ACTION_APPROVED = "APPROVED";
    public static final String ACTION_REJECT = "REJECT";
    public static final String ACTION_CANCEL = "CANCEL";
    public static final String ACTION_EDIT = "EDIT";
    public static final String ACTION_EXIT = "EXIT";
    public static final String ACTION_FIND = "FIND";
    public static final String ACTION_ADD = "ADD";
    public static final String ACTION_YES = "Y";
    public static final String ACTION_NO = "N";
    public static final String ACTION_WAIT_PROCESS = "WAITPROCESS";
    public static final String ACTION_VIEW_DETAIL = "VIEW_DETAIL";
    public static final String ACTION_FILL_DATA_TO_FORM = "fillDataForm";
    public static final String CONTENT_TYPE_JSON = "application/json";
    public static final String CONTENT_TYPE_JSON_UTF =
        "application/json ; charset=utf-8";
    public static final String ACTION_REFRESH = "REFRESH";
 
    
    //ten loai_truy_cap TTSP cua phan Tra cuu CSDL
    public static final String PARAM_LOAI_TRUY_CAP =
        "TTSP_DEVKratosMANHNV-PC192.168.1.157";
    public static final String PARAM_SO_LAN_DANG_NHAP_SAI =
          "SO_LAN_DANG_NHAP_SAI_CHO_PHEP";
    //Param cho MQ
    public static final String PARAM_MQ_HOSTNAME = "MQ_HOSTNAME";
    public static final String PARAM_MQ_CHANEL = "MQ_CHANEL";
    public static final String PARAM_MQ_PORT = "MQ_PORT";
    public static final String PARAM_MQ_MANAGER_NAME = "MQ_MANAGER_NAME";
    public static final String PARAM_MQ_NAME = "MQ_NAME";
   

    //*****Cac tham so cho gui lenh
    public static final String XML_VERSION =
        "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>";
    public static final String VERSION_MSG = "1.0";
    public static final String DATE_TIME_FORMAT_SEND_ORDER =
        "dd-MM-yyyy HH:mm:ss"; 
    public static final String DATE_FORMAT_SEND_ORDER =
      "dd-MM-yyyy";
    public static final String NUM_OF_TX = "1";
    public static final String INVALID = "INVALID";
    public static final String ERROR = "ERROR";


    public static final String MESSAGE_LOGGING = "MESSAGE_LOGGING";
    // THAM SO HE THONG TRA CUU CHUNG TU GIAO DIEN
    public static final String PARAM_MQ_HOST_NAME_GD =
        "MQ_HOST_NAME_TCUU_CTU_GDIEN";
    public static final String PARAM_MQ_CHANNEL_GD =
        "MQ_CHANNEL_TCUU_CTU_GDIEN";
    public static final String PARAM_MQ_QUEUE_IN_GD =
        "MQ_QUEUE_IN_TCUU_CTU_GDIEN";
    public static final String PARAM_MQ_QUEUE_OUT_GD =
        "MQ_QUEUE_OUT_TCUU_CTU_GDIEN";
    public static final String PARAM_MQ_MANAGER_NAME_GD =
        "MQ_MANAGER_NAME_TCUU_CTU_GDIEN";

   
    // report
    public static final String FONT_FOR_REPORT = "/font/times.ttf";
    public static final String REPORT_DIRECTORY = "/report";
    
    public static final String TIMER_REFRESH="TIMER_REFRESH_TTINTTOAN_ONLINE";
    
   
    public static final String FORMAT_CURRENTCEY_PATERN_VND = "#,###";
    public static final String FORMAT_CURRENTCEY_PATERN_NT = "#,##0.00";

}
