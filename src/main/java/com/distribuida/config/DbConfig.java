package com.distribuida.config;


import com.jolbox.bonecp.BoneCPConfig;
import com.jolbox.bonecp.BoneCPDataSource;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.enterprise.inject.Produces;
import org.apache.commons.dbcp2.BasicDataSource;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;


@ApplicationScoped
public class DbConfig {
    @Inject
    @ConfigProperty(name="db.user")
    String dbUser;
    @Inject
    @ConfigProperty(name="db.password")
    String dbPassword;
    @Inject
    @ConfigProperty(name="db.url")
    String dbUrl;


    @PostConstruct
    public void init(){

        //System.out.println("*********************postcosnt");

        //Primera opción, en cualquier clase
        /*
        Config config =  ConfigProvider.getConfig();
        String user = config.getValue("db.user", String.class);
        String password = config.getValue("db.password", String.class);
        String url = config.getValue("db.url", String.class);
        System.out.println("op1: "+user);
        System.out.println("op1: "+password);
        System.out.println("op1: "+url);
        */

        //Segunda opción, solo componentes CDI
        /*
        System.out.println("op2: "+dbUser);
        System.out.println("op2: "+dbPassword);
        System.out.println("op2: "+dbUrl);
        */
    }

    //Pool de conexión
    @Produces
    @ApplicationScoped
    //Hikari
    /*public Handle poolConexion() {
        HikariDataSource config = new HikariDataSource();
        config.setDriverClassName("org.postgresql.Driver");
        config.setJdbcUrl(dbUrl);
        config.setUsername(dbUser);
        config.setPassword(dbPassword);
        //Conexión BD
        con = Jdbi.create(config);
        return con.open();
    }*/
    //Jolbox
   public Handle poolConexion(){
        BoneCPConfig conf = new BoneCPConfig();
        conf.setJdbcUrl(dbUrl);
        conf.setUsername(dbUser);
        conf.setPassword(dbPassword);
        conf.setMinConnectionsPerPartition(5);
        conf.setMaxConnectionsPerPartition(10);
        BoneCPDataSource configuracion = new BoneCPDataSource(conf);
        //Conexión BD
        Jdbi con = Jdbi.create(configuracion);
        return con.open();
    }
    //Apache
    /*public Handle poolConexion() {
        BasicDataSource config = new BasicDataSource();
        config.setUrl(dbUrl);
        config.setUsername(dbUser);
        config.setPassword(dbPassword);
        config.setInitialSize(5);
        config.setMaxTotal(10);
        config.setMaxIdle(5);
        config.setMinIdle(2);
        //Conexión BD
        con = Jdbi.create(config);
        return con.open();
    }*/
}
