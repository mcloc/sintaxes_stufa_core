package br.com.sintechs.stufaSerialRead;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

import org.apache.commons.io.IOUtils;

public class MessageReaderThread extends Thread implements Runnable {

	private Logger _log = Logger.getGlobal();
	private volatile boolean running = true;
	private final MessageQueue messageQueue;
	private FileOutputStream outputStreamLock;
	private FileInputStream inputStreamMsg;
	private FileOutputStream outputStreamWriteLock;
	private String messageFilename;
	private boolean IN_LOCK = false;
	private String shmAddressReadLock;
	private String shmAddressWriteLock;

	public MessageReaderThread(String msgFilename, String _shmAddressReadLock, String _shmAddressWriteLock,
			MessageQueue msgQueueObject) throws FileNotFoundException {
		super();
		outputStreamLock = new FileOutputStream(_shmAddressReadLock);
		inputStreamMsg = new FileInputStream(msgFilename);
		outputStreamWriteLock = new FileOutputStream(_shmAddressWriteLock);
		messageFilename = msgFilename;
		messageQueue = msgQueueObject;
		shmAddressReadLock = _shmAddressReadLock;
		shmAddressWriteLock = _shmAddressWriteLock;
	}

	private void resetReader() throws Exception {
		try {
			outputStreamLock.close();
			outputStreamLock = new FileOutputStream(shmAddressReadLock);
			inputStreamMsg = new FileInputStream(messageFilename);
			outputStreamWriteLock.close();
			outputStreamWriteLock = new FileOutputStream(shmAddressWriteLock);
			// inputStreamMsg.getChannel().truncate(0);
			IN_LOCK = false;
		} catch (FileNotFoundException e) {
			throw new Exception(e.getMessage());
			// _log.severe(e.getMessage());

		}

	}
	
	private void writeFile() {
		System.out.println("Writing File");
		
	}
	
	private void readFile() throws Exception {
		try {
			lockMessageFile();
			// inputStreamMsg.getChannel().lock();
			messageQueue.add(IOUtils.readLines(inputStreamMsg, "UTF-8"));
			if (messageQueue.getMessageQueue().size() > 0)
				unlockMessageFile();

			// _log.info(""+messageQueue.getMessageQueue().size());
			// _log.info(messageQueue.getMessage());
			// DEBUG
			Thread.sleep(200);
			// ArrayList<String> x = messageQueue.getMessageQueue();
			// resetReader();
			// _log.info(messageQueue.getMessage());
			IN_LOCK = false;

		} catch (FileNotFoundException e) {
			throw new Exception("TRANSFER_AREA_FILE not found...");
		} catch (IOException e) {
			throw new Exception("TRANSFER_AREA_FILE not found...");
		} finally {

		}
	}

	/**
	 * Unlock and truncate fileMessage
	 */
	public synchronized void unlockMessageFile() throws Exception {
		inputStreamMsg.close();
		FileOutputStream outputStreamMsg = new FileOutputStream(messageFilename, true);
		outputStreamMsg.getChannel().truncate(0);
		outputStreamMsg.getChannel().force(true);
		outputStreamMsg.close();
		IOUtils.write("0", outputStreamLock, "UTF-8");
		outputStreamLock.close();
		IN_LOCK = false;
		resetReader();

	}

	private synchronized void lockMessageFile() throws IOException {
		IN_LOCK = true;
		// outputStreamLock.getChannel().truncate(0);
		IOUtils.write("1", outputStreamLock, "UTF-8");

	}

	private boolean checkLockReadWrite() throws Exception {
		// List<String> writingLock;
		// try {
		// writingLock = IOUtils.readLines(outputStreamWriteLock, "UTF-8");
		// } catch (IOException e) {
		// throw new Exception(e.getMessage());
		// }
		// String line;

		// if(writingLock.size() > 0 )
		// line = writingLock.get(0);
		// else
		// line = "";
		if (IN_LOCK) {
			System.out.println("ReadWrite LOCK...");
			return false;
		}

		return true;
	}

	public synchronized void run() {
		running = true;
		while (running) {
			try {
				if (checkLockReadWrite()) {
					try {
						writeFile();
					} catch (Exception e) {
						_log.severe(e.getMessage());
					}
				}
				Thread.sleep(100);
			} catch (Exception e) {
				_log.severe(e.getMessage());
			}
		}
	}



	public void terminate() {
		running = false;
	}
}
