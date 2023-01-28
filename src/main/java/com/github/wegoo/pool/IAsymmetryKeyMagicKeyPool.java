package com.github.wegoo.pool;

import java.security.KeyPair;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * @author: zhangzhenwei
 * @description: IAsymmetryKeyMagicKeyPool
 *  非对称密钥池
 * @date: 2023/1/25  14:00
 * @since: 1.0.0
 */
public interface IAsymmetryKeyMagicKeyPool extends IMagicKeyPool {

  ConcurrentHashMap<String, BlockingDeque<KeyPair>> keyPool = new ConcurrentHashMap<>();

  default void init(List<IKeyAlgType> keyAlgTypes, float loadFactor, int bufferSize) {
    keyAlgTypes.forEach(keyAlgType -> {
      keyPool.put(IMagicKeyPool.getPoolsKey(keyAlgType), new LinkedBlockingDeque<>(
          Math.min(Math.abs(bufferSize), IMagicKeyPool.DEFAULT_MAX_POOL_SIZE)));
    });
  }

  /**
   * @author zhangzhenwei
   * @description add 
   * 补充密钥方法
   * @param [keyPair]
   * @return void
   * @date 2023/1/28  20:56
   * @since: 1.0.0 
   */
  void add(KeyPair keyPair);

  /**
   * @author zhangzhenwei
   * @description get 
   * 从缓冲池获取密钥
   * @param [keyAlgType, timeout, unit]
   * @return java.security.KeyPair
   * @date 2023/1/28  20:51
   * @since: 1.0.0 
   */
  KeyPair get(IKeyAlgType keyAlgType, long timeout, TimeUnit unit);

}
