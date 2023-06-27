package org.labtest.encrypt;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.security.SecureRandom;

import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class BCryptCredentialHandlerTest 
{
	String password = "faveur";
	String encryptedPassword = "$2a$12$efjOaYtFzQ2LQSdkHSHsU.Ao6OeYB6Fnl9BcbzEd.ySsjSlgdZKCW";
	
    @Test
    public void matchesTest()
    {
    	BCryptCredentialHandler bch = new BCryptCredentialHandler();
    	assertFalse(bch.matches(null, null));
    	assertFalse(bch.matches(null, encryptedPassword));
    	assertFalse(bch.matches("", encryptedPassword));
    	assertFalse(bch.matches(password, null));
    	assertFalse(bch.matches(password, ""));
        assertTrue((bch.matches(password, encryptedPassword)) );
    }
    
    public void mutateTest() {
    	BCryptCredentialHandler bch = new BCryptCredentialHandler();
    	assertThatEncrypt(bch);
    }
    
    @Test
    public void encryptWithSecureRandom() {
    	BCryptCredentialHandler bch = new BCryptCredentialHandler();
    	bch.setRandom(new SecureRandom());
    	assertThatEncrypt(bch);
    }
    
    @Test
    public void encryptWithRounds() {
    	BCryptCredentialHandler bch = new BCryptCredentialHandler();
    	bch.setRound(20);
    	assertThatEncrypt(bch);
    }
    
    @Test
    public void encryptWithSecureRandomAndRounds() {
    	BCryptCredentialHandler bch = new BCryptCredentialHandler();
    	bch.setRandom(new SecureRandom());
    	bch.setRound(20);
    	assertThatEncrypt(bch);
    }
    
    private void assertThatEncrypt(BCryptCredentialHandler bch) {
    	String encrypted = bch.mutate(password);
    	assertTrue(bch.matches(password, encrypted));
    }
}
