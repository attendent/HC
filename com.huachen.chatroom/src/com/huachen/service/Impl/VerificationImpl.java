package com.huachen.service.Impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.huachen.service.Verification;

public class VerificationImpl implements Verification {

	@Override
	public boolean isLength(String username, String password) {
		if (username != null && password != null) {
			if (username.length() > 0 && password.length() > 0 && username.length() < 16 && password.length() < 16) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isLength(String username, String password, String nextpassword) {
		if (username != null && password != null && nextpassword != null) {
			if (username.length() > 0 && password.length() > 0 && nextpassword.length() > 0 && username.length() < 16
					&& password.length() < 16 && nextpassword.length() < 16) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isCode(String checkcode, String inputCode) {
		if (inputCode != null && checkcode != null) {
			if (inputCode.equalsIgnoreCase(checkcode)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean mailFormat(String mail) {
		boolean tag = true;
		final String pattern1 = "^[a-z0-9]+([._\\\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$";
		final Pattern pattern = Pattern.compile(pattern1);
		final Matcher mat = pattern.matcher(mail);	
		if (!mat.find()) {
			tag = false;
		}
		return tag;
	}
}
