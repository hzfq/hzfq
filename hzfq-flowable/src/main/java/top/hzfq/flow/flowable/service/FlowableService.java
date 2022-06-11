package top.hzfq.flow.flowable.service;

import org.flowable.engine.*;
import org.flowable.ui.modeler.serviceapi.ModelService;

/**
 * @author huzhifengqing@qq.com
 * @since 2022/2/27 17:05
 */
public interface FlowableService {

    /**
     * UI Modeler 模型服务
     */
    ModelService getModelService();

    /**
     * 流程引擎
     */
    ProcessEngine getProcessEngine();

    /**
     * 业务流程定义
     */
    RepositoryService getRepositoryService();

    /**
     * 流程对象实例
     */
    RuntimeService getRuntimeService();

    /**
     * 流程任务节点
     */
    TaskService getTaskService();

    /**
     * 流程历史信息
     */
    HistoryService getHistoryService();

    /**
     * 流程表单
     */
    FormService getFormService();

    /**
     * 用户及组管理
     */
    IdentityService getIdentityService();

    /**
     * 管理维护
     */
    ManagementService getManagementService();

    /**
     * 动态流程
     */
    DynamicBpmnService getDynamicBpmnService();
}

