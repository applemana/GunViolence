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

import dao.ParticipantDao;
import model.Participant;
import model.Shooting;


@WebServlet("/participants")
public class Participants extends HttpServlet {

  private ParticipantDao participantDao;

  @Override
  public void init() throws ServletException {
    participantDao = ParticipantDao.getInstance();
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
      messages.put("title", "Participants for shootingId " + shootingId);
    }

    List<Participant> participants;
    try {
      Shooting shooting = new Shooting(Integer.parseInt(shootingId));
      participants = participantDao.getParticipantsOfShooting(shooting);
    } catch (SQLException e) {
      e.printStackTrace();
      throw new IOException(e);
    }
    req.setAttribute("participants", participants);
    req.getRequestDispatcher("/Participants.jsp").forward(req, resp);
  }
}

