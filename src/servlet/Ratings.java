package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.RatingDao;
import model.City;
import model.Rating;


@WebServlet("/ratings")
public class Ratings extends HttpServlet {

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

    String cityId = req.getParameter("cityid");
    if (cityId == null || cityId.trim().isEmpty()) {
      messages.put("title", "Invalid cityId.");
    } else {
      messages.put("title", "Rating for cityId " + cityId);
    }

    Rating rating;
    try {
      City city = new City(Integer.parseInt(cityId));
      rating = ratingDao.getRatingForCity(city);
      rating.getCity().getCityName();
    } catch (SQLException e) {
      e.printStackTrace();
      throw new IOException(e);
    }
    req.setAttribute("rating", rating);
    req.getRequestDispatcher("/Ratings.jsp").forward(req, resp);
  }
}
