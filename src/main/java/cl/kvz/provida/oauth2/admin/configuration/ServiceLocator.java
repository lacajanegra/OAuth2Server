package cl.kvz.provida.oauth2.admin.configuration;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import cl.kvz.provida.oauth2.core.util.Constants;

public class ServiceLocator {

	private ApplicationContext _context = null;

	private static final transient Logger LOGGER = Logger
			.getLogger(ServiceLocator.class);
	private static ServiceLocator _instace = null;

	private ServiceLocator() {
	}

	public synchronized static ServiceLocator getInstance() {
		if (_instace == null || _instace._context == null) {
			try {
				_instace = new ServiceLocator();
				// _instace._context = new ClassPathXmlApplicationContext(
				// ServiceLocator._CONFIG);
				_instace._context = new FileSystemXmlApplicationContext(
						Constants.SPRING_CONFIG);
			} catch (Exception e) {
				LOGGER.error(e);
			}
		}
		return _instace;
	}

	public Object getBean(String id) {
		return this._context.getBean(id);
	}

	public <T> T getBean(Class<T> class1) {
		return this._context.getBean(class1);
	}

}
