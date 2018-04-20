package teclan.sigar.modle;

import com.alibaba.fastjson.JSON;

/**
 * 网络流量，默认单位：秒
 * 
 * @author dev
 *
 */
public class NetTraffic {

	private String ip;

	/**
	 * 每秒接收的字节数
	 */
	private long rxSpeedInBytes;
	/**
	 * 每秒发送的字节数
	 */
	private long txSpeedInBytes;
	/**
	 * 每秒接收的包裹数
	 */
	private long rxSpeesInPackets;
	/**
	 * 每秒发送的包裹数
	 */
	private long txSpeesInPackets;

	/**
	 * 总的字节数（发送和接收）
	 */
	private long speedInBytes;
	/**
	 * 总的包裹数（发送和接收）
	 */
	private long speedInPackets;

	public NetTraffic() {

	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public long getRxSpeedInBytes() {
		return rxSpeedInBytes;
	}

	public void setRxSpeedInBytes(long rxSpeedInBytes) {
		this.rxSpeedInBytes = rxSpeedInBytes;
	}

	public long getTxSpeedInBytes() {
		return txSpeedInBytes;
	}

	public void setTxSpeedInBytes(long txSpeedInBytes) {
		this.txSpeedInBytes = txSpeedInBytes;
	}

	public long getRxSpeesInPackets() {
		return rxSpeesInPackets;
	}

	public void setRxSpeesInPackets(long rxSpeesInPackets) {
		this.rxSpeesInPackets = rxSpeesInPackets;
	}

	public long getTxSpeesInPackets() {
		return txSpeesInPackets;
	}

	public void setTxSpeesInPackets(long txSpeesInPackets) {
		this.txSpeesInPackets = txSpeesInPackets;
	}

	public long getSpeedInBytes() {
		return txSpeedInBytes + rxSpeedInBytes;
	}

	public void setSpeedInBytes(long speedInBytes) {
		this.speedInBytes = speedInBytes;
	}

	public long getSpeedInPackets() {
		return txSpeesInPackets + rxSpeesInPackets;
	}

	public void setSpeedInPackets(long speedInPackets) {
		this.speedInPackets = speedInPackets;
	}

	public String toString() {
		return JSON.toJSONString(this);
	}

}
