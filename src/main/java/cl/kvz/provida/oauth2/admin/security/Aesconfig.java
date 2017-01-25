package cl.kvz.provida.oauth2.admin.security;

public class Aesconfig {

	private String alg;
	private String ci;
	private String key;
	private String iv;
	private static Aesconfig aesconfig;
	
	public Aesconfig() {
		this.alg = "AES";
		this.ci  = "AES/CBC/PKCS5Padding";
		this.key = "F56HYTU8E2A5YT61";
		this.iv  = "3IGIE94UTUASJ318";
	}
	
	public Aesconfig(String alg, String ci, String key, String iv) {
		super();
		this.alg = alg;
		this.ci = ci;
		this.key = key;
		this.iv = iv;
	}
	
	public String getAlg() {
		return alg;
	}
	public void setAlg(String alg) {
		this.alg = alg;
	}
	public String getCi() {
		return ci;
	}
	public void setCi(String ci) {
		this.ci = ci;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getIv() {
		return iv;
	}
	public void setIv(String iv) {
		this.iv = iv;
	}
	
	public static Aesconfig get(){
		if(aesconfig ==null){
			aesconfig = new  Aesconfig();
		}
		return aesconfig;
	}
}
