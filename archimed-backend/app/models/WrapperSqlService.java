package models;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import generated.Archimed;
import generated.ConfigElement;
import play.Play;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Created on 17/01/15.
 */
public class WrapperSqlService {
    private static WrapperSqlService singleton;

    public static WrapperSqlService getInstance() throws Exception {
        if(singleton != null){
            //return singleton;
        }
        singleton = new WrapperSqlService();
        return singleton;
    }

    private WrapperSqlService() throws Exception {

        mainConfig = new ArchimedConfig(Play.application().configuration().getString("archimed.wrapperconfpath"));

        groupConfig = Iterables.find(mainConfig.getXmlConf().getEnvironnements().getGroup(), new Predicate<Archimed.Environnements.Group>() {
            @Override
            public boolean apply(Archimed.Environnements.Group group) {
                return group.isActive();
            }
        });

        wrapperConfig = Iterables.find(groupConfig.getWrapper(), new Predicate<ConfigElement>() {
            @Override
            public boolean apply(ConfigElement configElement) {
                return configElement.getName().equals(Play.application().configuration().getString("archimed.wrappername"));
            }
        });

        Class.forName(this.getParam("driver.class"));

    }

    public Connection getConnection() throws SQLException{

        String url = this.getParam("url"), user = this.getParam("user"), password = this.getParam("password");
        return DriverManager.getConnection( url, user, password );

    }

    public ArchimedConfig mainConfig;
    public Archimed.Environnements.Group groupConfig;
    public ConfigElement wrapperConfig;

    public String getParam(final String name){

        return Iterables.find(wrapperConfig.getParam(), new Predicate<ConfigElement.Param>() {
            @Override
            public boolean apply(ConfigElement.Param param) {
                return name.equals(param.getName());
            }
        }).getValue();

    }
}
