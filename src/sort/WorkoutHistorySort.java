package sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import beans.WorkoutHistory;

public class WorkoutHistorySort {

	public static ArrayList<WorkoutHistory> sort(String sortingStrategy, ArrayList<WorkoutHistory> history) {
		switch(sortingStrategy) {
			case "NAME_ASC":
				return sortByNameAsc(history);
			case "NAME_DESC":
				return sortByNameDesc(history);
			case "DATE_ASC":
				return sortByDateAsc(history);
			case "DATE_DESC":
				return sortByDateDesc(history);
			case "ADDITIONAL_PAYMENT_ASC":
				return sortByAdditionalPaymentAsc(history);
			case "ADDITIONAL_PAYMENT_DESC":
				return sortByAdditionalPaymentDesc(history);
			default:
				return history;
		}
	}
	
	private static ArrayList<WorkoutHistory> sortByAdditionalPaymentAsc(ArrayList<WorkoutHistory> history) {
		Collections.sort(history, new Comparator<WorkoutHistory>(){

			  public int compare(WorkoutHistory h1, WorkoutHistory h2)
			  {
			     return h1.getWorkout().getAdditionalPayment() < h2.getWorkout().getAdditionalPayment() ? -1 : 1;
			  }
			});
		return history;
	}
	
	private static ArrayList<WorkoutHistory> sortByAdditionalPaymentDesc(ArrayList<WorkoutHistory> history) {
		Collections.reverse(sortByAdditionalPaymentAsc(history));
		return history;
	}
	
	private static ArrayList<WorkoutHistory> sortByNameAsc(ArrayList<WorkoutHistory> history) {
		Collections.sort(history, new Comparator<WorkoutHistory>(){

			  public int compare(WorkoutHistory h1, WorkoutHistory h2)
			  {
			     return h1.getWorkout().getSportFacility().getName().compareToIgnoreCase(h2.getWorkout().getSportFacility().getName());
			  }
			});
		return history;
	}
	
	private static ArrayList<WorkoutHistory> sortByNameDesc(ArrayList<WorkoutHistory> history) {
		Collections.reverse(sortByNameAsc(history));
		return history;
	}
	
	private static ArrayList<WorkoutHistory> sortByDateAsc(ArrayList<WorkoutHistory> facilities) {
		Collections.sort(facilities, new Comparator<WorkoutHistory>(){

			  public int compare(WorkoutHistory h1, WorkoutHistory h2)
			  {
			     return h1.getCheckInDateTime().compareTo(h2.getCheckInDateTime());
			  }
			});
		return facilities;
	}
	
	private static ArrayList<WorkoutHistory> sortByDateDesc(ArrayList<WorkoutHistory> history) {
		Collections.reverse(sortByDateAsc(history));
		return history;
	}
	
}
