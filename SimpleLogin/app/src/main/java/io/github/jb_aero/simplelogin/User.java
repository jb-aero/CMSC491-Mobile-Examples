package io.github.jb_aero.simplelogin;

/**
 * @authors James Bilbrey, Kately Seitz
 */
class User {

	public String email, name;

	public User()
	{

	}

	public User(String email, String name)
	{
		this.email = email;
		this.name = name;
	}

	public static String emailToDB(String email)
	{
		return email.replace('.', '*');
	}

	public static String dbToEmail(String email)
	{
		return email.replace('*', '.');
	}
}
