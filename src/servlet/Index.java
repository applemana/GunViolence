package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ShootingDao;
import dao.StateDao;
import model.State;


@WebServlet("/index")
public class Index extends HttpServlet {

  private StateDao stateDao;

  @Override
  public void init() throws ServletException {
    stateDao = StateDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    List<State> states;
    try {
      states = stateDao.getAllStates();
    } catch (SQLException e) {
      e.printStackTrace();
      throw new IOException(e);
    }
    req.setAttribute("states", states);
    req.getRequestDispatcher("/Index.jsp").forward(req, resp);
  }
}
