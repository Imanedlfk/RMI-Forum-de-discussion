package serveur;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import client.User;

public class proxyImpl  extends UnicastRemoteObject implements Serializable,proxy  {

	private User user;

	public proxyImpl(User user) throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		this.user=user;
	}
	public proxyImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ecouter(String msg) throws RemoteException {
		// TODO Auto-generated method stub
		user.ecrire(msg);
	}
}
