package com.example.jaspersoft.reportcache;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class CollectionDSTester {

    static final Logger LOGGER = Logger.getLogger(CollectionDSTester.class.getName());

    public static void main(String[] args) throws SQLException, JRException {
        CollectionDS collectionDS = new CollectionDS();

        String url = "jdbc:postgresql://localhost/foodmart?loggerLevel=TRACE&loggerFile=pgjdbc.log";
        Properties props = new Properties();
        props.setProperty("user","postgres");
        props.setProperty("password","postgres");

        Connection conn = DriverManager.getConnection(url, props);

        JRBeanCollectionDataSource jrBeanCollectionDataSource = collectionDS.jdbc2CollectionDS(new JRResultSetDataSource(conn.createStatement().executeQuery("SELECT brand_name, product_name, gross_weight FROM product")));
        JRBeanCollectionDataSource jrBeanCollectionDataSource1 = collectionDS.rewind(jrBeanCollectionDataSource);

        //LOGGER.fine("CollectionDSTester ==== JRBeanCollectionDataSource ds: " + jrBeanCollectionDataSource.getRecordCount());
        //LOGGER.fine("CollectionDSTester ==== JRBeanCollectionDataSource1 ds: " + jrBeanCollectionDataSource1.getRecordCount());

        //System.out.println("CollectionDSTester ==== JRBeanCollectionDataSource ds: " + jrBeanCollectionDataSource.getRecordCount());
        System.out.println("CollectionDSTester ==== JRBeanCollectionDataSource1 ds: " + jrBeanCollectionDataSource1.getRecordCount());

    }
}

