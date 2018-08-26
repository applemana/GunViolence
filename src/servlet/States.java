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

import dao.StateDao;
import model.State;


@WebServlet("/states")
public class States extends HttpServlet {

  private StateDao stateDao;

  @Override
  public void init() throws ServletException {
    stateDao = StateDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<>();
    req.setAttribute("messages", messages);

    messages.put("title", "States");

    List<State> states;
    try {
      states = stateDao.getAllStates();
    } catch (SQLException e) {
      e.printStackTrace();
      throw new IOException(e);
    }
    req.setAttribute("states", states);
    req.getRequestDispatcher("/States.jsp").forward(req, resp);
  }
}
