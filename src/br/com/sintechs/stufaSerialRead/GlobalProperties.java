package br.com.sintechs.stufaSerialRead;

public class GlobalProperties {
	
	private String SHM_ADDRESS_READ = "/dev/shm/serial2arduinoRead";
	private String SHM_ADDRESS_WRITE = "/dev/shm/serial2arduinoWrite";
	private String SHM_ADDRESS_READ_LOCK = "/dev/shm/serial2arduinoReadLock";
	private String SHM_ADDRESS_WRITE_LOCK = "/dev/shm/serial2arduinoWriteLock";
	private Integer BAUD_RATE = 115200;
	private Integer IPC_SERVER_PORT = 1932;
	
	
	public String getSHM_ADDRESS_READ() {
		return SHM_ADDRESS_READ;
	}
	public void setSHM_ADDRESS_READ(String sHM_ADDRESS_READ) {
		SHM_ADDRESS_READ = sHM_ADDRESS_READ;
	}
	public String getSHM_ADDRESS_WRITE() {
		return SHM_ADDRESS_WRITE;
	}
	public void setSHM_ADDRESS_WRITE(String sHM_ADDRESS_WRITE) {
		SHM_ADDRESS_WRITE = sHM_ADDRESS_WRITE;
	}
	public String getSHM_ADDRESS_READ_LOCK() {
		return SHM_ADDRESS_READ_LOCK;
	}
	public void setSHM_ADDRESS_READ_LOCK(String sHM_ADDRESS_READ_LOCK) {
		SHM_ADDRESS_READ_LOCK = sHM_ADDRESS_READ_LOCK;
	}
	public String getSHM_ADDRESS_WRITE_LOCK() {
		return SHM_ADDRESS_WRITE_LOCK;
	}
	public void setSHM_ADDRESS_WRITE_LOCK(String sHM_ADDRESS_WRITE_LOCK) {
		SHM_ADDRESS_WRITE_LOCK = sHM_ADDRESS_WRITE_LOCK;
	}
	public Integer getBAUD_RATE() {
		return BAUD_RATE;
	}
	public void setBAUD_RATE(Integer bAUD_RATE) {
		BAUD_RATE = bAUD_RATE;
	}
	public Integer getIPC_SERVER_PORT() {
		return IPC_SERVER_PORT;
	}
	public void setIPC_SERVER_PORT(Integer iPC_SERVER_PORT) {
		IPC_SERVER_PORT = iPC_SERVER_PORT;
	}

	
	
	
}
