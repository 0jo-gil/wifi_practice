package controller;

import com.google.gson.Gson;
import dto.LocationDateDto;
import service.LocationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/location-history")
public class LocationHistoryController extends HttpServlet {
    private final LocationService locationService = new LocationService();
    @Override
    public void init() throws ServletException {
        System.out.println("Location History Controller Start!");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Double LNT = Double.parseDouble(req.getParameter("lnt"));
        Double LAT = Double.parseDouble(req.getParameter("lat"));

        System.out.println(LNT);
        System.out.println(LAT);

        locationService.requestInsertLocationHistory(LAT, LNT);

        resp.getWriter().print("SUCCESS");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<LocationDateDto> locationDateDtoList = locationService.searchLocationHistory();

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().print(new Gson().toJson(locationDateDtoList));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        locationService.deleteLocationHistory(id);

        resp.getWriter().print("SUCCESS");
    }

    @Override
    public void destroy() {
        System.out.println("Location History Controller End!");
    }
}
