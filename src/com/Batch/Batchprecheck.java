/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Batch;

import com.RMS.*;
import com.ESB.*;
import com.jdbc.Batchjdbc;
import com.jdbc.Csvcreation;
import com.jdbc.JDBCUtility;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author ukannan_np
 */
public class Batchprecheck implements Batchjdbc{
   private static JDBCUtility jdbc1;
    public Batchprecheck() throws Exception
    {
          jdbc1 = new JDBCUtility("jdbc:oracle:thin:@iecwxuvuodb320.primark.local:1521/SRV_PRF_MOM_SUPPORT.PRIMARK.LOCAL","PERF03","prf3321");
    }

    public void Batchreadcheck() throws FileNotFoundException, Exception {

        
       Date d = new Date();
   SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   String starttime = f.format(new Date());

       Csvcreation csv=new Csvcreation("BatchjobDatabasecheck"+starttime.substring(0,12)+".txt",true);
       csv.Ofile();
       csv.Wfile("BatchjobDatabasecheck Date - "+starttime.substring(0,12));
      
            ArrayList<String> data = new ArrayList();

            

            data = jdbc1.collectData(query);

            
            System.out.println("Check the cost_events processing from ASYNC to BATCH fo rcost event type NIL & CC:" + data);
            System.out.println("EVENT_TYPE,EVENT_DESC,EVENT_RUN_TYPE,MAX_TRAN_SIZE");
            System.out.println(data);
             csv.Wfile("Check the cost_events processing from ASYNC to BATCH fo rcost event type NIL & CC: Completed" );
             csv.Wfile("EVENT_TYPE,EVENT_DESC,EVENT_RUN_TYPE,MAX_TRAN_SIZE");
            for (String line : data) {

               csv.Wfile(line);

                System.out.println("ItemId =" + line);
              
            }
            csv.Wfile("\n****************@@@@****************\n");
            ArrayList<String> data1 = new ArrayList();

            

            data1 = jdbc1.collectData(query1);

            
            System.out.println("Check the thread for fcexec,fcthreadexec");
            System.out.println("RESTART_NAME,THREAD_VAL,START_TIME,FINISH_TIME,PROGRAM_NAME,PROGRAM_STATUS");
            csv.Wfile("Check the thread for fcexec,fcthreadexec: Completed");
            csv.Wfile("RESTART_NAME,THREAD_VAL,START_TIME,FINISH_TIME,PROGRAM_NAME,PROGRAM_STATUS");
            for (String line : data1) {

               csv.Wfile(line);

                System.out.println(line);
              
            }
            csv.Wfile("\n****************@@@@****************\n");
             ArrayList<String> data2 = new ArrayList();

            

            data2 = jdbc1.collectData(query2);

            
            System.out.println("Check price event data");
            System.out.println("to_char(effective_date, 'dd-Mon-yy') effective_date, status,count(1)");
            csv.Wfile("Check price event data:Completed");
            csv.Wfile("to_char(effective_date, 'dd-Mon-yy') effective_date, status,count(1)");
            for (String line : data2) {

               csv.Wfile(line);

                System.out.println(line);
              
            }
            csv.Wfile("\n****************@@@@****************\n");
              ArrayList<String> data3 = new ArrayList();

         
            data3 = jdbc1.collectData(query3);

            
            System.out.println("Check ffe_item_grade_hist EXPLODE");
            System.out.println("batch_ind,count(*) as ffe_item_grade_hist");
            csv.Wfile("Check ffe_item_grade_hist EXPLODE:Completed");
            csv.Wfile("batch_ind,count(*) as ffe_item_grade_hist");
            for (String line : data3) {

               csv.Wfile(line);

                System.out.println(line);
              
            }
            csv.Wfile("\n****************@@@@****************\n");
 /*ArrayList<String> data4 = new ArrayList();

            String query4 = "select  batch_ind,count(*) as ffe_item_grade_hist  from ffe_item_grade_hist  group by batch_ind";

            data3 = jdbc1.collectData(query4);

            
            System.out.println("Check ffe_updt_itmloc EXPLODE");
            System.out.println("batch_ind,count(*) as ffe_item_grade_hist");
           csv.Wfile("Check ffe_updt_itmloc EXPLODE:Completed");
            csv.Wfile("batch_ind,count(*) as ffe_item_grade_hist");
            for (String line : data4) {

               csv.Wfile(line);

                System.out.println(line);
              
            }
            csv.Wfile("\n****************@@@@****************\n");
   */        ArrayList<String> data5 = new ArrayList();

          

            data5 = jdbc1.collectData(query5);

            
            System.out.println("Check ffe_updt_itmloc EXPLODE");
            System.out.println("batch_ind,count(*) as ffe_item_grade_st_exc_hist");
            csv.Wfile("Check ffe_updt_itmloc EXPLODE:Completed");
            csv.Wfile("batch_ind,count(*) as ffe_item_grade_st_exc_hist");
            for (String line : data5) {

               csv.Wfile(line);

                System.out.println(line);
              
            }
            csv.Wfile("\n****************@@@@****************\n");
            ArrayList<String> data6 = new ArrayList();

            

            data6 = jdbc1.collectData(query6);

            
            System.out.println("Check ffe_updt_itmloc EXPLODE");
            csv.Wfile("Check ffe_updt_itmloc EXPLODE:Completed");
            System.out.println("batch_ind,count(*) as ffe_store_grade_stg");
            csv.Wfile("batch_ind,count(*) as ffe_store_grade_stg");
            for (String line : data6) {

               csv.Wfile(line);

                System.out.println(line);
              
            }
            csv.Wfile("\n****************@@@@****************\n");
            ArrayList<String> data7 = new ArrayList();

            

            data7 = jdbc1.collectData(query7);

            
            System.out.println("Check ffe_updt_itmloc EXPLODE");
            csv.Wfile("Check ffe_updt_itmloc EXPLODE:Completed");
            System.out.println("batch_ind,count(*) as ffe_batch_grade_queue");
            csv.Wfile("batch_ind,count(*) as ffe_batch_grade_queue");
            for (String line : data7) {

               csv.Wfile(line);

                System.out.println(line);
              
            }
            csv.Wfile("\n****************@@@@****************\n");
            ArrayList<String> data8 = new ArrayList();

        
            data8 = jdbc1.collectData(query8);

            
            System.out.println("PRE_RCPT");
            csv.Wfile("Check PRE_RCPT:Completed");
            System.out.println("TBL_NM,count(*) as PRE_RCPT");
            csv.Wfile("TBL_NM,count(*) as PRE_RCPT");
            for (String line : data8) {

               csv.Wfile(line);

                System.out.println(line);
              
            }
            csv.Wfile("\n****************@@@@****************\n");
     /*       ArrayList<String> data9 = new ArrayList();

            String query9 = "SELECT im_kimball.item     AS kimball_7,\n" +
"             NB_INT_item_sql.CLEAN_ITEM_DESC(im_orin.short_desc, 'ITEM_KIMB_REDKIMBALL')   AS orin_short_desc,\n" +
"             im_orin.dept        AS orin_dept,\n" +
"             rizp.selling_retail\n" +
"        FROM item_master im_kimball\n" +
"        JOIN item_master im_orin\n" +
"          ON im_orin.item = im_kimball.item_parent\n" +
"        JOIN rpm_item_zone_price rizp\n" +
"          ON rizp.item = im_orin.item\n" +
"         AND rizp.zone_id in (1,2,3,4,5,6)\n" +
"       WHERE im_kimball.item_number_type = 'MANL'\n" +
"         AND im_kimball.item_level > im_kimball.tran_level\n" +
"         AND im_kimball.item_grandparent is not null\n" +
"         AND im_kimball.status = 'A'\n" +
"       ORDER BY im_kimball.item";
            data9 = jdbc1.collectData(query9);

            
            System.out.println("Check data for NB_RMS_RKB_KIMBALL_GEN");
            System.out.println("im_kimball.item     AS kimball_7,\n" +
"             NB_INT_item_sql.CLEAN_ITEM_DESC(im_orin.short_desc, 'ITEM_KIMB_REDKIMBALL')   AS orin_short_desc,\n" +
"             im_orin.dept        AS orin_dept,\n" +
"             rizp.selling_retail");
            csv.Wfile("im_kimball.item     AS kimball_7,\n" +
"             NB_INT_item_sql.CLEAN_ITEM_DESC(im_orin.short_desc, 'ITEM_KIMB_REDKIMBALL')   AS orin_short_desc,\n" +
"             im_orin.dept        AS orin_dept,\n" +
"             rizp.selling_retail");
            for (String line : data9) {

               csv.Wfile(line);

                System.out.println(line);
              
            }
       */       ArrayList<String> data10 = new ArrayList();

         

            data10 = jdbc1.collectData(query10);

            
            System.out.println("Check data for NB_RMS_RKB_BARCODE_GEN");
            csv.Wfile("Check data for NB_RMS_RKB_BARCODE_GEN:Completed");
            System.out.println(" Count of item ");
            csv.Wfile("Count of item ");
            for (String line : data10) {

               csv.Wfile("Item count:"+line);

                System.out.println(line);
              
            }
           
              ArrayList<String> data11 = new ArrayList();

            

            data11 = jdbc1.collectData(query11);

                  

            System.out.println("Check Orderstatus");
            csv.Wfile("Check Orderstatus:Completed");
            System.out.println("Status,count(*) as Orderstatus");
            csv.Wfile("status,count(*)");
            for (String line : data11) {

               csv.Wfile(line);

                System.out.println(line);
                csv.Wfile("Orderstatus :"+line);
              
            }
            csv.Wfile("\n****************@@@@****************\n");
            
             ArrayList<String> data12 = new ArrayList();

            

            data12 = jdbc1.collectData(query12);

                  

            System.out.println("Check data for NB_FREIGHT_SPLIT");
            csv.Wfile("Check data for NB_FREIGHT_SPLIT:Completed");
            System.out.println("Status,count(*)");
            csv.Wfile("status,count(*) as NB_FREIGHT_SPLIT count");
            for (String line : data12) {

               csv.Wfile("NB_FREIGHT_SPLIT count ,"+line);

                System.out.println("NB_FREIGHT_SPLIT count ,"+line);
              
            }
            csv.Wfile("\n****************@@@@****************\n");
              ArrayList<String> data13 = new ArrayList();

            

            data13 = jdbc1.collectData(query13);

                  

            System.out.println("Check data for NB_PRE_DUTY_SPLIT");
            csv.Wfile("Check data for NB_PRE_DUTY_SPLIT:Completed");
            System.out.println("Status,count(*)");
            csv.Wfile("status,count(*)");
            for (String line : data13) {

               csv.Wfile("NB_PRE_DUTY_SPLIT count ,"+line);

                System.out.println("NB_PRE_DUTY_SPLIT count ,"+line);
              
            }
            csv.Wfile("\n****************@@@@****************\n");
             ArrayList<String> data14 = new ArrayList();

           

            data14 = jdbc1.collectData(query14);

                  

            System.out.println("Check data for FFE_RMS_DATA job");
            csv.Wfile("Check data for FFE_RMS_DATA job:Completed");
            System.out.println("Status,count(*)");
            csv.Wfile("status,count(*)");
            for (String line : data14) {

               csv.Wfile("FFE_RMS_DATA count ,"+line);

                System.out.println("FFE_RMS_DATA count ,"+line);
              
            }
            csv.Wfile("\n****************@@@@****************\n");
            
            
             ArrayList<String> data15 = new ArrayList();

            

            data15 = jdbc1.collectData(query15);

                  

            System.out.println("Check data for FFE_BATCH_SUCCESS ");
            csv.Wfile("Check data for FFE_BATCH_SUCCESS :Completed");
            System.out.println("FFE_BATCH_SUCCESS,count(*)");
            csv.Wfile("status,count(*)");
            for (String line : data15) {

               csv.Wfile("FFE_BATCH_SUCCESS count ,"+line);

                System.out.println("FFE_BATCH_SUCCESS count ,"+line);
              
            }
            csv.Wfile("\n****************@@@@****************\n");
              ArrayList<String> data16 = new ArrayList();

            

            data16 = jdbc1.collectData(query16);

                  

            System.out.println("Check data for rpm_stage_item_loc_clean ");
            csv.Wfile("Check data for rpm_stage_item_loc_clean :Completed");
            System.out.println("rpm_stage_item_loc_clean,count(*)");
            csv.Wfile("status,count(*)");
            for (String line : data16) {

               csv.Wfile("rpm_stage_item_loc_clean count ,"+line);

                System.out.println("rpm_stage_item_loc_clean count ,"+line);
              
            }
            csv.Wfile("\n****************@@@@****************\n");
             ArrayList<String> data17 = new ArrayList();

            

            data17 = jdbc1.collectData(query17);

                  

            System.out.println("Check data for luw_edi_injector ");
            csv.Wfile("Check data for luw_edi_injector :Completed");
            System.out.println("luw_edi_injector,count(*)");
            csv.Wfile("status,count(*)");
            for (String line : data17) {

               csv.Wfile("rpm_stage_item_loc_clean count ,"+line);

                System.out.println("FFE_BATCH_SUCCESS count ,"+line);
              
            }
            csv.Wfile("\n****************@@@@****************\n");
             
             csv.Wfile("Overall Status:Completed");
            csv.Cfile();
    }
        

    
}
