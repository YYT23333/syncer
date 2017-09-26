package com.github.zzt93.syncer.config.pipeline.output;

import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zzt
 */
public class PipelineBatch {

  private final Logger logger = LoggerFactory.getLogger(PipelineBatch.class);

  private final TimeUnit delayTimeUnit = TimeUnit.MILLISECONDS;
  /**
   * default is 100, soft limit
   */
  private int size = 100;
  /**
   * delay in {@link TimeUnit#MILLISECONDS}, default is 100
   */
  private int delay = 100;

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    if (size < 0) {
      IllegalArgumentException illegalArgumentException = new IllegalArgumentException(
          "`batch.size` is invalid, minimum is 0");
      logger.error("Illegal settings of `batch.size`", illegalArgumentException);
      throw illegalArgumentException;
    }
    this.size = size;
  }

  public int getDelay() {
    return delay;
  }

  public void setDelay(int delay) {
    if (delay < 50) {
      IllegalArgumentException exception = new IllegalArgumentException(
          "Delay is too small, may affect performance. If you want to disable batch, set size to 0.");
      logger.error("Illegal settings of `batch.delay`", exception);
      throw exception;
    }
    this.delay = delay;
  }

  public TimeUnit getDelayTimeUnit() {
    return delayTimeUnit;
  }
}
