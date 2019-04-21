package org.third;

import org.bouncycastle.jsse.provider.BouncyCastleJsseProvider;

public class CacheControlFilter {
	public static void main(String[] args) {
		for (java.security.Provider p : java.security.Security.getProviders()) {
			System.out.println(p.getName() + ":  ");
			p.getServices().forEach(s -> {
				System.out.println("  "+s.getType() + "_" + s.getAlgorithm());
			});
			
			BouncyCastleJsseProvider bcJsse  = new BouncyCastleJsseProvider();
//			bcJsse.put(key, value)

		}
	}
}
