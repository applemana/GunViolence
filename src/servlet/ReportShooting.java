package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CityDao;
import dao.FatalDao;
import dao.NonFatalDao;
import dao.SchoolDao;
import dao.ShootingDao;
import dao.StateDao;
import model.City;
import model.Fatal;
import model.NonFatal;
import model.School;
import model.Shooting;
import model.State;

@WebServlet("/ReportShooting")
public class ReportShooting extends HttpServlet {

  private ShootingDao shootingDao;
  private CityDao cityDao;
  private SchoolDao schoolDao;
  private FatalDao fatalDao;
  private NonFatalDao nonFatalDao;

  @Override
  public void init() throws ServletException {
    shootingDao = ShootingDao.getInstance();
    cityDao = CityDao.getInstance();
    schoolDao = SchoolDao.getInstance();
    fatalDao = FatalDao.getInstance();
    nonFatalDao = NonFatalDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
      IOException {

    req.getRequestDispatcher("/ReportShooting.jsp").forward(req, resp);
  }

  /**
   * Parses a date from a string value into a Java Util Date
   *
   * @param dateString the date to be parsed into a Date
   *
   * @return Java Util Date
   *
   * @throws ParseException when the string is not in the expected format.
   */
  private Date parseDate(String dateString) throws ParseException {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    return formatter.parse(dateString);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String shootingType = req.getParameter("shootingType");
    String cityId = req.getParameter("cityId");
    String date = req.getParameter("date");
    String numGunsString = req.getParameter("numberOfGuns");
    String characteristics = req.getParameter("characteristics");
    String numInjuredString = req.getParameter("numberInjured");
    String numKilledString = req.getParameter("numberKilled");
    String typeSchool = req.getParameter("typeOfSchool");

    if (cityId == null || cityId.equals("") || numGunsString == null || numGunsString.equals("")
        || numInjuredString == null || numInjuredString.equals("") || numKilledString == null
        || numKilledString.equals("")) {
      req.getRequestDispatcher("/ReportShooting.jsp").forward(req, resp);

    } else {
      try {
        int numGuns = Integer.parseInt(numGunsString);
        int numKilled = Integer.parseInt(numKilledString);
        int numInjured = Integer.parseInt(numInjuredString);
        Date parsedDate = this.parseDate(date);

        City city = cityDao.getCityFromCityId(Integer.parseInt(cityId));
        Shooting shooting = new Shooting(1, city, parsedDate, numGuns, characteristics);

        shootingDao.create(shooting);
        shooting = shootingDao.getNewestShooting();

        switch (shootingType) {
          case "School":
            if (!typeSchool.equals("Select a School Type")) {
              School school = new School(shooting.getShootingId(), city, parsedDate, numGuns,
                  characteristics, School.SchoolType.valueOf(typeSchool), numKilled);
              schoolDao.create(school);
            }
            break;
          case "Fatal":
            Fatal fatal = new Fatal(shooting.getShootingId(), city, parsedDate, numGuns,
                characteristics, numKilled, numInjured);
            fatalDao.create(fatal);
            break;
          default:
            NonFatal nonFatal = new NonFatal(shooting.getShootingId(), city, parsedDate, numGuns,
                characteristics, numInjured);
            nonFatalDao.create(nonFatal);
            break;
        }
      } catch (SQLException | ParseException e1) {
        e1.printStackTrace();
      }
      req.getRequestDispatcher("/ReportShooting.jsp").forward(req, resp);
    }
  }
}
