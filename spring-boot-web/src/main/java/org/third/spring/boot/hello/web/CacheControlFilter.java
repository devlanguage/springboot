package org.third.spring.boot.hello.web;


public class CacheControlFilter {
	public static void main(String[] args) {
		for (java.security.Provider p : java.security.Security.getProviders()) {
			System.out.println(p.getName() + ":  ");
			p.getServices().forEach(s -> {
				System.out.println("  "+s.getType() + "_" + s.getAlgorithm());
			});
			

		}
	}
}
