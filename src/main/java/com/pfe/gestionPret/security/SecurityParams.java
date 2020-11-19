package com.pfe.gestionPret.security;

public class SecurityParams {
	public static final String JWT_HEADER_NAME="Authorization";
	public static final String SECRET="motez.gharbi@hotmail.com";
	public static final long EXPIRATION=10*24*60*60*1000;
	public static final String HEADER_PREFIX="Bearer ";
}
