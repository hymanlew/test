package hyman.utils2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import java.util.concurrent.*;

@Slf4j
public class ThreadPoolUtil {

    public static ThreadPoolExecutor threadPool;

    public  static void execute(Runnable runnable){
        getThreadPool().execute(runnable);
    }

    public  static <T> Future<T> submit(Callable<T> callable){
        return getThreadPool().submit(callable);
    }

    public static ThreadPoolExecutor getThreadPool() {
        if (threadPool != null) {
            showThreadPoolInfo();
            return threadPool;
        } else {
            synchronized (ThreadPoolUtil.class) {
                if (threadPool == null) {
                    threadPool = SpringUtils
                            .getBean("threadPoolExecutor");
                    //new ThreadPoolTaskExecutor(8, 16, 60,
                    //TimeUnit.SECONDS,
                    //new LinkedBlockingQueue<>(32),
                    //new ThreadPoolExecutor.CallerRunsPolicy());
                }
                showThreadPoolInfo();
                return threadPool;
            }
        }
    }

    private static void showThreadPoolInfo() {
        if (null == threadPool) {
            return;
        }
        log.info("taskCount [{}], completedTaskCount [{}], activeCount [{}], queueSize [{}]",
                threadPool.getTaskCount(),
                threadPool.getCompletedTaskCount(),
                threadPool.getActiveCount(),
                threadPool.getQueue().size());
    }

    public static void shutdownAndAwaitTermination(ExecutorService pool) {
        if (pool != null && !pool.isShutdown()) {
            //先使用shutdown, 停止接收新任务并尝试完成所有已存在任务
            pool.shutdown();
            try {
                if (!pool.awaitTermination(120, TimeUnit.SECONDS)) {
                    //如果超时, 则调用shutdownNow，
                    //取消在workQueue中Pending的任务,并中断所有阻塞函数
                    pool.shutdownNow();
                    if (!pool.awaitTermination(120, TimeUnit.SECONDS)) {
                        //如果仍然超時，則強制退出
                        logger.info("Pool did not terminate");
                        Thread.currentThread().interrupt();
                    }
                }
            }catch (InterruptedException ie) {
                //另外处理异常时，关闭线程池，并对当前调用的线程做中断处理
                pool.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }

    // 打印线程异常信息，扩展线程池的 afterExecute(r, t) 方法
    public static void printException(Runnable r, Throwable t) {
        if (t == null && r instanceof Future<?>) {
            try {
                Future<?> future = (Future<?>) r;
                if (future.isDone()) {
                    future.get();
                }
            }catch (CancellationException ce) {
                t = ce;
            }catch (ExecutionException ee) {
                t = ee.getCause();
            }catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }
        if (t != null) {
            logger.error(t.getMessage(), t);
        }
    }
}
