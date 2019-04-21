package org.third.common.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Result;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

class JaxbSchemaOutputResolver extends SchemaOutputResolver {

	private File f;

	public JaxbSchemaOutputResolver(String fileName) {
		f = new File(fileName);
	}

	public JaxbSchemaOutputResolver(String dir, String fileName) {
		f = new File(dir, fileName);
	}

	@Override
	public Result createOutput(String namespaceUri, String suggestedFileName) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

}

class ClientCertificateValidatorConfig {

	public final static String CERTIFICATE_PARSER_CLASS_ATT_NAME = "class";
	public final static String USER_NAME_ATTRIBUTE_NAME_ATT_NAME = "attributeName";
	public final static String USER_NAME_HEADER_NAME_ATT_NAME = "headerName";

	public final static String USER_NAME_ATTRIBUTE_NAME_DEF_VAL = "cn";

	/**
	 * denotes a fully qualified class name that implements the interface
	 * {@link com.hp.ccue.identity.hpssoImpl.validators.clientCertificate.UserIdentifierRetriever}
	 * if null, the default one is used
	 */
	private String certificateParserClass;
	/**
	 * Denotes the name of the attribute that holds the user name. The attribute is
	 * part ofthe certificate to be parsed by {@link #certificateParserClass}
	 */
	private String userNameAttributeName;
	/**
	 * Denotes the header name that holds the client certificate
	 */
	private String userNameHeaderName;

	public ClientCertificateValidatorConfig() {
	}

	@Override
	public ClientCertificateValidatorConfig clone() throws CloneNotSupportedException {

		return (ClientCertificateValidatorConfig) super.clone();

	}

	@XmlAttribute(name = "class")
	public String getCertificateParserClass() {

		return certificateParserClass;
	}

	public void setCertificateParserClass(String certificateParserClass) {

		this.certificateParserClass = certificateParserClass;
	}

	@XmlAttribute(name = "attributeName")
	public String getUserNameAttributeName() {

		return userNameAttributeName;
	}

	public void setUserNameAttributeName(String userNameAttributeName) {

		this.userNameAttributeName = userNameAttributeName;
	}

	@XmlAttribute(name = "headerName")
	public String getUserNameHeaderName() {

		return userNameHeaderName;
	}

	public void setUserNameHeaderName(String userNameHeaderName) {

		this.userNameHeaderName = userNameHeaderName;
	}
}

@XmlRegistry
class JaxbObjectFactory {

	@XmlElementDecl(name = "ClientCertificate")
	public JAXBElement<ClientCertificateValidatorConfig> createClientCertificate(ClientCertificateValidatorConfig cac) {

		return new JAXBElement<ClientCertificateValidatorConfig>(new QName("ClientCertificate"),
				ClientCertificateValidatorConfig.class, cac);
	}

}

public class XmlUtils {
	/**
	 * Logger Support
	 */
	static final Logger logger = LoggerFactory.getLogger(XmlUtils.class);
	static DocumentBuilder documentBuilder;

	static {
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			documentBuilderFactory.setFeature("http://xml.org/sax/features/external-general-entities", false);
			documentBuilderFactory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
			documentBuilderFactory.setExpandEntityReferences(false);
			documentBuilderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

			documentBuilder = documentBuilderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Instantiate the w3c DOM object.
	 * 
	 * @return DOM object
	 */
	public static final Document getDocument() {
		return documentBuilder.newDocument();
	}

	/**
	 * Parses the file to one DOM object
	 * 
	 * @param file file path
	 * @return DOM Object
	 */
	public static final Document getDocument(String file) throws SAXException, IOException {
		return documentBuilder.parse(CommonUtils.getFile(file));
	}

	/**
	 * Parses the file to one DOM object
	 * 
	 * @param file file path represented by {@link File}
	 * @return DOM Object
	 * 
	 * @throws SAXException
	 * @throws IOException
	 */
	public static final Document getDocument(File file) throws SAXException, IOException {
		return documentBuilder.parse(file.getPath());
	}

	/**
	 * Parses the stream object to one DOM object
	 * 
	 * @param file XML Stream
	 * @return DOM Object
	 * 
	 * @throws SAXException
	 * @throws IOException
	 */
	public static final Document getDocument(InputStream file) throws SAXException, IOException {
		return documentBuilder.parse(file);
	}

	/**
	 * Gets the child elements under one DOM node
	 * 
	 * @param parent  parent node
	 * @param tagName child node tag name
	 * @return child node list. if not founnd, return null
	 */
	public static final NodeList getChildren(Element parent, String tagName) {
		return parent.getElementsByTagName(tagName);
	}

	/**
	 * Gets the value of the first element under one DOM node
	 * 
	 * @param parent  parent element node
	 * @param tagName child element node name
	 * @return node value of first child elment
	 */
	public static String getNodeValue(Element parent, String tagName) {
		return parent.getElementsByTagName(tagName).item(0).getFirstChild().getNodeValue();
	}

	/**
	 * Gets the value of one DOM element
	 * 
	 * @param element element object
	 * @return text value wrapped by element tag
	 */
	public static String getNodeValue(Node element) {
		return element.getFirstChild().getNodeValue();
	}

	public static final <T> void generateSchema(String outputFile, Class<? extends T>... classes)
			throws JAXBException, IOException {
		JaxbSchemaOutputResolver resolver = new JaxbSchemaOutputResolver(outputFile);
		JAXBContext context = JAXBContext.newInstance(classes);
		context.generateSchema(resolver);
	}

	public static final String toString(org.w3c.dom.Element element) throws IOException {
		org.apache.xml.serialize.OutputFormat format = new org.apache.xml.serialize.OutputFormat(
				element.getOwnerDocument());
		format.setEncoding("UTF-8");
		java.io.StringWriter stringOut = new java.io.StringWriter();
		org.apache.xml.serialize.XMLSerializer serial = new org.apache.xml.serialize.XMLSerializer(stringOut, format);
		serial.asDOMSerializer();
		serial.serialize(element);
		return stringOut.toString();
	}

	public static final String IDM_SH_WEB_DIR = "C:/Users/gongyo/workspace/idmsh/idm-service/target/idm-service-1.8.0-SNAPSHOT";
	public static final String IDM_PROPEL_WEB_DIR = "C:/Users/gongyo/workspace/propel/main/idm-service/target/idm-service-1.9.0-SNAPSHOT";

	private static javax.xml.parsers.DocumentBuilderFactory documentBuilderFactory;

	static {
		javax.xml.parsers.DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		try {
			documentBuilderFactory.setFeature("http://xml.org/sax/features/external-general-entities", false);
			documentBuilderFactory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
			documentBuilderFactory.setExpandEntityReferences(false);
			documentBuilderFactory.setFeature(javax.xml.XMLConstants.FEATURE_SECURE_PROCESSING, true);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		javax.xml.parsers.SAXParserFactory spf = SAXParserFactory.newInstance();
		try {
			spf.setFeature(javax.xml.XMLConstants.FEATURE_SECURE_PROCESSING, true);
			javax.xml.parsers.SAXParser sp = spf.newSAXParser();
			org.xml.sax.XMLReader xmlReader = sp.getXMLReader();
		} catch (ParserConfigurationException | SAXException e) {
			e.printStackTrace();
		}

		// JAXBContext context =
		// JAXBContext.newInstance(HpSsoConfiguration.class,
		// LwssoValidatorConfig.class,
		// AbstractValidatorConfig.class,
		// ClientCertificateValidatorConfig.class,
		// IdmValidatorConfig.class, CustomValidatorConfig.class);
		//
		// javax.xml.bind.Marshaller marshaller = context.createMarshaller();
		// marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
		// Boolean.TRUE);
		//
		// javax.xml.bind.Unmarshaller unMarshaller =
		// context.createUnmarshaller();
		// unMarshaller.setProperty("com.sun.xml.bind.ObjectFactory", new
		// JaxbObjectFactory());
	}

	public static void main(String[] args) throws JAXBException, IOException {
		generateSchema(ClientCertificateValidatorConfig.class.getSimpleName() + ".xsd",
				ClientCertificateValidatorConfig.class);
	}
}
