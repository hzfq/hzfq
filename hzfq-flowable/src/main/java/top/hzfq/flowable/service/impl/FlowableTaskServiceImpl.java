package top.hzfq.flowable.service.impl;

import org.flowable.engine.TaskService;
import org.springframework.stereotype.Service;
import top.hzfq.flowable.service.FlowableTaskService;

import javax.annotation.Resource;

/**
 * @author huzhifengqing@qq.com
 * @since 2022/2/27 17:54
 */
@Service
public class FlowableTaskServiceImpl implements FlowableTaskService {

    @Resource
    private TaskService taskService;
}
