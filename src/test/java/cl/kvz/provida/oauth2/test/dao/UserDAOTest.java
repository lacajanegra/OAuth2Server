package cl.kvz.provida.oauth2.test.dao;

import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cl.kvz.provida.oauth2.admin.dao.IUserDAO;
import cl.kvz.provida.oauth2.admin.model.User;


public class UserDAOTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void test() {
		try{
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

		IUserDAO dao =   context.getBean(IUserDAO.class);

		User entity = new User();
		entity.setName("Manuel Castillo");
		entity.setId("mcastillo");
		entity.setUserCreation("APP");
		entity.setDateCreation(new Date());

		dao.save(entity);

		System.out.println("Entidad::" + entity);

		List<User> list = dao.list();

		for (User p : list) {
			System.out.println("User List::" + p);
		}
		
		context.close();
		}catch(Exception e){
			 e.printStackTrace();
		}
	}

}
