package com.edevelopers.realdbtime.Lib;



import com.edevelopers.realdbtime.Model.DBColumn;
import com.edevelopers.realdbtime.Model.DBColumnResult;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Const {

    public static String API_URL = "";
    public static String COCD = "";

    public static final int GET_TAG_INSERT = 1;
    public static final int GET_TAG_UPDATE = 2;
    public static final int GET_TAG_GETDATA = 3;
    public static final int GET_TAG_GETDATA_WHERE = 4;

    public static final int MSSQL = 1;
    public static final int MYSQL = 2;


    public static final String TAG = "RealTime_Db_Live";
    public static final String api_key = "api_key";
    public static final String appname = "appname";
    public static final String api_secret = "api_secret";
    public static final String g_username = "g_username";
    public static final String g_password = "g_password";
    public static final String query = "query";
    public static final String cocd = "cocd";

    public static final String BRACK_START = " ( ";
    public static final String BRACK_end = " ) ";
    public static final String BEST_LEVEL = " SELECT CONCAT('[', better_result, ']') AS best_result FROM ";
    public static final String BETTER_lEVEL = " SELECT GROUP_CONCAT('{', my_json, '}' SEPARATOR ',') AS better_result FROM ";
    public static final String BOTTOM_SECOND_lEVEL = " AS more_json ";
    public static final String BOTTOM_LAST_lEVEL = " AS yet_more_json;";

    public static final String first_t_start = "'\"";
    public static final String first_t_end = "\":',";
    public static final String mid_t = "'\"',";
    public static final String second_t_start = ", '\"'";
    public static final String second_t_end = ",','";

    public static final String INSERT_START_INIT = "INSERT INTO ";
    public static final String INSERT_START_VALUES = "VALUES ";
    public static final String INSERT_HEAD_DECLARE = "`";
    public static final String INSERT_separater_DECLARE = ",";
    public static final String INSERT_VALUE_HEAD_DECLARE = "'";


    public static final String WHERE_CLAUS_START = " WHERE ";
    public static final String reg = "password";
    String str = "Where email = 'admin@example.com'";

    public static String wclausebuilder(ArrayList<DBColumnResult> DBcolres){
        String wherecalus = "";
        int warrsize = DBcolres.size();
        warrsize = warrsize -1;
        for(int i = 0; i < DBcolres.size(); i++){
            if(warrsize == i){
                wherecalus += "`" +DBcolres.get(i).getColumnname()+"` = '"+ DBcolres.get(i).getColumnResult()+"' " ;
            }else{
                wherecalus += "`" +DBcolres.get(i).getColumnname()+"` = '"+ DBcolres.get(i).getColumnResult()+"' and " ;
            }
        }

        String result = WHERE_CLAUS_START + wherecalus;
        return result;
    }

    public static String insertQueryBuilder(String TableName, ArrayList<DBColumnResult> DBcolres){
        String start_result = INSERT_START_INIT + INSERT_HEAD_DECLARE + TableName + INSERT_HEAD_DECLARE + BRACK_START ;
        String end_result = INSERT_START_VALUES + BRACK_START ;

        String end_start_result = BRACK_end;
        String end_end_result = BRACK_end+";";

        String res_st = "";
        String res_end = "";
        int arrsize = DBcolres.size();
        arrsize = arrsize-1;
        for(int i = 0; i < DBcolres.size(); i++){
            if(arrsize == i){
                res_st += INSERT_HEAD_DECLARE+DBcolres.get(i).getColumnname()+INSERT_HEAD_DECLARE;
                res_end += INSERT_VALUE_HEAD_DECLARE+DBcolres.get(i).getColumnResult()+INSERT_VALUE_HEAD_DECLARE;
            }
            else{
                res_st += INSERT_HEAD_DECLARE+DBcolres.get(i).getColumnname()+INSERT_HEAD_DECLARE+INSERT_separater_DECLARE;
                res_end += INSERT_VALUE_HEAD_DECLARE+DBcolres.get(i).getColumnResult()+INSERT_VALUE_HEAD_DECLARE+INSERT_separater_DECLARE;
            }
        }

        String result = start_result + res_st + end_start_result + end_result + res_end + end_end_result;
        return result;
    }

    public static String updateQueryBuilder(String TableName, ArrayList<DBColumnResult> DBcolvalue, ArrayList<DBColumnResult> DBcolwhere){
        String HeadUPdate = "UPDATE  `"+TableName+"` SET ";
        String upcalus = "";
        int uparrsize = DBcolvalue.size();
        uparrsize = uparrsize -1;
        for(int i = 0; i < DBcolvalue.size(); i++){
            if(uparrsize == i){
                upcalus += "`" +DBcolvalue.get(i).getColumnname()+"` = '"+ DBcolvalue.get(i).getColumnResult()+"' " ;
            }else{
                upcalus += "`" +DBcolvalue.get(i).getColumnname()+"` = '"+ DBcolvalue.get(i).getColumnResult()+"',  " ;
            }
        }

        String wresult = wclausebuilder(DBcolwhere);
        String result = HeadUPdate + upcalus + wresult +" ;";
        return result;
    }

}
