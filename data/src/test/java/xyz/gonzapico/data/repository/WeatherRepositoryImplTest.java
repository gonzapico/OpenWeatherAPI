package xyz.gonzapico.data.repository;

import io.reactivex.observers.TestObserver;
import java.util.List;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import retrofit2.HttpException;
import xyz.gonzapico.data.MockResponseDispatcher;
import xyz.gonzapico.data.RxJavaTestRunner;
import xyz.gonzapico.data.cloud.WeatherService;
import xyz.gonzapico.data.repository.adapter.RetrofitFactory;
import xyz.gonzapico.domain.OpenWeatherAPIResponse;
import xyz.gonzapico.domain.repository.WeatherRepository;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static xyz.gonzapico.data.cloud.WeatherServiceKt.HELSINKI;

/**
 * Created by gfernandez on 6/29/17.
 */
@RunWith(RxJavaTestRunner.class) public class WeatherRepositoryImplTest extends BaseTestCase {

  @Spy RetrofitFactory retrofitFactory;
  private WeatherRepository repository;

  @Before public void setUp() throws Exception {
    super.setUp();
    MockResponseDispatcher.reset();
    server.setDispatcher(MockResponseDispatcher.DISPATCHER);
    server.start();
    repository = new WeatherRepositoryImpl(retrofitFactory, server.url("").url().toString());
  }

  @After public void tearDown() throws Exception {
    super.tearDown();
  }

  /**
   * Checks whether the User repository and service is working as expected and the behaviour of the
   * code have not changed.
   */
  @Test public void testRepositoryGotResponseOkForCity() throws InterruptedException {
    // Preconditions
    TestObserver<OpenWeatherAPIResponse> subscriber = new TestObserver<>();

    // Attaches the subscriber and executes the Observable.
    repository.getWeatherInfoOf(HELSINKI).subscribe(subscriber);
    RecordedRequest request = server.takeRequest();

    // Checks whether these methods from inner objects are called.
    verify(retrofitFactory).setBaseUrl(anyString());
    verify(retrofitFactory).create(WeatherService.class);
    verify(retrofitFactory).getClient();

    // Ensures there aren't new changes on the Rest adapter structure
    verifyNoMoreInteractions(retrofitFactory);

    // Ensures Observer and Requests are working as expected
    List<OpenWeatherAPIResponse> events = subscriber.values();
    subscriber.assertNoErrors();
    subscriber.assertValueCount(1);
    subscriber.assertComplete();
    //subscriber.assertNotSubscribed();
    Assert.assertEquals(1, server.getRequestCount());
    Assert.assertNotNull(events);
    Assert.assertEquals(1, events.size());
    Assert.assertEquals(HELSINKI, events.get(0).getName());
  }

  /**
   * Ensures a 500 exception is thrown as an event through the observable stream
   */
  @Test public void testRepositoryGot500Response() {
    // Preconditions
    TestObserver<OpenWeatherAPIResponse> subscriber = new TestObserver<>();
    MockResponseDispatcher.RETURN_500 = true;

    // Attaches the subscriber and executes the Observable.
    repository.getWeatherInfoOf(HELSINKI).subscribe(subscriber);

    // We ensure we've got 500 Server Error in the onError method of the Subscriber.
    subscriber.assertError(retrofit2.HttpException.class);
    subscriber.assertNoValues();
    subscriber.assertNotSubscribed();
    List<Throwable> exceptions = subscriber.errors();
    //noinspection all
    Assert.assertEquals(500, ((HttpException) exceptions.get(0)).code());
  }

  /**
   * Ensures a 404 exception is thrown as an event through the observable stream
   */
  @Test public void testRepositoryGot404Response() {
    // Preconditions
    TestObserver<OpenWeatherAPIResponse> subscriber = new TestObserver<>();

    // Attaches the subscriber and executes the Observable.
    repository.getWeatherInfoOf(HELSINKI).subscribe(subscriber);

    // We ensure we've got 404 Not Found in the onError method of the Subscriber.
    subscriber.assertError(HttpException.class);
    subscriber.assertNoValues();
    subscriber.assertNotSubscribed();
    List<Throwable> exceptions = subscriber.errors();
    //noinspection all
    Assert.assertEquals(404, ((HttpException) exceptions.get(0)).code());
  }
}