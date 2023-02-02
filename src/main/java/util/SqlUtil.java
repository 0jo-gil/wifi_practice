package util;

public class SqlUtil {
    // 위치정보 검색 히스토리 저장
    public static final String INSERT_WIFI_HISTORY = " insert into wifi_history " +
                                                    " (LAT, LNT, SEARCH_DATE) " +
                                                    " values(?,?,?) ";
    public static final String INSERT_WIFI_RESULT = " insert into wifi_result " +
                                                    " (X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, X_SWIFI_ADRES1, X_SWIFI_ADRES2, X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, X_SWIFI_SVC_SE, X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3, LNT, LAT, WORK_DTTM) " +
                                                    " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
    public static final String SELECT_WIFI_RESULT = " SELECT " +
                                                    " ST_DISTANCE_SPHERE(POINT(?, ?), POINT(LAT, LNT)) as distance, " +
                                                    " X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, X_SWIFI_ADRES1, X_SWIFI_ADRES2, X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, X_SWIFI_SVC_SE, X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3, LNT, LAT, WORK_DTTM " +
                                                    " from wifi_result " +
                                                    " order by distance " +
                                                    " limit 1, 20 ";

    public static final String SELECT_WIFI_HISTORY = " select * " +
                                                    " from wifi_history ";

    public static final String DELETE_WIFI_HISTORY = " delete from wifi_history " +
                                                    " where ID = ? ";
}
