package main;

import dBService.DBService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.ClazzUtil;

public class Main {

    private static final Logger logger = LogManager.getLogger(ClazzUtil.returnClazzName());

    public static void main(String[] args) throws Exception {

        DBService dbService = new DBService();
        dbService.printConnectionInfo();

        System.out.println(dbService.addUser("Olha"));






        dbService.closeConnection();


    }
}
