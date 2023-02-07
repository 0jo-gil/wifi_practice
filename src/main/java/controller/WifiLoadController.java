package controller;


import com.google.gson.Gson;
import dto.WifiDto;
import service.WifiService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/get-wifi-list")
public class WifiLoadController extends HttpServlet {
    private final WifiService wifiService = new WifiService();
    public void init(){
        System.out.println("Wifi Controller Start!");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
        Double LNT = Double.parseDouble(req.getParameter("lnt"));
        Double LAT = Double.parseDouble(req.getParameter("lat"));


        List<WifiDto> wifiDtoList = wifiService.searchWifiListInDb(LAT, LNT, 0);

        if(wifiDtoList.isEmpty()){
           wifiDtoList = wifiService.searchWifiListDirect(LAT, LNT);
        }

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().print(new Gson().toJson(wifiDtoList));
    }

    public void destroy(){
        System.out.println("Wifi Controller End!");
    }
}
