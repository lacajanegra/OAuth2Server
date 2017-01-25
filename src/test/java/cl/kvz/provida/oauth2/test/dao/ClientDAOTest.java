package cl.kvz.provida.oauth2.test.dao;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.util.Assert;

public class ClientDAOTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void list() {
		try {
//			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
//					"spring2.xml");
//
//			IClientDAO dao = context.getBean(IClientDAO.class);

			// User entity = new User();
			// entity.setName("Manuel Castillo");
			// entity.setId("mcastillo");
			// entity.setUserCreation("APP");
			// entity.setDateCreation(new Date());
			//
			// dao.save(entity);

			// System.out.println("Entidad::" + entity);

//			List<Client> list = dao.list();
//
//			for (Client c : list) {
//				
//				
//				JSONObject jsonObject = new JSONObject(c);
//				String myJson = jsonObject.toString();
//				System.out.println("User List::" + myJson);
//			}

			Assert.doesNotContain("prueba", "i");
//			context.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
