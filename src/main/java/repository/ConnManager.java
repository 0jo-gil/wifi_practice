package repository;

import org.mariadb.jdbc.MariaDbPoolDataSource;
import util.ConfigUtil;
import util.SqlUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ConnManager {
    private static MariaDbPoolDataSource mariaDbPoolDataSource = new MariaDbPoolDataSource();

    static {
        try{
            mariaDbPoolDataSource.setUser(ConfigUtil.getDbConfig().getUser());
            mariaDbPoolDataSource.setPassword(ConfigUtil.getDbConfig().getPassword());
            mariaDbPoolDataSource.setUrl(ConfigUtil.getDbConfig().getUrl());

            try(
                    Connection connection = mariaDbPoolDataSource.getConnection();
                    PreparedStatement wifiResultPreparedStatement = connection.prepareStatement(SqlUtil.CREATE_WIFI_RESULT);
                    PreparedStatement locationHistoryPreparedStatement = connection.prepareStatement(SqlUtil.CREATE_LOCATION_HISTORY);
            ) {
                wifiResultPreparedStatement.executeUpdate();
                locationHistoryPreparedStatement.executeUpdate();
            } catch(Exception e){
                throw new RuntimeException(e);
            }
        } catch (Exception e){

        }
    }


    private ConnManager(){

    }

    public static Connection getConnection(){
        try{
            return mariaDbPoolDataSource.getConnection();
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public static void connectionClose(){
        mariaDbPoolDataSource.close();
    }


}
