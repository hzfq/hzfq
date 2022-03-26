package top.hzfq.flowable.service;

import org.flowable.bpmn.model.BpmnModel;

import java.io.InputStream;
import java.util.List;

/**
 * 流程图例生成器
 *
 * @author huzhifengqing@qq.com
 * @since 2022/2/28 23:47
 */
public interface FlowableProcessDiagramService {

    /**
     * 生成PNG图片流
     *
     * @param bpmnModel             模型
     * @param highLightedActivities 活动节点
     * @param highLightedFlows      高亮线
     */
    InputStream generatePngDiagram(BpmnModel bpmnModel, List<String> highLightedActivities, List<String> highLightedFlows);

    /**
     * 生成PNG图片流
     *
     * @param bpmnModel 模型
     */
    InputStream generatePngDiagram(BpmnModel bpmnModel);
}
