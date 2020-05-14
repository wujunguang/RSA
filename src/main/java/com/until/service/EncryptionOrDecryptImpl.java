package com.until.service;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.until.bean.CiphertextStorage;

@Service
public class EncryptionOrDecryptImpl implements EncryptionOrDecrypt {
//公钥：n e 私钥 ：n d

	@Autowired
	private ExGcd exGcd;

	CiphertextStorage ciphertextStorage = new CiphertextStorage();

	public String Encryption(String Ciphertext, BigInteger p, BigInteger q, BigInteger e) {
		BigInteger[] encrypt = new BigInteger[Ciphertext.length()];
		BigInteger a, b;// a为明文，b为密文
		BigInteger n = p.multiply(q);
		String src = "";
		for (int i = 0; i < Ciphertext.length(); ++i) {
			a = BigInteger.valueOf(Ciphertext.charAt(i));
			b = a.modPow(e, n);
			encrypt[i] = b;
			src += b.toString();
		}
		ciphertextStorage.setSrc(encrypt);
		return src;
	}

	public String Decrypt(BigInteger p, BigInteger q, BigInteger e) {
		BigInteger n = p.multiply(q);
		BigInteger a, b;// a为明文，b为密文
		BigInteger[] tt = ciphertextStorage.getSrc();
		StringBuffer plaintext = new StringBuffer();
		n = p.multiply(q);
		BigInteger m = p.subtract(new BigInteger("1")).multiply(q.subtract(new BigInteger("1")));
		BigInteger d = exGcd.ex_gcd(e, m);
		// BigInteger dd = e.modInverse(m);// 找一个整数 d，使得 (e * d ) % m = 1
		for (int i = 0; i < tt.length; ++i) {
			b = tt[i];
			a = b.modPow(d, n);// b^d%n
			plaintext.append((char) a.intValue());
		}
		return plaintext.toString();
	}
}
