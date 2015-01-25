package controllers;

import models.ArchimedConfig;
import play.mvc.*;

import utils.FileInfo;
import views.html.*;

import models.Person;

import play.data.Form;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import play.db.ebean.Model;

import javax.xml.bind.JAXBException;

import static play.libs.Json.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render());
    }

    public static Result addPerson() {
    	Person person = Form.form(Person.class).bindFromRequest().get();
    	person.save();
    	return redirect(routes.Application.index());
    }

    public static Result getPersons() {
    	List<Person> persons = new Model.Finder(String.class, Person.class).all();
    	return ok(toJson(persons));
    }

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
            return ok(toJson(ArchimedConfig.archimedFromFileName(filename).xmlConf));
        }catch (JAXBException e){
            return internalServerError(toJson(e));
        }
    }
}
