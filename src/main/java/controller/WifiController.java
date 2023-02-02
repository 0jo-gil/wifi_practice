package controller;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet
public class WifiController extends HttpServlet {


    public void init(){
        System.out.println("Wifi Controller Start!");
    }


    public void destroy(){
        System.out.println("Wifi Controller End!");
    }
}
