package pdc_project2.util;

public class ScoreUtil {

	public static String getGrade(float score) {
		if (score >= 93 && score <= 100) {
			return "A+";
		} else if (score >= 88 && score < 93) {
			return "A";
		} else if (score >= 83 && score < 88) {
			return "A-";
		} else if (score >= 78 && score < 83) {
			return "B+";
		} else if (score >= 72 && score < 78) {
			return "B";
		} else if (score >= 68 && score < 72) {
			return "B-";
		} else if (score >= 63 && score < 68) {
			return "C+";
		} else if (score >= 58 && score < 63) {
			return "C";
		} else if (score >= 53 && score < 58) {
			return "C-";
		} else if (score >= 50 && score < 53) {
			return "D";
		} else if (score >= 40 && score < 50) {
			return "F";
		} else {
			return "O";
		}
	}

	public static float getCredit(float score) {
		if (score >= 93 && score <= 100) {
			return 4.0f;
		} else if (score >= 88 && score < 93) {
			return 3.7f;
		} else if (score >= 83 && score < 88) {
			return 3.2f;
		} else if (score >= 78 && score < 83) {
			return 3.0f;
		} else if (score >= 72 && score < 78) {
			return 2.7f;
		} else if (score >= 68 && score < 72) {
			return 2.2f;
		} else if (score >= 63 && score < 68) {
			return 2.0f;
		} else if (score >= 58 && score < 63) {
			return 1.7f;
		} else if (score >= 53 && score < 58) {
			return 1.2f;
		} else if (score >= 50 && score < 53) {
			return 1.0f;
		} else if (score >= 40 && score < 50) {
			return 0.0f;
		} else {
			return 0.0f;
		}
	}
}
