package org.third.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityUtils {
	private static final Logger logger = LoggerFactory.getLogger(SecurityUtils.class);

	public static enum HashAlgorithm {
		MD5("MD5"), SHA1("SHA1"), SHA256("SHA-256");

		private String algorithm;

		private HashAlgorithm(String name) {
			this.algorithm = name;
		}

		public String getAlgorithm() {
			return algorithm;
		}
	}

	public static enum MacAlgorithm {
		HMAC_SHA256("HmacSHA256");

		private String algorithm;

		private MacAlgorithm(String name) {
			this.algorithm = name;
		}

		public String getAlgorithm() {
			return algorithm;
		}
	}

	/**
	 * Computes the MD5 hash value of the given text.
	 *
	 * @param input the input text
	 *
	 * @return the MD5 hash value of the input text
	 */
	public static final String hashAsMD5(String text) {
		return hash(text.getBytes(StandardCharsets.UTF_8), HashAlgorithm.MD5);
	}

	/**
	 * Computes the SHA1 hash value of the given text.
	 *
	 * @param input the input text
	 *
	 * @return the SHA1 hash value of the input text
	 */
	public static final String hashAsSha1(String text) {
		return hash(text.getBytes(StandardCharsets.UTF_8), HashAlgorithm.SHA1);
	}
	
	/**
	 * Computes the SHA1 hash value of the given file
	 *
	 * @param input the given file
	 *
	 * @return the SHA1 hash value of the input file
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static final String hashAsSha1(File file) throws FileNotFoundException, IOException {
		return hash(StreamUtils.streamToByteArray(new FileInputStream(file)), HashAlgorithm.SHA1);
	}

	/**
	 * Computes the SHA256 hash value of the given text.
	 *
	 * @param input the input text
	 *
	 * @return the SHA1 hash value of the input text
	 */
	public static final String hashAsSha256(String text) {
		return hash(text.getBytes(StandardCharsets.UTF_8), HashAlgorithm.SHA256);
	}

	/**
	 * Computes the hash value of the given text and hash algorithm.
	 *
	 * @param input the input text
	 * @param which algorithm should be used
	 *
	 * @return the hash value of the input text
	 */
	private static String hash(byte[] text, HashAlgorithm algorithm) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance(algorithm.getAlgorithm());
		} catch (NoSuchAlgorithmException e) {
			logger.error("Not supported algorithm: {}", algorithm, e);
		}
		return CodecUtils.encodeHex(md.digest(text));
	}

	/**
	 * 
	 * @param text
	 * @return
	 */
	public static final String sign(String text) {
		return sign(text.getBytes(), MacAlgorithm.HMAC_SHA256);
	}

	/**
	 * 
	 * @param inputBytes
	 * @param algorithm
	 * @return
	 */
	public static final String sign(byte[] inputBytes, MacAlgorithm algorithm) {
		try {
			Mac mac = Mac.getInstance(algorithm.getAlgorithm());
			SecretKey key = new SecretKeySpec(CommonUtils.ENV_SIGNING_KEY.getBytes(), algorithm.getAlgorithm());
			mac.init(key);
			byte[] signature = mac.doFinal(inputBytes);
			return CodecUtils.encodeBase64URLSafeString(signature);
		} catch (Exception ex) {
			return null;
		}
	}
}
