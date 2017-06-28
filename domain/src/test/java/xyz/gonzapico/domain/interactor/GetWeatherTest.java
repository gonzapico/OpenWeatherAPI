package xyz.gonzapico.domain.interactor;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import xyz.gonzapico.domain.executor.PostExecutionThread;
import xyz.gonzapico.domain.executor.ThreadExecutor;
import xyz.gonzapico.domain.repository.WeatherRepository;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

/**
 * Created by gfernandez on 6/28/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class GetWeatherTest {
  private GetWeather getWeatherUseCase;

  @Mock private ThreadExecutor mockThreadExecutor;
  @Mock private WeatherRepository mockWeatherRepository;
  @Mock private PostExecutionThread mockPostExecutionThread;

  private static String FAKE_CITY = "Helsinki";

  @Before
  public void setUp() {
    getWeatherUseCase = new GetWeather(mockWeatherRepository, mockThreadExecutor, mockPostExecutionThread);
  }

  @Test
  public void testGetWeatherObservableCase(){
    getWeatherUseCase.buildUseCaseObservable(GetWeather.WeatherParams.forCity(FAKE_CITY));

    verify(mockWeatherRepository).getWeatherInfoOf(FAKE_CITY);
    verifyNoMoreInteractions(mockWeatherRepository);
    verifyZeroInteractions(mockThreadExecutor);
    verifyZeroInteractions(mockPostExecutionThread);
  }
}