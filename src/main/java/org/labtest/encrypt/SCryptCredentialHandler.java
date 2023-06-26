package org.labtest.encrypt;

import org.apache.catalina.CredentialHandler;

import com.lambdaworks.crypto.SCryptUtil;

public class SCryptCredentialHandler implements CredentialHandler {
	private int n = 16384; //iterations
	private int r = 8; // memory cost
	private int p = 1; 
	@Override
	public boolean matches(String inputCredentials, String storedCredentials) {
		if (inputCredentials == null || inputCredentials.length() == 0 || 
				storedCredentials == null || storedCredentials.length() == 0)
			return false;
		return SCryptUtil.check(inputCredentials, storedCredentials);
	}

	@Override
	public String mutate(String inputCredentials) {
		return SCryptUtil.scrypt(inputCredentials, n, r, p);
	}
	
	public void setCPUCost(int cost) {
		this.n = cost;
	}
	
	public void setMemoryCost(int cost) {
		this.r = cost;
	}
	
	public void setParallelization(int cost) {
		this.p = cost;
	}

}
