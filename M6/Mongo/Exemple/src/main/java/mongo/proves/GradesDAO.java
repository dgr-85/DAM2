package mongo.proves;

public interface GradesDAO {
	public void addGrade(Grade grade);

	public Grade findGradeById(Integer id);

	public Grade updateGrade(Grade grade);

	public void deleteGrade(Integer id);
}
