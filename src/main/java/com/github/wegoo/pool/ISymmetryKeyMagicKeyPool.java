package com.github.wegoo.pool;

import java.security.Key;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

public interface ISymmetryKeyMagicKeyPool extends IMagicKeyPool {

  ConcurrentHashMap<String, BlockingDeque<Key>> keyPool = new ConcurrentHashMap<>();

  default void init(List<IKeyAlgType> keyAlgTypes, float loadFactor, int bufferSize) {
    keyAlgTypes.forEach(keyAlgType -> {
      keyPool.put(keyAlgType.getKeyAlg() + "_" + keyAlgType.getKeyLen(), new LinkedBlockingDeque<>(
          Math.min(Math.abs(bufferSize), IMagicKeyPool.DEFAULT_MAX_POOL_SIZE)));
    });
  }

  void add(Key key);

  Key get();


}
