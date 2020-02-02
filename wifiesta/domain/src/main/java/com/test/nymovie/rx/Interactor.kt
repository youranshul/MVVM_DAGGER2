import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.SingleObserver


abstract class SingleInteractor<T, PARAMS>(
    private val executionThread: ExecutionThread,
    private val postExecutionThread: PostExecutionThread
) {
    protected abstract fun buildUseCase(params: PARAMS?): Single<T>

    fun execute(observer: SingleObserver<T>?, params: PARAMS? = null): SingleObserver<T>? {
        return  this.buildUseCase(params)
            .subscribeOn(executionThread.scheduler)
            .observeOn(postExecutionThread.scheduler)
            .subscribeWith(observer)
    }
}

interface PostExecutionThread {
    val scheduler: Scheduler
}

interface ExecutionThread {
    val scheduler: Scheduler
}
