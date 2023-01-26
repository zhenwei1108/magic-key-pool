package com.github.wegoo.pool.generator;

import com.github.wegoo.pool.IKeyAlgType;
import java.security.Key;
import java.security.KeyPair;

public interface IKeyGenerator {

  Key generatorKey(IKeyAlgType iKeyAlgType);

}
