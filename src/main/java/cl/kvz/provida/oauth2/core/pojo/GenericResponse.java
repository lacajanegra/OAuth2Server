/**
 * 
 */
package cl.kvz.provida.oauth2.core.pojo;

import java.io.Serializable;

/**
 * @author Juan Francisco Maldonado Leon 
 * 
 * @desc Respuesta generica para servicios rest.
 * 
 * Codigos de error 
 * [0] = OK 
 * [99] = ERROR no contralado ( Sin conexion BD / Sin conexion servicios externos, etc )
 * [1-98] = Custom Errors
 * 
 *  Glosa Response 
 *  Glasa informativa utilizada para el caso de exito o de error.
 *  
 *  Response 
 *  Objecto para devolver informacion adicional.
 */
public class GenericResponse implements Serializable
{
	private static final long serialVersionUID = 2474571247263287888L;
	
	public static final int COD_SUCCESS = 0;
	public static final int COD_ERROR = 99;
	private int codResponse;
	private String glosaResponse;
	private Object data;
	
	
	/**
	 * 
	 */
	public GenericResponse()
	{
		
	}
	
	
	/**
	 * 
	 */
	public GenericResponse( int codResponse, String glosaResponse )
	{
		System.out.println("entre a generic response cod: " + codResponse + " glosa: " + glosaResponse);
		this.codResponse = codResponse;
		this.glosaResponse = glosaResponse;
	}
	
	
	/**
	 * 
	 */
	public GenericResponse( int codResponse, String glosaResponse, Object data )
	{
		this.codResponse = codResponse;
		this.glosaResponse = glosaResponse;
		this.data = data;
	}
	
	
	
	
	public GenericResponse(Object data) {
		
		this.codResponse = COD_SUCCESS;
		this.data = data;
	}


	/**
	 * @return the codResponse
	 */
	public int getCodResponse() {
		return codResponse;
	}
	/**
	 * @param codResponse the codResponse to set
	 */
	public void setCodResponse(int codResponse) {
		this.codResponse = codResponse;
	}
	/**
	 * @return the glosaResponse
	 */
	public String getGlosaResponse() {
		return glosaResponse;
	}
	/**
	 * @param glosaResponse the glosaResponse to set
	 */
	public void setGlosaResponse(String glosaResponse) {
		this.glosaResponse = glosaResponse;
	}
	/**
	 * @return the response
	 */
	public Object getData() {
		return data;
	}
	/**
	 * @param response the response to set
	 */
	public void setData(Object data) {
		this.data = data;
	}
	
	
	
	
	
}
