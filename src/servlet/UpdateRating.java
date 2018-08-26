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
import model.Rating;


@WebServlet("/ratingupdate")
public class UpdateRating extends HttpServlet {

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

    // Retrieve user and validate.
    String ratingId = req.getParameter("ratingid");
    if (ratingId == null || ratingId.trim().isEmpty()) {
      messages.put("success", "Please enter a valid ratingId.");
    } else {
      try {
        Rating rating = ratingDao.getRatingById(Integer.parseInt(ratingId));
        if (rating == null) {
          messages.put("success", "RatingId does not exist.");
        }
        req.setAttribute("rating", rating);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
    }

    req.getRequestDispatcher("/UpdateRating.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    // Retrieve user and validate.
    String ratingId = req.getParameter("ratingid");
    if (ratingId == null || ratingId.trim().isEmpty()) {
      messages.put("success", "Please enter a valid ratingId.");
    } else {
      try {
        Rating rating = ratingDao.getRatingById(Integer.parseInt(ratingId));
        if (rating == null) {
          messages.put("success", "RatingId does not exist. No update to perform.");
        } else {
          String newGrade = req.getParameter("grade");
          String newReason = req.getParameter("reason");
          if (newGrade == null || newGrade.trim().isEmpty()) {
            messages.put("success", "Please enter a valid Grade.");
          } else if (newReason == null || newReason.trim().isEmpty()) {
            messages.put("success", "Please enter a valid Reason for Grade.");
          } else {
            rating = ratingDao.updateRating(rating, Rating.Grade.valueOf(new Rating().getGradeFromString(newGrade)), newReason);
            messages.put("success", "Successfully updated ratingId " + ratingId);
          }
        }
        req.setAttribute("rating", rating);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
    }

    req.getRequestDispatcher("/UpdateRating.jsp").forward(req, resp);
  }
}
