package com.edevelopers.realdbtime.Model;

import android.content.ComponentName;
import android.content.Context;

import com.edevelopers.realdbtime.Lib.Const;
import com.edevelopers.realdbtime.Lib.ConstNew;

import java.util.ArrayList;

public class ModelClass {
    Context context;
    String api_key="";
    String appname="";
    String api_secret="";
    String query="";
    String TableName = "";
    String password = "";
    ArrayList<DBColumn> dbcolumn = new ArrayList<>();
    ArrayList<DBColumnDoub> dbcolumndoub = new ArrayList<>();

    public ModelClass(){

    }

    public ModelClass(Context context, String api_key, String apisecret, String appname, String TableName,
                      ArrayList<DBColumn> dbcolumn,ArrayList<DBColumnResult> DBcolres,ArrayList<DBColumnResult> DBcolwhere,int type,int db,int limit){
        this.context = context;
        this.api_key = api_key;
        this.api_secret = apisecret;
        this.appname = appname;
        this.TableName = TableName;
        this.dbcolumn = dbcolumn;
        if(Const.GET_TAG_INSERT == type){
            this.query = Const.insertQueryBuilder(TableName,DBcolres);
        }
        else if(Const.GET_TAG_GETDATA == type){
            if(db == Const.MSSQL){
                if(limit > 0){
                    this.query = ConstNew.getMSSQLQueryLimit(String.valueOf(limit),TableName,dbcolumn);
                }
                else {
                    this.query = ConstNew.getMSSQLQuery(TableName,dbcolumn);
                }
            }
        }
        if(Const.GET_TAG_UPDATE == type){
            this.query = Const.updateQueryBuilder(TableName,DBcolres,DBcolwhere);
        }
        else if(Const.GET_TAG_GETDATA_WHERE == type){
            if(db == Const.MSSQL){
                if(limit > 0){
                    this.query = ConstNew.getMSSQLQuery_where_Limit(String.valueOf(limit),TableName,dbcolumn,DBcolwhere);
                }
                else {
                    this.query = ConstNew.getMSSQLQuery_where(TableName,dbcolumn,DBcolwhere);
                }
            }
        }
    }

    public ModelClass(Context context, String api_key, String apisecret, String appname, String TableName,
                      ArrayList<DBColumn> dbcolumn,ArrayList<DBColumnResult> DBcolres,String DBcolwhere,int type,int db,int limit){
        this.context = context;
        this.api_key = api_key;
        this.api_secret = apisecret;
        this.appname = appname;
        this.TableName = TableName;
        this.dbcolumn = dbcolumn;
        if(Const.GET_TAG_GETDATA_WHERE == type){
            if(db == Const.MSSQL){
                if(limit > 0){
                    this.query = ConstNew.getMSSQLQuery_where_RawLimit(String.valueOf(limit),TableName,dbcolumn,DBcolwhere);
                }
                else {
                    this.query = ConstNew.getMSSQLQuery_whereRaw(TableName,dbcolumn,DBcolwhere);
                }
            }
        }
    }

    public ModelClass(Context context, String api_key, String apisecret, String appname, String TableName,
                      ArrayList<DBColumnDoub> dbcolumndoub,String DBcolwhere,int type,int db,int limit){
        this.context = context;
        this.api_key = api_key;
        this.api_secret = apisecret;
        this.appname = appname;
        this.TableName = TableName;
        this.dbcolumndoub = dbcolumndoub;
        if(Const.GET_TAG_GETDATA_WHERE == type){
            if(db == Const.MSSQL){
                if(limit > 0){
                    ArrayList<DBColRAWReturn> getcols = ConstNew.getMSSQLQuery_where_RawAliyasingLimit(String.valueOf(limit),TableName,dbcolumndoub,DBcolwhere);
                    this.query = getcols.get(0).getQuery();
                    this.dbcolumn = getcols.get(0).getDbcols();
                }
                else {
                    ArrayList<DBColRAWReturn> getcols = ConstNew.getMSSQLQuery_whereRawAliyasing(TableName,dbcolumndoub,DBcolwhere);
                    this.query = getcols.get(0).getQuery();
                    this.dbcolumn = getcols.get(0).getDbcols();
                }
            }
        }
    }


    public String getPassword() {
        return password;
    }

    public Context getContext() {
        return context;
    }

    public String getApi_key() {
        return api_key;
    }

    public String getApi_secret() {
        return api_secret;
    }

    public String getAppname() {
        return appname;
    }

    public String getQuery() {
        return query;
    }

    public ArrayList<DBColumn> getDbcolumn() {
        return dbcolumn;
    }

    public String getTableName() {
        return TableName;
    }
}
