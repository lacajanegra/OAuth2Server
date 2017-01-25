package cl.kvz.provida.oauth2.admin.security;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import static org.apache.commons.codec.binary.Base64.decodeBase64;
import static org.apache.commons.codec.binary.Base64.encodeBase64;

public class Security {

	public static String encrypt(String cleartext) throws Exception {
		Cipher cipher = Cipher.getInstance(Aesconfig.get().getCi());
		SecretKeySpec skeySpec = new SecretKeySpec(Aesconfig.get().getKey().getBytes(), Aesconfig.get().getAlg());
		IvParameterSpec ivParameterSpec = new IvParameterSpec(Aesconfig.get().getIv().getBytes());
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivParameterSpec);
		byte[] encrypted = cipher.doFinal(cleartext.getBytes());
		return new String(encodeBase64(encrypted));
	}

	public static String decrypt(String encrypted) throws Exception {
		Cipher cipher = Cipher.getInstance(Aesconfig.get().getCi());
		SecretKeySpec skeySpec = new SecretKeySpec(Aesconfig.get().getKey().getBytes(), Aesconfig.get().getAlg());
		IvParameterSpec ivParameterSpec = new IvParameterSpec(Aesconfig.get().getIv().getBytes());
		byte[] enc = decodeBase64(encrypted);
		cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivParameterSpec);
		byte[] decrypted = cipher.doFinal(enc);
		return new String(decrypted);
	}
}