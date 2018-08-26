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


@WebServlet("/ratingcreate")
public class CreateRating extends HttpServlet {

  private RatingDao ratingDao;

  @Override
  public void init() throws ServletException {
    ratingDao = RatingDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    String cityId = req.getParameter("city");
    if (cityId == null || cityId.trim().isEmpty()) {
      messages.put("title", "Invalid cityId.");
    } else {
      messages.put("title", "Rate the cityId " + cityId);
    }

    req.getRequestDispatcher("/RatingCreate.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    // Retrieve and validate name.
    String cityId = req.getParameter("cityid");
    if (cityId == null || cityId.trim().isEmpty()) {
      messages.put("success", "Invalid City Id");
    } else {
      try {
        City city = new City(Integer.parseInt(cityId));
        Rating existingRating = ratingDao.getRatingForCity(city);

        if (existingRating != null) {
          messages.put("success", "Rating already exists for this city.");
        } else {
          // Create the Rating.
          Rating.Grade grade = Rating.Grade.valueOf(req.getParameter("grade"));
          if (grade == null) {
            messages.put("success", "Grade Entered Invalid.");
          } else {
            String reasonFor = req.getParameter("explanation");
            try {
              Rating rating = new Rating(grade, reasonFor, new City(Integer.parseInt(cityId)));
              ratingDao.create(rating);
              messages.put("success", "Successfully Rate");
            } catch (SQLException e) {
              e.printStackTrace();
              throw new IOException(e);
            }
          }
        }
      } catch (SQLException e1) {
        e1.printStackTrace();
      }
    }

    req.getRequestDispatcher("/RatingCreate.jsp").forward(req, resp);
  }
}
