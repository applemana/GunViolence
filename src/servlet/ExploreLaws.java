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

@WebServlet("/explorelaws")
public class ExploreLaws extends HttpServlet {

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
    // Provide a title and render the JSP.
    messages.put("title", "Explore Laws");
    req.getRequestDispatcher("/ExploreLaws.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<>();
    req.setAttribute("messages", messages);

    List<Law> laws;


    String type = req.getParameter("type");
    try {
      laws = lawDao.getLawsByType(type);
    } catch (SQLException e) {
      e.printStackTrace();
      throw new IOException(e);
    }
    messages.put("success", "Displaying results for " + type);

    req.setAttribute("laws", laws);

    req.getRequestDispatcher("/ExploreLaws.jsp").forward(req, resp);


  }
}
