package top.hzfq.flowable.service.impl;

import org.flowable.bpmn.model.BpmnModel;
import org.flowable.image.ProcessDiagramGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.hzfq.flowable.service.FlowableProcessDiagramService;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.List;

/**
 * @author huzhifengqing@qq.com
 * @since 2022/2/28 23:48
 */
@Service
public class FlowableProcessDiagramServiceImpl implements FlowableProcessDiagramService {

    private static final String IMAGE_TYPE = "png";
    @Value("${flowable.activityFontName}")
    private String activityFontName;
    @Value("${flowable.labelFontName}")
    private String labelFontName;
    @Value("${flowable.annotationFontName}")
    private String annotationFontName;
    @Resource
    private ProcessDiagramGenerator generator;

    @Override
    public InputStream generatePngDiagram(BpmnModel bpmnModel, List<String> highLightedActivities, List<String> highLightedFlows) {
        return generator.generateDiagram(bpmnModel, IMAGE_TYPE, highLightedActivities, highLightedFlows,
                activityFontName, labelFontName, annotationFontName, null, 1.0, true);
    }

    @Override
    public InputStream generatePngDiagram(BpmnModel bpmnModel) {
        return generator.generateDiagram(bpmnModel, IMAGE_TYPE, activityFontName, labelFontName, annotationFontName,
                null, 1.0, true);
    }
}
