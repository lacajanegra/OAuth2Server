package cl.kvz.provida.oauth2.core.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import cl.kvz.provida.oauth2.core.pojo.FormUser;
import cl.kvz.provida.oauth2.core.util.Validator;

/**
 * Servlet implementation class InitApp
 */
@WebServlet("/InitApp")
public class InitApp extends HttpServlet {

	private static final long serialVersionUID = -8725986142406219426L;
	private static final transient Logger LOGGER = Logger
			.getLogger(InitApp.class);

	/**
	 * 
	 * @param archivoProperties
	 */
	private void configure(String archivoProperties) {
		try {
			if (!archivoProperties.startsWith("/")) {
				ClassLoader loader = Thread.currentThread()
						.getContextClassLoader();
				URL url = loader.getResource(archivoProperties);
				DOMConfigurator.configure(url);
			} else {
				DOMConfigurator.configure(archivoProperties);
			}
		} catch (Exception exception) {
			System.out.println("*************************************");
			exception.printStackTrace();
			LOGGER.error("Ocurrio un error en el servlet", exception);
			return;
		}
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InitApp() {
		super();
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {

		//property.cargarProperties("../../bin/u01/data/oauth/conf/config.properties");
//		String archivoProperties = config.getInitParameter("log4j-file");
//		this.configure(archivoProperties);

		LOGGER.info("--------------------------------------------------------");
		LOGGER.info("---              APP INICIALIZADA                    ---");
		LOGGER.info("--------------------------------------------------------");
		
	}

}
