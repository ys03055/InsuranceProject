package service;

import entity.Client;

public interface ClientService {
<<<<<<< HEAD
	public boolean register(Client client);
	public Client login(String id, String pw);
	public boolean delete(String id, String pw);
	public Client checkClientID(String clientID);
}
=======
	
	public boolean register();
	public Client login();
	public boolean delete();

}
>>>>>>> 0256c1c125ff4c463f1198edcc973869bcb36a1c
