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

import dao.CityDao;
import dao.GunDao;
import dao.ParticipantDao;
import dao.ShootingDao;
import model.Shooting;
import model.State;


@WebServlet("/shootings")
public class Shootings extends HttpServlet {

  private ShootingDao shootingDao;
  private CityDao cityDao;
  private GunDao gunDao;
  private ParticipantDao participantDao;

  @Override
  public void init() throws ServletException {
    shootingDao = ShootingDao.getInstance();
    cityDao = CityDao.getInstance();
    gunDao = GunDao.getInstance();
    participantDao = ParticipantDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<>();
    req.setAttribute("messages", messages);

    String stateAbb = req.getParameter("state");
    String cityId = req.getParameter("cityid");
    String type = req.getParameter("type");


    List<Shooting> shootings = new ArrayList<>();

    Map<Shooting, int[]> gunsAndParticipantsPerShooting= new HashMap<>();
    try {
      if (cityId != null && !cityId.trim().isEmpty()) {
        shootings = shootingDao.getShootingsByCityId(Integer.parseInt(cityId));
        messages.put("title", "Shootings for cityId " + cityId);
        stateAbb = cityDao.getStateAbbFromCityId(cityId);
        req.setAttribute("stateAbb", stateAbb);
      } else if (stateAbb != null && !stateAbb.trim().isEmpty()) {
        State state = new State(stateAbb);
        req.setAttribute("stateAbb", stateAbb);
        shootings = shootingDao.getStatesLastTenShootings(state);
        messages.put("title", "10 Most Recent Shootings for " + stateAbb);
      } else {
        messages.put("title", "Invalid city id and state abbreviation.");
      }
      for (Shooting shooting: shootings) {
        int[] numberGunsAndParticipants = new int[2];
        numberGunsAndParticipants[0] = gunDao.getNumberGunsUsedInShooting(shooting);
        numberGunsAndParticipants[1] = participantDao.getNumberParticipantsInShooting(shooting);
        gunsAndParticipantsPerShooting.put(shooting, numberGunsAndParticipants);
      }



    } catch (SQLException e) {
      e.printStackTrace();
      throw new IOException(e);
    }
    req.setAttribute("stateAbb", stateAbb);
    req.setAttribute("shootings", shootings);
    req.setAttribute("gunsAndParticipants", gunsAndParticipantsPerShooting);
    req.getRequestDispatcher("/Shootings.jsp").forward(req, resp);
  }
}
