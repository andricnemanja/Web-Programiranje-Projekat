package sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import beans.SportFacility;

public class SportFacilitySort {

	public static ArrayList<SportFacility> sort(String sortingStrategy, ArrayList<SportFacility> facilities) {
		switch(sortingStrategy) {
			case "RATING_ASC":
				return sortByRatingAsc(facilities);
			case "RATING_DESC":
				return sortByRatingDesc(facilities);
			case "NAME_ASC":
				return sortByNameAsc(facilities);
			case "NAME_DESC":
				return sortByNameDesc(facilities);
			case "LOCATION_ASC":
				return sortByLocationAsc(facilities);
			case "LOCATION_DESC":
				return sortByLocationDesc(facilities);
			default:
				return facilities;
		}
	}
	
	private static ArrayList<SportFacility> sortByRatingAsc(ArrayList<SportFacility> facilities) {
		Collections.sort(facilities, new Comparator<SportFacility>(){

			  public int compare(SportFacility s1, SportFacility s2)
			  {
			     return s1.getAverageRating() < s2.getAverageRating() ? -1 : 1;
			  }
			});
		return facilities;
	}
	
	private static ArrayList<SportFacility> sortByRatingDesc(ArrayList<SportFacility> facilities) {
		Collections.reverse(sortByRatingAsc(facilities));
		return facilities;
	}
	
	private static ArrayList<SportFacility> sortByNameAsc(ArrayList<SportFacility> facilities) {
		Collections.sort(facilities, new Comparator<SportFacility>(){

			  public int compare(SportFacility s1, SportFacility s2)
			  {
			     return s1.getName().compareToIgnoreCase(s2.getName());
			  }
			});
		return facilities;
	}
	
	private static ArrayList<SportFacility> sortByNameDesc(ArrayList<SportFacility> facilities) {
		Collections.reverse(sortByNameAsc(facilities));
		return facilities;
	}
	
	private static ArrayList<SportFacility> sortByLocationAsc(ArrayList<SportFacility> facilities) {
		Collections.sort(facilities, new Comparator<SportFacility>(){

			  public int compare(SportFacility s1, SportFacility s2)
			  {
			     return s1.getLocation().getCity().compareToIgnoreCase(s2.getLocation().getCity());
			  }
			});
		return facilities;
	}
	
	private static ArrayList<SportFacility> sortByLocationDesc(ArrayList<SportFacility> facilities) {
		Collections.reverse(sortByLocationAsc(facilities));
		return facilities;
	}
	
}
