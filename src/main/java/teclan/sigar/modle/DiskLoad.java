package teclan.sigar.modle;

import com.alibaba.fastjson.JSON;

/**
 * 磁盘负载，针对单个文件系统，例如windows的某个盘符，单位：秒
 * 
 * @author dev
 *
 */
public class DiskLoad {

	/**
	 * 文件系统，例如windows的某个盘符
	 */
	private String fileSystem;

	/**
	 * 读取速度
	 */
	private long readInBytes;

	/**
	 * 写速率
	 */
	private long writeInBytes;

	/**
	 * 读取次数
	 */
	private long diskReads;

	/**
	 * 写次数
	 */
	private long diskWrites;

	/**
	 * 磁盘队列：磁盘的读写请求的平均数量。
	 */
	private double diskQueue;

	public DiskLoad() {

	}

	public String getFileSystem() {
		return fileSystem;
	}

	public void setFileSystem(String fileSystem) {
		this.fileSystem = fileSystem;
	}

	public long getReadInBytes() {
		return readInBytes;
	}

	public void setReadInBytes(long readInBytes) {
		this.readInBytes = readInBytes;
	}

	public long getWriteInBytes() {
		return writeInBytes;
	}

	public void setWriteInBytes(long writeInBytes) {
		this.writeInBytes = writeInBytes;
	}

	public long getDiskReads() {
		return diskReads;
	}

	public void setDiskReads(long diskReads) {
		this.diskReads = diskReads;
	}

	public long getDiskWrites() {
		return diskWrites;
	}

	public void setDiskWrites(long diskWrites) {
		this.diskWrites = diskWrites;
	}

	public double getDiskQueue() {
		return diskQueue;
	}

	public void setDiskQueue(double diskQueue) {
		this.diskQueue = diskQueue;
	}

	public String toString() {
		return JSON.toJSONString(this);
	}


}
