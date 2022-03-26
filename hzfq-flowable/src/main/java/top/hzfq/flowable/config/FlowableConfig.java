package top.hzfq.flowable.config;

import org.flowable.bpmn.converter.BpmnXMLConverter;
import org.flowable.editor.language.json.converter.BpmnJsonConverter;
import org.flowable.image.ProcessDiagramGenerator;
import org.flowable.image.impl.DefaultProcessDiagramGenerator;
import org.flowable.validation.ProcessValidator;
import org.flowable.validation.ProcessValidatorFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author huzhifengqing@qq.com
 * @since 2022/2/28 23:49
 */
@Configuration
public class FlowableConfig {

    @Bean
    public ProcessDiagramGenerator processDiagramGenerator() {
        return new DefaultProcessDiagramGenerator();
    }

    @Bean
    public BpmnXMLConverter bpmnXMLConverter() {
        return new BpmnXMLConverter();
    }

    @Bean
    public BpmnJsonConverter bpmnJsonConverter() {
        return new BpmnJsonConverter();
    }

    /**
     * 流程验证器
     */
    @Bean
    public ProcessValidator processValidator() {
        return new ProcessValidatorFactory().createDefaultProcessValidator();
    }
}
