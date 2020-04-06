package dao;

import bean.Student;

import java.util.List;

public interface StudentMapper {
	void saveStudent (Student stu);
	Student findStudentById (Integer id);
	List<Student> findAllStudent ();
	void deleteStudentById (Integer id);
	void updateStudent (Student stu);
	//
	void insertStudentPhone (Student stu);
	Student findStudentPhone (Integer id);
}
