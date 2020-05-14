package com.until.service;

import java.math.BigInteger;

import org.springframework.stereotype.Service;

@Service
public class GcdImpl implements Gcd {

	@Override
	public boolean GCD(BigInteger a, BigInteger b) {
		BigInteger t = gcd(a, b);
		if (t.equals(BigInteger.ONE))
			return true;
		return false;
	}

	public static BigInteger gcd(BigInteger m, BigInteger n) {
		if (n.equals(BigInteger.ZERO))
			return m;
		else
			return gcd(n, m.mod(n));
	}
}
