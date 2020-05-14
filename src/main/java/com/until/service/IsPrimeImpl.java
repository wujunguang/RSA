package com.until.service;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.springframework.stereotype.Service;

@Service
public class IsPrimeImpl implements IsPrime {

	@Override
	public boolean judge(BigInteger x) {
		if (x.equals(BigInteger.ONE) || x.equals(BigInteger.ZERO))
			return false;
		if (x.equals(BigInteger.TWO)) {
			return true;
		}
		for (int i = 1; i <= 30; i++) {
			double t1 = Math.random();
			BigDecimal t2 = BigDecimal.valueOf(t1);
			String t3 = String.valueOf(x.subtract(BigInteger.TWO));
			BigDecimal t4 = new BigDecimal(t3);
			BigDecimal t5 = t2.multiply(t4);
			BigInteger ans = t5.toBigInteger();
			BigInteger now = ans.add(BigInteger.TWO);
			// int now = (int)Math.random()*(x-2)+2;
			if (quick_pow(now, x.subtract(BigInteger.ONE), x).equals(BigInteger.ONE) == false)
				return false;
		}
		return true;
	}

	public static BigInteger quick_pow(BigInteger a, BigInteger b, BigInteger mod) {
		BigInteger ans = BigInteger.ONE;
		while (b.equals(BigInteger.ZERO) == false) {
			if (b.mod(BigInteger.TWO).equals(BigInteger.ONE) == true)
				ans = ans.multiply(a).mod(mod);
			a = a.multiply(a).mod(mod);
			b = b.divide(BigInteger.TWO);
		}
		return ans;
	}
}
