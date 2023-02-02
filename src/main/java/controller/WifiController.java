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

@WebServlet("/wifi-search")
public class WifiController extends HttpServlet {
    private final WifiService wifiService = new WifiService();
    public void init(){
        System.out.println("Wifi Controller Start!");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  {

        try{
            Double LNT = Double.parseDouble(req.getParameter("LNT"));
            Double LAT = Double.parseDouble(req.getParameter("LAT"));

            List<WifiDto> wifiDtoList = wifiService.searchWifiListInDb(LAT, LNT, 0);

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(new Gson().toJson(wifiDtoList));
        } catch (Exception e){

        }
    }

    public void destroy(){
        System.out.println("Wifi Controller End!");
    }
}
