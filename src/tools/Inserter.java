package tools;

import java.sql.SQLException;
import java.util.List;

import dao.CityDao;
import dao.GunDao;
import dao.LawDao;
import dao.ParticipantDao;
import dao.RatingDao;
import dao.ShootingDao;
import dao.StateDao;
import model.City;
import model.Gun;
import model.Law;
import model.Participant;
import model.Rating;
import model.Shooting;
import model.State;


/**
 * main() runner, used for the app demo.
 * 
 * Instructions:
 * 1. Create a new MySQL schema and then run the CREATE TABLE statements from lecture:
 * http://goo.gl/86a11H.
 * 2. Update ConnectionManager with the correct user, password, and schema.
 */
public class Inserter {

	public static void main(String[] args) throws SQLException {
		// DAO instances.
		StateDao stateDao = StateDao.getInstance();
		LawDao lawDao = LawDao.getInstance();
		CityDao cityDao = CityDao.getInstance();
		ShootingDao shootingDao = ShootingDao.getInstance();
		ParticipantDao participantDao = ParticipantDao.getInstance();
		GunDao gunDao = GunDao.getInstance();
		RatingDao ratingDao = RatingDao.getInstance();

		//This is used to update the ratings for all cities using the calculateRatings Algorithm.
		//ratingDao.calculateRatings();

		State s1 = stateDao.getStateFromAbbreviation("WA");
		System.out.format("Reading state: abb:%s name:%s \n",
			s1.getStateAbbreviation(), s1.getStateName());
		List<State> sList1 = stateDao.getAllStates();
		for(State s : sList1) {
			System.out.format("Reading states: abb:%s name:%s \n",
					s.getStateAbbreviation(), s.getStateName());
		}

		List<Law> lList1 = lawDao.getLawsByState(s1);
		for(Law l : lList1) {
			System.out.format("Reading laws: id:%s effect:%s \n",
					l.getLawId(), l.getEffect().name());
		}

		List<City> cList1 = cityDao.getCitiesByState(s1);
		for(City c : cList1) {
			System.out.format("Reading cities: id:%s name:%s \n",
					c.getCityId(), c.getCityName());
		}

		List<Shooting> shList1 = shootingDao.getStatesLastTenShootings(s1);
		for(Shooting sh : shList1) {
			System.out.format("Reading states last ten shootings: shID:%s cID:%s date:%s \n",
					sh.getShootingId(), sh.getCity().getCityId(), sh.getShootingDate());
		}

		List<Shooting> shList2 = shootingDao.getShootingsByCityId(1);
		for(Shooting sh : shList2) {
			System.out.format("Reading shootings: shID:%s cID:%s date:%s \n",
					sh.getShootingId(), sh.getCity().getCityId(), sh.getShootingDate());
		}

		Shooting sh1 = shootingDao.getShootingFromShootingId(240200);
		List<Participant> pList1 = participantDao.getParticipantsOfShooting(sh1);
		for(Participant p : pList1) {
			System.out.format("Reading participants: age:%s gender:%s name:%s \n",
					p.getAge(), p.getGender(), p.getName());
		}

		List<Gun> gList1 = gunDao.getGunsUsedInShooting(sh1);
		for(Gun g : gList1) {
			System.out.format("Reading guns: id:%s stolen:%s gun type:%s \n",
					g.getGunId(), g.getStolen(), g.getGunType());
		}

		Rating rate1 = ratingDao.getRatingById(1);
		System.out.format("Reading rating: id:%s grade:%s reason:%s cityId:%s \n",
				rate1.getRatingId(), rate1.getGrade().toString(), rate1.getReasonForGrade(), rate1.getCity().getCityId());
	}
}
