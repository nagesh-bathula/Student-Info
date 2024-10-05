package Student.demo.dao;

import Student.demo.entity.Student;

public interface StudentDAO 
{
	void save(Student student);
	Student findById(int id);
	void deleteAll();
}
