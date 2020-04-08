package code.service;

import code.domain.SysLog;

import java.util.List;

public interface ISysLogService {
    void save(SysLog sysLog) throws Exception;

    List<SysLog> findAll() throws Exception;

}
