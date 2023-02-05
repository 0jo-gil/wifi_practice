package service;

import dto.LocationDateDto;
import repository.DaoManager;

import java.util.List;

public class LocationService {

    private final DaoManager daoManager = new DaoManager();

    public void requestInsertLocationHistory(double LAT, double LNT){
        try{
            daoManager.insertLocationHistory(LAT, LNT);
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public List<LocationDateDto> searchLocationHistory(){
        try{
            List<LocationDateDto> locationDateList = daoManager.searchLocationDate();
            return locationDateList;
        } catch (Exception e){
            return null;
        }
    }

    public void deleteLocationHistory(int id){
        try{
            daoManager.deleteLocationHistory(id);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
