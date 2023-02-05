package service;

import com.google.gson.JsonElement;
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
        int dataNum = -1;

        JsonObject resJson = null;

        try{
            daoManager.removeWifiList();
            for (int i = 0; i < 20; i++)    {
                String sendResult = RequestUtil.sendRequest(startNum, endNum);

                resJson = (JsonObject) JsonParser.parseString(sendResult);


                startNum += 1000;
                endNum += 1000;

                // 와이파이 리스트 DB 저장
                JsonElement result = resJson.get(ConfigUtil.getApiConfig().getName()).getAsJsonObject().get("row");

                dataNum = daoManager.insertWifiResult(result);
            }

            return dataNum;
        } catch (Exception e){
            System.out.println(e);
            return dataNum;
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

    public List<WifiDto> searchWifiListDirect (double LAT, double LNT){
        int startNum = 1;
        int endNum = 1000;

        JsonObject resJson = null;

        return null;
    }

}
