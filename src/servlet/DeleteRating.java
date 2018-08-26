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


@WebServlet("/ratingdelete")
public class DeleteRating extends HttpServlet {

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
    // Provide a title and render the JSP.
    messages.put("title", "Delete Rating");
    req.getRequestDispatcher("/DeleteRating.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<>();
    req.setAttribute("messages", messages);

    // Retrieve and validate name.
    String ratingId = req.getParameter("ratingid");
    if (ratingId == null || ratingId.trim().isEmpty()) {
      messages.put("title", "Invalid ratingId");
      messages.put("disableSubmit", "true");
    } else {
      // Delete the Rating.
      Rating rating = new Rating(Integer.parseInt(ratingId));
      try {
        rating = ratingDao.delete(rating);
        // Update the message.
        if (rating == null) {
          messages.put("title", "Successfully deleted ratingId " + ratingId);
          messages.put("disableSubmit", "true");
        } else {
          messages.put("title", "Failed to delete ratingId " + ratingId);
          messages.put("disableSubmit", "false");
        }
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
    }

    req.getRequestDispatcher("/DeleteRating.jsp").forward(req, resp);
  }
}
