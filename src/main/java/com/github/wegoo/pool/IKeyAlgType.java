package com.github.wegoo.pool;

/**
 * @author: zhangzhenwei
 * @description: IKeyAlgType
 *  算法类型抽象
 * @date: 2023/1/25  12:35
 * @since: 1.0.0
 */
public interface IKeyAlgType {

  /**
   * @author zhangzhenwei
   * @description getKeyAlg
   * 获取密钥算法
   * @return java.lang.String
   * @date 2023/1/25  12:35
   * @since: 1.0.0
   */
  String getKeyAlg();

  /**
   * @author zhangzhenwei
   * @description getKeyLen
   * 获取密钥长度
   * @return int
   * @date 2023/1/25  12:35
   * @since: 1.0.0
   */
  int getKeyLen();

  /**
   * @author zhangzhenwei
   * @description getCacheSize
   * 获取密钥缓冲数量
   * @return int
   * @date 2023/1/25  13:47
   * @since: 1.0.0
   */
  int getBufferSize();


}
