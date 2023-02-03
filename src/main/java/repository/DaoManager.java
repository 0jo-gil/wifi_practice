package repository;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import dto.WifiDto;
import util.SqlUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DaoManager {
    public int insertWifiResult(JsonElement wifiResultList){
        Gson gson = new Gson();

        List<WifiDto> result = Arrays.asList(gson.fromJson(wifiResultList, WifiDto[].class));

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
        } catch (Exception e){

            System.out.println(e);
            return -1;
        }
        return result.size();
    }


    public List<WifiDto> searchWifiListInDb(double LAT, double LNT, int itemCount){
        int dataIndex = 0;

        WifiDto wifiDto = null;
        try(
                Connection connection = ConnManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SqlUtil.SELECT_WIFI_RESULT)
                ) {

            preparedStatement.setDouble(dataIndex++, LAT);
            preparedStatement.setDouble(dataIndex++, LNT);

            ResultSet rs = preparedStatement.executeQuery();

            List<WifiDto> wifiDtoList = new ArrayList<>();


            while(rs.next()){
                wifiDto = WifiDto.builder()
                            .DISTANCE(rs.getString(wifiDto.getDISTANCE()))
                            .X_SWIFI_MGR_NO(rs.getString(wifiDto.getX_SWIFI_MGR_NO()))
                            .X_SWIFI_WRDOFC(rs.getString(wifiDto.getX_SWIFI_WRDOFC()))
                            .X_SWIFI_MAIN_NM(rs.getString(wifiDto.getX_SWIFI_MAIN_NM()))
                            .X_SWIFI_ADRES1(rs.getString(wifiDto.getX_SWIFI_ADRES1()))
                            .X_SWIFI_ADRES2(rs.getString(wifiDto.getX_SWIFI_ADRES2()))
                            .X_SWIFI_INSTL_FLOOR(rs.getString(wifiDto.getX_SWIFI_INSTL_FLOOR()))
                            .X_SWIFI_INSTL_TY(rs.getString(wifiDto.getX_SWIFI_INSTL_TY()))
                            .X_SWIFI_INSTL_MBY(rs.getString(wifiDto.getX_SWIFI_INSTL_MBY()))
                            .X_SWIFI_SVC_SE(rs.getString(wifiDto.getX_SWIFI_SVC_SE()))
                            .X_SWIFI_CMCWR(rs.getString(wifiDto.getX_SWIFI_CMCWR()))
                            .X_SWIFI_CNSTC_YEAR(rs.getString(wifiDto.getX_SWIFI_CNSTC_YEAR()))
                            .X_SWIFI_INOUT_DOOR(rs.getString(wifiDto.getX_SWIFI_INOUT_DOOR()))
                            .X_SWIFI_REMARS3(rs.getString(wifiDto.getX_SWIFI_REMARS3()))
                            .LNT(rs.getString(wifiDto.getLNT()))
                            .LAT(rs.getString(wifiDto.getLAT()))
                            .WORK_DTTM(rs.getString(wifiDto.getWORK_DTTM()))
                        .build();

                    wifiDtoList.add(wifiDto);
            }
            return wifiDtoList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
