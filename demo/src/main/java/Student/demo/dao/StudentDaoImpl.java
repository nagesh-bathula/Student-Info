package Student.demo.dao;

import java.math.BigDecimal;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import Student.demo.entity.Student;
import jakarta.persistence.EntityManager;

@Repository
public class StudentDaoImpl implements StudentDAO {

	// field entity manager
	private EntityManager entityManager;
	// inject entity manager constructor

	public StudentDaoImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	@Transactional
	public void save(Student student) {
	    // Get the next value from the sequence
	    BigDecimal nextIdDecimal = (BigDecimal) entityManager.createNativeQuery("SELECT student_id_seq.NEXTVAL FROM dual").getSingleResult();
	    int nextId = nextIdDecimal.intValue(); // Convert BigDecimal to int
	    student.setId(nextId); // Set the student ID from the sequence
	    entityManager.merge(student);
	}



	
	@Override
	public Student findById(int id) {
	    return entityManager.find(Student.class, id);
	}

	@Override
	@Transactional
    public void deleteAll() {
        entityManager.createQuery("DELETE FROM Student").executeUpdate(); // Delete all rows
    }

}
