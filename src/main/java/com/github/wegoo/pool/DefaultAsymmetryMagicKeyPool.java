package com.github.wegoo.pool;

import java.security.Key;
import java.security.KeyPair;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

public class DefaultAsymmetryMagicKeyPool implements IAsymmetryKeyMagicKeyPool {

  ConcurrentHashMap<String, BlockingDeque<KeyPair>> keyPool = new ConcurrentHashMap<>();


  @Override
  public void get() {

  }

  @Override
  public void init(List<IKeyAlgType> keyAlgTypes, float loadFactor) {
    keyAlgTypes.forEach(keyAlgType -> {
      keyPool.put(keyAlgType.getKeyAlg() + "_" + keyAlgType.getKeyLen(),
          new LinkedBlockingDeque<>(
              Math.min(Math.abs(keyAlgType.getCacheSize()), IMagicKeyPool.DEFAULT_MAX_POOL_SIZE)));
    });

  }

}
