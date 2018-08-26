package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CityDao;
import dao.RatingDao;
import model.City;
import model.CityRating;
import model.State;


@WebServlet("/cities")
public class Cities extends HttpServlet {
  private RatingDao ratingDao;

  @Override
  public void init() throws ServletException {
    ratingDao = RatingDao.getInstance();

  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<>();
    req.setAttribute("messages", messages);

    String stateAbb = req.getParameter("state");
    String stateName;
    List<City> cities = new ArrayList<>();
    Map<Integer, CityRating> eachCitiesRating;
    try {
      State state = new State(stateAbb);
      eachCitiesRating = ratingDao.getRatingsPerState(state);

      for (Map.Entry<Integer, CityRating> city : eachCitiesRating.entrySet()) {
        cities.add(city.getValue().getCity());
      }

    } catch (SQLException e) {
      e.printStackTrace();
      throw new IOException(e);
    }

    stateName = cities.get(0).getState().getStateName();

    if (stateAbb == null || stateAbb.trim().isEmpty()) {
      messages.put("title", "Invalid state abbreviation.");
    } else {
      messages.put("title", "Cities in " + stateName);
    }
    req.setAttribute("cities", cities);
    req.setAttribute("ratings", eachCitiesRating);
    req.getRequestDispatcher("/Cities.jsp").forward(req, resp);


  }
}
