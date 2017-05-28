package by.itacademy.controllers;

import by.itacademy.LocationsHandler;
import by.itacademy.pojos.Location;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Yury V. on 28.05.17.
 */

@WebServlet("/index.html")
public class MainPageController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/db_connection_test.jsp");

        LocationsHandler locationsHandler = new LocationsHandler();
        Location location = locationsHandler.getLocationById(1L);
        if (location == null) {
            locationsHandler.addNewLocationInDatabase("Minsk", "27.525773, 53.89079");
            location = locationsHandler.getLocationById(1L);
        }
        req.setAttribute("location", location);

        dispatcher.forward(req, resp);

    }
}
