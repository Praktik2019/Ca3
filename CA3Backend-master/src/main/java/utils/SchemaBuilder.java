/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.HashMap;
import javax.persistence.Persistence;

/**
 *
 * @author Mads
 */
public class SchemaBuilder {
    public static void main(String[] args) {
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.sql-load-script-source", "scripts/ClearDB.sql");
        Persistence.generateSchema("pu", properties);
        
        properties.remove("javax.persistence.sql-load-script-source");
        Persistence.generateSchema("pu", properties);
    }
}
