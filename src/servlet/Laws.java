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

import dao.LawDao;
import model.Law;
import model.State;


@WebServlet("/laws")
public class Laws extends HttpServlet {

  private LawDao lawDao;

  @Override
  public void init() throws ServletException {
    lawDao = LawDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<>();
    req.setAttribute("messages", messages);

    String stateAbb = req.getParameter("state");

    List<Law> laws;
    try {
      State state = new State(stateAbb);
      laws = lawDao.getLawsByState(state);
    } catch (SQLException e) {
      e.printStackTrace();
      throw new IOException(e);
    }

    if (stateAbb == null || stateAbb.trim().isEmpty()) {
      messages.put("title", "Invalid state abbreviation.");
    } else {
      messages.put("title", laws.get(0).getState().getStateName() + "'s Gun Laws");
    }

    req.setAttribute("laws", laws);
    req.getRequestDispatcher("/Laws.jsp").forward(req, resp);
  }
}