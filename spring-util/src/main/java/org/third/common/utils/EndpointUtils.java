package org.third.common.utils;

import org.third.common.entity.CommonConstants;

public class EndpointUtils {

	private static final String apiServerEndpoint = "https" + "://" + EnvUtils.getApiserverhost() + ":"
			+ EnvUtils.getApiserverport();
	// private static final String etcdEndpoint = EnvUtils.getEtcdendpoints() ==
	// null? null : EnvUtils.getEtcdendpoints().split(",")[0];

	private static final String etcdEndpoint = "https" + "://" + System.getenv("K8S_MASTER_IP") + ":" + "4001";

	private static final String idmEndpoint = "https" + "://" + EnvUtils.getIdmserverhost() + ":"
			+ EnvUtils.getIdmserverport();
	private static final String autopassEndpoint = "https" + "://" + EnvUtils.getAutopassserverhost(); // +
																										// EnvUtils.getAutopassserverport();
	// registryendpod
	private static final String apiRegistryEndpoint = "https" + "://" + EnvUtils.getApiRegistryHost() + ":"
			+ EnvUtils.getApiRegistryPort();

	private static final String proxyRegistryEndpoint = "https://" + CommonConstants.KUBE_REGISTRY_PROXY_NAME + ":5000";

	public static String getApiserverendpoint() {
		return apiServerEndpoint;
	}

	// getregistrypod
	public static String getApiRegistryEndpoint() {
		return apiRegistryEndpoint;
	}

	public static String getEtcdendpoint() {
		return etcdEndpoint;
	}

	public static String getIdmendpoint() {
		return idmEndpoint;
	}

	public static String getAutopassendpoint() {
		return autopassEndpoint;
	}

	public static String getProxyRegistryEndpoint() {
		return proxyRegistryEndpoint;
	}
}
