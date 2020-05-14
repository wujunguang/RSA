package com.until.service;

import java.math.BigInteger;

public interface EncryptionOrDecrypt {
	public String Encryption(String Ciphertext, BigInteger p, BigInteger q, BigInteger e);

	public String Decrypt(BigInteger p, BigInteger q, BigInteger e);

}
