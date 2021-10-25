/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdbc;

/**
 *
 * @author ukannan_np
 */
public interface Batchjdbc {

    String query = "select EVENT_TYPE,EVENT_DESC,EVENT_RUN_TYPE,MAX_TRAN_SIZE from  cost_event_run_type_config where event_type in('CC','NIL')";
    String query1 = "select RESTART_NAME,THREAD_VAL,nvl(START_TIME,'31-DEC-9999'),nvl(FINISH_TIME,'31-DEC-9999'),PROGRAM_NAME,PROGRAM_STATUS from RMS.RESTART_PROGRAM_STATUS where program_name in ('fcexec', 'fcthreadexec')";
    String query2 = "select to_char(effective_date, 'dd-Mon-yy') effective_date, status,count(1) \n"
            + "from RPM_STAGE_PRICE_CHANGE\n"
            + "group by to_char(effective_date, 'dd-Mon-yy'), status\n"
            + "order by 1";
    String query3 = "select  batch_ind,count(*) as ffe_item_grade_hist  from ffe_item_grade_hist  group by batch_ind";
    String query5 = "select  batch_ind,count(*) as ffe_item_grade_st_exc_hist  from ffe_item_grade_st_exc_hist  group by batch_ind";
    String query6 = "select  batch_ind,count(*) as ffe_store_grade_stg  from ffe_store_grade_stg  group by batch_ind";
    String query7 = "select  batch_ind,count(*) as ffe_batch_grade_queue  from ffe_batch_grade_queue  group by batch_ind";
    String query8 = "select 'NB_INT_PRE_RCPT_PO_HEAD' AS TBL_NM,COUNT(1) AS CNT from INTG.NB_INT_PRE_RCPT_PO_HEAD UNION ALL\n"
            + "select 'NB_INT_PRE_RCPT_PO_DETAIL' AS TBL_NM,COUNT(1) AS CNT from INTG.NB_INT_PRE_RCPT_PO_DETAIL UNION ALL\n"
            + "select 'NB_INT_PRE_RCPT_PO_PACK' AS TBL_NM, COUNT(1) AS CNT from INTG.NB_INT_PRE_RCPT_PO_PACK UNION ALL\n"
            + "select 'NB_INT_PRE_RCPT_CTN_HEAD' AS TBL_NM,COUNT(1) AS CNT from INTG.NB_INT_PRE_RCPT_CTN_HEAD UNION ALL\n"
            + "select 'NB_INT_PRE_RCPT_CTN_DETAIL' AS TBL_NM,COUNT(1) AS CNT from INTG.NB_INT_PRE_RCPT_CTN_DETAIL UNION ALL\n"
            + "select 'NB_INT_PRE_RCPT_EVENT' AS TBL_NM,COUNT(1) AS CNT from INTG.NB_INT_PRE_RCPT_EVENT UNION ALL\n"
            + "select 'NB_INT_PRE_RCPT_SHIP_HEAD' AS TBL_NM, COUNT(1) AS CNT from INTG.NB_INT_PRE_RCPT_SHIP_HEAD UNION ALL\n"
            + "select 'NB_INT_PRE_RCPT_SHIP_DETAIL' AS TBL_NM, COUNT(1) AS CNT from INTG.NB_INT_PRE_RCPT_SHIP_DETAIL UNION ALL\n"
            + "select 'NB_INT_PRE_RCPT_CONTROL' AS TBL_NM, COUNT(1) AS CNT from NB_INT_PRE_RCPT_CONTROL";
    String query10 = " SELECT count(*)\n"
            + "        FROM item_master im_kimball\n"
            + "        JOIN item_master im_ean\n"
            + "          ON im_ean.item_parent = im_kimball.item_parent\n"
            + "         AND im_ean.item <> im_kimball.item\n"
            + "         AND im_kimball.item_level > im_kimball.tran_level\n"
            + "         AND im_kimball.status = 'A'\n"
            + "         AND im_kimball.item_level = im_ean.item_level\n"
            + "         AND im_kimball.item_grandparent is not null\n"
            + "       WHERE im_kimball.item_number_type = 'MANL'\n"
            + "         AND im_ean.item_number_type <> 'MANL'\n"
            + "       ORDER BY im_kimball.item";
    String query11 = " select status, count(1) from ordhead group by status";
    String query12 = "select count(*) from intg.NB_EXT_INVC_HEADER where status='N'";
    String query13 = "select nvl(count(*),0) from intg.nb_ext_duty_invoice_header where status='N'";
    String query14 = "Select nvl(count(*),0) from RMS.nb_ffe_sync_data where PROCESSED_IND = 'N'";
    String query15 = "select nvl(count(*),0) from FFE_BATCH_SUCCESS";
    String query16 = "select nvl(count(*),0) from rpm_stage_item_loc_clean";
    String query17 = "select luw_edi_injector from im_system_options";
    String query18 = "select PROCESSED_IND,nvl(count(*),0) from RMS.nb_ffe_sync_data group by PROCESSED_IND";
    String query19 = "select nvl(count(*),0) from RPM_BATCH_CONTROL where program_name = 'com.retek.rpm.batch.NewItemLocBatch'";

}
