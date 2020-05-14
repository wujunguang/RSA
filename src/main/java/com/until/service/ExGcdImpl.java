package com.until.service;

import java.math.BigInteger;

import org.springframework.stereotype.Service;

@Service
public class ExGcdImpl implements ExGcd {

	@Override
	public BigInteger ex_gcd(BigInteger e, BigInteger m) {
		GetSpace x = new GetSpace();
		GetSpace y = new GetSpace();
		BigInteger ans = exgcd(e, m, x, y);
		if (ans.equals(BigInteger.ONE)) {
			x.data = x.data.mod(m);
			return x.data;
		}
		return null;
	}

	public static BigInteger exgcd(BigInteger a, BigInteger b, GetSpace x, GetSpace y) {
		if (b.equals(BigInteger.ZERO)) {
			x.data = BigInteger.ONE;
			y.data = BigInteger.ZERO;
			return a;
		}
		BigInteger ans = exgcd(b, a.mod(b), x, y);
		BigInteger tmp = x.data;
		x.data = y.data;
		y.data = tmp.subtract(a.divide(b).multiply(y.data));
		return ans;
	}

}

class GetSpace {
	BigInteger data;

	public GetSpace() {

	}

	public GetSpace(BigInteger data) {
		this.data = data;
	}
}
