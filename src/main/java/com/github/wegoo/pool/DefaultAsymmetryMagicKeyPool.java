package com.github.wegoo.pool;

import com.github.wegoo.pool.generator.IKeyPairGenerator;
import java.security.KeyPair;
import java.security.PublicKey;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public abstract class DefaultAsymmetryMagicKeyPool implements IAsymmetryKeyMagicKeyPool {

  ConcurrentHashMap<String, BlockingDeque<KeyPair>> keyPool = new ConcurrentHashMap<>(5);
  IKeyPairGenerator keyGenerator;

  public void init(List<IKeyAlgType> keyAlgTypes, float loadFactor, int bufferSize) {
    keyAlgTypes.forEach(keyAlgType -> {
      keyPool.put(keyAlgType.getKeyAlg() + "_" + keyAlgType.getKeyLen(),
          new LinkedBlockingDeque<>(
              Math.min(Math.abs(bufferSize), IMagicKeyPool.DEFAULT_MAX_POOL_SIZE)));
    });

  }

  @Override
  public void add(KeyPair keyPair) {
    PublicKey aPublic = keyPair.getPublic();
    String algorithm = aPublic.getAlgorithm();

  }


  void put(IKeyPairGenerator generator) {
    keyGenerator = generator;
  }


  @Override
  public KeyPair get(IKeyAlgType keyAlgType, long timeout, TimeUnit unit) {
    BlockingDeque<KeyPair> keyPairs = keyPool.get(IMagicKeyPool.getPoolsKey(keyAlgType));
    KeyPair keyPair;
    try {
      keyPair = keyPairs.poll(timeout, unit);
    } catch (Exception ignore) {
      keyPair = keyGenerator.generatorKeyPair(keyAlgType);
    }
    return keyPair;
  }



}
