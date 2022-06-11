package top.hzfq.flow.flowable.service;

import org.flowable.bpmn.model.BpmnModel;
import org.flowable.ui.modeler.domain.Model;

/**
 * 流程业务定义操作
 *
 * @author huzhifengqing@qq.com
 * @since 2022/2/27 17:26
 */
public interface FlowableRepositoryService {

    void deploy(Model model, BpmnModel bpmnModel);

}
