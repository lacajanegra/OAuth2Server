package cl.kvz.provida.oauth2.core.ldap;

import java.io.Serializable;
import java.rmi.RemoteException;

import javax.xml.rpc.holders.StringHolder;

import org.apache.log4j.Logger;

import aswbmttd.CL_1008_WSNACAR.pub.services.wws_validaClaveAcceso.Wws_validaClaveAcceso_PortTypeProxy;
import aswbmttd.CL_1008_WSNACAR.pub.services.wws_validaClaveAcceso.holders.ControlHolder;
import cl.kvz.provida.oauth2.core.util.Constants;

public class LdapService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4777971172511593017L;
	
	private static final transient Logger LOGGER = Logger.getLogger( LdapService.class );
	
	
	public boolean checkCredentials(String rut, String dv, String pass) {
		ControlHolder control = new ControlHolder();
		javax.xml.rpc.holders.StringHolder validado = new StringHolder();
		try {

			Wws_validaClaveAcceso_PortTypeProxy port = new Wws_validaClaveAcceso_PortTypeProxy(
					Constants.LDAP_URL);
			port.ws_validaClaveAcceso(rut, dv, pass, validado,
					control);
		} catch (RemoteException e) {
			LOGGER.error(e);
		}
		return validado.value.equals(Constants.LDAP_S);
	}
}
