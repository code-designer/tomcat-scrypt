#Tomcat SCrypt
Tomcat [SCrypt](https://en.wikipedia.org/wiki/Scrypt) is inspired of Tomcat BCrypt.
This is an implementation of Tomcat CredentialHandler wrapping [SCrypt](https://mvnrepository.com/artifact/com.lambdaworks/scrypt/1.4.0) (1.4.0), 
Java implementation of scrypt algorithm.

#How to use it
- Export SCryptCredentialHandler as jar file
- Copy to TOMCAT_HOME/lib
- Nest SCryptCredentialHandler in your realm
	<Context>
		<Realm className="org.apache.catalina.realm.DataSourceRealm"
			[...]
			>
			<CredentialHandler className="org.labtest.encrypt.SCryptoCredentialHandler"/>
		</Realm>
	</Context>
	
PS: Do not forget SCrypt jar file
#How to get CredentialHandler in code for hashing plaintext
We can get credential handler through 
- application context
	CredentialHandler ch = (CredentialHandler)application.getAttribute(Globals.CREDENTIAL_HANDLER);
	String stored = ch.mutate(plaintext);
- reflection
	Class<?> globals = Class.forName("org.apache.catalina.Globals");
	String attrName = (String)globals.getDeclaredField("CREDENTIAL_HANDLER").get(null);
	Object ch = context.getAttribute(attrName);
	Class<?> ich = Class.forName("org.apache.catalina.CredentialHandler");
	Method mutateMethod = ich.getMethod("mutate", new Class[] { String.class} );
	String stored = (String)mutateMethod.invoke(plaintext);