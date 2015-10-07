package qianyan.rc;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import android.app.Application;

public class ApplicationUtil extends Application{

	/**
	 * @param args
	 */
	
	private Socket socket;
	private DataOutputStream os;
	private DataInputStream is;
	private String ipnum;
	private int port;
	
	public void init(String ipnum, int port) throws IOException, Exception{
		this.socket = new Socket(ipnum,port);
		this.os = new DataOutputStream(socket.getOutputStream());
		this.is = new DataInputStream(socket.getInputStream());
	}
	
	public Socket getSocket(){
		return socket;
	}
	
	public void setSocket(Socket socket){
		this.socket = socket;
	}
	
	public DataOutputStream getOut(){
		return os;
	}
	
	public DataInputStream getIn(){
		return is;
	}
	
	public void setOut(DataOutputStream os){
		this.os = os;
	}
	
	public void setIn(DataInputStream ss){
		this.is = is;
	}
	
}
