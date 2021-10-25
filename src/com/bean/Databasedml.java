package src.com.bean;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.jdbc.JDBCUtility;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author tkmagq7
 */
public class Databasedml {

    public static ArrayList<String> SkuList;
    private static double totalTimeTaken;
    
    private PrintWriter out;
    public static String use, pass, query;
    private JDBCUtility po;
    public static String url = "jdbc:mysql://10.186.19.34:3306/edi_ph";
    public static int pht_po_count;
    private boolean data1 = true;

    /*	public static void main(String args[]) throws Exception {
    long startTime = System.currentTimeMillis();
    SimpleDateFormat sdf = new SimpleDateFormat("MM:dd:yyyy HH:mm");
    Date resultdate = new Date(startTime);
    System.out.println(sdf.format(resultdate));
    Poedihub_Kafka po = new Poedihub_Kafka();
    po.dbconnect();
    double timeTaken = (System.currentTimeMillis() - startTime)/(double)1000;
    totalTimeTaken += timeTaken;
    System.out.println("totalTimeTaken"+totalTimeTaken);

    }*/
    public JDBCUtility dbconnect() throws Exception {

        //long endtime =System.currentTimeMillis()+ 1* 30*1000;

        use = "poediadmin";
        pass = "poediadmin";
        query = "select count(*) from edi_ph.pht_po";
        po = check(url, use, pass, query);

        return po;
        //System.out.println(endtime);


    }

    public JDBCUtility check(String url, String use, String pass, String query) throws Exception {
        JDBCUtility jd = null;
        try {
            jd = new JDBCUtility(url, use, pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jd;
    }

    public int data(JDBCUtility jd, String query) throws Exception {

        int count=0;
        return count;
    }
}
