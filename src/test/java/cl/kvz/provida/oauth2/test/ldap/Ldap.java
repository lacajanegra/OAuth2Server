package cl.kvz.provida.oauth2.test.ldap;

import javax.xml.rpc.holders.StringHolder;

import org.junit.BeforeClass;
import org.junit.Test;

import aswbmttd.CL_1008_WSNACAR.pub.services.wws_validaClaveAcceso.Wws_validaClaveAcceso_PortTypeProxy;
import aswbmttd.CL_1008_WSNACAR.pub.services.wws_validaClaveAcceso.holders.ControlHolder;
import cl.kvz.provida.oauth2.core.util.Constants;

public class Ldap {
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void test() {
		try {
			ControlHolder control = new ControlHolder();
			javax.xml.rpc.holders.StringHolder validado = new StringHolder();
			Wws_validaClaveAcceso_PortTypeProxy port = new Wws_validaClaveAcceso_PortTypeProxy(Constants.LDAP_URL);
			port.ws_validaClaveAcceso("3555499", "8", "111h1", validado, control);
			System.out.println(validado.value);
			
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}

}
