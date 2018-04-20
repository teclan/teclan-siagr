package teclan.sigar;

import java.util.List;

import org.hyperic.sigar.Cpu;
import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.NetConnection;
import org.hyperic.sigar.NetInfo;
import org.hyperic.sigar.NetInterfaceConfig;
import org.hyperic.sigar.NetInterfaceStat;
import org.hyperic.sigar.OperatingSystem;
import org.hyperic.sigar.ProcCredName;
import org.hyperic.sigar.ProcExe;
import org.hyperic.sigar.ProcMem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.Swap;
import org.hyperic.sigar.Who;
import org.hyperic.sigar.cmd.Ps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import teclan.sigar.modle.DiskLoad;
import teclan.sigar.modle.NetTraffic;
import teclan.sigar.modle.ProcessInfo;

public class SigarUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(SigarUtils.class);

	private static Sigar sigar = null;

	private SigarUtils() {

	}

	public static Sigar getInstance() {
		
		if(sigar==null) {
			sigar = new Sigar();
		}

		return sigar;
	}

	/**
	 * Cpu sigar class.
	 * 
	 * @return
	 */
	public static Cpu getCpu() {
		try {
			return SigarUtils.getInstance().getCpu();
		} catch (SigarException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * CPU percentage usage
	 * 
	 * 获取CPU的总体情况
	 * 
	 * @return
	 */
	public static CpuPerc getCpuPerc() {
		try {
			return SigarUtils.getInstance().getCpuPerc();
		} catch (SigarException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
		
	}
	/**
	 * Get list of cpu infomation.
	 * 
	 * 获取 CPU 的信息列表
	 * 
	 * @return
	 */
	public static CpuInfo[] getCpuInfoList() {
		try {
			return SigarUtils.getInstance().getCpuInfoList();
		} catch (SigarException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return new CpuInfo[] {};
	}

	/**
	 * Get system per-CPU info in percentage format
	 * 
	 * @return
	 */
	public static CpuPerc[] getCpuPercList() {
		try {
			return SigarUtils.getInstance().getCpuPercList();
		} catch (SigarException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return new CpuPerc[] {};
	}

	/**
	 * Get list of per-cpu metrics.
	 * 
	 * 获取每个 CPU 的指标
	 * 
	 * @return
	 */
	public static Cpu[]	getCpuList() {
		try {
			return SigarUtils.getInstance().getCpuList();
		} catch (SigarException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return new Cpu [] {};
	}

	/**
	 * Get list of file systems.
	 * 
	 * 获取文件系统信息
	 * 
	 * @return
	 */
	public static FileSystem[] getFileSystemList() {
		try {
			return SigarUtils.getInstance().getFileSystemList();
		} catch (SigarException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return new FileSystem[] {};
	}

	/**
	 * FQDN：(Fully Qualified Domain Name)全限定域名：同时带有主机名和域名的名称
	 * 
	 * @return
	 */
	public static String getFQDN() {
		try {
			return SigarUtils.getInstance().getFQDN();
		} catch (SigarException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return "获取失败";
	}

	/**
	 * Get system memory info.
	 * 
	 * 获取内存信息
	 * 
	 * @return
	 */
	public static Mem getMem() {

		try {
			return SigarUtils.getInstance().getMem();
		} catch (SigarException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 获取文件系统的使用信息，包括读取次数、读取字节、写次数、写字节数
	 * 
	 * @param fileSystem
	 *            文件系统，例如windows下的盘符
	 * @return
	 */
	public static FileSystemUsage getMountedFileSystemUsage(String fileSystem) {
		try {
			return SigarUtils.getInstance().getMountedFileSystemUsage(fileSystem);
		} catch (SigarException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 获取指定文件系统的负载，此方法内部有睡眠，睡眠时长 1s
	 * 
	 * @param fileSystem
	 *            文件系统，例如windows的某个盘符
	 * @return
	 */
	public static DiskLoad getDiskLoad(String fileSystem) {
		try {

			if (fileSystem == null || "".equals(fileSystem.trim())) {
				return null;
			}

			FileSystemUsage before = SigarUtils.getInstance().getMountedFileSystemUsage(fileSystem);
			long startTime = System.currentTimeMillis();

			Thread.sleep(1000);

			FileSystemUsage after = SigarUtils.getInstance().getMountedFileSystemUsage(fileSystem);
			long afterTime = System.currentTimeMillis();

			DiskLoad diskLoad = new DiskLoad();

			long deltaTime = afterTime - startTime;

			diskLoad.setFileSystem(fileSystem);
			diskLoad.setDiskQueue((after.getDiskQueue() - before.getDiskQueue()) * 8 / deltaTime * 1000);
			diskLoad.setDiskReads((after.getDiskReads() - before.getDiskReads()) / deltaTime);
			diskLoad.setDiskWrites((after.getDiskWrites() - before.getDiskWrites()) / deltaTime);
			diskLoad.setWriteInBytes((after.getDiskWriteBytes() - before.getDiskWriteBytes()) / deltaTime);
			diskLoad.setReadInBytes((after.getDiskReadBytes() - before.getDiskReadBytes()) / deltaTime);

			return diskLoad;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;

	}

	public static NetConnection[] getNetConnectionList(int prot) {
		try {
			return SigarUtils.getInstance().getNetConnectionList(prot);
		} catch (SigarException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 获取通用网络配置，包括默认网关，DNS，域名，主机名等
	 * 
	 * @return
	 */
	public static NetInfo getNetInfo() {
		try {
			return SigarUtils.getInstance().getNetInfo();
		} catch (SigarException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 获取本机器的主机名称
	 * 
	 * @return
	 */
	public static String getHostName() {

		NetInfo info = getNetInfo();

		return info == null ? "" : info.getHostName();
	}

	/**
	 * Get pid of the current process.
	 * 
	 * @return
	 */
	public static long getPid() {
		return SigarUtils.getInstance().getPid();
	}

	/**
	 * 获取指定进程的启动参数
	 * 
	 * @param pid
	 * @return
	 */
	public static String[] getProcArgs(long pid) {
		try {
			return SigarUtils.getInstance().getProcArgs(pid);
		} catch (SigarException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}


	/**
	 * 获取指定进程的名称
	 * 
	 * @param pid
	 * @return
	 */
	public static ProcCredName getProcCredName(long pid) {
		try {
			return SigarUtils.getInstance().getProcCredName(pid);
		} catch (SigarException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * Get process current working directory.
	 * 
	 * @param pid
	 * @return
	 */
	public static ProcExe getProcExe(long pid) {
		try {
			return SigarUtils.getInstance().getProcExe(pid);
		} catch (SigarException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * Get system process list.
	 * 
	 * @return
	 */
	public static long[] getProcList() {
		try {
			return SigarUtils.getInstance().getProcList();
		} catch (SigarException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 获取指定进程信息
	 * 
	 * @param pid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static ProcessInfo getProcessInfo(long pid) {


		List<String> list = null;
		try {
			list = Ps.getInfo(getInstance(), pid);
		} catch (SigarException e) {
			LOGGER.error(e.getMessage(), e);
		}

		ProcessInfo info = new ProcessInfo();
		info.setPid(list.get(0));
		info.setUser(list.get(1));
		info.setStartTime(list.get(2));
		info.setMemSize(list.get(3));
		info.setMemUse(list.get(4));
		info.setMemhare(list.get(5));
		info.setState(list.get(6));
		info.setCpuTime(list.get(7));
		info.setName(list.get(8));

		return info;
	}

	/**
	 * Get process memory info.
	 * 
	 * @param pid
	 * @return
	 */
	public static ProcMem getProcMem(long pid) {
		try {
			return SigarUtils.getInstance().getProcMem(pid);
		} catch (SigarException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * Find the pid of the process which is listening on the given port.
	 * 
	 * Supported Platforms: Linux, Windows 2003, Windows XP, AIX.
	 * 
	 * @param protocol
	 *            - NetFlags.CONN_TCP or NetFlags.CONN_UDP.
	 * @param port
	 * @return
	 */
	public static long getProcPort(int protocol, long port) {
		try {
			return SigarUtils.getInstance().getProcPort(protocol, port);
		} catch (SigarException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return -1;
	}

	/**
	 * Get system swap info.
	 * 
	 * @return
	 */
	public static Swap getSwap() {
		try {
			return SigarUtils.getInstance().getSwap();
		} catch (SigarException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	public static Who[] getWhoList() {
		try {
			return SigarUtils.getInstance().getWhoList();
		} catch (SigarException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 获取操作系统信息
	 * 
	 * @return
	 */
	public static OperatingSystem getOsInfos() {
		return OperatingSystem.getInstance();
	}

	public static String[] getNetInterfaceList() {
		try {
			return SigarUtils.getInstance().getNetInterfaceList();
		} catch (SigarException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return new String[] {};
	}

	public static NetInterfaceConfig getNetInterfaceConfig(String interfaceName) {
		try {
			return SigarUtils.getInstance().getNetInterfaceConfig(interfaceName);
		} catch (SigarException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	public static NetInterfaceStat getNetInterfaceStat(String interfaceName) {
		try {
			return SigarUtils.getInstance().getNetInterfaceStat(interfaceName);
		} catch (SigarException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;

	}

	/**
	 * 获取指定IP所在网卡的网络流量，此方法内部有睡眠，睡眠时长 1s
	 * 
	 * @param ip
	 * @return
	 */
	public static NetTraffic getNetTraffic(String ip) {
		
		String[] interfaceNames = getNetInterfaceList();

		for (int i = 0; i < interfaceNames.length; i++) {
			

			NetInterfaceConfig config = getNetInterfaceConfig(interfaceNames[i]);
			
			if (!ip.equals(config.getAddress())) {
				continue;
			}
			
			NetInterfaceStat statBefore = getNetInterfaceStat(interfaceNames[i]);
			long startTime = System.currentTimeMillis();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				LOGGER.error(e.getMessage(), e);
			}

			NetInterfaceStat statAfter = getNetInterfaceStat(interfaceNames[i]);
			long afterTime = System.currentTimeMillis();

			long deltaTime = afterTime - startTime;

			NetTraffic netTraffic = new NetTraffic();
			netTraffic.setIp(ip);
			netTraffic.setRxSpeedInBytes((statAfter.getRxBytes() - statBefore.getRxBytes()) * 8 / deltaTime * 1000);
			netTraffic.setTxSpeedInBytes((statAfter.getTxBytes() - statBefore.getTxBytes()) * 8 / deltaTime * 1000);
			netTraffic.setRxSpeesInPackets((statAfter.getRxPackets() - statBefore.getRxPackets()) / deltaTime);
			netTraffic.setTxSpeesInPackets((statAfter.getTxPackets() - statBefore.getTxPackets()) / deltaTime);

			return netTraffic;
		}

		return null;

	}

}
