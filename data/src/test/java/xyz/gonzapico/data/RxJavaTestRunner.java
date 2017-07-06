package xyz.gonzapico.data;

import android.support.annotation.NonNull;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.TimeUnit;
import org.mockito.runners.MockitoJUnitRunner;

public class RxJavaTestRunner extends MockitoJUnitRunner {

  public RxJavaTestRunner(Class<?> testClass) throws InvocationTargetException {
    super(testClass);

    RxJavaPlugins.reset();
    Scheduler immediate = new Scheduler() {
      @Override
      public Disposable scheduleDirect(@NonNull Runnable run, long delay, @NonNull TimeUnit unit) {
        // this prevents StackOverflowErrors when scheduling with a delay
        return super.scheduleDirect(run, 0, unit);
      }

      @Override public Worker createWorker() {
        return new ExecutorScheduler.ExecutorWorker(Runnable::run);
      }
    };

    RxJavaPlugins.setInitIoSchedulerHandler(scheduler -> immediate);
    RxJavaPlugins.setInitNewThreadSchedulerHandler(scheduler -> immediate);
  }
}
