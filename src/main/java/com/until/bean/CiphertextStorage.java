package com.until.bean;

import java.math.BigInteger;

public class CiphertextStorage {
	BigInteger[] src;

	public CiphertextStorage() {
		src = new BigInteger[1];
	}

	public BigInteger[] getSrc() {
		return src;
	}

	public void setSrc(BigInteger[] src) {
		this.src = src;
	}

}
