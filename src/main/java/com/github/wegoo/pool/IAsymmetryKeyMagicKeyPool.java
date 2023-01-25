package com.github.wegoo.pool;

import java.security.KeyPair;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author: zhangzhenwei
 * @description: IAsymmetryKeyMagicKeyPool
 *  非对称密钥池
 * @date: 2023/1/25  14:00
 * @since: 1.0.0
 */
public interface IAsymmetryKeyMagicKeyPool extends IMagicKeyPool {

  ConcurrentHashMap<String, BlockingDeque<KeyPair>> keyPool = new ConcurrentHashMap<>();

  default void init(List<IKeyAlgType> keyAlgTypes, float loadFactor) {
    keyAlgTypes.forEach(keyAlgType -> {
      keyPool.put(keyAlgType.getKeyAlg() + "_" + keyAlgType.getKeyLen(),
          new LinkedBlockingDeque<>(
              Math.min(Math.abs(keyAlgType.getBufferSize()), IMagicKeyPool.DEFAULT_MAX_POOL_SIZE)));
    });
  }

  void add(KeyPair keyPair);

  KeyPair get();

}
