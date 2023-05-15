package serveur;

import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;




public class ForumImpl extends UnicastRemoteObject implements Forum {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int id;//id d'un client;
	String hostname;
	TreeMap<Integer,proxy> Proxies;
	
	protected ForumImpl() throws RemoteException {
		super();
		id=0;
		Proxies= new TreeMap<Integer,proxy>();
		
		try{
			hostname = InetAddress.getLocalHost().getHostName();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}

	@Override
	public int entrer(proxy pr) throws RemoteException {
		// TODO Auto-generated method stub		
		id++;
    	pr.ecouter("----------client connecté a "+hostname+ " avec Id "+id);
    	Proxies.put(id,pr);
    	return id;
		
	}
	@Override
	public String qui() throws RemoteException {
		// TODO Auto-generated method stub
		
		String inter="";
		Set<Integer> keys = Proxies.keySet();
		 for(int key: keys)
		 {
			 inter+=" ,User N° "+key; 
		 }
		return inter+"\n";
	}

	@Override
	public void dire(int id, String msg) throws RemoteException {
		// TODO Auto-generated method stub
		String Msg="User N°"+id+" : "+msg;
		Set<Integer> keys = Proxies.keySet();
		for(int key: keys)
		{
			proxy P=Proxies.get(key);
			P.ecouter(Msg);
		}
	}

	@Override
	public void quiter(int id) throws RemoteException {
		// TODO Auto-generated method stub
		proxy pr;
        pr=Proxies.get(id);
        Proxies.remove(id);
        pr.ecouter("-------Vous etes N°"+id+" deconnecté de "+hostname);
	}

	
	

	
	public static void main(String[] args) {
        try {
            Forum server = new ForumImpl();
            Naming.rebind("rmi://localhost:1298/Server", server);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
