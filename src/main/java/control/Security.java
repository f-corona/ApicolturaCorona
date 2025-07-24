package control;


import java.util.regex.Pattern;

public class Security {
	
	private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#+=\\(\\)\\^?&])[A-Za-z\\d$@$!%*#+=\\(\\)\\^?&]{3,}$");
	
	// Metodo per generare l'hash di una stringa (L07)
	public static String toHash(String password) {
        String hashString = null;
        try {
            java.security.MessageDigest digest = java.security.MessageDigest.getInstance("SHA-512");
            byte[] hash = digest.digest(password.getBytes(java.nio.charset.StandardCharsets.UTF_8));
            hashString = "";
            for (int i = 0; i < hash.length; i++) { 
                hashString += Integer.toHexString((hash[i] & 0xFF) | 0x100).substring(1,3);
            }
        } catch (java.security.NoSuchAlgorithmException e) {
            System.err.println(e);
        }
        return hashString;
    }
    
	public static boolean validateEmail(String email) {
    	if (email == null || email.isEmpty()) return false; 
        return Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$").matcher(email).matches();
    }

	public static boolean validatePassword(String password) {
        if (password == null || password.isEmpty()) return false;
        return PASSWORD_PATTERN.matcher(password).matches();
    }

}
