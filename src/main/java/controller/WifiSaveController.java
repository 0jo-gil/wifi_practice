package controller;


import com.google.gson.Gson;
import service.WifiService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/save-wifi-list")
public class WifiSaveController extends HttpServlet {
    public void init() {
        System.out.println("Wifi Save Controller Start!");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int wifiResult = new WifiService().requestInsertWifiResult();

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().print(wifiResult);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    public void destroy(){
        System.out.println("Wifi Save Controller End!");
    }

}
