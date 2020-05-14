package com.until.service;

import java.math.BigInteger;
import java.util.Random;

import org.springframework.stereotype.Service;
@Service
public class GenerateDataImpl implements GenerateData {

	@Override
	public BigInteger generatePrimeP() {
		BigInteger p = BigInteger.probablePrime(32, new Random());
		return p;
	}

	@Override
	public BigInteger generatePrimeQ() {
		BigInteger q = BigInteger.probablePrime(32, new Random());
		return q;
	}

	@Override
	public BigInteger generateIntergerE() {
		BigInteger e = BigInteger.probablePrime((int) (Math.random() * 63 + 2), new Random());
		return e;
	}

}
