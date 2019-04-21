package org.third.common.entity;

public class CommonConstants {

	//************************ Kubernates ************************
	public static final String KUBE_DEFAULT_API_VERSION = "v1";  
	
	
	//************************ ETCD ************************
	//Base ETCD GET
	public static final String ETCD_BASE_URL = "/v3alpha/kv/range";

	//Base ETCD POST
	public static final String POST_ETCD_BASE_URL = "/v3alpha/kv/put" ;


	//etcd white key array for put
	public static final String [] whiteArray = {"mng-labels"};


	//Base ETCD DELETE
	public static final String DELETE_ETCD_BASE_URL = "/v3alpha/kv/deleterange";
	
	//the key in etcd stores the labels
	public static final String LABEL_ETCD_KEY = "/mng-labels";
	
	//the key in etcd stores the labels
	public static final String LABEL_WOKER_KEY = "Worker";
	
	//the key in etcd stores the labels
	public static final String LABEL_WOKER_VALUE = "label";
	
	//the key in etcd stores the front-end steps and data
	public static final String FRONT_STORAGE_ETCD_KEY = "/suite-installer/v1.1/front-end-storage";

	public static final String UPGRADE_STORAGE_ETCD_KEY = "/suite-installer/v1.1/upgrade-storage";
	
	//the dir in etcd stores the suite configuration
	public static final String SUITE_STORAGE_ETCD_DIR = "/suite-installer/v1.1/suite-storage";

	public static final String SUITE_UNBOUND_NFS_KEY = "/suite-installer/v1.1/unbound-nfs";
	
	public static final int ETCD_WATCHER_MAX_TIMEOUT_IN_SECONDS = 1 * 60;
	
	public static final int ETCD_WATCHER_CONNECTION_TIMEOUT_IN_SECONDS = 2;
	
	public static final int ETCD_WATCHER_MAX_CONNECTION = 3;

	public static final String CA_CUSTOM_ROOTCA = "/suite-installer/v1.1/ca-custom-rootca";

	public static final String CA_CUSTOM_REALMS = "/suite-installer/v1.1/ca-custom-realms";

	public static final String CA_SUITE_NAMESPACE = "/suite-installer/v1.1/ca-suite-namespace";

	public static final String CA_EXTERNAL_HOST = "/suite-installer/v1.1/ca-external-host";

	public static final String SUITE_FEATURE_SET_OLD = "/suite-installer/v1.1/suite-feature-set-old";

	public static final String SUITE_IMAGE_MODIFY = "/suite-installer/v1.1/suite-image-modify";

	public static final String SUITE_VOLUME_OLD = "/suite-installer/v1.1/suite-volume-old";

	//************************ IDM ************************
	//Base IDM URL PATH
	public static final String IDM_BASE_URL = "/idm-service/v2.0/tokens";

	public static final String IDM_ROLES_URL = "/idm-service/api/scim/organizations/{tenant}/users/{name}/roles";

	//Basic Auth to IDM rest api, IDM required this to be hard coded. (idmTransportUser:idmTransportUser)
	public static final String IDM_BASIC_AUTH = "Basic aWRtVHJhbnNwb3J0VXNlcjppZG1UcmFuc3BvcnRVc2Vy";
	
	//IDM Default Tenant name
	//TODO need modify
	public static final String IDM_DEFAULT_TENANT = "Provider";
	
	public static final String IDM_TOKEN_NAME = "X-AUTH-TOKEN";
	
	public static final String IDM_TOKEN_NAME_IN_COOKIE = "X-TOKEN";

	public static final String IDM_RF_TOKEN_NAME_IN_COOKIE = "X-REFRESH_TOKEN";

	public static final String TOKEN_NAME = "X-AUTH-TOKEN";

	public static final String REFRESH_TOKEN_NAME = "X-AUTH-REFRESH-TOKEN";

	public static final String IDM_HEALTHZ_URL = "/idm-service/api/version-info";

	//************************ Vault ************************
	//executable bin file to generate vault id
	public static final String VAULT_GEN_ROLEID_BIN = "/bin/genRoleId";
	
	//executable bin file to generate vault id
	public static final String VAULT_UPDATE_ROLEID_BIN = "/bin/updateRoleId";
	
	//executable bin file to save vault secret
	public static final String VAULT_SAVE_SECRET_BIN = "/bin/saveSecret";

	//executable bin file to save vault secret
	public static final String VAULT_GET_SECRET_BIN = "/bin/get_secret";
	
	//************************ AutoPass ************************
	//Autopass team defined this product id
	public static final String AUTOPASS_PRODUCT_UNIQUE_ID = "ITOMSUITE_2017.01";
	
	public static final String AUTOPASS_BASE_URL = "/autopass/services/v1";
	
	public static final String AUTOPASS_BASE_URL_V10 = "/autopass/services/v10.0";
	
	public static final String AUTO_PASS_TOKEN_NAME = "APAUTHTOKEN";
	
	public static final String ATUO_PASS_URL_KEY= "ATUOPASSURL";
	
	//************************ SS ************************
	//executable bin file to generate vault id
	public static final String CSRF_TOKEN_NAME = "X-CSRF-TOKEN";
	
	//************************ INGRESS ************************
	public static final String INGRESS_NODE_SELECTOR_KEY = "role";
	
	public static final String INGRESS_NODE_SELECTOR_VALUE = "loadbalancer";
	
	//************************ suit name ************************
	public static final String ITSMA = "itsma";
	
	public static final String VAULT_RT_TOKEN="/cdf/vault/config/root-token";
	
	public static final String CORE="core";

	//
	public static final String VAULT_BASE_URL="v1/itom/suite";

	//certificates api
	public static final String VAULT_CA_MOUNT_LIST = "/v1/sys/mounts";
	public static final String VAULT_CA_MOUNT="/v1/sys/mounts/{path}";
	public static final String VAULT_CA_MOUNT_TUNE="/v1/sys/mounts/{path}/tune";
	public static final String VAULT_CA_ROOT="/v1/{realm}/root/generate/{internal}";
	public static final String VAULT_CA_ROOT_CONFIG="/v1/{realm}/config/urls";
	public static final String VAULT_CA_ROOT_ROLES="/v1/{realm}/roles/{name}";
	public static final String VAULT_CA_CERTIFICATE = "/v1/{realm}/ca/pem";
	public static final String VAULT_CA_ISSUE = "/v1/{realm}/issue/{ca-name}";
	public static final String VAULT_CA_POLICY="/v1/sys/policy/{policy-name}";
	public static final String VAULT_CA_APPROLE = "/v1/auth/approle/role/{role-name}";
	public static final String VAULT_CA_APPROLEID="/v1/auth/approle/role/{role-name}/role-id";
	public static final String VAULT_CA_APPROLE_DEL = "/v1/auth/approle/role/{role-name}";
  public static final String VAULT_CA_READ_ROOT = "/v1/{realm}/cert/ca";

	//kube secret
	public static final String KUBE_SECRET_CREATE = "/api/v1/namespaces/{name}/secrets";
	public static final String KUBE_SECRET_UPDATE = "/api/v1/namespaces/{namespace}/secrets/{name}";
	public static final String KUBE_SECRET_READ = "/api/v1/namespaces/{namespace}/secrets/{name}";
	//kube configmap
	public static final String KUBE_CONFIGMAP_CREATE = "/api/v1/namespaces/{namespace}/configmaps";
	public static final String KUBE_CONFIGMAP_UPDATE = "/api/v1/namespaces/{namespace}/configmaps/{name}";
	public static final String KUBE_CONFIGMAP_READ =  "/api/v1/namespaces/{namespace}/configmaps/{name}";
	public static final String KUBE_CONFIGMAP_PATCH = "/api/v1/namespaces/{namespace}/configmaps/{name}";
	//kube service
	public static final String KUBE_FRONTED_DELETE = "/api/v1/namespaces/{namespace}/services/{name}";
	public static final String KUBE_FRONTED_CREATE = "/api/v1/namespaces/{namespace}/services";
	public static final String KUBE_INGRESS_CREATE = "/apis/extensions/v1beta1/namespaces/{namespace}/ingresses";
	public static final String KUBE_INGRESS_DELETE = "/apis/extensions/v1beta1/namespaces/{namespace}/ingresses/{name}";
	public static final String KUBE_CONFIGMAP_GET = "/api/v1/namespaces/{namespace}/configmaps/{name}";
	public static final String KUBE_NODE_LABEL = "/api/v1/nodes/{name}";
	public static final String KUBE_CONFIG_POD_READ = "/api/v1/namespaces/{namespace}/pods/{name}";
	public static final String KUBE_CONFIG_DEPLOYMENT_READ = "/apis/apps/v1beta2/namespaces/{namespace}/deployments/{name}";
	public static final String KUBE_PV_LIST = "/api/v1/persistentvolumes";
	//kube deployment
	public static final String KUBE_DEPLOYMENT_READ = "/apis/apps/v1beta1/namespaces/{namespace}/deployments";
	//kubernate resource status api
	public static final String KUBE_STATUS_NAMESPACE = "/api/v1/namespaces/{0}/status";
	public static final String KUBE_STATUS_NODE = "/api/v1/nodes/{0}/status";
	public static final String KUBE_STATUS_POD = "/api/v1/namespaces/{0}/pods/{1}/status";
	public static final String KUBE_STATUS_SERVICE = "/api/v1/namespaces/{0}/services/{1}/status";
	public static final String KUBE_STATUS_DEPLOYMENT = "/apis/apps/v1beta2/namespaces/{0}/deployments/{1}/status";
	public static final String KUBE_STATUS_DAEMONSET= "/apis/apps/v1beta2/namespaces/{0}/daemonsets/{1}/status";
	public static final String KUBE_STATUS_STATEFULSETS = "/apis/apps/v1beta2/namespaces/{0}/statefulsets/{1}/status";
	public static final String KUBE_STATUS_JOB = "/apis/batch/v1/namespaces/{0}/jobs/{1}/status";
	public static final String KUBE_STATUS_REPLICATIONCONTROLLER = "/api/v1/namespaces/{0}/replicationcontrollers/{1}/status";
	public static final String KUBE_STATUS_REPLICASETS = "/apis/apps/v1beta2/namespaces/{0}/replicasets/{1}/status";
	//10 save transparams
	public static final String SUITE_INSTALLER_PARAMS = "/api/v1/suite-installer/params";

	public static final String SUITE_INSTALLER_NFS_IP_PATH = "/api/v1/suite-installer/NFS";

	public static final String SUITE_INSTALLER_MASTER_NODE = "/api/v1/suite-installer/MASTERNODE";

	public static final String SUITE_INSTALLER_WORKER_NODE = "/api/v1/suite-installer/WORKERNODE";

	public static final String SUITE_INSTALLER_MASTER_NODE_ERROR = "/api/v1/suite-installer/NODEERROR";


	public static final String SUITE_INSTALLER_POD_NAME = "/api/v1/suite-installer/POD_NAME";

	//store in valut
	public static final String SUITE_INSTALLER_PARAMS_For_Vault = "vault-params-key";

	//LANDING_PAGE_URL
	public static final String LANDING_PAGE_URL_KEY = "LANDING_PAGE_URL";

	//whether menu is registered
	public static final String SUITE_INSTALLER_MENU_REGISTER = "/api/v1/suite-installer/MENUREGISTER";

	//TOKEN
	public static final String REFEASH_TOKEN_KEY = "LANDING_PAGE_URL";

	public static final String CERT_CHECK = "/tmp/offline_sync_tools/certCheck";
	
	public static final String ORACLE_JDBC_URL = "jdbc:oracle:thin:@%s:%s:%s";

	public static final String ORACLE_JDBC_URL_PREFIX = "jdbc:oracle:thin:@";

	public static final String ORC_WAY_CONSTR = "CONSTR";
	
	public static final String POSTGRES_JDBC_URL = "jdbc:postgresql://%s:%s/%s";
	
	public static final String ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";
	
	public static final String ORACLE = "extoracle";

	public static final String POSTGRES = "extpostgres";

	public static final String INTERNAL_DB = "intpostgres";
	
	public static final String DATABASE_TYPE = "databaseType";
	
	public static final String DATABASE_HOST = "databaseHost";
	
	public static final String DATABASE_PORT = "port";
	
	public static final String DATABASE_NAME = "databaseName";
	
	public static final String DATABASE_USER = "username";
	
	public static final String DATABASE_PWD = "password";

	public static final String LOCAL_SUITE_REGISTRY = "localhost:5000";

	public static final String UPDATE_CALLBACK_KEY="UPDATE_CALLBACK_KEY";

	public static final String CHANGE_CALLBACK_KEY="CHANGE_CALLBACK_KEY";

	public static final String RECONFIG_CALLBACK_KEY="RECONFIG_CALLBACK_KEY";

	public static final String RESOURCE_CALLBACK_KEY="RESOURCE_CALLBACK_KEY";

	//************************ database configuratoin ************************

	public static final String JDBC_CONF_JAR_PATH = "/jdbc";

	public static final String JDBC_CONF_CRT_PATH = "/jdbc/certificates";

	public static final String DB_CERTIFICATE_DELIMITER = "#";

	public static final String DB_ORACLE_JKS_NAME = "truststore.jks";

	//************************ SIMPLE UPDATE ************************
	public static final String SIMPLE_UPDATE_STATUS = "/suite-installer/v1.1/upgrade/simple_update";

	//************************ VAULT KEY PREFIX************************
	public static final String PRIVATE_KEY_CONTENT = "PRIVATE_KEY_CONTENT";

	//************************KUBE-REGISTRY-PROXY.core*****************
	public static final String KUBE_REGISTRY_PROXY_NAME = "kube-registry-proxy.core";

}
