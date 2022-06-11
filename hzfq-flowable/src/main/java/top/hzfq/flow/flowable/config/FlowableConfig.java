package top.hzfq.flow.flowable.config;

import org.flowable.bpmn.converter.BpmnXMLConverter;
import org.flowable.editor.language.json.converter.BpmnJsonConverter;
import org.flowable.idm.spring.SpringIdmEngineConfiguration;
import org.flowable.image.ProcessDiagramGenerator;
import org.flowable.image.impl.DefaultProcessDiagramGenerator;
import org.flowable.spring.boot.EngineConfigurationConfigurer;
import org.flowable.ui.common.util.XmlUtil;
import org.flowable.validation.ProcessValidator;
import org.flowable.validation.ProcessValidatorFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.hzfq.flow.flowable.idm.FlowUserDataManager;

import javax.annotation.Resource;
import javax.xml.stream.XMLInputFactory;

/**
 * @author huzhifengqing@qq.com
 * @since 2022/2/28 23:49
 */
@Configuration(proxyBeanMethods = false)
public class FlowableConfig {

    @Resource
    private FlowUserDataManager flowUserDataManager;

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

    @Bean
    public XMLInputFactory xmlInputFactory() {
        return XmlUtil.createSafeXmlInputFactory();
    }

    /**
     * 流程验证器
     */
    @Bean
    public ProcessValidator processValidator() {
        return new ProcessValidatorFactory().createDefaultProcessValidator();
    }

    @Bean
    public EngineConfigurationConfigurer<SpringIdmEngineConfiguration> hzfqIdmEngineConfigurer() {
        return idmEngineConfiguration -> {
            idmEngineConfiguration.setUserDataManager(flowUserDataManager);
        };
    }
}
