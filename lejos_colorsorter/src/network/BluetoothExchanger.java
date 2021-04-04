package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lejos.hardware.Bluetooth;
import lejos.remote.nxt.BTConnection;
import lejos.remote.nxt.BTConnector;
import lejos.remote.nxt.NXTConnection;

public class BluetoothExchanger implements NetworkInterface {

	BTConnection connection;
	BTConnector connector;

	DataInputStream inputStream;
	DataOutputStream outputStream;

	/* TODO update return / handle no connection */
	public boolean connect() {
		connector = (BTConnector) Bluetooth.getNXTCommConnector();
		connection = (BTConnection) connector.waitForConnection(60000, NXTConnection.RAW);

		inputStream = connection.openDataInputStream();
		outputStream = connection.openDataOutputStream();
		return true;
	}

	public byte[] listen() throws IOException {
		return inputStream.readAllBytes();

	}

	public void send(byte[] data) throws IOException {
		outputStream.write(data, 0, data.length);
	}

}
