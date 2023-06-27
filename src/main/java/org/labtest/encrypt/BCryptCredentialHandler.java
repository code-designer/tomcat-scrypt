package org.labtest.encrypt;

import java.security.SecureRandom;

import org.apache.catalina.CredentialHandler;
import org.mindrot.jbcrypt.BCrypt;

import com.lambdaworks.crypto.SCryptUtil;

public class BCryptCredentialHandler implements CredentialHandler
{
	private SecureRandom random;
	private int rounds = 12;
	@Override
	public boolean matches(String inputCredentials, String storedCredentials) {
		if(inputCredentials == null || inputCredentials.length() == 0 ||
				storedCredentials == null || storedCredentials.length() == 0)
			return false;
		return BCrypt.checkpw(inputCredentials, storedCredentials);
	}

	@Override
	public String mutate(String inputCredentials) {
		return BCrypt.hashpw(inputCredentials, getSalt());
	}
	
	private String getSalt() {
		if (random == null)
			return BCrypt.gensalt(rounds);
		
		return BCrypt.gensalt(rounds, random);
	}
	
	public void setRandom(SecureRandom random) {
		this.random = random;
	}
	
	public void setRound(int rounds) {
		this.rounds = rounds;
	}
	
	public static void main( String[] args )
    {
        
    }
}
