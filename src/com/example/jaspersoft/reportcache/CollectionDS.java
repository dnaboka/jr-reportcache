package com.example.jaspersoft.reportcache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRDesignField;

public class CollectionDS {


    static final Logger LOGGER = Logger.getLogger(CollectionDS.class.getName());

    public static JRBeanCollectionDataSource jdbc2CollectionDS(JRDataSource res) throws JRException {

        JRBeanCollectionDataSource ds = null;

        LOGGER.fine("Inside JRBeanCollectionDataSource");

        JRDesignField f = new JRDesignField();

        f.setName("brand_name");
        f.setValueClass(java.lang.String.class);
        f.setValueClassName("java.lang.String");

        JRDesignField f2 = new JRDesignField();
        f2.setName("product_name");
        f2.setValueClass(java.lang.String.class);
        f2.setValueClassName("java.lang.String");

        JRDesignField f3 = new JRDesignField();
        f3.setName("gross_weight");
        f3.setValueClass(java.lang.Float.class);
        f3.setValueClassName("java.lang.Float");

        LOGGER.fine("After JRDesignField");

        Collection<CBean> coll = new ArrayList<CBean>();

        if (res instanceof JRResultSetDataSource) {

            while (((JRResultSetDataSource) res).next()) {

                String brand_name = (String) res.getFieldValue(f);
                String product_name = (String) res.getFieldValue(f2);
                Float gross_weight = (Float) res.getFieldValue(f3);

                CBean product = new CBean();
                product.setbrand_name(brand_name);
                product.setproduct_name(product_name);
                product.setgross_weight(gross_weight);

                coll.add(product);

            }

        } else {

            throw new JRException("Your Data Source MUST be of type JRResultSetDataSource in JSS or JRS, or JSControlledResultSetDataSource in JRS");

        }

        ds = new JRBeanCollectionDataSource(coll);
        LOGGER.fine("JRBeanCollectionDataSource ds: " + ds.getRecordCount());

        return ds.cloneDataSource();

    }

    public static JRBeanCollectionDataSource rewind (JRBeanCollectionDataSource res) throws JRException {

        // JRBeanCollectionDataSource ds = res.cloneDataSource();
        res.moveFirst();
        return res;

    }

}

