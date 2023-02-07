package util;

public class DistanceUtil {
    public String distance(double curLat, double curLnt, double baseLat, double baseLnt){
        double theta = curLnt - baseLnt;
        double dist = Math.sin(deg2rad(curLat)) * Math.sin(deg2rad(baseLat)) + Math.cos(deg2rad(curLat)) * Math.cos(deg2rad(baseLat)) * Math.cos(deg2rad(theta));

        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;

        dist = dist * 1.609344;



        return String.format("%.4f", dist);
    }

    private static double deg2rad(double deg){
        return deg * Math.PI / 180.0;
    }

    private  static double rad2deg(double rad){
        return rad * 180.0 / Math.PI;
    }


}
