package com.until.controller;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.until.bean.Msg;
import com.until.service.EncryptionOrDecrypt;
import com.until.service.Gcd;
import com.until.service.GenerateData;
import com.until.service.IsPrime;

@Controller
public class rsa2Controller {
	@Autowired
	private EncryptionOrDecrypt encryptionOrDecrypt;

	@Autowired
	private Gcd gcd;

	@Autowired
	private IsPrime isPrime;

	@Autowired
	private GenerateData generateData;

	@RequestMapping("/encryption.action")
	@ResponseBody
	public Msg encryption(@RequestParam(value = "p") BigInteger p, @RequestParam(value = "q") BigInteger q,
			@RequestParam(value = "e") BigInteger e, @RequestParam(value = "plainText") String plainText) {
		String src = encryptionOrDecrypt.Encryption(plainText, p, q, e);
		return Msg.success().add("result", src);
	}

	@RequestMapping("/decrypt.action")
	@ResponseBody
	public Msg decrypt(@RequestParam(value = "p") BigInteger p, @RequestParam(value = "q") BigInteger q,
			@RequestParam(value = "e") BigInteger e) {
		String src = encryptionOrDecrypt.Decrypt(p, q, e);
		return Msg.success().add("result", src);
	}

	@RequestMapping("/generate.action")
	@ResponseBody
	public Msg generate() {
		boolean flag = false;
		BigInteger p = null, q = null, e = null;
		while (flag == false) {
			p = generateData.generatePrimeP();
			q = generateData.generatePrimeQ();
			e = generateData.generateIntergerE();
			BigInteger eulerN = p.subtract(new BigInteger("1")).multiply(q.subtract(new BigInteger("1")));
			if (isPrime.judge(p) == true && isPrime.judge(q) == true && gcd.GCD(eulerN, e))
				flag = true;
		}
		return Msg.success().add("resultP", p).add("resultQ", q).add("resultE", e);
	}

	@RequestMapping("/verification1.action")
	@ResponseBody
	public String verification1(@RequestParam(value = "p") BigInteger p) {
		if (isPrime.judge(p) == true) {
			return "true";
		} else
			return "false";
	}

	@RequestMapping("/verification2.action")
	@ResponseBody
	public String verification2(@RequestParam(value = "q") BigInteger q) {
		if (isPrime.judge(q) == true) {
			return "true";
		} else
			return "false";
	}

	@RequestMapping("/verification3.action")
	@ResponseBody
	public String verification3(@RequestParam(value = "p") BigInteger p, @RequestParam(value = "q") BigInteger q,
			@RequestParam(value = "e") BigInteger e) {
		BigInteger eulerN = p.subtract(new BigInteger("1")).multiply(q.subtract(new BigInteger("1")));
		boolean flag = gcd.GCD(eulerN, e);
		if (flag) {
			return "true";
		} else {
			return "false";
		}
	}
}
