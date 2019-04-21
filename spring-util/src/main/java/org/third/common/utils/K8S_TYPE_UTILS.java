package org.third.common.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class K8S_TYPE_UTILS {
	private static final Map<String, String> TYPE_MAP = new HashMap<String, String>();
	private static final Map<String, String> SPECIFC_TYPE_MAP = new HashMap<String, String>();
	private static final Map<String, String> NORMAL_TYPE_MAP = new HashMap<String, String>();
	private static final Map<String, String> EXTENSION_TYPE_MAP = new HashMap<String, String>();
	private static final Map<String, String> SIMPLE_TYPE_MAP = new HashMap<String, String>();
	
	private static final List<String> ALL_TYPES;
	private static final List<String> SPECIFC_TYPES;
	private static final List<String> NORMAL_TYPES;
	private static final List<String> EXTENSION_TYPE;
	private static final List<String> SIMPLE_TYPE;

	static{
		//specific
		SPECIFC_TYPE_MAP.put("namespace", "namespaces");
		SPECIFC_TYPE_MAP.put("persistentvolume", "persistentvolumes");
		SPECIFC_TYPE_MAP.put("node", "nodes");
		
		//normal types
		NORMAL_TYPE_MAP.put("configmap", "configmaps");
		NORMAL_TYPE_MAP.put("service", "services");
		NORMAL_TYPE_MAP.put("replicationcontroller", "replicationcontrollers");
		NORMAL_TYPE_MAP.put("pod", "pods");
		NORMAL_TYPE_MAP.put("persistentvolumeclaim", "persistentvolumeclaims");
		NORMAL_TYPE_MAP.put("secret", "secrets");
		
		//extension types
		EXTENSION_TYPE_MAP.put("ingress", "ingresses");
		EXTENSION_TYPE_MAP.put("deployment", "deployments");
		EXTENSION_TYPE_MAP.put("job", "jobs");
		EXTENSION_TYPE_MAP.put("statefulset","statefulsets");
		EXTENSION_TYPE_MAP.put("daemonset","daemonsets");
		EXTENSION_TYPE_MAP.put("replicaset","replicasets");
		EXTENSION_TYPE_MAP.put("role","roles");
		EXTENSION_TYPE_MAP.put("horizontalpodautoscaler","horizontalpodautoscalers");
		EXTENSION_TYPE_MAP.put("cronJob","cronjobs");

   //simple update types
		SIMPLE_TYPE_MAP.put("replicationcontroller", "replicationcontrollers");
		SIMPLE_TYPE_MAP.put("pod", "pods");
		SIMPLE_TYPE_MAP.put("deployment", "deployments");
		SIMPLE_TYPE_MAP.put("job", "jobs");
		SIMPLE_TYPE_MAP.put("statefulset","statefulsets");
		SIMPLE_TYPE_MAP.put("daemonset","daemonsets");
		SIMPLE_TYPE_MAP.put("replicaset","replicasets");
		SIMPLE_TYPE_MAP.put("cronJob", "cronjobs");

		//All Types
		TYPE_MAP.putAll(SPECIFC_TYPE_MAP);
		TYPE_MAP.putAll(NORMAL_TYPE_MAP);
		TYPE_MAP.putAll(EXTENSION_TYPE_MAP);
		
		ALL_TYPES = Collections.unmodifiableList(new ArrayList<String>(TYPE_MAP.values()));
		SPECIFC_TYPES = Collections.unmodifiableList(new ArrayList<String>(SPECIFC_TYPE_MAP.values()));
		NORMAL_TYPES = Collections.unmodifiableList(new ArrayList<String>(NORMAL_TYPE_MAP.values()));
		EXTENSION_TYPE = Collections.unmodifiableList(new ArrayList<String>(EXTENSION_TYPE_MAP.values()));
		SIMPLE_TYPE = Collections.unmodifiableList(new ArrayList<String>(SIMPLE_TYPE_MAP.values()));
	}

	public static String getType(String str){

		if(str == null){
			return null;
		}
		return TYPE_MAP.get(str.toLowerCase(Locale.ENGLISH));
	}
	public static List<String> getAllSupportTypes(){
		return ALL_TYPES;
	}
	public static List<String> getSpecificTypes(){
		return SPECIFC_TYPES;
	}
	public static List<String> getNormalTypes(){
		return NORMAL_TYPES;
	}
	public static List<String> getExtensionTypes(){
		return EXTENSION_TYPE;
	}
	public static List<String> getSimpleType() {
		return SIMPLE_TYPE;
	}
}
