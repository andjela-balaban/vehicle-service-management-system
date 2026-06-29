package org.unibl.etf.accounting.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import org.unibl.etf.model.Receipt;

public interface AccountingInterface extends Remote {
	void serializeReceipt(Receipt receipt) throws RemoteException;
}
