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

import dao.GunDao;
import model.Gun;
import model.Shooting;


@WebServlet("/guns")
public class Guns extends HttpServlet {

  private GunDao gunDao;

  @Override
  public void init() throws ServletException {
    gunDao = GunDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<>();
    req.setAttribute("messages", messages);

    String shootingId = req.getParameter("shooting");
    if (shootingId == null || shootingId.trim().isEmpty()) {
      messages.put("title", "Invalid shootingId.");
    } else {
      messages.put("title", "Guns for shootingId " + shootingId);
    }

    List<Gun> guns;
    try {
      Shooting shooting = new Shooting(Integer.parseInt(shootingId));
      guns = gunDao.getGunsUsedInShooting(shooting);
    } catch (SQLException e) {
      e.printStackTrace();
      throw new IOException(e);
    }
    req.setAttribute("guns", guns);
    req.getRequestDispatcher("/Guns.jsp").forward(req, resp);
  }
}