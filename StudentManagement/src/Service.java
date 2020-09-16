import java.security.KeyStore.Entry;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class Service {
	private Connection con;

	public Service(Connection con) {
		super();
		this.con = con;
	}
	ResultSet executeQuery(String query) {
		ResultSet data = null;
		try {
			data = con.createStatement().executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}
	void execute(String query) {
		try {
			con.createStatement().execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	ResultSet getInfo(String table, String... strings){
		String query = "SELECT ";
		for (String column : strings) {
			query += column+",";
		}
		query = query.substring(0, query.length()-1);
		query += " FROM manage_student."+table;
//		System.out.println(query);
		return executeQuery(query);
	}
	ArrayList<String> getIDs(String table, String idColumnName){
		ArrayList<String> ids = new ArrayList<String>();
		ResultSet data = getInfo(table, idColumnName);
		try {
			while(data.next()) {
				ids.add(data.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ids;
	}
	public void updateNumberOfStudent() {
		ArrayList<String> ids = getIDs("departments", "DeptID");
		for (String id : ids) {
			String query = "UPDATE manage_student.departments SET NoOfStudents = (SELECT COUNT(*) from manage_student.students WHERE DeptID = \""+id+"\") WHERE DeptID = \""+id+"\"";
			execute(query);
//			System.out.println(query);
		}
		System.out.println("Updated number of students in each department!");
		ResultSet result = getInfo("departments", "*");
		try {
			while(result.next()) {
				System.out.println("Department \""+result.getString(2)+"\" has "+result.getInt(3)+ " students");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public float getAverageScore(ResultSet data) {
		HashMap<String, Float> validScores = new HashMap<String, Float>();
		try {
			while(data.next()) {
				String subject = data.getString(1);
				float score = data.getFloat(2);
				if(validScores.containsKey(subject)) {
					if (validScores.get(subject)<score) {
						validScores.put(subject, score);
					}
				}
				else {
					validScores.put(subject, score);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(validScores.size()==0) {
			return 0;
		}
		float sum = 0;
		for (java.util.Map.Entry<String, Float> entry: validScores.entrySet()) {
			sum+=entry.getValue();
		}
		return sum/validScores.size();
	}
	public void updateAverageScore() {
		ArrayList<String> ids = getIDs("students", "StudentID");
		for (String id : ids) {
			String query = "SELECT CourseID, Mark FROM manage_student.results WHERE StudentID = \""+id+"\"";
			ResultSet data = executeQuery(query);
			float averageScore = getAverageScore(data);
			query = "UPDATE manage_student.students SET AverageScore = "+averageScore+" WHERE StudentID = \""+id+"\"";
//			System.out.println(query);
			execute(query);
		}
		System.out.println("Updated average score of students!");
		ResultSet students = getInfo("students", "*");
		try {
			while(students.next()) {
				System.out.println("Student "+students.getString(2)+" "+students.getString(3)+"("+students.getString(1)+") has CPA = "+students.getFloat(9));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
