package teclan.sigar;

import org.hyperic.sigar.CpuPerc;
import org.junit.Test;

public class CpuTest {

	@Test
	public void cpuTest() {

		CpuPerc cpuPerc = SigarUtils.getCpuPerc();

		System.out.println(cpuPerc);

	}

}
