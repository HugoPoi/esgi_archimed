package controllers;


import generated.Archimed;
import models.ArchimedConfig;
import play.mvc.*;

import utils.FileInfo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.bind.JAXBException;

import static play.libs.Json.*;

public class ConfigManager extends Controller {

    public static Result getConfigList(){

        try {
            ArrayList<FileInfo> list = new ArrayList<>();
            for (File file : ArchimedConfig.listConfigFile()) {
                list.add(new FileInfo(file));
            }
            return ok(toJson(list));
        }catch (IOException e){
            return internalServerError(toJson(e));
        }

    }

    public static Result getConfig(String filename, String format){
        try {
            if(format.equals("xml")){
                return ok((new ArchimedConfig(filename)).path);
            }else{
                return ok(toJson((new ArchimedConfig(filename)).getXmlConf()));
            }
        }catch (JAXBException e){
            return internalServerError(toJson(e));
        }
    }

    public static Result saveConfig(String filename){

        try {
            ArchimedConfig o = new ArchimedConfig(filename);
            o.setXmlConf(fromJson(request().body().asJson(), Archimed.class));
            o.save();
            return ok(toJson(o.getXmlConf()));
        }catch (Exception e){
            return internalServerError(toJson(e));
        }

    }


    public static Result preflight(String all) {
        response().setHeader("Access-Control-Allow-Origin", "*");
        response().setHeader("Allow", "*");
        response().setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS");
        response().setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Referer, User-Agent");
        return ok();
    }

}
