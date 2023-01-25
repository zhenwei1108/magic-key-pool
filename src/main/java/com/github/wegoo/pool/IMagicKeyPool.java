package com.github.wegoo.pool;

import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author: zhangzhenwei
 * @description: IMagicKeyPool
 *  魔法密钥池，自补充
 * @date: 2023/1/25  14:28
 * @since: 1.0.0
 */
public interface IMagicKeyPool {

  /**
   * @author zhangzhenwei
   * @description 默认阈值。 密钥池达到此值时才会进行自动扩充
   * @date 2023/1/25  14:05
   * @since: 1.0.0
   */
  float DEFAULT_LOAD_FACTOR = 0.75f;
  /**
   * @author zhangzhenwei
   * @description 524 288 个
   *
   * @date 2023/1/25  14:17
   * @since: 1.0.0
   */
  int DEFAULT_MAX_POOL_SIZE = 1 << 19;

  /**
   * @author zhangzhenwei
   * @description init
   * 初始化密钥池
   * @param [keyAlgTypes] 算法、长度、缓冲数量
   * @return void
   * @date 2023/1/25  14:27
   * @since: 1.0.0
   */
  default void init(List<IKeyAlgType> keyAlgTypes) {
    init(keyAlgTypes, IMagicKeyPool.DEFAULT_LOAD_FACTOR);
  }

  /**
   * @author zhangzhenwei
   * @description init
   * 初始化擦欧洲
   * @param [keyAlgTypes, loadFactor]
   *       算法、长度、缓冲数量，  阈值
   * @return void
   * @date 2023/1/25  14:27
   * @since: 1.0.0
   */
  void init(List<IKeyAlgType> keyAlgTypes, float loadFactor);

}
