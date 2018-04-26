package teclan.sigar;

import org.hyperic.sigar.SigarException;
import org.junit.Test;

public class NetTest {

	@Test
	public void net() throws InterruptedException, SigarException {
		
		// NetTraffic netTraffic = SigarUtils.getNetTraffic("10.0.0.134");
		//
		// System.out.println(netTraffic.getRxSpeedInBytes() * 1.0 / 1024 / 1024);
		// System.out.println(netTraffic.getTxSpeedInBytes() * 1.0 / 1024 / 1024);

		// System.out.println(SigarUtils.getDiskLoad("E:\\").getWriteInBytes() * 1.0 /
		// 1024);

		String YW_ENV = System.getProperty("JAVA_HOME");

		System.out.println(YW_ENV);

	}

	public static void main(String[] args) {
		String YW_ENV = System.getProperty("YW_ENV");

		System.out.println(YW_ENV);
	}

}
