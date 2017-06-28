package xyz.gonzapico.domain.executor;

import java.util.concurrent.Executor;
import xyz.gonzapico.domain.interactor.BaseUseCase;

/**
 * Executor implementation can be based on different frameworks or techniques of asynchronous
 * execution, but every implementation will execute the
 * {@link BaseUseCase} out of the UI thread.
 */
public interface ThreadExecutor extends Executor {
}