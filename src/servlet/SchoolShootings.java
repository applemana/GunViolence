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

import dao.SchoolDao;
import model.School;
import model.Shooting;
import model.State;


@WebServlet("/schoolshootings")
public class SchoolShootings extends HttpServlet {

  private SchoolDao schoolDao;

  @Override
  public void init() throws ServletException {
    schoolDao = SchoolDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<>();
    req.setAttribute("messages", messages);

    String stateAbb = req.getParameter("state");

    List<School> shootings = new ArrayList<>();
    try {
      if (stateAbb != null && !stateAbb.trim().isEmpty()) {
        State state = new State(stateAbb);
        req.setAttribute("stateAbb", stateAbb);
        shootings = schoolDao.getStatesLastTenSchoolShootings(state);
        if (!shootings.isEmpty()) {
          messages.put("title", shootings.get(0).getCity().getState().getStateName() + "'s School Shootings");
        } else {
          messages.put("title", "No school shootings recorded for this state");
        }
      } else {
        messages.put("title", "Invalid state abbreviation.");
      }

    } catch (SQLException e) {
      e.printStackTrace();
      throw new IOException(e);
    }
    req.setAttribute("stateAbb", stateAbb);
    req.setAttribute("shootings", shootings);
    req.getRequestDispatcher("/SchoolShootings.jsp").forward(req, resp);
  }
}