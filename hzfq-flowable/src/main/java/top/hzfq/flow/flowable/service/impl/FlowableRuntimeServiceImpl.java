package top.hzfq.flow.flowable.service.impl;

import org.flowable.engine.RuntimeService;
import org.springframework.stereotype.Service;
import top.hzfq.flow.flowable.service.FlowableRuntimeService;

import javax.annotation.Resource;

/**
 * @author huzhifengqing@qq.com
 * @since 2022/2/27 17:54
 */
@Service
public class FlowableRuntimeServiceImpl implements FlowableRuntimeService {

    @Resource
    private RuntimeService runtimeService;

}
