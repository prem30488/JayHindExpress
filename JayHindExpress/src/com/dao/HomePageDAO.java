package com.dao;

import com.models.HomePage;

public interface HomePageDAO {
	public HomePage getconfig(int configId);
	public HomePage getDefaultconfig();
	public void updateDefaultConfig(HomePage defaultConfig);
}
