package com.example.project.util;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.enterprise.inject.Typed;

@Typed
public final class MessageDigests {

	public enum Algorithm {
		SHA_256("SHA-256");

		private final String name;

		private Algorithm(String name) {
			this.name = name;
		}
	}

	private MessageDigests() {
		throw new AssertionError();
	}

	private static MessageDigest getMessageDigestInstance(Algorithm algorithm) {
		try {
			return MessageDigest.getInstance(algorithm.name);
		}
		catch (NoSuchAlgorithmException e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public static byte[] digest(String message, byte[] salt, Algorithm algorithm) {
		MessageDigest messageDigest = getMessageDigestInstance(algorithm);
		messageDigest.update(salt);
		return messageDigest.digest(message.getBytes(UTF_8));
	}
}
