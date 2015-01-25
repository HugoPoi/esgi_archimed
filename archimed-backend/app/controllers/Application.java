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

public class Application extends Controller {

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

    public static Result getConfig(String filename){
        try {
            return ok(toJson((new ArchimedConfig(filename)).getXmlConf()));
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
}
