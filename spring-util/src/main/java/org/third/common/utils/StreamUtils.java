package org.third.common.utils;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Context;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class StreamUtils {
	static final Logger logger = LoggerFactory.getLogger(StreamUtils.class);
	/**
	 * Cache size for received character from stream
	 */
	public static final int BUFFER_SIZE = 1024;
	public static final int EOF = -1;

	/**
	 * Copy the contents of the given InputStream into a String. Leaves the stream
	 * open when done.
	 * 
	 * @param in the InputStream to copy from
	 * @return the String that has been copied to
	 * 
	 * @throws IOException in case of I/O errors
	 */
	public static String streamToString(InputStream in, Charset charset) throws IOException {

		StringBuilder stringBuffer = new StringBuilder();
		char[] buffer = new char[BUFFER_SIZE];
		int bytesRead = -1;

		InputStreamReader reader = null;
		try {
			reader = new InputStreamReader(in, charset);
			while ((bytesRead = reader.read(buffer)) != -1) {
				stringBuffer.append(buffer, 0, bytesRead);
			}
		} finally {
			closeQuietly(reader);
		}
		return stringBuffer.toString();
	}

	public static String streamToString(InputStream in) throws IOException {
		return streamToString(in, StandardCharsets.UTF_8);
	}
	
	public static String pkiStreamToString(InputStream in) throws IOException {
		StringBuilder stringBuffer = new StringBuilder();
		String line = null;

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
			while ((line = reader.readLine()) != null) {
				if (line.length() == 0 || line.charAt(0) == '-') {
					continue;
				} else {
					stringBuffer.append(line);
				}
			}
		} finally {
			closeQuietly(reader);
		}
		return stringBuffer.toString();
	}

	/**
	 * Copies bytes from a large (over 2GB) <code>InputStream</code> to an
	 * <code>OutputStream</code>.
	 * <p>
	 * This method uses the provided buffer, so there is no need to use a
	 * <code>BufferedInputStream</code>.
	 * <p>
	 *
	 * @param input  the <code>InputStream</code> to read from
	 * @param output the <code>OutputStream</code> to write to
	 * @param buffer the buffer to use for the copy
	 * @return the number of bytes copied
	 */
	public static long copyToOutputStream(final InputStream input, final OutputStream output) throws IOException {
		long count = 0;
		int bytesRead;
		byte[] buffer = new byte[BUFFER_SIZE];

		while (EOF != (bytesRead = input.read(buffer))) {
			output.write(buffer, 0, bytesRead);
			count += bytesRead;
		}
		output.flush();		
		return count;
	}

	/**
	 * Read the content from {@code input} and return it as byte array
	 * 
	 * @param input
	 * @return
	 * @throws IOException
	 */
	public static byte[] streamToByteArray(InputStream input) throws IOException {

		ByteArrayOutputStream output = null;
		byte[] result = null;
		try {
			output = new ByteArrayOutputStream(BUFFER_SIZE);
			copyToOutputStream(input, output);
			result = output.toByteArray();
		} finally {
			StreamUtils.closeQuietly(input, output);
		}

		return result;
	}

	/**
	 * Release the resource one by one. if some exception happens, exception will
	 * caught and print into logger
	 * 
	 * @param closableObjects resource object
	 */
	public static final void closeQuietly(Closeable... closableObjects) {
		if (null != closableObjects) {
			for (Closeable closableObject : closableObjects) {
				if (null != closableObject) {
					try {
						closableObject.close();
					} catch (IOException e) {
						logger.error("Failed to close the closeble object:" + closableObject, e);
					}
				}
			}
		}
	}

	/**
	 * Release the resource one by one. if some exception happens, exception will
	 * caught and print into logger
	 * 
	 * @param closableObjects resource object
	 */
	public static final void closeQuietly(Context... closableObjects) {
		if (null != closableObjects) {
			for (Context closableObject : closableObjects) {
				if (null != closableObject) {
					try {
						logger.trace("Closing the {}", closableObject);
						closableObject.close();
					} catch (Exception e) {
						logger.error("Failed to close the closeble object:" + closableObject, e);
					}
				}
			}
		}
	}

	public static String getFileContent(String filePath) throws IOException {
		byte[] bytes = Files.readAllBytes(Paths.get(filePath));
		return new String(bytes, StandardCharsets.UTF_8);
	}

	public static byte[] stringToUtf8Bytes(String str) {
		try {
			return str.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	public static String readerToString(Reader reader) {
		String result = null;
		try {
			result = IOUtils.toString(reader);
		} catch (IOException e) {
			throw new RuntimeException("Faid to read string from reader", e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					logger.warn(e.getMessage());
				}
			}
		}
		return result;

	}

	public static void writeStringToFile(String content, String filePath) throws IOException {
		writeByteArrayToFile(new File(filePath), content.getBytes(StandardCharsets.UTF_8));
	}

	public static InputStream stringToInputStream(String str) {

		if (str == null) {
			return null;
		}

		return new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8));
	}

	public static void writeByteArrayToFile(File file, byte[] contentInBytes) throws IOException {
		// if file doesn't exists, then create it
		if (!file.exists()) {
			if (!file.createNewFile()) {
				throw new IOException("Can't create file " + file);
			}
		}
		FileOutputStream fop = null;
		try {
			fop = new FileOutputStream(file);
			// get the content in bytes
			fop.write(contentInBytes);
			fop.flush();
		} finally {
			if (fop != null) {
				fop.close();
			}
		}
	}

	public static void copyToOutputStream(byte[] source, FileOutputStream output) throws IOException {
		output.write(source);
		output.flush();
	}

}
