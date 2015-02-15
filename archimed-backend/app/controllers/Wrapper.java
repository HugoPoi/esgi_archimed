package controllers;

import play.mvc.Controller;
import play.mvc.Result;

import java.util.Date;

import static play.libs.Json.*;

/**
 * Created by hugo on 15/02/15.
 */
public class Wrapper extends Controller {

    public static Result doRequest(){

        //return xml result set
        return ok();
    }

    public static Result ping(){
        return ok(toJson(new Object(){
            public String status = "ok";
        }));
    }
}
