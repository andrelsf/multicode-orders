package br.dev.multicode.mcorders.configs.database;

import java.util.UUID;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class CustomUUIDGenerator implements IdentifierGenerator {

  @Override
  public Object generate(
      SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
    final String[] uuidSplited = UUID.randomUUID()
        .toString()
        .split("-");
    return uuidSplited[2]
        .concat(uuidSplited[1])
        .concat(uuidSplited[0])
        .concat(uuidSplited[3])
        .concat(uuidSplited[4]);
  }
}
