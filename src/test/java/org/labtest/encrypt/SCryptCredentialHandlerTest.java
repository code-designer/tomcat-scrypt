package org.labtest.encrypt;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class SCryptCredentialHandlerTest {
	private String password = "faveur";
	private String encryptedPassword = "$s0$e0801$TLJgx8eJ7dIDC/bOQHbxBw==$+6r29s+/R9QVSsV131fDVba4Eu0CJYhzsLkJcuHNnDg=";
	@Test
	public void matchesTest() {
		SCryptCredentialHandler sch = new SCryptCredentialHandler();
		assertFalse(sch.matches(null, null));
		assertFalse(sch.matches("", encryptedPassword));
		assertFalse(sch.matches(password, null));
		assertFalse(sch.matches(password, ""));
		assertTrue(sch.matches(password, encryptedPassword));
	}
	
	@Test
	public void mutateTest() {
		SCryptCredentialHandler sch = new SCryptCredentialHandler();
		assertThatEncrypt(sch);
	}
	
	@Test
	public void encryptWithCPUCost() {
		SCryptCredentialHandler sch = new SCryptCredentialHandler();
		sch.setCPUCost(4096);
		assertThatEncrypt(sch);
	}
	
	@Test
	public void encryptWithMemoryCost() {
		SCryptCredentialHandler sch = new SCryptCredentialHandler();
		sch.setMemoryCost(4);
		assertThatEncrypt(sch);
	}
	
	@Test 
	public void encryptWithParallelization() {
		SCryptCredentialHandler sch = new SCryptCredentialHandler();
		sch.setParallelization(2);
		assertThatEncrypt(sch);
	}
	
	@Test
	public void encryptWithAllParameters() {
		SCryptCredentialHandler sch = new SCryptCredentialHandler();
		sch.setCPUCost(4096);
		sch.setMemoryCost(4);
		sch.setParallelization(2);
		assertThatEncrypt(sch);
	}
	
	private void assertThatEncrypt(SCryptCredentialHandler sch) {
		String encrypted = sch.mutate(password);
		assertTrue(sch.matches(password, encrypted));
	}

}
