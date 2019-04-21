package org.third.common.utils;

import org.third.common.entity.CommonConstants;

public class EnvUtils {
	private static final String apiServerHost = System.getenv("API_SERVER_HOST");
	private static final String apiServerPort = System.getenv("API_SERVER_PORT");
	private static final String apiServerTokenFile = "/var/run/secrets/kubernetes.io/serviceaccount/token";
	//
	private static final String etcdEndpoints = System.getenv("ETCD_ENDPOINT");
	private static final String etcdClientCertificateFile = System.getenv("ETCD_TLS_PEM_FILE");
	private static final String etcdClientCertificatePwd = "changeit";
	//
	private static final String idmServerHost = System.getenv("IDM_SERVER");
	private static final String idmServerPort = System.getenv("IDM_SVC_SERVICE_PORT");
	//
	private static final String externalHost = System.getenv("EXTERNAL_ACCESS_HOST");
	private static final String externalPort = System.getenv("EXTERNAL_ACCESS_PORT");
	private static final String apiRegistryHost = System.getenv("REGISTRY_SERVER");
	private static final String apiRegistryPort =System.getenv("API_REGISTRY_PORT");
	//
	private static final String autopassServerHost = externalHost;
	//private static final String autopassServerPort = "5814";

	private static final String vaultEndpoints = System.getenv("VAULT_ADDR");

	private static final String vaultSigningKey = System.getenv("VAULT_SIGNING_KEY");

	private static final String suiteRegistry = (System.getenv("SUITE_REGISTRY")==null
			||"".equals(System.getenv("SUITE_REGISTRY")))?CommonConstants.LOCAL_SUITE_REGISTRY:System.getenv("SUITE_REGISTRY");

	//	apiRegistryPort
	//********************************
//
//	private static final String apiServerHost = "SGDLITVM0631.hpeswlab.net";
//	private static final String apiRegistryHost ="kube-registry.core";
//	private static final String apiRegistryHost ="SGDLITVM0631.hpeswlab.net";
	//registrypoint
//	private static final String apiRegistryPort = "5000";
//	private static final String apiServerPort = "8443";
//	private static final String apiServerTokenFile = "C:\\Users\\caozhe\\git\\suite-installer\\Project\\src\\main\\resources\\certificates\\token";
////
//	private static final String etcdEndpoints = "SGDLITVM0631.hpeswlab.net";
//	private static final String etcdClientCertificateFile = "C:\\Users\\caozhe\\git\\suite-installer\\Project\\src\\main\\resources\\certificates\\server.p12";
////
//	private static final String etcdClientCertificatePwd = "changeit";
////
//	private static final String idmServerHost = "SGDLITVM0631.hpeswlab.net";
//	private static final String idmServerPort = "5443";
////
//	private static final String autopassServerHost = "SGDLITVM0631.hpeswlab.net";
//	private static final String autopassServerPort = "5443";
//////
//	private static final String externalHost = "localhost";
//	private static final String externalPort = "8080";

	public static String getApiserverhost() {
		return apiServerHost;
	}
	public static String getApiserverport() {
		return apiServerPort;
	}

	//获得获得apiregistryport
	public static String getApiRegistryPort() {
		return apiRegistryPort;
	}

	//获得获得apiregistryport
	public static String getApiRegistryHost() {
		return apiRegistryHost;
	}

	public static String getApiservertokenfile() {
		return apiServerTokenFile;
	}
	public static String getEtcdendpoints() {
		return etcdEndpoints;
	}
	public static String getEtcdclientcertificatefile() {
		return etcdClientCertificateFile;
	}
	public static String getEtcdclientcertificatepwd() {
		return etcdClientCertificatePwd;
	}
	public static String getIdmserverhost() {
		return idmServerHost;
	}
	public static String getIdmserverport() {
		return idmServerPort;
	}
	public static String getAutopassserverhost() {
		return autopassServerHost;
	}
//	public static String getAutopassserverport() {
//		return autopassServerPort;
//	}
	public static String getExternalport() {
		return externalPort;
	}
	public static String getExternalhost() {
		return externalHost;
	}
	public static String getVaultendpoints() {
		return vaultEndpoints;
	}

	public static String getVaultSigningKey() {
		return vaultSigningKey;
	}
	public static String getSuiteRegistry() {return suiteRegistry;}
}