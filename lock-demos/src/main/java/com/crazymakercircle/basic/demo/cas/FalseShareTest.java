package com.crazymakercircle.basic.demo.cas;

import com.crazymakercircle.basic.demo.cas.busi.ContendedDemo;
import com.crazymakercircle.basic.demo.cas.busi.PaddedAtomicLong;
import com.crazymakercircle.util.Print;
import org.openjdk.jol.info.ClassLayout;

public class FalseShareTest  {
    @org.junit.Test
    public void testLongAdder()
    {
        PaddedAtomicLong atomicLong=  new PaddedAtomicLong();
        String printable=  ClassLayout.parseInstance(atomicLong).toPrintable();

        Print.tcfo("printable = " + printable);
    }

    @org.junit.Test
    public void testContendedDemo()
    {
        ContendedDemo contendedDemo=  new ContendedDemo();
        String printable=  ClassLayout.parseInstance(contendedDemo).toPrintable();

        Print.tcfo("printable = " + printable);
    }

}