package xyz.gonzapico.data.repository;

import android.support.annotation.CallSuper;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.Callable;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.After;
import org.junit.Before;

/**
 * Created by gfernandez on 6/28/17.
 */

public class BaseTestCase {

  MockWebServer server;

  @CallSuper public void setUp() throws Exception {
    server = new MockWebServer();
    RxAndroidPlugins.setInitMainThreadSchedulerHandler(
        new Function<Callable<Scheduler>, Scheduler>() {
          @Override public Scheduler apply(Callable<Scheduler> schedulerCallable) throws Exception {
            return Schedulers.trampoline();
          }
        });
  }

  @CallSuper public void tearDown() throws Exception {
    server.shutdown();
    RxAndroidPlugins.reset();
  }
}
