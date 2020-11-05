package bench;

import com.sun.management.GarbageCollectionNotificationInfo;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;
import javax.management.MBeanServer;
import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import javax.management.ObjectName;
import javax.management.openmbean.CompositeData;

/*
О формате логов
http://openjdk.java.net/jeps/158
*/

/*
-Xms2048m
-Xmx2048m
-XX:+HeapDumpOnOutOfMemoryError
-XX:HeapDumpPath=./GC_Log
-verbose:gc
-Xlog:gc*

-----------------------
Список GC:
-XX:+UseSerialGC
-XX:+UseParallelGC
-XX:+UseG1GC
-XX:+UnlockExperimentalVMOptions -XX:+UseZGC //Доступен на Windows начиная с JDK 14
 */

public class GcDemo {
  private static int minorCount = 0;
  private static int majorCount = 0;
  private static float minorTime = 0;
  private static float majorTime = 0;

  public static void main(String... args) throws Exception {
    System.out.println("Starting pid: " + ManagementFactory.getRuntimeMXBean().getName());
    switchOnMonitoring();
    long beginTime = System.currentTimeMillis();

    int size = 5 * 1000 * 1000;
    int loopCounter = 1000;

    MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
    ObjectName name = new ObjectName("ru.sbrf:type=Benchmark");

    Benchmark mbean = new Benchmark(loopCounter);
    mbs.registerMBean(mbean, name);
    mbean.setSize(size);
    mbean.run();

    System.out.println("time in ms: " + (System.currentTimeMillis() - beginTime) / 1000);
  }

  private static void switchOnMonitoring() {
    List<GarbageCollectorMXBean> gcbeans = java.lang.management.ManagementFactory.getGarbageCollectorMXBeans();
    for (GarbageCollectorMXBean gcbean : gcbeans) {
      System.out.println("GC name:" + gcbean.getName());
      NotificationEmitter emitter = (NotificationEmitter) gcbean;
      NotificationListener listener = (notification, handback) -> {
        if (notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)) {
          GarbageCollectionNotificationInfo info = GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());
          String gcName = info.getGcName();
          String gcAction = info.getGcAction();
          String gcCause = info.getGcCause();

          long startTime = info.getGcInfo().getStartTime();
          long duration = info.getGcInfo().getDuration();

          /*
            Можно было бы воспользоваться JConsole, но, к сожалению,
            она переодически отваливается с timeout socket exception :(
            Поэтому считаем сами
          */
          if(gcAction.indexOf("minor") > 0)
          {
            minorCount ++;
            minorTime+= duration;
          }
          else if(gcAction.indexOf("major") > 0)
          {
            majorCount ++;
            majorTime+= duration;
          }

          System.out.println("start:" + startTime +
                  " Name:" + gcName +
                  ", action:" + gcAction +
                  ", gcCause:" + gcCause +
                  "(" + duration + " ms)");
          System.out.println("Minor = "+minorCount+" ("+minorTime/1000+") , Major = "+majorCount+" ("+majorTime/1000+")");
        }
      };
      emitter.addNotificationListener(listener, null, null);
    }
  }

}
