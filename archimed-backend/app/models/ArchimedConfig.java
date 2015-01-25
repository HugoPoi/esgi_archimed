package models;

import generated.Archimed;
import play.Play;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

public class ArchimedConfig {

    public String id;

    public String name;

    private Archimed xmlConf;

    public Archimed getXmlConf() throws JAXBException {
        if(xmlConf == null){
            JAXBContext jc = JAXBContext.newInstance("generated");
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            xmlConf = (Archimed) unmarshaller.unmarshal(path);
        }
        return xmlConf;
    }

    public void setXmlConf(Archimed xmlConf) {
        this.xmlConf = xmlConf;
    }

    public File path;

    public ArchimedConfig(File file) throws JAXBException{

        path = file;


    }

    public ArchimedConfig(String filename){

        path = Play.application().getFile(Play.application().configuration().getString("archimed.configpath") + "/" + filename);

    }

    public static File[] listConfigFile(){

        return Play.application().getFile(Play.application().configuration().getString("archimed.configpath")).listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".xml");
            }
        });

    }

    public void save() throws JAXBException, FileNotFoundException{
        JAXBContext jc = JAXBContext.newInstance("generated");
        Marshaller marshaller = jc.createMarshaller();
        marshaller.marshal(xmlConf, new FileOutputStream(path));
    }
}