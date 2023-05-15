package serveur;

import java.rmi.Remote;
import java.rmi.RemoteException;

import client.User;

public interface proxy extends Remote {
	public void ecouter(String msg) throws RemoteException;

}
