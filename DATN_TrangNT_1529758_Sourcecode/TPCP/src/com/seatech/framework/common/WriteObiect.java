package com.seatech.framework.common;

import com.seatech.framework.exception.DatabaseConnectionFailureException;

import com.seatech.framework.tbao.ThongBaoSender;

import com.seatech.framework.tbao.ThongBaoTPCP;

import com.seatech.tp.dmtraichu.action.DMTraiChuDelegate;
import com.seatech.tp.dmtraichu.vo.DMTraiChuVO;

import java.io.File;
import java.io.FileOutputStream;

import java.io.IOException;

import java.lang.reflect.Field;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.text.DecimalFormat;

import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import java.util.List;

import java.util.Locale;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.sql.DataSource;

public class WriteObiect {
    public WriteObiect() {
        super();
    }
    private String testStr;
    
    private void searchObject() throws Exception {
      Comparator<DMTraiChuVO> comparator = new Comparator<DMTraiChuVO>() {
          public int compare(DMTraiChuVO emp1, DMTraiChuVO emp2) {
              return emp1.getMa_chu_so_huu().compareTo(emp2.getMa_chu_so_huu());
          }
      };
      Connection conn = null;
      conn = getConnection();
      List dmucDviSoHuu = (List) new DMTraiChuDelegate(conn).getDMTraiChu();
      Collections.sort(dmucDviSoHuu, comparator);
       
      DMTraiChuVO keyEmp = new DMTraiChuVO();
      keyEmp.setMa_chu_so_huu("SCIC");
       
      int index = Collections.binarySearch(dmucDviSoHuu, keyEmp, comparator);
       
      if (index >= 0) {
          keyEmp = (DMTraiChuVO)dmucDviSoHuu.get(index);
          System.out.println("Found: " + keyEmp.getMa_tv());
      }
    }

    public static void main(String[] args) {
        ThongBaoTPCP abc = new ThongBaoTPCP();
        Connection conn = null;
        WriteObiect writeObiect = new WriteObiect();
        
//        writeObiect.inVOClass("TP_BKE_CHUYEN_TIEN");
//        writeObiect.inVOClass2("TP_BKE_CHUYEN_TIEN");
//        //writeObiect.inVOClass("TP_BKE_CHUYEN_TIEN_CTIET");
//        writeObiect.inVOClass2("TP_BKE_CHUYEN_TIEN_CTIET");
//        // System.out.println( writeObiect.toString());

        try {
         // writeObiect.searchObject();
            double value = 123456789.98;
          DecimalFormat myFormatter = new DecimalFormat("###,###.###");
          String output = myFormatter.format(value);
          System.out.println(value + "  " + output);
           Locale[] abcd = Locale.getAvailableLocales();
            for(int i = 0; i < abcd.length ;i++){
                if(abcd[i].toString() == "de_DE"){
           //   System.out.println(abcd[i]);
                }
            }
//          NumberFormat nf = NumberFormat.getNumberInstance(Locale.GERMAN).format("###,###.###");
          DecimalFormat df = ((DecimalFormat)NumberFormat.getNumberInstance(Locale.GERMAN));
          df.applyPattern("###,###.###");
          String output2 = df.format(value);
            
          DecimalFormatSymbols symbolsDE_DE = DecimalFormatSymbols.getInstance(Locale.GERMANY);
          DecimalFormat formatDE_DE = new DecimalFormat("#,###.#", symbolsDE_DE);
          System.out.println(new DecimalFormat("#,###.00", 
                                 DecimalFormatSymbols.getInstance(
                                     new java.util.Locale("vi", "VI"))).format(9.87)); // prints -123.456,8
            
          //System.out.println(((DecimalFormat)NumberFormat.getNumberInstance(Locale.GERMAN)).applyPattern("###,###.###").format(value));
            
       //     conn = new WriteObiect().getConnection();
          //  abc.setConn(conn);
//            abc.sendThongBao("daonq@seatechit.com.vn", "Noi dung email html", null);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void inVOClass(String tableName) {
        PreparedStatement pstmt = null;
        Connection conn = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("select lower(column_name) as column_name , comments from user_col_comments where table_name = '" + tableName + "'");
            ResultSet rs = pstmt.executeQuery();
            StringBuilder builder = new StringBuilder();
            while (rs.next()) {
                builder.append(tableName.replaceAll("_", "")+"." + rs.getString("column_name") + "=" + rs.getString("comments"));
                builder.append("\n");

            }
            String fileName = "D:\\Project\\TPCP\\SourceCode\\TPCPApp\\TPCP\\src\\com\\seatech\\tp\\resource\\" + tableName.replaceAll("_", "") + "Resource.properties";
            saveFile(fileName, builder);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void inVOClass2(String tableName) {
        PreparedStatement pstmt = null;
        Connection conn = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("select * from " + tableName);
            ResultSet rs = pstmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            StringBuilder builder = null;
            StringBuilder builder2 = null;
            builder2 = new StringBuilder();
            builder2.append("public Contructor(){");
            for (int i = 1; i <= columnCount; i++) {
                String name = rsmd.getColumnName(i).toLowerCase();
                builder = new StringBuilder();

                builder.append("private String ");
                builder.append(name);
                builder.append(";");
                System.out.println(builder.toString());
                System.out.println("");
                builder2.append("\n");
                builder2.append("   this." + name + "=\"\";" + "");
            }
            System.out.println("private String pageNumber;\n");
            builder2.append("\n}");
            System.out.println(builder2.toString());

            for (int i = 1; i <= columnCount; i++) {
                String abc = "";
                String name = rsmd.getColumnName(i).toLowerCase();
                String nameTemp[] = name.split("_");
                builder = new StringBuilder();
                builder.append("public void set");
                builder.append(name.substring(0, 1).toUpperCase() + name.substring(1));
                builder.append("(String " + name.toLowerCase() + ") { ");
                builder.append("\n" +
                        "   this." + name.toLowerCase() + " = " + name.toLowerCase() + ";");
                builder.append("\n}");
                System.out.println(builder.toString());
                System.out.println("");
            }
            builder = new StringBuilder();
            builder.append("public void setPageNumber(String pageNumber) { ");
            builder.append("\n" +
                    "   this.pageNumber = pageNumber;");
            builder.append("\n}");
            for (int i = 1; i <= columnCount; i++) {
                String abc = "";
                String name = rsmd.getColumnName(i).toLowerCase();
                String nameTemp[] = name.split("_");
                builder = new StringBuilder();
                builder.append("public String get");
                builder.append(name.substring(0, 1).toUpperCase() + name.substring(1));

                builder.append("() { ");
                builder.append("\n" +
                        "   return " + name.toLowerCase() + ";");
                builder.append("\n}");
                System.out.println(builder.toString());
                System.out.println("");
            }
            builder = new StringBuilder();
            builder.append("public String getPageNumber() { ");
            builder.append("\n" +
                    "   return pageNumber;");
            builder.append("\n}");
            System.out.println(builder.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getName());
        sb.append(": ");
        for (Field f : getClass().getDeclaredFields()) {
            sb.append(f.getName());
            sb.append("=");
            try {
                sb.append(f.get(this));
            } catch (IllegalAccessException e) {
            }
            sb.append(", ");
        }
        return sb.toString();
    }
    private static final String dataSourceJNDI = "jdbc/tpcpDS";

    public Connection getConnection() {
        Connection conn = null;
        String driver = "oracle.jdbc.driver.OracleDriver";
        try {
            // Load the database driver
            Class.forName(driver);
            conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.89:1521:dev", "TPCP_OWNER", "tpcp");
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return conn;
    }

    public void setTestStr(String testStr) {
        this.testStr = testStr;
    }

    public String getTestStr() {
        return testStr;
    }

    private void saveFile(String fileName, StringBuilder builder) {
        FileOutputStream fop = null;
        File file;
        String content = builder.toString();

        try {

            file = new File(fileName);
            fop = new FileOutputStream(file);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            // get the content in bytes
            byte[] contentInBytes = content.getBytes("UTF-8");

            fop.write(contentInBytes);
            fop.flush();
            fop.close();

            System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fop != null) {
                    fop.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
