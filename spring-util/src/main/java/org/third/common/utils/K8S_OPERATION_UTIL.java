package org.third.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Huailong Tang(Jason) on 2017/5/22.
 */
public class K8S_OPERATION_UTIL {

    public static final Map<String,String> K8S_API = new HashMap<String,String>();
    static {
        K8S_API.put("namespaces","v1");
        K8S_API.put("persistentvolumes","v1");
        K8S_API.put("configmaps","v1");
        K8S_API.put("services","v1");
        K8S_API.put("replicationcontrollers","v1");
        K8S_API.put("pods","v1");
        K8S_API.put("nodes","v1");
        K8S_API.put("persistentvolumeclaims","v1");
        K8S_API.put("secrets","v1");
        K8S_API.put("ingresses","extensions/v1beta1");
        K8S_API.put("deployments","extensions/v1beta1");
        K8S_API.put("jobs","batch/v1");
        K8S_API.put("daemonsets","extensions/v1beta1");
        K8S_API.put("statefulsets","apps/v1beta1");
        //Added for Horizontal Pod Autoscaler support
        K8S_API.put("horizontalpodautoscalers","autoscaling/v2alpha1");
        //Add for Replicasets
        K8S_API.put("replicasets","apps/v1beta2");
        //Add for cronJobs
        K8S_API.put("cronjobs","batch/v1beta1");
    }

    public static final Map<String,String> K8S_API_1_6 = new HashMap<>();
    static {
        K8S_API_1_6.put("namespaces","v1");
        K8S_API_1_6.put("persistentvolumes","v1");
        K8S_API_1_6.put("configmaps","v1");
//        K8S_API_1_6.put("services","v1");
        K8S_API_1_6.put("replicationcontrollers","v1");
        K8S_API_1_6.put("pods","v1");
        K8S_API_1_6.put("nodes","v1");
        K8S_API_1_6.put("persistentvolumeclaims","v1");
        K8S_API_1_6.put("secrets","v1");
        K8S_API_1_6.put("ingresses","extensions/v1beta1");
        K8S_API_1_6.put("deployments","apps/v1beta1");
        K8S_API_1_6.put("jobs","batch/v1");
        K8S_API_1_6.put("daemonsets","extensions/v1beta1");
        K8S_API_1_6.put("statefulsets","apps/v1beta1");
        //Add for cronJobs
        K8S_API_1_6.put("cronjobs","batch/v2alpha1");
    }

   public static final List<String> K8S_RES_STATUS_TYPE = new ArrayList<>();
    static {
       K8S_RES_STATUS_TYPE.add("namespaces");
       K8S_RES_STATUS_TYPE.add("nodes");
       K8S_RES_STATUS_TYPE.add("pods");
       K8S_RES_STATUS_TYPE.add("services");
       K8S_RES_STATUS_TYPE.add("deployments");
       K8S_RES_STATUS_TYPE.add("daemonsets");
       K8S_RES_STATUS_TYPE.add("statefulsets");
       K8S_RES_STATUS_TYPE.add("jobs");
       K8S_RES_STATUS_TYPE.add("replicationcontrollers");
       K8S_RES_STATUS_TYPE.add("replicasets");
    }

    public static final String ETCD_KEY_RECONFIGURATION = "reconfiguration_url";


}
