package repository;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import dto.LocationDateDto;
import dto.WifiDto;
import util.DistanceUtil;
import util.SqlUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;

public class DaoManager {
    private static Gson gson = new Gson();

    public int insertWifiResult(JsonElement wifiResultList){

        List<WifiDto> result = Arrays.asList(gson.fromJson(wifiResultList, WifiDto[].class));

        int dataCount = -1;

        try(
                Connection connection = ConnManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SqlUtil.INSERT_WIFI_RESULT)
                ){

            for (WifiDto wifiDto : result){
                preparedStatement.setString(1, wifiDto.getX_SWIFI_MGR_NO());
                preparedStatement.setString(2, wifiDto.getX_SWIFI_WRDOFC());
                preparedStatement.setString(3, wifiDto.getX_SWIFI_MAIN_NM());
                preparedStatement.setString(4, wifiDto.getX_SWIFI_ADRES1());
                preparedStatement.setString(5, wifiDto.getX_SWIFI_ADRES2());
                preparedStatement.setString(6, wifiDto.getX_SWIFI_INSTL_FLOOR());
                preparedStatement.setString(7, wifiDto.getX_SWIFI_INSTL_TY());
                preparedStatement.setString(8, wifiDto.getX_SWIFI_INSTL_MBY());
                preparedStatement.setString(9, wifiDto.getX_SWIFI_SVC_SE());
                preparedStatement.setString(10, wifiDto.getX_SWIFI_CMCWR());
                preparedStatement.setString(11, wifiDto.getX_SWIFI_CNSTC_YEAR());
                preparedStatement.setString(12, wifiDto.getX_SWIFI_INOUT_DOOR());
                preparedStatement.setString(13, wifiDto.getX_SWIFI_REMARS3());
                preparedStatement.setDouble(14, Double.parseDouble(wifiDto.getLNT()));
                preparedStatement.setDouble(15, Double.parseDouble(wifiDto.getLAT()));
                preparedStatement.setString(16, wifiDto.getWORK_DTTM());

                preparedStatement.addBatch();
                preparedStatement.clearParameters();
            }

            preparedStatement.executeBatch();


            dataCount = countWifiList();

            return dataCount;
        } catch (Exception e){
            System.out.println(e);
            return dataCount;
        }
    }
    public static int countWifiList(){
        try(
                Connection connection = ConnManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SqlUtil.COUNT_WIFI_RESULT);
                ResultSet resultSet = preparedStatement.executeQuery()
                ){
            int count = -1;

            while(resultSet.next()){
                count = resultSet.getInt("COUNT");
            }

            return count;
        } catch (Exception e){
            return -1;
        }
    }
    public void removeWifiList(){
        try(
                Connection connection = ConnManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SqlUtil.DELETE_WIFI_RESULT)
                ){
            preparedStatement.executeUpdate();
        } catch (Exception e){
            System.out.println(e);
        }
    }
    public List<WifiDto> searchWifiListInDb(double LAT, double LNT, int itemCount){
        try(
                Connection connection = ConnManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SqlUtil.SELECT_WIFI_RESULT)
                ) {

            preparedStatement.setDouble(1, LNT);
            preparedStatement.setDouble(2, LAT);

            ResultSet rs = preparedStatement.executeQuery();
            List<WifiDto> wifiDtoList = new ArrayList<>();


            while(rs.next()){
                Double DISTANCE = rs.getDouble("DISTANCE") / 1000;
                String X_SWIFI_MGR_NO = rs.getString("X_SWIFI_MGR_NO");
                String X_SWIFI_WRDOFC = rs.getString("X_SWIFI_WRDOFC");
                String X_SWIFI_MAIN_NM = rs.getString("X_SWIFI_MAIN_NM");
                String X_SWIFI_ADRES1 = rs.getString("X_SWIFI_ADRES1");
                String X_SWIFI_ADRES2 = rs.getString("X_SWIFI_ADRES2");
                String X_SWIFI_INSTL_FLOOR = rs.getString("X_SWIFI_INSTL_FLOOR");
                String X_SWIFI_INSTL_TY = rs.getString("X_SWIFI_INSTL_TY");
                String X_SWIFI_INSTL_MBY = rs.getString("X_SWIFI_INSTL_MBY");
                String X_SWIFI_SVC_SE = rs.getString("X_SWIFI_SVC_SE");
                String X_SWIFI_CMCWR = rs.getString("X_SWIFI_CMCWR");
                String X_SWIFI_CNSTC_YEAR = rs.getString("X_SWIFI_CNSTC_YEAR");
                String X_SWIFI_INOUT_DOOR = rs.getString("X_SWIFI_INOUT_DOOR");
                String X_SWIFI_REMARS3 = rs.getString("X_SWIFI_REMARS3");
                String LNTStr = rs.getString("LNT");
                String LATStr = rs.getString("LAT");
                String WORK_DTTM = rs.getString("WORK_DTTM");


                WifiDto dto = new WifiDto();

                dto.setDISTANCE(String.format("%.4f", DISTANCE));
                dto.setX_SWIFI_MGR_NO(X_SWIFI_MGR_NO);
                dto.setX_SWIFI_WRDOFC(X_SWIFI_WRDOFC);
                dto.setX_SWIFI_MAIN_NM(X_SWIFI_MAIN_NM);
                dto.setX_SWIFI_ADRES1(X_SWIFI_ADRES1);
                dto.setX_SWIFI_ADRES2(X_SWIFI_ADRES2);
                dto.setX_SWIFI_INSTL_FLOOR(X_SWIFI_INSTL_FLOOR);
                dto.setX_SWIFI_INSTL_TY(X_SWIFI_INSTL_TY);
                dto.setX_SWIFI_INSTL_MBY(X_SWIFI_INSTL_MBY);
                dto.setX_SWIFI_SVC_SE(X_SWIFI_SVC_SE);
                dto.setX_SWIFI_CMCWR(X_SWIFI_CMCWR);
                dto.setX_SWIFI_CNSTC_YEAR(X_SWIFI_CNSTC_YEAR);
                dto.setX_SWIFI_INOUT_DOOR(X_SWIFI_INOUT_DOOR);
                dto.setX_SWIFI_REMARS3(X_SWIFI_REMARS3);
                dto.setLNT(LNTStr);
                dto.setLAT(LATStr);
                dto.setWORK_DTTM(WORK_DTTM);

                wifiDtoList.add(dto);
            }

            return wifiDtoList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<WifiDto> searchWifiListDirect(double LAT, double LNT, JsonElement wifiResultList){
        List<WifiDto> wifiDtoList = new ArrayList<>();
        List<WifiDto> result = Arrays.asList(gson.fromJson(wifiResultList, WifiDto[].class));
        DistanceUtil distanceUtil = new DistanceUtil();

        for (WifiDto wifiDto : result){
            String X_SWIFI_MGR_NO = wifiDto.getX_SWIFI_MGR_NO();
            String X_SWIFI_WRDOFC = wifiDto.getX_SWIFI_WRDOFC();
            String X_SWIFI_MAIN_NM = wifiDto.getX_SWIFI_MAIN_NM();
            String X_SWIFI_ADRES1 = wifiDto.getX_SWIFI_ADRES1();
            String X_SWIFI_ADRES2 = wifiDto.getX_SWIFI_ADRES2();
            String X_SWIFI_INSTL_FLOOR = wifiDto.getX_SWIFI_INSTL_FLOOR();
            String X_SWIFI_INSTL_TY = wifiDto.getX_SWIFI_INSTL_TY();
            String X_SWIFI_INSTL_MBY = wifiDto.getX_SWIFI_INSTL_MBY();
            String X_SWIFI_SVC_SE = wifiDto.getX_SWIFI_SVC_SE();
            String X_SWIFI_CMCWR = wifiDto.getX_SWIFI_CMCWR();
            String X_SWIFI_CNSTC_YEAR = wifiDto.getX_SWIFI_CNSTC_YEAR();
            String X_SWIFI_INOUT_DOOR = wifiDto.getX_SWIFI_INOUT_DOOR();
            String X_SWIFI_REMARS3 = wifiDto.getX_SWIFI_REMARS3();
            String LNTStr = wifiDto.getLNT();
            String LATStr = wifiDto.getLAT();
            String WORK_DTTM = wifiDto.getWORK_DTTM();

            Double DISTANCE = Double.valueOf(distanceUtil.distance(LAT, LNT, Double.parseDouble(LNTStr), Double.parseDouble(LATStr)));


            wifiDto.setDISTANCE(Double.toString(DISTANCE));
            wifiDto.setX_SWIFI_MGR_NO(X_SWIFI_MGR_NO);
            wifiDto.setX_SWIFI_WRDOFC(X_SWIFI_WRDOFC);
            wifiDto.setX_SWIFI_MAIN_NM(X_SWIFI_MAIN_NM);
            wifiDto.setX_SWIFI_ADRES1(X_SWIFI_ADRES1);
            wifiDto.setX_SWIFI_ADRES2(X_SWIFI_ADRES2);
            wifiDto.setX_SWIFI_INSTL_FLOOR(X_SWIFI_INSTL_FLOOR);
            wifiDto.setX_SWIFI_INSTL_TY(X_SWIFI_INSTL_TY);
            wifiDto.setX_SWIFI_INSTL_MBY(X_SWIFI_INSTL_MBY);
            wifiDto.setX_SWIFI_SVC_SE(X_SWIFI_SVC_SE);
            wifiDto.setX_SWIFI_CMCWR(X_SWIFI_CMCWR);
            wifiDto.setX_SWIFI_CNSTC_YEAR(X_SWIFI_CNSTC_YEAR);
            wifiDto.setX_SWIFI_INOUT_DOOR(X_SWIFI_INOUT_DOOR);
            wifiDto.setX_SWIFI_REMARS3(X_SWIFI_REMARS3);
            wifiDto.setLNT(LNTStr);
            wifiDto.setLAT(LATStr);
            wifiDto.setWORK_DTTM(WORK_DTTM);

            wifiDtoList.add(wifiDto);
        }



        return wifiDtoList;
    }


    public void insertLocationHistory(double LAT, double LNT){
        try(
                Connection connection = ConnManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SqlUtil.INSERT_WIFI_HISTORY)
                ){
            LocalDateTime now = LocalDateTime.now();

            preparedStatement.setDouble(1, LAT);
            preparedStatement.setDouble(2, LNT);
            preparedStatement.setString(3, String.valueOf(now));

            preparedStatement.executeUpdate();
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public List<LocationDateDto> searchLocationDate (){
        try(
                Connection connection = ConnManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SqlUtil.SELECT_WIFI_HISTORY)
                ){

            ResultSet rs = preparedStatement.executeQuery();
            List<LocationDateDto> locationList = new ArrayList<>();

            while(rs.next()){
                Integer ID = rs.getInt("id");
                Double LAT = rs.getDouble("LAT");
                Double LNT = rs.getDouble("LNT");
                String DATE = rs.getString("SEARCH_DATE");

                LocationDateDto dto = new LocationDateDto();

                dto.setID(ID);
                dto.setLAT(LAT);
                dto.setLNT(LNT);
                dto.setSEARCH_DATE(DATE);

                locationList.add(dto);
            }

            return locationList;
        } catch(Exception e){
            return null;
        }
    }

    public void deleteLocationHistory(int id){
        try(
                Connection connection = ConnManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SqlUtil.DELETE_WIFI_HISTORY)
                ){
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}
