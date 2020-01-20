package com.dao;

import com.models.UserAttempts;

public interface UserAttemptsDAO {
	void updateFailAttempts(String userName);
	void resetFailAttempts(String userName);
	UserAttempts getUserAttempts(int userId);
	UserAttempts getUserAttempts(String userName);
}