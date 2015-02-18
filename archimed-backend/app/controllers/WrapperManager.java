package controllers;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import generated.Archimed;
import models.ArchimedConfig;
import play.mvc.Controller;
import play.mvc.Result;

import javax.xml.bind.JAXBException;
import static play.libs.Json.*;

/**
 * Created on 15/01/15.
 */
public class WrapperManager extends Controller {

    public static Result createInstance(String filename, String wrapperName){

        try {
            ArchimedConfig config = new ArchimedConfig(filename);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        //exec jar
        // exec "-Dhttp.port -Darchimed.wrappername='test1' -Darchimed.wrapperconfigpath='/hjhjdfh/jkhsjdk/jshd'"
        //ping instance


        return ok();
    }

    public static Result doRequest(String filename, String xpath){

        try {
            ArchimedConfig config = new ArchimedConfig(filename);


        Archimed.Environnements.Group activeGroup = Iterables.find(config.getXmlConf().getEnvironnements().getGroup(), new Predicate<Archimed.Environnements.Group>() {
            @Override
            public boolean apply(Archimed.Environnements.Group group) {
                return group.isActive();
            }
        });
        //search the wrapper
        //do the request
        //return result set
        } catch (Exception e) {
            return badRequest(toJson(e));
        }

        return ok();
    }

}
