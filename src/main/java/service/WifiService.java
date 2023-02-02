package service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dto.WifiDto;
import repository.ConnManager;
import repository.DaoManager;
import util.ConfigUtil;
import util.RequestUtil;
import util.SqlUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class WifiService {
    private  final DaoManager daoManager = new DaoManager();
    // 데이터 위치 기반 저장, 데이터 저장 갯수 반환
    public int requestInsertWifiResult(){
        int startNum = 1;
        int endNum = 1000;

        JsonObject resJson = null;

        try{
            for (int i = 0; i < 15; i++)    {
                String sendResult = RequestUtil.sendRequest(startNum, endNum);

                resJson = (JsonObject) JsonParser.parseString(sendResult);

                startNum += 1000;
                endNum += 1000;
            }
            return resJson.get(ConfigUtil.getApiConfig().getName())
                    .getAsJsonObject().get("list_total_count").getAsInt();
        } catch (Exception e){
            return -1;
        }
    }

    public List<WifiDto> searchWifiListInDb (double LAT, double LNT, int itemCount){
        try{
            List<WifiDto> wifiResultList = daoManager.searchWifiListInDb(LAT, LNT, itemCount);
            return wifiResultList;
        } catch (Exception e){
            return null;
        }
    }

//    public static void main(String[] args) {
//        WifiService test = new WifiService();
//
//        System.out.println(test.requestInsertWifiResult());
//
//    }

}
