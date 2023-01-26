package com.github.wegoo.pool.generator;

import com.github.wegoo.pool.IKeyAlgType;
import java.security.KeyPair;

public interface IKeyPairGenerator {

  KeyPair generatorKeyPair(IKeyAlgType iKeyAlgType);


}
