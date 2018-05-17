package com.huachen.Service.Impl;

import com.huachenservice.Verification;

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
}
