package top.hzfq.flow.flowable.service.impl;

import org.flowable.bpmn.model.BpmnModel;
import org.flowable.engine.RepositoryService;
import org.flowable.ui.modeler.domain.Model;
import org.springframework.stereotype.Service;
import top.hzfq.flow.flowable.service.FlowableRepositoryService;
import top.hzfq.flow.flowable.util.FlowableConstant;

import javax.annotation.Resource;

/**
 * @author huzhifengqing@qq.com
 * @since 2022/2/27 17:54
 */
@Service
public class FlowableRepositoryServiceImpl implements FlowableRepositoryService {

    @Resource
    private RepositoryService repositoryService;

    @Override
    public void deploy(Model model, BpmnModel bpmnModel) {
        repositoryService.createDeployment()
                .name(model.getName())
                .key(model.getKey())
                .category(FlowableConstant.category)
                .tenantId(FlowableConstant.tenantId)
                .addBpmnModel(model.getKey() + ".bpmn", bpmnModel)
                .deploy();
    }
}
