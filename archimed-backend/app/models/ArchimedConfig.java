package models;

import generated.Archimed;
import play.Play;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

public class ArchimedConfig {

    public String id;

    public String name;

    public Archimed xmlConf;

    public ArchimedConfig(File file) throws JAXBException{

        JAXBContext jc = JAXBContext.newInstance("generated");
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        xmlConf = (Archimed) unmarshaller.unmarshal(file);
    }

    public static File[] listConfigFile(){

        return Play.application().getFile(Play.application().configuration().getString("archimed.configpath")).listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".xml");
            }
        });

    }

    public static ArchimedConfig archimedFromFileName(String filename) throws JAXBException{

        return new ArchimedConfig(Play.application().getFile(Play.application().configuration().getString("archimed.configpath") + "/" + filename));

    }
}